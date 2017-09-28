package com.society.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sa_general_head")
public class GeneralHeadJPA {
	
	@Id
	@GeneratedValue
	@Column(name = "general_head_id")
	private Integer generalHeadId;
	
	@Column(name = "general_head_name")
	private String generalHeadName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "section_id")
	private GeneralHeadSectionJPA section;

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

	public GeneralHeadSectionJPA getSection() {
		return section;
	}

	public void setSection(GeneralHeadSectionJPA section) {
		this.section = section;
	}
	
}
