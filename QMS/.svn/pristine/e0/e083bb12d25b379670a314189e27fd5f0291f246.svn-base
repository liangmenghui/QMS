package com.unind.qms.web.supplier.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unind.base.domain.admin.BaseEntity;
import com.unind.base.domain.admin.user.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author fyx
 * 供应商特批信息记录表
 */
@Entity
@Table(name = SpecialCheckSupplierInfo.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class SpecialCheckSupplierInfo extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "t_supplier_specail";

    /**
     * 有效时间
     */
    @ApiModelProperty(name="bsValidDate",required=true,value="有效时间")
    @Column(length = 50)
    protected String bsValidDate;

    /**
     * 供应商编号
     */
    @ApiModelProperty(name="bsSuppCode",required=true,value="供应商编号")
    @NotNull
    @Column(length = 50)
    protected String bsSuppCode;

    /**
     * 特批类别
     */
    @ApiModelProperty(name="bsCheckType",required=true,value="特批类别")
    @NotNull
    @Column(length = 150)
    protected String bsCheckType;
    /**
     * 修改人
     */
    @ApiModelProperty(name="pkModifiedBy",hidden=true,value="修改人")
    @Column
    protected Long pkModifiedBy;

    @ApiModelProperty(name="modifiedBy",hidden=true,value="修改人--user")
    @ManyToOne
    @JoinColumn(name = "pkModifiedBy", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected SysUser modifiedBy;
    
    /**
     * 备注
     */
    @ApiModelProperty(name="bsRemark",value="备注")
    @Column(length = 500)
    protected String bsRemark;
    
    

    public String getBsValidDate() {
		return bsValidDate;
	}

	public void setBsValidDate(String bsValidDate) {
		this.bsValidDate = bsValidDate;
	}

	public String getBsCheckType() {
		return bsCheckType;
	}

	public void setBsCheckType(String bsCheckType) {
		this.bsCheckType = bsCheckType;
	}

	public String getBsRemark() {
		return bsRemark;
	}

	public void setBsRemark(String bsRemark) {
		this.bsRemark = bsRemark;
	}

	public String getBsSuppCode() {
        return bsSuppCode;
    }

    public void setBsSuppCode(String bsSuppCode) {
        this.bsSuppCode = bsSuppCode;
    }


    public Long getPkModifiedBy() {
        return pkModifiedBy;
    }

    public void setPkModifiedBy(Long pkModifiedBy) {
        this.pkModifiedBy = pkModifiedBy;
    }

    public SysUser getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(SysUser modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
