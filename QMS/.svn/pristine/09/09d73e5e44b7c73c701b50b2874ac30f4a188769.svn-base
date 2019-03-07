package com.unind.qms.web.samplePlan.service.internal;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.context.SessionContextUtils;
import com.unind.qms.web.samplePlan.dao.SampleLevelRiskDao;
import com.unind.qms.web.samplePlan.entity.SampleLevelRisk;
import com.unind.qms.web.samplePlan.service.SampleLevelRiskService;
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
 * 产品风险等级——检验水平关系
 */
@Service
@Transactional(rollbackFor = BusinessException.class)
public class SampleLevelRiskImpl extends BaseOprService implements SampleLevelRiskService {
    @Autowired
    private SampleLevelRiskDao sampleLevelRiskDao;

    @Override
    @Transactional
    public ApiResponseResult edit(SampleLevelRisk sampleLevelRisk) throws BusinessException {
        if(sampleLevelRisk == null || sampleLevelRisk.getId() == null){
            return ApiResponseResult.failure("记录ID为必填项！");
        }
        SampleLevelRisk o = sampleLevelRiskDao.findOne(sampleLevelRisk.getId());
        if(o == null){
            return ApiResponseResult.failure("记录ID不存在或已被删除");
        }

        //o.setBsRiskLevel(sampleLevelRisk.getBsRiskLevel());
        o.setBsLevel0(sampleLevelRisk.getBsLevel0());
        o.setBsLevel1(sampleLevelRisk.getBsLevel1());
        o.setBsLevel2(sampleLevelRisk.getBsLevel2());
        o.setBsLevel3(sampleLevelRisk.getBsLevel3());
        o.setBsLevel4(sampleLevelRisk.getBsLevel4());
        o.setBsLevel5(sampleLevelRisk.getBsLevel5());
        o.setBsLevel6(sampleLevelRisk.getBsLevel6());
        o.setBsLevel7(sampleLevelRisk.getBsLevel7());
        o.setBsLevel8(sampleLevelRisk.getBsLevel8());
        o.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
        o.setBsModifiedTime(new Date());
        sampleLevelRiskDao.save(o);
        return ApiResponseResult.success("修改成功！").data(o);
    }

    @Override
    @Transactional
    public ApiResponseResult getlist(Integer bsRiskLevel, PageRequest pageRequest) throws BusinessException {
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BaseEnumConstants.FALSE));
        if(bsRiskLevel != null){
            filters.add(new SearchFilter("bsRiskLevel", SearchFilter.Operator.EQ, bsRiskLevel));
        }
        Specification<SampleLevelRisk> spec = Specifications.where(super.and(filters, SampleLevelRisk.class));
        Page<SampleLevelRisk> page = sampleLevelRiskDao.findAll(spec, pageRequest);
        return ApiResponseResult.success().data(Datagrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
    }
}
