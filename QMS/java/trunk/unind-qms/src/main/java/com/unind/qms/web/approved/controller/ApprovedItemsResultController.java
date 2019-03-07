package com.unind.qms.web.approved.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.pub.admin.role.SysRolePubService;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.approved.entity.ApprovedFlow;
import com.unind.qms.web.approved.entity.ApprovedItemsResult;
import com.unind.qms.web.approved.service.ApprovedFlowService;
import com.unind.qms.web.approved.service.ApprovedItemsResultService;
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
 * 审核项目结果
 * @author chen
 *
 */
@Api(description = "审核项目结果模块")
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/approvedItemsResult")
public class ApprovedItemsResultController extends WebController {
	@Autowired
	private ApprovedItemsResultService approvedItemsResultService;

	@ApiOperation(value="新增审核项目结果", notes="新增审核项目结果")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(ApprovedItemsResult approvedItemsResult) {
		try {
			return approvedItemsResultService.add(approvedItemsResult);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="删除审核项目结果", notes="删除审核项目结果")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApiResponseResult delete(Long id) {
		try {
			if(null!=id && id==-1) {
				return ApiResponseResult.failure("没有删除权限");
			}
			return approvedItemsResultService.delete(id);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}
}
