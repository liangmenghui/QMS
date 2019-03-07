package com.unind.qms.web.basic.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.approved.service.ApprovedItemsRecordFileService;
import com.unind.qms.web.basic.service.FeedbackInfoFileService;
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
 * 客诉信息附件关联
 * @author chen
 *
 */
@Api(description = "客诉信息附件关联")
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/feedbackInfoFile")
public class FeedbackInfoFileController extends WebController {
	@Autowired
	private FeedbackInfoFileService feedbackInfoFileService;

//	@ApiOperation(value="新增客诉信息附件", notes="新增客诉信息附件")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "bsFeedbackId", value = "客诉信息ID", dataType = "Long", paramType="query",defaultValue=""),
//			@ApiImplicitParam(name = "file", value = "附件", dataType = "MultipartFile", paramType="query",defaultValue=""),
//	})
//	@RequestMapping(value = "/add", method = RequestMethod.POST)
//	public ApiResponseResult add(
//			@RequestParam(value = "bsFeedbackId", required = true) Long bsFeedbackId, MultipartFile file) {
//		try {
//			return feedbackInfoFileService.add(bsFeedbackId,file);
//		} catch (BusinessException e) {
//			e.printStackTrace();
//			return ApiResponseResult.failure(e.getMessage());
//		}
//	}

	@ApiOperation(value="删除客诉信息附件", notes="删除客诉信息附件")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApiResponseResult delete(Long id) {
		try {
			if(null!=id && id==-1) {
				return ApiResponseResult.failure("没有删除权限");
			}
			return feedbackInfoFileService.delete(id);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}
}
