package com.example.roma.sys.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.example.framework.core.base.entity.BaseEntity;

public class RoleMenu extends BaseEntity{
	
	@Max(9999999999L)
	private java.lang.Integer roleId;
	@Max(9999999999L)
	private java.lang.Integer menuId;


	public void setRoleId(java.lang.Integer value) {
		this.roleId = value;
	}
	
	public java.lang.Integer getRoleId() {
		return this.roleId;
	}
	public void setMenuId(java.lang.Integer value) {
		this.menuId = value;
	}
	
	public java.lang.Integer getMenuId() {
		return this.menuId;
	}
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("RoleId:",getRoleId()+" ")
			.append("MenuId:",getMenuId()+" ")
			.toString();
	}
}

