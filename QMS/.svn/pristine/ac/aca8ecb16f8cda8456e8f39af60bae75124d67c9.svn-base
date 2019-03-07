package com.unind.qms.web.approved.service.internal;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.domain.admin.enumeration.EnableStateEnum;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BaseService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.context.SessionContextUtils;
import com.unind.qms.enumeration.ApprovedEnumConstants;
import com.unind.qms.enumeration.ApprovedStateEnum;
import com.unind.qms.enumeration.BasicStateEnum;
import com.unind.qms.web.approved.dao.ApprovedFlowDao;
import com.unind.qms.web.approved.dao.ApprovedFlowRecordDao;
import com.unind.qms.web.approved.dao.ApprovedItemsDao;
import com.unind.qms.web.approved.dao.ApprovedItemsRecordDao;
import com.unind.qms.web.approved.entity.*;
import com.unind.qms.web.approved.service.ApprovedFlowRecordService;
import com.unind.qms.web.basic.dao.TodoInfoDao;
import com.unind.qms.web.basic.entity.TodoInfo;
import com.unind.qms.web.product.dao.ProductInfoDao;
import com.unind.qms.web.product.entity.ProductInfo;
import com.unind.qms.web.supplier.dao.SupplierInfoDao;
import com.unind.qms.web.supplier.entity.SupplierInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import java.util.*;

@Service
@Transactional(rollbackFor = BusinessException.class)
public class ApprovedFlowRecordImpl extends BaseOprService implements ApprovedFlowRecordService {
    @Autowired
    private ApprovedFlowRecordDao approvedFlowRecordDao;
    @Autowired
    private ApprovedItemsDao approvedItemsDao;
    @Autowired
    private ApprovedItemsRecordDao approvedItemsRecordDao;
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private SupplierInfoDao supplierInfoDao;
    @Autowired
    private TodoInfoDao todoInfoDao;
    @Autowired
    private ApprovedFlowDao approvedFlowDao;

