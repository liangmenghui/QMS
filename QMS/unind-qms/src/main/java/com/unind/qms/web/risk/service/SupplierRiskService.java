package com.unind.qms.web.risk.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.risk.entity.SupplierRisk;
import org.springframework.data.domain.PageRequest;

import java.nio.charset.Charset;

/**
 * @author Shen
 */
public interface SupplierRiskService {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
    public static final Charset CHARSET = Charset.forName("UTF-8");

    public ApiResponseResult add(SupplierRisk supplierRisk) throws BusinessException;

    public ApiResponseResult edit(SupplierRisk supplierRisk) throws BusinessException;

    public ApiResponseResult delete(Long id) throws BusinessException;

    public ApiResponseResult getlist(Long bsSuppId, PageRequest pageRequest) throws BusinessException;

}