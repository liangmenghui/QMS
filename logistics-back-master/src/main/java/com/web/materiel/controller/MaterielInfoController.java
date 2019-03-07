package com.web.materiel.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import com.app.base.control.WebController;
import com.app.base.data.ApiResponseResult;
import com.web.materiel.entity.MaterielInfo;
import com.web.materiel.service.MaterielInfoService;

@Api(value = "物料基础信息模块")
@CrossOrigin
@ControllerAdvice
@RestController
@RequestMapping(value = "/materielInfo")
public class MaterielInfoController extends WebController {

    @Autowired
    private MaterielInfoService materielInfoService;

    @ApiOperation(value = "新增物料", notes = "新增物料")
    @PostMapping("/add")
    public ApiResponseResult add(@RequestBody(required=false) MaterielInfo materielInfo){
        try{
            return materielInfoService.add(materielInfo);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return ApiResponseResult.failure("新增物料失败！");
        }
    }

    @ApiOperation(value = "编辑物料", notes = "编辑物料")
    @PostMapping("/edit")
    public ApiResponseResult edit(@RequestBody(required=false) MaterielInfo materielInfo){
        try{
            return materielInfoService.edit(materielInfo);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return ApiResponseResult.failure("编辑物料失败！");
        }
    }

    @ApiOperation(value = "删除物料", notes = "删除物料")
    @PostMapping("/delete")
    public ApiResponseResult delete(@RequestParam(value = "id",required = false) Long id){
        try{
            return materielInfoService.delete(id);
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return ApiResponseResult.failure("删除物料失败！");
        }
    }

    @ApiOperation(value = "获取物料列表", notes = "获取物料列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mateK3Code", value = "K3物料号", required = false, dataType = "String", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "mateName", value = "物料名称", required = false, dataType = "String", paramType = "query", defaultValue = "")
    })
    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    public ApiResponseResult getlist(@RequestParam(value = "mateK3Code", required = false) String mateK3Code,
                                     @RequestParam(value = "mateName", required = false) String mateName){
        try{
            Sort sort = new Sort(Sort.Direction.DESC, "id");
            return materielInfoService.getlist(mateK3Code, mateName, super.getPageRequest(sort));
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return ApiResponseResult.failure("获取物料列表失败！");
        }
    }

    @ApiOperation(value = "获取物料列表（SRM和K3）", notes = "获取物料列表（SRM和K3）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mateK3Code", value = "K3物料号", required = false, dataType = "String", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "mateName", value = "物料名称", required = false, dataType = "String", paramType = "query", defaultValue = "")
    })
    @RequestMapping(value = "/getlistAll", method = RequestMethod.GET)
    public ApiResponseResult getlistAll(@RequestParam(value = "mateK3Code", required = false) String mateK3Code,
                                        @RequestParam(value = "mateName", required = false) String mateName){
        try{
            //SRM物料表排序
            Sort sort = new Sort(Sort.Direction.DESC, "id");
            //K3物料表排序
            Sort sort2 = new Sort(Sort.Direction.DESC, "fItemId");
            return materielInfoService.getlistAll(mateK3Code, mateName, super.getPageRequest(sort), super.getPageRequestK3(sort2));
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return ApiResponseResult.failure("获取物料列表失败！");
        }
    }

}
