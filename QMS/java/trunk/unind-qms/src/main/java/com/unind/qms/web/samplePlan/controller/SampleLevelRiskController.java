package com.unind.qms.web.samplePlan.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.samplePlan.entity.SampleLevelRisk;
import com.unind.qms.web.samplePlan.service.SampleLevelRiskService;
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
 * 产品风险等级—检验水平关系
 */
@Api(description = "产品风险等级,检验水平关系模块")
@RestController
@RequestMapping(value = Constants.CONTEXT_PATH  + "/sampleLevelRisk")
public class SampleLevelRiskController extends WebController {
    @Autowired
    private SampleLevelRiskService sampleLevelRiskService;

    @ApiOperation(value = "修改关系", notes = "修改关系")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ApiResponseResult edit(SampleLevelRisk sampleLevelRisk){
        try{
            return sampleLevelRiskService.edit(sampleLevelRisk);
        }catch (BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "获取关系", notes = "获取关系")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bsRiskLevel", value = "风险等级", dataType = "Integer", paramType = "query", defaultValue = "")
    })
    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    public ApiResponseResult getlist(Integer bsRiskLevel){
        try{
            //按风险等级顺序排序
            Sort sort = new Sort(Sort.Direction.ASC, "bsRiskLevel");
            return sampleLevelRiskService.getlist(bsRiskLevel, super.getPageRequest(sort));
        }catch (BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

}
