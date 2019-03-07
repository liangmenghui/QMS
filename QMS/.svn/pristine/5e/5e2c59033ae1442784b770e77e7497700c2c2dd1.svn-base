package com.unind.qms.web.approved.service.internal;

import com.unind.base.components.mail.MailSenderService;
import com.unind.base.configure.AppConfig;
import com.unind.base.dao.admin.SysUserDao;
import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.domain.admin.user.SysUser;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.context.SessionContextUtils;
import com.unind.qms.enumeration.ApprovedStateEnum;
import com.unind.qms.enumeration.BasicStateEnum;
import com.unind.qms.web.approved.dao.*;
import com.unind.qms.web.approved.entity.*;
import com.unind.qms.web.approved.service.ApprovedItemsRecordService;
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
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional(rollbackFor = BusinessException.class)
public class ApprovedItemsRecordImpl extends BaseOprService implements ApprovedItemsRecordService {
    @Autowired
    private ApprovedItemsRecordDao approvedItemsRecordDao;
    @Autowired
    private ApprovedRecorderMapDao approvedRecorderMapDao;
    @Autowired
    private ApprovedFlowRecordDao approvedFlowRecordDao;
//    @Autowired
//    private ApprovedItemsResultDao approvedItemsResultDao;
    @Autowired
    private TodoInfoDao todoInfoDao;
//    @Autowired
//    private ApprovedTermsScoreDao approvedTermsScoreDao;
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private SupplierInfoDao supplierInfoDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private AppConfig appConfig;

//    @Transactional
//    public ApiResponseResult approved(Long bsItemsId, Long bsItemsRecordId, int bsResult, String resultDesc, String itemsDesc, Integer prodResult) throws BusinessException {
//        //当前项目记录
//        ApprovedItemsRecord approvedItemsRecord = approvedItemsRecordDao.findOne(bsItemsRecordId);
//        //当前项目是否不存在或者正在进行中
//        if(null == approvedItemsRecord || ApprovedStateEnum.ITEMS_IN.intValue() != approvedItemsRecord.getBsStatus()){
//            return ApiResponseResult.failure("当前项目不存在或者不在进行中！");
//        }
//        //当前审批人
//        ApprovedRecorderMap approvedRecorderMap = approvedRecorderMapDao.findByBsItemsRecordIdAndBsPriority(bsItemsRecordId, approvedItemsRecord.getBsStep()).get(0);
//        //当前登录人是否为当前审批人
//        if(!SessionContextUtils.getCurrentUser().getId().equals(approvedRecorderMap.getBsRecorderId())){
//            return ApiResponseResult.failure("请使用当前审批人账号！");
//        }
//
//        //初始化待办事项
//        TodoInfo todoInfo = new TodoInfo();
//        StringBuffer nextTodoItemsName = new StringBuffer();
//        nextTodoItemsName.append("--"+approvedItemsRecord.getItemsBy().getBsName());
//
//        //项目通过,且当成品检验不是不可接受时
//        if(bsResult == ApprovedStateEnum.ITEMS_RESULT_PASS.intValue()) {
//            //成品检验时，核查结果不是不可接受，才可操作项目记录表
//            if (prodResult != 2) {
//                //是否为项目最后审批人
//                int isLastRecorder = approvedItemsRecordDao.countByBsItemsRecordId(bsItemsRecordId);
//                //是项目最后审批人,或成品检验为可接受时
//                if (isLastRecorder > 0 || prodResult == 1) {
//                    //计算条款总得分
//    //                approvedItemsRecord.setBsScoreNum(approvedTermsScoreDao.findScoreNumByBsItemsRecoreId(bsItemsRecordId));
//
//                    //当前进行中的项目数量
//                    int isApproving = approvedItemsRecordDao.countByBsStatusAndBsFlowRecordId(1, approvedItemsRecord.getBsFlowRecordId());
//                    //当前未开始的项目数量
//                    int isApproveStart = approvedItemsRecordDao.countByBsStatusAndBsFlowRecordId(0, approvedItemsRecord.getBsFlowRecordId());
//
//                    //项目记录通过
//                    approvedItemsRecord.setBsStatus(ApprovedStateEnum.ITEMS_PASS.intValue());
//
//                    //下一项目为汇总项目
//                    if (isApproving == 1 && isApproveStart == 1) {
//                        //获取最后的汇总项目
//                        ApprovedItemsRecord nextApprovedItemsRecord = approvedItemsRecordDao.findByBsStatusAndBsFlowRecordId(0, approvedItemsRecord.getBsFlowRecordId());
//                        nextApprovedItemsRecord.setBsStatus(ApprovedStateEnum.ITEMS_IN.intValue());
//                        nextApprovedItemsRecord.setBsCreatedTime(new Date());
//                        nextApprovedItemsRecord.setBsModifiedTime(new Date());
//                        nextApprovedItemsRecord.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
//                        approvedItemsRecordDao.save(nextApprovedItemsRecord);
//
//                        //待办事项新增汇总项目的首位审批人
//                        nextTodoItemsName.setLength(0);
//                        nextTodoItemsName.append("--" + nextApprovedItemsRecord.getItemsBy().getBsName());
//                        for (ApprovedRecorderMap recorderMap : nextApprovedItemsRecord.getRecorderSet()) {
//                            if (recorderMap.getBsPriority() == 1 && recorderMap.getBsIsDel() == 0) {
//                                todoInfo.setBsUserId(recorderMap.getBsRecorderId());
//                                todoInfo.setBsReferId(nextApprovedItemsRecord.getBsFlowRecordId());
//                                todoInfo.setBsExtend(nextApprovedItemsRecord.getId());
//                                break;
//                            }
//                        }
//                    } else if (isApproveStart == 0) {//汇总项目进行中，则流程记录待确认，进行中，待办事项新增流程负责人
//                        ApprovedFlowRecord approvedFlowRecord = approvedFlowRecordDao.findOne(approvedItemsRecord.getBsFlowRecordId());
//                        approvedFlowRecord.setBsStatus(ApprovedStateEnum.FLOW_CONFIRM.intValue());//待确认
//                        approvedFlowRecord.setBsResult(ApprovedStateEnum.RESULT_IN.intValue());//进行中
//                        approvedFlowRecord.setBsModifiedTime(new Date());
//                        approvedFlowRecord.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
//
//                        //待办事项新增流程负责人
//                        nextTodoItemsName.setLength(0);
//                        nextTodoItemsName.append("--审核结果确认");
//                        todoInfo.setBsUserId(approvedFlowRecord.getFlowBy().getBsApprovederId());
//                        todoInfo.setBsReferId(approvedFlowRecord.getId());
//
//                        approvedFlowRecordDao.save(approvedFlowRecord);
//                    } else {
//                    }
//                } else {//不是项目最后审批人
//                    approvedItemsRecord.setBsStep(approvedItemsRecord.getBsStep() + 1);//项目步骤+1
//
//                    //待办事项新增当前项目的下一位审批人
//                    nextTodoItemsName.setLength(0);
//                    nextTodoItemsName.append("--" + approvedItemsRecord.getItemsBy().getBsName());
//                    for (ApprovedRecorderMap recorderMap : approvedItemsRecord.getRecorderSet()) {
//                        if (recorderMap.getBsPriority() == (approvedItemsRecord.getBsStep()) && recorderMap.getBsIsDel() == 0) {
//                            todoInfo.setBsUserId(recorderMap.getBsRecorderId());
//                            todoInfo.setBsReferId(approvedItemsRecord.getBsFlowRecordId());
//                            todoInfo.setBsExtend(approvedItemsRecord.getId());
//                            break;
//                        }
//                    }
//
//                }
//            }
//        }else if(bsResult == ApprovedStateEnum.ITEMS_RESULT_BACK.intValue()){//驳回
//            if(approvedItemsRecord.getBsStep()>1){
//                approvedItemsRecord.setBsStep(approvedItemsRecord.getBsStep()-1);//项目步骤-1
//
//                //待办事项新增当前项目的上一位审批人
//                for(ApprovedRecorderMap recorderMap:approvedItemsRecord.getRecorderSet()){
//                    if(recorderMap.getBsPriority()==(approvedItemsRecord.getBsStep())&&recorderMap.getBsIsDel()==0){
//                        todoInfo.setBsUserId(recorderMap.getBsRecorderId());
//                        todoInfo.setBsReferId(approvedItemsRecord.getBsFlowRecordId());
//                        todoInfo.setBsExtend(approvedItemsRecord.getId());
//                        break;
//                    }
//                }
//
//            }else{
//                return ApiResponseResult.failure("第一步审批人无法执行驳回操作！");
//            }
//        }else if(bsResult == ApprovedStateEnum.ITEMS_RESULT_NOPASS.intValue()){//不通过
//            //项目记录不通过
//            approvedItemsRecord.setBsStatus(ApprovedStateEnum.ITEMS_NOPASS.intValue());
//
//            ApprovedFlowRecord approvedFlowRecord = approvedFlowRecordDao.findOne(approvedItemsRecord.getBsFlowRecordId());
//            approvedFlowRecord.setBsStatus(ApprovedStateEnum.FLOW_CONFIRM.intValue());//待确认
//            approvedFlowRecord.setBsResult(ApprovedStateEnum.RESULT_IN.intValue());//进行中
//            approvedFlowRecord.setBsModifiedTime(new Date());
//            approvedFlowRecord.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
//
//            //待办事项新增流程负责人
//            nextTodoItemsName.setLength(0);
//            nextTodoItemsName.append("--审核结果确认");
//            todoInfo.setBsUserId(approvedFlowRecord.getFlowBy().getBsApprovederId());
//            todoInfo.setBsReferId(approvedFlowRecord.getId());
//
//            approvedFlowRecordDao.save(approvedFlowRecord);
//
//            //计算条款总得分
////            approvedItemsRecord.setBsScoreNum(approvedTermsScoreDao.findScoreNumByBsItemsRecoreId(bsItemsRecordId));
//
//            //获取流程记录
////            ApprovedFlowRecord approvedFlowRecord = approvedFlowRecordDao.findOne(approvedItemsRecord.getBsFlowRecordId());
//
//            //返回当前项目记录后的项目记录
////            List<ApprovedItemsRecord> approvedItemsRecordList = approvedItemsRecordDao.findByBsFlowRecordIdAndBsPriority(approvedItemsRecord.getBsFlowRecordId(),approvedItemsRecord.getBsPriority());
//            //如果流程允许让步且存在下一项目记录,则下一项目进行中
////            if(approvedFlowRecord.getFlowBy().getBsCanYield() == 1 && approvedItemsRecordList.size()>0){
////                ApprovedItemsRecord nextApprovedItemsRecord = approvedItemsRecordList.get(0);
////                nextApprovedItemsRecord.setBsStatus(ApprovedStateEnum.ITEMS_IN.intValue());
////                nextApprovedItemsRecord.setBsCreatedTime(new Date());
////                nextApprovedItemsRecord.setBsModifiedTime(new Date());
////                nextApprovedItemsRecord.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
////
////                //待办事项新增下一项目的首位审批人
////                nextTodoItemsName.setLength(0);
////                nextTodoItemsName.append("--"+nextApprovedItemsRecord.getItemsBy().getBsName());
////                for(ApprovedRecorderMap recorderMap:nextApprovedItemsRecord.getItemsBy().getRecorderSet()){
////                    if(recorderMap.getBsPriority()==1&&recorderMap.getBsIsDel()==0){
////                        todoInfo.setBsUserId(recorderMap.getBsRecorderId());
////                        todoInfo.setBsReferId(nextApprovedItemsRecord.getBsFlowRecordId());
////                        todoInfo.setBsExtend(nextApprovedItemsRecord.getId());
////                        break;
////                    }
////                }
////                approvedItemsRecordDao.save(nextApprovedItemsRecord);
////            }else{//流程不允许让步或者不存在下一项目记录，则流程记录待确认，进行中，待办事项新增流程负责人
//////                ApprovedFlowRecord approvedFlowRecord = approvedFlowRecordDao.findOne(approvedItemsRecord.getBsFlowRecordId());
////                approvedFlowRecord.setBsStatus(ApprovedStateEnum.FLOW_CONFIRM.intValue());//待确认
////                approvedFlowRecord.setBsResult(ApprovedStateEnum.RESULT_IN.intValue());//进行中
////                approvedFlowRecord.setBsModifiedTime(new Date());
////                approvedFlowRecord.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
////
////                //待办事项新增流程负责人
////                nextTodoItemsName.setLength(0);
////                nextTodoItemsName.append("--审核结果确认");
////                todoInfo.setBsUserId(approvedFlowRecord.getFlowBy().getBsApprovederId());
////                todoInfo.setBsReferId(approvedFlowRecord.getId());
////
////                approvedFlowRecordDao.save(approvedFlowRecord);
////            }
//        }else{
//            return ApiResponseResult.failure("传入参数为空!");
//        }
//
//        //关闭当前审批人待办事项
//        todoInfoDao.closeByBsUserIdAndBsExtend(approvedRecorderMap.getBsRecorderId(),approvedItemsRecord.getId());
//        //新增下一位审批人待办事项
//        if(null != todoInfo.getBsUserId()) {
//            //审批类型
//            int bsType = approvedItemsRecord.getItemsBy().getBsType();
//            if (bsType == ApprovedStateEnum.PR_APPROVED.intValue()) {
//                todoInfo.setBsType(BasicStateEnum.TODO_PR.intValue());
//                todoInfo.setBsRouter("/qms/product/approved");
//            } else if (bsType == ApprovedStateEnum.SUPP_APPROVED.intValue() || bsType == ApprovedStateEnum.SUPPQUAL_APPROVED.intValue()) {
//                todoInfo.setBsType(BasicStateEnum.TODO_SUPP.intValue());
//                todoInfo.setBsRouter("/qms/supplier/approved");
//            } else {
//                todoInfo.setBsType(BasicStateEnum.TODO_PR_PRO.intValue());
//                todoInfo.setBsRouter("/qms/product/approved");
//            }
//            todoInfo.setBsCreatedTime(new Date());
//            todoInfo.setPkCreatedBy(SessionContextUtils.getCurrentUser().getId());
//
//            //获取流程
//            Map<String, Object> param = new HashMap<String, Object>();
//            param.put("bsFlowId", approvedItemsRecord.getItemsBy().getBsFlowId());
//            StringBuffer sqlbf1 = new StringBuffer();
//            sqlbf1.append(" SELECT * FROM "+ ApprovedFlow.TABLE_NAME+" a WHERE a.id=:bsFlowId");
//            List<Map<String, Object>> flowList = super.findBySql(sqlbf1.toString(), param);
//
//            //获取供应商或产品信息
//            List<Map<String, Object>> entityList = new ArrayList<Map<String, Object>>();
//            String entityName = "";
//            if (null != approvedItemsRecord.getBsPrId()) {
//                param = new HashMap<String, Object>();
//                param.put("bsPrId", approvedItemsRecord.getBsPrId());
//                sqlbf1 = new StringBuffer();
//                sqlbf1.append(" SELECT * FROM "+ ProductInfo.TABLE_NAME+" a WHERE a.id = :bsPrId");
//                entityList = super.findBySql(sqlbf1.toString(), param);
//                entityName = entityList.get(0).get("BS_PR_NAME").toString();
//            } else {
//                param = new HashMap<String, Object>();
//                param.put("bsSuppId", approvedItemsRecord.getBsSuppId());
//                sqlbf1 = new StringBuffer();
//                sqlbf1.append(" SELECT * FROM "+ SupplierInfo.TABLE_NAME+" a WHERE a.id = :bsSuppId");
//                entityList = super.findBySql(sqlbf1.toString(), param);
//                entityName = entityList.get(0).get("BS_SUPP_CHIESE_NAME").toString();
//            }
//
//            todoInfo.setBsTitle("[" + flowList.get(0).get("BS_NAME") + "]" + entityName + nextTodoItemsName.toString());
//            todoInfo.setBsSystemType(BasicStateEnum.TODO_QMSTYPE.intValue());
//            todoInfoDao.save(todoInfo);
//        }
//        //保存当前项目记录
//        approvedItemsRecord.setBsDesc(itemsDesc);
//        approvedItemsRecord.setBsModifiedTime(new Date());
//        approvedItemsRecord.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
//        approvedItemsRecordDao.save(approvedItemsRecord);
//
//        //保存当前审批结果
//        ApprovedItemsResultService approvedItemsResult = new ApprovedItemsResultService();
//        approvedItemsResult.setBsItemsRecordId(bsItemsRecordId);
//        approvedItemsResult.setBsRecorderId(SessionContextUtils.getCurrentUser().getId());
//        approvedItemsResult.setBsResult(bsResult);
//        approvedItemsResult.setBsDesc(resultDesc);
//        approvedItemsResult.setBsCreatedTime(new Date());
//        approvedItemsResultDao.save(approvedItemsResult);
//        return ApiResponseResult.success("审批成功！");
//    }

