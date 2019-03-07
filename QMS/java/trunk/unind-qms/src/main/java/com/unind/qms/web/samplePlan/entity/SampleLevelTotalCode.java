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
 * 检验水平——批量——样本代字关系
 */
@Entity
@Table(name = SampleLevelTotalCode.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class SampleLevelTotalCode extends BaseEntity {
    private static final long serialVersionUID = -1120453838604001881L;
    public static final String TABLE_NAME = "t_sample_level_total_code";

    /**
     * 批量下限
     */
    @ApiModelProperty(name = "bsLowerLimit", value = "批量下限")
    @Column
    protected int bsLowerLimit;

    /**
     * 批量上限
     */
    @ApiModelProperty(name = "bsUpperLimit", value = "批量上限")
    @Column
    protected int bsUpperLimit;

    /**
     * 检验水平-I（1：A， 2：B， 3：C， 4：D， 5：E）
     */
    @ApiModelProperty(name = "bsLevel1",  value = "检验水平-I（1：A， 2：B， 3：C， 4：D， 5：E）")
    @Column
    protected int bsLevel1;

    /**
     * 检验水平-II（1：A， 2：B， 3：C， 4：D， 5：E）
     */
    @ApiModelProperty(name = "bsLevel2",  value = "检验水平-II（1：A， 2：B， 3：C， 4：D， 5：E）")
    @Column
    protected int bsLevel2;

    /**
     * 检验水平-III（1：A， 2：B， 3：C， 4：D， 5：E）
     */
    @ApiModelProperty(name = "bsLevel3",  value = "检验水平-III（1：A， 2：B， 3：C， 4：D， 5：E）")
    @Column
    protected int bsLevel3;

    /**
     * 检验水平-IV（1：A， 2：B， 3：C， 4：D， 5：E）
     */
    @ApiModelProperty(name = "bsLevel4",  value = "检验水平-IV（1：A， 2：B， 3：C， 4：D， 5：E）")
    @Column
    protected int bsLevel4;

    /**
     * 检验水平-V（1：A， 2：B， 3：C， 4：D， 5：E）
     */
    @ApiModelProperty(name = "bsLevel5",  value = "检验水平-V（1：A， 2：B， 3：C， 4：D， 5：E）")
    @Column
    protected int bsLevel5;

    /**
     * 检验水平-VI（1：A， 2：B， 3：C， 4：D， 5：E）
     */
    @ApiModelProperty(name = "bsLevel6",  value = "检验水平-VI（1：A， 2：B， 3：C， 4：D， 5：E）")
    @Column
    protected int bsLevel6;

    /**
     * 检验水平-VII（1：A， 2：B， 3：C， 4：D， 5：E）
     */
    @ApiModelProperty(name = "bsLevel7",  value = "检验水平-VII（1：A， 2：B， 3：C， 4：D， 5：E）")
    @Column
    protected int bsLevel7;

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

    public int getBsLowerLimit() {
        return bsLowerLimit;
    }

    public void setBsLowerLimit(int bsLowerLimit) {
        this.bsLowerLimit = bsLowerLimit;
    }

    public int getBsUpperLimit() {
        return bsUpperLimit;
    }

    public void setBsUpperLimit(int bsUpperLimit) {
        this.bsUpperLimit = bsUpperLimit;
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
