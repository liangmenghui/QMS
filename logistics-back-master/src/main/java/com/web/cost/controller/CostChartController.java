package com.web.cost.controller;

import com.app.base.control.WebController;
import com.app.base.data.ApiResponseResult;
import com.web.cost.service.CostChartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(value = "成本曲线信息模块")
@CrossOrigin
@ControllerAdvice
@RestController
@RequestMapping(value = "/costChart")
public class CostChartController extends WebController {

    @Autowired
    private CostChartService costChartService;

    @ApiOperation(value = "获取物料成本价格曲线", notes = "获取物料成本价格曲线")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mateK3Code", value = "K3物料号", required = true, dataType = "String", paramType = "query", defaultValue = "01.10.00010"),
            @ApiImplicitParam(name = "startDate", value = "起始时间", required = true, dataType = "Date", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = true, dataType = "Date", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "flag", value = "1:采购订单价 / 2:购货发票价", required = true, dataType = "Date", paramType = "query", defaultValue = "")
    })
    @RequestMapping(value = "/getPriceChart", method = RequestMethod.GET)
    public ApiResponseResult getPriceChart(@RequestParam(value = "mateK3Code", required = false) String mateK3Code,
                                           @RequestParam(value = "startDate", required = false) Date startDate,
                                           @RequestParam(value = "endDate", required = false) Date endDate,
                                           @RequestParam(value = "flag", required = false) Integer flag) {
        try{
            //flag为1时，获取采购订单价格曲线；flag为其它时，获取购货发票价格曲线
            if(flag != null && flag == 1){
                return costChartService.getOrderPrice(mateK3Code, startDate, endDate);
            }else{
                return costChartService.getInvoicePrice(mateK3Code, startDate, endDate);
            }
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return ApiResponseResult.failure("获取物料成本价格曲线失败！");
        }
    }
    
   
    

}
