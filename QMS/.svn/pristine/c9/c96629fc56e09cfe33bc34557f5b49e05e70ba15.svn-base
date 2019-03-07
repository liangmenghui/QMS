package com.unind.qms.web.approved.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.approved.entity.ApprovedItems;
import org.springframework.data.domain.PageRequest;

import java.nio.charset.Charset;

/**
 * 审批项目
 * @author chen
 *
 */
public interface ApprovedItemsService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	public static final Charset CHARSET = Charset.forName("UTF-8");

	public ApiResponseResult add(ApprovedItems approvedItems, String termsStr, String userStr) throws BusinessException;

	public ApiResponseResult edit(ApprovedItems approvedItems, String termsStr, String userStr) throws BusinessException;

	public ApiResponseResult delete(Long id) throws BusinessException;

	public ApiResponseResult getlist(Long bsFlowId, Long id, PageRequest pageRequest) throws BusinessException;
}
