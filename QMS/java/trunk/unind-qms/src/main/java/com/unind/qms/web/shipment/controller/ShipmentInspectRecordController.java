package com.unind.qms.web.shipment.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.shipment.entity.ShipmentInspect;
import com.unind.qms.web.shipment.entity.ShipmentInspectRecord;
import com.unind.qms.web.shipment.service.ShipmentInspectRecordService;
import com.unind.qms.web.shipment.service.ShipmentInspectService;
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
 * 出货检验报告记录
 * @author chen
 *
 */
@Api(description = "出货检验报告记录模块")
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/shipmentInspectRecord")
public class ShipmentInspectRecordController extends WebController {
	@Autowired
	private ShipmentInspectRecordService shipmentInspectRecordService;

	@ApiOperation(value="新增出货检验报告记录", notes="新增出货检验报告记录")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(ShipmentInspectRecord shipmentInspectRecord) {
		try {
			return shipmentInspectRecordService.add(shipmentInspectRecord);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="编辑出货检验报告记录", notes="编辑出货检验报告记录")
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ApiResponseResult edit(ShipmentInspectRecord shipmentInspectRecord) {
		try {
			return shipmentInspectRecordService.edit(shipmentInspectRecord);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="删除出货检验报告记录", notes="删除出货检验报告记录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID", dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApiResponseResult delete(Long id) {
		try {
			if(null!=id && id==-1) {
				return ApiResponseResult.failure("没有删除权限");
			}
			return shipmentInspectRecordService.delete(id);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="获取出货检验报告记录", notes="获取出货检验报告记录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bsShipmentId", value = "出货检验报告ID", dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/getlist", method = RequestMethod.GET)
	public ApiResponseResult getlist(Long bsShipmentId) {
		try {
			Sort sort = new Sort(Sort.Direction.DESC,"id");
			return shipmentInspectRecordService.getlist(bsShipmentId, super.getPageRequest(sort));
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}
}
