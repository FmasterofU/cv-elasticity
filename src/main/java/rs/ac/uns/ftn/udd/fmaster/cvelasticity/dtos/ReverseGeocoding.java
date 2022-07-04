package rs.ac.uns.ftn.udd.fmaster.cvelasticity.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReverseGeocoding {

	private GeocodedCity address;

	public GeocodedCity getAddress() {
		return address;
	}

	public void setAddress(GeocodedCity address) {
		this.address = address;
	}
	
	
}
