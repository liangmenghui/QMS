package com.unind.qms.web.samplePlan.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.samplePlan.entity.SampleLevelCodeNum;
import com.unind.qms.web.samplePlan.service.SampleLevelCodeNumService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 检验水平—样本代字—抽样数关系
 */
@Api(description = "检验水平,样本代字,抽样关系模块")
@RestController
@RequestMapping(value = Constants.CONTEXT_PATH  + "/sampleLevelCodeNum")
public class SampleLevelCodeNumController extends WebController {
    @Autowired
    private SampleLevelCodeNumService sampleLevelCodeNumService;

    @ApiOperation(value = "修改关系", notes = "修改关系")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ApiResponseResult edit(SampleLevelCodeNum sampleLevelCodeNum){
        try{
            return sampleLevelCodeNumService.edit(sampleLevelCodeNum);
        }catch (BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "获取关系", notes = "获取关系")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bsCode", value = "样本代字", dataType = "String", paramType = "query", defaultValue = ""),
    })
    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    public ApiResponseResult getlist(String bsCode){
        try{
            //按样本代字序号顺序排序
            Sort sort = new Sort(Sort.Direction.ASC, "bsCodeNo");
            return sampleLevelCodeNumService.getlist(bsCode, super.getPageRequest(sort));
        }catch (BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "根据风险等级和批量获取抽样数", notes = "根据风险等级和批量获取抽样数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bsRiskLevel", value = "风险等级", dataType = "Integer", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "bsTotal", value = "批量", dataType = "Integer", paramType = "query", defaultValue = "")
    })
    @RequestMapping(value = "/getSampleNumber", method = RequestMethod.POST)
    public ApiResponseResult getSampleNumber(Integer bsRiskLevel, Integer bsTotal){
        try{
            return sampleLevelCodeNumService.getSampleNumber(bsRiskLevel, bsTotal);
        }catch (BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

}
