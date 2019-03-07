package com.unind.qms.web.approved.entity;

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
import com.unind.qms.web.supplier.entity.SupplierInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author fyx
 * EHS审核条款记录
 */
@Entity
@Table(name = ApprovedEHSRecord.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class ApprovedEHSRecord extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "t_approved_EHS_Record";
    
    /**
     * 审核EHS条款ID
     */
    @ApiModelProperty(name="bsEHSItemsId",required=true,value="审核EHS条款ID")
    @NotNull
    @Column
    protected Long bsEHSItemsId;

    @ApiModelProperty(name="itemsBy",hidden=true,value="项目")
    @ManyToOne
    @JoinColumn(name = "bsEHSItemsId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected ApprovedEHSTerms itemsBy;
    
    
    /**
     * 审核EHS条款记录主表ID
     */
    @ApiModelProperty(name="mapId",required=true,value="审核EHS条款记录主表ID")
    @NotNull
    @Column
    protected Long mapId;

    @ApiModelProperty(name="ehsMap",hidden=true,value="主表")
    @ManyToOne
    @JoinColumn(name = "mapId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected ApprovedEHSMap ehsMap;
    /**
     * 供应商ID
     */
    /*@ApiModelProperty(name="supplierId",required=true,value="供应商ID")
    @NotNull
    @Column
    protected Long supplierId;

    @ApiModelProperty(name="supplierBy",hidden=true,value="项目")
    @ManyToOne
    @JoinColumn(name = "supplierId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected SupplierInfo supplierBy;*/

    /**
     * 审核结论（Y/N/-）
     */
    @ApiModelProperty(name="ehsEval",required=true,value="审核结论")
    @Column(length = 20)
    protected String ehsEval;
    /**
     * 问题
     */
    @ApiModelProperty(name="ehsViolation",required=true,value="问题")
    @Column(length = 1000)
    protected String ehsViolation;
	
	/**
     * 建议改善方案
     */
    @ApiModelProperty(name="ehsCommentsReference",required=true,value="建议改善方案")
    @Column(length = 1000)
    protected String ehsCommentsReference;
    
    /**
     * 采取的行动计划
     */
    @ApiModelProperty(name="ehsCorrectiveAction",required=true,value="建议改善方案")
    @Column(length = 1000)
    protected String ehsCorrectiveAction;

    /**
     * 条款备注
     */
    @ApiModelProperty(name="ehsRemark",value="条款备注")
    @Column(length = 450)
    protected String ehsRemark;  
    
    /**
     * 状态 0:保存 1:提交
     */
    @ApiModelProperty(name="bsStatus",value="状态")
    @Column(length = 20)
    protected String bsStatus;

    /**
     * 创建人
     */
    @ApiModelProperty(name="pkCreatedBy",hidden=true,value="创建人")
    @Column
    protected Long pkCreatedBy;
    /**
     * 修改人
     */
    @ApiModelProperty(name="pkModifiedBy",hidden=true,value="修改人")
    @Column
    protected Long pkModifiedBy;

    @ApiModelProperty(name="createdBy",hidden=true,value="创建人--user")
    @ManyToOne
    @JoinColumn(name = "pkCreatedBy", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected SysUser createdBy;

	@ApiModelProperty(name="modifiedBy",hidden=true,value="修改人--user")
    @ManyToOne
    @JoinColumn(name = "pkModifiedBy", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
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

	public Long getBsEHSItemsId() {
		return bsEHSItemsId;
	}

	public void setBsEHSItemsId(Long bsEHSItemsId) {
		this.bsEHSItemsId = bsEHSItemsId;
	}

	public ApprovedEHSTerms getItemsBy() {
		return itemsBy;
	}

	public void setItemsBy(ApprovedEHSTerms itemsBy) {
		this.itemsBy = itemsBy;
	}
	

	public String getEhsEval() {
		return ehsEval;
	}

	public void setEhsEval(String ehsEval) {
		this.ehsEval = ehsEval;
	}

	public String getEhsCommentsReference() {
		return ehsCommentsReference;
	}

	public void setEhsCommentsReference(String ehsCommentsReference) {
		this.ehsCommentsReference = ehsCommentsReference;
	}

	public String getEhsRemark() {
		return ehsRemark;
	}

	public void setEhsRemark(String ehsRemark) {
		this.ehsRemark = ehsRemark;
	}

	public String getEhsViolation() {
		return ehsViolation;
	}

	public void setEhsViolation(String ehsViolation) {
		this.ehsViolation = ehsViolation;
	}

	public String getEhsCorrectiveAction() {
		return ehsCorrectiveAction;
	}

	public void setEhsCorrectiveAction(String ehsCorrectiveAction) {
		this.ehsCorrectiveAction = ehsCorrectiveAction;
	}

	

	public Long getMapId() {
		return mapId;
	}

	public void setMapId(Long mapId) {
		this.mapId = mapId;
	}

	public ApprovedEHSMap getEhsMap() {
		return ehsMap;
	}

	public void setEhsMap(ApprovedEHSMap ehsMap) {
		this.ehsMap = ehsMap;
	}

	public String getBsStatus() {
		return bsStatus;
	}

	public void setBsStatus(String bsStatus) {
		this.bsStatus = bsStatus;
	}
    
    
}
