package com.unind.base.domain.admin.dict;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.unind.base.domain.admin.CommonEntity;

/**
 * 数据字典目录
 * @author tanxiang
 *
 */
@Entity
@Table(name=SysDictCatalog.TABLE_NAME)
@DynamicUpdate
public class SysDictCatalog extends CommonEntity {
	private static final long serialVersionUID = -2140783152943943761L;
	public final static String TABLE_NAME = "sys_dict_catalog";

	@Column(length = 255)
	protected String bsComment;

	public String getBsComment() {
		return bsComment;
	}
	public void setBsComment(String bsComment) {
		this.bsComment = bsComment;
	}

}
