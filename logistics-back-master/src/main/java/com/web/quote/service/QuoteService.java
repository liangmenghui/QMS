package com.web.quote.service;

import com.app.base.data.ApiResponseResult;
import com.web.quote.entity.Quote;
import org.springframework.data.domain.PageRequest;

import java.util.Date;

/**
 * 新料报价表
 *
 */
public interface QuoteService {

    public ApiResponseResult add(Quote quote) throws Exception;

    public ApiResponseResult edit(Quote quote) throws Exception;

    public ApiResponseResult delete(Long id) throws Exception;

    public ApiResponseResult getlist(Integer qtStatus, String keyword, Date startDate, Date endDate, PageRequest pageRequest) throws Exception;

    //获取报价单详情
    public ApiResponseResult getQuoteInfo(Long id) throws Exception;

    //确认报价
    public ApiResponseResult doQuote(Quote quote) throws Exception;
}
