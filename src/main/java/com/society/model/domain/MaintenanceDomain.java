package com.society.model.domain;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class MaintenanceDomain {
	
	private Date paymentCycleStartDate;
	
	private Date paymentDueDate;
	
	private String paymentCycle;
	
	private List<String> additionalNote;
	
	Map<Integer, String> generalHeadChargeMap;
	
	private Integer societyId;
	
	private Boolean cycleExist;
	
	private Boolean getMaintenanceTable;

	public Date getPaymentDueDate() {
		return paymentDueDate;
	}

	public void setPaymentDueDate(Date paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
	}
	
	public List<String> getAdditionalNote() {
		return additionalNote;
	}

	public void setAdditionalNote(List<String> additionalNote) {
		this.additionalNote = additionalNote;
	}

	public Map<Integer, String> getGeneralHeadChargeMap() {
		return generalHeadChargeMap;
	}

	public void setGeneralHeadChargeMap(Map<Integer, String> generalHeadChargeMap) {
		this.generalHeadChargeMap = generalHeadChargeMap;
	}

	public String getPaymentCycle() {
		return paymentCycle;
	}

	public void setPaymentCycle(String paymentCycle) {
		this.paymentCycle = paymentCycle;
	}

	public Date getPaymentCycleStartDate() {
		return paymentCycleStartDate;
	}

	public void setPaymentCycleStartDate(Date paymentCycleStartDate) {
		this.paymentCycleStartDate = paymentCycleStartDate;
	}

	public Integer getSocietyId() {
		return societyId;
	}

	public void setSocietyId(Integer societyId) {
		this.societyId = societyId;
	}

	public Boolean getGetMaintenanceTable() {
		return getMaintenanceTable;
	}

	public void setGetMaintenanceTable(Boolean getMaintenanceTable) {
		this.getMaintenanceTable = getMaintenanceTable;
	}

	public Boolean getCycleExist() {
		return cycleExist;
	}

	public void setCycleExist(Boolean cycleExist) {
		this.cycleExist = cycleExist;
	}

}
