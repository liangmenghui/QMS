package com.unind.qms.web.shipment.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.shipment.entity.ShipmentInspect;
import com.unind.qms.web.shipment.entity.ShipmentInspectRecord;
import org.springframework.data.domain.PageRequest;

import java.nio.charset.Charset;

/**
 * 出货检验报告
 * @author chen
 *
 */
public interface ShipmentInspectRecordService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	public static final Charset CHARSET = Charset.forName("UTF-8");

	public ApiResponseResult add(ShipmentInspectRecord shipmentInspectRecord) throws BusinessException;

	public ApiResponseResult edit(ShipmentInspectRecord shipmentInspectRecord) throws BusinessException;

	public ApiResponseResult delete(Long id) throws BusinessException;

	public ApiResponseResult getlist(Long bsShipmentId, PageRequest pageRequest) throws BusinessException;
}
