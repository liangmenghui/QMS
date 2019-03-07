package com.unind.qms.web.approved.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.approved.entity.ApprovedFlow;
import com.unind.qms.web.approved.entity.ApprovedFlowRecord;
import com.unind.qms.web.approved.service.ApprovedFlowRecordService;
import com.unind.qms.web.approved.service.ApprovedFlowService;
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
 * 审核流程记录
 * @author chen
 *
 */
@Api(description = "审批流程记录模块")
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/approvedFlowRecord")
public class ApprovedFlowRecordController extends WebController {
	@Autowired
	private ApprovedFlowRecordService approvedFlowRecordService;

	@ApiOperation(value="新增审核流程记录", notes="新增审核流程记录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bsType", value = "产品类型，11：产品过程审核，12：成品检验，21：供应商体系审核，22：初期评审", required = true, dataType = "int", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "recorderStr", value = "审核人(逗号分隔)", required = true, dataType = "String", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(ApprovedFlowRecord approvedFlowRecord, int bsType, String recorderStr) {
		try {
			return approvedFlowRecordService.add(approvedFlowRecord, bsType, recorderStr);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="提交确定审核流程", notes="提交确定审核流程")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "流程记录ID", required = true, dataType = "Long", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "bsDesc", value = "流程记录说明", dataType = "String", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "bsResult", value = "流程结果(1：通过，2：未通过)", dataType = "int", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ApiResponseResult edit(Long id, String bsDesc, int bsResult) {
		try {
			return approvedFlowRecordService.edit(id, bsDesc, bsResult);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="关闭审核流程", notes="关闭审核流程")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "流程记录ID", required = true, dataType = "Long", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "bsDesc", value = "流程记录说明", dataType = "String", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/close", method = RequestMethod.POST)
	public ApiResponseResult close(Long id, String bsDesc) {
		try {
			return approvedFlowRecordService.close(id, bsDesc);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="获取审核流程记录", notes="获取审核流程记录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID", dataType = "Long", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "bsSuppId", value = "供应商ID", dataType = "Long", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "bsPrId", value = "产品ID", dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/getlist", method = RequestMethod.GET)
	public ApiResponseResult getlist(Long id, Long bsSuppId, Long bsPrId) {
		try {
			Sort sort = new Sort(Sort.Direction.DESC,"id");
			return approvedFlowRecordService.getlist(id, bsSuppId, bsPrId, super.getPageRequest(sort));
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}
}
