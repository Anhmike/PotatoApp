package com.example.potatodiagnosisstatic;

public class Disease {
	int diseaseId;
	String diseaseName;
	String diseaseDescription;
	String diseasePart;
	public Disease(){
		
	}
	public String getDiseaseName() {
		return diseaseName;
	}
	public String getDiseaseDescription() {
		return diseaseDescription;
	}
	public String getDiseasePart(){
		return diseasePart;
	}
	public int getDiseaseId(){
		return diseaseId;
	}
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	public void setDiseaseDescription(String diseaseDescription) {
		this.diseaseDescription = diseaseDescription;
	}
	public void setDiseasePart(String diseasePart){
		this.diseasePart = diseasePart;
	}
	public void setDiseaseId(int id){
		this.diseaseId = id;
	}
}
