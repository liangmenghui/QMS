package com.unind.qms.web.risk.entity;

import com.unind.base.domain.admin.BaseEntity;
import com.unind.base.domain.admin.user.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Shen
 * 风险管理记录
 */
@Entity
@Table(name = RiskLevelRecord.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class RiskLevelRecord extends BaseEntity {

    private static final long serialVersionUID = 1154151142960277043L;
    public static final String TABLE_NAME = "t_risk_level_record";

    /**
     * 产品ID
     */
    @ApiModelProperty(name = "bsPrId", value = "产品ID")
    @Column
    protected Long bsPrId;

    /**
     * 供应商ID
     */
    @ApiModelProperty(name = "bsSuppId", value = "供应商ID")
    @Column
    protected Long bsSuppId;

    /**
     * 类型（12.供应商，23.产品）
     */
    @ApiModelProperty(name = "bsType", required = true, value = "类型（12.供应商，23.产品）")
    @Column
    protected int bsType;

    /**
     * 原风险等级
     */
    @ApiModelProperty(name = "bsOriginalRiskLevel", required = true, value = "原风险等级")
    @Column
    protected int bsOriginalRiskLevel;

    /**
     * 新风险等级
     */
    @ApiModelProperty(name = "bsNewRiskLevel", required = true, value = "新风险等级")
    @Column
    protected int bsNewRiskLevel;

    /**
     * 批准人ID
     */
    @ApiModelProperty(name = "bsApproverId", value = "批准人ID")
    @NotNull
    @Column
    protected Long bsApproverId;

    @ApiModelProperty(name = "approvedBy", hidden = true, value = "批准人--user")
    @ManyToOne
    @JoinColumn(name = "bsApproverId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected SysUser approvedBy;

    /**
     * 申请人ID
     */
    @ApiModelProperty(name = "bsApplicantId", required = true, value = "申请人ID")
    @NotNull
    @Column
    protected Long bsApplicantId;

    @ApiModelProperty(name = "applyBy", hidden = true, value = "申请人--user")
    @ManyToOne
    @JoinColumn(name = "bsApplicantId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected SysUser applyBy;

    /**
     * 申请意见
     */
    @ApiModelProperty(name = "bsAdvice", value = "申请意见")
    @Column(length = 500)
    protected String bsAdvice;

    public Long getBsPrId() {
        return bsPrId;
    }

    public void setBsPrId(Long bsPrId) {
        this.bsPrId = bsPrId;
    }

    public Long getBsSuppId() {
        return bsSuppId;
    }

    public void setBsSuppId(Long bsSuppId) {
        this.bsSuppId = bsSuppId;
    }

    public int getBsType() {
        return bsType;
    }

    public void setBsType(int bsType) {
        this.bsType = bsType;
    }

    public int getBsOriginalRiskLevel() {
        return bsOriginalRiskLevel;
    }

    public void setBsOriginalRiskLevel(int bsOriginalRiskLevel) {
        this.bsOriginalRiskLevel = bsOriginalRiskLevel;
    }

    public int getBsNewRiskLevel() {
        return bsNewRiskLevel;
    }

    public void setBsNewRiskLevel(int bsNewRiskLevel) {
        this.bsNewRiskLevel = bsNewRiskLevel;
    }

    public Long getBsApproverId() {
        return bsApproverId;
    }

    public void setBsApproverId(Long bsApproverId) {
        this.bsApproverId = bsApproverId;
    }

    public SysUser getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(SysUser approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Long getBsApplicantId() {
        return bsApplicantId;
    }

    public void setBsApplicantId(Long bsApplicantId) {
        this.bsApplicantId = bsApplicantId;
    }

    public SysUser getApplyBy() {
        return applyBy;
    }

    public void setApplyBy(SysUser applyBy) {
        this.applyBy = applyBy;
    }

    public String getBsAdvice() {
        return bsAdvice;
    }

    public void setBsAdvice(String bsAdvice) {
        this.bsAdvice = bsAdvice;
    }
}
