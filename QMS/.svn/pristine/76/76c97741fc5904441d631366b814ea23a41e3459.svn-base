package com.unind.base.domain.admin.dict;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.unind.base.domain.admin.CommonEntity;

/**
 * 数据字典表
 * @author tanxiang
 *
 */
@Entity
@Table(name=SysDict.TABLE_NAME)
@DynamicUpdate
public class SysDict extends CommonEntity {
	private static final long serialVersionUID = 4062427154837427849L;
	public final static String TABLE_NAME = "sys_dict";

	/**
	 * 所属数据字典目录
	 */
	protected Long pkDictCatalog;
	/**
	 * 数据字典值
	 */
	protected String bsValue;


	/**
	 * 所属字典目录
	 */
	@ManyToOne
	@JoinColumn(name = "pkDictCatalog", insertable = false, updatable = false)
	@NotFound(action=NotFoundAction.IGNORE)
	protected SysDictCatalog dictCatalog;

	public Long getPkDictCatalog() {
		return pkDictCatalog;
	}
	public void setPkDictCatalog(Long pkDictCatalog) {
		this.pkDictCatalog = pkDictCatalog;
	}
	public String getBsValue() {
		return bsValue;
	}
	public void setBsValue(String bsValue) {
		this.bsValue = bsValue;
	}
	public SysDictCatalog getDictCatalog() {
		return dictCatalog;
	}
	public void setDictCatalog(SysDictCatalog dictCatalog) {
		this.dictCatalog = dictCatalog;
	}

}
