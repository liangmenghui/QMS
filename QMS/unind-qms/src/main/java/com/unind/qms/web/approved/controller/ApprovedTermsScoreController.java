package com.unind.qms.web.approved.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.approved.entity.ApprovedTermsScore;
import com.unind.qms.web.approved.service.ApprovedTermsScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 审核条款评分
 * @author chen
 *
 */
@Api(description = "审批条款评分模块")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/approvedTermsScore")
public class ApprovedTermsScoreController extends WebController {
	@Autowired
	private ApprovedTermsScoreService approvedTermsScoreService;

	@ApiOperation(value="新增/修改审核条款评分", notes="新增/修改审核条款评分")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "评分ID", required = true, dataType = "Long", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "bsItemsRecoreId", value = "项目记录ID", required = true, dataType = "Long", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "bsTermsId", value = "条款ID", required = true, dataType = "Long", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "bsScore", value = "评分", dataType = "int", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "bsDesc", value = "说明", dataType = "String", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(String approvedTermsScoreStr) {
		try {
			Gson gson = new Gson();
			List<ApprovedTermsScore> approvedTermsScoreList = gson.fromJson(approvedTermsScoreStr, new TypeToken<List<ApprovedTermsScore>>(){}.getType());
			return approvedTermsScoreService.add(approvedTermsScoreList);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

//	@ApiOperation(value="编辑审核条款评分", notes="编辑审核条款评分")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "bsItemsRecoreId", value = "项目记录ID", required = true, dataType = "Long", paramType="query",defaultValue=""),
//			@ApiImplicitParam(name = "bsTermsId", value = "条款ID", required = true, dataType = "Long", paramType="query",defaultValue=""),
//			@ApiImplicitParam(name = "bsScore", value = "评分", dataType = "int", paramType="query",defaultValue=""),
//			@ApiImplicitParam(name = "bsDesc", value = "说明", dataType = "String", paramType="query",defaultValue=""),
//	})
//	@RequestMapping(value = "/edit", method = RequestMethod.POST)
//	public ApiResponseResult edit(List<ApprovedTermsScore> approvedTermsScoreList) {
//		try {
//			return approvedTermsScoreService.edit(approvedTermsScoreList);
//		} catch (BusinessException e) {
//			e.printStackTrace();
//			return ApiResponseResult.failure(e.getMessage());
//		}
//	}
}
