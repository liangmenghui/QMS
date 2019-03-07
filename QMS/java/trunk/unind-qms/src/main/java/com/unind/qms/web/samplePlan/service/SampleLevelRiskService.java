package com.unind.qms.web.samplePlan.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.samplePlan.entity.SampleLevelRisk;
import org.springframework.data.domain.PageRequest;

import java.nio.charset.Charset;

/**
 * 产品风险等级——检验水平关系
 */
public interface SampleLevelRiskService {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
    public static final Charset CHARSET = Charset.forName("UTF-8");

    public ApiResponseResult edit(SampleLevelRisk sampleLevelRisk) throws BusinessException;

    public ApiResponseResult getlist(Integer bsRiskLevel, PageRequest pageRequest) throws BusinessException;
}
