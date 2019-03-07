package com.unind.qms.provider;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.context.SessionContextUtils;
import com.unind.qms.web.basic.entity.FeedbackInfo;
import com.unind.qms.web.basic.entity.FeedbackInfoExtra;
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
import org.springframework.data.domain.Sort;
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
    private SupplierInfoDao supplierInfoDao;
    @Autowired
    private ProductRiskDao productRiskDao;
    @Autowired
    private SupplierRiskDao supplierRiskDao;
    @Autowired
    private ProductInfoDao productInfoDao;

    @After(value = "execution(public * com.unind.qms.web.basic.controller.FeedbackInfoController.add(..)) && args(feedbackInfo,fileIdStr,refundIdStr,feedbackInfoExtraStr)")
    @Transactional
    public void updateRisk(FeedbackInfo feedbackInfo, String fileIdStr, String refundIdStr, String feedbackInfoExtraStr) throws Exception{
        logger.info("客诉相关供应商和产品直升高风险开始！");
        //获取当前客诉下的所有供应商ID和产品ID
        Sort sort = new Sort(Sort.Direction.DESC, "bsCreatedTime");
        Gson gson = new Gson();
        List<FeedbackInfoExtra> feedbackInfoExtraList = gson.fromJson(feedbackInfoExtraStr, new TypeToken<List<FeedbackInfoExtra>>(){}.getType());
        for(int i = 0; i < feedbackInfoExtraList.size(); i++){
            FeedbackInfoExtra feedbackInfoExtra = feedbackInfoExtraList.get(i);

            //1.修改供应商风险信息表
            List<SearchFilter> filtersSupp = new ArrayList<SearchFilter>();
            filtersSupp.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BooleanStateEnum.FALSE.intValue()));
            filtersSupp.add(new SearchFilter("bsSuppId", SearchFilter.Operator.EQ, feedbackInfoExtra.getBsSuppId()));
            Specifications<SupplierRisk> specSupp = Specifications.where(BaseOprService.and(filtersSupp, SupplierRisk.class));
            List<SupplierRisk> supplierRiskList = supplierRiskDao.findAll(specSupp, sort);
            if(supplierRiskList.size() > 0){
                SupplierRisk supplierRisk = supplierRiskList.get(0);
                supplierRisk.setBsFeedbackType(5);    //客诉类型
                supplierRisk.setBsRiskLevel(5);       //风险等级
                supplierRisk.setBsModifiedTime(new Date());
                supplierRisk.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
                supplierRiskDao.save(supplierRisk);
                //2.修改供应商信息表
                SupplierInfo supplierInfo = supplierInfoDao.findOne(feedbackInfoExtra.getBsSuppId());
                if(supplierInfo != null){
                    supplierInfo.setBsRiskLevel(5);   //风险等级
                    supplierInfo.setBsRiskLevelSys(5); //系统风险等级
                    supplierInfo.setBsRiskTimeSys(new Date());
                    supplierInfo.setBsModifiedTime(new Date());
                    supplierInfo.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
                    supplierInfoDao.save(supplierInfo);
                }
            }
            //3.修改产品风险信息表
            if(feedbackInfoExtra.getBsPrId() != null){
                List<SearchFilter> filtersPro = new ArrayList<SearchFilter>();
                filtersPro.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BooleanStateEnum.FALSE.intValue()));
                filtersPro.add(new SearchFilter("bsPrId", SearchFilter.Operator.EQ, feedbackInfoExtra.getBsPrId()));
                Specifications<ProductRisk> specPro = Specifications.where(BaseOprService.and(filtersPro, ProductRisk.class));
                List<ProductRisk> productRiskList = productRiskDao.findAll(specPro, sort);
                if(productRiskList.size() > 0){
                    ProductRisk productRisk = productRiskList.get(0);
                    productRisk.setBsFeedbackType(5);   //客诉类型
                    productRisk.setBsRiskLevel(5);      //风险等级
                    productRisk.setBsModifiedTime(new Date());
                    productRisk.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
                    productRiskDao.save(productRisk);
                    //4.修改产品信息表
                    ProductInfo productInfo = productInfoDao.findOne(feedbackInfoExtra.getBsPrId());
                    if(productInfo != null){
                        productInfo.setBsRiskLevel(5);   //风险等级
                        productInfo.setBsRiskLevelSys(5); //系统风险等级
                        productInfo.setBsRiskTimeSys(new Date());
                        productInfo.setBsModifiedTime(new Date());
                        productInfo.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
                        productInfoDao.save(productInfo);
                    }
                }
            }
        }
        logger.info("客诉相关供应商和产品直升高风险结束！");
    }

}
