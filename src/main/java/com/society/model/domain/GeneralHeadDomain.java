package com.society.model.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class GeneralHeadDomain {
	
	private Integer generalHeadId;
	
	private String generalHeadName;
	
	private Integer sectionId;
	
	private String sectionName;
	
	private Boolean isDefault;
	
	private Integer societyId;

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
	
	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getSocietyId() {
		return societyId;
	}

	public void setSocietyId(Integer societyId) {
		this.societyId = societyId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
}