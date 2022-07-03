package rs.ac.uns.ftn.udd.fmaster.cvelasticity.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = IndexUnit.INDEX_NAME, shards = 1, replicas = 0)
public class IndexUnit {

	public static final String INDEX_NAME = "applicationlibrary";
	public static final String TYPE_NAME = "application";
	
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	
	@Field(analyzer = "serbian", searchAnalyzer = "serbian", type = FieldType.Text, store = true)
	private String name;
	
	@Field(analyzer = "serbian", searchAnalyzer = "serbian", type = FieldType.Text, store = true)
	private String surname;
	
	@Field(analyzer = "serbian", searchAnalyzer = "serbian", type = FieldType.Text, store = true)
	private String email;
	
	@Field(analyzer = "serbian", searchAnalyzer = "serbian", type = FieldType.Nested, store = true)
	private Education education;
	
	@Field(type = FieldType.Double, store = true)
	private Double latitude;
	
	@Field(type = FieldType.Double, store = true)
	private Double longitude;
	
	@Field(analyzer = "serbian", searchAnalyzer = "serbian", type = FieldType.Text, store = true)
	private String cvtext;
	
	@Id
	@Field(type = FieldType.Text, searchAnalyzer = "serbian", store = true)
	private String filename;
	
	@Field(type = FieldType.Double, store = true)
	private Double iplatitude;
	
	@Field(type = FieldType.Double, store = true)
	private Double iplongitude;
	
	@Field(type = FieldType.Text, store = true)
	private String ip;
	
	@Field(analyzer = "serbian", searchAnalyzer = "serbian", type = FieldType.Text, store = true)
	private String address;
	
	@Field(analyzer = "serbian", searchAnalyzer = "serbian", type = FieldType.Text, store = true)
	private String city;
	
	@Field(type = FieldType.Text, store = true)
	private String zipcode;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getCvtext() {
		return cvtext;
	}

	public void setCvtext(String cvtext) {
		this.cvtext = cvtext;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Double getIplatitude() {
		return iplatitude;
	}

	public void setIplatitude(Double iplatitude) {
		this.iplatitude = iplatitude;
	}

	public Double getIplongitude() {
		return iplongitude;
	}

	public void setIplongitude(Double iplongitude) {
		this.iplongitude = iplongitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
}
