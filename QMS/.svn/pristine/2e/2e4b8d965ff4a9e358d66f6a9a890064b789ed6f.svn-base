package com.unind.qms.web.sample.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.sample.entity.SampleRegular;
import com.unind.qms.web.sample.service.SampleRegularService;
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
 * 样品规格
 * @author chen
 *
 */
@Api(description = "样品规格模块")
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/sampleRegular")
public class SampleRegularController extends WebController {
	@Autowired
	private SampleRegularService sampleRegularService;

	@ApiOperation(value="新增样品规格", notes="新增样品规格")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(SampleRegular sampleRegular) {
		try {
			return sampleRegularService.add(sampleRegular);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="编辑样品规格", notes="编辑样品规格")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ApiResponseResult edit(SampleRegular sampleRegular) {
		try {
			return sampleRegularService.edit(sampleRegular);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="删除样品规格", notes="删除样品规格")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID", dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApiResponseResult delete(Long id) {
		try {
			if(null!=id && id==-1) {
				return ApiResponseResult.failure("没有删除权限");
			}
			return sampleRegularService.delete(id);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="获取样品规格", notes="获取样品规格")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bsPrId", value = "产品ID", dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/getlist", method = RequestMethod.GET)
	public ApiResponseResult getlist(Long bsPrId) {
		try {
			Sort sort = new Sort(Sort.Direction.DESC,"id");
			return sampleRegularService.getlist(bsPrId, super.getPageRequest(sort));
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}
}
