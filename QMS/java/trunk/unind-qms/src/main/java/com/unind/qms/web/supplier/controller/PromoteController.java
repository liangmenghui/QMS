package com.unind.qms.web.supplier.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.supplier.entity.Promote;
import com.unind.qms.web.supplier.service.PromoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *不符合改进项
 * @author Shen
 */
@Api(description = "不符合改进项模块")
@RestController
@RequestMapping(value = Constants.CONTEXT_PATH  + "/promote")
public class PromoteController extends WebController {
    @Autowired
    private PromoteService promoteService;

    @ApiOperation(value="新增不符合改进项", notes="新增不符合改进项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bsItemsRecordId", value = "项目记录ID", dataType = "Long", required = true, paramType="query", defaultValue="")
    })
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResponseResult add(String promoteStr, Long bsItemsRecordId){
        try {
            Gson gson = new Gson();
            List<Promote> promoteList = gson.fromJson(promoteStr, new TypeToken<List<Promote>>(){}.getType());
            return promoteService.add(promoteList, bsItemsRecordId);
        }catch (BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value="修改不符合改进项", notes="修改不符合改进项")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ApiResponseResult edit(Promote promote){
        try {
            return promoteService.edit(promote);
        }catch (BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value="删除不符合改进项", notes="删除不符合改进项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = "Long", required = true, paramType="query", defaultValue="")
    })
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ApiResponseResult delete(Long id){
        try {
            return promoteService.delete(id);
        }catch (BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "获取不符合改进项", notes = "获取不符合改进项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = "Long", paramType="query", defaultValue=""),
            @ApiImplicitParam(name = "bsFlowRecordId", value = "审核流程记录ID", dataType = "Long", paramType="query", defaultValue=""),
            @ApiImplicitParam(name = "bsItemsRecordId", value = "审核项目记录ID", dataType = "Long", paramType="query", defaultValue="")
    })
    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    public ApiResponseResult getlist(Long id, Long bsFlowRecordId, Long bsItemsRecordId){
        try{
            Sort sort = new Sort(Sort.Direction.ASC, "id");
            return promoteService.getlist(id, bsFlowRecordId, bsItemsRecordId, super.getPageRequest(sort));
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "更新所有不符合改进项的内容", notes = "更新所有不符合改进项的内容")
    @RequestMapping(value = "/updateContent", method = RequestMethod.POST)
    public ApiResponseResult updateContent(){
        try{
            return promoteService.updateContent();
        }catch(Exception e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

}
