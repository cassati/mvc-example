package com.example.roma.sys.service;

import java.util.List;
import com.example.roma.sys.entity.Role;
import com.example.framework.core.db.page.Page;

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
}