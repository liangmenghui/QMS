package com.unind.qms.web.shipment.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.shipment.service.ShipmentInspectRecordFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 出货检验核查记录附件关联（特批文件）
 * @author chen
 *
 */
@Api(description = "出货检验核查记录附件关联（特批文件）")
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/shipmentInspectRecordFile")
public class ShipmentInspectRecordFileController extends WebController {
	@Autowired
	private ShipmentInspectRecordFileService shipmentInspectRecordFileService;

	@ApiOperation(value="新增出货检验核查记录附件", notes="新增出货检验核查记录附件")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bsShipmentRecordId", value = "出货检验报告ID", dataType = "Long", paramType="query",defaultValue=""),
			@ApiImplicitParam(name = "file", value = "附件", dataType = "MultipartFile", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(
			@RequestParam(value = "bsShipmentRecordId", required = true) Long bsShipmentRecordId, MultipartFile file) {
		try {
			return shipmentInspectRecordFileService.add(bsShipmentRecordId,file);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="删除出货检验核查记录附件", notes="删除出货检验核查记录附件")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApiResponseResult delete(Long id) {
		try {
			if(null!=id && id==-1) {
				return ApiResponseResult.failure("没有删除权限");
			}
			return shipmentInspectRecordFileService.delete(id);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}
}
