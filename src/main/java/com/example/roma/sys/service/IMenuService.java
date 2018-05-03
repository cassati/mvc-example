package com.example.roma.sys.service;

import java.util.List;
import com.example.roma.sys.entity.Menu;
import com.example.framework.core.db.page.Page;

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
}