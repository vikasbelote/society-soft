package com.society.model.domain;

import java.util.List;

public class MaintenanceTableDomain {
	
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
}
