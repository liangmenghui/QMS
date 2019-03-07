package com.unind.qms.web.basic.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.basic.entity.FeedbackRefund;
import org.springframework.data.domain.PageRequest;

import java.nio.charset.Charset;

/**
 * 客诉退款信息
 * @author Shen
 */
public interface FeedbackRefundService {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
    public static final Charset CHARSET = Charset.forName("UTF-8");

    public ApiResponseResult add(FeedbackRefund feedbackRefund) throws BusinessException;

    public ApiResponseResult edit(FeedbackRefund feedbackRefund) throws BusinessException;

    public ApiResponseResult delete(Long id) throws BusinessException;

    public ApiResponseResult getlist(Long id, Long bsFeedbackId, Long bsPrId, String bsReportNo, PageRequest pageRequest) throws BusinessException;
}
