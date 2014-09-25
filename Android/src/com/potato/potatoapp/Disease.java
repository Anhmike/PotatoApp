package com.potato.potatoapp;

import java.util.ArrayList;

import org.joda.time.DateTime;

public class Disease {
	int ProblemId;
	String ProblemName;
	String ProblemDescription;
	String ProblemPart;
	public Disease(){
		
	}
	public String getProblemName() {
		return ProblemName;
	}
	public String getProblemDescription() {
		return ProblemDescription;
	}
	public String getProblemPart(){
		return ProblemPart;
	}
	public int getProblemId(){
		return ProblemId;
	}
	public void setProblemName(String problemName) {
		this.ProblemName = problemName;
	}
	public void setProblemDescription(String ProblemDescription) {
		this.ProblemDescription = ProblemDescription;
	}
	public void setProblemPart(String diseasePart){
		this.ProblemPart = diseasePart;
	}
	public void setProblemId(int id){
		this.ProblemId = id;
	}
	
	String name;
	int id;
	String description;
	String type;
	ArrayList<Integer> symptoms;
	
	
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
	
	
	int s_id;
	String s_description;
	Integer parentSymptom;
	DateTime s_updateDate;
	String imageLocation;
	
	public int s_getId() {
		return id;
	}
	public void s_setId(int id) {
		this.id = id;
	}
	public String s_getDescription() {
		return description;
	}
	public void s_setDescription(String description) {
		this.description = description;
	}
	public Integer getParentSymptom() {
		return parentSymptom;
	}
	public void setParentSymptom(Integer parentSymptom) {
		this.parentSymptom = parentSymptom;
	}
	public DateTime getUpdateDate() {
		return s_updateDate;
	}
	public void setUpdateDate(DateTime updateDate) {
		this.s_updateDate = updateDate;
	}
	public String getImageLocation() {
		return imageLocation;
	}
	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}
}
