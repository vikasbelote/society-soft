package com.society.model.domain;

public class AdminAssertContactDomain {
	
	private Integer contactId;
	
	private PersonDomain person;
	
	private Integer assetId;
	
	private Boolean isDeleted;
	
	private Boolean isUpdated;

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

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Boolean getIsUpdated() {
		return isUpdated;
	}

	public void setIsUpdated(Boolean isUpdated) {
		this.isUpdated = isUpdated;
	}

}
