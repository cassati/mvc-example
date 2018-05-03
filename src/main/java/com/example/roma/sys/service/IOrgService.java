package com.example.roma.sys.service;

import java.util.List;
import com.example.roma.sys.entity.Org;
import com.example.framework.core.db.page.Page;

public interface IOrgService{
	
	public int save(Org org);

	public int batchSave(List<Org> orgList);

	public int update(Org org);

	public int delete(java.lang.Integer id);

	public int delete(Org org);

	public Org get(java.lang.Integer id);

	public List<Org> queryByParams(Org org);

	public Page queryByParams(Org org,Page page);

	public List<Org> queryAll();

	public Page queryAll(Page page);
}