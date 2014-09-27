package com.potato.potatoapp.beans;

import org.joda.time.DateTime;

public class Symptom {
	int ID;
	String description;
	int parent;
	DateTime updateTime;
	String part;
	
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
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	
}
