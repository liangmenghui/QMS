package com.unind.qms.web.basic.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.basic.entity.FeedbackInfoExtra;
import org.springframework.data.domain.PageRequest;

import java.nio.charset.Charset;

/**
 * 客诉附加信息
 */
public interface FeedbackInfoExtraService {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
    public static final Charset CHARSET = Charset.forName("UTF-8");

    public ApiResponseResult add(FeedbackInfoExtra feedbackInfoExtra) throws BusinessException;

    public ApiResponseResult edit(FeedbackInfoExtra feedbackInfoExtra) throws BusinessException;

    public ApiResponseResult delete(Long id) throws BusinessException;

    public ApiResponseResult getlist(Long bsFeedbackId, Long bsSuppId, Long bsPrId, PageRequest pageRequest) throws BusinessException;
}
