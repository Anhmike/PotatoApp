package com.potato.potatoapp.beans;

import java.util.ArrayList;

public class UserDecisionStore {

	private UserDecisionStore instance;
	private ArrayList<Integer> selectedSymptoms;
	
	protected UserDecisionStore () {
		selectedSymptoms = new ArrayList<Integer>();
	}
	
	public UserDecisionStore getInstance() {
		if(instance == null) {
			instance = new UserDecisionStore();
		}
		
		return instance;
	}
	
	public ArrayList<Integer> getSelectedSymptoms() {
		return selectedSymptoms;
	}
	
	public void setSelectedSymptoms(ArrayList<Integer> selectedSymptoms) {
		this.selectedSymptoms = selectedSymptoms;
	}
	
	public void addNewSelection(int selection) {
		selectedSymptoms.add(selection);
	}
	
	public void removeLastSelection() {
		selectedSymptoms.remove(selectedSymptoms.size() -1);
	}
}
