package com.unind.qms.web.risk.entity;

import com.unind.base.domain.admin.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

/**
 * 风险等级管理（审核周期和检验水平）
 * @author Shen
 */
@Entity
@Table(name = RiskLevelManagement.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class RiskLevelManagement extends BaseEntity {
    private static final long serialVersionUID = 7620339498990815294L;
    public static final String TABLE_NAME = "t_risk_level_management";

    /**
     * 风险等级
     */
    @ApiModelProperty(name = "bsRiskLevel", value = "风险等级")
    @Column
    protected int bsRiskLevel;

    /**
     * 供应商审核周期（单位：天）
     */
    @ApiModelProperty(name = "bsAuditCycle", value = "供应商审核周期（单位：天）")
    @Column
    protected int bsAuditCycle;

    /**
     * 检验水平
     */
    @ApiModelProperty(name = "bsVerificationLevel", value = "检验水平")
    @Column(length = 25)
    protected String bsVerificationLevel;

    public int getBsRiskLevel() {
        return bsRiskLevel;
    }

    public void setBsRiskLevel(int bsRiskLevel) {
        this.bsRiskLevel = bsRiskLevel;
    }

    public int getBsAuditCycle() {
        return bsAuditCycle;
    }

    public void setBsAuditCycle(int bsAuditCycle) {
        this.bsAuditCycle = bsAuditCycle;
    }

    public String getBsVerificationLevel() {
        return bsVerificationLevel;
    }

    public void setBsVerificationLevel(String bsVerificationLevel) {
        this.bsVerificationLevel = bsVerificationLevel;
    }
}
