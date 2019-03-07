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
 * 检验水平——样本代字——抽样数关系
 */
@Entity
@Table(name = SampleLevelCodeNum.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class SampleLevelCodeNum extends BaseEntity {
    private static final long serialVersionUID = -1120453838604001881L;
    public static final String TABLE_NAME = "t_sample_level_code_num";

    /**
     * 样本代字
     */
    @ApiModelProperty(name = "bsCode", value = "样品代字")
    @Column(length = 25)
    protected String bsCode;

    /**
     * 样本代字序号
     */
    @ApiModelProperty(name = "bsCodeNo", value = "样本代字序号")
    @Column
    protected int bsCodeNo;

    /**
     * 检验水平-R（抽样数）
     */
    @ApiModelProperty(name = "bsLevel0",  value = "检验水平-R（抽样数）")
    @Column
    protected int bsLevel0;

    /**
     * 检验水平-I（抽样数）
     */
    @ApiModelProperty(name = "bsLevel1",  value = "检验水平-I（抽样数）")
    @Column
    protected int bsLevel1;

    /**
     * 检验水平-II（抽样数）
     */
    @ApiModelProperty(name = "bsLevel2",  value = "检验水平-II（抽样数）")
    @Column
    protected int bsLevel2;

    /**
     * 检验水平-III（抽样数）
     */
    @ApiModelProperty(name = "bsLevel3",  value = "检验水平-III（抽样数）")
    @Column
    protected int bsLevel3;

    /**
     * 检验水平-IV（抽样数）
     */
    @ApiModelProperty(name = "bsLevel4",  value = "检验水平-IV（抽样数）")
    @Column
    protected int bsLevel4;

    /**
     * 检验水平-V（抽样数）
     */
    @ApiModelProperty(name = "bsLevel5",  value = "检验水平-V（抽样数）")
    @Column
    protected int bsLevel5;

    /**
     * 检验水平-VI（抽样数）
     */
    @ApiModelProperty(name = "bsLevel6",  value = "检验水平-VI（抽样数）")
    @Column
    protected int bsLevel6;

    /**
     * 检验水平-VII（抽样数）
     */
    @ApiModelProperty(name = "bsLevel7",  value = "检验水平-VII（抽样数）")
    @Column
    protected int bsLevel7;

    /**
     * 检验水平-T（抽样数）
     */
    @ApiModelProperty(name = "bsLevel8",  value = "检验水平-T（抽样数）")
    @Column
    protected int bsLevel8;

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

    public String getBsCode() {
        return bsCode;
    }

    public void setBsCode(String bsCode) {
        this.bsCode = bsCode;
    }

    public int getBsCodeNo() {
        return bsCodeNo;
    }

    public void setBsCodeNo(int bsCodeNo) {
        this.bsCodeNo = bsCodeNo;
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
