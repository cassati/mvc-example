package com.example.roma.sys.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.roma.sys.entity.User;
import com.example.roma.sys.dao.IUserDao;
import com.example.roma.sys.service.IUserService;
import com.example.framework.core.db.page.Page;

@Service
public class UserService implements IUserService{

	@Autowired
    private IUserDao userDao;
	
	@Override
	@Transactional
	public int save(User user){
		return this.userDao.save(user);
	}

	@Override
	@Transactional
	public int batchSave(List<User> userList){
		return this.userDao.batchSave(userList);
	}

	@Override
	@Transactional
	public int update(User user){
		return this.userDao.update(user);
	}

	@Override
	@Transactional
	public int delete(java.lang.Integer id){
		return this.userDao.deleteById(id);
	}

	@Override
	@Transactional
	public int delete(User user){
		return this.userDao.delete(user);
	}

	@Override
	public User get(Integer id){
		return this.userDao.get(id);
	}

	@Override
    public List<User> queryByParams(User user){
        List<User> userList = this.userDao.queryByParams(user);
        return userList;
    }

	@Override
    public Page queryByParams(User user,Page page){
        List<User> userList = this.userDao.queryByParams(user,page);
        page.setResult(userList);
        return page;
    }

	@Override
    public List<User> queryAll() {
        List<User> userList = this.userDao.queryAll();
        return userList;
    }

	@Override
    public Page queryAll(Page page) {
        List<User> userList = this.userDao.queryAll(page);
        page.setResult(userList);
        return page;
    }
}