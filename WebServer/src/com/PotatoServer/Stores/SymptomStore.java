package com.PotatoServer.Stores;

import org.joda.time.DateTime;

public class SymptomStore {
	int id;
	String description;
	int parentSymptom;
	DateTime updateDate;
	
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
	public int getParentSymptom() {
		return parentSymptom;
	}
	public void setParentSymptom(int parentSymptom) {
		this.parentSymptom = parentSymptom;
	}
	public DateTime getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(DateTime updateDate) {
		this.updateDate = updateDate;
	}
	
}
