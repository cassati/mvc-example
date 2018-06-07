package com.example.roma.sys.service;

import com.example.framework.core.db.page.Page;
import com.example.roma.sys.entity.Menu;

import java.util.List;

public interface IMenuService{
	
	public int save(Menu menu);

	public int batchSave(List<Menu> menuList);

	public int update(Menu menu);

	public int delete(java.lang.Integer id);

	public int delete(Menu menu);

	public Menu get(java.lang.Integer id);

	public List<Menu> queryByParams(Menu menu);

	public Page queryByParams(Menu menu,Page page);

	public List<Menu> queryAll();

	public Page queryAll(Page page);

	public List<Menu> queryByUsername(String username);
}