package com.society.model.domain;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class MaintenanceDomain {
	
	private Date paymentDueDate;
	
	private String paymentCycle;
	
	private List<String> additionalNote;
	
	Map<Integer, String> generalHeadChargeMap;

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

}
