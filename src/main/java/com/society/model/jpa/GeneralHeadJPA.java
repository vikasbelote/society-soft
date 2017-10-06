package com.society.model.jpa;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	
	@Column(name = "is_default")
	private Boolean isDefault;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "society_id")
	private SocietyJPA society;
	
	@OneToMany(mappedBy = "generalHead", fetch = FetchType.LAZY)
	private List<TransactionJPA> transactionList;

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
	
	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public SocietyJPA getSociety() {
		return society;
	}

	public void setSociety(SocietyJPA society) {
		this.society = society;
	}

	public List<TransactionJPA> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<TransactionJPA> transactionList) {
		this.transactionList = transactionList;
	}
	
}
