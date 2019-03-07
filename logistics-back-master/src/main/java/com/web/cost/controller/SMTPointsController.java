package com.web.cost.controller;

import com.app.base.control.WebController;
import com.app.base.data.ApiResponseResult;
import com.web.cost.entity.SMTPoints;
import com.web.cost.service.SMTPointsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * SMT点数
 *
 */
@Api(value = "SMT点数信息模块")
@CrossOrigin
@ControllerAdvice
@RestController
@RequestMapping(value = "/smtPoints")
public class SMTPointsController extends WebController {

    @Autowired
    private SMTPointsService smtPointsService;

    @ApiOperation(value = "获取SMT点数信息", notes = "获取SMT点数信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "setStatus", value = "是否设置（1：已设置 / 2：未设置）", required = false, dataType = "Integer", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "keyword", value = "关键字", required = false, dataType = "String", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "parentId", value = "父级ID", required = false, dataType = "Integer", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "sLevel", value = "等级", required = false, dataType = "Integer", paramType = "query", defaultValue = "")
    })
    @RequestMapping(value = "/getTreeList", method = RequestMethod.GET)
    public ApiResponseResult getTreeList(@RequestParam(value = "setStatus", required = false)Integer setStatus,
                                         @RequestParam(value = "keyword", required = false) String keyword,
                                         @RequestParam(value = "parentId", required = false) Integer parentId,
                                         @RequestParam(value = "sLevel", required = false) Integer sLevel){
        try{
            return smtPointsService.getTreeList(setStatus, keyword, parentId, sLevel, super.getPageRequest());
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return ApiResponseResult.failure("获取SMT点数信息失败！");
        }
    }

    @ApiOperation(value = "修改SMT点数信息", notes = "修改SMT点数信息")
    @RequestMapping(value = "/updatePoints", method = RequestMethod.POST)
    public ApiResponseResult updatePoints(@RequestBody(required = false) SMTPoints smtPoints){
        try{
            return smtPointsService.updatePoints(smtPoints);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return ApiResponseResult.failure("修改SMT点数信息失败！");
        }
    }
}
