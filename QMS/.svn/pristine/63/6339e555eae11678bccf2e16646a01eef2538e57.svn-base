package com.unind.qms.web.risk.entity;

import com.unind.base.domain.admin.BaseEntity;
import com.unind.fs.domain.file.FsFile;
import com.unind.qms.web.product.entity.ProductInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

/**
 * 产品风险ppm的不良品数
 * @author Shen
 */
@Entity
@Table(name = ProductPpm.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class ProductPpm extends BaseEntity {

    private static final long serialVersionUID = 1154151142960277043L;
    public static final String TABLE_NAME = "t_product_ppm";

    /**
     * 产品ID
     */
    @ApiModelProperty(name = "bsPrId", value = "产品ID")
    @Column
    protected Long bsPrId;

    @ApiModelProperty(name="productBy",hidden=true,value="产品记录")
    @ManyToOne
    @JoinColumn(name = "bsPrId", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected ProductInfo productBy;

    /**
     * 年份
     */
    @ApiModelProperty(name = "bsYear", value = "年份")
    @Column
    protected int bsYear;

    /**
     * 一月不良品数
     */
    @ApiModelProperty(name = "bsMonthResult1", value = "一月不良品数")
    @Column
    protected int bsMonthResult1;

    /**
     * 二月不良品数
     */
    @ApiModelProperty(name = "bsMonthResult2", value = "二月不良品数")
    @Column
    protected int bsMonthResult2;

    /**
     * 三月不良品数
     */
    @ApiModelProperty(name = "bsMonthResult3", value = "三月不良品数")
    @Column
    protected int bsMonthResult3;

    /**
     * 四月不良品数
     */
    @ApiModelProperty(name = "bsMonthResult4", value = "四月不良品数")
    @Column
    protected int bsMonthResult4;

    /**
     * 五月不良品数
     */
    @ApiModelProperty(name = "bsMonthResult5", value = "五月不良品数")
    @Column
    protected int bsMonthResult5;

    /**
     * 六月不良品数
     */
    @ApiModelProperty(name = "bsMonthResult6", value = "六月不良品数")
    @Column
    protected int bsMonthResult6;

    /**
     * 七月不良品数
     */
    @ApiModelProperty(name = "bsMonthResult7", value = "七月不良品数")
    @Column
    protected int bsMonthResult7;

    /**
     * 八月不良品数
     */
    @ApiModelProperty(name = "bsMonthResult8", value = "八月不良品数")
    @Column
    protected int bsMonthResult8;

    /**
     * 九月不良品数
     */
    @ApiModelProperty(name = "bsMonthResult9", value = "九月不良品数")
    @Column
    protected int bsMonthResult9;

    /**
     * 十月不良品数
     */
    @ApiModelProperty(name = "bsMonthResult10", value = "十月不良品数")
    @Column
    protected int bsMonthResult10;

    /**
     * 十一月不良品数
     */
    @ApiModelProperty(name = "bsMonthResult11", value = "十一月不良品数")
    @Column
    protected int bsMonthResult11;

    /**
     * 十二月不良品数
     */
    @ApiModelProperty(name = "bsMonthResult12", value = "十二月不良品数")
    @Column
    protected int bsMonthResult12;

    /**
     * 一月不良品数（客户）
     */
    @ApiModelProperty(name = "bsCusMonthResult1", value = "一月不良品数（客户）")
    @Column
    protected int bsCusMonthResult1;

    /**
     * 二月不良品数（客户）
     */
    @ApiModelProperty(name = "bsCusMonthResult2", value = "二月不良品数（客户）")
    @Column
    protected int bsCusMonthResult2;

    /**
     * 三月不良品数（客户）
     */
    @ApiModelProperty(name = "bsCusMonthResult3", value = "三月不良品数（客户）")
    @Column
    protected int bsCusMonthResult3;

    /**
     * 四月不良品数（客户）
     */
    @ApiModelProperty(name = "bsCusMonthResult4", value = "四月不良品数（客户）")
    @Column
    protected int bsCusMonthResult4;

    /**
     * 五月不良品数（客户）
     */
    @ApiModelProperty(name = "bsCusMonthResult5", value = "五月不良品数（客户）")
    @Column
    protected int bsCusMonthResult5;

    /**
     * 六月不良品数（客户）
     */
    @ApiModelProperty(name = "bsCusMonthResult6", value = "六月不良品数（客户）")
    @Column
    protected int bsCusMonthResult6;

    /**
     * 七月不良品数（客户）
     */
    @ApiModelProperty(name = "bsCusMonthResult7", value = "七月不良品数（客户）")
    @Column
    protected int bsCusMonthResult7;

    /**
     * 八月不良品数（客户）
     */
    @ApiModelProperty(name = "bsCusMonthResult8", value = "八月不良品数（客户）")
    @Column
    protected int bsCusMonthResult8;

    /**
     * 九月不良品数（客户）
     */
    @ApiModelProperty(name = "bsCusMonthResult9", value = "九月不良品数（客户）")
    @Column
    protected int bsCusMonthResult9;

    /**
     * 十月不良品数（客户）
     */
    @ApiModelProperty(name = "bsCusMonthResult10", value = "十月不良品数（客户）")
    @Column
    protected int bsCusMonthResult10;

    /**
     * 十一月不良品数（客户）
     */
    @ApiModelProperty(name = "bsCusMonthResult11", value = "十一月不良品数（客户）")
    @Column
    protected int bsCusMonthResult11;

    /**
     * 十二月不良品数（客户）
     */
    @ApiModelProperty(name = "bsCusMonthResult12", value = "十二月不良品数（客户）")
    @Column
    protected int bsCusMonthResult12;

    public Long getBsPrId() {
        return bsPrId;
    }

    public void setBsPrId(Long bsPrId) {
        this.bsPrId = bsPrId;
    }

    public ProductInfo getProductBy() {
        return productBy;
    }

    public void setProductBy(ProductInfo productBy) {
        this.productBy = productBy;
    }

    public int getBsYear() {
        return bsYear;
    }

    public void setBsYear(int bsYear) {
        this.bsYear = bsYear;
    }

    public int getBsMonthResult1() {
        return bsMonthResult1;
    }

    public void setBsMonthResult1(int bsMonthResult1) {
        this.bsMonthResult1 = bsMonthResult1;
    }

    public int getBsMonthResult2() {
        return bsMonthResult2;
    }

    public void setBsMonthResult2(int bsMonthResult2) {
        this.bsMonthResult2 = bsMonthResult2;
    }

    public int getBsMonthResult3() {
        return bsMonthResult3;
    }

    public void setBsMonthResult3(int bsMonthResult3) {
        this.bsMonthResult3 = bsMonthResult3;
    }

    public int getBsMonthResult4() {
        return bsMonthResult4;
    }

    public void setBsMonthResult4(int bsMonthResult4) {
        this.bsMonthResult4 = bsMonthResult4;
    }

    public int getBsMonthResult5() {
        return bsMonthResult5;
    }

    public void setBsMonthResult5(int bsMonthResult5) {
        this.bsMonthResult5 = bsMonthResult5;
    }

    public int getBsMonthResult6() {
        return bsMonthResult6;
    }

    public void setBsMonthResult6(int bsMonthResult6) {
        this.bsMonthResult6 = bsMonthResult6;
    }

    public int getBsMonthResult7() {
        return bsMonthResult7;
    }

    public void setBsMonthResult7(int bsMonthResult7) {
        this.bsMonthResult7 = bsMonthResult7;
    }

    public int getBsMonthResult8() {
        return bsMonthResult8;
    }

    public void setBsMonthResult8(int bsMonthResult8) {
        this.bsMonthResult8 = bsMonthResult8;
    }

    public int getBsMonthResult9() {
        return bsMonthResult9;
    }

    public void setBsMonthResult9(int bsMonthResult9) {
        this.bsMonthResult9 = bsMonthResult9;
    }

    public int getBsMonthResult10() {
        return bsMonthResult10;
    }

    public void setBsMonthResult10(int bsMonthResult10) {
        this.bsMonthResult10 = bsMonthResult10;
    }

    public int getBsMonthResult11() {
        return bsMonthResult11;
    }

    public void setBsMonthResult11(int bsMonthResult11) {
        this.bsMonthResult11 = bsMonthResult11;
    }

    public int getBsMonthResult12() {
        return bsMonthResult12;
    }

    public void setBsMonthResult12(int bsMonthResult12) {
        this.bsMonthResult12 = bsMonthResult12;
    }

    public int getBsCusMonthResult1() {
        return bsCusMonthResult1;
    }

    public void setBsCusMonthResult1(int bsCusMonthResult1) {
        this.bsCusMonthResult1 = bsCusMonthResult1;
    }

    public int getBsCusMonthResult2() {
        return bsCusMonthResult2;
    }

    public void setBsCusMonthResult2(int bsCusMonthResult2) {
        this.bsCusMonthResult2 = bsCusMonthResult2;
    }

    public int getBsCusMonthResult3() {
        return bsCusMonthResult3;
    }

    public void setBsCusMonthResult3(int bsCusMonthResult3) {
        this.bsCusMonthResult3 = bsCusMonthResult3;
    }

    public int getBsCusMonthResult4() {
        return bsCusMonthResult4;
    }

    public void setBsCusMonthResult4(int bsCusMonthResult4) {
        this.bsCusMonthResult4 = bsCusMonthResult4;
    }

    public int getBsCusMonthResult5() {
        return bsCusMonthResult5;
    }

    public void setBsCusMonthResult5(int bsCusMonthResult5) {
        this.bsCusMonthResult5 = bsCusMonthResult5;
    }

    public int getBsCusMonthResult6() {
        return bsCusMonthResult6;
    }

    public void setBsCusMonthResult6(int bsCusMonthResult6) {
        this.bsCusMonthResult6 = bsCusMonthResult6;
    }

    public int getBsCusMonthResult7() {
        return bsCusMonthResult7;
    }

    public void setBsCusMonthResult7(int bsCusMonthResult7) {
        this.bsCusMonthResult7 = bsCusMonthResult7;
    }

    public int getBsCusMonthResult8() {
        return bsCusMonthResult8;
    }

    public void setBsCusMonthResult8(int bsCusMonthResult8) {
        this.bsCusMonthResult8 = bsCusMonthResult8;
    }

    public int getBsCusMonthResult9() {
        return bsCusMonthResult9;
    }

    public void setBsCusMonthResult9(int bsCusMonthResult9) {
        this.bsCusMonthResult9 = bsCusMonthResult9;
    }

    public int getBsCusMonthResult10() {
        return bsCusMonthResult10;
    }

    public void setBsCusMonthResult10(int bsCusMonthResult10) {
        this.bsCusMonthResult10 = bsCusMonthResult10;
    }

    public int getBsCusMonthResult11() {
        return bsCusMonthResult11;
    }

    public void setBsCusMonthResult11(int bsCusMonthResult11) {
        this.bsCusMonthResult11 = bsCusMonthResult11;
    }

    public int getBsCusMonthResult12() {
        return bsCusMonthResult12;
    }

    public void setBsCusMonthResult12(int bsCusMonthResult12) {
        this.bsCusMonthResult12 = bsCusMonthResult12;
    }
}
