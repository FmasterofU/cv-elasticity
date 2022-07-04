package rs.ac.uns.ftn.udd.fmaster.cvelasticity.service;

import java.util.List;

import org.elasticsearch.common.geo.GeoPoint;

import rs.ac.uns.ftn.udd.fmaster.cvelasticity.dtos.Field;


public interface SearchService {

	List<String> findAllCVsByNameAndSurname(String name, String surname) throws Exception;
	List<String> findAllCVsByEducation(String level, String grade) throws Exception;
	List<String> findAllCVsByTerms(String data) throws Exception;
	List<String> findAllByBooleanQuery(String data) throws Exception;
	List<String> findAllByGeoSearch(Integer radius, GeoPoint geo) throws Exception;
	List<String> findAllByPhraseQuery(Field data) throws Exception;
	List<String> findAllCVsByTermsHighlight(String data) throws Exception;
}
