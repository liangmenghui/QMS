package com.unind.qms.web.shipment.service.internal;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BusinessException;
import com.unind.qms.enumeration.ApprovedStateEnum;
import com.unind.qms.web.approved.dao.ApprovedFlowDao;
import com.unind.qms.web.approved.entity.ApprovedFlow;
import com.unind.qms.web.approved.entity.ApprovedFlowRecord;
import com.unind.qms.web.approved.service.ApprovedFlowRecordService;
import com.unind.qms.web.product.dao.ProductInfoDao;
import com.unind.qms.web.product.entity.ProductInfo;
import com.unind.qms.web.shipment.dao.OrderInspectDao;
import com.unind.qms.web.shipment.entity.OrderInspect;
import com.unind.qms.web.shipment.service.OrderInspectService;
import com.unind.qms.web.shipment.service.ShipmentInspectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = BusinessException.class)
public class OrderInspectImpl extends BaseOprService implements OrderInspectService {
    @Autowired
    private OrderInspectDao orderInspectDao;
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private ApprovedFlowDao approvedFlowDao;
    @Autowired
    private ApprovedFlowRecordService approvedFlowRecordService;
    @Autowired
    private ShipmentInspectService shipmentInspectService;

    @Transactional
    public ApiResponseResult add(OrderInspect orderInspect) throws BusinessException {
        if (null == orderInspect || null == orderInspect.getBsPrCode()) {
            return ApiResponseResult.failure("产品编号为必填项");
        }
        if (null == orderInspect || null == orderInspect.getBsSuppCode()) {
            return ApiResponseResult.failure("供应商编号为必填项");
        }
        List<ProductInfo> productInfoList = productInfoDao.findByBsPrCodeAndBsSuppCodeAndBsIsDelOrderByIdDesc(orderInspect.getBsPrCode(), orderInspect.getBsSuppCode(),BooleanStateEnum.FALSE.intValue());
        if(productInfoList.size()<=0){
            return ApiResponseResult.failure("无该产品和供应商的订单");
        }
        orderInspect.setBsPrId(productInfoList.get(0).getId());
        //质检员
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("bsName",orderInspect.getBsCheckName());
        StringBuffer sqlbf1 = new StringBuffer();
        sqlbf1.append(" SELECT a.* FROM sys_user a");
        sqlbf1.append(" WHERE a.bs_name = :bsName and a.bs_is_del = '0'");
        Map<String, Object> userMap1 = super.findOneBySql(sqlbf1.toString(), param);
        if(null != userMap1){
            orderInspect.setBsCheckId(Long.valueOf(userMap1.get("ID").toString()));
        }
        //供应商质量工程师
        param = new HashMap<String, Object>();
        param.put("bsName",orderInspect.getBsSqeName());
        Map<String, Object> userMap2 = super.findOneBySql(sqlbf1.toString(), param);
        if(null != userMap2){
            orderInspect.setBsSqeId(Long.valueOf(userMap2.get("ID").toString()));
        }
        orderInspect.setBsCreatedTime(new Date());
        orderInspectDao.save(orderInspect);

        //创建成品检验审核
        if(null != orderInspect.getBsCheckId() && null != orderInspect.getBsSqeId()){
            ApprovedFlowRecord approvedFlowRecord = new ApprovedFlowRecord();
            //获取成品检验的流程信息
            ApprovedFlow approvedFlow = approvedFlowDao.findByBsTypeAndBsIsDel(ApprovedStateEnum.PRED_APPROVED.intValue(),0).get(0);

            approvedFlowRecord.setBsFlowId(approvedFlow.getId());
            approvedFlowRecord.setBsPrId(orderInspect.getBsPrId());
            //审批流
            String recorderStr = orderInspect.getBsCheckId() + "," + orderInspect.getBsSqeId();

            ApiResponseResult result = approvedFlowRecordService.add(approvedFlowRecord, ApprovedStateEnum.PRED_APPROVED.intValue(), recorderStr);
            shipmentInspectService.add(orderInspect.getId(), approvedFlowRecord.getId());
        }else{
            logger.error("无该质检员和供应商质量工程师的用户账号!");
            return ApiResponseResult.failure("无该质检员和供应商质量工程师的用户账号!");
        }
        return ApiResponseResult.success("新增成功！");
    }

    @Transactional
    public ApiResponseResult addAgain(Long id) throws BusinessException {
        if (null == id) {
            return ApiResponseResult.failure("传入参数为空！");
        }
        OrderInspect orderInspect = orderInspectDao.findOne(id);

        //创建成品检验审核
        if(null != orderInspect.getBsCheckId() && null != orderInspect.getBsSqeId()){
            ApprovedFlowRecord approvedFlowRecord = new ApprovedFlowRecord();
            //获取成品检验的流程信息
            ApprovedFlow approvedFlow = approvedFlowDao.findByBsTypeAndBsIsDel(ApprovedStateEnum.PRED_APPROVED.intValue(),0).get(0);

            approvedFlowRecord.setBsFlowId(approvedFlow.getId());
            approvedFlowRecord.setBsPrId(orderInspect.getBsPrId());
            //审批流
            String recorderStr = orderInspect.getBsCheckId() + "," + orderInspect.getBsSqeId();

            ApiResponseResult result = approvedFlowRecordService.add(approvedFlowRecord, ApprovedStateEnum.PRED_APPROVED.intValue(), recorderStr);
            shipmentInspectService.add(orderInspect.getId(), approvedFlowRecord.getId());
        }else{
            logger.error("无该质检员和供应商质量工程师的用户账号!");
            return ApiResponseResult.failure("无该质检员和供应商质量工程师的用户账号!");
        }
        return ApiResponseResult.success("新增成功！");
    }
}
