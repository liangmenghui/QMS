package com.unind.qms.web.approved.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.approved.entity.ApprovedItems;
import com.unind.qms.web.approved.service.ApprovedItemsRecordFileService;
import com.unind.qms.web.approved.service.ApprovedItemsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 审批项目记录附件关联
 * @author chen
 *
 */
@Api(description = "审批项目记录附件关联")
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/approvedItemsRecordFile")
public class ApprovedItemsRecordFileController extends WebController {
	@Autowired
	private ApprovedItemsRecordFileService approvedItemsRecordFileService;

	@ApiOperation(value="新增审批项目记录附件", notes="新增审批项目记录附件")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bsItemsRecordId", value = "项目记录ID", dataType = "Long", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "file", value = "附件", dataType = "MultipartFile", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(
			@RequestParam(value = "bsItemsRecordId", required = true) Long bsItemsRecordId, MultipartFile file) {
		try {
			return approvedItemsRecordFileService.add(bsItemsRecordId,file);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="删除审批项目记录附件", notes="删除审批项目记录附件")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApiResponseResult delete(Long id) {
		try {
			if(null!=id && id==-1) {
				return ApiResponseResult.failure("没有删除权限");
			}
			return approvedItemsRecordFileService.delete(id);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}
}
