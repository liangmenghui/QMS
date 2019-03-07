package com.unind.base.domain.admin.role;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.unind.base.domain.admin.CommonEntity;

@Entity
@Table(name=SysRole.TABLE_NAME)
@DynamicUpdate
public class SysRole extends CommonEntity {
	private static final long serialVersionUID = -5951531333314901264L;
	public static final String TABLE_NAME = "sys_role";

	/**
	 * 所属角色组
	 */
	@Column
	protected Long pkSysRolegroup;

	/**
	 * 备注
	 */
	@Column(length=255)
	protected String bsComment;

	@ManyToOne
	@JoinColumn(name="pkSysRolegroup", insertable=false, updatable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	protected SysRolegroup rolegroup;

	public Long getPkSysRolegroup() {
		return pkSysRolegroup;
	}
	public void setPkSysRolegroup(Long pkSysRolegroup) {
		this.pkSysRolegroup = pkSysRolegroup;
	}
	public String getBsName() {
		return bsName;
	}
	public void setBsName(String bsName) {
		this.bsName = bsName;
	}
	public String getBsComment() {
		return bsComment;
	}
	public void setBsComment(String bsComment) {
		this.bsComment = bsComment;
	}
	public SysRolegroup getRolegroup() {
		return rolegroup;
	}
	public void setRolegroup(SysRolegroup rolegroup) {
		this.rolegroup = rolegroup;
	}

}
