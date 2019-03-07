package com.unind.qms.web.approved.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.approved.entity.ApprovedTermsScore;
import org.springframework.data.domain.PageRequest;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 审批条款评分
 * @author chen
 *
 */
public interface ApprovedTermsScoreService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	public static final Charset CHARSET = Charset.forName("UTF-8");

	public ApiResponseResult add(List<ApprovedTermsScore> approvedTermsScoreList) throws BusinessException;

//	public ApiResponseResult edit(List<ApprovedTermsScore> approvedTermsScoreList) throws BusinessException;

	public ApiResponseResult getlist(Long bsItemsRecordId, PageRequest pageRequest) throws BusinessException;
}
