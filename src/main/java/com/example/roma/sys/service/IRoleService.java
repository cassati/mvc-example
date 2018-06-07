package com.example.roma.sys.service;

import com.example.framework.core.db.page.Page;
import com.example.roma.sys.entity.Role;

import java.util.List;

public interface IRoleService{
	
	public int save(Role role);

	public int batchSave(List<Role> roleList);

	public int update(Role role);

	public int delete(java.lang.Integer id);

	public int delete(Role role);

	public Role get(java.lang.Integer id);

	public List<Role> queryByParams(Role role);

	public Page queryByParams(Role role,Page page);

	public List<Role> queryAll();

	public Page queryAll(Page page);

	public List<Role> queryByUsername(String username);
}