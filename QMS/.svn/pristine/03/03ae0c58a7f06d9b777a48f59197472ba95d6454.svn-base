package com.unind.qms.web.approved.service.internal;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BaseService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.context.SessionContextUtils;
import com.unind.qms.App;
import com.unind.qms.web.approved.dao.ApprovedFlowDao;
import com.unind.qms.web.approved.entity.ApprovedFlow;
import com.unind.qms.web.approved.entity.ApprovedTerms;
import com.unind.qms.web.approved.service.ApprovedFlowService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = BusinessException.class)
public class ApprovedFlowImpl extends BaseOprService implements ApprovedFlowService {
    @Autowired
    private ApprovedFlowDao approvedFlowDao;

    @Transactional
    public ApiResponseResult add(ApprovedFlow approvedFlow) throws BusinessException {
        if (StringUtils.isEmpty(approvedFlow.getBsName()) || StringUtils.isEmpty(approvedFlow.getBsName().trim())) {
            return ApiResponseResult.failure("流程名称不能为空");
        }
        if (null == approvedFlow.getBsApprovederId()) {
            return ApiResponseResult.failure("最终审批人不能为空");
        }
        int counts = approvedFlowDao.countByBsName(approvedFlow.getBsName());
        if (counts > 0) {
            return ApiResponseResult.failure("项目已存在");
        }
        approvedFlow.setBsName(approvedFlow.getBsName().trim());

        approvedFlow.setBsCreatedTime(new Date());
        approvedFlow.setPkCreatedBy(SessionContextUtils.getCurrentUser().getId());
//        approvedFlow.setPkCreatedBy(Long.parseLong("1"));
        approvedFlowDao.save(approvedFlow);
        return ApiResponseResult.success("新增成功！").data(approvedFlow);
    }

    @Transactional
    public ApiResponseResult edit(ApprovedFlow approvedFlow) throws BusinessException {
        if (null == approvedFlow || null == approvedFlow.getId()) {
            return ApiResponseResult.failure("记录ID为必填项");
        }
        if (StringUtils.isEmpty(approvedFlow.getBsName()) || StringUtils.isEmpty(approvedFlow.getBsName().trim())) {
            return ApiResponseResult.failure("流程名称不能为空");
        }
        if (null == approvedFlow.getBsApprovederId()) {
            return ApiResponseResult.failure("最终审批人不能为空");
        }
        ApprovedFlow o = approvedFlowDao.findOne(approvedFlow.getId());
        if (null == o) {
            return ApiResponseResult.failure("记录ID不存在或已被删除");
        }
        if (!StringUtils.equals(approvedFlow.getBsName().trim(), o.getBsName())) {
            int counts = approvedFlowDao.countByBsName(approvedFlow.getBsName());
            if (counts > 0) {
                return ApiResponseResult.failure("项目名称不能重复");
            }
            o.setBsName(approvedFlow.getBsName().trim());
        }
        o.setBsType(approvedFlow.getBsType());
        o.setBsApprovederId(approvedFlow.getBsApprovederId());
//        o.setBsApprovedStatus(approvedFlow.getBsApprovedStatus());
//        o.setBsCanYield(approvedFlow.getBsCanYield());
//        o.setBsRejectStatus(approvedFlow.getBsRejectStatus());

        o.setBsModifiedTime(new Date());
		o.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
//        o.setPkModifiedBy(Long.parseLong("1"));
        approvedFlowDao.save(o);
        return ApiResponseResult.success("修改成功！");
    }

    @Transactional
    public ApiResponseResult delete(Long id) throws BusinessException {
        ApprovedFlow o = approvedFlowDao.findOne(id);
        if (null == o) {
            return ApiResponseResult.failure("记录ID不存在或已被删除").status("error1");
        }
        if (o.getId() <= 0) {
            return ApiResponseResult.failure("没有操作权限");
        }
        o.setBsIsDel(BooleanStateEnum.TRUE.intValue());
        approvedFlowDao.save(o);
        return ApiResponseResult.success("删除成功！");
    }

    @Transactional(readOnly = true)
    public ApiResponseResult getlist(String keyWord, PageRequest pageRequest) {
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BooleanStateEnum.FALSE.intValue()));
        if (StringUtils.isNotEmpty(keyWord)) {
            filters.add(new SearchFilter("bsName", SearchFilter.Operator.LIKE, keyWord));
        }
        Specifications<ApprovedFlow> spec = Specifications.where(super.and(filters, ApprovedFlow.class));
        Page<ApprovedFlow> page = approvedFlowDao.findAll(spec, pageRequest);
        return ApiResponseResult.success().data(Datagrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
    }

    /**
     * 复制审核流程
     * @param id
     * @return
     */
    @Transactional
    public ApiResponseResult copy(Long id){
        if(null == id){
            return ApiResponseResult.failure("记录ID为必填项");
        }
        ApprovedFlow o = approvedFlowDao.findOne(id);
        if(null == o){
            return ApiResponseResult.failure("记录ID不存在或已被删除");
        }

        ApprovedFlow approvedFlow = new ApprovedFlow();
        //复制审核流程名在原来的后面加个 副本，若有多个则加数字1、2、3
        String copyName = o.getBsName() + "-副本";   //副本名称
        int copyNumber = 0;                          //副本次数
        List<ApprovedFlow> approvedFlowList = approvedFlowDao.findByBsNameLike(copyName);
        if(approvedFlowList.size() > 0){
            copyNumber = approvedFlowList.size() + 1;
            copyName = copyName + "-" + copyNumber;
        }
        approvedFlow.setBsName(copyName);
        approvedFlow.setBsType(o.getBsType());
        approvedFlow.setBsApprovederId(o.getBsApprovederId());
//        approvedFlow.setBsCanYield(o.getBsCanYield());
//        approvedFlow.setBsApprovedStatus(o.getBsApprovedStatus());
//        approvedFlow.setBsRejectStatus(o.getBsRejectStatus());
        approvedFlow.setBsCreatedTime(new Date());
        approvedFlow.setPkCreatedBy(SessionContextUtils.getCurrentUser().getId());

        approvedFlowDao.save(approvedFlow);
        return ApiResponseResult.success("复制成功！").data(approvedFlow);
    }
}
