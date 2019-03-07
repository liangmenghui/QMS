package com.unind.base.domain.admin.group;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.unind.base.domain.admin.BaseEntity;
import com.unind.base.domain.admin.user.SysUser;

@Entity
@Table(name=SysGroup.TABLE_NAME)
@DynamicUpdate
public class SysGroup extends BaseEntity {
	private static final long serialVersionUID = 5392367045925123391L;
	public static final String TABLE_NAME = "sys_group";

	/**
	 * 编码
	 */
	@NotNull
	@Column(length = 25)
	protected String bsCode;
	/**
	 * 名称
	 */
	@NotNull
	@Column(length = 100)
	protected String bsName;
	/**
	 * 备注
	 */
	@Column(length = 255)
	protected String bsComment;

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
	@ManyToOne
	@JoinColumn(name="pkCreatedBy", insertable=false, updatable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	protected SysUser createdBy;

	@ManyToOne
	@JoinColumn(name="pkModifiedBy", insertable=false, updatable=false)
	@NotFound(action=NotFoundAction.IGNORE)
	protected SysUser modifiedBy;

	public String getBsCode() {
		return bsCode;
	}
	public void setBsCode(String bsCode) {
		this.bsCode = bsCode;
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

}
