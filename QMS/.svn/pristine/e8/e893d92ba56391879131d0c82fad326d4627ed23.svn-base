package com.unind.qms.provider;

import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.context.SessionContextUtils;
import com.unind.qms.web.basic.entity.FeedbackInfo;
import com.unind.qms.web.product.dao.ProductInfoDao;
import com.unind.qms.web.product.entity.ProductInfo;
import com.unind.qms.web.risk.dao.ProductRiskDao;
import com.unind.qms.web.risk.dao.SupplierRiskDao;
import com.unind.qms.web.risk.entity.ProductRisk;
import com.unind.qms.web.risk.entity.SupplierRisk;
import com.unind.qms.web.supplier.dao.SupplierInfoDao;
import com.unind.qms.web.supplier.entity.SupplierInfo;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Aspect
@Component
public class AspectService {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private SupplierInfoDao supplierInfoDao;
    @Autowired
    private ProductRiskDao productRiskDao;
    @Autowired
    private SupplierRiskDao supplierRiskDao;

    @After(value = "execution(public * com.unind.qms.web.basic.controller.FeedbackInfoController.add(..)) && args(feedbackInfo,fileIdStr)")
    @Transactional
    public void updateRisk(FeedbackInfo feedbackInfo, String fileIdStr) throws Exception{
        logger.info("客诉相关供应商和产品直升高风险开始！");
        //1.修改供应商风险信息表
        List<SearchFilter> filtersSupp = new ArrayList<SearchFilter>();
        filtersSupp.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BooleanStateEnum.FALSE.intValue()));
        filtersSupp.add(new SearchFilter("bsSuppId", SearchFilter.Operator.EQ, feedbackInfo.getBsSuppId()));
        Specifications<SupplierRisk> specSupp = Specifications.where(BaseOprService.and(filtersSupp, SupplierRisk.class));
        SupplierRisk supplierRisk = supplierRiskDao.findOne(specSupp);
        if(supplierRisk != null){
            supplierRisk.setBsFeedbackType(5);
            supplierRisk.setBsRiskLevel(5);
            supplierRisk.setBsModifiedTime(new Date());
            supplierRisk.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
            supplierRiskDao.save(supplierRisk);
            //2.修改供应商信息表
            SupplierInfo supplierInfo = supplierInfoDao.findOne(feedbackInfo.getBsSuppId());
            if(supplierInfo != null){
                supplierInfo.setBsRiskLevelSys(5);
                supplierInfo.setBsRiskTimeSys(new Date());
                supplierInfoDao.save(supplierInfo);
            }
        }
        //3.修改产品风险信息表
        if(feedbackInfo.getBsPrId() != null){
            List<SearchFilter> filtersPro = new ArrayList<SearchFilter>();
            filtersPro.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BooleanStateEnum.FALSE.intValue()));
            filtersPro.add(new SearchFilter("bsPrId", SearchFilter.Operator.EQ, feedbackInfo.getBsPrId()));
            Specifications<ProductRisk> specPro = Specifications.where(BaseOprService.and(filtersPro, ProductRisk.class));
            ProductRisk productRisk = productRiskDao.findOne(specPro);
            if(productRisk != null){
                productRisk.setBsFeedbackType(5);
                productRisk.setBsRiskLevel(5);
                productRisk.setBsModifiedTime(new Date());
                productRisk.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
                productRiskDao.save(productRisk);
                //4.修改产品信息表
                ProductInfo productInfo = productInfoDao.findOne(feedbackInfo.getBsPrId());
                if(productInfo != null){
                    productInfo.setBsRiskLevelSys(5);
                    productInfo.setBsRiskTimeSys(new Date());
                    productInfoDao.save(productInfo);
                }
            }
        }
        logger.info("客诉相关供应商和产品直升高风险结束！");
    }

}
