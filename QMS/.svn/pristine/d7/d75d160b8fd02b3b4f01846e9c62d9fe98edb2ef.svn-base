package com.unind.qms.web.shipment.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.Charset;

/**
 * 出货检验报告附件关联
 * @author chen
 *
 */
public interface ShipmentInspectFileService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	public static final Charset CHARSET = Charset.forName("UTF-8");

	public ApiResponseResult add(Long bsShipmentId, MultipartFile file) throws BusinessException;

	public ApiResponseResult delete(Long id) throws BusinessException;

}
