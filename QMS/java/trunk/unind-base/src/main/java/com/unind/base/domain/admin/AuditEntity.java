package com.unind.base.domain.admin;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.unind.base.domain.admin.group.SysGroup;
import com.unind.base.domain.admin.org.SysOrg;
import com.unind.base.domain.admin.user.SysUser;

@MappedSuperclass
public abstract class AuditEntity extends BaseEntity {
	private static final long serialVersionUID = -6784498964459870355L;

	/**
	 * 创建人
	 */
	@Column
	protected Long pkCreatedBy;
	/**
	 * 修改人
	 */
	@Column
    protected Long pkModifiedBy;
	/**
	 * 所属组织
	 */
	@Column
	protected Long pkGroup;
	/**
	 * 所属组织
	 */
	@Column
	protected Long pkOrg;
	/**
	 * 所属组织
	 */
	@ManyToOne
	@JoinColumn(name = "pkGroup", insertable = false, updatable = false)
	@NotFound(action=NotFoundAction.IGNORE)
	protected SysGroup group;
	/**
	 * 所属组织
	 */
	@ManyToOne
	@JoinColumn(name = "pkOrg", insertable = false, updatable = false)
	@NotFound(action=NotFoundAction.IGNORE)
	protected SysOrg org;

	@ManyToOne
	@JoinColumn(name="pkCreatedBy", insertable=false, updatable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	protected SysUser createdBy;

	@ManyToOne
	@JoinColumn(name="pkModifiedBy", insertable=false, updatable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	protected SysUser modifiedBy;

	public Long getPkCreatedBy() {
		return pkCreatedBy;
	}
	public void setPkCreatedBy(Long pkCreatedBy) {
		this.pkCreatedBy = pkCreatedBy;
	}
	public Long getPkModifiedBy() {
		return pkModifiedBy;
	}
	public void setPkModifiedBy(Long pkModifiedBy) {
		this.pkModifiedBy = pkModifiedBy;
	}
	public SysUser getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(SysUser createdBy) {
		this.createdBy = createdBy;
	}
	public SysUser getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(SysUser modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Long getPkGroup() {
		return pkGroup;
	}
	public void setPkGroup(Long pkGroup) {
		this.pkGroup = pkGroup;
	}
	public Long getPkOrg() {
		return pkOrg;
	}
	public void setPkOrg(Long pkOrg) {
		this.pkOrg = pkOrg;
	}
	public SysGroup getGroup() {
		return group;
	}
	public void setGroup(SysGroup group) {
		this.group = group;
	}
	public SysOrg getOrg() {
		return org;
	}
	public void setOrg(SysOrg org) {
		this.org = org;
	}
}
