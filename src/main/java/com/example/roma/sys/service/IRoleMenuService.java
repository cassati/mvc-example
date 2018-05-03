package com.example.roma.sys.service;

import java.util.List;
import com.example.roma.sys.entity.RoleMenu;
import com.example.framework.core.db.page.Page;

public interface IRoleMenuService{
	
	public int save(RoleMenu roleMenu);

	public int batchSave(List<RoleMenu> roleMenuList);

	public int update(RoleMenu roleMenu);

	public int delete(java.lang.Integer roleId);

	public int delete(RoleMenu roleMenu);

	public RoleMenu get(java.lang.Integer roleId);

	public List<RoleMenu> queryByParams(RoleMenu roleMenu);

	public Page queryByParams(RoleMenu roleMenu,Page page);

	public List<RoleMenu> queryAll();

	public Page queryAll(Page page);
}