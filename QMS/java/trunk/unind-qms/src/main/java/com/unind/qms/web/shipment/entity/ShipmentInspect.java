package com.unind.qms.web.shipment.entity;

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
import java.util.Set;

/**
 * @author chen
 */
@Entity
@Table(name = ShipmentInspect.TABLE_NAME)
@DynamicUpdate
@ApiModel
public class ShipmentInspect extends BaseEntity {
    private static final long serialVersionUID = 7151771262953316256L;
    public static final String TABLE_NAME = "t_shipment_inspect";

    /**
     * 产品ID
     */
    @ApiModelProperty(name="bsPrId",required=true,value="产品ID")
    @NotNull
    @Column
    protected Long bsPrId;

    /**
     * 审核流程记录ID
     */
    @ApiModelProperty(name="bsFlowRecordId",required=true,value="审核流程记录ID")
    @NotNull
    @Column
    protected Long bsFlowRecordId;

    /**
     * 验货计划ID
     */
    @ApiModelProperty(name="bsOrderId",required=true,value="验货计划ID")
//    @NotNull
    @Column
    protected Long bsOrderId;

    /**
     * 检验标准
     */
    @ApiModelProperty(name="bsInspectStandard",value="检验标准")
    @Column(length = 50)
    protected String bsInspectStandard;

    /**
     * 检验水平
     */
    @ApiModelProperty(name="bsInspectLevel",value="检验水平")
    @Column(length = 20)
    protected String bsInspectLevel;

    /**
     * 生产日期
     */
    @ApiModelProperty(name="bsProductDate",value="生产日期")
    @Column
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    protected Date bsProductDate;

    /**
     * 验货日期
     */
    @ApiModelProperty(name="bsInspectDate",value="验货日期")
    @Column
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    protected Date bsInspectDate;

    /**
     * 客户
     */
    @ApiModelProperty(name="bsCustomer",value="客户")
    @Column(length = 500)
    protected String bsCustomer;

    /**
     * 合同号
     */
    @ApiModelProperty(name="bsContractNo",value="合同号")
    @Column(length = 50)
    protected String bsContractNo;

    /**
     * 批次号
     */
    @ApiModelProperty(name="bsBatchNo",value="批次号")
    @Column(length = 50)
    protected String bsBatchNo;

    /**
     * AQL水平
     */
    @ApiModelProperty(name="bsAqlLevel",value="AQL水平")
    @Column(length = 50)
    protected String bsAqlLevel;

    /**
     * 产品数量
     */
    @ApiModelProperty(name="bsProductNum",value="产品数量")
    @Column
    protected int bsProductNum;

    /**
     * 箱子数量
     */
    @ApiModelProperty(name="bsBoxesNum",value="箱子数量")
    @Column
    protected int bsBoxesNum;

    /**
     * 接受数
     */
    @ApiModelProperty(name="bsAcceptNum",value="接受数")
    @Column
    protected int bsAcceptNum;

    /**
     * 不接受数
     */
    @ApiModelProperty(name="bsRejectNum",value="不接受数")
    @Column
    protected int bsRejectNum;

    /**
     * 抽样数
     */
    @ApiModelProperty(name="bsSamplesNum",value="抽样数")
    @Column
    protected int bsSamplesNum;

    /**
     * 样品ID
     */
    @ApiModelProperty(name="bsSampleId",value="样品ID")
    @Column
    protected Long bsSampleId;

    //-----------------------------包装检查
    /**
     * 包装方式
     */
    @ApiModelProperty(name="bsPackWay",value="包装方式")
    @Column(length = 50)
    protected String bsPackWay;

    /**
     * 密封方式
     */
    @ApiModelProperty(name="bsSealWay",value="密封方式")
    @Column(length = 50)
    protected String bsSealWay;

    /**
     * 标签
     */
    @ApiModelProperty(name="bsPackTag",value="标签")
    @Column(length = 50)
    protected String bsPackTag;

    /**
     * 包装外观
     */
    @ApiModelProperty(name="bsPackAppearance",value="包装外观")
    @Column(length = 100)
    protected String bsPackAppearance;

    /**
     * 每栈板数量
     */
    @ApiModelProperty(name="bsPackStackNum",value="每栈板数量")
    @Column
    protected int bsPackStackNum;

    /**
     * 每箱数量
     */
    @ApiModelProperty(name="bsPackBoxNum",value="每箱数量")
    @Column
    protected int bsPackBoxNum;

