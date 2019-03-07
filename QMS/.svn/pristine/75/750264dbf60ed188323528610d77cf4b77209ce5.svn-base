package com.unind.qms.web.approved.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.unind.base.domain.admin.BaseEntity;
import com.unind.base.domain.admin.user.SysUser;
import com.unind.fs.domain.file.FsFile;
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
@Table(name = ApprovedItemsRecordFile.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class ApprovedItemsRecordFile extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "t_approved_items_record_file";

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
     * 文件信息ID
     */
    @ApiModelProperty(name="fsFileId",required=true,value="文件信息ID")
    @NotNull
    @Column
    protected Long fsFileId;

    @ApiModelProperty(name="fsFileBy",hidden=true,value="文件记录")
    @OneToOne
    @JoinColumn(name = "fsFileId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected FsFile fsFileBy;

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

    public Long getFsFileId() {
        return fsFileId;
    }

    public void setFsFileId(Long fsFileId) {
        this.fsFileId = fsFileId;
    }

    public FsFile getFsFileBy() {
        return fsFileBy;
    }

    public void setFsFileBy(FsFile fsFileBy) {
        this.fsFileBy = fsFileBy;
    }
}
