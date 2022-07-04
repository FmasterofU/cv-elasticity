package rs.ac.uns.ftn.udd.fmaster.cvelasticity.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import rs.ac.uns.ftn.udd.fmaster.cvelasticity.model.IndexUnit;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HighlightedIndexUnit extends IndexUnit {

	private String highlight;

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}
	
}
