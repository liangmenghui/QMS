package com.unind.qms.web.approved.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.approved.entity.ApprovedFlow;
import com.unind.qms.web.approved.entity.ApprovedFlowRecord;
import org.springframework.data.domain.PageRequest;

import java.nio.charset.Charset;

/**
 * 审批流程记录
 * @author chen
 *
 */
public interface ApprovedFlowRecordService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	public static final Charset CHARSET = Charset.forName("UTF-8");

	public ApiResponseResult add(ApprovedFlowRecord approvedFlowRecord, int bsType) throws BusinessException;

	public ApiResponseResult edit(Long id, String bsDesc, int bsResult) throws BusinessException;

	public ApiResponseResult close(Long id, String bsDesc) throws BusinessException;
//
//	public ApiResponseResult delete(Long id) throws BusinessException;

	public ApiResponseResult getlist(Long id, Long bsSuppId, Long bsPrId, PageRequest pageRequest) throws BusinessException;
}