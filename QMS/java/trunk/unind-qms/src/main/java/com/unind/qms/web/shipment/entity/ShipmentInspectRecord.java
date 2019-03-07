package com.unind.qms.web.shipment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import java.util.Set;

/**
 * @author chen
 */
@Entity
@Table(name = ShipmentInspectRecord.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class ShipmentInspectRecord extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "t_shipment_inspect_record";

    /**
     * 出货检验报告ID
     */
    @ApiModelProperty(name="bsShipmentId",required=true,value="出货检验报告ID")
    @NotNull
    @Column
    protected Long bsShipmentId;

    /**
     * 核查人ID
     */
    @ApiModelProperty(name="bsInspecter",required=true,value="核查人ID")
    @Column
    protected Long bsInspecter;

    @ApiModelProperty(name="inspectBy",hidden=true,value="核查人--user")
    @ManyToOne
    @JoinColumn(name = "bsInspecter", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected SysUser inspectBy;

    /**
     * 核查说明
     */
    @ApiModelProperty(name="bsDesc",value="核查说明")
    @Column(length = 500)
    protected String bsDesc;

    @ApiModelProperty(name="shipmentInspectBy",hidden=true,value="出货检验报告")
    @ManyToOne
    @JoinColumn(name = "bsShipmentId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected ShipmentInspect shipmentInspectBy;

    /**
     * 核查结果
     */
    @ApiModelProperty(name="bsResult",value="核查结果")
    @Column
    protected int bsResult;

    //文件集合
    @OneToMany(mappedBy = "shipmentInspectRecord")
    protected Set<ShipmentInspectRecordFile> fileSet;

    public Long getBsShipmentId() {
        return bsShipmentId;
    }

    public void setBsShipmentId(Long bsShipmentId) {
        this.bsShipmentId = bsShipmentId;
    }

    public Long getBsInspecter() {
        return bsInspecter;
    }

    public void setBsInspecter(Long bsInspecter) {
        this.bsInspecter = bsInspecter;
    }

    public SysUser getInspectBy() {
        return inspectBy;
    }

    public void setInspectBy(SysUser inspectBy) {
        this.inspectBy = inspectBy;
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

    public int getBsResult() {
        return bsResult;
    }

    public void setBsResult(int bsResult) {
        this.bsResult = bsResult;
    }

    public Set<ShipmentInspectRecordFile> getFileSet() {
        return fileSet;
    }

    public void setFileSet(Set<ShipmentInspectRecordFile> fileSet) {
        this.fileSet = fileSet;
    }
}
