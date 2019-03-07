package com.unind.qms.web.approved.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.approved.entity.ApprovedItems;
import com.unind.qms.web.approved.service.ApprovedItemsService;
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
 * 审核项目
 * @author chen
 *
 */
@Api(description = "审批项目模块")
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/approvedItems")
public class ApprovedItemsController extends WebController {
	@Autowired
	private ApprovedItemsService approvedItemsService;

	@ApiOperation(value="新增审核项目", notes="新增审核项目")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "termsStr", value = "条款ID集合(,)", dataType = "String", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "userStr", value = "记录人ID集合(,)", dataType = "String", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(ApprovedItems approvedItems, String termsStr, String userStr) {
		try {
			return approvedItemsService.add(approvedItems,termsStr,userStr);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="编辑审核项目", notes="编辑审核项目")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "termsStr", value = "条款ID集合(,)", required = true, dataType = "String", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "userStr", value = "记录人ID集合(,)", required = true, dataType = "String", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ApiResponseResult edit(ApprovedItems approvedItems, String termsStr, String userStr) {
		try {
			return approvedItemsService.edit(approvedItems,termsStr,userStr);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="删除审核项目", notes="删除审核项目")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApiResponseResult delete(Long id) {
		try {
			if(null!=id && id==-1) {
				return ApiResponseResult.failure("没有删除权限");
			}
			return approvedItemsService.delete(id);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="获取审核项目", notes="获取审核项目,传入流程ID获取其下所有项目；传入项目ID获取项目信息及其下所有条款")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bsFlowId", value = "流程ID", dataType = "Long", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "id", value = "项目ID", dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/getlist", method = RequestMethod.GET)
	public ApiResponseResult getlist(Long bsFlowId,Long id) {
		try {
			Sort sort = new Sort(Sort.Direction.ASC,"bsPriority");
			return approvedItemsService.getlist(bsFlowId, id, super.getPageRequest(sort));
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}
}
