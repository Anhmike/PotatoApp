package com.potato.potatoapp;

import org.joda.time.DateTime;

public class Symptom {
	int ID;
	String description;
	int parent;
	DateTime updateTime;
	
	public Symptom(){
		
	}
	public int getId() {
		return ID;
	}
	public String getDescription() {
		return description;
	}
	public int getParent() {
		return parent;
	}
	public void setId(int ID) {
		this.ID = ID;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public DateTime getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(DateTime updateTime) {
		this.updateTime = updateTime;
	}
	
}
