package com.society.model.domain;

import java.util.List;

public class EmailDomain {
	
	private Integer cycleId;
	
	private Integer societyId;
	
	private String rootPath;
	
	private List<StatusMemberDomain> memberIds;
	
	public Integer getCycleId() {
		return cycleId;
	}

	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}

	public Integer getSocietyId() {
		return societyId;
	}

	public void setSocietyId(Integer societyId) {
		this.societyId = societyId;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public List<StatusMemberDomain> getMemberIds() {
		return memberIds;
	}

	public void setMemberIds(List<StatusMemberDomain> memberIds) {
		this.memberIds = memberIds;
	}

	
}
