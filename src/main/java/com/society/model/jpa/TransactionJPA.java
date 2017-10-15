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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "general_head_id")
	private GeneralHeadJPA generalHead;
	
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name = "transaction_description_id")
	private TransactionDescriptionJPA transactionDescription;
	
	@Column(name = "transaction_amount")
	private Double transactionAmount;
	
	@Column(name = "transaction_date")
	private Date transactionDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "society_id")
	private SocietyJPA society;
	
	@Column(name = "transaction_type")
	private String transactionType;

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
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

	public SocietyJPA getSociety() {
		return society;
	}

	public void setSociety(SocietyJPA society) {
		this.society = society;
	}

	public TransactionDescriptionJPA getTransactionDescription() {
		return transactionDescription;
	}

	public void setTransactionDescription(
			TransactionDescriptionJPA transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
}
