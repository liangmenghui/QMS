package com.unind.qms.web.supplier.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.supplier.service.CustomerApprovedFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 客户审核文件关联
 * @author Shen
 */
@Api(description = "客户审核文件关联")
@RestController
@RequestMapping(value = Constants.CONTEXT_PATH + "/customerApprovedFile")
public class CustomerApprovedFileController extends WebController {

    @Autowired
    private CustomerApprovedFileService customerApprovedFileService;

    @ApiOperation(value="新建客户审核文件关联", notes="新建客户审核文件关联")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bsCustomerApprovedId", value = "客户审核ID", dataType = "Long", paramType = "query", defaultValue = ""),
    })
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResponseResult add(@RequestParam(value = "bsCustomerApprovedId", required = true) Long bsCustomerApprovedId, MultipartFile file){
        try{
            return customerApprovedFileService.add(bsCustomerApprovedId, file);
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "删除客户审核文件", notes = "删除客户审核文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType = "query", defaultValue = "")
    })
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ApiResponseResult delete(Long id){
        try{
            if(id == null || id == -1){
                return ApiResponseResult.failure("没有删除权限");
            }
            return customerApprovedFileService.delete(id);
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }
}
