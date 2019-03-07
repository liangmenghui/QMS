package com.unind.qms.web.supplier.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unind.base.domain.admin.BaseEntity;
import com.unind.base.domain.admin.user.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Set;

/**
 * 客户审核记录
 * @author Shen
 */
@Entity
@Table(name = CustomerApprovedRecord.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class CustomerApprovedRecord extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "t_customer_approved_record";

    /**
     * 供应商ID
     */
    @ApiModelProperty(name = "bsSuppId", value = "供应商ID")
    @Column
    protected Long bsSuppId;

    /**
     * 序号
     */
    @ApiModelProperty(name = "bsNo", value = "序号")
    @Column(length = 50)
    protected String bsNo;

    /**
     * 审核结果（1.通过 2.未通过 ）
     */
    @ApiModelProperty(name = "bsResult", value = "审核结果（1.通过 2.未通过）")
    @Column
    protected int bsResult;

    /**
     * 审核说明
     */
    @ApiModelProperty(name = "bsDesc", value = "审核说明")
    @Column(length = 500)
    protected String bsDesc;

    /**
     * 客户名称
     */
    @ApiModelProperty(name = "bsCusName", value = "客户名称")
    @Column(length = 50)
    protected String bsCusName;

    /**
     * 负责人
     */
    @ApiModelProperty(name = "bsResponsible", value = "负责人")
    @Column(length = 50)
    protected String bsResponsible;

    //文件集合
    @OneToMany(mappedBy = "customerApproved")
    protected Set<CustomerApprovedFile> fileSet;

    /**
     * 创建人
     */
    @ApiModelProperty(name = "pkCreatedBy", hidden = true ,value = "创建人")
    @Column
    protected Long pkCreatedBy;

    /**
     * 修改人
     */
    @ApiModelProperty(name = "pkModifiedBy", hidden = true, value = "修改人")
    @Column
    protected Long pkModifiedBy;

    @ApiModelProperty(name = "createdBy", hidden = true, value = "创建人--user")
    @ManyToOne
    @JoinColumn(name = "pkCreatedBy", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected SysUser createdBy;

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

    public String getBsNo() {
        return bsNo;
    }

    public void setBsNo(String bsNo) {
        this.bsNo = bsNo;
    }

    public int getBsResult() {
        return bsResult;
    }

    public void setBsResult(int bsResult) {
        this.bsResult = bsResult;
    }

    public String getBsDesc() {
        return bsDesc;
    }

    public void setBsDesc(String bsDesc) {
        this.bsDesc = bsDesc;
    }

    public String getBsCusName() {
        return bsCusName;
    }

    public void setBsCusName(String bsCusName) {
        this.bsCusName = bsCusName;
    }

    public String getBsResponsible() {
        return bsResponsible;
    }

    public void setBsResponsible(String bsResponsible) {
        this.bsResponsible = bsResponsible;
    }

    public Set<CustomerApprovedFile> getFileSet() {
        return fileSet;
    }

    public void setFileSet(Set<CustomerApprovedFile> fileSet) {
        this.fileSet = fileSet;
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
