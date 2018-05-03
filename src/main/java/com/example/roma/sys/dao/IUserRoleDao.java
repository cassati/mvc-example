package com.example.roma.sys.dao;

import java.util.List;
import com.example.framework.core.base.dao.IBaseDao;
import com.example.framework.core.db.page.Page;
import com.example.roma.sys.entity.UserRole;

public interface IUserRoleDao extends IBaseDao<UserRole>{
	
	public List<UserRole> queryByParams(UserRole userRole);

	public List<UserRole> queryByParams(UserRole userRole,Page page);

	public List<UserRole> queryAll();

	public List<UserRole> queryAll(Page page);

}
