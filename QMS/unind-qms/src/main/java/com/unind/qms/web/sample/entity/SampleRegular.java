package com.unind.qms.web.sample.entity;

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
@Table(name = SampleRegular.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class SampleRegular extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "t_sample_regular";

    /**
     * 产品ID
     */
    @ApiModelProperty(name="bsPrId",required=true,value="产品ID")
    @NotNull
    @Column
    protected Long bsPrId;

    /**
     * 规格名称
     */
    @ApiModelProperty(name="bsName",required=true,value="规格名称")
    @NotNull
    @Column(length = 50)
    protected String bsName;

    /**
     * 规格上限
     */
    @ApiModelProperty(name="bsUpLimit",value="规格上限")
    @Column(length = 50)
    protected String bsUpLimit;

    /**
     * 规格下限
     */
    @ApiModelProperty(name="bsLowLimit",value="规格下限")
    @Column(length = 50)
    protected String bsLowLimit;

    /**
     * 测量工具
     */
    @ApiModelProperty(name="bsTool",value="测量工具")
    @Column(length = 50)
    protected String bsTool;

    /**
     * 单位
     */
    @ApiModelProperty(name="bsUnit",value="单位")
    @Column(length = 10)
    protected String bsUnit;

    /**
     * 备注
     */
    @ApiModelProperty(name="bsRemark",value="备注")
    @Column(length = 500)
    protected String bsRemark;

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

    public Long getBsPrId() {
        return bsPrId;
    }

    public void setBsPrId(Long bsPrId) {
        this.bsPrId = bsPrId;
    }

    public String getBsName() {
        return bsName;
    }

    public void setBsName(String bsName) {
        this.bsName = bsName;
    }

    public String getBsUpLimit() {
        return bsUpLimit;
    }

    public void setBsUpLimit(String bsUpLimit) {
        this.bsUpLimit = bsUpLimit;
    }

    public String getBsLowLimit() {
        return bsLowLimit;
    }

    public void setBsLowLimit(String bsLowLimit) {
        this.bsLowLimit = bsLowLimit;
    }

    public String getBsTool() {
        return bsTool;
    }

    public void setBsTool(String bsTool) {
        this.bsTool = bsTool;
    }

    public String getBsUnit() {
        return bsUnit;
    }

    public void setBsUnit(String bsUnit) {
        this.bsUnit = bsUnit;
    }

    public String getBsRemark() {
        return bsRemark;
    }

    public void setBsRemark(String bsRemark) {
        this.bsRemark = bsRemark;
    }

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
}
