package com.unind.qms.web.supplier.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.supplier.entity.SupplierInfo;
import com.unind.qms.web.supplier.service.SupplierInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandle;

/**
 * 供应商基本资料
 * @author chen
 *
 */
@Api(description = "供应商基本信息模块")
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/supplierInfo")
public class SupplierInfoController extends WebController {
	@Autowired
	private SupplierInfoService supplierInfoService;

	@ApiOperation(value="新增供应商", notes="新增供应商")
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(SupplierInfo supplierInfo) {
		try {
			return supplierInfoService.add(supplierInfo);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="编辑供应商", notes="编辑供应商")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ApiResponseResult edit(SupplierInfo supplierInfo) {
		try {
			return supplierInfoService.edit(supplierInfo);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="删除供应商", notes="删除供应商")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID", dataType = "Long", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "bsSuppCode", value = "供应商编号", dataType = "String", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApiResponseResult delete(Long id, String bsSuppCode) {
		try {
			if(null!=id && id==-1) {
				return ApiResponseResult.failure("没有删除权限");
			}
			return supplierInfoService.delete(id,bsSuppCode);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="获取供应商", notes="获取供应商")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "keyWord", value = "查询条件", dataType = "String", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "id", value = "ID", dataType = "Long", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "bsSuppCode", value = "供应商编号", dataType = "String", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "bsIsApprove", value = "是否审批中（0：否，1：是）", dataType = "Integer", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "bsSuppStatus", value = "供应商状态", dataType = "String", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "bsRiskLevel" ,value = "风险等级", dataType = "Integer", paramType="query",defaultValue="" ),
	})
	@RequestMapping(value = "/getlist", method = RequestMethod.GET)
	public ApiResponseResult getlist(String keyWord, Long id, String bsSuppCode, Integer bsIsApprove, String bsSuppStatus, Integer bsRiskLevel) {
		try {
			Sort sort = new Sort(Sort.Direction.DESC,"id");
			return supplierInfoService.getlist(keyWord, id, bsSuppCode, bsIsApprove, bsSuppStatus, bsRiskLevel, super.getPageRequest(sort));
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value = "申请变更风险等级（添加待办事项）", notes = "申请变更风险等级（添加待办事项）")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "供应商ID", dataType = "Long", paramType = "query", defaultValue = "", required = true),
			@ApiImplicitParam(name = "bsRiskLevelUser", value = "风险等级(user)", dataType = "Integer", paramType = "query", defaultValue = "", required = true),
			@ApiImplicitParam(name = "bsRiskDescUser", value = "风险等级备注(user)", dataType = "Integer", paramType = "query", defaultValue = ""),
	})
	@RequestMapping(value = "applyRiskLevel", method = RequestMethod.POST)
	public ApiResponseResult applyRiskLevel(Long id, Integer bsRiskLevelUser, String bsRiskDescUser){
		try{
			return supplierInfoService.applyRiskLevel(id, bsRiskLevelUser, bsRiskDescUser);
		}catch(BusinessException e){
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value = "完成变更风险等级（关闭待办事项）", notes = "完成变更风险等级（关闭待办事项）")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "供应商ID", dataType = "Long", paramType = "query", defaultValue = "", required = true),
			@ApiImplicitParam(name = "bsRiskLevelUser", value = "风险等级(user)", dataType = "Integer", paramType = "query", defaultValue = "", required = true),
			@ApiImplicitParam(name = "isApprove", value = "是否通过（0.驳回 1.通过）", dataType = "Integer", paramType = "query", defaultValue = "")
	})
	@RequestMapping(value = "completeRiskLevel", method = RequestMethod.POST)
	public ApiResponseResult completeRiskLevel(Long id, Integer bsRiskLevelUser, Integer isApprove){
		try{
			//1.待办驳回则不做任何操作
			if(isApprove != null && isApprove == BooleanStateEnum.FALSE.intValue()){
				return ApiResponseResult.success("待办驳回！风险等级不调整。");
			}else{
				//2.待办通过，调整风险等级
				return supplierInfoService.completeRiskLevel(id, bsRiskLevelUser);
			}
		}catch(BusinessException e){
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value = "切换调整风险等级模式", notes = "切换调整风险等级模式")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "供应商ID", dataType = "Long", paramType = "query", defaultValue = "", required = true),
			@ApiImplicitParam(name = "bsRiskManual", value = "切换前管理模式(0.自动/1.手动)", dataType = "Integer", paramType = "query", defaultValue = "", required = true),
	})
	@RequestMapping(value = "autoRiskLevel", method = RequestMethod.POST)
	public ApiResponseResult autoRiskLevel(Long id, Integer bsRiskManual){
		try{
			return supplierInfoService.autoRiskLevel(id, bsRiskManual);
		}catch(BusinessException e){
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="SRM获取供应商数据", notes="SRM获取供应商数据")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "start", value = "datatable起始位置", dataType = "int", paramType = "query", defaultValue = "", required = true),
			@ApiImplicitParam(name = "length", value = "datatable数据条数", dataType = "int", paramType = "query", defaultValue = "", required = true),
			@ApiImplicitParam(name = "schInfo", value = "datatable模糊搜索", dataType = "String", paramType = "query", defaultValue = ""),
			@ApiImplicitParam(name = "approvedLevel", value = "供应商审批阶段(review:初评,coach:辅导)", dataType = "String", paramType = "query", defaultValue = ""),
	})
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getSrmApprovedSupp", method = RequestMethod.POST)
	public ApiResponseResult getSrmReview(int start, int length, String schInfo, String approvedLevel) {
		try {
			Sort sort = new Sort(Sort.Direction.DESC,"id");
			Pageable pageRequest = new PageRequest(start/length, length, sort);
			return supplierInfoService.getSrmApprovedSupp(schInfo, approvedLevel, pageRequest);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="SRM获取供应商审批上传文件", notes="SRM获取供应商审批上传文件")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "suppcode", value = "供应商编号", dataType = "String", paramType = "query", defaultValue = "", required = true),
	})
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/getSrmApprovedFile", method = RequestMethod.POST)
	public ApiResponseResult getSrmApprovedFile(String suppcode) {
		try {
			return supplierInfoService.getSrmApprovedFile(suppcode);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="修改SRM供应商状态和等级", notes="修改SRM供应商状态和等级")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bsLoginName", value = "供应商账号", dataType = "String", paramType = "query", defaultValue = "", required = true),
			@ApiImplicitParam(name = "bsResult", value = "审批类型（1:通过，2：驳回，3：不通过）", dataType = "int", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "bsGrade", value = "供应商等级", dataType = "int", paramType = "query", defaultValue = "", required = true),
	})
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/changeSuppGradeAndStatus", method = RequestMethod.POST)
	public ApiResponseResult changeSuppGradeAndStatus(String bsLoginName, int bsResult, int bsGrade) {
		try {
			return supplierInfoService.changeSuppGradeAndStatus(bsLoginName,bsResult,bsGrade);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}
}
