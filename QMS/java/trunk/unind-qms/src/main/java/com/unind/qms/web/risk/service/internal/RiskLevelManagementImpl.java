package com.unind.qms.web.risk.service.internal;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.risk.dao.RiskLevelManagementDao;
import com.unind.qms.web.risk.entity.RiskLevelManagement;
import com.unind.qms.web.risk.service.RiskLevelManagementService;
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
 * 风险等级管理（审核周期和检验水平）
 * @author Shen
 */
@Service
@Transactional(rollbackFor = BusinessException.class)
public class RiskLevelManagementImpl extends BaseOprService implements RiskLevelManagementService {

    @Autowired
    private RiskLevelManagementDao riskLevelManagementDao;

    @Override
    @Transactional
    public ApiResponseResult add(RiskLevelManagement riskLevelManagement) throws BusinessException {
        if(riskLevelManagement == null){
            return ApiResponseResult.failure("不能为空！");
        }
        List<RiskLevelManagement> o = riskLevelManagementDao.findByBsIsDelAndBsRiskLevel(BooleanStateEnum.FALSE.intValue(), riskLevelManagement.getBsRiskLevel());
        if(o.size() > 0){
            return ApiResponseResult.failure("此风险等级信息已存在");
        }
        riskLevelManagement.setBsCreatedTime(new Date());
        riskLevelManagementDao.save(riskLevelManagement);
        return ApiResponseResult.success("新增成功！").data(riskLevelManagement);
    }

    @Override
    @Transactional
    public ApiResponseResult edit(RiskLevelManagement riskLevelManagement) throws BusinessException {
        if(riskLevelManagement == null){
            return ApiResponseResult.failure("不能为空！");
        }
        if(riskLevelManagement.getId() == null){
            return ApiResponseResult.failure("记录ID不能为空！");
        }
        RiskLevelManagement o = riskLevelManagementDao.findOne(riskLevelManagement.getId());
        if(o == null){
            return ApiResponseResult.failure("记录ID不存在或已被删除！");
        }

        o.setBsRiskLevel(riskLevelManagement.getBsRiskLevel());
        o.setBsAuditCycle(riskLevelManagement.getBsAuditCycle());
        o.setBsVerificationLevel(riskLevelManagement.getBsVerificationLevel());
        o.setBsModifiedTime(new Date());
        riskLevelManagementDao.save(riskLevelManagement);
        return ApiResponseResult.success("修改成功！").data(o);
    }

    @Override
    @Transactional
    public ApiResponseResult delete(Long id) throws BusinessException {
        if(id == null){
            return ApiResponseResult.failure("记录ID不能为空！");
        }
        RiskLevelManagement o = riskLevelManagementDao.findOne(id);
        if(o == null){
            return ApiResponseResult.failure("记录ID不存在或已被删除");
        }
        o.setBsIsDel(BooleanStateEnum.TRUE.intValue());
        riskLevelManagementDao.save(o);
        return ApiResponseResult.success("删除成功！");
    }

    @Override
    @Transactional
    public ApiResponseResult getlist(Integer bsRiskLevel, PageRequest pageRequest) throws BusinessException {
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BooleanStateEnum.FALSE.intValue()));
        if(bsRiskLevel != null){
            filters.add(new SearchFilter("bsRiskLevel", SearchFilter.Operator.EQ, bsRiskLevel));
        }
        Specification<RiskLevelManagement> spec = Specifications.where(super.and(filters, RiskLevelManagement.class));
        Page<RiskLevelManagement> page = riskLevelManagementDao.findAll(spec, pageRequest);
        return ApiResponseResult.success().data(Datagrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
    }
}
