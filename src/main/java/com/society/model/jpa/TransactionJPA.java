package com.society.model.jpa;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sa_transaction")
public class TransactionJPA {
	
	@Id
	@GeneratedValue
	@Column(name = "transaction_id")
	private Integer transactionId;
	
	@Column(name = "transaction_type")
	private String transactionType;
	
	@Column(name = "transaction_amount")
	private Double transactionAmount;
	
	@Column(name = "transaction_description")
	private String transactionDescription;
	
	@Column(name = "transaction_date")
	private Date transactionDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "general_head_id")
	private GeneralHeadJPA generalHead;

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getTransactionDescription() {
		return transactionDescription;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public GeneralHeadJPA getGeneralHead() {
		return generalHead;
	}

	public void setGeneralHead(GeneralHeadJPA generalHead) {
		this.generalHead = generalHead;
	}
}
