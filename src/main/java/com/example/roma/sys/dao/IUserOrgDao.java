package com.example.roma.sys.dao;

import java.util.List;
import com.example.framework.core.base.dao.IBaseDao;
import com.example.framework.core.db.page.Page;
import com.example.roma.sys.entity.UserOrg;

public interface IUserOrgDao extends IBaseDao<UserOrg>{
	
	public List<UserOrg> queryByParams(UserOrg userOrg);

	public List<UserOrg> queryByParams(UserOrg userOrg,Page page);

	public List<UserOrg> queryAll();

	public List<UserOrg> queryAll(Page page);

}