    @Transactional
    public ApiResponseResult approved(Long bsFlowRecordId, int bsResult, Integer bsConclusion) throws BusinessException {
        Sort sort = new Sort(Sort.Direction.ASC,"bsPriority");

        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("bsFlowRecordId", SearchFilter.Operator.EQ, bsFlowRecordId));
        Specifications<ApprovedItemsRecord> spec = Specifications.where(super.and(filters, ApprovedItemsRecord.class));

        List<ApprovedItemsRecord> approvedItemsRecordList = approvedItemsRecordDao.findAll(spec, sort);

        //第一个项目记录
        ApprovedItemsRecord approvedItemsRecord = approvedItemsRecordList.get(0);
        //项目是否不存在或者正在进行中
        if(null == approvedItemsRecord || ApprovedStateEnum.ITEMS_IN.intValue() != approvedItemsRecord.getBsStatus()){
            return ApiResponseResult.failure("当前项目不存在或者不在进行中！");
        }
        //当前审批人
        ApprovedRecorderMap approvedRecorderMap = approvedRecorderMapDao.findByBsItemsRecordIdAndBsPriority(approvedItemsRecord.getId(), approvedItemsRecord.getBsStep()).get(0);
        //当前登录人是否为当前审批人
        if(!SessionContextUtils.getCurrentUser().getId().equals(approvedRecorderMap.getBsRecorderId())){
            return ApiResponseResult.failure("请使用当前审批人账号！");
        }

