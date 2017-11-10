package com.society.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "sa_maintenance_head_charge_calc_type")
public class MaintenanceHeadChargeCalcTypeJPA {
	
	@Id
	@GeneratedValue
	@Column(name = "calc_type_id")
	private Integer calcTypeId;
	
	@Column(name = "calc_type")
	private String calcType;

	public Integer getCalcTypeId() {
		return calcTypeId;
	}

	public void setCalcTypeId(Integer calcTypeId) {
		this.calcTypeId = calcTypeId;
	}

	public String getCalcType() {
		return calcType;
	}

	public void setCalcType(String calcType) {
		this.calcType = calcType;
	}
}
