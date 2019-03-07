package com.unind.qms.web.quality.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.quality.entity.QualityInspect;
import com.unind.qms.web.quality.service.QualityInspectService;
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
 * 质量审核报告
 * @author chen
 *
 */
@Api(description = "质量审核报告模块")
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/qualityInspect")
public class QualityInspectController extends WebController {
	@Autowired
	private QualityInspectService qualityInspectService;

	@ApiOperation(value="新增/修改质量审核报告", notes="新增/修改质量审核报告")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(QualityInspect qualityInspect) {
		try {
			return qualityInspectService.add(qualityInspect);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="删除质量审核报告", notes="删除质量审核报告")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID", dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApiResponseResult delete(Long id) {
		try {
			if(null!=id && id==-1) {
				return ApiResponseResult.failure("没有删除权限");
			}
			return qualityInspectService.delete(id);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="获取质量审核报告", notes="获取质量审核报告")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bsFlowRecordId", value = "审核流程记录ID", dataType = "Long", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "bsPrId", value = "产品ID", dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/getlist", method = RequestMethod.GET)
	public ApiResponseResult getlist(Long bsFlowRecordId, Long bsPrId) {
		try {
			Sort sort = new Sort(Sort.Direction.DESC,"id");
			return qualityInspectService.getlist(bsFlowRecordId, bsPrId, super.getPageRequest(sort));
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}
}
