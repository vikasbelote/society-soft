package com.society.model.domain;

import java.util.List;

public class MaintenanceTableDomain {
	
	private String societyName;
	
	private String societyAdrress;
	
	private Integer maintenancePaymentDueInterest;
	
	private String maintenancePaymentChequeName;
	
	private List<MaintenancePersonDomain> memberList;
	
	private List<GeneralHeadDomain> columnList;

	public List<MaintenancePersonDomain> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<MaintenancePersonDomain> memberList) {
		this.memberList = memberList;
	}

	public List<GeneralHeadDomain> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<GeneralHeadDomain> columnList) {
		this.columnList = columnList;
	}

	public String getSocietyName() {
		return societyName;
	}

	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}

	public String getSocietyAdrress() {
		return societyAdrress;
	}

	public void setSocietyAdrress(String societyAdrress) {
		this.societyAdrress = societyAdrress;
	}

	public Integer getMaintenancePaymentDueInterest() {
		return maintenancePaymentDueInterest;
	}

	public void setMaintenancePaymentDueInterest(
			Integer maintenancePaymentDueInterest) {
		this.maintenancePaymentDueInterest = maintenancePaymentDueInterest;
	}

	public String getMaintenancePaymentChequeName() {
		return maintenancePaymentChequeName;
	}

	public void setMaintenancePaymentChequeName(String maintenancePaymentChequeName) {
		this.maintenancePaymentChequeName = maintenancePaymentChequeName;
	}
}
