package com.society.model.jpa;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sa_user_access_rights")
public class SocietyUserAccessRightsJPA {
	
	@EmbeddedId
	private AccessRightsId accessRightsId;

	public AccessRightsId getAccessRightsId() {
		return accessRightsId;
	}

	public void setAccessRightsId(AccessRightsId accessRightsId) {
		this.accessRightsId = accessRightsId;
	}

}
