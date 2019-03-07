package com.unind.qms.web.approved.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.approved.entity.ApprovedItems;
import com.unind.qms.web.approved.service.ApprovedItemsMapService;
import com.unind.qms.web.approved.service.ApprovedItemsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 审核项目条款关联
 * @author chen
 *
 */
@Api(description = "审核项目条款关联模块")
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/approvedItemsMap")
public class ApprovedItemsMapController extends WebController {
	@Autowired
	private ApprovedItemsMapService approvedItemsMapService;

	@ApiOperation(value="新增项目条款关联", notes="新增项目条款关联")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(Long bsItemsId,String TermsIdStr) {
		try {
			return approvedItemsMapService.add(bsItemsId,TermsIdStr);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="删除项目条款关联", notes="删除项目条款关联")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bsItemsId", value = "项目ID", required = true, dataType = "Long", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "bsTermsId", value = "条款ID", required = true, dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApiResponseResult delete(Long bsItemsId,Long bsTermsId) {
		try {
			return approvedItemsMapService.delete(bsItemsId,bsTermsId);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}
}
