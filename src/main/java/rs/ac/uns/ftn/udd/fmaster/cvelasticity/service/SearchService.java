package rs.ac.uns.ftn.udd.fmaster.cvelasticity.service;

import java.util.List;

public interface SearchService {

	List<String> findAllCVsByNameAndSurname(String name, String surname) throws Exception;
	List<String> findAllCVsByEducation(String level, String grade) throws Exception;
}
