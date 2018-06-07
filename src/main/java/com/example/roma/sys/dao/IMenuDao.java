package com.example.roma.sys.dao;

import com.example.framework.core.base.dao.IBaseDao;
import com.example.framework.core.db.page.Page;
import com.example.roma.sys.entity.Menu;

import java.util.List;
import java.util.Map;

public interface IMenuDao extends IBaseDao<Menu>{

	public List<Menu> queryByParams(Menu menu);

	public List<Menu> queryByParams(Menu menu,Page page);

	public List<Menu> queryAll();

	public List<Menu> queryAll(Page page);

	public List<Menu> queryByUsername(Map<String, Object> params);
}
