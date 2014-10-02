package com.potato.potatoapp.beans;

import java.util.ArrayList;

public class XMLReturn {
	
	private ArrayList<Problem> problems;
	private ArrayList<Symptom> symptoms;
	private ArrayList<Picture> images;
	
	public ArrayList<Problem> getProblems() {
		return problems;
	}
	public void setProblems(ArrayList<Problem> problems) {
		this.problems = problems;
	}
	public ArrayList<Symptom> getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(ArrayList<Symptom> symptoms) {
		this.symptoms = symptoms;
	}
	public ArrayList<Picture> getImages() {
		return images;
	}
	public void setImages(ArrayList<Picture> images) {
		this.images = images;
	}
	

}
