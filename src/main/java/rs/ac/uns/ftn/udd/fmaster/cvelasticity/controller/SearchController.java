package rs.ac.uns.ftn.udd.fmaster.cvelasticity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.udd.fmaster.cvelasticity.dtos.Field;
import rs.ac.uns.ftn.udd.fmaster.cvelasticity.dtos.GeoSearch;
import rs.ac.uns.ftn.udd.fmaster.cvelasticity.dtos.NameAndSurname;
import rs.ac.uns.ftn.udd.fmaster.cvelasticity.model.Education;
import rs.ac.uns.ftn.udd.fmaster.cvelasticity.model.IndexUnit;
import rs.ac.uns.ftn.udd.fmaster.cvelasticity.service.IndexUnitService;
import rs.ac.uns.ftn.udd.fmaster.cvelasticity.service.SearchService;

@RestController
public class SearchController {

	@Autowired
	SearchService search;
	@Autowired
	private IndexUnitService indexUnitService;
	
	@PostMapping(value="/search/cv/name", consumes="application/json")
	public ResponseEntity<List<String>> searchNameAndSurname(@RequestBody NameAndSurname data) throws Exception {
		return new ResponseEntity<List<String>>(search.findAllCVsByNameAndSurname(data.getName(), data.getSurname()), HttpStatus.OK);
	}
	
	@PostMapping(value="/search/cv/education", consumes="application/json")
	public ResponseEntity<List<String>> searchEducation(@RequestBody Education data) throws Exception {
		return new ResponseEntity<List<String>>(search.findAllCVsByEducation(data.getEducationlevel(), data.getEducationgrade()), HttpStatus.OK);
	}
	
	@PostMapping(value="/search/cv/terms", consumes="application/json")
	public ResponseEntity<List<String>> searchCV(@RequestBody String data) throws Exception {
		return new ResponseEntity<List<String>>(search.findAllCVsByTerms(data), HttpStatus.OK);
	}
	
	@PostMapping(value="/search/bool", consumes="application/json")
	public ResponseEntity<List<String>> searchBool(@RequestBody String data) throws Exception {
		return new ResponseEntity<List<String>>(search.findAllByBooleanQuery(data), HttpStatus.OK);
	}
	
	@PostMapping(value="/search/nearby", consumes="application/json")
	public ResponseEntity<List<String>> searchNearby(@RequestBody GeoSearch data) throws Exception {
		IndexUnit unit = new IndexUnit();
		unit.setCity(data.getCity());
		unit.setAddress("");
		unit.setZipcode("");
		return new ResponseEntity<List<String>>(search.findAllByGeoSearch(data.getRadius(), indexUnitService.calculateGeocodedLongLat(unit).getGeo()), HttpStatus.OK);
	}
	
	@PostMapping(value="/search/phrase", consumes="application/json")
	public ResponseEntity<List<String>> searchPhrase(@RequestBody Field data) throws Exception {
		return new ResponseEntity<List<String>>(search.findAllByPhraseQuery(data), HttpStatus.OK);
	}
	
	@PostMapping(value="/search/cv/terms/highlight", consumes="application/json")
	public ResponseEntity<List<String>> searchCVHighlight(@RequestBody String data) throws Exception {
		return new ResponseEntity<List<String>>(search.findAllCVsByTermsHighlight(data), HttpStatus.OK);
	}
	
	@PostMapping(value="/search/phrase/highlight", consumes="application/json")
	public ResponseEntity<List<String>> searchPhraseHighlight(@RequestBody Field data) throws Exception {
		return new ResponseEntity<List<String>>(search.findAllByPhraseQueryHighlight(data), HttpStatus.OK);
	}
	
	@PostMapping(value="/stat/city", consumes="application/json")
	public ResponseEntity<List<String>> statCity() throws Exception {
		return new ResponseEntity<List<String>>(search.findCityStat1(), HttpStatus.OK);
	}
}
