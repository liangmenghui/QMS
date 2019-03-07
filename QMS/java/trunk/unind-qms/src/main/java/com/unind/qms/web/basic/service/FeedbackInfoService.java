package com.unind.qms.web.basic.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.user.SysUser;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.basic.entity.FeedbackInfo;
import com.unind.qms.web.basic.entity.FeedbackInfoExtra;
import org.springframework.data.domain.PageRequest;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 客诉信息
 * @author chen
 *
 */
public interface FeedbackInfoService {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	public static final Charset CHARSET = Charset.forName("UTF-8");

	public ApiResponseResult add(FeedbackInfo feedbackInfo, String fileIdStr, String refundIdStr, List<FeedbackInfoExtra> feedbackInfoExtraList) throws BusinessException;

	public ApiResponseResult edit(FeedbackInfo feedbackInfo, String fileIdStr) throws BusinessException;

	public ApiResponseResult delete(Long id) throws BusinessException;

	public ApiResponseResult getlist(String keyWord, Long id, Integer bsStatus, PageRequest pageRequest) throws BusinessException;

	public ApiResponseResult getuser(SysUser sysUser, PageRequest pageRequest) throws BusinessException;
}