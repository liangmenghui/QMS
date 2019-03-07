package com.unind.qms.web.basic.service.internal;

import com.unind.base.dao.admin.SysUserDao;
import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.domain.admin.user.SysUser;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BaseService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.context.SessionContextUtils;
import com.unind.qms.enumeration.BasicStateEnum;
import com.unind.qms.web.basic.dao.*;
import com.unind.qms.web.basic.entity.FeedbackInfo;
import com.unind.qms.web.basic.entity.FeedbackInfoExtra;
import com.unind.qms.web.basic.entity.FeedbackInfoFile;
import com.unind.qms.web.basic.entity.FeedbackRefund;
import com.unind.qms.web.basic.service.FeedbackInfoService;
import com.unind.qms.web.product.dao.ProductInfoDao;
import com.unind.qms.web.product.entity.ProductInfo;
import com.unind.qms.web.risk.dao.ProductRiskDao;
import com.unind.qms.web.risk.dao.SupplierRiskDao;
import com.unind.qms.web.risk.entity.ProductRisk;
import com.unind.qms.web.risk.entity.SupplierRisk;
import com.unind.qms.web.supplier.dao.SupplierInfoDao;
import com.unind.qms.web.supplier.entity.SupplierInfo;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = BusinessException.class)
public class FeedbackInfoImpl extends BaseOprService implements FeedbackInfoService {
    @Autowired
    private FeedbackInfoDao feedbackInfoDao;
    @Autowired
    private TodoInfoDao todoInfoDao;
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private SupplierInfoDao supplierInfoDao;
    @Autowired
    private ProductRiskDao productRiskDao;
    @Autowired
    private SupplierRiskDao supplierRiskDao;
    @Autowired
    private FeedbackInfoFileDao feedbackInfoFileDao;
    @Autowired
    private FeedbackRefundDao feedbackRefundDao;
    @Autowired
    private FeedbackInfoExtraDao feedbackInfoExtraDao;
    @Autowired
    private SysUserDao sysUserDao;

    @Transactional
    public ApiResponseResult add(FeedbackInfo feedbackInfo, String fileIdStr, String refundIdStr, List<FeedbackInfoExtra> feedbackInfoExtraList) throws BusinessException {
        //添加客诉信息
        feedbackInfo.setBsStep(1);
//        feedbackInfo.setBsStatus(BasicStateEnum.FEEDBACK_START.intValue());
        feedbackInfo.setBsCreatedTime(new Date());
		feedbackInfo.setPkCreatedBy(SessionContextUtils.getCurrentUser().getId());
        feedbackInfoDao.save(feedbackInfo);

        //添加客诉文件
        if(StringUtils.isNotEmpty(fileIdStr)){
            String[] fileIdArr = fileIdStr.split(",");
            if(fileIdArr.length>0){
                for(String fileId:fileIdArr){
                    FeedbackInfoFile feedbackInfoFile = new FeedbackInfoFile();
                    feedbackInfoFile.setBsFeedbackId(feedbackInfo.getId());
                    feedbackInfoFile.setFsFileId(Long.valueOf(fileId));
                    feedbackInfoFile.setBsCreatedTime(new Date());
                    feedbackInfoFileDao.save(feedbackInfoFile);
                }
            }
        }

        //更新客诉退款信息
        if(StringUtils.isNotEmpty(refundIdStr)){
            String[] refundIdArr = refundIdStr.split(",");
            if(refundIdArr.length > 0){
                for (String refundId : refundIdArr){
                    FeedbackRefund feedbackRefund = feedbackRefundDao.findOne(Long.valueOf(refundId));
                    if(feedbackRefund != null){
                        feedbackRefund.setBsFeedbackId(feedbackInfo.getId());
                        feedbackRefund.setBsModifiedTime(new Date());
                        feedbackRefundDao.save(feedbackRefund);
                    }
                }
            }
        }

        //添加客诉附加信息
        if(feedbackInfoExtraList.size() > 0){
            for(int i = 0; i < feedbackInfoExtraList.size(); i++){
                if(feedbackInfoExtraList.get(i).getBsSuppId() == null){
                    return ApiResponseResult.failure("供应商ID不能为空！");
                }
                feedbackInfoExtraList.get(i).setBsFeedbackId(feedbackInfo.getId());
                feedbackInfoExtraList.get(i).setBsCreatedTime(new Date());
            }
            feedbackInfoExtraDao.save(feedbackInfoExtraList);
        }

        return ApiResponseResult.success("新增成功！").data(feedbackInfo);
    }

