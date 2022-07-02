package rs.ac.uns.ftn.udd.fmaster.cvelasticity.model;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

public class Education {
	
	@Field(analyzer = "serbian", type = FieldType.String, index = FieldIndex.analyzed, store = true)
	private String educationlevel;
	
	@Field(analyzer = "serbian", type = FieldType.String, index = FieldIndex.analyzed, store = true)
	private String educationgrade;

	public String getEducationlevel() {
		return educationlevel;
	}

	public void setEducationlevel(String educationlevel) {
		this.educationlevel = educationlevel;
	}

	public String getEducationgrade() {
		return educationgrade;
	}

	public void setEducationgrade(String educationgrade) {
		this.educationgrade = educationgrade;
	}
}
