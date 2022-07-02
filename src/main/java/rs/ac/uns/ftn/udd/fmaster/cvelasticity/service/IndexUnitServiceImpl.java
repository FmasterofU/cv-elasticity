package rs.ac.uns.ftn.udd.fmaster.cvelasticity.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rs.ac.uns.ftn.udd.fmaster.cvelasticity.dtos.AddressGeocoding;
import rs.ac.uns.ftn.udd.fmaster.cvelasticity.dtos.IPGeolocation;
import rs.ac.uns.ftn.udd.fmaster.cvelasticity.model.IndexUnit;

public class IndexUnitServiceImpl implements IndexUnitService {

	public IndexUnit calculateGeolocatedLongLat(IndexUnit unit) {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
			.url("https://ip-geolocation-ipwhois-io.p.rapidapi.com/json/?ip=" + unit.getIp())
			.get()
			.addHeader("X-RapidAPI-Key", "728cbcd951msh66588696af0f8acp1e144fjsn362b114c907e")
			.addHeader("X-RapidAPI-Host", "ip-geolocation-ipwhois-io.p.rapidapi.com")
			.build();

		Response response;
		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			e.printStackTrace();
			return unit;
		}
		
		ObjectMapper mapper = new ObjectMapper();
		IPGeolocation result;
		try {
			result = mapper.readValue(response.body().string(), IPGeolocation.class);
		} catch (IOException e) {
			e.printStackTrace();
			return unit;
		}
		
		unit.setIplatitude(result.getLatitude());
		unit.setIplongitude(result.getLongitude());
		
		return unit;
	}
	
	public IndexUnit calculateGeocodedLongLat(IndexUnit unit) {
		OkHttpClient client = new OkHttpClient();

		Request request;
		try {
			request = new Request.Builder()
				.url("https://forward-reverse-geocoding.p.rapidapi.com/v1/search?q=" + URLEncoder.encode(unit.getAddress() + ", " + unit.getZipcode() + " " + unit.getCity(), StandardCharsets.UTF_8.toString()))
				.get()
				.addHeader("X-RapidAPI-Key", "728cbcd951msh66588696af0f8acp1e144fjsn362b114c907e")
				.addHeader("X-RapidAPI-Host", "forward-reverse-geocoding.p.rapidapi.com")
				.build();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return unit;
		}

		Response response;
		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			e.printStackTrace();
			return unit;
		}
		
		ObjectMapper mapper = new ObjectMapper();
		AddressGeocoding result;
		try {
			result = mapper.readValue(response.body().string(), AddressGeocoding.class);
		} catch (IOException e) {
			e.printStackTrace();
			return unit;
		}
		
		unit.setLongitude(Double.parseDouble(result.getLon()));
		unit.setLatitude(Double.parseDouble(result.getLat()));
		
		return unit;
	}
	
	@Override
	public IndexUnit recalculateIndexUnitFields(IndexUnit unit) {
		unit = calculateGeolocatedLongLat(unit);
		unit = calculateGeocodedLongLat(unit);
		return unit;
	}

}
