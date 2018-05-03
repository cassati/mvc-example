package com.example.roma.sys.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.example.framework.core.base.entity.BaseEntity;

public class Org extends BaseEntity{
	
	@Max(9999999999L)
	private java.lang.Integer id;
	@NotNull @Max(9999999999L)
	private java.lang.Integer parentId;
	@NotBlank
	@Length(max=255)
	private java.lang.String orgName;
	@NotBlank @Length(max=255)
	private java.lang.String orgCode;


	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setParentId(java.lang.Integer value) {
		this.parentId = value;
	}
	
	public java.lang.Integer getParentId() {
		return this.parentId;
	}
	public void setOrgName(java.lang.String value) {
		this.orgName = value;
	}
	
	public java.lang.String getOrgName() {
		return this.orgName;
	}
	public void setOrgCode(java.lang.String value) {
		this.orgCode = value;
	}
	
	public java.lang.String getOrgCode() {
		return this.orgCode;
	}
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id:",getId()+" ")
			.append("ParentId:",getParentId()+" ")
			.append("OrgName:",getOrgName()+" ")
			.append("OrgCode:",getOrgCode()+" ")
			.toString();
	}
}

