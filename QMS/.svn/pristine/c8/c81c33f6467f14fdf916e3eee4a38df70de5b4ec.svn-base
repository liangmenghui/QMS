package com.unind.qms.web.basic.entity;

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
@Table(name = FeedbackInfoFile.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class FeedbackInfoFile extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "t_feedback_info_file";

    /**
     * 客诉ID
     */
    @ApiModelProperty(name="bsFeedbackId",required=true,value="客诉ID")
    @NotNull
    @Column
    protected Long bsFeedbackId;

    @ApiModelProperty(name="feedbackInfo",hidden=true,value="客诉信息")
    @ManyToOne
    @JoinColumn(name = "bsFeedbackId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected FeedbackInfo feedbackInfo;

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

    public Long getBsFeedbackId() {
        return bsFeedbackId;
    }

    public void setBsFeedbackId(Long bsFeedbackId) {
        this.bsFeedbackId = bsFeedbackId;
    }

    public FeedbackInfo getFeedbackInfo() {
        return feedbackInfo;
    }

    @JsonBackReference
    public void setFeedbackInfo(FeedbackInfo feedbackInfo) {
        this.feedbackInfo = feedbackInfo;
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
