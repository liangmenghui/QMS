package com.unind.qms.web.shipment.entity;

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
 * @author chen
 */
@Entity
@Table(name = OrderInspect.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class OrderInspect extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "t_order_inspect";

    /**
     * 产品ID
     */
    @ApiModelProperty(name="bsPrId",required=true,value="产品ID")
    @NotNull
    @Column
    protected Long bsPrId;

    /**
     * 产品编号
     */
    @ApiModelProperty(name="bsPrCode",required=true,value="产品编号")
    @NotNull
    @Column(length = 50)
    protected String bsPrCode;

    /**
     * 产品名称
     */
    @ApiModelProperty(name="bsPrName",required=true,value="产品名称")
    @NotNull
    @Column(length = 80)
    protected String bsPrName;

    /**
     * 供应商编号
     */
    @ApiModelProperty(name="bsSuppCode",value="供应商编号")
    @Column(length = 50)
    protected String bsSuppCode;

    /**
     * 供应商名称
     */
    @ApiModelProperty(name="bsSuppChieseName",value="供应商名称")
    @Column(length = 150)
    protected String bsSuppChieseName;

    /**
     * 订单PO号
     */
    @ApiModelProperty(name="bsPoNo",value="订单PO号")
    @Column(length = 50)
    protected String bsPoNo;

    /**
     * 订单数量
     */
    @ApiModelProperty(name="bsPoQty",value="订单数量")
    @Column
    protected int bsPoQty;

    /**
     * 合同号
     */
    @ApiModelProperty(name="bsContractNo",value="合同号")
    @Column(length = 50)
    protected String bsContractNo;

    /**
     * 部件号
     */
    @ApiModelProperty(name="bsPartNo",value="部件号")
    @Column(length = 50)
    protected String bsPartNo;

    /**
     * 出货时间
     */
    @ApiModelProperty(name="bsOutTime",value="出货时间")
    @Column(length = 50)
    protected String bsOutTime;

    /**
     * 验货时间
     */
    @ApiModelProperty(name="bsCheckTime",value="验货时间")
    @Column(length = 50)
    protected String bsCheckTime;

    /**
     * 检验员ID
     */
    @ApiModelProperty(name="bsCheckId",value="检验员ID")
    @Column
    protected Long bsCheckId;

    /**
     * 检验员
     */
    @ApiModelProperty(name="bsCheckName",value="检验员")
    @Column(length = 50)
    protected String bsCheckName;

    /**
     * 供应商质量工程师ID
     */
    @ApiModelProperty(name="bsSqeName",value="供应商质量工程师ID")
    @Column
    protected Long bsSqeId;

    /**
     * 供应商质量工程师
     */
    @ApiModelProperty(name="bsSqeName",value="供应商质量工程师")
    @Column(length = 50)
    protected String bsSqeName;

    /**
     * 修改人
     */
    @ApiModelProperty(name="pkModifiedBy",hidden=true,value="修改人")
    @Column
    protected Long pkModifiedBy;

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

    public String getBsPrCode() {
        return bsPrCode;
    }

    public void setBsPrCode(String bsPrCode) {
        this.bsPrCode = bsPrCode;
    }

    public String getBsPrName() {
        return bsPrName;
    }

    public void setBsPrName(String bsPrName) {
        this.bsPrName = bsPrName;
    }

    public String getBsSuppCode() {
        return bsSuppCode;
    }

    public void setBsSuppCode(String bsSuppCode) {
        this.bsSuppCode = bsSuppCode;
    }

    public String getBsSuppChieseName() {
        return bsSuppChieseName;
    }

    public void setBsSuppChieseName(String bsSuppChieseName) {
        this.bsSuppChieseName = bsSuppChieseName;
    }

    public String getBsPoNo() {
        return bsPoNo;
    }

    public void setBsPoNo(String bsPoNo) {
        this.bsPoNo = bsPoNo;
    }

    public int getBsPoQty() {
        return bsPoQty;
    }

    public void setBsPoQty(int bsPoQty) {
        this.bsPoQty = bsPoQty;
    }

    public String getBsContractNo() {
        return bsContractNo;
    }

    public void setBsContractNo(String bsContractNo) {
        this.bsContractNo = bsContractNo;
    }

    public String getBsPartNo() {
        return bsPartNo;
    }

    public void setBsPartNo(String bsPartNo) {
        this.bsPartNo = bsPartNo;
    }

    public String getBsOutTime() {
        return bsOutTime;
    }

    public void setBsOutTime(String bsOutTime) {
        this.bsOutTime = bsOutTime;
    }

    public String getBsCheckTime() {
        return bsCheckTime;
    }

    public void setBsCheckTime(String bsCheckTime) {
        this.bsCheckTime = bsCheckTime;
    }

    public Long getBsCheckId() {
        return bsCheckId;
    }

    public void setBsCheckId(Long bsCheckId) {
        this.bsCheckId = bsCheckId;
    }

    public String getBsCheckName() {
        return bsCheckName;
    }

    public void setBsCheckName(String bsCheckName) {
        this.bsCheckName = bsCheckName;
    }

    public Long getBsSqeId() {
        return bsSqeId;
    }

    public void setBsSqeId(Long bsSqeId) {
        this.bsSqeId = bsSqeId;
    }

    public String getBsSqeName() {
        return bsSqeName;
    }

    public void setBsSqeName(String bsSqeName) {
        this.bsSqeName = bsSqeName;
    }

    public Long getPkModifiedBy() {
        return pkModifiedBy;
    }

    public void setPkModifiedBy(Long pkModifiedBy) {
        this.pkModifiedBy = pkModifiedBy;
    }

    public SysUser getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(SysUser modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
