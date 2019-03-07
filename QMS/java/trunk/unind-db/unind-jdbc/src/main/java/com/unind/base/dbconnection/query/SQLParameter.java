package com.unind.base.dbconnection.query;

import java.util.ArrayList;
import java.util.List;

/**
 * sql参数
 * @author tanxiang
 *
 * @param <T>
 */
public class SQLParameter<T> {
	public final static int DEFAULT_PAG = 1;
	public final static int DEFAULT_PAGESIZE = 10000;
	/**
	 * 页码
	 */
	private int page = DEFAULT_PAG;
	/**
	 * 页大小
	 */
	private int pageSize = DEFAULT_PAGESIZE;

	public SQLParameter() {
	}

	public static <T> SQLParameter<T> newInstance() {
		return new SQLParameter<T>();
	}

	public SQLParameter<T> clear() {
		list.clear();
		return this;
	}

	public SQLParameter<T> reset() {
		this.page = DEFAULT_PAG;
		this.pageSize = DEFAULT_PAGESIZE;
		list.clear();
		return this;
	}

	private List<T> list = new ArrayList<T>();

	public SQLParameter<T> add(T param) {
		list.add(param);
		return this;
	}

	public List<T> toList() {
		return list;
	}

	public T[] toArray(T[] a) {
		return list.toArray(a);
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartIndex() {
		return (page-1) * pageSize;
	}
	
}
