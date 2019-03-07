package com.unind.qms.web.risk.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.basic.entity.FeedbackInfo;
import com.unind.qms.web.risk.entity.RiskLevelRecord;
import org.springframework.data.domain.PageRequest;

import java.nio.charset.Charset;

/**
 * @author Shen
 */
public interface RiskLevelRecordService {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
    public static final Charset CHARSET = Charset.forName("UTF-8");

    public ApiResponseResult add(RiskLevelRecord riskLevelRecord) throws BusinessException;

    public ApiResponseResult edit(RiskLevelRecord riskLevelRecord) throws BusinessException;

    public ApiResponseResult delete(Long id) throws BusinessException;

    public ApiResponseResult getlist(Integer bsType, Long bsPrSuppId, PageRequest pageRequest) throws BusinessException;

    public ApiResponseResult updateRisk(FeedbackInfo feedbackInfo) throws Exception;
}
