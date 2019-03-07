package com.web.materiel.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;

import com.app.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 物料基础信息表
 *
 */
@Entity(name = "MaterielInfo")
@Table(name = MaterielInfo.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class MaterielInfo extends BaseEntity {
    private static final long serialVersionUID = 6944849575214769761L;
    public static final String TABLE_NAME = "t_materiel";
    

    public MaterielInfo (){
    	
    }
    
    /**
     * K3物料号
     */
    @ApiModelProperty(name = "mateK3Code", value = "K3物料号")
    @Column(name = "mate_k3_code", length = 200)
    protected String mateK3Code;

    /**
     * 物料类别名称
     */
    @ApiModelProperty(name = "categoryName", value = "物料类别名称")
    @Column(length = 200)
    protected String categoryName;

    /**
     * 物料名称
     */
    @ApiModelProperty(name = "mateName", value = "物料名称")
    @Column(length = 500)
    protected String mateName;
    
    /**
     * 物料全称
     */
    @ApiModelProperty(name = "mateFullName", value = "物料名称全称")
    @Column(length = 500)
    protected String mateFullName;

    /**
     * 物料规格
     */
    @ApiModelProperty(name = "mateModel",  value = "物料规格")
    @Column(length = 500)
    protected String mateModel;

    /**
     * 供应商编号
     */
    @ApiModelProperty(name = "SuppCode",value = "供应商编号")
    @Column(length = 200)
    protected String suppCode;

    /**
     * 供应商中文名称
     */
    @ApiModelProperty(name = "suppChineseName", value = "供应商中文名称")
    @Column(length = 500)
    protected String suppChineseName;

    /**
     * 备注
     */
    @ApiModelProperty(name = "remark", value = "备注")
    @Column(length = 500)
    protected String remark;
    
    
    @ApiModelProperty(name = "itemStyle", value = "物料类型")
    @Column
    protected Integer itemStyle;
    

    public String getMateK3Code() {
        return mateK3Code;
    }

    public void setMateK3Code(String mateK3Code) {
        this.mateK3Code = mateK3Code;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public String getSuppCode() {
        return suppCode;
    }

    public void setSuppCode(String suppCode) {
        this.suppCode = suppCode;
    }

    public String getSuppChineseName() {
        return suppChineseName;
    }

    public void setSuppChineseName(String suppChineseName) {
        this.suppChineseName = suppChineseName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	

	public Integer getItemStyle() {
		return itemStyle;
	}

	public void setItemStyle(Integer itemStyle) {
		this.itemStyle = itemStyle;
	}

	public String getMateFullName() {
		return mateFullName;
	}

	public void setMateFullName(String mateFullName) {
		this.mateFullName = mateFullName;
	}
    
}
