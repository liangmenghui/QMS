package com.unind.base.dbconnection.query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@SuppressWarnings("rawtypes")
@Repository
public class BaseImpl implements BaseDao {
	@Autowired
	private EntityManager entityManager;

	public void add(Object entity) {
		entityManager.persist(entity);
	}

	public void edit(Object entity) {
		entityManager.persist(entity);
	}

	public void delete(Object entity) {
		entityManager.remove(entity);
	}

	public Object findOne(String hql, SQLParameter parameters) {
		parameters.setPage(1);
		parameters.setPageSize(1);
		return findAll(hql, parameters).iterator().next();
	}

	public List findAll(String hql, SQLParameter parameters) {
		Query query = entityManager.createQuery(hql);
		query.setFirstResult(parameters.getStartIndex()).setMaxResults(parameters.getPageSize());
		int index = 1;
		for (Object obj : parameters.toList()) {
			if (obj instanceof Parameter) {
				Parameter parameter = (Parameter) obj;
				query.setParameter(parameter.getName(), parameter.getValue());
			} else {
				query.setParameter(index++, obj);
			}
		}
		return query.getResultList();
	}

	/**
	 * 原生sql,查询一条
	 * 
	 * @param sql
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> findOneNativeSql(String sql, Map<String, Object> paramMap) {
		List<Map<String, Object>> list = createSQLQuery(sql, paramMap, null);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 原生sql,查询数据
	 * 
	 * @param sql
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> findNativeSql(String sql, Map<String, Object> paramMap) {
		return createSQLQuery(sql, paramMap, null);
	}

	/**
	 * 原生sql,查询数据
	 * 
	 * @param sql
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> findNativeSql(String sql, Map<String, Object> paramMap, Pageable pageable) {
		return createSQLQuery(sql, paramMap, pageable);
	}

	/**
	 * 原生sql,查询数据
	 * 
	 * @param sql
	 * @param paramMap
	 * @return
	 */
	public Page<Map<String, Object>> findPageNativeSql(String sql, Pageable pageable, Map<String, Object> paramMap) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		long total = 0L;
		if (pageable != null) {
			total = countNativeSql(sql, paramMap);
			if (total > 0) {
				list = createSQLQuery(sql, paramMap, pageable);
			}
		} else {
			list = createSQLQuery(sql, paramMap, pageable);
			if (list != null) {
				total = list.size();
			}
		}
		PageImpl<Map<String, Object>> page = new PageImpl<Map<String, Object>>(list, pageable, total);
		return page;
	}

	/**
	 * 原生sql,查询条数
	 * 
	 * @param sql
	 * @param paramMap
	 * @return
	 */
	public long countNativeSql(String sql, Map<String, Object> paramMap) {
		StringBuffer sbuf = new StringBuffer("select count(*) total from ( " + sql + " ) t");
		List<Map<String, Object>> dblist = createSQLQuery(sbuf.toString(), paramMap, null);
		long total = 0;
		if (dblist != null && dblist.size() != 0) {
			total = Long.parseLong(dblist.get(0).get("total").toString());
		}
		return total;
	}

	/**
	 * 调用sql查询返回map对象
	 * 
	 * @param sql
	 * @param pageable
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Map<String, Object>> createSQLQuery(String sql, Map<String, Object> paramMap, Pageable pageable) {
		Session session = entityManager.unwrap(Session.class);
		SQLQuery query = session.createSQLQuery(sql);

		if (pageable != null) {
			query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
			query.setMaxResults(pageable.getPageSize());
		}

		if (paramMap != null && paramMap.size() > 0) {
			for (String key : paramMap.keySet()) {
				query.setParameter(key, paramMap.get(key));
			}
		}

		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

		List list = query.list();
		List<Map<String, Object>> maplist = (List<Map<String, Object>>) list;
		return maplist;
	}

}