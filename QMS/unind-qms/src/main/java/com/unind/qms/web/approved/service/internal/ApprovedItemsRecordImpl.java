package com.unind.qms.web.approved.service.internal;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.context.SessionContextUtils;
import com.unind.qms.enumeration.ApprovedStateEnum;
import com.unind.qms.enumeration.BasicStateEnum;
import com.unind.qms.web.approved.dao.*;
import com.unind.qms.web.approved.entity.ApprovedFlowRecord;
import com.unind.qms.web.approved.entity.ApprovedItemsRecord;
import com.unind.qms.web.approved.entity.ApprovedItemsResult;
import com.unind.qms.web.approved.entity.ApprovedRecorderMap;
import com.unind.qms.web.approved.service.ApprovedItemsRecordService;
import com.unind.qms.web.basic.dao.TodoInfoDao;
import com.unind.qms.web.basic.entity.TodoInfo;
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
public class ApprovedItemsRecordImpl extends BaseOprService implements ApprovedItemsRecordService {
    @Autowired
    private ApprovedItemsRecordDao approvedItemsRecordDao;
    @Autowired
    private ApprovedRecorderMapDao approvedRecorderMapDao;
    @Autowired
    private ApprovedFlowRecordDao approvedFlowRecordDao;
    @Autowired
    private ApprovedItemsResultDao approvedItemsResultDao;
    @Autowired
    private TodoInfoDao todoInfoDao;
    @Autowired
    private ApprovedTermsScoreDao approvedTermsScoreDao;

