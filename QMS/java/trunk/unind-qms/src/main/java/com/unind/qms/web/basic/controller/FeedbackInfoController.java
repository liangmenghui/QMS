package com.unind.qms.web.basic.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.user.SysUser;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.admin.user.SysUserService;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.provider.ExcelFeedbackService;
import com.unind.qms.provider.ExcelService;
import com.unind.qms.web.basic.entity.FeedbackInfo;
import com.unind.qms.web.basic.entity.FeedbackInfoExtra;
import com.unind.qms.web.basic.service.FeedbackInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 客诉信息
 * @author chen
 *
 */
@Api(description = "客诉信息模块")
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/feedbackInfo")
public class FeedbackInfoController extends WebController {
	@Autowired
	private FeedbackInfoService feedbackInfoService;
	@Autowired
	private SysUserService sysUserService;

	@ApiOperation(value="新增客诉信息", notes="新增客诉信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "fileIdStr", value = "文件表ID集合(,分割)", dataType = "String", paramType="query", defaultValue=""),
			@ApiImplicitParam(name = "refundIdStr", value = "客诉退款信息ID集合(,分割)", dataType = "String", paramType = "query", defaultValue = ""),
			@ApiImplicitParam(name = "feedbackInfoExtraStr", value = "客诉附加信息", dataType = "String", paramType = "query", defaultValue = "")
	})
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(FeedbackInfo feedbackInfo, String fileIdStr, String refundIdStr, String feedbackInfoExtraStr) {
		try {
			Gson gson = new Gson();
			List<FeedbackInfoExtra> feedbackInfoExtraList = gson.fromJson(feedbackInfoExtraStr, new TypeToken<List<FeedbackInfoExtra>>(){}.getType());
			return feedbackInfoService.add(feedbackInfo, fileIdStr, refundIdStr, feedbackInfoExtraList);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="编辑客诉信息", notes="编辑客诉信息")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ApiResponseResult edit(FeedbackInfo feedbackInfo, String fileIdStr) {
		try {
			return feedbackInfoService.edit(feedbackInfo, fileIdStr);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="删除客诉信息", notes="删除客诉信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID", dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApiResponseResult delete(Long id) {
		try {
			if(null!=id && id==-1) {
				return ApiResponseResult.failure("没有删除权限");
			}
			return feedbackInfoService.delete(id);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="获取客诉信息", notes="获取客诉信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "keyWord", value = "查询条件", dataType = "String", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "id", value = "ID", dataType = "Long", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "bsStatus", value = "状态（1.待处理 2.处理中 3.已处理）", dataType = "Integer", paramType = "query", defaultValue = "")
	})
	@RequestMapping(value = "/getlist", method = RequestMethod.GET)
	public ApiResponseResult getlist(String keyWord, Long id, Integer bsStatus) {
		try {
			Sort sort = new Sort(Sort.Direction.DESC,"id");
			return feedbackInfoService.getlist(keyWord ,id, bsStatus, super.getPageRequest(sort));
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="获取所有用户信息", notes="获取所有用户信息")
	@ApiImplicitParams({
	})
	@RequestMapping(value = "/getuser", method = RequestMethod.GET)
	public ApiResponseResult getuser(SysUser sysUser) {
		try {
			return feedbackInfoService.getuser(sysUser, super.getPageRequest());
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}
}
