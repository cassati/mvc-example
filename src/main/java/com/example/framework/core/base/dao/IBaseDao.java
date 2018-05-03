package com.example.framework.core.base.dao;


import com.example.framework.core.db.marker.IMarkerDao;

import java.util.List;

public interface IBaseDao<T> extends IMarkerDao {

	/**
	 * 保存数据
	 * @param entity
	 * @return
	 */
	public int save(T entity);
	
	/**
	 * 批量保存数据
	 * @param entities
	 * @return
	 */
	public int batchSave(List<T> entities);
	
	/**
	 * 更新数据
	 * @param entity
	 * @return
	 */
	public int update(T entity);
	
	/**
	 * 删除数据（一般为逻辑删除，更新del_flag字段为1）
	 * @param entity
	 * @return
	 */
	public int delete(T entity);
	
	/**
	 * 根据ID删除数据（一般为逻辑删除，更新del_flag字段为1）
	 * @param id
	 * @return
	 */
	public int deleteById(Integer id);
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(Integer id);
}
