package com.society.model.domain;

import java.util.List;

public class MaintenancePersonDomain {
	
	private String name;
	
	private List<String> generalHeadValues;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getGeneralHeadValues() {
		return generalHeadValues;
	}

	public void setGeneralHeadValues(List<String> generalHeadValues) {
		this.generalHeadValues = generalHeadValues;
	}

	
}
