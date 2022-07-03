package rs.ac.uns.ftn.udd.fmaster.cvelasticity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.udd.fmaster.cvelasticity.dtos.NameAndSurname;
import rs.ac.uns.ftn.udd.fmaster.cvelasticity.service.SearchService;

@RestController
public class SearchController {

	@Autowired
	SearchService search;
	
	@PostMapping(value="/search/cv/name", consumes="application/json")
	public ResponseEntity<List<String>> searchTermQuery(@RequestBody NameAndSurname data) throws Exception {
		return new ResponseEntity<List<String>>(search.findAllCVsByNameAndSurname(data.getName(), data.getSurname()), HttpStatus.OK);
	}
	
}
