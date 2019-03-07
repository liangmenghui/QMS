package com.unind.base.domain.admin.resrce;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.unind.base.domain.admin.TreeEntity;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;

@Entity
@Table(name=SysResrce.TABLE_NAME)
@DynamicUpdate
public class SysResrce extends TreeEntity<Long> {
	private static final long serialVersionUID = -2954531842876043320L;
	public static final String TABLE_NAME = "sys_resrce";

	/**
	 * url
	 */
	@Column(length = 255)
	protected String bsUrl;

	/**
	 * 启用状态 1：启用 2：停用
	 * 默认值：2 停用
	 */
	@Column
	protected Integer bsEnableState = BaseEnumConstants.DISABLED;

	/**
	 * 备注
	 */
	@Column(length = 255)
	protected String bsComment;

	public String getBsUrl() {
		return bsUrl;
	}

	public void setBsUrl(String bsUrl) {
		this.bsUrl = bsUrl;
	}

	public Integer getBsEnableState() {
		return bsEnableState;
	}

	public void setBsEnableState(Integer bsEnableState) {
		this.bsEnableState = bsEnableState;
	}

	public String getBsComment() {
		return bsComment;
	}

	public void setBsComment(String bsComment) {
		this.bsComment = bsComment;
	}

}
