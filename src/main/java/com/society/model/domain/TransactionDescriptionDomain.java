package com.society.model.domain;

public class TransactionDescriptionDomain {

	private Integer descId;
	
	private String label;
	
	private Integer generalHeadId;
	
	private String generalHeadName;

	public Integer getDescId() {
		return descId;
	}

	public void setDescId(Integer descId) {
		this.descId = descId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getGeneralHeadId() {
		return generalHeadId;
	}

	public void setGeneralHeadId(Integer generalHeadId) {
		this.generalHeadId = generalHeadId;
	}

	public String getGeneralHeadName() {
		return generalHeadName;
	}

	public void setGeneralHeadName(String generalHeadName) {
		this.generalHeadName = generalHeadName;
	}
	
	@Override
	public String toString() {
		return "{\"descId\" : " + this.descId + ", \"label\": \"" +this.label + "\",  \"generalHeadId\" : " + this.generalHeadId + ", \"generalHeadName\": \"" + this.generalHeadName + "\"}";
	}
}
