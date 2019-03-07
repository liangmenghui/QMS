package com.unind.qms.web.approved.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.approved.entity.ApprovedEHSRecord;
import com.unind.qms.web.approved.entity.ApprovedEHSTerms;
import com.unind.qms.web.approved.entity.ApprovedTermsScore;
import com.unind.qms.web.approved.service.ApprovedEHSTermsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * EHS条款
 * @author fyx
 *
 */
@Api(description = "审批EHS条款模块")
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/approvedEHSTerms")
public class ApprovedEHSTermsController extends WebController {
	
	@Autowired
	private ApprovedEHSTermsService approvedEHSTermsService;

	@ApiOperation(value="新增EHS条款", notes="新增EHS条款")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(ApprovedEHSTerms approvedEHSTerms) {
		try {
			return approvedEHSTermsService.add(approvedEHSTerms);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="编辑条款", notes="编辑条款")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ApiResponseResult edit(ApprovedEHSTerms approvedEHSTerms) {
		try {
			return approvedEHSTermsService.edit(approvedEHSTerms);
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
			return approvedEHSTermsService.delete(id);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="获取EHS条款", notes="获取EHS条款")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "keyWord", value = "查询条件", dataType = "String", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "mapId", value = "项目ID", dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/getlist", method = RequestMethod.GET)
	public ApiResponseResult getlist(String keyWord, Long mapId, Long itemsRecordId) {
		try {
			Sort sort = new Sort(Sort.Direction.DESC,"id");
			//approvedEHSTermsService.getlist(keyWord, itemsId, itemsRecordId, super.getPageRequest(sort));
			return approvedEHSTermsService.getlist(keyWord, mapId, itemsRecordId, super.getPageRequest(sort));
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}
	
	@ApiOperation(value="新增/修改EHS条款审批记录", notes="新增/修改EHS条款审批记录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "评分ID", required = true, dataType = "Long", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "supplierId", value = "供应商ID", required = true, dataType = "Long", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "bsEHSItemsId", value = "条款ID", required = true, dataType = "Long", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "ehsEval", value = "结论", dataType = "String", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "ehsCorrectiveAction", value = "行动和措施", dataType = "String", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "ehsViolation", value = "问题", dataType = "String", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "ehsCommentsReference", value = "建议改善方案", dataType = "String", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "ehsRemark", value = "备注", dataType = "String", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/addEHSRecord", method = RequestMethod.POST)
	public ApiResponseResult addEHSRecord(String approvedEHSRecordStr,String mapId,String supplierId) {
		try {
			Gson gson = new Gson();
			List<ApprovedEHSRecord> approvedEHSRecordList = gson.fromJson(approvedEHSRecordStr, new TypeToken<List<ApprovedEHSRecord>>(){}.getType());
			return approvedEHSTermsService.addEHSRecord(approvedEHSRecordList,mapId,supplierId);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}
	
	@ApiOperation(value="获取审批EHS条款记录", notes="获取审批EHS条款记录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "keyWord", value = "查询条件", dataType = "String", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "itemsId", value = "项目ID", dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/getRecords", method = RequestMethod.GET)
	public ApiResponseResult getRecords(String keyWord, Long supplierId) {
		try {
			Sort sort = new Sort(Sort.Direction.DESC,"id");
			//approvedEHSTermsService.getlist(keyWord, itemsId, itemsRecordId, super.getPageRequest(sort));
			return approvedEHSTermsService.getRecords(keyWord, supplierId, super.getPageRequest(sort));
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}
	
	
	@ApiOperation(value="获取EHS条款信息", notes="获取EHS条款信息")
	@ApiImplicitParams({
	})
	@RequestMapping(value = "/getItemList", method = RequestMethod.GET)
	public ApiResponseResult getItemList(String keyWord) {
		try {
			Sort sort = new Sort(Sort.Direction.DESC,"id");
			//approvedEHSTermsService.getlist(keyWord, itemsId, itemsRecordId, super.getPageRequest(sort));
			return approvedEHSTermsService.getItemList(keyWord,  super.getPageRequest(sort));
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	/*@ApiOperation(value="Excel导入条款", notes="Excel导入条款")
	@ApiParam(name = "file",value = "Excel文件")
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	public ApiResponseResult importExcel(MultipartFile file){
		try{
			return approvedTermsService.importExcel(file);
		}catch(Exception e){
			return ApiResponseResult.failure(e.getMessage());
		}
	}*/
}
