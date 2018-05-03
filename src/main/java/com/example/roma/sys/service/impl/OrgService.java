package com.example.roma.sys.service.impl;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.roma.sys.entity.Org;
import com.example.roma.sys.dao.IOrgDao;
import com.example.roma.sys.service.IOrgService;
import com.example.framework.core.db.page.Page;

@Service
public class OrgService implements IOrgService{

	@Autowired
    private IOrgDao orgDao;
	
	@Override
	@Transactional
	public int save(Org org){
		return this.orgDao.save(org);
	}

	@Override
	@Transactional
	public int batchSave(List<Org> orgList){
		return this.orgDao.batchSave(orgList);
	}

	@Override
	@Transactional
	public int update(Org org){
		return this.orgDao.update(org);
	}

	@Override
	@Transactional
	public int delete(java.lang.Integer id){
		return this.orgDao.deleteById(id);
	}

	@Override
	@Transactional
	public int delete(Org org){
		return this.orgDao.delete(org);
	}

	@Override
	public Org get(Integer id){
		return this.orgDao.get(id);
	}

	@Override
    public List<Org> queryByParams(Org org){
        List<Org> orgList = this.orgDao.queryByParams(org);
        return orgList;
    }

	@Override
    public Page queryByParams(Org org,Page page){
        List<Org> orgList = this.orgDao.queryByParams(org,page);
        page.setResult(orgList);
        return page;
    }

	@Override
    public List<Org> queryAll() {
        List<Org> orgList = this.orgDao.queryAll();
        return orgList;
    }

	@Override
    public Page queryAll(Page page) {
        List<Org> orgList = this.orgDao.queryAll(page);
        page.setResult(orgList);
        return page;
    }
}