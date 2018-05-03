package com.example.roma.sys.dao;

import java.util.List;
import com.example.framework.core.base.dao.IBaseDao;
import com.example.framework.core.db.page.Page;
import com.example.roma.sys.entity.RoleMenu;

public interface IRoleMenuDao extends IBaseDao<RoleMenu>{
	
	public List<RoleMenu> queryByParams(RoleMenu roleMenu);

	public List<RoleMenu> queryByParams(RoleMenu roleMenu,Page page);

	public List<RoleMenu> queryAll();

	public List<RoleMenu> queryAll(Page page);

}
