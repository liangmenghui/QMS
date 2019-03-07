package com.unind.qms.web.approved.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.approved.entity.ApprovedItemsResult;

import java.nio.charset.Charset;

/**
 * 审批流程结果
 * @author chen
 *
 */
public interface ApprovedItemsResultService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	public static final Charset CHARSET = Charset.forName("UTF-8");

	public ApiResponseResult add(ApprovedItemsResult approvedItemsResult) throws BusinessException;

	public ApiResponseResult delete(Long id) throws BusinessException;
}
