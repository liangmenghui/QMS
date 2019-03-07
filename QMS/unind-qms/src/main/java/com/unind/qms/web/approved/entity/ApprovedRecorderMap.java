package com.unind.qms.web.approved.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.unind.base.domain.admin.BaseEntity;
import com.unind.base.domain.admin.IdEntity;
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
@Table(name = ApprovedRecorderMap.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class ApprovedRecorderMap extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "t_approved_recorder_map";

    /**
     * 审核项目ID
     */
    @ApiModelProperty(name="bsItemsId",required=true,value="审核项目ID")
    @NotNull
    @Column
    protected Long bsItemsId;

    /**
     * 记录人ID
     */
    @ApiModelProperty(name="bsRecorderId",required=true,value="记录人ID")
    @NotNull
    @Column
    protected Long bsRecorderId;

    /**
     * 优先级
     */
    @ApiModelProperty(name="bsPriority",required=true,value="优先级")
    @NotNull
    @Column
    protected int bsPriority;

    @ApiModelProperty(name="approvedItems",hidden=true,value="项目")
    @ManyToOne
    @JoinColumn(name = "bsItemsId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected ApprovedItems approvedItems;

    @ApiModelProperty(name="recorderBy",hidden=true,value="记录人")
    @ManyToOne
    @JoinColumn(name = "bsRecorderId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected SysUser recorderBy;

    public Long getBsItemsId() {
        return bsItemsId;
    }

    public void setBsItemsId(Long bsItemsId) {
        this.bsItemsId = bsItemsId;
    }

    public Long getBsRecorderId() {
        return bsRecorderId;
    }

    public void setBsRecorderId(Long bsRecorderId) {
        this.bsRecorderId = bsRecorderId;
    }

    public int getBsPriority() {
        return bsPriority;
    }

    public void setBsPriority(int bsPriority) {
        this.bsPriority = bsPriority;
    }

    public ApprovedItems getApprovedItems() {
        return approvedItems;
    }

    @JsonBackReference
    public void setApprovedItems(ApprovedItems approvedItems) {
        this.approvedItems = approvedItems;
    }

    public SysUser getRecorderBy() {
        return recorderBy;
    }

    public void setRecorderBy(SysUser recorderBy) {
        this.recorderBy = recorderBy;
    }
}
