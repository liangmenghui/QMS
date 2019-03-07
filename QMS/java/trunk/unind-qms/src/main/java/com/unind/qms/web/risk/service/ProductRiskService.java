package com.unind.qms.web.risk.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.risk.entity.ProductRisk;
import org.springframework.data.domain.PageRequest;

import java.nio.charset.Charset;

/**
 * 出货检验报告
 * @author chen
 *
 */
public interface ProductRiskService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	public static final Charset CHARSET = Charset.forName("UTF-8");

	public ApiResponseResult add(ProductRisk productRisk) throws BusinessException;

	public ApiResponseResult edit(ProductRisk productRisk) throws BusinessException;

	public ApiResponseResult getlist(Long bsPrId, PageRequest pageRequest) throws BusinessException;

	public ProductRisk updateValueType(ProductRisk productRisk) throws BusinessException;

	public ProductRisk updatePpmValueType(ProductRisk productRisk) throws BusinessException;
}
