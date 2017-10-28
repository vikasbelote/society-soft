package com.society.model.domain;

import java.util.List;

public class MaintenancePersonDomain {
	
	private Integer memberId;
	
	private String name;
	
	private List<MaintenacneChargeDomain> generalHeadValues;

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MaintenacneChargeDomain> getGeneralHeadValues() {
		return generalHeadValues;
	}

	public void setGeneralHeadValues(List<MaintenacneChargeDomain> generalHeadValues) {
		this.generalHeadValues = generalHeadValues;
	}

	
}
