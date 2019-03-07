package com.unind.qms.web.risk.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.risk.entity.RiskApprovedRecord;
import org.springframework.data.domain.PageRequest;

import java.nio.charset.Charset;

/**
 * 风险批准记录表
 * @author Shen
 */
public interface RiskApprovedRecordService {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
    public static final Charset CHARSET = Charset.forName("UTF-8");

    public ApiResponseResult edit(RiskApprovedRecord riskApprovedRecord) throws BusinessException;

    public ApiResponseResult getlist(Long id, Long bsPrId, Long bsSuppId, Integer bsStatus, PageRequest pageRequest) throws BusinessException;

    public ApiResponseResult approve(Long id, String bsApprovedAdvice, Integer bsStatus) throws BusinessException;
}
