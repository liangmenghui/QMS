package com.unind.base.domain.admin.uergroup;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.unind.base.domain.admin.CommonEntity;

/**
 * 用户组
 * @author tanxiang
 *
 */
@Entity
@Table(name=SysUsergroup.TABLE_NAME)
@DynamicUpdate
public class SysUsergroup extends CommonEntity {
	private static final long serialVersionUID = -8412865927261264978L;
	public static final String TABLE_NAME = "sys_usergroup";

	/**
	 * 备注
	 */
	@Column(length=255)
	protected String bsComment;

	public String getBsComment() {
		return bsComment;
	}
	public void setBsComment(String bsComment) {
		this.bsComment = bsComment;
	}
	
}
