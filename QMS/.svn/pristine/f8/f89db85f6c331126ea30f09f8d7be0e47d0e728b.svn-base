package com.unind.qms.web.shipment.service.internal;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BaseService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.context.SessionContextUtils;
import com.unind.qms.web.shipment.dao.ShipmentInspectDao;
import com.unind.qms.web.shipment.dao.ShipmentInspectRecordDao;
import com.unind.qms.web.shipment.entity.ShipmentInspect;
import com.unind.qms.web.shipment.entity.ShipmentInspectRecord;
import com.unind.qms.web.shipment.service.ShipmentInspectRecordService;
import com.unind.qms.web.shipment.service.ShipmentInspectService;
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
public class ShipmentInspectRecordImpl extends BaseOprService implements ShipmentInspectRecordService {
    @Autowired
    private ShipmentInspectRecordDao shipmentInspectRecordDao;

    @Transactional
    public ApiResponseResult add(ShipmentInspectRecord shipmentInspectRecord) throws BusinessException {
        if (null == shipmentInspectRecord || null == shipmentInspectRecord.getBsShipmentId()) {
            return ApiResponseResult.failure("出货检验报告ID为必填项");
        }

        shipmentInspectRecord.setBsCreatedTime(new Date());
        shipmentInspectRecordDao.save(shipmentInspectRecord);
        return ApiResponseResult.success("新增成功！").data(shipmentInspectRecord);
    }

    @Transactional
    public ApiResponseResult edit(ShipmentInspectRecord shipmentInspectRecord) throws BusinessException {
        if (null == shipmentInspectRecord || null == shipmentInspectRecord.getId()) {
            return ApiResponseResult.failure("记录ID为必填项");
        }
        if (null == shipmentInspectRecord.getBsShipmentId()) {
            return ApiResponseResult.failure("出货检验报告ID为必填项");
        }
        ShipmentInspectRecord o = shipmentInspectRecordDao.findOne(shipmentInspectRecord.getId());
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
        o.setBsShipmentId(shipmentInspectRecord.getBsShipmentId());
        o.setBsInspecter(shipmentInspectRecord.getBsInspecter());
        o.setBsDesc(shipmentInspectRecord.getBsDesc());
        o.setBsResult(shipmentInspectRecord.getBsResult());

        o.setBsModifiedTime(new Date());
        shipmentInspectRecordDao.save(o);
        return ApiResponseResult.success("修改成功！").data(o);
    }

    @Transactional
    public ApiResponseResult delete(Long id) throws BusinessException {
        ShipmentInspectRecord o = shipmentInspectRecordDao.findOne(id);
        if (null == o) {
            return ApiResponseResult.failure("记录ID不存在或已被删除").status("error1");
        }
        if (o.getId() <= 0) {
            return ApiResponseResult.failure("没有操作权限");
        }
        o.setBsIsDel(BooleanStateEnum.TRUE.intValue());
        shipmentInspectRecordDao.save(o);
        return ApiResponseResult.success("删除成功！");
    }

    @Transactional(readOnly = true)
    public ApiResponseResult getlist(Long bsShipmentId, PageRequest pageRequest) {
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BaseEnumConstants.FALSE));
        if (bsShipmentId != null) {
            filters.add(new SearchFilter("bsShipmentId", SearchFilter.Operator.EQ, bsShipmentId));
        }
        Specifications<ShipmentInspectRecord> spec = Specifications.where(super.and(filters, ShipmentInspectRecord.class));
        Page<ShipmentInspectRecord> page = shipmentInspectRecordDao.findAll(spec, pageRequest);
        return ApiResponseResult.success().data(Datagrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
    }
}
