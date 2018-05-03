package com.example.roma.sys.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.example.framework.core.base.entity.BaseEntity;

public class UserRole extends BaseEntity{
	
	@Max(9999999999L)
	private java.lang.Integer userId;
	@Max(9999999999L)
	private java.lang.Integer roleId;


	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setRoleId(java.lang.Integer value) {
		this.roleId = value;
	}
	
	public java.lang.Integer getRoleId() {
		return this.roleId;
	}
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("UserId:",getUserId()+" ")
			.append("RoleId:",getRoleId()+" ")
			.toString();
	}
}