    @Transactional
    public ApiResponseResult add(ApprovedFlowRecord approvedFlowRecord, int bsType) throws BusinessException {
        if(null == approvedFlowRecord.getBsFlowId()){
            return ApiResponseResult.failure("流程ID不能为空");
        }
        if(null != approvedFlowRecord.getBsPrId()){//产品审核
            List<ApprovedFlowRecord> approvedFlowRecordList =approvedFlowRecordDao.findByBsPrIdAndAndBsStatus(approvedFlowRecord.getBsPrId());
            if(approvedFlowRecordList.size()==0){
                approvedFlowRecord.setBsStatus(ApprovedStateEnum.FLOW_IN.intValue());
                approvedFlowRecord.setBsResult(ApprovedStateEnum.RESULT_IN.intValue());

                approvedFlowRecord.setBsCreatedTime(new Date());
                approvedFlowRecord.setPkCreatedBy(SessionContextUtils.getCurrentUser().getId());
//                approvedFlowRecord.setPkCreatedBy(Long.parseLong("1"));
                approvedFlowRecordDao.save(approvedFlowRecord);

                if(bsType == ApprovedStateEnum.PR_APPROVED.intValue()){//产品过程审核
                    productInfoDao.updatebsProcessRecordIdById(approvedFlowRecord.getId(),approvedFlowRecord.getBsPrId());
                }else if(bsType == ApprovedStateEnum.PRED_APPROVED.intValue()){//成品检验
                    productInfoDao.updatebsProductRecordIdById(approvedFlowRecord.getId(),approvedFlowRecord.getBsPrId());
                }else{//供应商审核
                }

            }else{
                return ApiResponseResult.failure("该产品存在进行中的审批！");
            }
        }else if(null != approvedFlowRecord.getBsSuppId()){//供应商审核
            List<ApprovedFlowRecord> approvedFlowRecordList =approvedFlowRecordDao.findByBsSuppIdAndAndBsStatus(approvedFlowRecord.getBsSuppId());
            if(approvedFlowRecordList.size()==0){
                approvedFlowRecord.setBsStatus(ApprovedStateEnum.FLOW_IN.intValue());
                approvedFlowRecord.setBsResult(ApprovedStateEnum.RESULT_IN.intValue());

                approvedFlowRecord.setBsCreatedTime(new Date());
                approvedFlowRecord.setPkCreatedBy(SessionContextUtils.getCurrentUser().getId());
//                approvedFlowRecord.setPkCreatedBy(Long.parseLong("1"));
                approvedFlowRecordDao.save(approvedFlowRecord);

                supplierInfoDao.updateBsSuppRecordIdById(approvedFlowRecord.getId(), approvedFlowRecord.getBsSuppId());
            }else{
                return ApiResponseResult.failure("该供应商存在进行中的审批！");
            }
        }else{
            return ApiResponseResult.failure("请输入供应商ID或产品ID！");
        }

        //初始化待办事项
        TodoInfo todoInfo = new TodoInfo();

        //获取该流程记录
        ApprovedFlow approvedFlow = approvedFlowDao.findOne(approvedFlowRecord.getBsFlowId());
        //获取该流程所有项目
        List<ApprovedItems> approvedItemsList = approvedItemsDao.findByBsFlowId(approvedFlowRecord.getBsFlowId());
        for (ApprovedItems approvedItems:approvedItemsList) {
            ApprovedItemsRecord approvedItemsRecord = new ApprovedItemsRecord();
            approvedItemsRecord.setBsStatus(ApprovedStateEnum.ITEMS_START.intValue());
            if(approvedItems.getBsPriority()==1){
                approvedItemsRecord.setBsStatus(ApprovedStateEnum.ITEMS_IN.intValue());
            }
            approvedItemsRecord.setBsFlowRecordId(approvedFlowRecord.getId());
            approvedItemsRecord.setBsItemsId(approvedItems.getId());
            approvedItemsRecord.setBsPriority(approvedItems.getBsPriority());
            approvedItemsRecord.setBsStep(1);

            if(null != approvedFlowRecord.getBsPrId()){
                approvedItemsRecord.setBsPrId(approvedFlowRecord.getBsPrId());
            }else approvedItemsRecord.setBsSuppId(approvedFlowRecord.getBsSuppId());

            approvedItemsRecord.setBsCreatedTime(new Date());
            approvedItemsRecord.setPkCreatedBy(SessionContextUtils.getCurrentUser().getId());
//            approvedItemsRecord.setPkCreatedBy(Long.parseLong("1"));
            approvedItemsRecordDao.save(approvedItemsRecord);

            //当前为该流程的首个项目记录时
            if(approvedItems.getBsPriority()==1){
                //待办事项新增首个项目的首位审批人
                for(ApprovedRecorderMap recorderMap:approvedItems.getRecorderSet()){
                    if(recorderMap.getBsPriority()==1&&recorderMap.getBsIsDel()==0){
                        todoInfo.setBsUserId(recorderMap.getBsRecorderId());
                        todoInfo.setBsReferId(approvedItemsRecord.getBsFlowRecordId());
                        todoInfo.setBsExtend(approvedItemsRecord.getId());

                        if(bsType == ApprovedStateEnum.PR_APPROVED.intValue()||bsType == ApprovedStateEnum.PRED_APPROVED.intValue()){
                            todoInfo.setBsRouter("/qms/product/approved");
                        }else if(bsType == ApprovedStateEnum.SUPP_APPROVED.intValue()||bsType == ApprovedStateEnum.SUPPQUAL_APPROVED.intValue()){
                            todoInfo.setBsRouter("/qms/supplier/approved");
                        }else{
                            todoInfo.setBsRouter("Unknown");
                        }

                        if(bsType == ApprovedStateEnum.PR_APPROVED.intValue()){
                            todoInfo.setBsType(BasicStateEnum.TODO_PR.intValue());
                        }else if(bsType == ApprovedStateEnum.SUPP_APPROVED.intValue()||bsType == ApprovedStateEnum.SUPPQUAL_APPROVED.intValue()){
                            todoInfo.setBsType(BasicStateEnum.TODO_SUPP.intValue());
                        }else{
                            todoInfo.setBsType(BasicStateEnum.TODO_PR_PRO.intValue());
                        }

                        //获取供应商或产品信息
                        String entityName = "";
                        if(null != approvedItemsRecord.getBsPrId()){
                            ProductInfo productInfo = productInfoDao.findOne(approvedFlowRecord.getBsPrId());
                            entityName = productInfo.getBsPrName();
                        }else{
                            SupplierInfo supplierInfo = supplierInfoDao.findOne(approvedFlowRecord.getBsSuppId());
                            entityName = supplierInfo.getBsSuppChieseName();
                        }
                        todoInfo.setBsTitle("["+approvedFlow.getBsName()+"]"+entityName+"--"+approvedItems.getBsName());
                        todoInfo.setBsSystemType(BasicStateEnum.TODO_QMSTYPE.intValue());

                        todoInfo.setBsCreatedTime(new Date());
                        todoInfo.setPkCreatedBy(SessionContextUtils.getCurrentUser().getId());
                        todoInfoDao.save(todoInfo);
                        break;
                    }
                }
            }
        }
        return ApiResponseResult.success("新增成功！").data(approvedFlowRecord);
    }

