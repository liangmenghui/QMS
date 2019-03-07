package com.unind.qms.web.approved.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.approved.entity.ApprovedTerms;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.Charset;

/**
 * 审批条款
 * @author chen
 *
 */
public interface ApprovedTermsService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	public static final Charset CHARSET = Charset.forName("UTF-8");

	public ApiResponseResult add(ApprovedTerms approvedTerms) throws BusinessException;

	public ApiResponseResult edit(ApprovedTerms approvedTerms) throws BusinessException;

	public ApiResponseResult delete(Long id) throws BusinessException;

	public ApiResponseResult getlist(String keyWord, Long itemsId, Long itemsRecordId, Integer bsType, PageRequest pageRequest) throws BusinessException;

	public ApiResponseResult importExcel(MultipartFile file) throws Exception;
}
