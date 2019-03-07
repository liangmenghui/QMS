package com.unind.base.provider;

import java.util.ArrayList;
import java.util.Iterator;
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

public class BaseOprService extends BaseService {

	@Autowired
	private EntityManager entityManager;

	/**
	 * 查询记录总数
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public long countByHql(String hql, List<Object> params) {
		Query query = entityManager.createQuery(hql);
		Iterator<?> it = query.getParameters().iterator();
		int pos = 0;
		while (it.hasNext()) {
			query.setParameter(pos, it.next());
			pos++;
		}
		return ((Number) query.getSingleResult()).longValue();
	}

	public Object findOneByHql(String hql, List<Object> params) {
		return findByHql(hql, params).iterator().next();
	}

	/**
	 * 查询记录列表
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List findByHql(String hql, List<Object> params) {
		Query query = entityManager.createQuery(hql);
		if (null == params || params.size() == 0) {
			return query.getResultList();
		}
		Iterator<?> it = query.getParameters().iterator();
		int pos = 0;
		while (it.hasNext()) {
			query.setParameter(pos, it.next());
			pos++;
		}
		return query.getResultList();
	}

	/**
	 * 查询记录列表
	 * 
	 * @param hql
	 * @param params
	 * @param pageable
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List findByHql(String hql, List<Object> params, Pageable pageable) {
		Query query = entityManager.createQuery(hql);
		if (null == params || params.size() == 0) {
			query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
			query.setMaxResults((pageable.getPageNumber() + 1) * pageable.getPageSize());
			return query.getResultList();
		}
		Iterator<?> it = query.getParameters().iterator();
		int pos = 0;
		while (it.hasNext()) {
			query.setParameter(pos, it.next());
			pos++;
		}
		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults((pageable.getPageNumber() + 1) * pageable.getPageSize());
		return query.getResultList();
	}

	/**
	 * 原生sql,查询一条
	 * 
	 * @param sql
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> findOneBySql(String sql, Map<String, Object> paramMap) {
		List<Map<String, Object>> list = createSQLQuery(sql, null, paramMap);
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
	public List<Map<String, Object>> findBySql(String sql, Map<String, Object> paramMap) {
		return createSQLQuery(sql, null, paramMap);
	}

	/**
	 * 原生sql,查询数据
	 * 
	 * @param sql
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> findBySql(String sql, Pageable pageable, Map<String, Object> paramMap) {
		return createSQLQuery(sql, pageable, paramMap);
	}

	/**
	 * 原生sql,查询数据
	 * 
	 * @param sql
	 * @param paramMap
	 * @return
	 */
	public Page<Map<String, Object>> findPageBySql(String sql, Pageable pageable, Map<String, Object> paramMap) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		long total = 0L;
		if (pageable != null) {
			total = countBySql(sql, paramMap);
			if (total > 0) {
				list = createSQLQuery(sql, pageable, paramMap);
			}
		} else {
			list = createSQLQuery(sql, pageable, paramMap);
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
	public long countBySql(String sql, Map<String, Object> paramMap) {
		StringBuffer sbuf = new StringBuffer("select count(*) total from ( " + sql + " ) t");
		List<Map<String, Object>> dblist = createSQLQuery(sbuf.toString(), null, paramMap);
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Map<String, Object>> createSQLQuery(String sql, Pageable pageable, Map<String, Object> paramMap) {
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
