package com.unind.qms.web.approved.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = ApprovedItemsResult.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class ApprovedItemsResult extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "t_approved_items_result";

    /**
     * 审核项目记录ID
     */
    @ApiModelProperty(name="bsItemsRecordId",required=true,value="审核项目记录ID")
    @NotNull
    @Column
    protected Long bsItemsRecordId;

    @ApiModelProperty(name="approvedItemsRecord",hidden=true,value="项目记录")
    @ManyToOne
    @JoinColumn(name = "bsItemsRecordId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected ApprovedItemsRecord approvedItemsRecord;

    /**
     * 记录人ID
     */
    @ApiModelProperty(name="bsRecorderId",required=true,value="记录人ID")
    @NotNull
    @Column
    protected Long bsRecorderId;

    @ApiModelProperty(name="resultBy",hidden=true,value="结果创建人--user")
    @ManyToOne
    @JoinColumn(name = "bsRecorderId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected SysUser resultBy;

    /**
     * 审核说明
     */
    @ApiModelProperty(name="bsDesc",value="审核说明")
    @Column(length = 450)
    protected String bsDesc;

    /**
     * 审核结果
     */
    @ApiModelProperty(name="bsResult",value="审核结果")
    @Column
    protected int bsResult;

    public Long getBsItemsRecordId() {
        return bsItemsRecordId;
    }

    public void setBsItemsRecordId(Long bsItemsRecordId) {
        this.bsItemsRecordId = bsItemsRecordId;
    }

    public ApprovedItemsRecord getApprovedItemsRecord() {
        return approvedItemsRecord;
    }

    @JsonBackReference
    public void setApprovedItemsRecord(ApprovedItemsRecord approvedItemsRecord) {
        this.approvedItemsRecord = approvedItemsRecord;
    }

    public Long getBsRecorderId() {
        return bsRecorderId;
    }

    public void setBsRecorderId(Long bsRecorderId) {
        this.bsRecorderId = bsRecorderId;
    }

    public SysUser getResultBy() {
        return resultBy;
    }

    public void setResultBy(SysUser resultBy) {
        this.resultBy = resultBy;
    }

    public String getBsDesc() {
        return bsDesc;
    }

    public void setBsDesc(String bsDesc) {
        this.bsDesc = bsDesc;
    }

    public int getBsResult() {
        return bsResult;
    }

    public void setBsResult(int bsResult) {
        this.bsResult = bsResult;
    }
}
