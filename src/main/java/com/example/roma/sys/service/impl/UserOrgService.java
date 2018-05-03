package com.example.roma.sys.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.roma.sys.entity.UserOrg;
import com.example.roma.sys.dao.IUserOrgDao;
import com.example.roma.sys.service.IUserOrgService;
import com.example.framework.core.db.page.Page;

@Service
public class UserOrgService implements IUserOrgService{

	@Autowired
    private IUserOrgDao userOrgDao;
	
	@Override
	@Transactional
	public int save(UserOrg userOrg){
		return this.userOrgDao.save(userOrg);
	}

	@Override
	@Transactional
	public int batchSave(List<UserOrg> userOrgList){
		return this.userOrgDao.batchSave(userOrgList);
	}

	@Override
	@Transactional
	public int update(UserOrg userOrg){
		return this.userOrgDao.update(userOrg);
	}

	@Override
	@Transactional
	public int delete(java.lang.Integer userId){
		return this.userOrgDao.deleteById(userId);
	}

	@Override
	@Transactional
	public int delete(UserOrg userOrg){
		return this.userOrgDao.delete(userOrg);
	}

	@Override
	public UserOrg get(Integer id){
		return this.userOrgDao.get(id);
	}

	@Override
    public List<UserOrg> queryByParams(UserOrg userOrg){
        List<UserOrg> userOrgList = this.userOrgDao.queryByParams(userOrg);
        return userOrgList;
    }

	@Override
    public Page queryByParams(UserOrg userOrg,Page page){
        List<UserOrg> userOrgList = this.userOrgDao.queryByParams(userOrg,page);
        page.setResult(userOrgList);
        return page;
    }

	@Override
    public List<UserOrg> queryAll() {
        List<UserOrg> userOrgList = this.userOrgDao.queryAll();
        return userOrgList;
    }

	@Override
    public Page queryAll(Page page) {
        List<UserOrg> userOrgList = this.userOrgDao.queryAll(page);
        page.setResult(userOrgList);
        return page;
    }
}