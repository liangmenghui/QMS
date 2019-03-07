package com.unind.qms.web.basic.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.basic.entity.ExcelTemp;
import com.unind.qms.web.basic.service.ExcelTempService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * excel模板
 * @author chen
 *
 */
@Api(description = "excel模板")
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/excelTemp")
public class ExcelTempController extends WebController {
	@Autowired
	private ExcelTempService excelTempService;

	@ApiOperation(value="新增excel模板", notes="新增excel模板")
	@ApiImplicitParams({
	})
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(ExcelTemp excelTemp) {
		try {
			return excelTempService.add(excelTemp);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="编辑excel模板", notes="编辑excel模板")
	@ApiImplicitParams({
	})
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ApiResponseResult edit(ExcelTemp excelTemp) {
		try {
			return excelTempService.edit(excelTemp);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="删除excel模板", notes="删除excel模板")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApiResponseResult delete(Long id) {
		try {
			if(null!=id && id==-1) {
				return ApiResponseResult.failure("没有删除权限");
			}
			return excelTempService.delete(id);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="获取excel模板", notes="获取excel模板")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID", dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/getlist", method = RequestMethod.GET)
	public ApiResponseResult getlist(Long id) {
		try {
			Sort sort = new Sort(Sort.Direction.DESC,"id");
			return excelTempService.getlist(id, super.getPageRequest(sort));
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}
}
