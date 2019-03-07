package com.unind.qms.web.sample.service.internal;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.context.SessionContextUtils;
import com.unind.qms.web.quality.dao.QualityInspectDao;
import com.unind.qms.web.sample.dao.SampleRegularRecordDao;
import com.unind.qms.web.sample.entity.SampleRegularRecord;
import com.unind.qms.web.sample.service.SampleRegularRecordService;
import com.unind.qms.web.shipment.dao.ShipmentInspectDao;
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
public class SampleRegularRecordImpl extends BaseOprService implements SampleRegularRecordService {
    @Autowired
    private SampleRegularRecordDao sampleRegularRecordDao;
    @Autowired
    private ShipmentInspectDao shipmentInspectDao;
    @Autowired
    private QualityInspectDao qualityInspectDao;

    @Transactional
    public ApiResponseResult add(List<SampleRegularRecord> sampleRegularRecordList, Long bsShipmentInspectId) throws BusinessException {
//        if(null != sampleRecord){
//            sampleRecord.setBsCreatedTime(new Date());
//            sampleRecord.setPkCreatedBy(SessionContextUtils.getCurrentUser().getId());
//            sampleRecordDao.save(sampleRecord);
//            //出货检验报告添加样品ID
//            if(null != sampleRecord.getBsShipmentId()){
//                shipmentInspectDao.updateBsSampleIdByBsId(sampleRecord.getBsSampleId(),sampleRecord.getBsShipmentId());
//            }else{//质量检验报告添加样品ID
//                qualityInspectDao.updateBsSampleIdByBsId(sampleRecord.getBsSampleId(),sampleRecord.getBsQualityId());
//            }
//        }
        List<SampleRegularRecord> sampleRegularRecordList1 = new ArrayList<SampleRegularRecord>();
        for(int i=0;i<sampleRegularRecordList.size();i++){
            SampleRegularRecord sampleRegularRecord = sampleRegularRecordList.get(i);
            if(null == sampleRegularRecord.getId()){//新增
                if(bsShipmentInspectId == null){
                    return ApiResponseResult.failure("出货检验报告ID为必填项！");
                }
                if(sampleRegularRecord.getBsSampleRegularId() == null){
                    return ApiResponseResult.failure("样品规格ID为必填项！");
                }
                sampleRegularRecord.setBsShipmentInspectId(bsShipmentInspectId);   //出货检验报告ID
                sampleRegularRecord.setBsCreatedTime(new Date());
                sampleRegularRecord.setPkCreatedBy(SessionContextUtils.getCurrentUser().getId());
                sampleRegularRecordDao.save(sampleRegularRecord);
                sampleRegularRecordList1.add(sampleRegularRecord);
            }else{//修改
                SampleRegularRecord o = sampleRegularRecordDao.findOne(sampleRegularRecord.getId());
                if(o == null){
                    return ApiResponseResult.failure("此样品规格记录ID不存在或已删除！").data(sampleRegularRecord);
                }
                o.setBsMeasureResult(sampleRegularRecord.getBsMeasureResult());
                o.setBsDesc(sampleRegularRecord.getBsDesc());
                o.setBsModifiedTime(new Date());
                o.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
                sampleRegularRecordDao.save(o);
            }
        }
        return ApiResponseResult.success("新增/修改成功！").data(sampleRegularRecordList1);
    }

    @Transactional
    public ApiResponseResult delete(Long id) throws BusinessException {
        SampleRegularRecord o = sampleRegularRecordDao.findOne(id);
        if (null == o) {
            return ApiResponseResult.failure("记录ID不存在或已被删除").status("error1");
        }
        if (o.getId() <= 0) {
            return ApiResponseResult.failure("没有操作权限");
        }
        o.setBsIsDel(BooleanStateEnum.TRUE.intValue());
        sampleRegularRecordDao.save(o);
        return ApiResponseResult.success("删除成功！");
    }

    @Transactional(readOnly = true)
    public ApiResponseResult getlist(Long bsShipmentInspectId, PageRequest pageRequest) {
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BaseEnumConstants.FALSE));
        if (bsShipmentInspectId != null) {
            filters.add(new SearchFilter("bsShipmentInspectId", SearchFilter.Operator.EQ, bsShipmentInspectId));
        }
        Specifications<SampleRegularRecord> spec = Specifications.where(super.and(filters, SampleRegularRecord.class));
        Page<SampleRegularRecord> page = sampleRegularRecordDao.findAll(spec, pageRequest);
        return ApiResponseResult.success().data(Datagrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
    }
}
