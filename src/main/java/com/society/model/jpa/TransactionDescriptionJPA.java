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
@Table(name = "sa_transaction_description")
public class TransactionDescriptionJPA {
	
	@Id
	@GeneratedValue
	@Column(name = "desc_id")
	private Integer descId;
	
	@Column(name = "transaction_description")
	private String transactionDescription;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "general_head_id")
	private GeneralHeadJPA generalHead;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "society_id")
	private SocietyJPA society;

	public Integer getDescId() {
		return descId;
	}

	public void setDescId(Integer descId) {
		this.descId = descId;
	}

	public String getTransactionDescription() {
		return transactionDescription;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
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
}
