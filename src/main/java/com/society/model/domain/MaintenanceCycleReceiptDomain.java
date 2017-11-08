package com.society.model.domain;

import java.sql.Date;
import java.util.List;

public class MaintenanceCycleReceiptDomain {
	
	private Integer cycleId;
	
	private Date paymentDueDate;
	
	private Date startDate;
	
	private Date endDate;
	
	private List<MaintenacneNoteDomain> notes;
	
	private List<MaintenanceReceiptDomain> receipts;
	
	private Integer societyId;
	
	private String societyName;
	
	private String address;
	
	private Integer lateInterestRate;
	
	private String chequeName;
	
	private List<GeneralHeadDomain> generalHeadList;
	
	private List<String> additionalNote;

	public Integer getCycleId() {
		return cycleId;
	}

	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}

	public Date getPaymentDueDate() {
		return paymentDueDate;
	}

	public void setPaymentDueDate(Date paymentDueDate) {
		this.paymentDueDate = paymentDueDate;
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

	public List<MaintenacneNoteDomain> getNotes() {
		return notes;
	}

	public void setNotes(List<MaintenacneNoteDomain> notes) {
		this.notes = notes;
	}

	public List<MaintenanceReceiptDomain> getReceipts() {
		return receipts;
	}

	public void setReceipts(List<MaintenanceReceiptDomain> receipts) {
		this.receipts = receipts;
	}

	public Integer getSocietyId() {
		return societyId;
	}

	public void setSocietyId(Integer societyId) {
		this.societyId = societyId;
	}

	public String getSocietyName() {
		return societyName;
	}

	public void setSocietyName(String societyName) {
		this.societyName = societyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getLateInterestRate() {
		return lateInterestRate;
	}

	public void setLateInterestRate(Integer lateInterestRate) {
		this.lateInterestRate = lateInterestRate;
	}

	public String getChequeName() {
		return chequeName;
	}

	public void setChequeName(String chequeName) {
		this.chequeName = chequeName;
	}

	public List<GeneralHeadDomain> getGeneralHeadList() {
		return generalHeadList;
	}

	public void setGeneralHeadList(List<GeneralHeadDomain> generalHeadList) {
		this.generalHeadList = generalHeadList;
	}

	public List<String> getAdditionalNote() {
		return additionalNote;
	}

	public void setAdditionalNote(List<String> additionalNote) {
		this.additionalNote = additionalNote;
	}
}
