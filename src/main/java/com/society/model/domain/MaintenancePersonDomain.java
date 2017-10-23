package com.society.model.domain;

import java.util.List;

public class MaintenancePersonDomain {
	
	private String name;
	
	private List<String> generalHeadValue;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getGeneralHeadValue() {
		return generalHeadValue;
	}

	public void setGeneralHeadValue(List<String> generalHeadValue) {
		this.generalHeadValue = generalHeadValue;
	}
}
