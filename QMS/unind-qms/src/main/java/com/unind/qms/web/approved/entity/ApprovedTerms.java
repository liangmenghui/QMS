package com.unind.qms.web.approved.entity;

import com.unind.base.domain.admin.BaseEntity;
import com.unind.base.domain.admin.group.SysGroup;
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
@Table(name = ApprovedTerms.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class ApprovedTerms extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "t_approved_terms";

    /**
     * 条款号
     */
    @ApiModelProperty(name="bsNo",required=true,value="条款号")
    @NotNull
    @Column(length = 50)
    protected String bsNo;
	
	/**
     * 条款名称
     */
    @ApiModelProperty(name="bsName",required=true,value="条款名称")
    @NotNull
    @Column(length = 200)
    protected String bsName;

    /**
     * 条款类型
     */
    @ApiModelProperty(name="bsType",required=true,value="条款类型")
    @NotNull
    @Column
    protected int bsType;

    /**
     * 条款内容
     */
    @ApiModelProperty(name="bsContent",value="条款内容")
    @Column(length = 2000)
    protected String bsContent;

    /**
     * 评分标准
     */
    @ApiModelProperty(name="bsStandard",value="评分标准")
    @Column(length = 2000)
    protected String bsStandard;

    /**
     * 条款备注
     */
    @ApiModelProperty(name="bsRemark",value="条款备注")
    @Column(length = 450)
    protected String bsRemark;

    /**
     * 条款内容（英文）
     */
    @ApiModelProperty(name="bsContentEn",value="条款内容（英文）")
    @Column(length = 2000)
    protected String bsContentEn;

    /**
     * 评分标准（英文）
     */
    @ApiModelProperty(name="bsStandardEn",value="评分标准（英文）")
    @Column(length = 2000)
    protected String bsStandardEn;

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

    public String getBsNo() {
        return bsNo;
    }

    public void setBsNo(String bsNo) {
        this.bsNo = bsNo;
    }

    public String getBsName() {
        return bsName;
    }

    public void setBsName(String bsName) {
        this.bsName = bsName;
    }

    public int getBsType() {
        return bsType;
    }

    public void setBsType(int bsType) {
        this.bsType = bsType;
    }

    public String getBsContent() {
        return bsContent;
    }

    public void setBsContent(String bsContent) {
        this.bsContent = bsContent;
    }

    public String getBsStandard() {
        return bsStandard;
    }

    public void setBsStandard(String bsStandard) {
        this.bsStandard = bsStandard;
    }

    public String getBsRemark() {
        return bsRemark;
    }

    public void setBsRemark(String bsRemark) {
        this.bsRemark = bsRemark;
    }

    public String getBsContentEn() {
        return bsContentEn;
    }

    public void setBsContentEn(String bsContentEn) {
        this.bsContentEn = bsContentEn;
    }

    public String getBsStandardEn() {
        return bsStandardEn;
    }

    public void setBsStandardEn(String bsStandardEn) {
        this.bsStandardEn = bsStandardEn;
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
