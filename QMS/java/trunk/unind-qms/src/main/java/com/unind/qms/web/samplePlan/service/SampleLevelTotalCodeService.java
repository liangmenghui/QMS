package com.unind.qms.web.samplePlan.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.samplePlan.entity.SampleLevelTotalCode;
import org.springframework.data.domain.PageRequest;

import java.nio.charset.Charset;

/**
 * 检验水平——批量——样本代字关系
 */
public interface SampleLevelTotalCodeService {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
    public static final Charset CHARSET = Charset.forName("UTF-8");

    public ApiResponseResult edit(SampleLevelTotalCode sampleLevelTotalCode) throws BusinessException;

    public ApiResponseResult getlist(Integer bsLowerLimit, Integer bsUpperLimit, PageRequest pageRequest) throws BusinessException;
}
