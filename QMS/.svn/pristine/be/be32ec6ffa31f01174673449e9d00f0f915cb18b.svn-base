package com.unind.qms.web.risk.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.risk.entity.RiskApprovedRecord;
import com.unind.qms.web.risk.service.RiskApprovedRecordService;
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
 * 风险批准记录表模块
 * @author Shen
 */
@Api(description = "风险批准记录表模块")
@RestController
@RequestMapping(value = Constants.CONTEXT_PATH + "/riskApprovedRecord")
public class RiskApprovedRecordController extends WebController {
    @Autowired
    private RiskApprovedRecordService riskApprovedRecordService;

    @ApiOperation(value="修改风险批准记录", notes="修改风险批准记录")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ApiResponseResult edit(RiskApprovedRecord riskApprovedRecord){
        try{
            return riskApprovedRecordService.edit(riskApprovedRecord);
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value="获取风险批准记录", notes="获取风险批准记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "记录ID", dataType = "Long", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "bsPrId", value = "产品ID", dataType = "Long", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "bsSuppId", value = "供应商ID", dataType = "Long", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "bsStatus", value = "批准状态（0:进行中, 1:同意, 2:拒绝）", dataType = "Integer", paramType = "query", defaultValue = "")
    })
    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    public ApiResponseResult getlist(Long id, Long bsPrId, Long bsSuppId, Integer bsStatus){
        try{
            Sort sort = new Sort(Sort.Direction.DESC, "id");
            return riskApprovedRecordService.getlist(id, bsPrId, bsSuppId, bsStatus, super.getPageRequest(sort));
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value="批准修改风险等级", notes="批准修改风险等级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "记录ID", dataType = "Long", paramType="query",defaultValue=""),
            @ApiImplicitParam(name = "bsApprovedAdvice", value = "批准意见", dataType = "String", paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "bsStatus", value = "批准状态（0:进行中, 1:同意, 2:拒绝）", dataType = "Integer", paramType = "query",defaultValue = "")
    })
    @RequestMapping(value = "/approve", method = RequestMethod.POST)
    public ApiResponseResult approve(Long id, String bsApprovedAdvice, Integer bsStatus){
        try{
            return riskApprovedRecordService.approve(id, bsApprovedAdvice, bsStatus);
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

}
