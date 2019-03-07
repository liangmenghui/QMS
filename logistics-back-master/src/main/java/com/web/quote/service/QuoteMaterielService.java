package com.web.quote.service;

import com.app.base.data.ApiResponseResult;
import com.web.quote.entity.QuoteMateriel;
import org.springframework.data.domain.PageRequest;

/**
 * 新料报价物料关联表（报价明细）
 *
 */
public interface QuoteMaterielService {

    public ApiResponseResult add(QuoteMateriel quoteMateriel) throws Exception;

    public ApiResponseResult edit(QuoteMateriel quoteMateriel) throws Exception;

    public ApiResponseResult delete(Long id) throws Exception;

    public ApiResponseResult getlist(String keyword, PageRequest pageRequest) throws Exception;
}
