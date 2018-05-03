package com.example.roma.sys.dao;

import java.util.List;
import com.example.framework.core.base.dao.IBaseDao;
import com.example.framework.core.db.page.Page;
import com.example.roma.sys.entity.Org;

public interface IOrgDao extends IBaseDao<Org>{
	
	public List<Org> queryByParams(Org org);

	public List<Org> queryByParams(Org org,Page page);

	public List<Org> queryAll();

	public List<Org> queryAll(Page page);

}
