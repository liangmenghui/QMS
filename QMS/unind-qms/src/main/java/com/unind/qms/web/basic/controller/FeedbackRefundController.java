package com.unind.qms.web.basic.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.basic.entity.FeedbackRefund;
import com.unind.qms.web.basic.service.FeedbackRefundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客诉退款信息
 * @author Shen
 *
 */
@Api(description = "客诉退款信息模块")
@RestController
@RequestMapping(value = Constants.CONTEXT_PATH  + "/feedbackRefund")
public class FeedbackRefundController extends WebController {
    @Autowired
    private FeedbackRefundService feedbackRefundService;

    @ApiOperation(value="新增客诉信息", notes="新增客诉信息")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResponseResult add(FeedbackRefund feedbackRefund){
        try {
            return feedbackRefundService.add(feedbackRefund);
        }catch (BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value="修改客诉信息", notes="修改客诉信息")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ApiResponseResult edit(FeedbackRefund feedbackRefund){
        try {
            return feedbackRefundService.edit(feedbackRefund);
        }catch (BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value="删除客诉信息", notes="删除客诉信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = "Long", required = true, paramType="query", defaultValue="")
    })
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ApiResponseResult delete(Long id){
        try {
            return feedbackRefundService.delete(id);
        }catch (BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "获取客诉退款信息", notes = "获取客诉退款信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = "Long", paramType="query", defaultValue=""),
            @ApiImplicitParam(name = "bsFeedbackId", value = "客诉ID", dataType = "Long", paramType="query", defaultValue=""),
            @ApiImplicitParam(name = "bsPrId", value = "产品ID", dataType = "Long", paramType="query", defaultValue=""),
            @ApiImplicitParam(name = "bsReportNo", value = "客户报告号", dataType = "String", paramType="query", defaultValue="")
    })
    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    public ApiResponseResult getlist(Long id, Long bsFeedbackId, Long bsPrId, String bsReportNo){
        try{
            Sort sort = new Sort(Sort.Direction.DESC, "id");
            return feedbackRefundService.getlist(id, bsFeedbackId, bsPrId, bsReportNo, super.getPageRequest(sort));
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

}
