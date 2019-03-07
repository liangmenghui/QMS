package com.web.quote.controller;

import com.app.base.control.WebController;
import com.app.base.data.ApiResponseResult;
import com.web.quote.entity.QuoteMateriel;
import com.web.quote.service.QuoteMaterielService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 新料报价物料关联表（报价明细）
 *
 */
@Api(description = "新料报价物料关联模块")
@CrossOrigin
@ControllerAdvice
@RestController
@RequestMapping(value = "/quoteMateriel")
public class QuoteMaterielController extends WebController {

    @Autowired
    private QuoteMaterielService quoteMaterielService;

    @ApiOperation(value = "新增报价物料关联信息", notes = "新增报价物料关联信息")
    @PostMapping("/add")
    public ApiResponseResult add(@RequestBody(required = false) QuoteMateriel quoteMateriel){
        try{
            return quoteMaterielService.add(quoteMateriel);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return ApiResponseResult.failure("新增报价物料关联信息失败！");
        }
    }
}
