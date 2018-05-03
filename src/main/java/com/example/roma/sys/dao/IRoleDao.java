package com.example.roma.sys.dao;

import java.util.List;
import com.example.framework.core.base.dao.IBaseDao;
import com.example.framework.core.db.page.Page;
import com.example.roma.sys.entity.Role;

public interface IRoleDao extends IBaseDao<Role>{
	
	public List<Role> queryByParams(Role role);

	public List<Role> queryByParams(Role role,Page page);

	public List<Role> queryAll();

	public List<Role> queryAll(Page page);

}
