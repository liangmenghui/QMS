package com.unind.qms.web.supplier.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unind.base.domain.admin.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Shen
 */
@Entity
@Table(name = Promote.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class Promote extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "t_promote";

    /**
     * 审核流程记录ID
     */
    @ApiModelProperty(name = "bsFlowRecordId", required = true, value = "审核流程记录ID")
//    @NotNull
    @Column
    protected Long bsFlowRecordId;

    /**
     * 审核项目记录ID
     */
    @ApiModelProperty(name = "bsItemsRecordId", required = true, value = "审核项目记录ID")
    @NotNull
    @Column
    protected Long bsItemsRecordId;

//    @ApiModelProperty(name = "approvedItemsRecord", hidden = true, value = "审核项目记录")
//    @ManyToOne
//    @JoinColumn(name = "bsItemsRecordId", insertable = false, updatable = false)
//    @NotFound(action = NotFoundAction.IGNORE)
//    protected ApprovedItemsRecord approvedItemsRecord;

    /**
     * 负责人
     */
    @ApiModelProperty(name = "bsResponsible", value = "负责人")
    @Column(length = 50)
    protected String bsResponsible;

    /**
     * 不符合项描述
     */
    @ApiModelProperty(name = "bsContent", value = "不符合项描述")
    @Column(length = 2000)
    protected String bsContent;

    /**
     * 改进措施
     */
    @ApiModelProperty(name = "bsAction", value = "改进措施")
    @Column(length = 500)
    protected String bsAction;

    /**
     * 完成日期
     */
    @ApiModelProperty(name = "bsDeadline", value = "完成日期")
    @Column
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    protected Date bsDeadline;

    /**
     * 条款ID
     */
    @ApiModelProperty(name = "bsTermsId", value = "条款ID")
    @Column
    protected Long bsTermsId;

    /**
     * 条款编号
     */
    @ApiModelProperty(name = "bsTermsNo", value = "条款编号")
    @Column(length = 50)
    protected String bsTermsNo;

    public Long getBsItemsRecordId() {
        return bsItemsRecordId;
    }

    public void setBsItemsRecordId(Long bsItemsRecordId) {
        this.bsItemsRecordId = bsItemsRecordId;
    }

    public Long getBsFlowRecordId() {
        return bsFlowRecordId;
    }

    public void setBsFlowRecordId(Long bsFlowRecordId) {
        this.bsFlowRecordId = bsFlowRecordId;
    }

    public String getBsResponsible() {
        return bsResponsible;
    }

    public void setBsResponsible(String bsResponsible) {
        this.bsResponsible = bsResponsible;
    }

    public String getBsContent() {
        return bsContent;
    }

    public void setBsContent(String bsContent) {
        this.bsContent = bsContent;
    }

    public String getBsAction() {
        return bsAction;
    }

    public void setBsAction(String bsAction) {
        this.bsAction = bsAction;
    }

    public Date getBsDeadline() {
        return bsDeadline;
    }

    public void setBsDeadline(Date bsDeadline) {
        this.bsDeadline = bsDeadline;
    }

    public Long getBsTermsId() {
        return bsTermsId;
    }

    public void setBsTermsId(Long bsTermsId) {
        this.bsTermsId = bsTermsId;
    }

    public String getBsTermsNo() {
        return bsTermsNo;
    }

    public void setBsTermsNo(String bsTermsNo) {
        this.bsTermsNo = bsTermsNo;
    }
}