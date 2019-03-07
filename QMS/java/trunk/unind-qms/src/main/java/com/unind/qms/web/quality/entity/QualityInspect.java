package com.unind.qms.web.quality.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unind.base.domain.admin.BaseEntity;
import com.unind.base.domain.admin.user.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@Table(name = QualityInspect.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class QualityInspect extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "t_quality_inspect";

    /**
     * 产品ID
     */
    @ApiModelProperty(name="bsPrId",required=true,value="产品ID")
    @NotNull
    @Column
    protected Long bsPrId;

    /**
     * 审核流程记录ID
     */
    @ApiModelProperty(name="bsFlowRecordId",required=true,value="审核流程记录ID")
    @NotNull
    @Column
    protected Long bsFlowRecordId;

    /**
     * 取样日期
     */
    @ApiModelProperty(name="bsSamplingDate",value="取样日期")
    @Column
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    protected Date bsSamplingDate;

    /**
     * 取样地点
     */
    @ApiModelProperty(name="bsSamplingPlace",value="取样地点")
    @Column(length = 50)
    protected String bsSamplingPlace;

    /**
     * 生产日期
     */
    @ApiModelProperty(name="bsProductDate",value="生产日期")
    @Column
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    protected Date bsProductDate;

    /**
     * 产品外形尺寸图示
     */
    @ApiModelProperty(name="bsAppearanceFile",value="产品外形尺寸图示")
    @Column(length = 100)
    protected String bsAppearanceFile;

    /**
     * 样品ID
     */
    @ApiModelProperty(name="bsSampleId",value="样品ID")
    @Column
    protected Long bsSampleId;

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

    public Long getBsPrId() {
        return bsPrId;
    }

    public void setBsPrId(Long bsPrId) {
        this.bsPrId = bsPrId;
    }

    public Long getBsFlowRecordId() {
        return bsFlowRecordId;
    }

    public void setBsFlowRecordId(Long bsFlowRecordId) {
        this.bsFlowRecordId = bsFlowRecordId;
    }

    public Date getBsSamplingDate() {
        return bsSamplingDate;
    }

    public void setBsSamplingDate(Date bsSamplingDate) {
        this.bsSamplingDate = bsSamplingDate;
    }

    public String getBsSamplingPlace() {
        return bsSamplingPlace;
    }

    public void setBsSamplingPlace(String bsSamplingPlace) {
        this.bsSamplingPlace = bsSamplingPlace;
    }

    public Date getBsProductDate() {
        return bsProductDate;
    }

    public void setBsProductDate(Date bsProductDate) {
        this.bsProductDate = bsProductDate;
    }

    public String getBsAppearanceFile() {
        return bsAppearanceFile;
    }

    public void setBsAppearanceFile(String bsAppearanceFile) {
        this.bsAppearanceFile = bsAppearanceFile;
    }

    public Long getBsSampleId() {
        return bsSampleId;
    }

    public void setBsSampleId(Long bsSampleId) {
        this.bsSampleId = bsSampleId;
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
