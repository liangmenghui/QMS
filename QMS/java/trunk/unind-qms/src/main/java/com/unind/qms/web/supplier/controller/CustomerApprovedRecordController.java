package com.unind.qms.web.supplier.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.WebController;
import com.unind.qms.web.supplier.entity.CustomerApprovedRecord;
import com.unind.qms.web.supplier.service.CustomerApprovedRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.unind.base.web.Constants;

/**
 * 客户审核记录
 * @author Shen
 */
@Api(description = "客户审核记录模块")
@RestController
@RequestMapping(value = Constants.CONTEXT_PATH + "/customerApprovedRecord")
public class CustomerApprovedRecordController extends WebController {

    @Autowired
    private CustomerApprovedRecordService customerApprovedRecordService;

    @ApiOperation(value = "新增客户审核记录", notes = "新增客户审核记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileIdStr", value = "文件表ID集合(,分割)", dataType = "String", paramType = "query", defaultValue = "")
    })
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResponseResult add(CustomerApprovedRecord customerApprovedRecord, String fileIdStr){
        try{
            return customerApprovedRecordService.add(customerApprovedRecord, fileIdStr);
        }catch (BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "修改客户审核记录", notes = "修改客户审核记录")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ApiResponseResult edit(CustomerApprovedRecord customerApprovedRecord){
        try{
            return customerApprovedRecordService.edit(customerApprovedRecord);
        }catch (BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "删除客户审核记录", notes = "删除客户审核记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = "Long", paramType = "query", defaultValue = "")
    })
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ApiResponseResult delete(Long id){
        try{
            return customerApprovedRecordService.delete(id);
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "获取客户审核记录", notes = "获取客户审核记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = "Long", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "bsSuppId", value = "供应商ID", dataType = "Long", paramType = "query", defaultValue = "")
    })
    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    public ApiResponseResult getlist(Long id, Long bsSuppId){
        try{
            Sort sort = new Sort(Sort.Direction.DESC, "id");
            return customerApprovedRecordService.getlist(id, bsSuppId, super.getPageRequest(sort));
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.success(e.getMessage());
        }
    }

}
