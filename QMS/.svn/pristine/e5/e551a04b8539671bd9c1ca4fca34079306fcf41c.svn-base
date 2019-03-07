package com.unind.qms.web.approved.entity;

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
@Table(name = ApprovedFlow.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class ApprovedFlow extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "t_approved_flow";

    /**
     * 流程名称
     */
    @ApiModelProperty(name="bsName",required=true,value="流程名称")
    @NotNull
    @Column(length = 45)
    protected String bsName;

    /**
     * 类型
     */
    @ApiModelProperty(name="bsType",required=true,value="类型")
    @NotNull
    @Column
    protected int bsType;

    /**
     * 最终审批人ID
     */
    @ApiModelProperty(name="bsApprovederId",required=true,value="最终审批人ID")
    @NotNull
    @Column
    protected Long bsApprovederId;

    @ApiModelProperty(name="approvedBy",hidden=true,value="最终审批人--user")
    @ManyToOne
    @JoinColumn(name = "bsApprovederId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected SysUser approvedBy;

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

    public Long getBsApprovederId() {
        return bsApprovederId;
    }

    public void setBsApprovederId(Long bsApprovederId) {
        this.bsApprovederId = bsApprovederId;
    }

    public SysUser getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(SysUser approvedBy) {
        this.approvedBy = approvedBy;
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
