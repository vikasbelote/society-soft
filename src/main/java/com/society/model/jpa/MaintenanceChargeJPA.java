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
@Table(name = "sa_maintenance_charge")
public class MaintenanceChargeJPA {

	@Id
	@GeneratedValue
	@Column(name = "charge_id")
	private Integer chargeId;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "receipt_id")
	private MaintenanceReceiptJPA receipt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "general_head_id")
	private GeneralHeadJPA generalHead;
	
	@Column(name = "charge_value")
	private Double chargeValue;

	public Integer getChargeId() {
		return chargeId;
	}

	public void setChargeId(Integer chargeId) {
		this.chargeId = chargeId;
	}

	public MaintenanceReceiptJPA getReceipt() {
		return receipt;
	}

	public void setReceipt(MaintenanceReceiptJPA receipt) {
		this.receipt = receipt;
	}

	public GeneralHeadJPA getGeneralHead() {
		return generalHead;
	}

	public void setGeneralHead(GeneralHeadJPA generalHead) {
		this.generalHead = generalHead;
	}

	public Double getChargeValue() {
		return chargeValue;
	}

	public void setChargeValue(Double chargeValue) {
		this.chargeValue = chargeValue;
	}
}
