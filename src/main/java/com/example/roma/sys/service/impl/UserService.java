package com.example.roma.sys.service.impl;

import com.example.framework.core.db.page.Page;
import com.example.roma.sys.dao.IUserDao;
import com.example.roma.sys.entity.Menu;
import com.example.roma.sys.entity.Role;
import com.example.roma.sys.entity.User;
import com.example.roma.sys.service.IMenuService;
import com.example.roma.sys.service.IRoleService;
import com.example.roma.sys.service.IUserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements IUserService{

	@Autowired
    private IUserDao userDao;
	@Autowired
    private IRoleService roleService;
	@Autowired
	private IMenuService menuService;

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

    @Override
	public Page queryUserRole(Page page) {
		List<User> userList = this.userDao.queryUserRole(page);
		page.setResult(userList);
		return page;
	}

	@Override
	public User getByUsername(String username) {
		User params = new User();
		params.setUsername(username);
		List<User> users = queryByParams(params);
		if (CollectionUtils.isNotEmpty(users)) {
			return users.get(0);
		}
		return null;
	}

    @Override
	public Set<String> queryUserMenus(String username) {
        List<Menu> menus = this.menuService.queryByUsername(username);
        Set<String> menuSet = new HashSet<>();
        for (Menu m : menus) {
            menuSet.add(m.getMenuCode());
        }
        return menuSet;
	}

	@Override
    public Set<String> queryUserRoles(String username) {
        List<Role> roles = this.roleService.queryByUsername(username);
        Set<String> roleSet = new HashSet<>();
        for (Role r : roles) {
            roleSet.add(r.getRoleCode());
        }
        return roleSet;
    }
}