    @Transactional
    public ApiResponseResult edit(FeedbackInfo feedbackInfo, String fileIdStr) throws BusinessException {
        if (null == feedbackInfo || null == feedbackInfo.getId()) {
            return ApiResponseResult.failure("记录ID为必填项");
        }
        FeedbackInfo o = feedbackInfoDao.findOne(feedbackInfo.getId());
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
        //添加客诉文件
        if(StringUtils.isNotEmpty(fileIdStr)){
            String[] fileIdArr = fileIdStr.split(",");
            if(fileIdArr.length>0){
                for(String fileId:fileIdArr){
                    FeedbackInfoFile f = feedbackInfoFileDao.findOne(Long.valueOf(fileId));
                    if(null == f){
                        FeedbackInfoFile feedbackInfoFile = new FeedbackInfoFile();
                        feedbackInfoFile.setBsFeedbackId(feedbackInfo.getId());
                        feedbackInfoFile.setFsFileId(Long.valueOf(fileId));
                        feedbackInfoFile.setBsCreatedTime(new Date());
                        feedbackInfoFileDao.save(feedbackInfoFile);
                    }
                }
            }
        }

        feedbackInfo.setBsUserId(o.getBsUserId());
        feedbackInfo.setBsCreatedTime(o.getBsCreatedTime());
        feedbackInfo.setPkCreatedBy(o.getPkCreatedBy());
        feedbackInfo.setBsVersion(o.getBsVersion());
        feedbackInfo.setBsIsDel(o.getBsIsDel());
        feedbackInfo.setBsModifiedTime(new Date());
        feedbackInfo.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
        //客诉关闭，则关闭所有待办
        if(feedbackInfo.getBsStatus() == BasicStateEnum.FEEDBACK_FINISH.intValue()){
            todoInfoDao.closeByBsAndBsReferId(feedbackInfo.getId());
        }
        feedbackInfoDao.save(feedbackInfo);
        return ApiResponseResult.success("修改成功！").data(feedbackInfo);
    }

    @Transactional
    public ApiResponseResult delete(Long id) throws BusinessException {
        FeedbackInfo o = feedbackInfoDao.findOne(id);
        if (null == o) {
            return ApiResponseResult.failure("记录ID不存在或已被删除").status("error1");
        }
        if (o.getId() <= 0) {
            return ApiResponseResult.failure("没有操作权限");
        }
        o.setBsIsDel(BooleanStateEnum.TRUE.intValue());
        feedbackInfoDao.save(o);
        return ApiResponseResult.success("删除成功！");
    }

    @Transactional(readOnly = true)
    public ApiResponseResult getlist(String keyWord, Long id, Integer bsStatus, PageRequest pageRequest) {
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BooleanStateEnum.FALSE.intValue()));
        if(null != id){
            filters.add(new SearchFilter("id", SearchFilter.Operator.EQ, id));
        }
        if(null != bsStatus){
            filters.add(new SearchFilter("bsStatus", SearchFilter.Operator.EQ, bsStatus));
        }

        List<SearchFilter> filters1 = new ArrayList<SearchFilter>();
        if (StringUtils.isNotEmpty(keyWord)) {
            if(StringUtils.isNumeric(keyWord)){
                Long feedbackId = Long.parseLong(keyWord);
                filters1.add(new SearchFilter("id", SearchFilter.Operator.EQ, keyWord));
            }else{
            }

//            List prList = productInfoDao.findByPrNameLike(keyWord);
//            for(int i=0;i<prList.size();i++){
//                filters1.add(new SearchFilter("bsPrId", SearchFilter.Operator.EQ, prList.get(i).toString()));
//            }
//
//            List suppList = supplierInfoDao.findByBsSuppChieseNameLike(keyWord);
//            for(int i=0;i<suppList.size();i++){
//                filters1.add(new SearchFilter("bsSuppId", SearchFilter.Operator.EQ, suppList.get(i).toString()));
//            }
        }
        Specifications<FeedbackInfo> spec = Specifications.where(super.and(filters, FeedbackInfo.class));
        Specifications<FeedbackInfo> spec1 = spec.and(super.or(filters1, FeedbackInfo.class));
        Page<FeedbackInfo> page = feedbackInfoDao.findAll(spec1, pageRequest);
        return ApiResponseResult.success().data(Datagrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
    }

    @Transactional(readOnly = true)
    public ApiResponseResult getuser(SysUser sysUser, PageRequest pageRequest) {
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BooleanStateEnum.FALSE.intValue()));
        if(null != sysUser.getId()){
            filters.add(new SearchFilter("id", SearchFilter.Operator.EQ, sysUser.getId()));
        }
        if(null != sysUser.getBsCode()){
            filters.add(new SearchFilter("bsCode", SearchFilter.Operator.EQ, sysUser.getBsCode()));
        }
        if(null != sysUser.getBsName()){
            filters.add(new SearchFilter("bsName", SearchFilter.Operator.LIKE, sysUser.getBsName()));
        }
        Specifications<SysUser> spec = Specifications.where(super.and(filters, SysUser.class));
        Page<SysUser> page = sysUserDao.findAll(spec, pageRequest);
        return ApiResponseResult.success().data(Datagrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
    }
}
