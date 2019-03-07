package com.unind.qms.web.shipment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.unind.base.domain.admin.BaseEntity;
import com.unind.fs.domain.file.FsFile;
import com.unind.qms.web.basic.entity.FeedbackInfo;
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
@Table(name = ShipmentInspectFile.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class ShipmentInspectFile extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "t_shipment_inspect_file";

    /**
     * 出货检验报告ID
     */
    @ApiModelProperty(name="bsShipmentId",required=true,value="出货检验报告ID")
    @NotNull
    @Column
    protected Long bsShipmentId;

    @ApiModelProperty(name="shipmentInspect",hidden=true,value="出货检验报告")
    @ManyToOne
    @JoinColumn(name = "bsShipmentId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected ShipmentInspect shipmentInspect;

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

    public Long getBsShipmentId() {
        return bsShipmentId;
    }

    public void setBsShipmentId(Long bsShipmentId) {
        this.bsShipmentId = bsShipmentId;
    }

    @JsonBackReference
    public ShipmentInspect getShipmentInspect() {
        return shipmentInspect;
    }

    public void setShipmentInspect(ShipmentInspect shipmentInspect) {
        this.shipmentInspect = shipmentInspect;
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
