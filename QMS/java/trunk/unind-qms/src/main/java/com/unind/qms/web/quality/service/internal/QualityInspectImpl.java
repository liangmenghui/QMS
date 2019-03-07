package com.unind.qms.web.quality.service.internal;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BaseService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.context.SessionContextUtils;
import com.unind.qms.web.quality.dao.QualityInspectDao;
import com.unind.qms.web.quality.entity.QualityInspect;
import com.unind.qms.web.quality.service.QualityInspectService;
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
public class QualityInspectImpl extends BaseOprService implements QualityInspectService {
    @Autowired
    private QualityInspectDao qualityInspectDao;

    @Transactional
    public ApiResponseResult add(QualityInspect qualityInspect) throws BusinessException {
        if (null == qualityInspect || null == qualityInspect.getBsPrId()) {
            return ApiResponseResult.failure("产品ID为必填项");
        }
        if (null == qualityInspect || null == qualityInspect.getBsFlowRecordId()) {
            return ApiResponseResult.failure("审核流程记录ID为必填项");
        }

        if(null == qualityInspect.getId()){
            qualityInspect.setBsCreatedTime(new Date());
            qualityInspect.setPkCreatedBy(SessionContextUtils.getCurrentUser().getId());
        }else{
            QualityInspect o = qualityInspectDao.findOne(qualityInspect.getId());
            if (null == o) {
                return ApiResponseResult.failure("记录ID不存在或已被删除");
            }
            o.setBsSamplingDate(qualityInspect.getBsSamplingDate());
            o.setBsSamplingPlace(qualityInspect.getBsSamplingPlace());
            o.setBsProductDate(qualityInspect.getBsProductDate());
            o.setBsAppearanceFile(qualityInspect.getBsAppearanceFile());

            o.setBsModifiedTime(new Date());
            o.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
        }
        qualityInspectDao.save(qualityInspect);
        return ApiResponseResult.success("新增/修改成功！").data(qualityInspect);
    }

    @Transactional
    public ApiResponseResult delete(Long id) throws BusinessException {
        QualityInspect o = qualityInspectDao.findOne(id);
        if (null == o) {
            return ApiResponseResult.failure("记录ID不存在或已被删除").status("error1");
        }
        if (o.getId() <= 0) {
            return ApiResponseResult.failure("没有操作权限");
        }
        o.setBsIsDel(BooleanStateEnum.TRUE.intValue());
        qualityInspectDao.save(o);
        return ApiResponseResult.success("删除成功！");
    }

    @Transactional(readOnly = true)
    public ApiResponseResult getlist(Long bsFlowRecordId, Long bsPrId, PageRequest pageRequest) {
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BaseEnumConstants.FALSE));
        if (bsFlowRecordId != null) {
            filters.add(new SearchFilter("bsFlowRecordId", SearchFilter.Operator.EQ, bsFlowRecordId));
        }
        if (bsPrId != null) {
            filters.add(new SearchFilter("bsPrId", SearchFilter.Operator.EQ, bsPrId));
        }
        Specifications<QualityInspect> spec = Specifications.where(super.and(filters, QualityInspect.class));
        Page<QualityInspect> page = qualityInspectDao.findAll(spec, pageRequest);
        return ApiResponseResult.success().data(Datagrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
    }
}
