package com.example.roma.sys.service;

import java.util.List;
import com.example.roma.sys.entity.User;
import com.example.framework.core.db.page.Page;

public interface IUserService{
	
	public int save(User user);

	public int batchSave(List<User> userList);

	public int update(User user);

	public int delete(java.lang.Integer id);

	public int delete(User user);

	public User get(java.lang.Integer id);

	public List<User> queryByParams(User user);

	public Page queryByParams(User user,Page page);

	public List<User> queryAll();

	public Page queryAll(Page page);
}