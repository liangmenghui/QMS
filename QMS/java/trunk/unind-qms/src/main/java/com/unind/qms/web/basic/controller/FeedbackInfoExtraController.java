package com.unind.qms.web.basic.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.basic.entity.FeedbackInfoExtra;
import com.unind.qms.web.basic.service.FeedbackInfoExtraService;
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
 * 客诉附加信息模块
 */
@Api(description = "客诉附加信息模块")
@RestController
@RequestMapping(value = Constants.CONTEXT_PATH  + "/feedbackInfoExtra")
public class FeedbackInfoExtraController extends WebController {

    @Autowired
    private FeedbackInfoExtraService feedbackInfoExtraService;

    @ApiOperation(value = "新增客诉附加信息", notes = "新增客诉附加信息")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResponseResult add(FeedbackInfoExtra feedbackInfoExtra){
        try{
            return feedbackInfoExtraService.add(feedbackInfoExtra);
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "修改客诉附加信息", notes = "修改客诉附加信息")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ApiResponseResult edit(FeedbackInfoExtra feedbackInfoExtra){
        try{
            return feedbackInfoExtraService.edit(feedbackInfoExtra);
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "删除客诉附加信息", notes = "删除客诉附加信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", dataType = "Long", paramType = "query", defaultValue = ""),
    })
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ApiResponseResult delete(Long id){
        try{
            return feedbackInfoExtraService.delete(id);
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "获取客诉附加信息", notes = "获取客诉附加信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bsFeedbackId", value = "客诉ID", dataType = "Long", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "bsSuppId", value = "供应商ID", dataType = "Long", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "bsPrId", value = "产品ID", dataType = "Long", paramType = "query", defaultValue = "")
    })
    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    public ApiResponseResult getlist(Long bsFeedbackId, Long bsSuppId, Long bsPrId){
        try{
            Sort sort = new Sort(Sort.Direction.DESC, "id");
            return feedbackInfoExtraService.getlist(bsFeedbackId, bsSuppId, bsPrId, super.getPageRequest(sort));
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

}
