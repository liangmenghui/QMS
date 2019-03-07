package com.web.quote.controller;

import com.app.aspect.MyLog;
import com.app.base.control.WebController;
import com.app.base.data.ApiResponseResult;
import com.web.quote.entity.Quote;
import com.web.quote.service.QuoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(description = "新料报价模块")
@CrossOrigin
@ControllerAdvice
@RestController
@RequestMapping(value = "/quote")
public class QuoteController extends WebController {

    @Autowired
    private QuoteService quoteService;

    @ApiOperation(value = "删除报价", notes = "删除报价")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = false, dataType = "Long", paramType = "query", defaultValue = "")
    })
    @PostMapping("/delete")
    public ApiResponseResult delete(@RequestParam(value = "id", required = false) Long id){
        try{
            return quoteService.delete(id);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return ApiResponseResult.failure("删除报价失败！");
        }
    }

    @ApiOperation(value = "获取报价列表", notes = "获取报价列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "qtStatus", value = "报价状态", required = false, dataType = "Integer", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "keyword", value = "关键字", required = false, dataType = "String", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "startDate", value = "报价日期", required = false, dataType = "Date", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "endDate", value = "报价截止日期", required = false, dataType = "Date", paramType = "query", defaultValue = "")
    })
    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    public ApiResponseResult getlist(@RequestParam(value = "qtStatus", required = false) Integer qtStatus,
                                     @RequestParam(value = "keyword", required = false) String keyword,
                                     @RequestParam(value = "startDate", required = false) Date startDate,
                                     @RequestParam(value = "endDate", required = false) Date endDate){
        try{
            Sort sort = new Sort(Sort.Direction.DESC, "id");
            return quoteService.getlist(qtStatus, keyword, startDate, endDate, super.getPageRequest(sort));
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return ApiResponseResult.failure("获取报价列表失败！");
        }
    }

    @ApiOperation(value = "获取报价单详情", notes = "获取报价单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = false, dataType = "Long", paramType = "query", defaultValue = "")
    })
    @RequestMapping(value = "/getQuoteInfo", method = RequestMethod.GET)
    public ApiResponseResult getQuoteInfo(Long id){
        try{
            return quoteService.getQuoteInfo(id);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return ApiResponseResult.failure("获取报价单详情失败！");
        }
    }

    @ApiOperation(value = "确认报价", notes = "确认报价")
    @PostMapping("/doQuote")
    public ApiResponseResult doQuote(@RequestBody(required = false) Quote quote){
        try{
            return quoteService.doQuote(quote);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return ApiResponseResult.failure("确认报价失败！");
        }
    }
}
