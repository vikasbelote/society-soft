package com.society.model.jpa;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AccessRightsId implements Serializable {

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "menu_id")
	private Integer menuId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccessRightsId)) return false;
        AccessRightsId that = (AccessRightsId) o;
        return Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getMenuId(), that.getMenuId());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getMenuId());
    }
}
