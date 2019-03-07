package com.unind.base.domain.admin.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.unind.base.domain.admin.TreeEntity;
import com.unind.base.domain.admin.user.SysUser;

/**
 * 角色组
 * 
 * @author tanxiang
 *
 */
@Entity
@Table(name = SysRolegroup.TABLE_NAME)
@DynamicUpdate
public class SysRolegroup extends TreeEntity<SysUser> {
	private static final long serialVersionUID = -4318345560180181434L;
	public static final String TABLE_NAME = "sys_rolegroup";

	/**
	 * 备注
	 */
	@Column(length = 255)
	protected String bsComment;

	public String getBsComment() {
		return bsComment;
	}

	public void setBsComment(String bsComment) {
		this.bsComment = bsComment;
	}

}
