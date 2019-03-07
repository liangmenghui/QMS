package com.unind.qms.web.product.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.product.entity.ProductInfo;
import org.springframework.data.domain.PageRequest;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * 产品基本资料
 * @author chen
 *
 */
public interface ProductInfoService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	public static final Charset CHARSET = Charset.forName("UTF-8");

	public ApiResponseResult add(ProductInfo productInfo) throws BusinessException;

	public ApiResponseResult delete(Long id) throws BusinessException;

	public ApiResponseResult getlist(String keyWord, Long id, String suppCode, Integer bsIsApprove, Integer bsStatus, Integer bsRiskLevel, Integer bsIsSqe, Integer bsApprovedType, PageRequest pageRequest) throws BusinessException;

	public ApiResponseResult applyRiskLevel(Long id, Integer bsRiskLevelUser, String bsRiskDescUser) throws BusinessException;

	public ApiResponseResult completeRiskLevel(Long id, Long bsRecordId, Integer bsRiskLevelUser, String bsApprovedAdvice, Integer bsResult) throws BusinessException;

	public ApiResponseResult autoRiskLevel(Long id, Integer bsRiskManual) throws BusinessException;

	public ApiResponseResult getProcessApproved(String keyWord, Date startDate, Date endDate, Integer bsConclusion, PageRequest pageRequest) throws BusinessException;
	
	public ApiResponseResult getProductApproved(String keyWord, Date startDate, Date endDate, Integer bsConclusion, PageRequest pageRequest) throws BusinessException;

}
