package com.example.roma.sys.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.roma.sys.entity.Role;
import com.example.roma.sys.dao.IRoleDao;
import com.example.roma.sys.service.IRoleService;
import com.example.framework.core.db.page.Page;

@Service
public class RoleService implements IRoleService{

	@Autowired
    private IRoleDao roleDao;
	
	@Override
	@Transactional
	public int save(Role role){
		return this.roleDao.save(role);
	}

	@Override
	@Transactional
	public int batchSave(List<Role> roleList){
		return this.roleDao.batchSave(roleList);
	}

	@Override
	@Transactional
	public int update(Role role){
		return this.roleDao.update(role);
	}

	@Override
	@Transactional
	public int delete(java.lang.Integer id){
		return this.roleDao.deleteById(id);
	}

	@Override
	@Transactional
	public int delete(Role role){
		return this.roleDao.delete(role);
	}

	@Override
	public Role get(Integer id){
		return this.roleDao.get(id);
	}

	@Override
    public List<Role> queryByParams(Role role){
        List<Role> roleList = this.roleDao.queryByParams(role);
        return roleList;
    }

	@Override
    public Page queryByParams(Role role,Page page){
        List<Role> roleList = this.roleDao.queryByParams(role,page);
        page.setResult(roleList);
        return page;
    }

	@Override
    public List<Role> queryAll() {
        List<Role> roleList = this.roleDao.queryAll();
        return roleList;
    }

	@Override
    public Page queryAll(Page page) {
        List<Role> roleList = this.roleDao.queryAll(page);
        page.setResult(roleList);
        return page;
    }

    @Override
	public List<Role> queryByUsername(String username) {
		Map<String, Object> params = new HashMap<>();
		params.put("username", username);
		return this.roleDao.queryByUsername(params);
	}
}