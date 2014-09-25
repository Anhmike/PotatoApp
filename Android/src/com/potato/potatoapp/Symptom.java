package com.potato.potatoapp;

public class Symptom {
	int ID;
	String symName;
	int parent;
	public Symptom(){
		
	}
	public int getId() {
		return ID;
	}
	public String getName() {
		return symName;
	}
	public int getParent() {
		return parent;
	}
	public void setId(int ID) {
		this.ID = ID;
	}
	public void setName(String name) {
		this.symName = name;
	}
	public void setSymParent(int parent) {
		this.parent = parent;
	}
	
}
