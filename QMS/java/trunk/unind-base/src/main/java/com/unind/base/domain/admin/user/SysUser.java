package com.unind.base.domain.admin.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unind.base.domain.admin.CommonEntity;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.domain.admin.enumeration.UserStatusEnum;

@Entity
@Table(name=SysUser.TABLE_NAME)
@DynamicUpdate
public class SysUser extends CommonEntity {
	private static final long serialVersionUID = 4625660587007894370L;
	public final static String TABLE_NAME = "sys_user";

	/**
	 * 邮箱
	 */
	@Column(length=100)
	protected String bsEmail;
	/**
	 * 手机号
	 */
	@Column(length=20)
	protected String bsMobile;
	/**
	 * 密码
	 */
	@NotNull
	@Column(length=100)
	protected String bsPassword;
	/**
	 * 密钥
	 */
    @JsonIgnore
	@Column(length=100)
	protected String bsSalt;
	/**
	 * 帐号状态
	 * @see UserStatusEnum
	 */
	protected Integer bsStatus = BaseEnumConstants.USER_ENABLED;
	/**
	 * 注释
	 */
	@Column(length=255)
	protected String bsComment;

	public String getBsEmail() {
		return bsEmail;
	}
	public void setBsEmail(String bsEmail) {
		this.bsEmail = bsEmail;
	}
	public String getBsMobile() {
		return bsMobile;
	}
	public void setBsMobile(String bsMobile) {
		this.bsMobile = bsMobile;
	}
	public String getBsPassword() {
		return bsPassword;
	}
	public void setBsPassword(String bsPassword) {
		this.bsPassword = bsPassword;
	}
	public String getBsSalt() {
		return bsSalt;
	}
	public void setBsSalt(String bsSalt) {
		this.bsSalt = bsSalt;
	}
	public Integer getBsStatus() {
		return bsStatus;
	}
	public void setBsStatus(Integer bsStatus) {
		this.bsStatus = bsStatus;
	}
	public String getBsComment() {
		return bsComment;
	}
	public void setBsComment(String bsComment) {
		this.bsComment = bsComment;
	}

}
