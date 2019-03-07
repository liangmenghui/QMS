package com.unind.qms.web.approved.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.approved.entity.ApprovedFlow;
import org.springframework.data.domain.PageRequest;

import java.nio.charset.Charset;

/**
 * 审批流程
 * @author chen
 *
 */
public interface ApprovedFlowService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	public static final Charset CHARSET = Charset.forName("UTF-8");

	public ApiResponseResult add(ApprovedFlow approvedFlow) throws BusinessException;

	public ApiResponseResult edit(ApprovedFlow approvedFlow) throws BusinessException;

	public ApiResponseResult delete(Long id) throws BusinessException;

	public ApiResponseResult getlist(String keyWord, PageRequest pageRequest) throws BusinessException;

	public ApiResponseResult copy(Long id) throws BusinessException;
}
