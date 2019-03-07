package com.unind.qms.web.risk.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.risk.entity.RiskEvidence;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.Charset;

/**
 * 风险证据描述
 * @author Shen
 */
public interface RiskEvidenceService {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
    public static final Charset CHARSET = Charset.forName("UTF-8");

    public ApiResponseResult add(RiskEvidence riskEvidence, MultipartFile file) throws BusinessException;

    public ApiResponseResult delete(Long id) throws BusinessException;

    public ApiResponseResult getlist(Long bsPrId, Long bsSuppId, Integer bsRiskNo, PageRequest pageRequest) throws BusinessException;
}
