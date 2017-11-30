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
@Table(name = "sa_asset_contact")
public class AssetContactJPA {
	
	@Id
	@GeneratedValue
	@Column(name = "contact_id")
	private Integer contactId;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "person_id")
	private PersonJPA person;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "asset_id")
	private AssetTrackerJPA asset;

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public PersonJPA getPerson() {
		return person;
	}

	public void setPerson(PersonJPA person) {
		this.person = person;
	}

	public AssetTrackerJPA getAsset() {
		return asset;
	}

	public void setAsset(AssetTrackerJPA asset) {
		this.asset = asset;
	}
}
