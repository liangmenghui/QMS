package com.unind.qms.web.shipment.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.shipment.entity.OrderInspect;

import java.nio.charset.Charset;

/**
 * 验货订单
 * @author chen
 *
 */
public interface OrderInspectService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	public static final Charset CHARSET = Charset.forName("UTF-8");

	public ApiResponseResult add(OrderInspect orderInspect) throws BusinessException;
}
