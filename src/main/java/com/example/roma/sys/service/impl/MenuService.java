package com.example.roma.sys.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.roma.sys.entity.Menu;
import com.example.roma.sys.dao.IMenuDao;
import com.example.roma.sys.service.IMenuService;
import com.example.framework.core.db.page.Page;

@Service
public class MenuService implements IMenuService{

	@Autowired
    private IMenuDao menuDao;
	
	@Override
	@Transactional
	public int save(Menu menu){
		return this.menuDao.save(menu);
	}

	@Override
	@Transactional
	public int batchSave(List<Menu> menuList){
		return this.menuDao.batchSave(menuList);
	}

	@Override
	@Transactional
	public int update(Menu menu){
		return this.menuDao.update(menu);
	}

	@Override
	@Transactional
	public int delete(java.lang.Integer id){
		return this.menuDao.deleteById(id);
	}

	@Override
	@Transactional
	public int delete(Menu menu){
		return this.menuDao.delete(menu);
	}

	@Override
	public Menu get(Integer id){
		return this.menuDao.get(id);
	}

	@Override
    public List<Menu> queryByParams(Menu menu){
        List<Menu> menuList = this.menuDao.queryByParams(menu);
        return menuList;
    }

	@Override
    public Page queryByParams(Menu menu,Page page){
        List<Menu> menuList = this.menuDao.queryByParams(menu,page);
        page.setResult(menuList);
        return page;
    }

	@Override
    public List<Menu> queryAll() {
        List<Menu> menuList = this.menuDao.queryAll();
        return menuList;
    }

	@Override
    public Page queryAll(Page page) {
        List<Menu> menuList = this.menuDao.queryAll(page);
        page.setResult(menuList);
        return page;
    }
}