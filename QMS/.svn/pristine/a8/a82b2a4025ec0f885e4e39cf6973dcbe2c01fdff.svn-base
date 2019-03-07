package com.unind.qms.web.supplier.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.Charset;

/**
 * 客户审核文件关联
 * @author Shen
 */
public interface CustomerApprovedFileService {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
    public static final Charset CHARSET = Charset.forName("UTF-8");

    public ApiResponseResult add(Long bsCustomerApprovedId, MultipartFile file) throws BusinessException;

    public ApiResponseResult delete(Long id) throws BusinessException;
}
