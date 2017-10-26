package com.society.model.jpa;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sa_society_config")
public class SocietyConfigJPA implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name = "config_id")
	private Integer configId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "society_id")
	private SocietyJPA society;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
	@Column(name = "maintenance_cycle")
	private Integer maintenanceCycle;
	
	@Column(name = "maintenance_payment_due_interest")
	private Integer maintenancePaymentDueInterest;
	
	@Column(name = "maintenance_payment_cheque_name")
	private String maintenancePaymentChequeName;

	public Integer getConfigId() {
		return configId;
	}

	public void setConfigId(Integer configId) {
		this.configId = configId;
	}

	public SocietyJPA getSociety() {
		return society;
	}

	public void setSociety(SocietyJPA society) {
		this.society = society;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getMaintenanceCycle() {
		return maintenanceCycle;
	}

	public void setMaintenanceCycle(Integer maintenanceCycle) {
		this.maintenanceCycle = maintenanceCycle;
	}

	public Integer getMaintenancePaymentDueInterest() {
		return maintenancePaymentDueInterest;
	}

	public void setMaintenancePaymentDueInterest(
			Integer maintenancePaymentDueInterest) {
		this.maintenancePaymentDueInterest = maintenancePaymentDueInterest;
	}

	public String getMaintenancePaymentChequeName() {
		return maintenancePaymentChequeName;
	}

	public void setMaintenancePaymentChequeName(String maintenancePaymentChequeName) {
		this.maintenancePaymentChequeName = maintenancePaymentChequeName;
	}
}
