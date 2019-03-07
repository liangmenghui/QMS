package com.unind.qms.web.samplePlan.entity;

import com.unind.base.domain.admin.BaseEntity;
import com.unind.base.domain.admin.user.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

/**
 * 产品风险等级——检验水平关系
 */
@Entity
@Table(name = SampleLevelRisk.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class SampleLevelRisk extends BaseEntity {
    private static final long serialVersionUID = -1120453838604001881L;
    public static final String TABLE_NAME = "t_sample_level_risk";

    /**
     * 产品风险等级
     */
    @ApiModelProperty(name = "bsRiskLevel", value = "产品风险等级")
    @Column
    protected int bsRiskLevel;

    /**
     * 检验水平-R（0：false， 1：true）
     */
    @ApiModelProperty(name = "bsLevel0",  value = "检验水平-R（0：false， 1：true）")
    @Column
    protected int bsLevel0;

    /**
     * 检验水平-I（0：false， 1：true）
     */
    @ApiModelProperty(name = "bsLevel1",  value = "检验水平-I（0：false， 1：true）")
    @Column
    protected int bsLevel1;

    /**
     * 检验水平-II（0：false， 1：true）
     */
    @ApiModelProperty(name = "bsLevel2",  value = "检验水平-II（0：false， 1：true）")
    @Column
    protected int bsLevel2;

    /**
     * 检验水平-III（0：false， 1：true）
     */
    @ApiModelProperty(name = "bsLevel3",  value = "检验水平-III（0：false， 1：true）")
    @Column
    protected int bsLevel3;

    /**
     * 检验水平-IV（0：false， 1：true）
     */
    @ApiModelProperty(name = "bsLevel4",  value = "检验水平-IV（0：false， 1：true）")
    @Column
    protected int bsLevel4;

    /**
     * 检验水平-V（0：false， 1：true）
     */
    @ApiModelProperty(name = "bsLevel5",  value = "检验水平-V（0：false， 1：true）")
    @Column
    protected int bsLevel5;

    /**
     * 检验水平-VI（0：false， 1：true）
     */
    @ApiModelProperty(name = "bsLevel6",  value = "检验水平-VI（0：false， 1：true）")
    @Column
    protected int bsLevel6;

    /**
     * 检验水平-VII（0：false， 1：true）
     */
    @ApiModelProperty(name = "bsLevel7",  value = "检验水平-VII（0：false， 1：true）")
    @Column
    protected int bsLevel7;

    /**
     * 检验水平-T（0：false， 1：true）
     */
    @ApiModelProperty(name = "bsLevel8",  value = "检验水平-T（0：false， 1：true）")
    @Column
    protected int bsLevel8;

    /**
     * 检验水平标准名称
     */
    @ApiModelProperty(name = "bsStandardName", value = "检验水平标准名称")
    @Column(length = 100)
    protected String bsStandardName;

    /**
     * 分数线（Ac=0，即0收1退）
     */
    @ApiModelProperty(name = "bsScore", value = "分数线（Ac=0，即0收1退）")
    @Column
    protected int bsScore;

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

    public int getBsRiskLevel() {
        return bsRiskLevel;
    }

    public void setBsRiskLevel(int bsRiskLevel) {
        this.bsRiskLevel = bsRiskLevel;
    }

    public int getBsLevel0() {
        return bsLevel0;
    }

    public void setBsLevel0(int bsLevel0) {
        this.bsLevel0 = bsLevel0;
    }

    public int getBsLevel1() {
        return bsLevel1;
    }

    public void setBsLevel1(int bsLevel1) {
        this.bsLevel1 = bsLevel1;
    }

    public int getBsLevel2() {
        return bsLevel2;
    }

    public void setBsLevel2(int bsLevel2) {
        this.bsLevel2 = bsLevel2;
    }

    public int getBsLevel3() {
        return bsLevel3;
    }

    public void setBsLevel3(int bsLevel3) {
        this.bsLevel3 = bsLevel3;
    }

    public int getBsLevel4() {
        return bsLevel4;
    }

    public void setBsLevel4(int bsLevel4) {
        this.bsLevel4 = bsLevel4;
    }

    public int getBsLevel5() {
        return bsLevel5;
    }

    public void setBsLevel5(int bsLevel5) {
        this.bsLevel5 = bsLevel5;
    }

    public int getBsLevel6() {
        return bsLevel6;
    }

    public void setBsLevel6(int bsLevel6) {
        this.bsLevel6 = bsLevel6;
    }

    public int getBsLevel7() {
        return bsLevel7;
    }

    public void setBsLevel7(int bsLevel7) {
        this.bsLevel7 = bsLevel7;
    }

    public int getBsLevel8() {
        return bsLevel8;
    }

    public void setBsLevel8(int bsLevel8) {
        this.bsLevel8 = bsLevel8;
    }

    public String getBsStandardName() {
        return bsStandardName;
    }

    public void setBsStandardName(String bsStandardName) {
        this.bsStandardName = bsStandardName;
    }

    public int getBsScore() {
        return bsScore;
    }

    public void setBsScore(int bsScore) {
        this.bsScore = bsScore;
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
