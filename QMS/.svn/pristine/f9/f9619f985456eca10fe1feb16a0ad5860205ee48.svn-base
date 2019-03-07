package com.unind.qms.web.basic.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.basic.entity.FeedbackHandler;
import com.unind.qms.web.basic.service.FeedbackHandlerService;
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
 * 客诉处理人员关联
 * @author chen
 *
 */
@Api(description = "客诉处理人员关联模块")
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/feedbackHandler")
public class FeedbackHandlerController extends WebController {
	@Autowired
	private FeedbackHandlerService feedbackHandlerService;

	@ApiOperation(value="新增客诉处理人", notes="新增客诉处理人")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(FeedbackHandler feedbackHandler) {
		try {
			return feedbackHandlerService.add(feedbackHandler);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="编辑客诉处理人", notes="编辑客诉处理人")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ApiResponseResult edit(FeedbackHandler feedbackHandler) {
		try {
			return feedbackHandlerService.edit(feedbackHandler);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="删除客诉处理人", notes="删除客诉处理人")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID", dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApiResponseResult delete(Long id) {
		try {
			if(null!=id && id==-1) {
				return ApiResponseResult.failure("没有删除权限");
			}
			return feedbackHandlerService.delete(id);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="获取客诉处理人", notes="获取客诉处理人")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bsFeedbackId", value = "客诉ID", dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/getlist", method = RequestMethod.GET)
	public ApiResponseResult getlist(Long bsFeedbackId) {
		try {
			Sort sort = new Sort(Sort.Direction.DESC,"id");
			return feedbackHandlerService.getlist(bsFeedbackId, super.getPageRequest(sort));
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}
}
