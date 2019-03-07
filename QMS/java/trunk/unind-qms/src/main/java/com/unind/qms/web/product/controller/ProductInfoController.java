package com.unind.qms.web.product.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.provider.ExcelService;
import com.unind.qms.web.product.entity.ProductInfo;
import com.unind.qms.web.product.service.ProductInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 产品基本资料
 * @author chen
 *
 */
@Api(description = "产品基本资料模块")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/productInfo")
public class ProductInfoController extends WebController {
	@Autowired
	private ProductInfoService productInfoService;
	@Autowired
	private ExcelService excelService;
//	@ApiOperation(value="新增产品", notes="新增产品")
//	@RequestMapping(value = "/add", method = RequestMethod.POST)
//	public ApiResponseResult add(ProductInfo productInfo) {
//		try {
//			return productInfoService.add(productInfo);
//		} catch (BusinessException e) {
//			e.printStackTrace();
//			return ApiResponseResult.failure(e.getMessage());
//		}
//	}

	@ApiOperation(value="删除产品", notes="删除产品")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID", dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApiResponseResult delete(Long id) {
		try {
			if(null!=id && id==-1) {
				return ApiResponseResult.failure("没有删除权限");
			}
			return productInfoService.delete(id);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="获取产品", notes="获取产品")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "keyWord", value = "查询条件", dataType = "String", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "id", value = "产品ID", dataType = "Long", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "suppCode", value = "供应商编号", dataType = "Long", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "bsIsApprove", value = "是否审批中（0：否，1：是）", dataType = "Integer", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "bsStatus", value = "产品状态", dataType = "Integer", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "bsRiskLevel" ,value = "风险等级", dataType = "Integer", paramType="query",defaultValue="" ),
			@ApiImplicitParam(name = "bsIsSqe" ,value = "是否根据sqe查询（1：是）", dataType = "Integer", paramType="query",defaultValue="" ),
			@ApiImplicitParam(name = "bsApprovedType" ,value = "审核类型（11：过程审核，12：成品检验）", dataType = "Integer", paramType="query",defaultValue="" ),
	})
	@RequestMapping(value = "/getlist", method = RequestMethod.GET)
	public ApiResponseResult getlist(String keyWord, Long id, String suppCode, Integer bsIsApprove, Integer bsStatus, Integer bsRiskLevel, Integer bsIsSqe, Integer bsApprovedType) {
		try {
			Sort sort = new Sort(Sort.Direction.DESC,"id");
			return productInfoService.getlist(keyWord, id, suppCode, bsIsApprove, bsStatus, bsRiskLevel, bsIsSqe, bsApprovedType, super.getPageRequest(sort));
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="申请变更风险等级（添加待办事项）", notes="申请修改风险等级（添加待办事项）")
	@ApiImplicitParams(value = {
			@ApiImplicitParam(name = "id", value = "产品ID", dataType = "Long", paramType = "query", defaultValue = "", required = true),
			@ApiImplicitParam(name = "bsRiskLevelUser", value = "风险等级(user)", dataType = "Integer", paramType = "query", defaultValue = "", required = true),
			@ApiImplicitParam(name = "bsRiskDescUser", value = "风险等级备注(user)", dataType = "String", paramType = "query", defaultValue = ""),
	})
	@RequestMapping(value = "/applyRiskLevel", method = RequestMethod.POST)
	public ApiResponseResult applyRiskLevel(Long id, Integer bsRiskLevelUser, String bsRiskDescUser){
		try{
			return productInfoService.applyRiskLevel(id, bsRiskLevelUser, bsRiskDescUser);
		}catch(BusinessException e){
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="完成变更风险等级（关闭待办事项）", notes="完成修改风险等级（关闭待办事项）")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "产品ID", dataType = "Long", paramType = "query", defaultValue = "", required = true),
			@ApiImplicitParam(name = "bsRecordId", value = "风险管理记录ID", dataType = "Long", paramType = "query", defaultValue = "", required = true),
			@ApiImplicitParam(name = "bsRiskLevelUser", value = "风险等级（user）", dataType = "Integer", paramType = "query", defaultValue = "", required = true),
			@ApiImplicitParam(name = "bsApprovedAdvice", value = "批准人意见", dataType = "String", paramType = "query", defaultValue = ""),
			@ApiImplicitParam(name = "bsResult", value = "是否通过（0.进行中 1.通过 2.驳回）", dataType = "Integer", paramType = "query", defaultValue = "", required = true)
	})
	@RequestMapping(value = "/completeRiskLevel", method = RequestMethod.POST)
	public ApiResponseResult completeRiskLevel(Long id, Long bsRecordId, Integer bsRiskLevelUser, String bsApprovedAdvice, Integer bsResult){
		try{
			return productInfoService.completeRiskLevel(id, bsRecordId, bsRiskLevelUser, bsApprovedAdvice, bsResult);
		}catch(BusinessException e){
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value = "切换调整风险等级模式", notes = "切换调整风险等级模式")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "产品ID", dataType = "Long", paramType = "query", defaultValue = "", required = true),
			@ApiImplicitParam(name = "bsRiskManual", value = "切换前管理模式(0.自动/1.手动)", dataType = "Integer", paramType = "query", defaultValue = "", required = true),
	})
	@RequestMapping(value = "/autoRiskLevel", method = RequestMethod.POST)
	public ApiResponseResult autoRiskLevel(Long id, Integer bsRiskManual){
		try{
			return productInfoService.autoRiskLevel(id, bsRiskManual);
		}catch (BusinessException e){
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="获取过程审核产品", notes="获取过程审核产品")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "keyWord", value = "查询条件", dataType = "String", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "startDate" ,value = "初始时间", dataType = "Date", paramType="query",defaultValue="" ),
			@ApiImplicitParam(name = "endDate" ,value = "截止时间", dataType = "Date", paramType="query",defaultValue="" ),
			@ApiImplicitParam(name = "bsConclusion" ,value = "审核结果", dataType = "Integer", paramType="query",defaultValue="" ),
	})
	@RequestMapping(value = "/getProcessApproved", method = RequestMethod.GET)
	public ApiResponseResult getProcessApproved(String keyWord, Date startDate, Date endDate, Integer bsConclusion) {
		try {
			Sort sort = new Sort(Sort.Direction.DESC,"approvedFlowRecordBy.id");
			return productInfoService.getProcessApproved(keyWord, startDate, endDate, bsConclusion, super.getPageRequest(sort));
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="导出过程审核产品清单", notes="导出过程审核产品清单")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "keyWord", value = "查询条件", dataType = "String", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "startDate" ,value = "初始时间", dataType = "Date", paramType="query",defaultValue="" ),
			@ApiImplicitParam(name = "endDate" ,value = "截止时间", dataType = "Date", paramType="query",defaultValue="" ),
			@ApiImplicitParam(name = "bsConclusion" ,value = "审核结果", dataType = "Integer", paramType="query",defaultValue="" ),
	})
	@RequestMapping(value = "/getProcessApprovedExcel", method = RequestMethod.GET)
	public void getProcessApprovedExcel(String keyWord, Date startDate, Date endDate, Integer bsConclusion) {
		try {
			excelService.writeProcessApprovedExcel(keyWord, startDate, endDate, bsConclusion, getResponse());
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	@ApiOperation(value="获取成品检验审核产品", notes="获取成品检验审核产品")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "keyWord", value = "查询条件", dataType = "String", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "startDate" ,value = "初始时间", dataType = "Date", paramType="query",defaultValue="" ),
			@ApiImplicitParam(name = "endDate" ,value = "截止时间", dataType = "Date", paramType="query",defaultValue="" ),
			@ApiImplicitParam(name = "bsConclusion" ,value = "审核结果", dataType = "Integer", paramType="query",defaultValue="" ),
	})
	@RequestMapping(value = "/getProductApproved", method = RequestMethod.GET)
	public ApiResponseResult getProductApproved(String keyWord, Date startDate, Date endDate, Integer bsConclusion) {
		try {
			Sort sort = new Sort(Sort.Direction.DESC,"approvedFlowRecordBy.id");
			return productInfoService.getProductApproved(keyWord, startDate, endDate, bsConclusion, super.getPageRequest(sort));
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="导出成品检验产品清单", notes="导出成品检验产品清单")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "keyWord", value = "查询条件", dataType = "String", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "startDate" ,value = "初始时间", dataType = "Date", paramType="query",defaultValue="" ),
			@ApiImplicitParam(name = "endDate" ,value = "截止时间", dataType = "Date", paramType="query",defaultValue="" ),
			@ApiImplicitParam(name = "bsConclusion" ,value = "审核结果", dataType = "Integer", paramType="query",defaultValue="" ),
	})
	@RequestMapping(value = "/getProductApprovedExcel", method = RequestMethod.GET)
	public void getProductApprovedExcel(String keyWord, Date startDate, Date endDate, Integer bsConclusion) {
		try {
			excelService.writeProductApprovedExcel(keyWord, startDate, endDate, bsConclusion, getResponse());
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
