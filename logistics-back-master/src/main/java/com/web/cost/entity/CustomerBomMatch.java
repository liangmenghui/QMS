package com.web.cost.entity;

import com.app.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 客户BOM匹配数据表
 *
 */
@Entity(name = "CustomerBomMatch")
@Table(name = CustomerBomMatch.TABLE_NAME)
public class CustomerBomMatch extends BaseEntity {
    private static final long serialVersionUID = -6429155299124466096L;
    public static final String TABLE_NAME = "t_customer_bom_match";

    /**
     * 客户BOM表ID
     */
    @ApiModelProperty(name = "cusBomId", value = "客户BOM表ID")
    @Column
    protected Long cusBomId;

    /**
     * 客户BOM的参数表ID
     */
    @ApiModelProperty(name = "BomParamsId", value = "客户BOM的参数表ID")
    @Column
    protected Long bomParamsId;

    /**
     * 文件ID
     */
    @ApiModelProperty(name = "fileId", value = "文件ID")
    @Column
    protected Long fileId;

    /**
     * 匹配度
     */
    @ApiModelProperty(name = "ratio", value = "匹配度")
    @Column
    protected Float ratio;
    
    /**
     * 是否选中
     */
    @ApiModelProperty(name = "checkStatus",  value = "是否选中")
    @Column
    protected Integer checkStatus=0;
    
    /**
     * 修改人名称
     */
    @ApiModelProperty(name = "modifiedName", value = "修改人名称")
    @Column(length = 100)
    protected String modifiedName;


    /**
     * K3物料ID
     */
    @ApiModelProperty(name = "fItemId", value = "K3物料ID")
    @Column
    protected Integer fItemId;

    /**
     * K3物料号
     */
    @ApiModelProperty(name = "fNumber", value = "K3物料号")
    @Column(length = 200)
    protected String fNumber;

    /**
     * K3物料名称
     */
    @ApiModelProperty(name = "fName", value = "K3物料名称")
    @Column(length = 500)
    protected String fName;

    /**
     * K3物料规格
     */
    @ApiModelProperty(name = "fModel",  value = "K3物料规格")
    @Column(length = 500)
    protected String fModel;

    /**
     * K3物料最新采购价
     */
    @ApiModelProperty(name = "fOrderPriceLatest",  value = "K3物料最新采购价")
    @Column
    protected BigDecimal fOrderPriceLatest;

    /**
     * K3物料库存均价
     */
    @ApiModelProperty(name = "fStockPriceAvg", value = "K3物料库存均价")
    @Column
    protected BigDecimal fStockPriceAvg;

    /**
     * 物料ID
     */
    @ApiModelProperty(name = "mateId", value = "物料ID")
    @Column
    protected Long mateId;

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
     * 最新采购单价（不含税）
     */
    @Column
    protected BigDecimal fPrice;

    /**
     * 最新采购单价（含税）
     */
    @Column
    protected BigDecimal fAuxPriceDiscount;
    
    /**
     * 3个月内的最高采购单价（不含税）
     */
    @Column
    protected BigDecimal fPrice3MonthMax;

    /**
     * 3个月内的最高采购单价（含税）
     */
    @Column
    protected BigDecimal fAuxPrice3MonthMax;
    
    /**
     * 3个月内的最低采购单价（不含税）
     */
    @Column
    protected BigDecimal fPrice3MonthMin;

    /**
     * 3个月内的最低采购单价（含税）
     */
    @Column
    protected BigDecimal fAuxPrice3MonthMin;

    /**
     * smt点数
     */
    @ApiModelProperty(name = "smtPoints", value = "smt点数")
    @Column
    protected Float smtPoints;

    public Long getCusBomId() {
        return cusBomId;
    }

    public void setCusBomId(Long cusBomId) {
        this.cusBomId = cusBomId;
    }

    public Long getBomParamsId() {
        return bomParamsId;
    }

    public void setBomParamsId(Long bomParamsId) {
        this.bomParamsId = bomParamsId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Float getRatio() {
        return ratio;
    }

    public void setRatio(Float ratio) {
        this.ratio = ratio;
    }

    public Integer getfItemId() {
        return fItemId;
    }

    public void setfItemId(Integer fItemId) {
        this.fItemId = fItemId;
    }

    public String getfNumber() {
        return fNumber;
    }

    public void setfNumber(String fNumber) {
        this.fNumber = fNumber;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfModel() {
        return fModel;
    }

    public void setfModel(String fModel) {
        this.fModel = fModel;
    }

    public BigDecimal getfOrderPriceLatest() {
        return fOrderPriceLatest;
    }

    public void setfOrderPriceLatest(BigDecimal fOrderPriceLatest) {
        this.fOrderPriceLatest = fOrderPriceLatest;
    }

    public BigDecimal getfStockPriceAvg() {
        return fStockPriceAvg;
    }

    public void setfStockPriceAvg(BigDecimal fStockPriceAvg) {
        this.fStockPriceAvg = fStockPriceAvg;
    }

    public Long getMateId() {
        return mateId;
    }

    public void setMateId(Long mateId) {
        this.mateId = mateId;
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

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getModifiedName() {
		return modifiedName;
	}

	public void setModifiedName(String modifiedName) {
		this.modifiedName = modifiedName;
	}

	public BigDecimal getfPrice() {
		return fPrice;
	}

	public void setfPrice(BigDecimal fPrice) {
		this.fPrice = fPrice;
	}

	public BigDecimal getfAuxPriceDiscount() {
		return fAuxPriceDiscount;
	}

	public void setfAuxPriceDiscount(BigDecimal fAuxPriceDiscount) {
		this.fAuxPriceDiscount = fAuxPriceDiscount;
	}

	public BigDecimal getfPrice3MonthMax() {
		return fPrice3MonthMax;
	}

	public void setfPrice3MonthMax(BigDecimal fPrice3MonthMax) {
		this.fPrice3MonthMax = fPrice3MonthMax;
	}

	public BigDecimal getfAuxPrice3MonthMax() {
		return fAuxPrice3MonthMax;
	}

	public void setfAuxPrice3MonthMax(BigDecimal fAuxPrice3MonthMax) {
		this.fAuxPrice3MonthMax = fAuxPrice3MonthMax;
	}

	public BigDecimal getfPrice3MonthMin() {
		return fPrice3MonthMin;
	}

	public void setfPrice3MonthMin(BigDecimal fPrice3MonthMin) {
		this.fPrice3MonthMin = fPrice3MonthMin;
	}

	public BigDecimal getfAuxPrice3MonthMin() {
		return fAuxPrice3MonthMin;
	}

	public void setfAuxPrice3MonthMin(BigDecimal fAuxPrice3MonthMin) {
		this.fAuxPrice3MonthMin = fAuxPrice3MonthMin;
	}

    public Float getSmtPoints() {
        return smtPoints;
    }

    public void setSmtPoints(Float smtPoints) {
        this.smtPoints = smtPoints;
    }
}
