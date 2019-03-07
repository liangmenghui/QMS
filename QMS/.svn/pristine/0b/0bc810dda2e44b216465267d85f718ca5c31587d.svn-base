package com.unind.qms.web.risk.entity;

import com.unind.base.domain.admin.BaseEntity;
import com.unind.fs.domain.file.FsFile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

/**
 * 风险证据描述
 * @author Shen
 */
@Entity
@Table(name = RiskEvidence.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class RiskEvidence extends BaseEntity {

    private static final long serialVersionUID = 1154151142960277043L;
    public static final String TABLE_NAME = "t_risk_evidence";

    /**
     * 产品ID
     */
    @ApiModelProperty(name = "bsPrId", value = "产品ID")
    @Column
    protected Long bsPrId;

    /**
     * 供应商ID
     */
    @ApiModelProperty(name = "bsSuppId", value = "供应商ID")
    @Column
    protected Long bsSuppId;

    /**
     * 风险序号
     */
    @ApiModelProperty(name = "bsRiskNo", value = "风险序号")
    @Column
    protected int bsRiskNo;

    /**
     * 备注
     */
    @ApiModelProperty(name = "bsRemark", value = "备注")
    @Column(length = 500)
    protected String bsRemark;

    /**
     * 文件信息ID
     */
    @ApiModelProperty(name = "fsFileId", value = "文件信息ID")
    @Column
    protected Long fsFileId;

    @ApiModelProperty(name = "fsFileBy", hidden = true, value = "文件记录")
    @OneToOne
    @JoinColumn(name = "fsFileId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected FsFile fsFileBy;

    public Long getBsPrId() {
        return bsPrId;
    }

    public void setBsPrId(Long bsPrId) {
        this.bsPrId = bsPrId;
    }

    public Long getBsSuppId() {
        return bsSuppId;
    }

    public void setBsSuppId(Long bsSuppId) {
        this.bsSuppId = bsSuppId;
    }

    public int getBsRiskNo() {
        return bsRiskNo;
    }

    public void setBsRiskNo(int bsRiskNo) {
        this.bsRiskNo = bsRiskNo;
    }

    public String getBsRemark() {
        return bsRemark;
    }

    public void setBsRemark(String bsRemark) {
        this.bsRemark = bsRemark;
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
