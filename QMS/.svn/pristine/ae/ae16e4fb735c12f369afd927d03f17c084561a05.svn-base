package com.unind.qms.web.approved.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.approved.entity.ApprovedFlow;
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
 * 审核流程
 * @author chen
 *
 */
@Api(description = "审批流程模块")
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/approvedFlow")
public class ApprovedFlowController extends WebController {
	@Autowired
	private ApprovedFlowService approvedFlowService;

	@ApiOperation(value="新增审核流程", notes="新增审核流程")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(ApprovedFlow approvedFlow) {
		try {
			return approvedFlowService.add(approvedFlow);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="编辑审核流程", notes="编辑审核流程")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ApiResponseResult edit(ApprovedFlow approvedFlow) {
		try {
			return approvedFlowService.edit(approvedFlow);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="删除审核流程", notes="删除审核流程")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApiResponseResult delete(Long id) {
		try {
			if(null!=id && id==-1) {
				return ApiResponseResult.failure("没有删除权限");
			}
			return approvedFlowService.delete(id);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="获取审核流程", notes="获取审核流程")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "keyWord", value = "查询条件", dataType = "String", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/getlist", method = RequestMethod.GET)
	public ApiResponseResult getlist(String keyWord) {
		try {
			Sort sort = new Sort(Sort.Direction.DESC,"id");
			return approvedFlowService.getlist(keyWord, super.getPageRequest(sort));
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="复制审核流程", notes="复制审核流程")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "审核流程ID", dataType = "Long", required = true, paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/copy", method = RequestMethod.POST)
	public ApiResponseResult copy(Long id){
		try{
			return approvedFlowService.copy(id);
		}catch (BusinessException e){
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}
}
