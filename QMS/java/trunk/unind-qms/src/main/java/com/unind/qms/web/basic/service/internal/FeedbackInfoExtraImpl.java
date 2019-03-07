package com.unind.qms.web.basic.service.internal;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.basic.dao.FeedbackInfoExtraDao;
import com.unind.qms.web.basic.entity.FeedbackInfoExtra;
import com.unind.qms.web.basic.service.FeedbackInfoExtraService;
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

/**
 * 客诉附加信息
 */
@Service
@Transactional(rollbackFor = BusinessException.class)
public class FeedbackInfoExtraImpl extends BaseOprService implements FeedbackInfoExtraService {

    @Autowired
    private FeedbackInfoExtraDao feedbackInfoExtraDao;

    @Override
    @Transactional
    public ApiResponseResult add(FeedbackInfoExtra feedbackInfoExtra) throws BusinessException {
        if(feedbackInfoExtra == null || feedbackInfoExtra.getBsFeedbackId() == null){
            return ApiResponseResult.failure("客诉ID不能为空！");
        }
        if(feedbackInfoExtra.getBsSuppId() == null){
            return ApiResponseResult.failure("供应商ID不能为空！");
        }
        feedbackInfoExtra.setBsCreatedTime(new Date());
        feedbackInfoExtraDao.save(feedbackInfoExtra);
        return ApiResponseResult.success("新增成功！").data(feedbackInfoExtra);
    }

    @Override
    @Transactional
    public ApiResponseResult edit(FeedbackInfoExtra feedbackInfoExtra) throws BusinessException {
        if(feedbackInfoExtra == null || feedbackInfoExtra.getId() == null){
            return ApiResponseResult.failure("记录ID不能为空！");
        }
        FeedbackInfoExtra o = feedbackInfoExtraDao.findOne(feedbackInfoExtra.getId());
        if(o == null){
            return ApiResponseResult.failure("记录ID不存在或已被删除！");
        }
        o.setBsSuppCompanyName(feedbackInfoExtra.getBsSuppCompanyName());
        o.setBsSuppCompanyCode(feedbackInfoExtra.getBsSuppCompanyCode());
        o.setBsSuppCompanyPerson(feedbackInfoExtra.getBsSuppCompanyPerson());
        o.setBsSuppCompanyEmail(feedbackInfoExtra.getBsSuppCompanyEmail());
        o.setBsSuppCompanyMobile(feedbackInfoExtra.getBsSuppCompanyMobile());
        o.setBsPrCode(feedbackInfoExtra.getBsPrCode());
        o.setBsBatchNo(feedbackInfoExtra.getBsBatchNo());
        o.setBsPrName(feedbackInfoExtra.getBsPrName());
        o.setBsPrNum(feedbackInfoExtra.getBsPrNum());
        o.setBsReportLocation(feedbackInfoExtra.getBsReportLocation());
        o.setBsProductLocation(feedbackInfoExtra.getBsProductLocation());
        o.setBsProductDate(feedbackInfoExtra.getBsProductDate());
        o.setBsModifiedTime(new Date());
        feedbackInfoExtraDao.save(o);
        return ApiResponseResult.success("修改成功！").data(o);
    }

    @Override
    @Transactional
    public ApiResponseResult delete(Long id) throws BusinessException {
        FeedbackInfoExtra o = feedbackInfoExtraDao.findOne(id);
        if (null == o) {
            return ApiResponseResult.failure("记录ID不存在或已被删除");
        }
//        o.setBsIsDel(BooleanStateEnum.TRUE.intValue());
        feedbackInfoExtraDao.delete(id);
        return ApiResponseResult.success("删除成功！");
    }

    @Override
    @Transactional
    public ApiResponseResult getlist(Long bsFeedbackId, Long bsSuppId, Long bsPrId, PageRequest pageRequest) throws BusinessException {
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BooleanStateEnum.FALSE.intValue()));
        if(bsFeedbackId != null){
            filters.add(new SearchFilter("bsFeedbackId", SearchFilter.Operator.EQ, bsFeedbackId));
        }
        if(bsSuppId != null){
            filters.add(new SearchFilter("bsSuppId", SearchFilter.Operator.EQ, bsSuppId));
        }
        if(bsPrId != null){
            filters.add(new SearchFilter("bsPrId", SearchFilter.Operator.EQ, bsPrId));
        }
        Specifications<FeedbackInfoExtra> spec = Specifications.where(and(filters, FeedbackInfoExtra.class));
        Page<FeedbackInfoExtra> page = feedbackInfoExtraDao.findAll(spec, pageRequest);
        return ApiResponseResult.success().data(Datagrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
    }
}