    //---------------------------出货检验报告检验结果
    /**
     * 检验结果
     */
    @ApiModelProperty(name="bsInspectResult",value="检验结果")
    @Column
    protected int bsInspectResult;

    /**
     * 检验说明
     */
    @ApiModelProperty(name="bsInspectDesc",value="检验说明")
    @Column(length = 500)
    protected String bsInspectDesc;

    /**
     * 个人意见
     */
    @ApiModelProperty(name="bsPersonalAdvise",value="个人意见")
    @Column(length = 500)
    protected String bsPersonalAdvise;

    /**
     * 公司意见
     */
    @ApiModelProperty(name="bsCompanyAdvise",value="公司意见")
    @Column(length = 500)
    protected String bsCompanyAdvise;

    /**
     * 备注
     */
    @ApiModelProperty(name="bsRemark",value="备注")
    @Column(length = 500)
    protected String bsRemark;

    //出货检验记录集合
    @OneToMany(mappedBy = "shipmentInspectBy")
    protected Set<ShipmentInspectRecord> recordSet;

    /**
     * 原材料厂家和出厂日期
     */
    @ApiModelProperty(name="bsPrInfoFile",value="原材料厂家和出厂日期")
    @Column
    protected Long bsPrInfoFile;

    /**
     * 原材料厂家和出厂日期是否通过
     */
    @ApiModelProperty(name="bsIsPrInfo",value="原材料厂家和出厂日期是否通过")
    @Column
    protected int bsIsPrInfo;

    /**
     * 原材料成分
     */
    @ApiModelProperty(name="bsPrCompositionFile",value="原材料成分")
    @Column
    protected Long bsPrCompositionFile;

    /**
     * 原材料成分是否通过
     */
    @ApiModelProperty(name="bsIsPrComposition",value="原材料成分是否通过")
    @Column
    protected int bsIsPrComposition;

    /**
     * 原材料和性能
     */
    @ApiModelProperty(name="bsPrPerformanceFile",value="原材料和性能")
    @Column
    protected Long bsPrPerformanceFile;

    /**
     * 原材料和性能是否通过
     */
    @ApiModelProperty(name="bsIsPerformance",value="原材料和性能是否通过")
    @Column
    protected int bsIsPerformance;

    /**
     * 性能测试设备
     */
    @ApiModelProperty(name="bsTestDeviceFile",value="性能测试设备")
    @Column
    protected Long bsTestDeviceFile;

    /**
     * 性能测试设备是否通过
     */
    @ApiModelProperty(name="bsIsTestDevice",value="性能测试设备是否通过")
    @Column
    protected int bsIsTestDevice;

    /**
     * 性能测试时间
     */
    @ApiModelProperty(name="bsTestDeviceDate",value="性能测试时间")
    @Column
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    protected Date bsTestDeviceDate;

    /**
     * 性能测试结果
     */
    @ApiModelProperty(name="bsTestResultFile",value="性能测试结果")
    @Column
    protected Long bsTestResultFile;

    /**
     * 性能测试结果是否通过
     */
    @ApiModelProperty(name="bsIsTestResult",value="性能测试结果是否通过")
    @Column
    protected int bsIsTestResult;

    /**
     * 性能测试设备1
     */
    @ApiModelProperty(name="bsTestDeviceFile1",value="性能测试设备1")
    @Column
    protected Long bsTestDeviceFile1;

    /**
     * 性能测试设备是否通过1
     */
    @ApiModelProperty(name="bsIsTestDevice1",value="性能测试设备是否通过1")
    @Column
    protected int bsIsTestDevice1;

    /**
     * 性能测试时间1
     */
    @ApiModelProperty(name="bsTestDeviceDate1",value="性能测试时间1")
    @Column
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    protected Date bsTestDeviceDate1;

    /**
     * 性能测试结果1
     */
    @ApiModelProperty(name="bsTestResultFile1",value="性能测试结果1")
    @Column
    protected Long bsTestResultFile1;

    /**
     * 性能测试结果是否通过1
     */
    @ApiModelProperty(name="bsIsTestResult1",value="性能测试结果是否通过1")
    @Column
    protected int bsIsTestResult1;

    /**
     * 性能测试设备2
     */
    @ApiModelProperty(name="bsTestDeviceFile2",value="性能测试设备2")
    @Column
    protected Long bsTestDeviceFile2;

