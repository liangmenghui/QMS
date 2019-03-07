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
import java.util.Set;

/**
 * @author chen
 */
@Entity
@Table(name = ApprovedItemsRecord.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class ApprovedItemsRecord extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "t_approved_items_record";

    /**
     * 审核状态      
     */
    @ApiModelProperty(name="bsStatus",value="审核状态")
    @NotNull
    @Column
    protected int bsStatus;

    /**
     * 审核流程记录ID
     */
    @ApiModelProperty(name="bsFlowRecordId",required=true,value="审核流程记录ID")
    @NotNull
    @Column
    protected Long bsFlowRecordId;

    /**
     * 审核项目ID
     */
    @ApiModelProperty(name="bsItemsId",required=true,value="审核项目ID")
    @NotNull
    @Column
    protected Long bsItemsId;

    @ApiModelProperty(name="itemsBy",hidden=true,value="项目")
    @ManyToOne
    @JoinColumn(name = "bsItemsId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected ApprovedItems itemsBy;

    /**
     * 审核优先级
     */
    @ApiModelProperty(name="bsPriority",required=true,value="审核优先级")
    @NotNull
    @Column
    protected int bsPriority;

    /**
     * 审核进度
     */
    @ApiModelProperty(name="bsStep",value="审核进度")
    @NotNull
    @Column
    protected int bsStep;

    /**
     * 产品ID
     */
    @ApiModelProperty(name="bsPrId",value="产品ID")
    @Column
    protected Long bsPrId;

    /**
     * 供应商ID
     */
    @ApiModelProperty(name="bsSuppId",value="供应商ID")
    @Column
    protected Long bsSuppId;

    /**
     * 审核说明
     */
    @ApiModelProperty(name="bsDesc",value="审核说明")
    @Column(length = 450)
    protected String bsDesc;

    /**
     * 条款得分
     */
    @ApiModelProperty(name="bsScoreNum",value="条款得分")
    @Column(length = 20)
    protected String bsScoreNum;

    /**
     * 审批结果集合
     */
    @OneToMany(mappedBy = "approvedItemsRecord")
    protected Set<ApprovedItemsResult> resultSet;

//    /**
//     * 文件集合
//     */
//    @OneToMany(mappedBy = "approvedItemsRecord")
//    protected Set<ApprovedItemsRecordFile> fileSet;

    //记录人集合
    @OneToMany(mappedBy = "approvedItemsRecord")
    protected Set<ApprovedRecorderMap> recorderSet;

    //条款得分集合
    @OneToMany(mappedBy = "approvedItemsRecord")
    protected Set<ApprovedTermsScore> termsScoreSet;

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

    public int getBsStatus() {
        return bsStatus;
    }

    public void setBsStatus(int bsStatus) {
        this.bsStatus = bsStatus;
    }

    public Long getBsFlowRecordId() {
        return bsFlowRecordId;
    }

    public void setBsFlowRecordId(Long bsFlowRecordId) {
        this.bsFlowRecordId = bsFlowRecordId;
    }

    public Long getBsItemsId() {
        return bsItemsId;
    }

    public void setBsItemsId(Long bsItemsId) {
        this.bsItemsId = bsItemsId;
    }

    public ApprovedItems getItemsBy() {
        return itemsBy;
    }

    public void setItemsBy(ApprovedItems itemsBy) {
        this.itemsBy = itemsBy;
    }

    public int getBsPriority() {
        return bsPriority;
    }

    public void setBsPriority(int bsPriority) {
        this.bsPriority = bsPriority;
    }

    public int getBsStep() {
        return bsStep;
    }

    public void setBsStep(int bsStep) {
        this.bsStep = bsStep;
    }

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

    public String getBsDesc() {
        return bsDesc;
    }

    public void setBsDesc(String bsDesc) {
        this.bsDesc = bsDesc;
    }

    public String getBsScoreNum() {
        return bsScoreNum;
    }

    public void setBsScoreNum(String bsScoreNum) {
        this.bsScoreNum = bsScoreNum;
    }

    public Set<ApprovedItemsResult> getResultSet() {
        return resultSet;
    }

    public void setResultSet(Set<ApprovedItemsResult> resultSet) {
        this.resultSet = resultSet;
    }

//    public Set<ApprovedItemsRecordFile> getFileSet() {
//        return fileSet;
//    }
//
//    public void setFileSet(Set<ApprovedItemsRecordFile> fileSet) {
//        this.fileSet = fileSet;
//    }

    public Set<ApprovedRecorderMap> getRecorderSet() {
        return recorderSet;
    }

    public void setRecorderSet(Set<ApprovedRecorderMap> recorderSet) {
        this.recorderSet = recorderSet;
    }

    public Set<ApprovedTermsScore> getTermsScoreSet() {
        return termsScoreSet;
    }

    public void setTermsScoreSet(Set<ApprovedTermsScore> termsScoreSet) {
        this.termsScoreSet = termsScoreSet;
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
