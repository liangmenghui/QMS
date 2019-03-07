package com.unind.qms.web.approved.service.internal;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BaseService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.context.SessionContextUtils;
import com.unind.qms.web.approved.dao.ApprovedItemsMapDao;
import com.unind.qms.web.approved.dao.ApprovedItemsRecordDao;
import com.unind.qms.web.approved.dao.ApprovedTermsScoreDao;
import com.unind.qms.web.approved.entity.ApprovedItemsMap;
import com.unind.qms.web.approved.entity.ApprovedTermsScore;
import com.unind.qms.web.approved.service.ApprovedItemsMapService;
import com.unind.qms.web.approved.service.ApprovedTermsScoreService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = BusinessException.class)
public class ApprovedTermsScoreImpl extends BaseOprService implements ApprovedTermsScoreService {
    @Autowired
    private ApprovedTermsScoreDao approvedTermsScoreDao;
    @Autowired
    private ApprovedItemsRecordDao approvedItemsRecordDao;

    @Transactional
    public ApiResponseResult add(List<ApprovedTermsScore> approvedTermsScoreList) throws BusinessException {
        List<ApprovedTermsScore> approvedTermsScoreList1 = new ArrayList<ApprovedTermsScore>();
        for(int i=0;i<approvedTermsScoreList.size();i++){
            ApprovedTermsScore approvedTermsScore = approvedTermsScoreList.get(i);
            if(null == approvedTermsScore.getId()){//新增
                approvedTermsScore.setBsCreatedTime(new Date());
                approvedTermsScore.setPkCreatedBy(SessionContextUtils.getCurrentUser().getId());
//                approvedTermsScore.setPkCreatedBy(Long.parseLong("1"));
                approvedTermsScoreDao.save(approvedTermsScore);
                approvedTermsScoreList1.add(approvedTermsScore);
            }else{//修改
                ApprovedTermsScore o = approvedTermsScoreDao.findOne(approvedTermsScore.getId());
                o.setBsItemsRecoreId(approvedTermsScore.getBsItemsRecoreId());
                o.setBsTermsId(approvedTermsScore.getBsTermsId());
                o.setBsScore(approvedTermsScore.getBsScore());
                o.setBsDesc(approvedTermsScore.getBsDesc());
                o.setBsModifiedTime(new Date());
                o.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
//                o.setPkModifiedBy(Long.parseLong("1"));
                approvedTermsScoreDao.save(o);
            }
        }
        //保存项目得分
        Long bsItemsRecordId = approvedTermsScoreList.get(0).getBsItemsRecoreId();
        approvedItemsRecordDao.updateBsScoreNumById(bsItemsRecordId,approvedTermsScoreDao.findScoreNumByBsItemsRecoreId(bsItemsRecordId));
        return ApiResponseResult.success("新增/修改成功！").data(approvedTermsScoreList1);
    }

//    @Transactional
//    public ApiResponseResult edit(List<ApprovedTermsScore> approvedTermsScoreList) throws BusinessException {
//        for(int i=0;i<approvedTermsScoreList.size();i++){
//            ApprovedTermsScore approvedTermsScore = approvedTermsScoreList.get(i);
//            ApprovedTermsScore o = approvedTermsScoreDao.findOne(approvedTermsScore.getId());
//            o.setBsItemsRecoreId(approvedTermsScore.getBsItemsRecoreId());
//            o.setBsTermsId(approvedTermsScore.getBsTermsId());
//            o.setBsScore(approvedTermsScore.getBsScore());
//            o.setBsDesc(approvedTermsScore.getBsDesc());
//            o.setBsModifiedTime(new Date());
//            approvedTermsScoreDao.save(o);
//        }
//        return ApiResponseResult.success("修改成功！");
//    }
}
