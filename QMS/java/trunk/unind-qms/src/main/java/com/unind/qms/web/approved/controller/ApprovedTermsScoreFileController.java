package com.unind.qms.web.approved.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.approved.service.ApprovedTermsScoreFileService;
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
 * 条款得分附件关联
 * @author chen
 *
 */
@Api(description = "条款得分附件关联")
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/approvedTermsScoreFile")
public class ApprovedTermsScoreFileController extends WebController {
	@Autowired
	private ApprovedTermsScoreFileService approvedTermsScoreFileService;

	@ApiOperation(value="新增条款得分附件", notes="新增条款得分附件")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bsTermsScoreId", value = "条款得分ID", dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(
			@RequestParam(value = "bsTermsScoreId", required = true) Long bsTermsScoreId, MultipartFile file) {
		try {
			return approvedTermsScoreFileService.add(bsTermsScoreId,file);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="删除条款得分附件", notes="删除条款得分附件")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApiResponseResult delete(Long id) {
		try {
			if(null!=id && id==-1) {
				return ApiResponseResult.failure("没有删除权限");
			}
			return approvedTermsScoreFileService.delete(id);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}
}