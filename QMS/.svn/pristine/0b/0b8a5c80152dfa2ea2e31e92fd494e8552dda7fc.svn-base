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
import com.unind.base.domain.admin.perm.SysPerm;
import com.unind.base.domain.admin.resrce.SysResrce;
import com.unind.base.domain.admin.role.SysRole;

/**
 * 角色资源权限关系
 * @author tanxiang
 *
 */
@Entity
@Table(name=SysRolePermsAgg.TABLE_NAME)
@DynamicUpdate
public class SysRolePermsAgg extends IdEntity {
	private static final long serialVersionUID = 8514511502283038345L;
	public static final String TABLE_NAME = "sys_role_perms_agg";

	/**
	 * 角色Pk
	 */
	@NotNull
	@Column
	protected Long pkRole;
	/**
	 * 资源PK
	 */
	@NotNull
	@Column
	protected Long pkResrce;
	/**
	 * 权限Pk
	 */
	@Column
	protected Long pkPerm;

	public Long getPkRole() {
		return pkRole;
	}
	public void setPkRole(Long pkRole) {
		this.pkRole = pkRole;
	}
	public Long getPkResrce() {
		return pkResrce;
	}
	public void setPkResrce(Long pkResrce) {
		this.pkResrce = pkResrce;
	}
	public Long getPkPerm() {
		return pkPerm;
	}
	public void setPkPerm(Long pkPerm) {
		this.pkPerm = pkPerm;
	}


	@ManyToOne
	@JoinColumn(name="pkRole",insertable=false,updatable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	protected SysRole role;

	@ManyToOne
	@JoinColumn(name="pkResrce",insertable=false,updatable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	protected SysResrce resrce;

	@ManyToOne
	@JoinColumn(name="pkPerm",insertable=false,updatable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	protected SysPerm perm;

	public SysRole getRole() {
		return role;
	}
	public void setRole(SysRole role) {
		this.role = role;
	}
	public SysResrce getResrce() {
		return resrce;
	}
	public void setResrce(SysResrce resrce) {
		this.resrce = resrce;
	}
	public SysPerm getPerm() {
		return perm;
	}
	public void setPerm(SysPerm perm) {
		this.perm = perm;
	}

}
