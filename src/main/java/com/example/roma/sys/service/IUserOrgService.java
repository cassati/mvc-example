package com.example.roma.sys.service;

import java.util.List;
import com.example.roma.sys.entity.UserOrg;
import com.example.framework.core.db.page.Page;

public interface IUserOrgService{
	
	public int save(UserOrg userOrg);

	public int batchSave(List<UserOrg> userOrgList);

	public int update(UserOrg userOrg);

	public int delete(java.lang.Integer userId);

	public int delete(UserOrg userOrg);

	public UserOrg get(java.lang.Integer userId);

	public List<UserOrg> queryByParams(UserOrg userOrg);

	public Page queryByParams(UserOrg userOrg,Page page);

	public List<UserOrg> queryAll();

	public Page queryAll(Page page);
}