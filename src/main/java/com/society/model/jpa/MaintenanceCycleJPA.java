package com.society.model.jpa;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sa_maintenance_cycle")
public class MaintenanceCycleJPA {
	
	@Id
	@GeneratedValue
	@Column(name = "cycle_id")
	private Integer cycleId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "society_id")
	private SocietyJPA society;
	
	@Column(name = "payment_due_date")
	private Date paymentDueDate; 
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;
	
	@OneToMany(mappedBy = "cycle" , fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<MaintenanceReceiptJPA> receiptList;
	
	@OneToMany(mappedBy = "cycle" , fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<MaintenanceCycleNoteJPA> noteList;
	
	public Integer getCycleId() {
		return cycleId;
	}

	public void setCycleId(Integer cycleId) {
		this.cycleId = cycleId;
	}

	public SocietyJPA getSociety() {
		return society;
	}

	public void setSociety(SocietyJPA society) {
		this.society = society;
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

	public List<MaintenanceReceiptJPA> getReceiptList() {
		return receiptList;
	}

	public void setReceiptList(List<MaintenanceReceiptJPA> receiptList) {
		this.receiptList = receiptList;
	}

	public List<MaintenanceCycleNoteJPA> getNoteList() {
		return noteList;
	}

	public void setNoteList(List<MaintenanceCycleNoteJPA> noteList) {
		this.noteList = noteList;
	}
	
	
}
