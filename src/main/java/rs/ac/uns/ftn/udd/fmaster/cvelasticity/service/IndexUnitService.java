package rs.ac.uns.ftn.udd.fmaster.cvelasticity.service;

import rs.ac.uns.ftn.udd.fmaster.cvelasticity.model.IndexUnit;

public interface IndexUnitService {

	IndexUnit recalculateIndexUnitFields(IndexUnit unit);
	
	IndexUnit calculateGeocodedLongLat(IndexUnit unit);
	
	String reverseGeocodeCity(Double lat, Double lon);
}
