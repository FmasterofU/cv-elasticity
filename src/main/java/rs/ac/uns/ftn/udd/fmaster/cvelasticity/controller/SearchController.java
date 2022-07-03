package rs.ac.uns.ftn.udd.fmaster.cvelasticity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.udd.fmaster.cvelasticity.dtos.NameAndSurname;
import rs.ac.uns.ftn.udd.fmaster.cvelasticity.model.Education;
import rs.ac.uns.ftn.udd.fmaster.cvelasticity.service.SearchService;

@RestController
public class SearchController {

	@Autowired
	SearchService search;
	
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
}
