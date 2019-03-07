package com.unind.qms.web.approved.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.unind.base.domain.admin.BaseEntity;
import com.unind.base.domain.admin.user.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chen
 */
@Entity
@Table(name = ApprovedItems.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class ApprovedItems extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "t_approved_items";

    /**
     * 项目名称
     */
    @ApiModelProperty(name="bsName",required=true,value="项目名称")
    @NotNull
    @Column(length = 45)
    protected String bsName;

    /**
     * 审核类型
     */
    @ApiModelProperty(name="bsType",required=true,value="审核类型")
    @NotNull
    @Column
    protected int bsType;

    /**
     * 页面路由名称
     */
    @ApiModelProperty(name="bsRouterName",value="页面路由名称")
    @Column(length = 45)
    protected String bsRouterName;

    /**
     * 页面路由
     */
    @ApiModelProperty(name="bsRouter",value="页面路由")
    @Column(length = 45)
    protected String bsRouter;

    /**
     * 审核内容类型
     */
    @ApiModelProperty(name="bsContentType",value="审核内容类型")
    @Column
    protected int bsContentType;

    /**
     * 审核流程ID
     */
    @ApiModelProperty(name="bsFlowId",required=true,value="审核流程ID")
    @NotNull
    @Column
    protected Long bsFlowId;

    /**
     * 审核优先级
     */
    @ApiModelProperty(name="bsPriority",required=true,value="审核优先级")
    @NotNull
    @Column
    protected int bsPriority;

    //条款集合
    @OneToMany(mappedBy = "approvedItems")
    protected Set<ApprovedItemsMap> termsSet;

    //记录人集合
    @OneToMany(mappedBy = "approvedItems")
    protected Set<ApprovedRecorderMap> recorderSet;

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

    public String getBsName() {
        return bsName;
    }

    public void setBsName(String bsName) {
        this.bsName = bsName;
    }

    public int getBsType() {
        return bsType;
    }

    public void setBsType(int bsType) {
        this.bsType = bsType;
    }

    public String getBsRouterName() {
        return bsRouterName;
    }

    public void setBsRouterName(String bsRouterName) {
        this.bsRouterName = bsRouterName;
    }

    public String getBsRouter() {
        return bsRouter;
    }

    public void setBsRouter(String bsRouter) {
        this.bsRouter = bsRouter;
    }

    public int getBsContentType() {
        return bsContentType;
    }

    public void setBsContentType(int bsContentType) {
        this.bsContentType = bsContentType;
    }

    public Set<ApprovedItemsMap> getTermsSet() {
        return termsSet;
    }

    public void setTermsSet(Set<ApprovedItemsMap> termsSet) {
        this.termsSet = termsSet;
    }

    public Set<ApprovedRecorderMap> getRecorderSet() {
        return recorderSet;
    }

    public void setRecorderSet(Set<ApprovedRecorderMap> recorderSet) {
        this.recorderSet = recorderSet;
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

    public Long getBsFlowId() {
        return bsFlowId;
    }

    public void setBsFlowId(Long bsFlowId) {
        this.bsFlowId = bsFlowId;
    }

    public int getBsPriority() {
        return bsPriority;
    }

    public void setBsPriority(int bsPriority) {
        this.bsPriority = bsPriority;
    }
}
