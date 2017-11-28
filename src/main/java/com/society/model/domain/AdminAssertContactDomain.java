package com.society.model.domain;

public class AdminAssertContactDomain {
	
	private Integer contactId;
	
	private PersonDomain person;
	
	private Integer assetId;

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public PersonDomain getPerson() {
		return person;
	}

	public void setPerson(PersonDomain person) {
		this.person = person;
	}

	public Integer getAssetId() {
		return assetId;
	}

	public void setAssetId(Integer assetId) {
		this.assetId = assetId;
	}

}
