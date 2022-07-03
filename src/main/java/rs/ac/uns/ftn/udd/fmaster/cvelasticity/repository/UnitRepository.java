package rs.ac.uns.ftn.udd.fmaster.cvelasticity.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import rs.ac.uns.ftn.udd.fmaster.cvelasticity.model.IndexUnit;

public interface UnitRepository extends ElasticsearchRepository<IndexUnit, String> {

	List<IndexUnit> findByName(String name);
	
	List<IndexUnit> findBySurname(String surname);

	List<IndexUnit> findByNameAndSurname(String name, String surname);

	IndexUnit findByFilename(String filename);
	
	List<IndexUnit> findByEducation_Educationgrade(String educationgrade);
	
	List<IndexUnit> findByEducation_Educationlevel(String educationlevel);
	
	List<IndexUnit> findByEducation_EducationlevelAndEducation_Educationgrade(String educationlevel, String educationgrade);
}
