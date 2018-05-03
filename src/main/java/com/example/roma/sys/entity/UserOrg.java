package com.example.roma.sys.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.example.framework.core.base.entity.BaseEntity;

public class UserOrg extends BaseEntity{
	
	@Max(9999999999L)
	private java.lang.Integer userId;
	@Max(9999999999L)
	private java.lang.Integer orgId;


	public void setUserId(java.lang.Integer value) {
		this.userId = value;
	}
	
	public java.lang.Integer getUserId() {
		return this.userId;
	}
	public void setOrgId(java.lang.Integer value) {
		this.orgId = value;
	}
	
	public java.lang.Integer getOrgId() {
		return this.orgId;
	}
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("UserId:",getUserId()+" ")
			.append("OrgId:",getOrgId()+" ")
			.toString();
	}
}

