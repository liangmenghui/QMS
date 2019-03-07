package com.unind.qms.web.approved.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.unind.base.domain.admin.BaseEntity;
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
@Table(name = ApprovedTermsScoreFile.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class ApprovedTermsScoreFile extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "t_approved_terms_score_file";

    /**
     * 条款得分记录ID
     */
    @ApiModelProperty(name="bsTermsScoreId",required=true,value="条款得分记录ID")
    @NotNull
    @Column
    protected Long bsTermsScoreId;

    @ApiModelProperty(name="approvedTermsScore",hidden=true,value="条款得分")
    @ManyToOne
    @JoinColumn(name = "bsTermsScoreId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected ApprovedTermsScore approvedTermsScore;

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

    public Long getBsTermsScoreId() {
        return bsTermsScoreId;
    }

    public void setBsTermsScoreId(Long bsTermsScoreId) {
        this.bsTermsScoreId = bsTermsScoreId;
    }

    public ApprovedTermsScore getApprovedTermsScore() {
        return approvedTermsScore;
    }

    @JsonBackReference
    public void setApprovedTermsScore(ApprovedTermsScore approvedTermsScore) {
        this.approvedTermsScore = approvedTermsScore;
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
