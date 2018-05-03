package com.example.roma.sys.dao;

import java.util.List;
import com.example.framework.core.base.dao.IBaseDao;
import com.example.framework.core.db.page.Page;
import com.example.roma.sys.entity.User;

public interface IUserDao extends IBaseDao<User>{
	
	public List<User> queryByParams(User user);

	public List<User> queryByParams(User user,Page page);

	public List<User> queryAll();

	public List<User> queryAll(Page page);

}
