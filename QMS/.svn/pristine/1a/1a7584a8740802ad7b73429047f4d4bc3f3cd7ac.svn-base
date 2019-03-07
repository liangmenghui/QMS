package com.unind.base.domain.admin.agg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.unind.base.domain.admin.IdEntity;
import com.unind.base.domain.admin.role.SysRole;
import com.unind.base.domain.admin.user.SysUser;

/**
 * 用户角色关系
 * @author tanxiang
 *
 */
@Entity
@Table(name=SysUserRolesAgg.TABLE_NAME)
@DynamicUpdate
public class SysUserRolesAgg extends IdEntity {
	private static final long serialVersionUID = -1009660832539206518L;
	public static final String TABLE_NAME = "sys_user_roles_agg";

	/**
	 * 用户Pk
	 */
	@Column
	@NotNull
	protected Long pkUser;

	/**
	 * 角色Pk
	 */
	@Column
	@NotNull
	protected Long pkRole;

	public Long getPkUser() {
		return pkUser;
	}
	public void setPkUser(Long pkUser) {
		this.pkUser = pkUser;
	}
	public Long getPkRole() {
		return pkRole;
	}
	public void setPkRole(Long pkRole) {
		this.pkRole = pkRole;
	}

	@ManyToOne
	@JoinColumn(name="pkUser",insertable=false,updatable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	protected SysUser user;

	@ManyToOne
	@JoinColumn(name="pkRole",insertable=false,updatable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	protected SysRole role;

	public SysUser getUser() {
		return user;
	}
	public void setUser(SysUser user) {
		this.user = user;
	}
	public SysRole getRole() {
		return role;
	}
	public void setRole(SysRole role) {
		this.role = role;
	}

}
