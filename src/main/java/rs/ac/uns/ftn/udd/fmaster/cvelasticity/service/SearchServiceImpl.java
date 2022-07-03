package rs.ac.uns.ftn.udd.fmaster.cvelasticity.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import rs.ac.uns.ftn.udd.fmaster.cvelasticity.model.IndexUnit;
import rs.ac.uns.ftn.udd.fmaster.cvelasticity.repository.UnitRepository;

@SuppressWarnings("deprecation")
@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	UnitRepository repository;
	
	private RestHighLevelClient client = new RestHighLevelClient(
			RestClient.builder(new HttpHost("localhost", 9200, "http"), new HttpHost("localhost", 9300, "http")));;
	
	private String toHexString(byte[] ba) {
	    StringBuilder str = new StringBuilder();
	    for(int i = 0; i < ba.length; i++)
	        str.append(String.format("%x", ba[i]));
	    return str.toString();
	}
	
	private String hash(String data) {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		messageDigest.update(data.getBytes());
		String stringHash = new String(messageDigest.digest());
		return toHexString(stringHash.getBytes());
	}
	
	@Override
	public List<String> findAllCVsByNameAndSurname(String name, String surname) throws Exception {
		List<IndexUnit> units;
		if((name == null || name == "") && (surname == null || surname == ""))
			throw new Exception("Epic Fail! both fields are empty.");
		else if (name == null || name == "")
			units = repository.findBySurname(surname);
		else if (surname == null || surname == "")
			units = repository.findByName(name);
		else
			units = repository.findByNameAndSurname(name, surname);
		List<String> ret = new ArrayList<String>();
		for(IndexUnit indexUnit : units)
			ret.add("id: " + hash(indexUnit.getFilename()) + "\nname: " + indexUnit.getName() + "\nsurname: " + indexUnit.getSurname() + "\nCV: \n" + indexUnit.getCvtext());
		return ret;
	}

	@Override
	public List<String> findAllCVsByEducation(String level, String grade) throws Exception {
		List<IndexUnit> units;
		if((level == null || level == "") && (grade == null || grade == ""))
			throw new Exception("Epic Fail! both fields are empty.");
		else if (level == null || level == "")
			units = repository.findByEducation_Educationgrade(grade);
		else if (grade == null || grade == "")
			units = repository.findByEducation_Educationlevel(level);
		else
			units = repository.findByEducation_EducationlevelAndEducation_Educationgrade(level, grade);
		List<String> ret = new ArrayList<String>();
		for(IndexUnit indexUnit : units)
			ret.add("id: " + hash(indexUnit.getFilename()) + "\nname: " + indexUnit.getName() + "\nsurname: " + indexUnit.getSurname() + "\neducation level: " + indexUnit.getEducation().getEducationlevel() + "\neducation grade: " + indexUnit.getEducation().getEducationgrade() + "\nCV: \n" + indexUnit.getCvtext());
		return ret;
	}

	@Override
	public List<String> findAllCVsByTerms(String data) throws Exception {
		QueryStringQueryBuilder queryBuilder = new QueryStringQueryBuilder(data);
		queryBuilder.defaultField("cvtext");
		queryBuilder.defaultOperator(Operator.AND);
		List<IndexUnit> result = search(queryBuilder);
		List<String> ret = new ArrayList<String>();
		for(IndexUnit indexUnit : result)
			ret.add("id: " + hash(indexUnit.getFilename()) + "\nname: " + indexUnit.getName() + "\nsurname: " + indexUnit.getSurname() + "\nCV: \n" + indexUnit.getCvtext());
		return ret;
	}
	
	private List<IndexUnit> search(QueryBuilder queryBuilder) throws Exception {
		ArrayList<IndexUnit> units = new ArrayList<IndexUnit>();
		SearchRequest searchRequest = new SearchRequest(IndexUnit.INDEX_NAME);
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		sourceBuilder.query(queryBuilder);
		searchRequest.source(sourceBuilder);
		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		SearchHit[] searchHits = searchResponse.getHits().getHits();
		for(SearchHit hit : searchHits)
			units.add(new ObjectMapper().convertValue(hit.getSourceAsMap(), IndexUnit.class));
		return units;
	}

}
