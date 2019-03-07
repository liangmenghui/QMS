package com.unind.qms.web.risk.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.risk.entity.ProductPpm;
import org.springframework.data.domain.PageRequest;

import java.nio.charset.Charset;

/**
 * 产品风险ppm的不良品数
 * @author Shen
 */
public interface ProductPpmService {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
    public static final Charset CHARSET = Charset.forName("UTF-8");

    public ApiResponseResult add(ProductPpm productPpm) throws BusinessException;

    public ApiResponseResult edit(ProductPpm productPpm) throws BusinessException;

    public ApiResponseResult delete(Long id) throws BusinessException;

    public ApiResponseResult getlist(String keyWord, Long id, Long bsPrId, Integer bsYear, PageRequest pageRequest) throws BusinessException;
}
