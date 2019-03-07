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

/**
 * 资源权限
 * @author tanxiang
 *
 */
@Entity
@Table(name=SysResrcePermAgg.TABLE_NAME)
@DynamicUpdate
public class SysResrcePermAgg extends IdEntity {
	private static final long serialVersionUID = 5734374319399787475L;
	public static final String TABLE_NAME = "sys_resrce_perm_agg";

	/**
	 * 资源Pk
	 */
	@NotNull
	@Column
	protected Long pkResrce;

	/**
	 * 权限Pk
	 */
	@NotNull
	@Column
	protected Long pkPerm;

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
	@JoinColumn(name="pkResrce",insertable=false,updatable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	protected SysResrce resrce;

	@ManyToOne
	@JoinColumn(name="pkPerm",insertable=false,updatable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	protected SysPerm perm;

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
