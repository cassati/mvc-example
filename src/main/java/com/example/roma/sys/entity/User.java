package com.example.roma.sys.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.example.framework.core.base.entity.BaseEntity;

public class User extends BaseEntity{
	
	@Max(9999999999L)
	private java.lang.Integer id;
	@NotBlank
	@Length(max=255)
	private java.lang.String name;
	@NotBlank @Length(max=255)
	private java.lang.String username;
	@NotBlank @Length(max=255)
	private java.lang.String password;


	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setUsername(java.lang.String value) {
		this.username = value;
	}
	
	public java.lang.String getUsername() {
		return this.username;
	}
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	
	public java.lang.String getPassword() {
		return this.password;
	}
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id:",getId()+" ")
			.append("Name:",getName()+" ")
			.append("Username:",getUsername()+" ")
			.append("Password:",getPassword()+" ")
			.toString();
	}
}

