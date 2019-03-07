package com.unind.qms.web.sample.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.unind.base.domain.admin.BaseEntity;
import com.unind.base.domain.admin.user.SysUser;
import com.unind.qms.web.shipment.entity.ShipmentInspect;
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
@Table(name = SampleRegularRecord.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class SampleRegularRecord extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "t_sample_regular_record";

    /**
     * 出货检验报告ID
     */
    @ApiModelProperty(name="bsShipmentInspectId",required=true,value="出货检验报告ID")
    @NotNull
    @Column
    protected Long bsShipmentInspectId;

    /**
     * 样品规格ID
     */
    @ApiModelProperty(name="bsSampleRegularId",required=true,value="样品规格ID")
    @NotNull
    @Column
    protected Long bsSampleRegularId;

    /**
     * 样品规格序号
     */
    @ApiModelProperty(name="bsSampleRegularIndex",required=true,value="样品规格序号")
    @Column
    protected int bsSampleRegularIndex;

    /**
     * 测量结果
     */
    @ApiModelProperty(name="bsMeasureResult",value="测量结果")
    @Column(length = 500)
    protected String bsMeasureResult;

    /**
     * 备注
     */
    @ApiModelProperty(name="bsDesc",value="备注")
    @Column(length = 500)
    protected String bsDesc;

    @ApiModelProperty(name="shipmentInspectBy",hidden=true,value="出货检验报告")
    @ManyToOne
    @JoinColumn(name = "bsShipmentInspectId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected ShipmentInspect shipmentInspectBy;

    @ApiModelProperty(name="sampleRegularBy",hidden=true,value="样品规格")
    @ManyToOne
    @JoinColumn(name = "bsSampleRegularId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected SampleRegular sampleRegularBy;

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

    public Long getBsShipmentInspectId() {
        return bsShipmentInspectId;
    }

    public void setBsShipmentInspectId(Long bsShipmentInspectId) {
        this.bsShipmentInspectId = bsShipmentInspectId;
    }

    public Long getBsSampleRegularId() {
        return bsSampleRegularId;
    }

    public void setBsSampleRegularId(Long bsSampleRegularId) {
        this.bsSampleRegularId = bsSampleRegularId;
    }

    public int getBsSampleRegularIndex() {
        return bsSampleRegularIndex;
    }

    public void setBsSampleRegularIndex(int bsSampleRegularIndex) {
        this.bsSampleRegularIndex = bsSampleRegularIndex;
    }

    public String getBsMeasureResult() {
        return bsMeasureResult;
    }

    public void setBsMeasureResult(String bsMeasureResult) {
        this.bsMeasureResult = bsMeasureResult;
    }

    public String getBsDesc() {
        return bsDesc;
    }

    public void setBsDesc(String bsDesc) {
        this.bsDesc = bsDesc;
    }

    public ShipmentInspect getShipmentInspectBy() {
        return shipmentInspectBy;
    }

    @JsonBackReference
    public void setShipmentInspectBy(ShipmentInspect shipmentInspectBy) {
        this.shipmentInspectBy = shipmentInspectBy;
    }

    public SampleRegular getSampleRegularBy() {
        return sampleRegularBy;
    }

    public void setSampleRegularBy(SampleRegular sampleRegularBy) {
        this.sampleRegularBy = sampleRegularBy;
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
