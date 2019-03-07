package com.unind.qms.web.risk.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.risk.entity.ProductPpm;
import com.unind.qms.web.risk.service.ProductPpmService;
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
 * 产品风险ppm的不良品数模块
 * @author Shen
 */
@Api(description = "产品风险ppm不良数模块")
@RestController
@RequestMapping(value = Constants.CONTEXT_PATH + "/productPpm")
public class ProductPpmController extends WebController {

    @Autowired
    private ProductPpmService productPpmService;

    @ApiOperation(value="新增不良数", notes="新增不良数")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResponseResult add(ProductPpm productPpm){
        try{
            return productPpmService.add(productPpm);
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value="修改不良数", notes="修改不良数")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ApiResponseResult edit(ProductPpm productPpm){
        try{
            return productPpmService.edit(productPpm);
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value="删除不良数", notes="删除不良数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = "Long", paramType = "query", defaultValue = "")
    })
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ApiResponseResult delete(Long id){
        try{
            return productPpmService.delete(id);
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "获取不良品数", notes = "获取不良品数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyWord", value = "查询条件", dataType = "String", paramType="query",defaultValue=""),
            @ApiImplicitParam(name = "id", value = "ID", dataType = "Long", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "bsPrId", value = "产品ID", dataType = "Long", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "bsYear", value = "年份", dataType = "Integer", paramType = "query", defaultValue = "")
    })
    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    public ApiResponseResult getlist(String keyWord, Long id, Long bsPrId, Integer bsYear){
        try{
            Sort sort = new Sort(Sort.Direction.DESC, "id");
            return productPpmService.getlist(keyWord, id, bsPrId, bsYear, super.getPageRequest(sort));
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }
}
