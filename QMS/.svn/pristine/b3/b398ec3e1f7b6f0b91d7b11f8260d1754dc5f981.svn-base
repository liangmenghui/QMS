package com.unind.qms.web.basic.entity;

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
 * @author Shen
 */
@Entity
@Table(name = FeedbackRefund.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class FeedbackRefund extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "t_feedback_refund";

    /**
     * 客诉ID
     */
    @ApiModelProperty(name = "bsFeedbackId", value = "客诉ID")
    @Column
    protected Long bsFeedbackId;

    /**
     * 产品ID
     */
    @ApiModelProperty(name = "bsPrId", required = true, value = "产品ID")
    @NotNull
    @Column
    protected Long bsPrId;

    /**
     * 客户报告号
     */
    @ApiModelProperty(name = "bsReportNo", value = "客户报告号")
    @Column(length = 50)
    protected String bsReportNo;

    /**
     * 数量
     */
    @ApiModelProperty(name = "bsNum", value = "数量")
    @Column(length = 50)
    protected String bsNum;

    /**
     * 单价
     */
    @ApiModelProperty(name = "bsPrice", value = "单价")
    @Column(length = 50)
    protected String bsPrice;

    /**
     * 扣罚金额
     */
    @ApiModelProperty(name = "bsSum", value = "扣罚金额")
    @Column(length = 50)
    protected String bsSum;

    /**
     * 扣款原因
     */
    @ApiModelProperty(name = "bsReason", value = "扣款原因")
    @Column(length = 500)
    protected String bsReason;

    public Long getBsFeedbackId() {
        return bsFeedbackId;
    }

    public void setBsFeedbackId(Long bsFeedbackId) {
        this.bsFeedbackId = bsFeedbackId;
    }

    public Long getBsPrId() {
        return bsPrId;
    }

    public void setBsPrId(Long bsPrId) {
        this.bsPrId = bsPrId;
    }

    public String getBsReportNo() {
        return bsReportNo;
    }

    public void setBsReportNo(String bsReportNo) {
        this.bsReportNo = bsReportNo;
    }

    public String getBsNum() {
        return bsNum;
    }

    public void setBsNum(String bsNum) {
        this.bsNum = bsNum;
    }

    public String getBsPrice() {
        return bsPrice;
    }

    public void setBsPrice(String bsPrice) {
        this.bsPrice = bsPrice;
    }

    public String getBsSum() {
        return bsSum;
    }

    public void setBsSum(String bsSum) {
        this.bsSum = bsSum;
    }

    public String getBsReason() {
        return bsReason;
    }

    public void setBsReason(String bsReason) {
        this.bsReason = bsReason;
    }
}
