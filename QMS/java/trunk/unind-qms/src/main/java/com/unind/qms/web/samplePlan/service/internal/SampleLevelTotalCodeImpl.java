package com.unind.qms.web.samplePlan.service.internal;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.samplePlan.dao.SampleLevelTotalCodeDao;
import com.unind.qms.web.samplePlan.entity.SampleLevelTotalCode;
import com.unind.qms.web.samplePlan.service.SampleLevelTotalCodeService;
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
 * 检验水平——批量——样本代字关系
 */
@Service
@Transactional(rollbackFor = BusinessException.class)
public class SampleLevelTotalCodeImpl extends BaseOprService implements SampleLevelTotalCodeService {
    @Autowired
    private SampleLevelTotalCodeDao sampleLevelTotalCodeDao;

    @Override
    @Transactional
    public ApiResponseResult edit(SampleLevelTotalCode sampleLevelTotalCode) throws BusinessException {
        if(sampleLevelTotalCode == null || sampleLevelTotalCode.getId() == null){
            return ApiResponseResult.failure("记录ID为必填项！");
        }
        SampleLevelTotalCode o = sampleLevelTotalCodeDao.findOne(sampleLevelTotalCode.getId());
        if(o == null){
            return ApiResponseResult.failure("记录ID不存在或已被删除");
        }

        o.setBsLowerLimit(sampleLevelTotalCode.getBsLowerLimit());
        o.setBsUpperLimit(sampleLevelTotalCode.getBsUpperLimit());
        o.setBsLevel1(sampleLevelTotalCode.getBsLevel1());
        o.setBsLevel2(sampleLevelTotalCode.getBsLevel2());
        o.setBsLevel3(sampleLevelTotalCode.getBsLevel3());
        o.setBsLevel4(sampleLevelTotalCode.getBsLevel4());
        o.setBsLevel5(sampleLevelTotalCode.getBsLevel5());
        o.setBsLevel6(sampleLevelTotalCode.getBsLevel6());
        o.setBsLevel7(sampleLevelTotalCode.getBsLevel7());
        o.setBsModifiedTime(new Date());
        sampleLevelTotalCodeDao.save(o);
        return ApiResponseResult.success("修改成功！").data(o);
    }

    @Override
    @Transactional
    public ApiResponseResult getlist(Integer bsLowerLimit, Integer bsUpperLimit, PageRequest pageRequest) throws BusinessException {
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BaseEnumConstants.FALSE));
        if(bsLowerLimit != null){
            filters.add(new SearchFilter("bsLowerLimit", SearchFilter.Operator.EQ, bsLowerLimit));
        }
        if(bsUpperLimit != null){
            filters.add(new SearchFilter("bsUpperLimit", SearchFilter.Operator.EQ, bsUpperLimit));
        }
        Specification<SampleLevelTotalCode> spec = Specifications.where(super.and(filters, SampleLevelTotalCode.class));
        Page<SampleLevelTotalCode> page = sampleLevelTotalCodeDao.findAll(spec, pageRequest);
        return ApiResponseResult.success().data(Datagrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
    }
}
