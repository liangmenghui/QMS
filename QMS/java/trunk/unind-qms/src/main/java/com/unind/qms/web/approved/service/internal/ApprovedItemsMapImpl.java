package com.unind.qms.web.approved.service.internal;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BaseService;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.approved.dao.ApprovedItemsDao;
import com.unind.qms.web.approved.dao.ApprovedItemsMapDao;
import com.unind.qms.web.approved.entity.ApprovedItems;
import com.unind.qms.web.approved.entity.ApprovedItemsMap;
import com.unind.qms.web.approved.service.ApprovedItemsMapService;
import com.unind.qms.web.approved.service.ApprovedItemsService;
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
public class ApprovedItemsMapImpl extends BaseOprService implements ApprovedItemsMapService {
    @Autowired
    private ApprovedItemsMapDao approvedItemsMapDao;

    @Transactional
    public ApiResponseResult add(Long bsItemsId, String TermsIdStr) throws BusinessException {
        if (null == bsItemsId) {
            return ApiResponseResult.failure("审批项目ID不能为空");
        }
        if (StringUtils.isEmpty(TermsIdStr) || StringUtils.isEmpty(TermsIdStr.trim())) {
            return ApiResponseResult.failure("审批条款ID不能为空");
        }
        String[] TermsIdArr = TermsIdStr.trim().split(",");

        for(String TermsId:TermsIdArr){
            ApprovedItemsMap approvedItemsMap = new ApprovedItemsMap();
            approvedItemsMap.setBsItemsId(bsItemsId);
            approvedItemsMap.setBsTermsId(Long.parseLong(TermsId));
            approvedItemsMap.setBsCreatedTime(new Date());
            approvedItemsMapDao.save(approvedItemsMap);
        }
        return ApiResponseResult.success("新增成功！");
    }

    @Transactional
    public ApiResponseResult delete(Long bsItemsId, Long bsTermsId) throws BusinessException {
        if (null == bsItemsId) {
            approvedItemsMapDao.deleteByBsItemsId(bsItemsId);
        }else{
            approvedItemsMapDao.deleteByBsTermsId(bsTermsId);
        }
//        o.setBsIsDel(ApprovedEnumConstants.TRUE);
        return ApiResponseResult.success("删除成功！");
    }
}
