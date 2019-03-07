package com.unind.qms.web.basic.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.basic.entity.TodoInfo;
import org.springframework.data.domain.PageRequest;

import java.nio.charset.Charset;

/**
 * 待办事项
 * @author chen
 *
 */
public interface TodoInfoService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	public static final Charset CHARSET = Charset.forName("UTF-8");

	public ApiResponseResult add(TodoInfo todoInfo, Long roleId) throws BusinessException;

	public ApiResponseResult edit(TodoInfo todoInfo) throws BusinessException;

	public ApiResponseResult close(Long id, Long bsUserId, Long roleId, Long bsReferId) throws BusinessException;

	public ApiResponseResult delete(Long id) throws BusinessException;

	public ApiResponseResult getlist(int bsStatus, PageRequest pageRequest) throws BusinessException;
}