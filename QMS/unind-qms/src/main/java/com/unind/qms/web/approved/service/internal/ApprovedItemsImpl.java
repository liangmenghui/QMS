package com.unind.qms.web.approved.service.internal;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.context.SessionContextUtils;
import com.unind.qms.web.approved.dao.ApprovedItemsDao;
import com.unind.qms.web.approved.dao.ApprovedItemsMapDao;
import com.unind.qms.web.approved.dao.ApprovedRecorderMapDao;
import com.unind.qms.web.approved.dao.ApprovedTermsDao;
import com.unind.qms.web.approved.entity.ApprovedItems;
import com.unind.qms.web.approved.entity.ApprovedItemsMap;
import com.unind.qms.web.approved.entity.ApprovedRecorderMap;
import com.unind.qms.web.approved.entity.ApprovedTerms;
import com.unind.qms.web.approved.service.ApprovedItemsService;
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
public class ApprovedItemsImpl extends BaseOprService implements ApprovedItemsService {
    @Autowired
    private ApprovedItemsDao approvedItemsDao;

    @Autowired
    private ApprovedItemsMapDao approvedItemsMapDao;

    @Autowired
    private ApprovedRecorderMapDao approvedRecorderMapDao;

    @Transactional
    public ApiResponseResult add(ApprovedItems approvedItems, String termsStr, String userStr) throws BusinessException {
        if (StringUtils.isEmpty(approvedItems.getBsName()) || StringUtils.isEmpty(approvedItems.getBsName().trim())) {
            return ApiResponseResult.failure("项目名称不能为空");
        }
        approvedItems.setBsName(approvedItems.getBsName().trim());

        approvedItems.setBsCreatedTime(new Date());
        approvedItems.setPkCreatedBy(SessionContextUtils.getCurrentUser().getId());
//        approvedItems.setPkCreatedBy(Long.parseLong("1"));
        approvedItemsDao.save(approvedItems);

        //新增项目关联条款
        if(StringUtils.isNotEmpty(termsStr)) {
            String[] termsArr = termsStr.trim().split(",");
            for (String termsId : termsArr) {
                ApprovedItemsMap approvedItemsMap = new ApprovedItemsMap();
                approvedItemsMap.setBsItemsId(approvedItems.getId());
                approvedItemsMap.setBsTermsId(Long.parseLong(termsId));
                approvedItemsMap.setBsCreatedTime(new Date());
                approvedItemsMapDao.save(approvedItemsMap);
            }
        }

        //新增项目关联记录人
        if(StringUtils.isNotEmpty(userStr)) {
            String[] userArr = userStr.trim().split(",");
            for (int i = 1;i<=userArr.length;i++) {
                ApprovedRecorderMap approvedRecorderMap = new ApprovedRecorderMap();
                approvedRecorderMap.setBsItemsId(approvedItems.getId());
                approvedRecorderMap.setBsRecorderId(Long.parseLong(userArr[i-1]));
                approvedRecorderMap.setBsPriority(i);
                approvedRecorderMap.setBsCreatedTime(new Date());
                approvedRecorderMapDao.save(approvedRecorderMap);
            }
        }
        return ApiResponseResult.success("新增成功！").data(approvedItems);
    }