    @Transactional
    public ApiResponseResult approved(Long bsItemsId, Long bsItemsRecordId, int bsResult, String resultDesc, String itemsDesc) throws BusinessException {
        //当前项目记录
        ApprovedItemsRecord approvedItemsRecord = approvedItemsRecordDao.findOne(bsItemsRecordId);
        //当前项目是否不存在或者正在进行中
        if(null == approvedItemsRecord || ApprovedStateEnum.ITEMS_IN.intValue() != approvedItemsRecord.getBsStatus()){
            return ApiResponseResult.failure("当前项目不存在或者不在进行中！");
        }
        //当前审批人
        ApprovedRecorderMap approvedRecorderMap = approvedRecorderMapDao.findByBsItemsIdAndBsPriority(bsItemsId, approvedItemsRecord.getBsStep()).get(0);
        //当前登录人是否为当前审批人
        if(!SessionContextUtils.getCurrentUser().getId().equals(approvedRecorderMap.getBsRecorderId())){
            return ApiResponseResult.failure("请使用当前审批人账号！");
        }

        //初始化待办事项
        TodoInfo todoInfo = new TodoInfo();
        StringBuffer nextTodoItemsName = new StringBuffer();
        nextTodoItemsName.append("--"+approvedItemsRecord.getItemsBy().getBsName());
        if(bsResult == ApprovedStateEnum.ITEMS_RESULT_PASS.intValue()){//通过
            //是否为项目最后审批人
            int isLastRecorder = approvedItemsRecordDao.countByBsItemsId(bsItemsId,bsItemsRecordId);
            if(isLastRecorder>0){//是项目最后审批人
                //项目记录通过
                approvedItemsRecord.setBsStatus(ApprovedStateEnum.ITEMS_PASS.intValue());
                //计算条款总得分
//                approvedItemsRecord.setBsScoreNum(approvedTermsScoreDao.findScoreNumByBsItemsRecoreId(bsItemsRecordId));

                //返回当前项目记录后的项目记录
                List<ApprovedItemsRecord> approvedItemsRecordList = approvedItemsRecordDao.findByBsFlowRecordIdAndBsPriority(approvedItemsRecord.getBsFlowRecordId(),approvedItemsRecord.getBsPriority());

                //如果存在下一项目记录,则下一项目进行中
                if(approvedItemsRecordList.size()>0){
                    ApprovedItemsRecord nextApprovedItemsRecord = approvedItemsRecordList.get(0);
                    nextApprovedItemsRecord.setBsStatus(ApprovedStateEnum.ITEMS_IN.intValue());
                    nextApprovedItemsRecord.setBsCreatedTime(new Date());
                    nextApprovedItemsRecord.setBsModifiedTime(new Date());
                    nextApprovedItemsRecord.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());

                    //待办事项新增下一项目的首位审批人
                    nextTodoItemsName.setLength(0);
                    nextTodoItemsName.append("--"+nextApprovedItemsRecord.getItemsBy().getBsName());
                    for(ApprovedRecorderMap recorderMap:nextApprovedItemsRecord.getItemsBy().getRecorderSet()){
                        if(recorderMap.getBsPriority()==1&&recorderMap.getBsIsDel()==0){
                            todoInfo.setBsUserId(recorderMap.getBsRecorderId());
                            todoInfo.setBsReferId(nextApprovedItemsRecord.getBsFlowRecordId());
                            todoInfo.setBsExtend(nextApprovedItemsRecord.getId());
                            break;
                        }
                    }
                    approvedItemsRecordDao.save(nextApprovedItemsRecord);
                }else{//不存在下一项目记录，则流程记录待确认，进行中，待办事项新增流程负责人
                    ApprovedFlowRecord approvedFlowRecord = approvedFlowRecordDao.findOne(approvedItemsRecord.getBsFlowRecordId());
                    approvedFlowRecord.setBsStatus(ApprovedStateEnum.FLOW_CONFIRM.intValue());//待确认
                    approvedFlowRecord.setBsResult(ApprovedStateEnum.RESULT_IN.intValue());//进行中
                    approvedFlowRecord.setBsModifiedTime(new Date());
                    approvedFlowRecord.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());

                    //待办事项新增流程负责人
                    nextTodoItemsName.setLength(0);
                    nextTodoItemsName.append("--审核结果确认");
                    todoInfo.setBsUserId(approvedFlowRecord.getFlowBy().getBsApprovederId());
                    todoInfo.setBsReferId(approvedFlowRecord.getId());

                    approvedFlowRecordDao.save(approvedFlowRecord);
                }
            }else{//不是项目最后审批人
                approvedItemsRecord.setBsStep(approvedItemsRecord.getBsStep()+1);//项目步骤+1

                //待办事项新增当前项目的下一位审批人
                nextTodoItemsName.setLength(0);
                nextTodoItemsName.append("--"+approvedItemsRecord.getItemsBy().getBsName());
                for(ApprovedRecorderMap recorderMap:approvedItemsRecord.getItemsBy().getRecorderSet()){
                    if(recorderMap.getBsPriority()==(approvedItemsRecord.getBsStep())&&recorderMap.getBsIsDel()==0){
                        todoInfo.setBsUserId(recorderMap.getBsRecorderId());
                        todoInfo.setBsReferId(approvedItemsRecord.getBsFlowRecordId());
                        todoInfo.setBsExtend(approvedItemsRecord.getId());
                        break;
                    }
                }

            }
        }else if(bsResult == ApprovedStateEnum.ITEMS_RESULT_BACK.intValue()){//驳回
            if(approvedItemsRecord.getBsStep()>1){
                approvedItemsRecord.setBsStep(approvedItemsRecord.getBsStep()-1);//项目步骤-1

                //待办事项新增当前项目的上一位审批人
                for(ApprovedRecorderMap recorderMap:approvedItemsRecord.getItemsBy().getRecorderSet()){
                    if(recorderMap.getBsPriority()==(approvedItemsRecord.getBsStep())&&recorderMap.getBsIsDel()==0){
                        todoInfo.setBsUserId(recorderMap.getBsRecorderId());
                        todoInfo.setBsReferId(approvedItemsRecord.getBsFlowRecordId());
                        todoInfo.setBsExtend(approvedItemsRecord.getId());
                        break;
                    }
                }

            }else{
                return ApiResponseResult.failure("第一步审批人无法执行驳回操作！");
            }
        }else if(bsResult == ApprovedStateEnum.ITEMS_RESULT_NOPASS.intValue()){//不通过
            //项目记录不通过
            approvedItemsRecord.setBsStatus(ApprovedStateEnum.ITEMS_NOPASS.intValue());
            //计算条款总得分
//            approvedItemsRecord.setBsScoreNum(approvedTermsScoreDao.findScoreNumByBsItemsRecoreId(bsItemsRecordId));

            //获取流程记录
            ApprovedFlowRecord approvedFlowRecord = approvedFlowRecordDao.findOne(approvedItemsRecord.getBsFlowRecordId());

            //返回当前项目记录后的项目记录
            List<ApprovedItemsRecord> approvedItemsRecordList = approvedItemsRecordDao.findByBsFlowRecordIdAndBsPriority(approvedItemsRecord.getBsFlowRecordId(),approvedItemsRecord.getBsPriority());
            //如果流程允许让步且存在下一项目记录,则下一项目进行中
            if(approvedFlowRecord.getFlowBy().getBsCanYield() == 1 && approvedItemsRecordList.size()>0){
                ApprovedItemsRecord nextApprovedItemsRecord = approvedItemsRecordList.get(0);
                nextApprovedItemsRecord.setBsStatus(ApprovedStateEnum.ITEMS_IN.intValue());
                nextApprovedItemsRecord.setBsCreatedTime(new Date());
                nextApprovedItemsRecord.setBsModifiedTime(new Date());
                nextApprovedItemsRecord.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());

                //待办事项新增下一项目的首位审批人
                nextTodoItemsName.setLength(0);
                nextTodoItemsName.append("--"+nextApprovedItemsRecord.getItemsBy().getBsName());
                for(ApprovedRecorderMap recorderMap:nextApprovedItemsRecord.getItemsBy().getRecorderSet()){
                    if(recorderMap.getBsPriority()==1&&recorderMap.getBsIsDel()==0){
                        todoInfo.setBsUserId(recorderMap.getBsRecorderId());
                        todoInfo.setBsReferId(nextApprovedItemsRecord.getBsFlowRecordId());
                        todoInfo.setBsExtend(nextApprovedItemsRecord.getId());
                        break;
                    }
                }
                approvedItemsRecordDao.save(nextApprovedItemsRecord);
            }else{//流程不允许让步或者不存在下一项目记录，则流程记录待确认，进行中，待办事项新增流程负责人
//                ApprovedFlowRecord approvedFlowRecord = approvedFlowRecordDao.findOne(approvedItemsRecord.getBsFlowRecordId());
                approvedFlowRecord.setBsStatus(ApprovedStateEnum.FLOW_CONFIRM.intValue());//待确认
                approvedFlowRecord.setBsResult(ApprovedStateEnum.RESULT_IN.intValue());//进行中
                approvedFlowRecord.setBsModifiedTime(new Date());
                approvedFlowRecord.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());

                //待办事项新增流程负责人
                nextTodoItemsName.setLength(0);
                nextTodoItemsName.append("--审核结果确认");
                todoInfo.setBsUserId(approvedFlowRecord.getFlowBy().getBsApprovederId());
                todoInfo.setBsReferId(approvedFlowRecord.getId());

                approvedFlowRecordDao.save(approvedFlowRecord);
            }
        }else{
            return ApiResponseResult.failure("传入参数为空!");
        }

        //关闭当前审批人待办事项
        todoInfoDao.closeByBsUserIdAndBsAndBsExtend(approvedRecorderMap.getBsRecorderId(),approvedItemsRecord.getId());
        //新增下一位审批人待办事项
