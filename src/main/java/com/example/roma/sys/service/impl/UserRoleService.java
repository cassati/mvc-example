package com.example.roma.sys.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.roma.sys.entity.UserRole;
import com.example.roma.sys.dao.IUserRoleDao;
import com.example.roma.sys.service.IUserRoleService;
import com.example.framework.core.db.page.Page;

@Service
public class UserRoleService implements IUserRoleService{

	@Autowired
    private IUserRoleDao userRoleDao;
	
	@Override
	@Transactional
	public int save(UserRole userRole){
		return this.userRoleDao.save(userRole);
	}

	@Override
	@Transactional
	public int batchSave(List<UserRole> userRoleList){
		return this.userRoleDao.batchSave(userRoleList);
	}

	@Override
	@Transactional
	public int update(UserRole userRole){
		return this.userRoleDao.update(userRole);
	}

	@Override
	@Transactional
	public int delete(java.lang.Integer userId){
		return this.userRoleDao.deleteById(userId);
	}

	@Override
	@Transactional
	public int delete(UserRole userRole){
		return this.userRoleDao.delete(userRole);
	}

	@Override
	public UserRole get(Integer id){
		return this.userRoleDao.get(id);
	}

	@Override
    public List<UserRole> queryByParams(UserRole userRole){
        List<UserRole> userRoleList = this.userRoleDao.queryByParams(userRole);
        return userRoleList;
    }

	@Override
    public Page queryByParams(UserRole userRole,Page page){
        List<UserRole> userRoleList = this.userRoleDao.queryByParams(userRole,page);
        page.setResult(userRoleList);
        return page;
    }

	@Override
    public List<UserRole> queryAll() {
        List<UserRole> userRoleList = this.userRoleDao.queryAll();
        return userRoleList;
    }

	@Override
    public Page queryAll(Page page) {
        List<UserRole> userRoleList = this.userRoleDao.queryAll(page);
        page.setResult(userRoleList);
        return page;
    }
}