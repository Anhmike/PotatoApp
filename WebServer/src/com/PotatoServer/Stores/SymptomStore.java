package com.PotatoServer.Stores;

import org.joda.time.DateTime;

public class SymptomStore {
	int id;
	String description;
	Integer parentSymptom;
	DateTime updateDate;
	String imageLocation;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getParentSymptom() {
		return parentSymptom;
	}
	public void setParentSymptom(Integer parentSymptom) {
		this.parentSymptom = parentSymptom;
	}
	public DateTime getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(DateTime updateDate) {
		this.updateDate = updateDate;
	}
	public String getImageLocation() {
		return imageLocation;
	}
	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}
	
}
