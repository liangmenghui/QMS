package com.unind.qms.web.basic.service.internal;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BaseService;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.basic.dao.FeedbackHandlerDao;
import com.unind.qms.web.basic.entity.FeedbackHandler;
import com.unind.qms.web.basic.service.FeedbackHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = BusinessException.class)
public class FeedbackHandlerImpl extends BaseOprService implements FeedbackHandlerService {
    @Autowired
    private FeedbackHandlerDao feedbackHandlerDao;

    @Transactional
    public ApiResponseResult add(FeedbackHandler feedbackHandler) throws BusinessException {
        if (null == feedbackHandler.getBsFeedbackId()) {
            return ApiResponseResult.failure("客诉ID不能为空");
        }

        feedbackHandler.setBsCreatedTime(new Date());
        if(null == feedbackHandler.getBsDeadline()){
            //如果没有传截止时间，则默认为一周之后
            Calendar curr = Calendar.getInstance();
            curr.setTime(feedbackHandler.getBsCreatedTime());
            curr.add(Calendar.WEEK_OF_YEAR, 1);
            feedbackHandler.setBsDeadline(curr.getTime());                  //有效结束时间
        }
        feedbackHandlerDao.save(feedbackHandler);
        return ApiResponseResult.success("新增成功！").data(feedbackHandler);
    }

    @Transactional
    public ApiResponseResult edit(FeedbackHandler feedbackHandler) throws BusinessException {
        if (null == feedbackHandler || null == feedbackHandler.getId()) {
            return ApiResponseResult.failure("记录ID为必填项");
        }
        if (null == feedbackHandler.getBsFeedbackId()) {
            return ApiResponseResult.failure("客诉ID不能为空");
        }
        FeedbackHandler o = feedbackHandlerDao.findOne(feedbackHandler.getId());
        if (null == o) {
            return ApiResponseResult.failure("记录ID不存在或已被删除");
        }
//        if (!StringUtils.equals(approvedFlow.getBsName().trim(), o.getBsName())) {
//            int counts = approvedFlowDao.countByBsName(approvedFlow.getBsName());
//            if (counts > 0) {
//                return ApiResponseResult.failure("项目名称不能重复");
//            }
//            o.setBsName(approvedFlow.getBsName().trim());
//        }

        o.setBsFeedbackId(feedbackHandler.getBsFeedbackId());
        o.setBsHandlerId(feedbackHandler.getBsHandlerId());
        o.setBsType(feedbackHandler.getBsType());
        o.setBsActionType(feedbackHandler.getBsActionType());
        o.setBsStatus(feedbackHandler.getBsStatus());
        o.setBsDesc(feedbackHandler.getBsDesc());
        o.setBsHandlerName(feedbackHandler.getBsHandlerName());
        o.setBsHandlerMobile(feedbackHandler.getBsHandlerMobile());
        o.setBsHandlerEmail(feedbackHandler.getBsHandlerEmail());
        o.setBsFileId(feedbackHandler.getBsFileId());
        o.setBsDeadline(feedbackHandler.getBsDeadline());
        o.setBsModifiedTime(new Date());

        feedbackHandlerDao.save(o);
        return ApiResponseResult.success("修改成功！").data(o);
    }

    @Transactional
    public ApiResponseResult delete(Long id) throws BusinessException {
        FeedbackHandler o = feedbackHandlerDao.findOne(id);
        if (null == o) {
            return ApiResponseResult.failure("记录ID不存在或已被删除").status("error1");
        }
        if (o.getId() <= 0) {
            return ApiResponseResult.failure("没有操作权限");
        }
        feedbackHandlerDao.delete(o);
        return ApiResponseResult.success("删除成功！");
    }

    @Transactional(readOnly = true)
    public ApiResponseResult getlist(Long bsFeedbackId, PageRequest pageRequest) {
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BaseEnumConstants.FALSE));
        filters.add(new SearchFilter("bsFeedbackId", SearchFilter.Operator.EQ, bsFeedbackId));
        Specifications<FeedbackHandler> spec = Specifications.where(super.and(filters, FeedbackHandler.class));
        Page<FeedbackHandler> page = feedbackHandlerDao.findAll(spec, pageRequest);
        return ApiResponseResult.success().data(Datagrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
    }
}