    /**
     * 性能测试设备是否通过2
     */
    @ApiModelProperty(name="bsIsTestDevice2",value="性能测试设备是否通过2")
    @Column
    protected int bsIsTestDevice2;

    /**
     * 性能测试时间2
     */
    @ApiModelProperty(name="bsTestDeviceDate2",value="性能测试时间2")
    @Column
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+08:00")
    protected Date bsTestDeviceDate2;

    /**
     * 性能测试结果2
     */
    @ApiModelProperty(name="bsTestResultFile2",value="性能测试结果2")
    @Column
    protected Long bsTestResultFile2;

    /**
     * 性能测试结果是否通过2
     */
    @ApiModelProperty(name="bsIsTestResult2",value="性能测试结果是否通过2")
    @Column
    protected int bsIsTestResult2;

    /**
     * 包装方式
     */
    @ApiModelProperty(name="bsPackWayFile",value="包装方式")
    @Column
    protected Long bsPackWayFile;

    /**
     * 包装方式是否通过
     */
    @ApiModelProperty(name="bsIsPackWay",value="包装方式是否通过")
    @Column
    protected int bsIsPackWay;

    /**
     * 密封方式
     */
    @ApiModelProperty(name="bsSealWayFile",value="密封方式")
    @Column
    protected Long bsSealWayFile;

    /**
     * 密封方式是否通过
     */
    @ApiModelProperty(name="bsIsSealWay",value="密封方式是否通过")
    @Column
    protected int bsIsSealWay;

    /**
     * 标签
     */
    @ApiModelProperty(name="bsPackTagFile",value="标签")
    @Column
    protected Long bsPackTagFile;

    /**
     * 标签是否通过
     */
    @ApiModelProperty(name="bsIsPackTag",value="标签是否通过")
    @Column
    protected int bsIsPackTag;

    /**
     * 包装外观
     */
    @ApiModelProperty(name="bsPackAppearanceFile",value="包装外观")
    @Column
    protected Long bsPackAppearanceFile;

    /**
     * 包装外观是否通过
     */
    @ApiModelProperty(name="bsIsPackAppearance",value="包装外观是否通过")
    @Column
    protected int bsIsPackAppearance;

    /**
     * 每栈板数量
     */
    @ApiModelProperty(name="bsPackStackFile",value="每栈板数量")
    @Column
    protected Long bsPackStackFile;

    /**
     * 每栈板数量是否通过
     */
    @ApiModelProperty(name="bsIsPackStack",value="每栈板数量是否通过")
    @Column
    protected int bsIsPackStack;

    /**
     * 每箱数量
     */
    @ApiModelProperty(name="bsPackBoxFile",value="每箱数量")
    @Column
    protected Long bsPackBoxFile;

    /**
     * 每箱数量是否通过
     */
    @ApiModelProperty(name="bsIsPackBox",value="每箱数量是否通过")
    @Column
    protected int bsIsPackBox;

    /**
     * 抽样检验不合格数量
     */
    @ApiModelProperty(name="bsSampleResult",value="抽样检验不合格数量")
    @Column
    protected int bsSampleResult;

    //文件集合
    @OneToMany(mappedBy = "shipmentInspect")
    protected Set<ShipmentInspectFile> fileSet;

    /**
     * 创建人
     */
    @ApiModelProperty(name="pkCreatedBy",hidden=true,value="创建人")
    @Column
    protected Long pkCreatedBy;

    /**
     * 修改人
     */
    @ApiModelProperty(name="pkModifiedBy",hidden=true,value="修改人")
    @Column
    protected Long pkModifiedBy;

