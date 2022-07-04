package rs.ac.uns.ftn.udd.fmaster.cvelasticity.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rs.ac.uns.ftn.udd.fmaster.cvelasticity.dtos.AddressGeocoding;
import rs.ac.uns.ftn.udd.fmaster.cvelasticity.dtos.IPGeolocation;
import rs.ac.uns.ftn.udd.fmaster.cvelasticity.dtos.ReverseGeocoding;
import rs.ac.uns.ftn.udd.fmaster.cvelasticity.model.IndexUnit;

@Service
public class IndexUnitServiceImpl implements IndexUnitService {

	public IndexUnit calculateGeolocatedLongLat(IndexUnit unit) {
		OkHttpClient client = new OkHttpClient();
		System.out.println(unit.getIp());
		Request request = new Request.Builder()
			.url("https://ip-geolocation-ipwhois-io.p.rapidapi.com/json/?ip=" + "133.133.133.133")
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
		String temp;
		try {
			temp = response.body().string();
			System.out.println(temp);
			result = mapper.readValue(temp, IPGeolocation.class);
		} catch (IOException e) {
			e.printStackTrace();
			return unit;
		}
		
		unit.setIpgeo(new GeoPoint(result.getLatitude(), result.getLongitude()));
		
		return unit;
	}
	
	public IndexUnit calculateGeocodedLongLat(IndexUnit unit) {
		OkHttpClient client = new OkHttpClient();

		Request request;
		try {
			String encodedAddr = URLEncoder.encode(unit.getAddress() + ", " + unit.getZipcode() + " " + unit.getCity(), StandardCharsets.UTF_8.toString()).replace("+", "%20");
			System.out.println(encodedAddr);
			request = new Request.Builder()
				.url("https://forward-reverse-geocoding.p.rapidapi.com/v1/search?q=" + encodedAddr + "&accept-language=en&polygon_threshold=0.0")
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
		AddressGeocoding[] result;
		String temp;
		try {
			temp = response.body().string();
			System.out.println(temp);
			result = mapper.readValue(temp, AddressGeocoding[].class);
		} catch (IOException e) {
			e.printStackTrace();
			return unit;
		}
		
		try {
			unit.setGeo(new GeoPoint(Double.parseDouble(result[0].getLat()), Double.parseDouble(result[0].getLon())));
		} catch (Exception e) {
			e.printStackTrace();
			return unit;
		}
		
		return unit;
	}
	
	@Override
	public IndexUnit recalculateIndexUnitFields(IndexUnit unit) {
		unit = calculateGeolocatedLongLat(unit);
		unit = calculateGeocodedLongLat(unit);
		return unit;
	}

	@Override
	public String reverseGeocodeCity(Double lat, Double lon) {
		OkHttpClient client = new OkHttpClient();

		System.out.println("lat=" + Double.toString(lat) + "lon=" + Double.toString(lon));
		Request request = new Request.Builder()
			.url("https://forward-reverse-geocoding.p.rapidapi.com/v1/reverse?lat=" + Double.toString(lat) + "&lon=" + Double.toString(lon) + "&accept-language=en&polygon_threshold=0.0")
			.get()
			.addHeader("X-RapidAPI-Key", "728cbcd951msh66588696af0f8acp1e144fjsn362b114c907e")
			.addHeader("X-RapidAPI-Host", "forward-reverse-geocoding.p.rapidapi.com")
			.build();

		Response response;
		try {
			response = client.newCall(request).execute();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		ObjectMapper mapper = new ObjectMapper();
		ReverseGeocoding result;
		String temp;
		try {
			temp = response.body().string();
			System.out.println(temp);
			result = mapper.readValue(temp, ReverseGeocoding.class);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		return result.getAddress().getCity();
	}

}
