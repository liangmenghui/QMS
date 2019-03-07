package com.unind.qms.web.risk.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.risk.entity.SupplierRisk;
import com.unind.qms.web.risk.service.SupplierRiskService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.Request;

/**
 * 供应商风险信息
 * @author Shen
 *
 */
@Api(description = "供应商风险信息模块")
@RestController
@RequestMapping(value = Constants.CONTEXT_PATH + "/supplierRisk")
public class SupplierRiskController extends WebController {

    @Autowired
    private SupplierRiskService supplierRiskService;

    @ApiOperation(value="新增供应商风险信息", notes="新增供应商风险信息")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResponseResult add(SupplierRisk supplierRisk){
        try{
            return supplierRiskService.add(supplierRisk);
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "修改供应商风险信息", notes = "修改供应商风险信息")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ApiResponseResult edit(SupplierRisk supplierRisk){
        try{
            return supplierRiskService.edit(supplierRisk);
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }
//    public ApiResponseResult edit(@RequestBody @ApiParam(name = "supplierRisk", value = "传入json格式", required = true) SupplierRisk supplierRisk){
//        try{
//            return supplierRiskService.edit(supplierRisk);
//        }catch(BusinessException e){
//            e.printStackTrace();
//            return ApiResponseResult.failure(e.getMessage());
//        }
//    }

    @ApiOperation(value = "删除供应商风险信息", notes = "删除供应商风险信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = "Long", paramType="query",defaultValue=""),
    })
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ApiResponseResult delete(Long id){
        try{
            if(id == null || id == -1){
                return ApiResponseResult.failure("没有删除权限");
            }
            return supplierRiskService.delete(id);
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value="获取供应商风险信息", notes="获取供应商风险信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bsSuppId", value = "供应商ID", dataType = "Long", paramType="query",defaultValue=""),
    })
    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    public ApiResponseResult getlist(Long bsSuppId){
        try{
            Sort sort = new Sort(Sort.Direction.DESC, "id");
            return supplierRiskService.getlist(bsSuppId, super.getPageRequest(sort));
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }
}
