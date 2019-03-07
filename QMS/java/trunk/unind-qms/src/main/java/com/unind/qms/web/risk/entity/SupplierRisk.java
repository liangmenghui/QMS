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
import java.util.function.Supplier;

/**
 * @author Shen
 */
@Entity
@Table(name = SupplierRisk.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class SupplierRisk extends BaseEntity {

    private static final long serialVersionUID = 1154151142960277043L;
    public static final String TABLE_NAME = "t_supplier_risk_info";

    /**
     * 供应商ID
     */
    @ApiModelProperty(name = "bsSuppId", required = true, value = "供应商ID")
    @NotNull
    @Column
    protected Long bsSuppId;

    /**
     * 风险等级
     */
    @ApiModelProperty(name = "bsRiskLevel", value = "风险等级")
    @Column
    protected int bsRiskLevel;

    /**
     * 风险等级得分
     */
    @ApiModelProperty(name = "bsRiskScore", value = "风险等级得分")
    @Column
    protected int bsRiskScore;

    /**
     * 客诉得分
     */
    @ApiModelProperty(name = "bsFeedbackScore", value = "客诉得分")
    @Column
    protected int bsFeedbackScore;

    /**
     * 客诉得分类型
     */
    @ApiModelProperty(name = "bsFeedbackType", value = "客诉得分类型")
    @Column
    protected int bsFeedbackType;

    /**
     * PPM得分
     */
    @ApiModelProperty(name = "bsPpmScore", value = "PPM得分")
    @Column
    protected int bsPpmScore;

    /**
     * PPM值
     */
    @ApiModelProperty(name = "bsPpmValue", value = "PPM值")
    @Column
    protected double bsPpmValue;

    /**
     * PPM得分类型
     */
    @ApiModelProperty(name = "bsPpmType", value = "PPM得分类型")
    @Column
    protected int bsPpmType;

    /**
     * 交货达成得分
     */
    @ApiModelProperty(name = "bsDeliveryScore", value = "交货达成得分")
    @Column
    protected int bsDeliveryScore;

    /**
     * 交货达成类型
     */
    @ApiModelProperty(name = "bsDeliveryType", value = "交货达成类型")
    @Column
    protected int bsDeliveryType;

    /**
     * 验货不合格得分
     */
    @ApiModelProperty(name = "bsInspectScore", value = "验货不合格得分")
    @Column
    protected int bsInspectScore;

    /**
     * 验货不合格次数
     */
    @ApiModelProperty(name = "bsInspectValue", value = "验货不合格次数")
    @Column
    protected double bsInspectValue;

    /**
     * 验货不合格得分类型
     */
    @ApiModelProperty(name = "bsInspectType", value = "验货不合格得分类型")
    @Column
    protected int bsInspectType;

    /**
     * 付款期得分
     */
    @ApiModelProperty(name = "bsPaymentScore", value = "付款期得分")
    @Column
    protected int bsPaymentScore;

    /**
     * 付款期得分类型
     */
    @ApiModelProperty(name = "bsPaymentType", value = "付款期得分类型")
    protected int bsPaymentType;

    /**
     * CPK得分
     */
    @ApiModelProperty(name = "bsCpkScore", value = "CPK得分")
    @Column
    protected int bsCpkScore;

    /**
     * CPK值
     */
    @ApiModelProperty(name = "bsCpkValue", value = "CPK值")
    @Column
    protected double bsCpkValue;

    /**
     * CPK得分类型
     */
    @ApiModelProperty(name = "bsCpkType", value = "CPK得分类型")
    @Column
    protected int bsCpkType;

    /**
     * 高风险产品得分
     */
    @ApiModelProperty(name = "bsDangerProScore", value = "高风险产品得分")
    @Column
    protected int bsDangerProScore;

    /**
     * 高风险产品数量
     */
    @ApiModelProperty(name = "bsDangerProCount", value = "高风险产品数量")
    @Column
    protected int bsDangerProCount;

    /**
     * 高风险产品得分类型
     */
    @ApiModelProperty(name = "bsDangerProType", value = "高风险产品得分类型")
    @Column
    protected int bsDangerProType;

    /**
     * 体系审核得分
     */
    @ApiModelProperty(name = "bsApprovedScore", value = "体系审核得分")
    @Column
    protected int bsApprovedScore;

    /**
     * 体系审核得分类型
     */
    @ApiModelProperty(name = "bsApproveType", value = "体系审核得分类型")
    @Column
    protected int bsApproveType;

    /**
     * EHS得分
     */
    @ApiModelProperty(name = "bsEhsScore", value = "EHS得分")
    @Column
    protected int bsEhsScore;

    /**
     * EHS数值
     */
    @ApiModelProperty(name = "bsEhsValue", value = "EHS数值")
    @Column
    protected double bsEhsValue;

    /**
     * EHS得分类型
     */
    @ApiModelProperty(name = "bsEhsType", value = "EHS得分类型")
    @Column
    protected int bsEhsType;

    /**
     * 商务得分
     */
    @ApiModelProperty(name = "bsBusinessScore", value = "商务得分")
    @Column
    protected int bsBusinessScore;

    /**
     * 公司股东构成的稳定性
     */
    @ApiModelProperty(name = "bsComStability", value = "公司股东构成的稳定性")
    @Column
    protected boolean bsComStability;

    /**
     * 反腐协议未签订
     */
    @ApiModelProperty(name = "bsAntiCorruptProtocol", value = "反腐协议未签订")
    @Column
    protected boolean bsAntiCorruptProtocol;

    /**
     * 长期协议未签订
     */
    @ApiModelProperty(name = "bsLongTermProtocol", value = "长期协议未签订")
    @Column
    protected boolean bsLongTermProtocol;

    /**
     * 战略合作协议未签订
     */
    @ApiModelProperty(name = "bsCooperProtocol", value = "战略合作协议未签订")
    @Column
    protected boolean bsCooperProtocol;

    /**
     * 商务手册未签订
     */
    @ApiModelProperty(name = "bsBusinessBroScore", value = "商务手册未签订")
    @Column
    protected boolean bsBusinessBroScore;

    /**
     * 修改风险等级状态(0.完成 1.进行中)
     */
    @ApiModelProperty(name="bsStatus",value="修改风险等级状态(0.完成 1.进行中)")
    @Column
    protected int bsStatus;

    /**
     * 修改人
     */
    @ApiModelProperty(name = "pkModifiedBy", hidden = true, value = "修改人")
    @Column
    protected Long pkModifiedBy;

    @ApiModelProperty(name = "modifiedBy", hidden = true, value = "修改人--user")
    @ManyToOne
    @JoinColumn(name = "pkModifiedBy", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected SysUser modifiedBy;

    public Long getBsSuppId() {
        return bsSuppId;
    }

    public void setBsSuppId(Long bsSuppId) {
        this.bsSuppId = bsSuppId;
    }

    public int getBsRiskLevel() {
        return bsRiskLevel;
    }

    public void setBsRiskLevel(int bsRiskLevel) {
        this.bsRiskLevel = bsRiskLevel;
    }

    public int getBsRiskScore() {
        return bsRiskScore;
    }

    public void setBsRiskScore(int bsRiskScore) {
        this.bsRiskScore = bsRiskScore;
    }

    public int getBsFeedbackScore() {
        return bsFeedbackScore;
    }

    public void setBsFeedbackScore(int bsFeedbackScore) {
        this.bsFeedbackScore = bsFeedbackScore;
    }

    public int getBsFeedbackType() {
        return bsFeedbackType;
    }

    public void setBsFeedbackType(int bsFeedbackType) {
        this.bsFeedbackType = bsFeedbackType;
    }

    public int getBsPpmScore() {
        return bsPpmScore;
    }

    public void setBsPpmScore(int bsPpmScore) {
        this.bsPpmScore = bsPpmScore;
    }

    public double getBsPpmValue() {
        return bsPpmValue;
    }

    public void setBsPpmValue(double bsPpmValue) {
        this.bsPpmValue = bsPpmValue;
    }

    public int getBsPpmType() {
        return bsPpmType;
    }

    public void setBsPpmType(int bsPpmType) {
        this.bsPpmType = bsPpmType;
    }

    public int getBsDeliveryScore() {
        return bsDeliveryScore;
    }

    public void setBsDeliveryScore(int bsDeliveryScore) {
        this.bsDeliveryScore = bsDeliveryScore;
    }

    public int getBsDeliveryType() {
        return bsDeliveryType;
    }

    public void setBsDeliveryType(int bsDeliveryType) {
        this.bsDeliveryType = bsDeliveryType;
    }

    public int getBsInspectScore() {
        return bsInspectScore;
    }

    public void setBsInspectScore(int bsInspectScore) {
        this.bsInspectScore = bsInspectScore;
    }

    public double getBsInspectValue() {
        return bsInspectValue;
    }

    public void setBsInspectValue(double bsInspectValue) {
        this.bsInspectValue = bsInspectValue;
    }

    public int getBsInspectType() {
        return bsInspectType;
    }

    public void setBsInspectType(int bsInspectType) {
        this.bsInspectType = bsInspectType;
    }

    public int getBsPaymentScore() {
        return bsPaymentScore;
    }

    public void setBsPaymentScore(int bsPaymentScore) {
        this.bsPaymentScore = bsPaymentScore;
    }

    public int getBsPaymentType() {
        return bsPaymentType;
    }

    public void setBsPaymentType(int bsPaymentType) {
        this.bsPaymentType = bsPaymentType;
    }

    public int getBsCpkScore() {
        return bsCpkScore;
    }

    public void setBsCpkScore(int bsCpkScore) {
        this.bsCpkScore = bsCpkScore;
    }

    public double getBsCpkValue() {
        return bsCpkValue;
    }

    public void setBsCpkValue(double bsCpkValue) {
        this.bsCpkValue = bsCpkValue;
    }

    public int getBsCpkType() {
        return bsCpkType;
    }

    public void setBsCpkType(int bsCpkType) {
        this.bsCpkType = bsCpkType;
    }

    public int getBsDangerProScore() {
        return bsDangerProScore;
    }

    public void setBsDangerProScore(int bsDangerProScore) {
        this.bsDangerProScore = bsDangerProScore;
    }

    public int getBsDangerProCount() {
        return bsDangerProCount;
    }

    public void setBsDangerProCount(int bsDangerProCount) {
        this.bsDangerProCount = bsDangerProCount;
    }

    public int getBsDangerProType() {
        return bsDangerProType;
    }

    public void setBsDangerProType(int bsDangerProType) {
        this.bsDangerProType = bsDangerProType;
    }

    public int getBsApprovedScore() {
        return bsApprovedScore;
    }

    public void setBsApprovedScore(int bsApprovedScore) {
        this.bsApprovedScore = bsApprovedScore;
    }

    public int getBsApproveType() {
        return bsApproveType;
    }

    public void setBsApproveType(int bsApproveType) {
        this.bsApproveType = bsApproveType;
    }

    public int getBsEhsScore() {
        return bsEhsScore;
    }

    public void setBsEhsScore(int bsEhsScore) {
        this.bsEhsScore = bsEhsScore;
    }

    public double getBsEhsValue() {
        return bsEhsValue;
    }

    public void setBsEhsValue(double bsEhsValue) {
        this.bsEhsValue = bsEhsValue;
    }

    public int getBsEhsType() {
        return bsEhsType;
    }

    public void setBsEhsType(int bsEhsType) {
        this.bsEhsType = bsEhsType;
    }

    public int getBsBusinessScore() {
        return bsBusinessScore;
    }

    public void setBsBusinessScore(int bsBusinessScore) {
        this.bsBusinessScore = bsBusinessScore;
    }

    public boolean isBsComStability() {
        return bsComStability;
    }

    public void setBsComStability(boolean bsComStability) {
        this.bsComStability = bsComStability;
    }

    public boolean isBsAntiCorruptProtocol() {
        return bsAntiCorruptProtocol;
    }

    public void setBsAntiCorruptProtocol(boolean bsAntiCorruptProtocol) {
        this.bsAntiCorruptProtocol = bsAntiCorruptProtocol;
    }

    public boolean isBsLongTermProtocol() {
        return bsLongTermProtocol;
    }

    public void setBsLongTermProtocol(boolean bsLongTermProtocol) {
        this.bsLongTermProtocol = bsLongTermProtocol;
    }

    public boolean isBsCooperProtocol() {
        return bsCooperProtocol;
    }

    public void setBsCooperProtocol(boolean bsCooperProtocol) {
        this.bsCooperProtocol = bsCooperProtocol;
    }

    public boolean isBsBusinessBroScore() {
        return bsBusinessBroScore;
    }

    public void setBsBusinessBroScore(boolean bsBusinessBroScore) {
        this.bsBusinessBroScore = bsBusinessBroScore;
    }

    public int getBsStatus() {
        return bsStatus;
    }

    public void setBsStatus(int bsStatus) {
        this.bsStatus = bsStatus;
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
