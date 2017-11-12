package com.society.model.domain;

import java.util.List;

public class MaintenancePersonDomain {
	
	private Integer memberId;
	
	private String name;
	
	private Integer squareFeet;
	
	private List<MaintenacneChargeDomain> maintenanceHeadChargeDomainList;

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

	public Integer getSquareFeet() {
		return squareFeet;
	}

	public void setSquareFeet(Integer squareFeet) {
		this.squareFeet = squareFeet;
	}

	public List<MaintenacneChargeDomain> getMaintenanceHeadChargeDomainList() {
		return maintenanceHeadChargeDomainList;
	}

	public void setMaintenanceHeadChargeDomainList(List<MaintenacneChargeDomain> maintenanceHeadChargeDomainList) {
		this.maintenanceHeadChargeDomainList = maintenanceHeadChargeDomainList;
	}
	
}
