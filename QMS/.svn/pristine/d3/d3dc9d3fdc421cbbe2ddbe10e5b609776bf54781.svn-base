package com.unind.base.dbconnection.query;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("rawtypes")
public class Datagrid implements Serializable {
	private static final long serialVersionUID = -8747473618280525378L;

	public Datagrid() {
	}

	public static Datagrid create() {
		return new Datagrid();
	}

	public static Datagrid create(List rows, int total) {
		return create(rows, total, 0, 0);
	}

	public static Datagrid create(List rows, int total, int page, int pageSize) {
		return new Datagrid(rows, total, page, pageSize);
	}

	public Datagrid(List rows, int total, int page, int pageSize) {
		this.rows = rows;
		this.total = total;
		this.page = page;
		this.pageSize = pageSize;
	}

	public Datagrid(List rows, int total) {
		this.rows = rows;
		this.total = total;
	}

	protected List rows;
	/**
	 * 页码
	 */
	protected int page;
	/**
	 * 页大小
	 */
	protected int pageSize;
	/**
	 * 总数
	 */
	protected int total;

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

	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

}
