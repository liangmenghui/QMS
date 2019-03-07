package com.unind.qms.web.risk.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.risk.entity.ProductRisk;
import com.unind.qms.web.risk.service.ProductRiskService;
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
 * 产品风险信息
 * @author chen
 *
 */
@Api(description = "产品风险信息模块")
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/productRisk")
public class ProductRiskController extends WebController {
	@Autowired
	private ProductRiskService productRiskService;

	@ApiOperation(value="新增产品风险信息", notes="新增产品风险信息")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(ProductRisk productRisk) {
		try {
			return productRiskService.add(productRisk);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="编辑产品风险信息", notes="编辑产品风险信息")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ApiResponseResult edit(ProductRisk productRisk) {
		try {
			return productRiskService.edit(productRisk);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="获取产品风险信息", notes="获取产品风险信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bsPrId", value = "产品ID", dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/getlist", method = RequestMethod.GET)
	public ApiResponseResult getlist(Long bsPrId) {
		try {
			Sort sort = new Sort(Sort.Direction.DESC,"bsCreatedTime");
			return productRiskService.getlist(bsPrId, super.getPageRequest(sort));
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}
}
