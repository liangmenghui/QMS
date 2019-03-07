package com.unind.qms.web.supplier.service;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.supplier.entity.CustomerApprovedRecord;
import org.springframework.data.domain.PageRequest;

import java.nio.charset.Charset;

/**
 * 客户审核记录
 * @author Shen
 */
public interface CustomerApprovedRecordService {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
    public static final Charset CHARSET = Charset.forName("UTF-8");

    public ApiResponseResult add(CustomerApprovedRecord customerApprovedRecord, String fileIdStr) throws BusinessException;

    public ApiResponseResult edit(CustomerApprovedRecord customerApprovedRecord) throws BusinessException;

    public ApiResponseResult delete(Long id) throws BusinessException;

    public ApiResponseResult getlist(Long id, Long bsSuppId, PageRequest pageRequest) throws BusinessException;
}
