package com.web.quote.entity;

import com.app.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 新料报价物料关联表（报价明细）
 *
 */
@Entity(name = "QuoteMateriel")
@Table(name = QuoteMateriel.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class QuoteMateriel extends BaseEntity {
    private static final long serialVersionUID = -8158104184933490519L;
    public static final String TABLE_NAME = "t_quote_materiel";

    /**
     * 报价单ID
     */
    @ApiModelProperty(name = "qtId", value = "报价单ID")
    @Column
    protected Long qtId;

    /**
     * 物料ID
     */
    @ApiModelProperty(name = "mateId", value = "物料ID")
    @Column
    protected Long mateId;

    /**
     * 物料编号
     */
    @ApiModelProperty(name = "mateCode", value = "物料号")
    @Column(name = "mate_code", length = 200)
    protected String mateCode;

    /**
     * 物料名称
     */
    @ApiModelProperty(name = "mateName", value = "物料名称")
    @Column(length = 500)
    protected String mateName;

    /**
     * 物料规格
     */
    @ApiModelProperty(name = "mateModel",  value = "物料规格")
    @Column(length = 500)
    protected String mateModel;

    /**
     * 单位
     */
    @ApiModelProperty(name = "qtUnit", value = "单位")
    @Column(length = 20)
    protected String qtUnit;

    /**
     * 数量
     */
    @ApiModelProperty(name = "qtMateNum", value = "数量")
    @Column
    protected Integer qtMateNum;

    /**
     * 单价
     */
    @ApiModelProperty(name = "qtUnitPrice", value = "单价")
    @Column
    protected Float qtUnitPrice;

    /**
     * 总价
     */
    @ApiModelProperty(name = "qtTotalPrice", value = "总价")
    @Column
    protected Float qtTotalPrice;

    /**
     * 报价备注
     */
    @ApiModelProperty(name = "qtMateDesc", value = "报价备注")
    @Column(length = 500)
    protected String qtMateDesc;

    public Long getQtId() {
        return qtId;
    }

    public void setQtId(Long qtId) {
        this.qtId = qtId;
    }

    public Long getMateId() {
        return mateId;
    }

    public void setMateId(Long mateId) {
        this.mateId = mateId;
    }

    public String getMateCode() {
        return mateCode;
    }

    public void setMateCode(String mateCode) {
        this.mateCode = mateCode;
    }

    public String getMateName() {
        return mateName;
    }

    public void setMateName(String mateName) {
        this.mateName = mateName;
    }

    public String getMateModel() {
        return mateModel;
    }

    public void setMateModel(String mateModel) {
        this.mateModel = mateModel;
    }

    public String getQtUnit() {
        return qtUnit;
    }

    public void setQtUnit(String qtUnit) {
        this.qtUnit = qtUnit;
    }

    public Integer getQtMateNum() {
        return qtMateNum;
    }

    public void setQtMateNum(Integer qtMateNum) {
        this.qtMateNum = qtMateNum;
    }

    public Float getQtUnitPrice() {
        return qtUnitPrice;
    }

    public void setQtUnitPrice(Float qtUnitPrice) {
        this.qtUnitPrice = qtUnitPrice;
    }

    public Float getQtTotalPrice() {
        return qtTotalPrice;
    }

    public void setQtTotalPrice(Float qtTotalPrice) {
        this.qtTotalPrice = qtTotalPrice;
    }

    public String getQtMateDesc() {
        return qtMateDesc;
    }

    public void setQtMateDesc(String qtMateDesc) {
        this.qtMateDesc = qtMateDesc;
    }
}
