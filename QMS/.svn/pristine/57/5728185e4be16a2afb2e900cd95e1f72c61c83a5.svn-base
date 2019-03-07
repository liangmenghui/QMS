package com.unind.qms.web.shipment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.unind.base.domain.admin.BaseEntity;
import com.unind.fs.domain.file.FsFile;
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
@Table(name = ShipmentInspectRecordFile.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class ShipmentInspectRecordFile extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "t_shipment_inspect_record_file";

    /**
     * 出货检验核查记录ID
     */
    @ApiModelProperty(name="bsShipmentRecordId",required=true,value="出货检验核查记录ID")
    @NotNull
    @Column
    protected Long bsShipmentRecordId;

    @ApiModelProperty(name="shipmentInspectRecord",hidden=true,value="出货检验核查记录")
    @ManyToOne
    @JoinColumn(name = "bsShipmentRecordId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected ShipmentInspectRecord shipmentInspectRecord;

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

    public Long getBsShipmentRecordId() {
        return bsShipmentRecordId;
    }

    public void setBsShipmentRecordId(Long bsShipmentRecordId) {
        this.bsShipmentRecordId = bsShipmentRecordId;
    }

    public ShipmentInspectRecord getShipmentInspectRecord() {
        return shipmentInspectRecord;
    }

    @JsonBackReference
    public void setShipmentInspectRecord(ShipmentInspectRecord shipmentInspectRecord) {
        this.shipmentInspectRecord = shipmentInspectRecord;
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
