package com.unind.qms.web.risk.service.internal;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.BaseEntity;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.context.SessionContextUtils;
import com.unind.qms.web.product.dao.ProductInfoDao;
import com.unind.qms.web.product.entity.ProductInfo;
import com.unind.qms.web.risk.dao.ProductRiskDao;
import com.unind.qms.web.risk.dao.SupplierRiskDao;
import com.unind.qms.web.risk.entity.ProductRisk;
import com.unind.qms.web.risk.entity.SupplierRisk;
import com.unind.qms.web.risk.service.SupplierRiskService;
import com.unind.qms.web.shipment.entity.ShipmentInspect;
import com.unind.qms.web.supplier.dao.SupplierInfoDao;
import com.unind.qms.web.supplier.entity.SupplierInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 供应商风险管理信息
 * @author Shen
 */
@Service
@Transactional(rollbackFor = BusinessException.class)
public class SupplierRiskImpl extends BaseOprService implements SupplierRiskService {

    @Autowired
    private SupplierRiskDao supplierRiskDao;
    @Autowired
    private SupplierInfoDao supplierInfoDao;
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private ProductRiskDao productRiskDao;

    /**
     * 添加供应商风险管理信息
     * @param supplierRisk
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional
    public ApiResponseResult add(SupplierRisk supplierRisk) throws BusinessException {
        if(null == supplierRisk || null == supplierRisk.getBsSuppId()){
            return ApiResponseResult.failure("供应商ID为必填项");
        }
        int count = supplierRiskDao.countByBsSuppId(supplierRisk.getBsSuppId());
        if(count > 0){
            return ApiResponseResult.failure("供应商已存在");
        }
        //1.添加创建时间
        supplierRisk.setBsCreatedTime(new Date());

        //2.添加type值
        //所有得分类型默认为1
        supplierRisk.setBsFeedbackType((supplierRisk.getBsFeedbackType() == 0) ? 1 : supplierRisk.getBsFeedbackType());
        supplierRisk.setBsPpmType((supplierRisk.getBsPpmType() == 0) ? 1 : supplierRisk.getBsPpmType());
        supplierRisk.setBsDeliveryType((supplierRisk.getBsDeliveryType() == 0) ? 1 : supplierRisk.getBsDeliveryType());
        supplierRisk.setBsInspectType((supplierRisk.getBsInspectType() == 0) ? 1 : supplierRisk.getBsInspectType());
        supplierRisk.setBsPaymentType((supplierRisk.getBsPaymentType() == 0) ? 1 : supplierRisk.getBsPaymentType());
        supplierRisk.setBsCpkType((supplierRisk.getBsCpkType() == 0) ? 1 : supplierRisk.getBsCpkType());
        supplierRisk.setBsDangerProType((supplierRisk.getBsDangerProType() == 0) ? 1 : supplierRisk.getBsDangerProType());
        supplierRisk.setBsApproveType((supplierRisk.getBsApproveType() == 0) ? 1 : supplierRisk.getBsApproveType());
        supplierRisk.setBsEhsType((supplierRisk.getBsEhsType() == 0) ? 1 : supplierRisk.getBsEhsType());

        //3.添加value值，并修改对应type值
        supplierRisk = updateValueType(supplierRisk);

        supplierRiskDao.save(supplierRisk);
        return ApiResponseResult.success("新增成功！").data(supplierRisk);
    }

    /**
     * 修改供应商风险管理信息
     * @param supplierRisk
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional
    public ApiResponseResult edit(SupplierRisk supplierRisk) throws BusinessException {
        if(null == supplierRisk || null == supplierRisk.getId()){
            return ApiResponseResult.failure("记录ID为必填项");
        }
        if(null == supplierRisk.getBsSuppId()){
            return ApiResponseResult.failure("供应商ID为必填项");
        }
        SupplierRisk o = supplierRiskDao.findOne(supplierRisk.getId());
        if(null == o){
            return ApiResponseResult.failure("记录ID不存在或已被删除");
        }
        //1.添加修改时间及修改人
        supplierRisk.setBsCreatedTime(o.getBsCreatedTime());
        supplierRisk.setBsVersion(o.getBsVersion());
        supplierRisk.setBsIsDel(o.getBsIsDel());
        supplierRisk.setBsModifiedTime(new Date());
        supplierRisk.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());

        //2.添加value值，并修改对应type值
        supplierRisk = updateValueType(supplierRisk);

        //3.判断直升高风险类型是否达到高风险（type为5）,满足要求直升高风险
        if(supplierRisk.getBsDeliveryType() == 5){
            supplierRisk.setBsRiskLevel(5);   //风险等级
            //3.1修改供应商信息表
            SupplierInfo supplierInfo2 = supplierInfoDao.findOne(supplierRisk.getBsSuppId());
            supplierInfo2.setBsRiskLevelSys(5);   //风险等级（系统）
            supplierInfo2.setBsRiskTimeSys(new Date());
            supplierInfoDao.save(supplierInfo2);
        }

        supplierRiskDao.save(supplierRisk);
        return ApiResponseResult.success("修改成功！").data(supplierRisk);
    }

    /**
     * 删除供应商风险管理信息
     * @param id
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional
    public ApiResponseResult delete(Long id) throws BusinessException {
        SupplierRisk o = supplierRiskDao.findOne(id);
        if(null == o){
            return ApiResponseResult.failure("记录ID不存在或已被删除");
        }
        if (o.getId() <= 0) {
            return ApiResponseResult.failure("没有操作权限");
        }
        o.setBsIsDel(BooleanStateEnum.TRUE.intValue());
        supplierRiskDao.save(o);
        return ApiResponseResult.success("删除成功！");
    }

    /**
     * 获取供应商风险管理信息
     * @param bsSuppId
     * @param pageRequest
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional(readOnly = true)
    public ApiResponseResult getlist(Long bsSuppId, PageRequest pageRequest) throws BusinessException {
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BaseEnumConstants.FALSE));
        if(bsSuppId != null){
            filters.add(new SearchFilter("bsSuppId", SearchFilter.Operator.EQ, bsSuppId));
        }
        Specifications<SupplierRisk> spec = Specifications.where(super.and(filters, SupplierRisk.class));
        Page<SupplierRisk> page = supplierRiskDao.findAll(spec, pageRequest);
        return ApiResponseResult.success().data(Datagrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
    }

    /**
     * 更新产品风险信息表的value及type
     * @param supplierRisk
     * @return
     * @throws BusinessException
     */
    public SupplierRisk updateValueType(SupplierRisk supplierRisk) throws BusinessException{
        //供应商的ppm值取该供应商下的所有产品的ppm值之和
        //供应商cpk值取该供应商下的所有产品的最大cpk值
        //供应商验货不合格次数取该供应商下所有产品之和
        //供应商高风险产品数量
        double ppmValue = 0;
        double cpkValue = 0;
        double inspectValue = 0;
        int dangerProCount = 0;
        boolean isTriple = false; //是否连续三次验货不合格
        //1.通过suppId从供应商信息表找到suppCode
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BooleanStateEnum.FALSE.intValue()));
        filters.add(new SearchFilter("id", SearchFilter.Operator.EQ, supplierRisk.getBsSuppId()));
        Specification<SupplierInfo> spec = Specifications.where(super.and(filters, SupplierInfo.class));
        SupplierInfo supplierInfo = supplierInfoDao.findOne(spec);
        if(supplierInfo != null){
            //2.通过suppCode从产品信息表找到供应商对应的多个产品prId
            List<ProductInfo> productInfoList = productInfoDao.findByBsIsDelAndBsSuppCode(BooleanStateEnum.FALSE.intValue(), supplierInfo.getBsSuppCode());
            if(productInfoList.size() > 0){
                for(ProductInfo productInfo : productInfoList){
                    //3.通过prId从产品风险信息表找到ppm值，并相加
                    //比较cpk值，得到最大值
                    //所有不合格次数之和
                    //高风险产品数量
                    List<ProductRisk> productRiskList = productRiskDao.findByBsIsDelAndBsPrId(BooleanStateEnum.FALSE.intValue(), productInfo.getId());
                    if(productInfoList.size() > 0){
                        for(int i = 0; i < productRiskList.size(); i++){
                            ppmValue = ppmValue + productRiskList.get(i).getBsPpmValue();  //ppm值
                            if(productRiskList.get(i).getBsCpkValue() > cpkValue){         //cpk值
                                cpkValue = productRiskList.get(i).getBsCpkValue();
                            }
                            inspectValue = inspectValue + productRiskList.get(i).getBsInspectValue();  //验货不合格次数
                            if(productRiskList.get(i).getBsInspectType() == 5){
                                isTriple = true;
                            }
                            if(productRiskList.get(i).getBsRiskLevel() == 5){
                                dangerProCount++;
                            }
                        }
                    }
                    //4修改value和type
                    //4.1修改ppm的value和type
                    supplierRisk.setBsPpmValue(ppmValue);
                    if(ppmValue <= 50){
                        supplierRisk.setBsPpmType(1);
                    }
                    if(ppmValue > 50 && ppmValue <= 200){
                        supplierRisk.setBsPpmType(2);
                    }
                    if(ppmValue > 200 && ppmValue <=500){
                        supplierRisk.setBsPpmType(3);
                    }
                    if(ppmValue > 500 && ppmValue <= 1000){
                        supplierRisk.setBsPpmType(4);
                    }
                    if(ppmValue > 1000){
                        supplierRisk.setBsPpmType(5);
                    }
                    //4.2修改cpk的value和type
                    supplierRisk.setBsCpkValue(cpkValue);
                    if(cpkValue < 1){
                        supplierRisk.setBsCpkType(5);
                    }
                    if(cpkValue >= 1 && cpkValue < 1.33){
                        supplierRisk.setBsCpkType(4);
                    }
                    if(cpkValue >= 1.33){
                        supplierRisk.setBsCpkType(3);
                    }
                    if(cpkValue >= 1.33 && cpkValue <= 1.67){
                        supplierRisk.setBsCpkType(2);
                    }
                    if(cpkValue >= 1.67){
                        supplierRisk.setBsCpkType(1);
                    }
                    //4.3修改不合格次数的value和type
                    supplierRisk.setBsInspectValue(inspectValue);
                    if(inspectValue == 0){
                        supplierRisk.setBsInspectType(1);
                    }
                    if(inspectValue == 1){
                        supplierRisk.setBsInspectType(2);
                    }
                    if(inspectValue >= 2){
                        supplierRisk.setBsInspectType(3);
                    }
                    if(inspectValue >= 3){
                        supplierRisk.setBsInspectType(4);
                    }
                    if(isTriple){  //连续发生三次以上
                        supplierRisk.setBsInspectType(5);
                    }
                    //4.4修改高风险产品占比的value和type
                    supplierRisk.setBsDangerProCount(dangerProCount);
                    if(dangerProCount == 0){
                        supplierRisk.setBsDangerProType(1);
                    }
                    if(dangerProCount == 1){
                        supplierRisk.setBsDangerProType(2);
                    }
                    if(dangerProCount == 1){
                        supplierRisk.setBsDangerProType(3);
                    }
                    if(dangerProCount == 2){
                        supplierRisk.setBsDangerProType(4);
                    }
                    if(dangerProCount == 3){
                        supplierRisk.setBsDangerProType(5);
                    }
                }
            }
        }

        return supplierRisk;
    }

}
