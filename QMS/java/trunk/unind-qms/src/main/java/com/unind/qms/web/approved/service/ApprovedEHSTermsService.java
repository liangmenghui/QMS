package com.unind.qms.web.approved.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.approved.entity.ApprovedEHSRecord;
import com.unind.qms.web.approved.entity.ApprovedEHSTerms;
import com.unind.qms.web.approved.entity.ApprovedTerms;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 审批EHS条款
 * @author fyx
 *
 */
public interface ApprovedEHSTermsService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	public static final Charset CHARSET = Charset.forName("UTF-8");

	public ApiResponseResult add(ApprovedEHSTerms approvedEHSTerms) throws BusinessException;

	public ApiResponseResult edit(ApprovedEHSTerms approvedTerms) throws BusinessException;

	public ApiResponseResult delete(Long id) throws BusinessException;

	public ApiResponseResult getlist(String keyWord, Long itemsId, Long itemsRecordId,  PageRequest pageRequest) throws BusinessException;

	public ApiResponseResult addEHSRecord(List<ApprovedEHSRecord> approvedEHSRecordList,String mapId,String supplierId) throws BusinessException;
	
	public ApiResponseResult importExcel(MultipartFile file) throws Exception;
	
	public ApiResponseResult getRecords(String keyWord, Long supplierId,  PageRequest pageRequest) throws BusinessException;
	
	public ApiResponseResult getItemList(String keyWord,  PageRequest pageRequest) throws BusinessException;
}
