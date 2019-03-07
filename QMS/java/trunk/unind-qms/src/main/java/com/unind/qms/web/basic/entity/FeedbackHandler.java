package com.unind.qms.web.basic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unind.base.domain.admin.BaseEntity;
import com.unind.base.domain.admin.user.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author chen
 */
@Entity
@Table(name = FeedbackHandler.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class FeedbackHandler extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "t_feedback_handler";

    /**
     * 客诉ID
     */
    @ApiModelProperty(name="bsFeedbackId",required=true,value="客诉ID")
    @NotNull
    @Column
    protected Long bsFeedbackId;

    /**
     * 处理人员ID
     */
    @ApiModelProperty(name = "bsHandlerId", value = "处理人员ID")
    @Column
    protected Long bsHandlerId;

    @ApiModelProperty(name = "handlerBy", hidden = true, value = "客诉处理人--user")
    @ManyToOne
    @JoinColumn(name = "bsHandlerId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected SysUser handlerBy;

    /**
     * 类型
     */
    @ApiModelProperty(name="bsType", required = true, value="类型")
    @Column
    protected int bsType;

    /**
     * 根本原因分析动作类型（1: Prevent 2: Protect 3:Predict）
     */
    @ApiModelProperty(name = "bsActionType", value = "根本原因分析动作类型")
    @Column
    protected int bsActionType;

    /**
     * 状态
     */
    @ApiModelProperty(name="bsStatus",value="状态")
    @Column
    protected int bsStatus;

    /**
     * 描述信息
     */
    @ApiModelProperty(name="bsDesc",value="描述信息")
    @Column(length = 500)
    protected String bsDesc;

    /**
     * 处理人员名称
     */
    @ApiModelProperty(name = "bsHandlerName", value = "处理人员名称")
    @Column(length = 100)
    protected String bsHandlerName;

    /**
     * 处理人员电话
     */
    @ApiModelProperty(name = "bsHandlerMobile", value = "处理人员电话")
    @Column(length = 100)
    protected String bsHandlerMobile;

    /**
     * 处理人员邮箱
     */
    @ApiModelProperty(name = "bsHandlerEmail", value = "处理人员邮箱")
    @Column(length = 100)
    protected String bsHandlerEmail;

    /**
     * 相关文件
     */
    @ApiModelProperty(name = "bsFileId", value = "相关文件")
    @Column
    protected Long bsFileId;

    /**
     * 截止日期
     */
    @ApiModelProperty(name = "bsDeadline", value = "截止日期")
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    protected Date bsDeadline;

    public Long getBsFeedbackId() {
        return bsFeedbackId;
    }

    public void setBsFeedbackId(Long bsFeedbackId) {
        this.bsFeedbackId = bsFeedbackId;
    }

    public Long getBsHandlerId() {
        return bsHandlerId;
    }

    public void setBsHandlerId(Long bsHandlerId) {
        this.bsHandlerId = bsHandlerId;
    }

    public SysUser getHandlerBy() {
        return handlerBy;
    }

    public void setHandlerBy(SysUser handlerBy) {
        this.handlerBy = handlerBy;
    }

    public int getBsType() {
        return bsType;
    }

    public void setBsType(int bsType) {
        this.bsType = bsType;
    }

    public int getBsActionType() {
        return bsActionType;
    }

    public void setBsActionType(int bsActionType) {
        this.bsActionType = bsActionType;
    }

    public int getBsStatus() {
        return bsStatus;
    }

    public void setBsStatus(int bsStatus) {
        this.bsStatus = bsStatus;
    }

    public String getBsDesc() {
        return bsDesc;
    }

    public void setBsDesc(String bsDesc) {
        this.bsDesc = bsDesc;
    }

    public String getBsHandlerName() {
        return bsHandlerName;
    }

    public void setBsHandlerName(String bsHandlerName) {
        this.bsHandlerName = bsHandlerName;
    }

    public String getBsHandlerMobile() {
        return bsHandlerMobile;
    }

    public void setBsHandlerMobile(String bsHandlerMobile) {
        this.bsHandlerMobile = bsHandlerMobile;
    }

    public String getBsHandlerEmail() {
        return bsHandlerEmail;
    }

    public void setBsHandlerEmail(String bsHandlerEmail) {
        this.bsHandlerEmail = bsHandlerEmail;
    }

    public Long getBsFileId() {
        return bsFileId;
    }

    public void setBsFileId(Long bsFileId) {
        this.bsFileId = bsFileId;
    }

    public Date getBsDeadline() {
        return bsDeadline;
    }

    public void setBsDeadline(Date bsDeadline) {
        this.bsDeadline = bsDeadline;
    }
}
