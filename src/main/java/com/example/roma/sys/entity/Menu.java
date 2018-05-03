package com.example.roma.sys.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.example.framework.core.base.entity.BaseEntity;

public class Menu extends BaseEntity{
	
	@Max(9999999999L)
	private java.lang.Integer id;
	@NotBlank
	@Length(max=255)
	private java.lang.String menuName;
	@NotBlank @Length(max=255)
	private java.lang.String menuCode;
	@Length(max=255)
	private java.lang.String permission;


	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setMenuName(java.lang.String value) {
		this.menuName = value;
	}
	
	public java.lang.String getMenuName() {
		return this.menuName;
	}
	public void setMenuCode(java.lang.String value) {
		this.menuCode = value;
	}
	
	public java.lang.String getMenuCode() {
		return this.menuCode;
	}
	public void setPermission(java.lang.String value) {
		this.permission = value;
	}
	
	public java.lang.String getPermission() {
		return this.permission;
	}
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id:",getId()+" ")
			.append("MenuName:",getMenuName()+" ")
			.append("MenuCode:",getMenuCode()+" ")
			.append("Permission:",getPermission()+" ")
			.toString();
	}
}

