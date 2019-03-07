package com.unind.qms.web.product.entity;

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
@Table(name = ProductInfo.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class ProductInfo extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "erp_product_info";

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
     * 产品状态
     */
    @ApiModelProperty(name="bsStatus",value="产品状态")
    @Column
    protected int bsStatus;

    /**
     * 产品类型
     */
    @ApiModelProperty(name="bsPrType",required=true,value="产品类型")
    @Column(length = 100)
    protected String bsPrType;

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
     * 组织
     */
    @ApiModelProperty(name="bsOrgIo",value="组织")
    @Column(length = 50)
    protected String bsOrgIo;

    /**
     * 图号
     */
    @ApiModelProperty(name="bsBlueprintNo",value="图号")
    @Column(length = 50)
    protected String bsBlueprintNo;

    /**
     * 图纸版本号
     */
    @ApiModelProperty(name="bsBlueprintVersion",value="图纸版本号")
    @Column(length = 20)
    protected String bsBlueprintVersion;

    /**
     * 过程审核流程记录ID
     */
    @ApiModelProperty(name="bsProcessRecordId",value="过程审核流程记录ID")
    @Column
    protected Long bsProcessRecordId;

    /**
     * 成品检验流程记录ID
     */
    @ApiModelProperty(name="bsProductRecordId",value="成品检验流程记录ID")
    @Column
    protected Long bsProductRecordId;

    /**
     * 风险等级
     */
    @ApiModelProperty(name="bsRiskLevel",value="风险等级")
    @Column
    protected int bsRiskLevel;

    /**
     * 风险等级得分
     */
    @ApiModelProperty(name="bsRiskScore",value="风险等级得分")
    @Column
    protected int bsRiskScore;

    /**
     * 手动管理风险等级
     */
    @ApiModelProperty(name="bsRiskManual",value="手动管理风险等级")
    @Column
    protected int bsRiskManual;

    /**
     * 是否审核中
     */
    @ApiModelProperty(name="bsIsApprove",value="是否审核中")
    @Column
    protected int bsIsApprove;

    /**
     * 风险等级(系统)
     */
    @ApiModelProperty(name="bsRiskLevelSys",value="风险等级(系统)")
    @Column
    protected int bsRiskLevelSys;

    /**
     * 风险等级评定时间(系统)
     */
    @ApiModelProperty(name="bsRiskTimeSys",value="风险等级评定时间(系统)")
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    protected Date bsRiskTimeSys;

    /**
     * 风险等级(user)
     */
    @ApiModelProperty(name="bsRiskLevelUser",value="风险等级(user)")
    @Column
    protected int bsRiskLevelUser;

    /**
     * 风险等级评定时间(user)
     */
    @ApiModelProperty(name="bsRiskTimeUser",value="风险等级评定时间(user)")
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    protected Date bsRiskTimeUser;

    /**
     * 风险等级备注(user)
     */
    @ApiModelProperty(name="bsRiskDescUser",value="风险等级备注(user)")
    @Column(length = 500)
    protected String bsRiskDescUser;

    /**
     * 产品产值
     */
    @ApiModelProperty(name="bsPrValue",value="产品产值")
    @Column
    protected int bsPrValue;

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

    public int getBsStatus() {
        return bsStatus;
    }

    public void setBsStatus(int bsStatus) {
        this.bsStatus = bsStatus;
    }

    public String getBsPrType() {
        return bsPrType;
    }

    public void setBsPrType(String bsPrType) {
        this.bsPrType = bsPrType;
    }

    public String getBsSuppCode() {
        return bsSuppCode;
    }

    public void setBsSuppCode(String bsSuppCode) {
        this.bsSuppCode = bsSuppCode;
    }

    public String getBsOrgIo() {
        return bsOrgIo;
    }

    public void setBsOrgIo(String bsOrgIo) {
        this.bsOrgIo = bsOrgIo;
    }

    public String getBsBlueprintNo() {
        return bsBlueprintNo;
    }

    public void setBsBlueprintNo(String bsBlueprintNo) {
        this.bsBlueprintNo = bsBlueprintNo;
    }

    public String getBsBlueprintVersion() {
        return bsBlueprintVersion;
    }

    public void setBsBlueprintVersion(String bsBlueprintVersion) {
        this.bsBlueprintVersion = bsBlueprintVersion;
    }

    public String getBsSuppChieseName() {
        return bsSuppChieseName;
    }

    public void setBsSuppChieseName(String bsSuppChieseName) {
        this.bsSuppChieseName = bsSuppChieseName;
    }

    public Long getBsProcessRecordId() {
        return bsProcessRecordId;
    }

    public void setBsProcessRecordId(Long bsProcessRecordId) {
        this.bsProcessRecordId = bsProcessRecordId;
    }

    public Long getBsProductRecordId() {
        return bsProductRecordId;
    }

    public void setBsProductRecordId(Long bsProductRecordId) {
        this.bsProductRecordId = bsProductRecordId;
    }

    public int getBsRiskLevel() {
        return bsRiskLevel;
    }

    public void setBsRiskLevel(int bsRiskLevel) {
        this.bsRiskLevel = bsRiskLevel;
    }

    public int getBsRiskScore() {
        return bsRiskScore;
    }

    public void setBsRiskScore(int bsRiskScore) {
        this.bsRiskScore = bsRiskScore;
    }

    public int getBsRiskManual() {
        return bsRiskManual;
    }

    public void setBsRiskManual(int bsRiskManual) {
        this.bsRiskManual = bsRiskManual;
    }

    public int getBsIsApprove() {
        return bsIsApprove;
    }

    public void setBsIsApprove(int bsIsApprove) {
        this.bsIsApprove = bsIsApprove;
    }

    public int getBsRiskLevelSys() {
        return bsRiskLevelSys;
    }

    public void setBsRiskLevelSys(int bsRiskLevelSys) {
        this.bsRiskLevelSys = bsRiskLevelSys;
    }

    public Date getBsRiskTimeSys() {
        return bsRiskTimeSys;
    }

    public void setBsRiskTimeSys(Date bsRiskTimeSys) {
        this.bsRiskTimeSys = bsRiskTimeSys;
    }

    public int getBsRiskLevelUser() {
        return bsRiskLevelUser;
    }

    public void setBsRiskLevelUser(int bsRiskLevelUser) {
        this.bsRiskLevelUser = bsRiskLevelUser;
    }

    public Date getBsRiskTimeUser() {
        return bsRiskTimeUser;
    }

    public void setBsRiskTimeUser(Date bsRiskTimeUser) {
        this.bsRiskTimeUser = bsRiskTimeUser;
    }

    public String getBsRiskDescUser() {
        return bsRiskDescUser;
    }

    public void setBsRiskDescUser(String bsRiskDescUser) {
        this.bsRiskDescUser = bsRiskDescUser;
    }

    public int getBsPrValue() {
        return bsPrValue;
    }

    public void setBsPrValue(int bsPrValue) {
        this.bsPrValue = bsPrValue;
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
