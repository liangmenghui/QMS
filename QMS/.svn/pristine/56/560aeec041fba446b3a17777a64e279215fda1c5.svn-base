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
import com.unind.base.domain.admin.uergroup.SysUsergroup;
import com.unind.base.domain.admin.user.SysUser;

/**
 * 用户组用户关系
 * @author tanxiang
 *
 */
@Entity
@Table(name=SysUsergroupUserAgg.TABLE_NAME)
@DynamicUpdate
public class SysUsergroupUserAgg extends IdEntity {
	private static final long serialVersionUID = 6958899086145581296L;
	public static final String TABLE_NAME = "sys_usergroup_user_agg";

	/**
	 * 用户Pk
	 */
	@NotNull
	@Column
	protected Long pkUser;

	/**
	 * 用户组Pk
	 */
	@NotNull
	@Column
	protected Long pkUsergroup;

	public Long getPkUser() {
		return pkUser;
	}
	public void setPkUser(Long pkUser) {
		this.pkUser = pkUser;
	}
	public Long getPkUsergroup() {
		return pkUsergroup;
	}
	public void setPkUsergroup(Long pkUsergroup) {
		this.pkUsergroup = pkUsergroup;
	}


	@ManyToOne
	@JoinColumn(name="pkUser",insertable=false,updatable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	protected SysUser user;

	@ManyToOne
	@JoinColumn(name="pkUsergroup",insertable=false,updatable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	protected SysUsergroup usergroup;

	public SysUser getUser() {
		return user;
	}
	public void setUser(SysUser user) {
		this.user = user;
	}
	public SysUsergroup getUsergroup() {
		return usergroup;
	}
	public void setUsergroup(SysUsergroup usergroup) {
		this.usergroup = usergroup;
	}

}
