package com.unind.qms.web.approved.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.approved.entity.ApprovedFlowRecord;
import com.unind.qms.web.approved.service.ApprovedFlowRecordService;
import com.unind.qms.web.approved.service.ApprovedItemsRecordService;
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
 * 审核项目记录
 * @author chen
 *
 */
@Api(description = "审核项目记录模块")
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/approvedItemsRecord")
public class ApprovedItemsRecordController extends WebController {
	@Autowired
	private ApprovedItemsRecordService approvedItemsRecordService;

	@ApiOperation(value="审核项目记录", notes="审核项目记录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bsItemsId", value = "项目ID", dataType = "Long", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "bsItemsRecordId", value = "项目记录ID", dataType = "Long", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "bsResult", value = "审批类型（1:通过，2：驳回，3：不通过）", dataType = "int", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "resultDesc", value = "审批意见--结果表", dataType = "String", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "itemsDesc", value = "审批意见--项目记录表", dataType = "String", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/approved", method = RequestMethod.POST)
	public ApiResponseResult approved(Long bsItemsId, Long bsItemsRecordId, int bsResult, String resultDesc, String itemsDesc) {
		try {
			return approvedItemsRecordService.approved(bsItemsId, bsItemsRecordId, bsResult, resultDesc, itemsDesc);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="获取审核项目记录", notes="获取审核项目记录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bsFlowRecordId", value = "流程记录ID", dataType = "Long", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "id", value = "项目记录ID", dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/getlist", method = RequestMethod.GET)
	public ApiResponseResult getlist(Long bsFlowRecordId, Long id) {
		try {
			Sort sort = new Sort(Sort.Direction.ASC,"bsPriority");
			return approvedItemsRecordService.getlist(bsFlowRecordId, id, super.getPageRequest(sort));
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}
}
