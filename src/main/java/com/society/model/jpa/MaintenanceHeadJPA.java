package com.society.model.jpa;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sa_maintenance_head")
public class MaintenanceHeadJPA {
	
	@Id
	@GeneratedValue
	@Column(name = "head_id")
	private Integer headId;
	
	@Column(name= "head_name")
	private String headName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "society_id")
	private SocietyJPA society;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "calc_id")
	private MaintenanceHeadChargeCalcJPA calculation;
	
	@Column(name = "is_default")
	private Boolean isDefault;
	
	@Column(name = "head_code")
	private String headCode;

	public Integer getHeadId() {
		return headId;
	}

	public void setHeadId(Integer headId) {
		this.headId = headId;
	}

	public String getHeadName() {
		return headName;
	}

	public void setHeadName(String headName) {
		this.headName = headName;
	}

	public SocietyJPA getSociety() {
		return society;
	}

	public void setSociety(SocietyJPA society) {
		this.society = society;
	}

	public MaintenanceHeadChargeCalcJPA getCalculation() {
		return calculation;
	}

	public void setCalculation(MaintenanceHeadChargeCalcJPA calculation) {
		this.calculation = calculation;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public String getHeadCode() {
		return headCode;
	}

	public void setHeadCode(String headCode) {
		this.headCode = headCode;
	}
}
