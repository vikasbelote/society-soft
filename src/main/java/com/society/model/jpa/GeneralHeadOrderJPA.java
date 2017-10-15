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
@Table(name = "sa_general_head_order")
public class GeneralHeadOrderJPA {
	
	@Id
	@GeneratedValue
	@Column(name = "order_id")
	private Integer orderId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "config_id")
	private SocietyConfigJPA societyConfig;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "general_head_id")
	private GeneralHeadJPA generalHead;
	
	@Column(name = "sequence_number")
	private Integer sequenceNumber;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public SocietyConfigJPA getSocietyConfig() {
		return societyConfig;
	}

	public void setSocietyConfig(SocietyConfigJPA societyConfig) {
		this.societyConfig = societyConfig;
	}

	public GeneralHeadJPA getGeneralHead() {
		return generalHead;
	}

	public void setGeneralHead(GeneralHeadJPA generalHead) {
		this.generalHead = generalHead;
	}

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
}
