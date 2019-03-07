package com.unind.qms.web.samplePlan.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.samplePlan.entity.SampleLevelTotalCode;
import com.unind.qms.web.samplePlan.service.SampleLevelTotalCodeService;
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
 * 检验水平—批量—样本代字关系
 */
@Api(description = "检验水平,批量,样本代字关系模块")
@RestController
@RequestMapping(value = Constants.CONTEXT_PATH  + "/sampleLevelTotalCode")
public class SampleLevelTotalCodeController extends WebController {
    @Autowired
    private SampleLevelTotalCodeService sampleLevelTotalCodeService;

    @ApiOperation(value = "修改关系", notes = "修改关系")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ApiResponseResult edit(SampleLevelTotalCode sampleLevelTotalCode){
        try{
            return sampleLevelTotalCodeService.edit(sampleLevelTotalCode);
        }catch (BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "获取关系", notes = "获取关系")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bsLowerLimit", value = "批量下限", dataType = "Integer", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "bsUpperLimit", value = "批量上限", dataType = "Integer", paramType = "query", defaultValue = "")
    })
    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    public ApiResponseResult getlist(Integer bsLowerLimit, Integer bsUpperLimit){
        try{
            //按批量下限顺序排序
            Sort sort = new Sort(Sort.Direction.ASC, "bsLowerLimit");
            return sampleLevelTotalCodeService.getlist(bsLowerLimit, bsUpperLimit, super.getPageRequest());
        }catch (BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

}
