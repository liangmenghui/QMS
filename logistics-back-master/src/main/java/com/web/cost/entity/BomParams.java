package com.web.cost.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;

import com.app.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 客户BOM的参数表
 *
 */
@Entity(name = "BomParams")
@Table(name = BomParams.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class BomParams extends BaseEntity {
    private static final long serialVersionUID = 5194146421428880640L;
    public static final String TABLE_NAME = "t_bom_params";

    /**
     * 文件ID（作为某一个客户BOM的唯一标识）
     */
    @ApiModelProperty(name = "fileId", value = "文件ID")
    @Column
    protected Long fileId;

    /**
     * BOM编号（也可作为客户BOM的唯一标识，匹配K3时生成）
     */
    @ApiModelProperty(name = "bomCode", value = "BOM编号")
    @Column
    protected String bomCode;

    /**
     * 起始行数
     */
    @ApiModelProperty(name = "startRow", value = "起始行数")
    @Column
    protected Integer startRow;


    /**
     * 规格列
     */
    @ApiModelProperty(name = "standardCol", value = "规格列")
    @Column
    protected String standardCol;

    /**
     * 类别列
     */
    @ApiModelProperty(name = "categoryCol", value = "类别列")
    @Column
    protected String categoryCol;

    /**
     * 数量列
     */
    @ApiModelProperty(name = "quantityCol", value = "数量列")
    @Column
    protected String quantityCol;

    /**
     * 属性4
     */
    @ApiModelProperty(name = "bomProp4", value = "属性4")
    @Column
    protected String bomProp4;

    /**
     * 封装列
     */
    @ApiModelProperty(name = "packageCol", value = "封装列")
    @Column
    protected String packageCol;

    /**
     * 制造商列
     */
    @ApiModelProperty(name = "makerCol", value = "制造商列")
    @Column
    protected String makerCol;

    /**
     * 规格的分隔符
     */
    @ApiModelProperty(name = "checkList", value = "规格的分隔符")
    @Column
    protected String checkList;

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getBomCode() {
		return bomCode;
	}

	public void setBomCode(String bomCode) {
		this.bomCode = bomCode;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	public String getStandardCol() {
		return standardCol;
	}

	public void setStandardCol(String standardCol) {
		this.standardCol = standardCol;
	}

	public String getCategoryCol() {
		return categoryCol;
	}

	public void setCategoryCol(String categoryCol) {
		this.categoryCol = categoryCol;
	}

	public String getQuantityCol() {
		return quantityCol;
	}

	public void setQuantityCol(String quantityCol) {
		this.quantityCol = quantityCol;
	}

	public String getBomProp4() {
		return bomProp4;
	}

	public void setBomProp4(String bomProp4) {
		this.bomProp4 = bomProp4;
	}

	public String getPackageCol() {
		return packageCol;
	}

	public void setPackageCol(String packageCol) {
		this.packageCol = packageCol;
	}

	public String getMakerCol() {
		return makerCol;
	}

	public void setMakerCol(String makerCol) {
		this.makerCol = makerCol;
	}

	public String getCheckList() {
		return checkList;
	}

	public void setCheckList(String checkList) {
		this.checkList = checkList;
	}
    
    
}
