package com.unind.qms.web.supplier.entity;

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
 * 客户审核文件关联
 * @author Shen
 */
@Entity
@Table(name = CustomerApprovedFile.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class CustomerApprovedFile extends BaseEntity {
    private static final long serialVersionUID = -8132328401953562918L;
    public static final String TABLE_NAME = "t_customer_approved_file";

    /**
     * 客户审核记录ID
     */
    @ApiModelProperty(name = "bsCustomerApprovedId", required = true, value = "客户审核记录ID")
    @NotNull
    @Column
    protected Long bsCustomerApprovedId;

    @ApiModelProperty(name = "customerApproved", required = true, value = "客户审核记录信息")
    @ManyToOne
    @JoinColumn(name = "bsCustomerApprovedId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected CustomerApprovedRecord customerApproved;

    /**
     * 文件信息ID
     */
    @ApiModelProperty(name = "fsFileId", required = true, value = "文件信息ID")
    @NotNull
    @Column
    protected Long fsFileId;

    @ApiModelProperty(name = "fsFileBy", hidden = true, value = "文件记录")
    @OneToOne
    @JoinColumn(name = "fsFileId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected FsFile fsFileBy;

    public Long getBsCustomerApprovedId() {
        return bsCustomerApprovedId;
    }

    public void setBsCustomerApprovedId(Long bsCustomerApprovedId) {
        this.bsCustomerApprovedId = bsCustomerApprovedId;
    }

    public CustomerApprovedRecord getCustomerApproved() {
        return customerApproved;
    }

    @JsonBackReference
    public void setCustomerApproved(CustomerApprovedRecord customerApproved) {
        this.customerApproved = customerApproved;
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
