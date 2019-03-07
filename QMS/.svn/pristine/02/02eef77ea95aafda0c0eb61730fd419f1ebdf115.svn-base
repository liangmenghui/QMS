package com.unind.qms.web.risk.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.risk.entity.RiskLevelManagement;
import com.unind.qms.web.risk.service.RiskLevelManagementService;
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
 * 风险等级管理（审核周期和检验水平）
 * @author Shen
 */
@Api(description = "风险等级管理模块")
@RestController
@RequestMapping(value = Constants.CONTEXT_PATH + "/riskLevelManagement")
public class RiskLevelManagementController extends WebController {

    @Autowired
    private RiskLevelManagementService riskLevelManagementService;

    @ApiOperation(value = "新增风险等级管理", notes = "新增风险等级管理")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResponseResult add(RiskLevelManagement riskLevelManagement){
        try{
            return riskLevelManagementService.add(riskLevelManagement);
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "修改风险等级管理", notes = "修改风险等级管理")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ApiResponseResult edit(RiskLevelManagement riskLevelManagement){
        try{
            return riskLevelManagementService.edit(riskLevelManagement);
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "删除风险等级管理", notes = "删除风险等级管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = "Long", paramType = "query",defaultValue = "")
    })
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ApiResponseResult delete(Long id){
        try{
            return riskLevelManagementService.delete(id);
        }catch (BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "获取风险等级管理", notes = "获取风险等级管理")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bsRiskLevel", value = "风险等级", dataType = "Integer", paramType = "query", defaultValue = "")
    })
    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    public ApiResponseResult getlist(Integer bsRiskLevel){
        try{
            Sort sort = new Sort(Sort.Direction.ASC, "bsRiskLevel");
            return riskLevelManagementService.getlist(bsRiskLevel, super.getPageRequest(sort));
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

}
