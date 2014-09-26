package com.example.potatodiagnosisstatic;

public class Symptom {
	int symID;
	String symName;
	int symParent;
	String symPart;
	
	public Symptom(){
		
	}
	public int getSymID() {
		return symID;
	}
	public String getSymName() {
		return symName;
	}
	public int getSymParent() {
		return symParent;
	}
	public String getSymPart(){
		return symPart;
	}
	public void setSymID(int symID) {
		this.symID = symID;
	}
	public void setSymName(String symName) {
		this.symName = symName;
	}
	public void setSymParent(int symParent) {
		this.symParent = symParent;
	}
	public void setSymPart(String symPart) {
		// TODO Auto-generated method stub
		this.symPart = symPart;
	}
	
}
