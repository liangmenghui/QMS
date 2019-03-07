package com.unind.qms.web.shipment.service.internal;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BaseService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.context.SessionContextUtils;
import com.unind.qms.web.shipment.dao.OrderInspectDao;
import com.unind.qms.web.shipment.dao.ShipmentInspectDao;
import com.unind.qms.web.shipment.entity.OrderInspect;
import com.unind.qms.web.shipment.entity.ShipmentInspect;
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
public class ShipmentInspectImpl extends BaseOprService implements ShipmentInspectService {
    @Autowired
    private ShipmentInspectDao shipmentInspectDao;
    @Autowired
    private OrderInspectDao orderInspectDao;

    @Transactional
    public ApiResponseResult add(Long bsOrderId, Long bsFlowRecordId) throws BusinessException {
        if(null != bsOrderId&&null != bsFlowRecordId){
            OrderInspect orderInspect = orderInspectDao.findOne(bsOrderId);

            ShipmentInspect shipmentInspect = new ShipmentInspect();
            shipmentInspect.setBsPrId(orderInspect.getBsPrId());
            shipmentInspect.setBsFlowRecordId(bsFlowRecordId);
            shipmentInspect.setBsOrderId(bsOrderId);
            shipmentInspect.setBsContractNo(orderInspect.getBsContractNo());
            shipmentInspect.setBsBatchNo(orderInspect.getBsPoNo());
            shipmentInspect.setBsProductNum(orderInspect.getBsPoQty());
            shipmentInspect.setBsCreatedTime(new Date());
            shipmentInspect.setPkCreatedBy(SessionContextUtils.getCurrentUser().getId());
            shipmentInspectDao.save(shipmentInspect);
            return ApiResponseResult.success("新增成功！").data(shipmentInspect);
        }else{
            return ApiResponseResult.failure("传入参数为空！");
        }
    }

    @Transactional
    public ApiResponseResult edit(ShipmentInspect shipmentInspect) throws BusinessException {
        if (null == shipmentInspect || null == shipmentInspect.getBsPrId()) {
            return ApiResponseResult.failure("产品ID为必填项");
        }
        if (null == shipmentInspect || null == shipmentInspect.getBsFlowRecordId()) {
            return ApiResponseResult.failure("审核流程记录ID为必填项");
        }

        ShipmentInspect o = shipmentInspectDao.findOne(shipmentInspect.getId());
        if (null == o) {
            return ApiResponseResult.failure("记录ID不存在或已被删除");
        }
        shipmentInspect.setBsIsDel(o.getBsIsDel());
        shipmentInspect.setBsCreatedTime(o.getBsCreatedTime());
        shipmentInspect.setPkCreatedBy(o.getPkCreatedBy());
        shipmentInspect.setBsVersion(o.getBsVersion());

        shipmentInspect.setBsModifiedTime(new Date());
        shipmentInspect.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
        shipmentInspectDao.save(shipmentInspect);
        return ApiResponseResult.success("修改成功！").data(shipmentInspect);
    }

    @Transactional
    public ApiResponseResult delete(Long id) throws BusinessException {
        ShipmentInspect o = shipmentInspectDao.findOne(id);
        if (null == o) {
            return ApiResponseResult.failure("记录ID不存在或已被删除").status("error1");
        }
        if (o.getId() <= 0) {
            return ApiResponseResult.failure("没有操作权限");
        }
        o.setBsIsDel(BooleanStateEnum.TRUE.intValue());
        shipmentInspectDao.save(o);
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
        Specifications<ShipmentInspect> spec = Specifications.where(super.and(filters, ShipmentInspect.class));
        Page<ShipmentInspect> page = shipmentInspectDao.findAll(spec, pageRequest);
        return ApiResponseResult.success().data(Datagrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
    }
}
