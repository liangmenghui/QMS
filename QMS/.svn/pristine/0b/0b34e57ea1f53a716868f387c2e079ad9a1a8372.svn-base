package com.unind.qms.web.approved.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.approved.entity.ApprovedTerms;
import com.unind.qms.web.approved.service.ApprovedTermsService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 审批条款
 * @author chen
 *
 */
@Api(description = "审批条款模块")
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/approvedTerms")
public class ApprovedTermsController extends WebController {
	@Autowired
	private ApprovedTermsService approvedTermsService;

	@ApiOperation(value="新增条款", notes="新增条款")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(ApprovedTerms approvedTerms) {
		try {
			return approvedTermsService.add(approvedTerms);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="编辑条款", notes="编辑条款")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ApiResponseResult edit(ApprovedTerms approvedTerms) {
		try {
			return approvedTermsService.edit(approvedTerms);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="删除条款", notes="删除条款")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApiResponseResult delete(Long id) {
		try {
			if(null!=id && id==-1) {
				return ApiResponseResult.failure("没有删除权限");
			}
			return approvedTermsService.delete(id);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="获取条款", notes="获取条款")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "keyWord", value = "查询条件", dataType = "String", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "itemsId", value = "项目ID", dataType = "Long", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "bsType", value = "条款类型", dataType = "Integer", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/getlist", method = RequestMethod.GET)
	public ApiResponseResult getlist(String keyWord, Long itemsId, Long itemsRecordId, Integer bsType) {
		try {
			Sort sort = new Sort(Sort.Direction.DESC,"id");
			return approvedTermsService.getlist(keyWord, itemsId, itemsRecordId, bsType, super.getPageRequest(sort));
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="Excel导入条款", notes="Excel导入条款")
	@ApiParam(name = "file",value = "Excel文件")
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	public ApiResponseResult importExcel(MultipartFile file){
		try{
			return approvedTermsService.importExcel(file);
		}catch(Exception e){
			return ApiResponseResult.failure(e.getMessage());
		}
	}
}
