package com.example.roma.sys.service;

import java.util.List;
import com.example.roma.sys.entity.UserRole;
import com.example.framework.core.db.page.Page;

public interface IUserRoleService{
	
	public int save(UserRole userRole);

	public int batchSave(List<UserRole> userRoleList);

	public int update(UserRole userRole);

	public int delete(java.lang.Integer userId);

	public int delete(UserRole userRole);

	public UserRole get(java.lang.Integer userId);

	public List<UserRole> queryByParams(UserRole userRole);

	public Page queryByParams(UserRole userRole,Page page);

	public List<UserRole> queryAll();

	public Page queryAll(Page page);
}