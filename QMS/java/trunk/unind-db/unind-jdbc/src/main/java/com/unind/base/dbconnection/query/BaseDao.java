package com.unind.base.dbconnection.query;

import java.util.List;


@SuppressWarnings("rawtypes")
public interface BaseDao {

	public void add(Object entity);

	public void edit(Object entity);

	public void delete(Object entity);

	public Object findOne(String hql, SQLParameter parameters);

	public List findAll(String hql, SQLParameter parameters);
}