    @ApiModelProperty(name="createdBy",hidden=true,value="创建人--user")
    @ManyToOne
    @JoinColumn(name = "pkCreatedBy", insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected SysUser createdBy;

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

    public Long getBsFlowRecordId() {
        return bsFlowRecordId;
    }

    public void setBsFlowRecordId(Long bsFlowRecordId) {
        this.bsFlowRecordId = bsFlowRecordId;
    }

    public Long getBsOrderId() {
        return bsOrderId;
    }

    public void setBsOrderId(Long bsOrderId) {
        this.bsOrderId = bsOrderId;
    }

    public String getBsInspectStandard() {
        return bsInspectStandard;
    }

    public void setBsInspectStandard(String bsInspectStandard) {
        this.bsInspectStandard = bsInspectStandard;
    }

    public String getBsInspectLevel() {
        return bsInspectLevel;
    }

    public void setBsInspectLevel(String bsInspectLevel) {
        this.bsInspectLevel = bsInspectLevel;
    }

    public Date getBsProductDate() {
        return bsProductDate;
    }

    public void setBsProductDate(Date bsProductDate) {
        this.bsProductDate = bsProductDate;
    }

    public Date getBsInspectDate() {
        return bsInspectDate;
    }

    public void setBsInspectDate(Date bsInspectDate) {
        this.bsInspectDate = bsInspectDate;
    }

    public String getBsCustomer() {
        return bsCustomer;
    }

    public void setBsCustomer(String bsCustomer) {
        this.bsCustomer = bsCustomer;
    }

    public String getBsContractNo() {
        return bsContractNo;
    }

    public void setBsContractNo(String bsContractNo) {
        this.bsContractNo = bsContractNo;
    }

    public String getBsBatchNo() {
        return bsBatchNo;
    }

    public void setBsBatchNo(String bsBatchNo) {
        this.bsBatchNo = bsBatchNo;
    }

    public String getBsAqlLevel() {
        return bsAqlLevel;
    }

    public void setBsAqlLevel(String bsAqlLevel) {
        this.bsAqlLevel = bsAqlLevel;
    }

    public int getBsProductNum() {
        return bsProductNum;
    }

    public void setBsProductNum(int bsProductNum) {
        this.bsProductNum = bsProductNum;
    }

    public int getBsBoxesNum() {
        return bsBoxesNum;
    }

    public void setBsBoxesNum(int bsBoxesNum) {
        this.bsBoxesNum = bsBoxesNum;
    }

    public int getBsAcceptNum() {
        return bsAcceptNum;
    }

    public void setBsAcceptNum(int bsAcceptNum) {
        this.bsAcceptNum = bsAcceptNum;
    }

    public int getBsRejectNum() {
        return bsRejectNum;
    }

    public void setBsRejectNum(int bsRejectNum) {
        this.bsRejectNum = bsRejectNum;
    }

    public int getBsSamplesNum() {
        return bsSamplesNum;
    }

    public void setBsSamplesNum(int bsSamplesNum) {
        this.bsSamplesNum = bsSamplesNum;
    }

    public Long getBsSampleId() {
        return bsSampleId;
    }

    public void setBsSampleId(Long bsSampleId) {
        this.bsSampleId = bsSampleId;
    }

    public String getBsPackWay() {
        return bsPackWay;
    }

    public void setBsPackWay(String bsPackWay) {
        this.bsPackWay = bsPackWay;
    }

    public String getBsSealWay() {
        return bsSealWay;
    }

    public void setBsSealWay(String bsSealWay) {
        this.bsSealWay = bsSealWay;
    }

    public String getBsPackTag() {
        return bsPackTag;
    }

    public void setBsPackTag(String bsPackTag) {
        this.bsPackTag = bsPackTag;
    }

    public String getBsPackAppearance() {
        return bsPackAppearance;
    }

    public void setBsPackAppearance(String bsPackAppearance) {
        this.bsPackAppearance = bsPackAppearance;
    }

    public int getBsPackStackNum() {
        return bsPackStackNum;
    }

    public void setBsPackStackNum(int bsPackStackNum) {
        this.bsPackStackNum = bsPackStackNum;
    }

    public int getBsPackBoxNum() {
        return bsPackBoxNum;
    }

    public void setBsPackBoxNum(int bsPackBoxNum) {
        this.bsPackBoxNum = bsPackBoxNum;
    }

    public int getBsInspectResult() {
        return bsInspectResult;
    }

    public void setBsInspectResult(int bsInspectResult) {
        this.bsInspectResult = bsInspectResult;
    }

    public String getBsInspectDesc() {
        return bsInspectDesc;
    }

    public void setBsInspectDesc(String bsInspectDesc) {
        this.bsInspectDesc = bsInspectDesc;
    }

    public String getBsPersonalAdvise() {
        return bsPersonalAdvise;
    }

    public void setBsPersonalAdvise(String bsPersonalAdvise) {
        this.bsPersonalAdvise = bsPersonalAdvise;
    }

    public String getBsCompanyAdvise() {
        return bsCompanyAdvise;
    }

    public void setBsCompanyAdvise(String bsCompanyAdvise) {
        this.bsCompanyAdvise = bsCompanyAdvise;
    }

    public String getBsRemark() {
        return bsRemark;
    }

    public void setBsRemark(String bsRemark) {
        this.bsRemark = bsRemark;
    }

    public Set<ShipmentInspectRecord> getRecordSet() {
        return recordSet;
    }

    public void setRecordSet(Set<ShipmentInspectRecord> recordSet) {
        this.recordSet = recordSet;
    }

    public Long getBsPrInfoFile() {
        return bsPrInfoFile;
    }

    public void setBsPrInfoFile(Long bsPrInfoFile) {
        this.bsPrInfoFile = bsPrInfoFile;
    }

    public int getBsIsPrInfo() {
        return bsIsPrInfo;
    }

    public void setBsIsPrInfo(int bsIsPrInfo) {
        this.bsIsPrInfo = bsIsPrInfo;
    }

    public Long getBsPrCompositionFile() {
        return bsPrCompositionFile;
    }

    public void setBsPrCompositionFile(Long bsPrCompositionFile) {
        this.bsPrCompositionFile = bsPrCompositionFile;
    }

    public int getBsIsPrComposition() {
        return bsIsPrComposition;
    }

    public void setBsIsPrComposition(int bsIsPrComposition) {
        this.bsIsPrComposition = bsIsPrComposition;
    }

    public Long getBsPrPerformanceFile() {
        return bsPrPerformanceFile;
    }

    public void setBsPrPerformanceFile(Long bsPrPerformanceFile) {
        this.bsPrPerformanceFile = bsPrPerformanceFile;
    }

    public int getBsIsPerformance() {
        return bsIsPerformance;
    }

    public void setBsIsPerformance(int bsIsPerformance) {
        this.bsIsPerformance = bsIsPerformance;
    }

    public Long getBsTestDeviceFile() {
        return bsTestDeviceFile;
    }

    public void setBsTestDeviceFile(Long bsTestDeviceFile) {
        this.bsTestDeviceFile = bsTestDeviceFile;
    }

    public int getBsIsTestDevice() {
        return bsIsTestDevice;
    }

    public void setBsIsTestDevice(int bsIsTestDevice) {
        this.bsIsTestDevice = bsIsTestDevice;
    }

    public Date getBsTestDeviceDate() {
        return bsTestDeviceDate;
    }

    public void setBsTestDeviceDate(Date bsTestDeviceDate) {
        this.bsTestDeviceDate = bsTestDeviceDate;
    }

    public Long getBsTestResultFile() {
        return bsTestResultFile;
    }

    public void setBsTestResultFile(Long bsTestResultFile) {
        this.bsTestResultFile = bsTestResultFile;
    }

    public int getBsIsTestResult() {
        return bsIsTestResult;
    }

    public void setBsIsTestResult(int bsIsTestResult) {
        this.bsIsTestResult = bsIsTestResult;
    }

    public Long getBsTestDeviceFile1() {
        return bsTestDeviceFile1;
    }

    public void setBsTestDeviceFile1(Long bsTestDeviceFile1) {
        this.bsTestDeviceFile1 = bsTestDeviceFile1;
    }

    public int getBsIsTestDevice1() {
        return bsIsTestDevice1;
    }

    public void setBsIsTestDevice1(int bsIsTestDevice1) {
        this.bsIsTestDevice1 = bsIsTestDevice1;
    }

    public Date getBsTestDeviceDate1() {
        return bsTestDeviceDate1;
    }

    public void setBsTestDeviceDate1(Date bsTestDeviceDate1) {
        this.bsTestDeviceDate1 = bsTestDeviceDate1;
    }

    public Long getBsTestResultFile1() {
        return bsTestResultFile1;
    }

    public void setBsTestResultFile1(Long bsTestResultFile1) {
        this.bsTestResultFile1 = bsTestResultFile1;
    }

    public int getBsIsTestResult1() {
        return bsIsTestResult1;
    }

    public void setBsIsTestResult1(int bsIsTestResult1) {
        this.bsIsTestResult1 = bsIsTestResult1;
    }

    public Long getBsTestDeviceFile2() {
        return bsTestDeviceFile2;
    }

    public void setBsTestDeviceFile2(Long bsTestDeviceFile2) {
        this.bsTestDeviceFile2 = bsTestDeviceFile2;
    }

    public int getBsIsTestDevice2() {
        return bsIsTestDevice2;
    }

    public void setBsIsTestDevice2(int bsIsTestDevice2) {
        this.bsIsTestDevice2 = bsIsTestDevice2;
    }

    public Date getBsTestDeviceDate2() {
        return bsTestDeviceDate2;
    }

    public void setBsTestDeviceDate2(Date bsTestDeviceDate2) {
        this.bsTestDeviceDate2 = bsTestDeviceDate2;
    }

    public Long getBsTestResultFile2() {
        return bsTestResultFile2;
    }

    public void setBsTestResultFile2(Long bsTestResultFile2) {
        this.bsTestResultFile2 = bsTestResultFile2;
    }

    public int getBsIsTestResult2() {
        return bsIsTestResult2;
    }

    public void setBsIsTestResult2(int bsIsTestResult2) {
        this.bsIsTestResult2 = bsIsTestResult2;
    }

    public Long getBsPackWayFile() {
        return bsPackWayFile;
    }

    public void setBsPackWayFile(Long bsPackWayFile) {
        this.bsPackWayFile = bsPackWayFile;
    }

    public int getBsIsPackWay() {
        return bsIsPackWay;
    }

    public void setBsIsPackWay(int bsIsPackWay) {
        this.bsIsPackWay = bsIsPackWay;
    }

    public Long getBsSealWayFile() {
        return bsSealWayFile;
    }

    public void setBsSealWayFile(Long bsSealWayFile) {
        this.bsSealWayFile = bsSealWayFile;
    }

    public int getBsIsSealWay() {
        return bsIsSealWay;
    }

    public void setBsIsSealWay(int bsIsSealWay) {
        this.bsIsSealWay = bsIsSealWay;
    }

    public Long getBsPackTagFile() {
        return bsPackTagFile;
    }

    public void setBsPackTagFile(Long bsPackTagFile) {
        this.bsPackTagFile = bsPackTagFile;
    }

    public int getBsIsPackTag() {
        return bsIsPackTag;
    }

    public void setBsIsPackTag(int bsIsPackTag) {
        this.bsIsPackTag = bsIsPackTag;
    }

    public Long getBsPackAppearanceFile() {
        return bsPackAppearanceFile;
    }

    public void setBsPackAppearanceFile(Long bsPackAppearanceFile) {
        this.bsPackAppearanceFile = bsPackAppearanceFile;
    }

    public int getBsIsPackAppearance() {
        return bsIsPackAppearance;
    }

    public void setBsIsPackAppearance(int bsIsPackAppearance) {
        this.bsIsPackAppearance = bsIsPackAppearance;
    }

    public Long getBsPackStackFile() {
        return bsPackStackFile;
    }

    public void setBsPackStackFile(Long bsPackStackFile) {
        this.bsPackStackFile = bsPackStackFile;
    }

    public int getBsIsPackStack() {
        return bsIsPackStack;
    }

    public void setBsIsPackStack(int bsIsPackStack) {
        this.bsIsPackStack = bsIsPackStack;
    }

    public Long getBsPackBoxFile() {
        return bsPackBoxFile;
    }

    public void setBsPackBoxFile(Long bsPackBoxFile) {
        this.bsPackBoxFile = bsPackBoxFile;
    }

    public int getBsIsPackBox() {
        return bsIsPackBox;
    }

    public void setBsIsPackBox(int bsIsPackBox) {
        this.bsIsPackBox = bsIsPackBox;
    }

    public int getBsSampleResult() {
        return bsSampleResult;
    }

    public void setBsSampleResult(int bsSampleResult) {
        this.bsSampleResult = bsSampleResult;
    }

    public Set<ShipmentInspectFile> getFileSet() {
        return fileSet;
    }

    public void setFileSet(Set<ShipmentInspectFile> fileSet) {
        this.fileSet = fileSet;
    }

    public Long getPkCreatedBy() {
        return pkCreatedBy;
    }

    public void setPkCreatedBy(Long pkCreatedBy) {
        this.pkCreatedBy = pkCreatedBy;
    }

    public Long getPkModifiedBy() {
        return pkModifiedBy;
    }

    public void setPkModifiedBy(Long pkModifiedBy) {
        this.pkModifiedBy = pkModifiedBy;
    }

    public SysUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(SysUser createdBy) {
        this.createdBy = createdBy;
    }

    public SysUser getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(SysUser modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