//        todoInfo.setBsRouter("/qms/supplier/approved");
        //审批类型
        int bsType = approvedItemsRecord.getItemsBy().getBsType();
        if(bsType == ApprovedStateEnum.PR_APPROVED.intValue()){
            todoInfo.setBsType(BasicStateEnum.TODO_PR.intValue());
            todoInfo.setBsRouter("/qms/product/approved");
        }else if(bsType == ApprovedStateEnum.SUPP_APPROVED.intValue()||bsType == ApprovedStateEnum.SUPPQUAL_APPROVED.intValue()){
            todoInfo.setBsType(BasicStateEnum.TODO_SUPP.intValue());
            todoInfo.setBsRouter("/qms/supplier/approved");
        }else{
            todoInfo.setBsType(BasicStateEnum.TODO_PR_PRO.intValue());
            todoInfo.setBsRouter("/qms/product/approved");
        }
        todoInfo.setBsCreatedTime(new Date());
        todoInfo.setPkCreatedBy(SessionContextUtils.getCurrentUser().getId());

        //获取流程
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("bsFlowId", approvedItemsRecord.getItemsBy().getBsFlowId());
        StringBuffer sqlbf1 = new StringBuffer();
        sqlbf1.append(" SELECT * FROM t_approved_flow a WHERE a.id=:bsFlowId");
        List<Map<String, Object>> flowList = super.findBySql(sqlbf1.toString(), param);

        //获取供应商或产品信息
        List<Map<String, Object>> entityList = new ArrayList<Map<String, Object>>();
        String entityName = "";
        if(null != approvedItemsRecord.getBsPrId()){
            param = new HashMap<String, Object>();
            param.put("bsPrId", approvedItemsRecord.getBsPrId());
            sqlbf1 = new StringBuffer();
            sqlbf1.append(" SELECT * FROM erp_product_info a WHERE a.id = :bsPrId");
            entityList = super.findBySql(sqlbf1.toString(), param);
            entityName = entityList.get(0).get("BS_PR_NAME").toString();
        }else{
            param = new HashMap<String, Object>();
            param.put("bsSuppId", approvedItemsRecord.getBsSuppId());
            sqlbf1 = new StringBuffer();
            sqlbf1.append(" SELECT * FROM t_supplier_info a WHERE a.id = :bsSuppId");
            entityList = super.findBySql(sqlbf1.toString(), param);
            entityName = entityList.get(0).get("BS_SUPP_CHIESE_NAME").toString();
        }

        todoInfo.setBsTitle("["+flowList.get(0).get("BS_NAME")+"]"+entityName+nextTodoItemsName.toString());
        todoInfo.setBsSystemType(BasicStateEnum.TODO_QMSTYPE.intValue());
        todoInfoDao.save(todoInfo);

        //保存当前项目记录
        approvedItemsRecord.setBsDesc(itemsDesc);
        approvedItemsRecord.setBsModifiedTime(new Date());
        approvedItemsRecord.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
        approvedItemsRecordDao.save(approvedItemsRecord);

        //保存当前审批结果
        ApprovedItemsResult approvedItemsResult = new ApprovedItemsResult();
        approvedItemsResult.setBsItemsRecordId(bsItemsRecordId);
        approvedItemsResult.setBsRecorderId(SessionContextUtils.getCurrentUser().getId());
        approvedItemsResult.setBsResult(bsResult);
        approvedItemsResult.setBsDesc(resultDesc);
        approvedItemsResult.setBsCreatedTime(new Date());
        approvedItemsResultDao.save(approvedItemsResult);
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
