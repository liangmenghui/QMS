package com.unind.qms.web.shipment.service.internal;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BaseService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.context.SessionContextUtils;
import com.unind.qms.enumeration.ApprovedStateEnum;
import com.unind.qms.web.shipment.dao.OrderInspectDao;
import com.unind.qms.web.shipment.dao.ShipmentInspectDao;
import com.unind.qms.web.shipment.entity.OrderInspect;
import com.unind.qms.web.shipment.entity.ShipmentInspect;
import com.unind.qms.web.shipment.service.ShipmentInspectService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import java.util.*;

@Service
@Transactional(rollbackFor = BusinessException.class)
public class ShipmentInspectImpl extends BaseOprService implements ShipmentInspectService {
    @Autowired
    private ShipmentInspectDao shipmentInspectDao;
    @Autowired
    private OrderInspectDao orderInspectDao;

    @Transactional(propagation= Propagation.REQUIRED)
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
            if(!SecurityUtils.getSubject().isAuthenticated()){
                shipmentInspect.setPkCreatedBy(Long.parseLong("1"));
            }else shipmentInspect.setPkCreatedBy(SessionContextUtils.getCurrentUser().getId());

            //获取产品包装规格数据
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("item_no", orderInspect.getBsPrCode());

            StringBuffer sqlbf1 = new StringBuffer();
            sqlbf1.append(" SELECT a.* FROM PMP_BASE_PALLET a");
            sqlbf1.append(" WHERE a.item_no = :item_no AND a.bs_is_del = '0'");
            Map<String, Object> recorderMap = super.findOneBySql(sqlbf1.toString(), param);
            if(null != recorderMap) {
                int boxNum = orderInspect.getBsPoQty();
                if(Integer.parseInt(recorderMap.get("BS_PACK_SIZE").toString()) != 0){
                    boxNum = (int)Math.ceil((double)orderInspect.getBsPoQty()/Double.parseDouble(recorderMap.get("BS_PACK_SIZE").toString()));
                }
                shipmentInspect.setBsBoxesNum(boxNum);
                shipmentInspect.setBsPackStackNum(Integer.parseInt(recorderMap.get("BS_BOX_SIZE").toString()));
                shipmentInspect.setBsPackBoxNum(Integer.parseInt(recorderMap.get("BS_PACK_SIZE").toString()));
            }

            //客户信息
            param = new HashMap<String, Object>();
            param.put("bs_item_code", orderInspect.getBsPrCode());

            sqlbf1 = new StringBuffer();
            sqlbf1.append(" SELECT a.* FROM erp_item_customer_agg a");
            sqlbf1.append(" WHERE a.bs_item_code = :bs_item_code AND a.bs_is_del = '0' AND bs_status = 'A'");
            List<Map<String, Object>> recordList = super.findBySql(sqlbf1.toString(), param);
            String customer = "";
            if(recordList.size()>0) {
                for(Map<String, Object> map:recordList){
                    if(customer.equals("")){
                        customer += map.get("BS_CUSTOMER_NAME");
                    }else customer += "/" + map.get("BS_CUSTOMER_NAME");
                }
            }
            shipmentInspect.setBsCustomer(customer);

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