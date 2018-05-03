package com.example.framework.core.base.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;
import java.util.Date;


public class BaseEntity implements Serializable{

	private static final long serialVersionUID = -3867387327526718784L;
	/**
	 * 删除标记（0：正常；1：删除；）
	 */
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	
	/**
	 * 系统自动创建ID:-1
	 */
	public static final Integer SYS_CREATE = -1;
	/**
	 * 创建者ID，如为系统自动创建，请传SYS_CREATE 
	 */
	@JsonInclude(Include.NON_NULL)
	private Integer createUser;   //创建人ID
	
	@JsonInclude(Include.NON_EMPTY)
	private String createName;    //创建人名称
	
	@JsonInclude(Include.NON_EMPTY)
	private Date createTime;	  //创建时间
	
	@JsonInclude(Include.NON_NULL)
	private Integer updateUser;   //更新者ID
	
	@JsonInclude(Include.NON_EMPTY)
	private String updateName;    //更新者名称
	
	@JsonInclude(Include.NON_EMPTY)
	private String updateRemark;  //更新备注
	
	@JsonInclude(Include.NON_EMPTY)
	private Date updateTime; 	  //更新时间
	
	@JsonInclude(Include.NON_EMPTY)
	private String operSource;    //操作来源

	
	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	public String getUpdateRemark() {
		return updateRemark;
	}

	public void setUpdateRemark(String updateRemark) {
		this.updateRemark = updateRemark;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getOperSource() {
		return operSource;
	}

	public void setOperSource(String operSource) {
		this.operSource = operSource;
	}

	public String getCreateName() {
		return createName;
	}
	
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	
	public String getUpdateName() {
		return updateName;
	}
	
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
}
