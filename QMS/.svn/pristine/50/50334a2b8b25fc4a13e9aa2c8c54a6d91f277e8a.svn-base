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
import com.unind.base.domain.admin.uergroup.SysUsergroup;

/**
 * 用户组权限关系
 * @author tanxiang
 *
 */
@Entity
@Table(name=SysUsergroupPermAgg.TABLE_NAME)
@DynamicUpdate
public class SysUsergroupPermAgg extends IdEntity {
	private static final long serialVersionUID = 5608807859555819829L;
	public static final String TABLE_NAME = "sys_usergroup_perm_agg";

	/**
	 * 用户组Pk
	 */
	@NotNull
	@Column
	protected Long pkUsergroup;
	/**
	 * 权限Pk
	 */
	@NotNull
	@Column
	protected Long pkPerm;

	public Long getPkUsergroup() {
		return pkUsergroup;
	}
	public void setPkUsergroup(Long pkUsergroup) {
		this.pkUsergroup = pkUsergroup;
	}
	public Long getPkPerm() {
		return pkPerm;
	}
	public void setPkPerm(Long pkPerm) {
		this.pkPerm = pkPerm;
	}


	@ManyToOne
	@JoinColumn(name="pkUsergroup",insertable=false,updatable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	protected SysUsergroup usergroup;

	@ManyToOne
	@JoinColumn(name="pkPerm",insertable=false,updatable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	protected SysPerm perm;

	public SysUsergroup getUsergroup() {
		return usergroup;
	}
	public void setUsergroup(SysUsergroup usergroup) {
		this.usergroup = usergroup;
	}
	public SysPerm getPerm() {
		return perm;
	}
	public void setPerm(SysPerm perm) {
		this.perm = perm;
	}

}
