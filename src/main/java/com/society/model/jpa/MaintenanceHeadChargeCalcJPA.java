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
@Table(name = "sa_maintenance_head_charge_calc")
public class MaintenanceHeadChargeCalcJPA {
	
	@Id
	@GeneratedValue
	@Column(name = "calc_id")
	private Integer calcId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "calc_type_id")
	private MaintenanceHeadChargeCalcTypeJPA calcType;
	
	@Column(name = "fixed_amount")
	private Double fixedAmount;
	
	@Column(name = "percentage_amount")
	private Double percentageAmount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reference_head_id")
	private MaintenanceHeadJPA referenceMaintenanceHead;

	public Integer getCalcId() {
		return calcId;
	}

	public void setCalcId(Integer calcId) {
		this.calcId = calcId;
	}

	public MaintenanceHeadChargeCalcTypeJPA getCalcType() {
		return calcType;
	}

	public void setCalcType(MaintenanceHeadChargeCalcTypeJPA calcType) {
		this.calcType = calcType;
	}

	public Double getFixedAmount() {
		return fixedAmount;
	}

	public void setFixedAmount(Double fixedAmount) {
		this.fixedAmount = fixedAmount;
	}

	public Double getPercentageAmount() {
		return percentageAmount;
	}

	public void setPercentageAmount(Double percentageAmount) {
		this.percentageAmount = percentageAmount;
	}

	public MaintenanceHeadJPA getReferenceMaintenanceHead() {
		return referenceMaintenanceHead;
	}

	public void setReferenceMaintenanceHead(MaintenanceHeadJPA referenceMaintenanceHead) {
		this.referenceMaintenanceHead = referenceMaintenanceHead;
	}

	
}
