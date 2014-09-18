package com.PotatoServer.Stores;

import java.util.ArrayList;

import org.joda.time.DateTime;

public class ProblemStore {
	String name;
	int id;
	String description;
	String type;
	DateTime updateDate;
	ArrayList<SymptomStore> symptoms;
}