    @Transactional
    public ApiResponseResult edit(ApprovedItems approvedItems,String termsStr, String userStr) throws BusinessException {
        if (null == approvedItems || null == approvedItems.getId()) {
            return ApiResponseResult.failure("记录ID为必填项");
        }
        if (StringUtils.isEmpty(approvedItems.getBsName()) || StringUtils.isEmpty(approvedItems.getBsName().trim())) {
            return ApiResponseResult.failure("项目名称不能为空");
        }
        ApprovedItems o = approvedItemsDao.findOne(approvedItems.getId());
        if (null == o) {
            return ApiResponseResult.failure("记录ID不存在或已被删除");
        }
//        if (!StringUtils.equals(approvedItems.getBsName().trim(), o.getBsName())) {
//            int counts = approvedItemsDao.countByBsName(approvedItems.getBsName());
//            if (counts > 0) {
//                return ApiResponseResult.failure("项目名称不能重复");
//            }
            o.setBsName(approvedItems.getBsName().trim());
//        }
        o.setBsType(approvedItems.getBsType());
        o.setBsRouter(approvedItems.getBsRouter());
        o.setBsRouterName(approvedItems.getBsRouterName());
        o.setBsContentType(approvedItems.getBsContentType());
        o.setBsFlowId(approvedItems.getBsFlowId());
        o.setBsPriority(approvedItems.getBsPriority());

        o.setBsModifiedTime(new Date());
		o.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
//        o.setPkModifiedBy(Long.parseLong("1"));
        approvedItemsDao.save(o);

        //修改项目关联条款
        if(StringUtils.isNotEmpty(termsStr)) {
            approvedItemsMapDao.deleteByBsItemsId(o.getId());//该项目下所有条款bsIsDel=1
            String[] termsArr = termsStr.trim().split(",");
            for (String termsId : termsArr) {
                List<SearchFilter> filters = new ArrayList<SearchFilter>();
                filters.add(new SearchFilter("bsItemsId", SearchFilter.Operator.EQ, o.getId()));
                filters.add(new SearchFilter("bsTermsId", SearchFilter.Operator.EQ, termsId));
                Specifications<ApprovedItemsMap> spec = Specifications.where(super.and(filters, ApprovedItemsMap.class));
                List<ApprovedItemsMap> approvedItemsMapList = approvedItemsMapDao.findAll(spec);

                ApprovedItemsMap approvedItemsMap = new ApprovedItemsMap();
                if(approvedItemsMapList.size()==0){//新增条款
                    approvedItemsMap.setBsItemsId(o.getId());
                    approvedItemsMap.setBsTermsId(Long.parseLong(termsId));
                    approvedItemsMap.setBsCreatedTime(new Date());
                    approvedItemsMapDao.save(approvedItemsMap);
                }else{//还原条款bsIsDel=0
                    approvedItemsMap = approvedItemsMapList.get(0);
                    approvedItemsMap.setBsIsDel(BooleanStateEnum.FALSE.intValue());
                    approvedItemsMap.setBsModifiedTime(new Date());
                    approvedItemsMapDao.save(approvedItemsMap);
                }
            }
        }

        //修改项目关联记录人
        if(StringUtils.isNotEmpty(userStr)) {
            approvedRecorderMapDao.deleteByBsItemsId(o.getId());//该项目下所有记录人bsIsDel=1
            String[] userArr = userStr.trim().split(",");
            for (int i = 1;i<=userArr.length;i++) {
                List<SearchFilter> filters = new ArrayList<SearchFilter>();
                filters.add(new SearchFilter("bsItemsId", SearchFilter.Operator.EQ, o.getId()));
                filters.add(new SearchFilter("bsRecorderId", SearchFilter.Operator.EQ, Long.parseLong(userArr[i-1])));
                filters.add(new SearchFilter("bsPriority", SearchFilter.Operator.EQ, i));
                Specifications<ApprovedRecorderMap> spec = Specifications.where(super.and(filters, ApprovedRecorderMap.class));
                List<ApprovedRecorderMap> approvedRecorderMapList = approvedRecorderMapDao.findAll(spec);

                ApprovedRecorderMap approvedRecorderMap = new ApprovedRecorderMap();
                if(approvedRecorderMapList.size() ==0){//新增记录人
                    approvedRecorderMap.setBsItemsId(o.getId());
                    approvedRecorderMap.setBsRecorderId(Long.parseLong(userArr[i-1]));
                    approvedRecorderMap.setBsPriority(i);
                    approvedRecorderMap.setBsCreatedTime(new Date());
                    approvedRecorderMapDao.save(approvedRecorderMap);
                }else{//还原记录人bsIsDel=0
                    approvedRecorderMap = approvedRecorderMapList.get(0);
                    approvedRecorderMap.setBsIsDel(BooleanStateEnum.FALSE.intValue());
                    approvedRecorderMap.setBsModifiedTime(new Date());
                    approvedRecorderMapDao.save(approvedRecorderMap);
                }
            }
        }
        return ApiResponseResult.success("修改成功！");
    }

    @Transactional
    public ApiResponseResult delete(Long id) throws BusinessException {
        ApprovedItems o = approvedItemsDao.findOne(id);
        if (null == o) {
            return ApiResponseResult.failure("记录ID不存在或已被删除").status("error1");
        }
        if (o.getId() <= 0) {
            return ApiResponseResult.failure("没有操作权限");
        }
        o.setBsIsDel(BooleanStateEnum.TRUE.intValue());
        approvedItemsDao.save(o);
        return ApiResponseResult.success("删除成功！");
    }

    @Transactional(readOnly = true)
    public ApiResponseResult getlist(Long bsFlowId, Long id, PageRequest pageRequest) {
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        if (null != bsFlowId) {   //根据流程ID查找所有项目
            filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BooleanStateEnum.FALSE.intValue()));
            filters.add(new SearchFilter("bsFlowId", SearchFilter.Operator.EQ, bsFlowId));
            Specifications<ApprovedItems> spec = Specifications.where(super.and(filters, ApprovedItems.class));
            Page<ApprovedItems> page = approvedItemsDao.findAll(spec, pageRequest);
            return ApiResponseResult.success().data(Datagrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
        }else if(null != id){  //根据项目ID查找项目数据和所有条款
            //查找项目数据
//            Map<String, Object> map = new HashMap<String, Object>();
            ApprovedItems o = approvedItemsDao.findOne(id);
//            map.put("iteminfo",o);

//            //查找该项目下的所有条款
//            Map<String, Object> param = new HashMap<String, Object>();
//            param.put("bsTermsId", id);
//            StringBuffer sqlbf = new StringBuffer();
//            sqlbf.append(" select a.bs_items_id,b.*");
//            sqlbf.append(" from t_approved_items_map a");
//            sqlbf.append(" left join t_approved_terms b on a.bs_terms_id = b.id");
//            sqlbf.append(" where a.bs_items_id = :bsTermsId and a.bs_is_del='0'");
//            List<Map<String, Object>> termsList = super.findBySql(sqlbf.toString(), param);
//            map.put("termsinfo", termsList);
//
//            //查找该项目下的所有记录人
//            Map<String, Object> param1 = new HashMap<String, Object>();
//            param1.put("bsTermsId", id);
//            StringBuffer sqlbf1 = new StringBuffer();
//            sqlbf1.append(" select a.bs_items_id,b.*");
//            sqlbf1.append(" from t_approved_recorder_map a");
//            sqlbf1.append(" left join sys_user b on a.bs_recorder_id = b.id");
//            sqlbf1.append(" where a.bs_items_id = :bsTermsId and a.bs_is_del='0'");
//            List<Map<String, Object>> recorderList = super.findBySql(sqlbf1.toString(), param1);
//            map.put("recorderinfo", recorderList);

//            return ApiResponseResult.success().data(map);
            return ApiResponseResult.success().data(o);
        }else{
            return ApiResponseResult.failure("传入参数为空");
        }
    }
}
