package rs.ac.uns.ftn.udd.fmaster.cvelasticity.service;

import javax.servlet.http.HttpServletRequest;

public interface RequestService {
	
	String getClientIp(HttpServletRequest request);
	
}