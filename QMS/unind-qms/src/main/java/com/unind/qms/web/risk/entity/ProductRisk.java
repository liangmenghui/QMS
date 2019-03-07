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
 * @author chen
 */
@Entity
@Table(name = ProductRisk.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class ProductRisk extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "t_product_risk_info";

    /**
     * 产品ID
     */
    @ApiModelProperty(name="bsPrId",required=true,value="产品ID")
    @NotNull
    @Column
    protected Long bsPrId;

    /**
     * 风险等级
     */
    @ApiModelProperty(name="bsRiskLevel",value="风险等级")
    @Column
    protected int bsRiskLevel;

    /**
     * 风险等级得分
     */
    @ApiModelProperty(name="bsRiskScore",value="风险等级得分")
    @Column
    protected int bsRiskScore;

    /**
     * 量产批准得分
     */
    @ApiModelProperty(name="bsMassProductScore",value="量产批准得分")
    @Column
    protected int bsMassProductScore;

    /**
     * 量产批准类型
     */
    @ApiModelProperty(name="bsMassProductType",value="量产批准类型")
    @Column
    protected int bsMassProductType;

    /**
     * 过程审核得分
     */
    @ApiModelProperty(name="bsApprovedScore",value="过程审核得分")
    @Column
    protected int bsApprovedScore;

    /**
     * 过程审核得分类型
     */
    @ApiModelProperty(name="bsApprovedType",value="过程审核得分类型")
    @Column
    protected int bsApprovedType;

    /**
     * 标准化得分
     */
    @ApiModelProperty(name="bsStandardScore",value="标准化得分")
    @Column
    protected int bsStandardScore;

    /**
     * 标准化得分类型
     */
    @ApiModelProperty(name="bsStandardType",value="标准化得分类型")
    @Column
    protected int bsStandardType;

    /**
     * CPK得分
     */
    @ApiModelProperty(name="bsCpkScore",value="CPK得分")
    @Column
    protected int bsCpkScore;

    /**
     * CPK值
     */
    @ApiModelProperty(name="bsCpkValue",value="CPK值")
    @Column
    protected double bsCpkValue;

    /**
     * CPK得分类型
     */
    @ApiModelProperty(name="bsCpkType",value="CPK得分类型")
    @Column
    protected int bsCpkType;

    /**
     * GRR得分
     */
    @ApiModelProperty(name="bsGrrScore",value="GRR得分")
    @Column
    protected int bsGrrScore;

    /**
     * GRR值
     */
    @ApiModelProperty(name="bsGrrValue",value="GRR值")
    @Column
    protected double bsGrrValue;

    /**
     * GRR得分类型
     */
    @ApiModelProperty(name="bsGrrType",value="GRR得分类型")
    @Column
    protected int bsGrrType;

    /**
     * 工程更改得分
     */
    @ApiModelProperty(name="bsEngineeringScore",value="工程更改得分")
    @Column
    protected int bsEngineeringScore;

    /**
     * 工程更改得分类型
     */
    @ApiModelProperty(name="bsEngineeringType",value="工程更改得分类型")
    @Column
    protected int bsEngineeringType;

    /**
     * 验货不合格得分
     */
    @ApiModelProperty(name="bsInspectScore",value="验货不合格得分")
    @Column
    protected int bsInspectScore;

    /**
     * 验货不合格次数
     */
    @ApiModelProperty(name="bsInspectValue",value="验货不合格次数")
    @Column
    protected double bsInspectValue;

    /**
     * 验货不合格得分类型
     */
    @ApiModelProperty(name="bsInspectType",value="验货不合格得分类型")
    @Column
    protected int bsInspectType;

    /**
     * 客诉得分
     */
    @ApiModelProperty(name="bsFeedbackScore",value="客诉得分")
    @Column
    protected int bsFeedbackScore;

    /**
     * 客诉得分类型
     */
    @ApiModelProperty(name="bsFeedbackType",value="客诉得分类型")
    @Column
    protected int bsFeedbackType;

    /**
     * PPM得分
     */
    @ApiModelProperty(name="bsPpmScore",value="PPM得分")
    @Column
    protected int bsPpmScore;

    /**
     * PPM值
     */
    @ApiModelProperty(name="bsPpmValue",value="PPM值")
    @Column
    protected double bsPpmValue;

    /**
     * PPM得分类型
     */
    @ApiModelProperty(name="bsPpmType",value="PPM得分类型")
    @Column
    protected int bsPpmType;

    /**
     * 项目风险类型
     */
    @ApiModelProperty(name="bsProjectType",value="项目风险类型")
    @Column
    protected int bsProjectType;

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

    public Long getBsPrId() {
        return bsPrId;
    }

    public void setBsPrId(Long bsPrId) {
        this.bsPrId = bsPrId;
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

    public int getBsMassProductScore() {
        return bsMassProductScore;
    }

    public void setBsMassProductScore(int bsMassProductScore) {
        this.bsMassProductScore = bsMassProductScore;
    }

    public int getBsMassProductType() {
        return bsMassProductType;
    }

    public void setBsMassProductType(int bsMassProductType) {
        this.bsMassProductType = bsMassProductType;
    }

    public int getBsApprovedScore() {
        return bsApprovedScore;
    }

    public void setBsApprovedScore(int bsApprovedScore) {
        this.bsApprovedScore = bsApprovedScore;
    }

    public int getBsApprovedType() {
        return bsApprovedType;
    }

    public void setBsApprovedType(int bsApprovedType) {
        this.bsApprovedType = bsApprovedType;
    }

    public int getBsStandardScore() {
        return bsStandardScore;
    }

    public void setBsStandardScore(int bsStandardScore) {
        this.bsStandardScore = bsStandardScore;
    }

    public int getBsStandardType() {
        return bsStandardType;
    }

    public void setBsStandardType(int bsStandardType) {
        this.bsStandardType = bsStandardType;
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

    public int getBsGrrScore() {
        return bsGrrScore;
    }

    public void setBsGrrScore(int bsGrrScore) {
        this.bsGrrScore = bsGrrScore;
    }

    public double getBsGrrValue() {
        return bsGrrValue;
    }

    public void setBsGrrValue(double bsGrrValue) {
        this.bsGrrValue = bsGrrValue;
    }

    public int getBsGrrType() {
        return bsGrrType;
    }

    public void setBsGrrType(int bsGrrType) {
        this.bsGrrType = bsGrrType;
    }

    public int getBsEngineeringScore() {
        return bsEngineeringScore;
    }

    public void setBsEngineeringScore(int bsEngineeringScore) {
        this.bsEngineeringScore = bsEngineeringScore;
    }

    public int getBsEngineeringType() {
        return bsEngineeringType;
    }

    public void setBsEngineeringType(int bsEngineeringType) {
        this.bsEngineeringType = bsEngineeringType;
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

    public int getBsProjectType() {
        return bsProjectType;
    }

    public void setBsProjectType(int bsProjectType) {
        this.bsProjectType = bsProjectType;
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