        //初始化待办事项
        TodoInfo todoInfo = new TodoInfo();

        for(ApprovedItemsRecord approvedItemsRecord1:approvedItemsRecordList) {
            //项目通过
            if (bsResult == ApprovedStateEnum.ITEMS_RESULT_PASS.intValue()) {
                //是否为项目最后审批人
                int isLastRecorder = approvedItemsRecordDao.countByBsItemsRecordId(approvedItemsRecord1.getId());
                //是项目最后审批人,或成品检验为可接受时
                if (isLastRecorder > 0 || bsConclusion == ApprovedStateEnum.CONCLUSION_PRODUCT_ACCEPT.intValue()) {
                    //项目记录通过
                    approvedItemsRecord1.setBsStatus(ApprovedStateEnum.ITEMS_PASS.intValue());

                    //流程记录通过，关闭
                    ApprovedFlowRecord approvedFlowRecord = approvedFlowRecordDao.findOne(approvedItemsRecord1.getBsFlowRecordId());
                    approvedFlowRecord.setBsStatus(ApprovedStateEnum.FLOW_CLOSE.intValue());//关闭
                    approvedFlowRecord.setBsResult(ApprovedStateEnum.RESULT_PASS.intValue());//通过
                    approvedFlowRecord.setBsConclusion(bsConclusion);
                    approvedFlowRecord.setBsModifiedTime(new Date());
                    approvedFlowRecord.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());

                    approvedFlowRecordDao.save(approvedFlowRecord);
                    //关闭产品和供应商的审核中状态
                    if(null != approvedFlowRecord.getBsPrId()){
                        //该产品仍在审核（成品检验）
                        int approvedCount =approvedFlowRecordDao.countByBsPrIdAndBsType(approvedFlowRecord.getBsPrId(), approvedFlowRecord.getFlowBy().getBsType());
                        if(approvedCount == 0){
                            productInfoDao.updatebsIsApproveById(approvedFlowRecord.getBsPrId());
                        }
                    }else{
                        supplierInfoDao.updatebsIsApproveById(approvedFlowRecord.getBsSuppId());
                    }

                } else {//不是项目最后审批人
                    approvedItemsRecord1.setBsStep(approvedItemsRecord1.getBsStep() + 1);//项目步骤+1

                    //待办事项新增当前项目的下一位审批人
                    for (ApprovedRecorderMap recorderMap : approvedItemsRecord1.getRecorderSet()) {
                        if (recorderMap.getBsPriority() == (approvedItemsRecord1.getBsStep()) && recorderMap.getBsIsDel() == 0) {
                            todoInfo.setBsUserId(recorderMap.getBsRecorderId());
                            todoInfo.setBsReferId(approvedItemsRecord1.getBsFlowRecordId());
                            break;
                        }
                    }
                }
                //保存当前项目记录
                approvedItemsRecord1.setBsModifiedTime(new Date());
                approvedItemsRecord1.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
                approvedItemsRecordDao.save(approvedItemsRecord1);

            } else if (bsResult == ApprovedStateEnum.ITEMS_RESULT_BACK.intValue()) {//驳回
                if (approvedItemsRecord1.getBsStep() > 1) {
                    approvedItemsRecord1.setBsStep(approvedItemsRecord1.getBsStep() - 1);//项目步骤-1

                    //保存当前项目记录
                    approvedItemsRecord1.setBsModifiedTime(new Date());
                    approvedItemsRecord1.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
                    approvedItemsRecordDao.save(approvedItemsRecord1);

                    //待办事项新增当前项目的上一位审批人
                    for (ApprovedRecorderMap recorderMap : approvedItemsRecord1.getRecorderSet()) {
                        if (recorderMap.getBsPriority() == (approvedItemsRecord1.getBsStep()) && recorderMap.getBsIsDel() == 0) {
                            todoInfo.setBsUserId(recorderMap.getBsRecorderId());
                            todoInfo.setBsReferId(approvedItemsRecord1.getBsFlowRecordId());
                            break;
                        }
                    }
                } else {
                    return ApiResponseResult.failure("第一步审批人无法执行驳回操作！");
                }
            } else if (bsResult == ApprovedStateEnum.ITEMS_RESULT_NOPASS.intValue()) {//不通过
                //项目记录不通过
                approvedItemsRecord1.setBsStatus(ApprovedStateEnum.ITEMS_NOPASS.intValue());

                //保存当前项目记录
                approvedItemsRecord1.setBsModifiedTime(new Date());
                approvedItemsRecord1.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
                approvedItemsRecordDao.save(approvedItemsRecord1);

                ApprovedFlowRecord approvedFlowRecord = approvedFlowRecordDao.findOne(approvedItemsRecord1.getBsFlowRecordId());
                approvedFlowRecord.setBsStatus(ApprovedStateEnum.FLOW_CLOSE.intValue());//关闭
                approvedFlowRecord.setBsResult(ApprovedStateEnum.RESULT_NOPASS.intValue());//未通过
                approvedFlowRecord.setBsConclusion(bsConclusion);
                approvedFlowRecord.setBsModifiedTime(new Date());
                approvedFlowRecord.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());

