package com.unind.qms.web.basic.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.basic.entity.ExcelTemp;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.Charset;

/**
 * excel模板
 * @author chen
 *
 */
public interface ExcelTempService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	public static final Charset CHARSET = Charset.forName("UTF-8");

	public ApiResponseResult add(ExcelTemp excelTemp) throws BusinessException;

	public ApiResponseResult edit(ExcelTemp excelTemp) throws BusinessException;

	public ApiResponseResult delete(Long id) throws BusinessException;

	public ApiResponseResult getlist(Long id, PageRequest pageRequest) throws BusinessException;
}
