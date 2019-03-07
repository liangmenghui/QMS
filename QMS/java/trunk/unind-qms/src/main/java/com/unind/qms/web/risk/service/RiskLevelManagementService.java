package com.unind.qms.web.risk.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.risk.entity.RiskLevelManagement;
import org.springframework.data.domain.PageRequest;

import java.nio.charset.Charset;

/**
 * 风险等级管理（审核周期和检验水平）
 * @author Shen
 */
public interface RiskLevelManagementService {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
    public static final Charset CHARSET = Charset.forName("UTF-8");

    public ApiResponseResult add(RiskLevelManagement riskLevelManagement) throws BusinessException;

    public ApiResponseResult edit(RiskLevelManagement riskLevelManagement) throws BusinessException;

    public ApiResponseResult delete(Long id) throws BusinessException;

    public ApiResponseResult getlist(Integer bsRiskLevel, PageRequest pageRequest) throws BusinessException;
}
