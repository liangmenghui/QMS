package com.unind.qms.web.approved.service.internal;

import com.unind.base.dao.admin.SysUserDao;
import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.domain.admin.user.SysUser;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.context.SessionContextUtils;
import com.unind.qms.web.approved.dao.ApprovedFlowDao;
import com.unind.qms.web.approved.dao.ApprovedItemsResultDao;
import com.unind.qms.web.approved.entity.ApprovedFlow;
import com.unind.qms.web.approved.entity.ApprovedItemsResult;
import com.unind.qms.web.approved.service.ApprovedFlowService;
import com.unind.qms.web.approved.service.ApprovedItemsResultService;
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
public class ApprovedItemsResultImpl extends BaseOprService implements ApprovedItemsResultService {
    @Autowired
    private ApprovedItemsResultDao approvedItemsResultDao;
    @Autowired
    private SysUserDao sysUserDao;

    @Transactional
    public ApiResponseResult add(ApprovedItemsResult approvedItemsResult) throws BusinessException {
        if (null == approvedItemsResult.getBsItemsRecordId()) {
            return ApiResponseResult.failure("流程记录ID不能为空");
        }
        approvedItemsResult.setBsRecorderId(SessionContextUtils.getCurrentUser().getId());
        if(null != approvedItemsResult.getId()){
            ApprovedItemsResult o = approvedItemsResultDao.findOne(approvedItemsResult.getId());
            o.setBsResult(approvedItemsResult.getBsResult());
            o.setBsDesc(approvedItemsResult.getBsDesc());
            o.setBsModifiedTime(new Date());
            approvedItemsResultDao.save(o);
            return ApiResponseResult.success("新增成功！").data(o);
        }else{
            approvedItemsResult.setBsCreatedTime(new Date());
            approvedItemsResultDao.save(approvedItemsResult);
            SysUser sysUser = sysUserDao.findOne(approvedItemsResult.getBsRecorderId());
            approvedItemsResult.setResultBy(sysUser);
            return ApiResponseResult.success("新增成功！").data(approvedItemsResult);
        }
    }

    @Transactional
    public ApiResponseResult delete(Long id) throws BusinessException {
        ApprovedItemsResult approvedItemsResult = approvedItemsResultDao.findOne(id);
        if(approvedItemsResult == null){
            return ApiResponseResult.failure("记录ID不存在或已被删除！");
        }
        if(approvedItemsResult.getId() <= 0){
            return ApiResponseResult.failure("没有操作权限！");
        }
//        promote.setBsIsDel(BooleanStateEnum.TRUE.intValue());
        approvedItemsResultDao.delete(approvedItemsResult);
        return ApiResponseResult.success("删除成功！");
    }
}
