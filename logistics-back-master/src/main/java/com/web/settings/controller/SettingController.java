package com.web.settings.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.app.base.control.WebController;
import com.app.base.data.ApiResponseResult;
import com.web.materiel.entity.MaterielInfo;
import com.web.materiel.service.MaterielInfoService;
import com.web.settings.service.SettingService;

@Api(value = "基础设置")
@CrossOrigin
@ControllerAdvice
@RestController
@RequestMapping(value = "/setting")
public class SettingController extends WebController {

    @Autowired
    private SettingService settingService;

//    @ApiOperation(value = "编辑物料", notes = "编辑物料")
//    @PostMapping("/edit")
//    public ApiResponseResult edit(@RequestBody(required=false) MaterielInfo materielInfo){
//        try{
//            return settingService.edit(materielInfo);
//        }catch (Exception e){
//            logger.error(e.getMessage(), e);
//            e.printStackTrace();
//            return ApiResponseResult.failure("编辑物料失败！");
//        }
//    }


    @ApiOperation(value = "获取列表", notes = "获取列表")
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    public ApiResponseResult getlist(){
        try{
            Sort sort = new Sort(Sort.Direction.DESC, "id");
            return settingService.getlist( super.getPageRequest(sort));
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return ApiResponseResult.failure("获取物料列表失败！");
        }
    }

    @ApiOperation(value = "修改配置", notes = "修改配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bomCheck", value = "匹配率", required = false, dataType = "Float", paramType = "query", defaultValue = "")
    })
    @RequestMapping(value = "/updateSetting", method = RequestMethod.POST)
    public ApiResponseResult updateSetting(@RequestParam(value = "bomCheck", required = false) Float bomCheck){
        try{
            return settingService.updateSetting(bomCheck);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return ApiResponseResult.failure("修改配置失败！");
        }
    }

}
