package com.unind.qms.web.risk.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.basic.entity.FeedbackInfo;
import com.unind.qms.web.risk.entity.RiskLevelRecord;
import com.unind.qms.web.risk.service.RiskLevelRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 风险管理记录
 * @author Shen
 *
 */
@Api(description = "风险管理记录模块")
@RestController
@RequestMapping(value = Constants.CONTEXT_PATH + "/riskLevelRecord")
public class RiskLevelRecordController extends WebController {

    @Autowired
    private RiskLevelRecordService riskLevelRecordService;

    @ApiOperation(value="新增风险管理记录", notes="新增风险管理记录")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResponseResult add(RiskLevelRecord riskLevelRecord){
        try{
            return riskLevelRecordService.add(riskLevelRecord);
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value="删除风险管理记录", notes="删除风险管理记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = "Long", paramType="query",defaultValue="")
    })
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ApiResponseResult delete(Long id){
        try{
            return riskLevelRecordService.delete(id);
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value="获取风险管理记录", notes="获取风险管理记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = "Long", paramType="query",defaultValue=""),
            @ApiImplicitParam(name = "bsType", value = "类型（12.供应商，23.产品）", dataType = "Integer", paramType="query",defaultValue=""),
            @ApiImplicitParam(name = "bsPrSuppId", value = "产品ID/供应商ID", dataType = "Long", paramType="query",defaultValue=""),
    })
    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    public ApiResponseResult getlist(Long id, Integer bsType, Long bsPrSuppId){
        try{
            Sort sort = new Sort(Sort.Direction.DESC, "id");
            return riskLevelRecordService.getlist(id, bsType, bsPrSuppId, super.getPageRequest(sort));
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value="客诉直升高风险", notes="客诉直升高风险")
    @RequestMapping(value = "/updateRisk", method = RequestMethod.GET)
    public ApiResponseResult updateRisk(FeedbackInfo feedbackInfo, String fileIdStr){
        try{
            logger.info("客诉相关的供应商和产品直升高风险开始！");
            return riskLevelRecordService.updateRisk(feedbackInfo);
        }catch(Exception e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

}