    @Transactional
    public ApiResponseResult edit(Long id, String bsDesc, int bsResult) throws BusinessException {
        if (null == id) {
            return ApiResponseResult.failure("记录ID为必填项");
        }
        ApprovedFlowRecord o = approvedFlowRecordDao.findOne(id);
        if (null == o) {
            return ApiResponseResult.failure("记录ID不存在或已被删除");
        }
        //当前登录人是否为流程负责人
        if(!SessionContextUtils.getCurrentUser().getId().equals(o.getFlowBy().getBsApprovederId())){
            return ApiResponseResult.failure("请使用当前流程负责人账号！");
        }
        if(o.getBsStatus() != ApprovedStateEnum.FLOW_CONFIRM.intValue()){
            return ApiResponseResult.failure("该审批任务的项目记录完毕后才可审批流程记录！");
        }
        if(bsResult != ApprovedStateEnum.RESULT_PASS.intValue()&&bsResult != ApprovedStateEnum.RESULT_NOPASS.intValue()){
            return  ApiResponseResult.failure("无该审批结果！");
        }
        o.setBsStatus(ApprovedStateEnum.FLOW_CLOSE.intValue());
        o.setBsResult(bsResult);
        o.setBsDesc(bsDesc);

        o.setBsModifiedTime(new Date());
        o.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
        approvedFlowRecordDao.save(o);

        //关闭当前流程下的所有待办事项
        todoInfoDao.closeByBsAndBsReferId(id);

        //关闭正在审核状态
        if(null != o.getBsPrId()){
            productInfoDao.updatebsIsApproveById(o.getBsPrId());
            //结果通过且存在通过状态
            if(bsResult == ApprovedStateEnum.RESULT_PASS.intValue()||o.getFlowBy().getBsApprovedStatus() != 0){
                productInfoDao.updatebsStatusById(o.getBsPrId(),o.getFlowBy().getBsApprovedStatus());
            }
        }else{
            supplierInfoDao.updatebsIsApproveById(o.getBsSuppId());
            //结果通过且存在通过状态
            if(bsResult == ApprovedStateEnum.RESULT_PASS.intValue()||o.getFlowBy().getBsApprovedStatus() != 0){
                supplierInfoDao.updatebsStatusById(o.getBsSuppId(),String.valueOf(o.getFlowBy().getBsApprovedStatus()));
            }
        }

        return ApiResponseResult.success("提交确定成功！");
    }

    @Transactional
    public ApiResponseResult close(Long id, String bsDesc) throws BusinessException {
        if (null == id) {
            return ApiResponseResult.failure("记录ID为必填项");
        }
        ApprovedFlowRecord o = approvedFlowRecordDao.findOne(id);
        if (null == o) {
            return ApiResponseResult.failure("记录ID不存在或已被删除");
        }
        //当前登录人是否为流程负责人
        if(!SessionContextUtils.getCurrentUser().getId().equals(o.getFlowBy().getBsApprovederId())){
            return ApiResponseResult.failure("请使用当前流程负责人账号！");
        }
        o.setBsStatus(ApprovedStateEnum.FLOW_CLOSE.intValue());
        o.setBsResult(ApprovedStateEnum.RESULT_CLOSE.intValue());
        o.setBsDesc(bsDesc);

        o.setBsModifiedTime(new Date());
        o.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
        approvedFlowRecordDao.save(o);

        //关闭正在审核的项目记录
        approvedItemsRecordDao.closeByBsFlowRecordId(id);

        //关闭当前流程下的所有待办事项
        todoInfoDao.closeByBsAndBsReferId(id);

        productInfoDao.updatebsIsApproveById(o.getBsPrId());
        supplierInfoDao.updatebsIsApproveById(o.getBsSuppId());
        return ApiResponseResult.success("关闭成功！");
    }

    @Transactional(readOnly = true)
    public ApiResponseResult getlist(Long id, Long bsSuppId, Long bsPrId, PageRequest pageRequest) {
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BooleanStateEnum.FALSE.intValue()));
        if (null != id) {
            filters.add(new SearchFilter("id", SearchFilter.Operator.EQ, id));
        }else if(null != bsSuppId){
            filters.add(new SearchFilter("bsSuppId", SearchFilter.Operator.EQ, bsSuppId));
        }else if(null != bsPrId){
            filters.add(new SearchFilter("bsPrId", SearchFilter.Operator.EQ, bsPrId));
        }else{ }
        Specifications<ApprovedFlowRecord> spec = Specifications.where(super.and(filters, ApprovedFlowRecord.class));
        Page<ApprovedFlowRecord> page = approvedFlowRecordDao.findAll(spec, pageRequest);
        return ApiResponseResult.success().data(Datagrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
    }
}
