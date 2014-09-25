package com.potato.potatoapp;

public class Symptom {
	int symID;
	String symName;
	int symParent;
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
	public void setSymID(int symID) {
		this.symID = symID;
	}
	public void setSymName(String symName) {
		this.symName = symName;
	}
	public void setSymParent(int symParent) {
		this.symParent = symParent;
	}
	
}
