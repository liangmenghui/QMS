package com.unind.qms.web.shipment.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.shipment.entity.OrderInspect;
import com.unind.qms.web.shipment.entity.ShipmentInspect;
import com.unind.qms.web.shipment.service.OrderInspectService;
import com.unind.qms.web.shipment.service.ShipmentInspectService;
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

/**
 * 验货订单
 * @author chen
 *
 */
@Api(description = "验货订单模块")
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/orderInspect")
public class OrderInspectController extends WebController {
	@Autowired
	private OrderInspectService orderInspectService;

	@ApiOperation(value="新增验货订单", notes="新增验货订单")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(OrderInspect orderInspect) {
		try {
			return orderInspectService.add(orderInspect);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}
}