                approvedFlowRecordDao.save(approvedFlowRecord);

                //关闭产品和供应商的审核中状态
                if(null != approvedFlowRecord.getBsPrId()){
                    //该产品仍在审核（成品检验）
                    int approvedCount =approvedFlowRecordDao.countByBsPrIdAndBsType(approvedFlowRecord.getBsPrId(), approvedFlowRecord.getFlowBy().getBsType());
                    if(approvedCount == 0){
                        productInfoDao.updatebsIsApproveById(approvedFlowRecord.getBsPrId());
                    }
                }else{
                    supplierInfoDao.updatebsIsApproveById(approvedFlowRecord.getBsSuppId());
                }
            } else {
                return ApiResponseResult.failure("传入参数为空!");
            }
        }

        //关闭当前审批人待办事项
        todoInfoDao.closeByBsUserIdAndBsReferId(approvedRecorderMap.getBsRecorderId(),bsFlowRecordId);
        //新增下一位审批人待办事项
        if(null != todoInfo.getBsUserId()) {
            //审批类型
            int bsType = approvedItemsRecord.getItemsBy().getBsType();
            if (bsType == ApprovedStateEnum.PR_APPROVED.intValue()) {
                todoInfo.setBsType(BasicStateEnum.TODO_PR.intValue());
                todoInfo.setBsRouter("/qms/product/approved");
            } else if (bsType == ApprovedStateEnum.SUPP_APPROVED.intValue() || bsType == ApprovedStateEnum.SUPPQUAL_APPROVED.intValue()) {
                todoInfo.setBsType(BasicStateEnum.TODO_SUPP.intValue());
                todoInfo.setBsRouter("/qms/supplier/approved");
            } else {
                todoInfo.setBsType(BasicStateEnum.TODO_PR_PRO.intValue());
                todoInfo.setBsRouter("/qms/product/approved");
            }
            todoInfo.setBsCreatedTime(new Date());
            todoInfo.setPkCreatedBy(SessionContextUtils.getCurrentUser().getId());

            //获取流程
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("bsFlowId", approvedItemsRecord.getItemsBy().getBsFlowId());
            StringBuffer sqlbf1 = new StringBuffer();
            sqlbf1.append(" SELECT * FROM "+ ApprovedFlow.TABLE_NAME+" a WHERE a.id=:bsFlowId");
            List<Map<String, Object>> flowList = super.findBySql(sqlbf1.toString(), param);

            //获取供应商或产品信息
            List<Map<String, Object>> entityList = new ArrayList<Map<String, Object>>();
            String entityName = "";
            if (null != approvedItemsRecord.getBsPrId()) {
                param = new HashMap<String, Object>();
                param.put("bsPrId", approvedItemsRecord.getBsPrId());
                sqlbf1 = new StringBuffer();
                sqlbf1.append(" SELECT * FROM "+ ProductInfo.TABLE_NAME+" a WHERE a.id = :bsPrId");
                entityList = super.findBySql(sqlbf1.toString(), param);
                entityName = entityList.get(0).get("BS_PR_NAME").toString();
            } else {
                param = new HashMap<String, Object>();
                param.put("bsSuppId", approvedItemsRecord.getBsSuppId());
                sqlbf1 = new StringBuffer();
                sqlbf1.append(" SELECT * FROM "+ SupplierInfo.TABLE_NAME+" a WHERE a.id = :bsSuppId");
                entityList = super.findBySql(sqlbf1.toString(), param);
                entityName = entityList.get(0).get("BS_SUPP_CHIESE_NAME").toString();
            }
            ApprovedFlowRecord o = approvedFlowRecordDao.findOne(approvedItemsRecord.getBsFlowRecordId());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            todoInfo.setBsTitle("[" + flowList.get(0).get("BS_NAME") + "]" + entityName+
                    (o.getBsPlanDate()==null?"":( "("+formatter.format(o.getBsPlanDate())+")" ))+
                    (bsResult == ApprovedStateEnum.ITEMS_RESULT_BACK.intValue()?"--驳回":""));
            todoInfo.setBsSystemType(BasicStateEnum.TODO_QMSTYPE.intValue());
            todoInfoDao.save(todoInfo);

            //发送邮件
            //先判断是否开启了邮件功能，开启了就可以发邮件
            String flagMail = appConfig.getString("scm.mail.enabled");
            if(StringUtils.isNotEmpty(flagMail) && flagMail.equals("true")){
                SysUser sysUser = sysUserDao.findOne(todoInfo.getBsUserId());
                if(sysUser != null && StringUtils.isNotEmpty(sysUser.getBsEmail())){
                    String nameTo = sysUser.getBsName();    //收件人名
                    String mailTo = sysUser.getBsEmail().trim();   //收件人邮箱
                    String subject = "待办事项提醒";    //主题
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                    String dateStr = simpleDateFormat.format(new Date());
                    StringBuffer text = new StringBuffer();   //邮件内容
                    text.append("<div style='padding: 5px;'>" + nameTo + "，" + "</div>");
                    text.append("<div style='padding: 5px;'>" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你好！" + todoInfo.getBsTitle() +"。请及时处理！</div>");
                    text.append("<div style='padding: 5px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请<a href='http://www.unind.net'>点击此处</a>进入QMS系统。</div>");
                    text.append("<div style='padding: 5px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;此致</div>");
                    text.append("<div style='padding: 5px;'>敬礼</div>");
                    text.append("<div style='padding: 5px; float: right; margin: 0 50px 0 0;'>QMS系统</div><br>");
                    text.append("<div style='padding: 5px; float: right; margin: 0 20px 0 0;clear: both;'>" + dateStr + "</div><br>");
                    mailSenderService.send(mailTo, subject, text.toString());
                }
            }
        }

        return ApiResponseResult.success("审批成功！");
    }

    @Transactional(readOnly = true)
    public ApiResponseResult getlist(Long bsFlowRecordId, Long id, PageRequest pageRequest) {
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        if (null != bsFlowRecordId) {
            filters.add(new SearchFilter("bsFlowRecordId", SearchFilter.Operator.EQ, bsFlowRecordId));
            filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BooleanStateEnum.FALSE.intValue()));
            Specifications<ApprovedItemsRecord> spec = Specifications.where(super.and(filters, ApprovedItemsRecord.class));
            Page<ApprovedItemsRecord> page = approvedItemsRecordDao.findAll(spec, pageRequest);
            return ApiResponseResult.success().data(Datagrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
        }else if(null != id){
            ApprovedItemsRecord o = approvedItemsRecordDao.findOne(id);
            return ApiResponseResult.success().data(o);
        }else{
            return ApiResponseResult.failure("传入参数为空");
        }
    }
}
