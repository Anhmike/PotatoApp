package com.potato.potatoapp;

import java.util.ArrayList;

import org.joda.time.DateTime;

public class Problem {
	String name;
	int id;
	String description;
	String type;
	ArrayList<Integer> symptoms;
	DateTime updateTime;
	
	public Problem() {
		symptoms = new ArrayList();
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public ArrayList<Integer> getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(ArrayList<Integer> symptoms) {
		this.symptoms = symptoms;
	}
	public void addSymptom(int symptomId) {
		symptoms.add(symptomId);
	}
	public DateTime getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(DateTime updateTime) {
		this.updateTime = updateTime;
	}
	
}
