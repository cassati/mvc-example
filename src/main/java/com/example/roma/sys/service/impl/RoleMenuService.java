package com.example.roma.sys.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.roma.sys.entity.RoleMenu;
import com.example.roma.sys.dao.IRoleMenuDao;
import com.example.roma.sys.service.IRoleMenuService;
import com.example.framework.core.db.page.Page;

@Service
public class RoleMenuService implements IRoleMenuService{

	@Autowired
    private IRoleMenuDao roleMenuDao;
	
	@Override
	@Transactional
	public int save(RoleMenu roleMenu){
		return this.roleMenuDao.save(roleMenu);
	}

	@Override
	@Transactional
	public int batchSave(List<RoleMenu> roleMenuList){
		return this.roleMenuDao.batchSave(roleMenuList);
	}

	@Override
	@Transactional
	public int update(RoleMenu roleMenu){
		return this.roleMenuDao.update(roleMenu);
	}

	@Override
	@Transactional
	public int delete(java.lang.Integer roleId){
		return this.roleMenuDao.deleteById(roleId);
	}

	@Override
	@Transactional
	public int delete(RoleMenu roleMenu){
		return this.roleMenuDao.delete(roleMenu);
	}

	@Override
	public RoleMenu get(Integer id){
		return this.roleMenuDao.get(id);
	}

	@Override
    public List<RoleMenu> queryByParams(RoleMenu roleMenu){
        List<RoleMenu> roleMenuList = this.roleMenuDao.queryByParams(roleMenu);
        return roleMenuList;
    }

	@Override
    public Page queryByParams(RoleMenu roleMenu,Page page){
        List<RoleMenu> roleMenuList = this.roleMenuDao.queryByParams(roleMenu,page);
        page.setResult(roleMenuList);
        return page;
    }

	@Override
    public List<RoleMenu> queryAll() {
        List<RoleMenu> roleMenuList = this.roleMenuDao.queryAll();
        return roleMenuList;
    }

	@Override
    public Page queryAll(Page page) {
        List<RoleMenu> roleMenuList = this.roleMenuDao.queryAll(page);
        page.setResult(roleMenuList);
        return page;
    }
}