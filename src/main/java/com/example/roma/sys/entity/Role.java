package com.example.roma.sys.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.example.framework.core.base.entity.BaseEntity;

public class Role extends BaseEntity{
	
	@Max(9999999999L)
	private java.lang.Integer id;
	@NotBlank
	@Length(max=255)
	private java.lang.String roleName;
	@NotBlank @Length(max=255)
	private java.lang.String roleCode;


	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setRoleName(java.lang.String value) {
		this.roleName = value;
	}
	
	public java.lang.String getRoleName() {
		return this.roleName;
	}
	public void setRoleCode(java.lang.String value) {
		this.roleCode = value;
	}
	
	public java.lang.String getRoleCode() {
		return this.roleCode;
	}
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id:",getId()+" ")
			.append("RoleName:",getRoleName()+" ")
			.append("RoleCode:",getRoleCode()+" ")
			.toString();
	}
}

