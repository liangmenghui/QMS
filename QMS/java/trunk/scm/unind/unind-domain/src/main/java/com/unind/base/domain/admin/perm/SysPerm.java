package com.unind.base.domain.admin.perm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.unind.base.domain.admin.CommonEntity;

@Entity
@Table(name=SysPerm.TABLE_NAME)
@DynamicUpdate
public class SysPerm extends CommonEntity {
	private static final long serialVersionUID = 5608807859555819829L;
	public static final String TABLE_NAME = "sys_perm";

	/**
	 * 备注
	 */
	@Column(length=255)
	protected String bsComment;

	public String getBsComment() {
		return bsComment;
	}
	public void setBsComment(String bsComment) {
		this.bsComment = bsComment;
	}

}
