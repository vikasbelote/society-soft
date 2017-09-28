package com.society.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sa_general_head_section")
public class GeneralHeadSectionJPA {
	
	@Id
	@GeneratedValue
	@Column(name = "section_id")
	private Integer sectionId;
	
	@Column(name = "section_name")
	private String sectionName;

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

}
