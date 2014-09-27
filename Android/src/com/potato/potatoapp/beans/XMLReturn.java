package com.potato.potatoapp.beans;

import java.util.ArrayList;

public class XMLReturn {
	
	private ArrayList<Problem> problems;
	private ArrayList<Symptom> symptoms;
	
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
	

}
