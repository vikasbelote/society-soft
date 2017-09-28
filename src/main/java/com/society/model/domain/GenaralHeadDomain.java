package com.society.model.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class GenaralHeadDomain {
	
	private Integer generalHeadId;
	
	private String generalHeadName;
	
	private Integer sectionId;

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

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}
	
}
