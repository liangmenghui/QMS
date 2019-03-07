package com.unind.qms.web.risk.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.qms.web.risk.entity.RiskEvidence;
import com.unind.qms.web.risk.service.RiskEvidenceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 风险证据描述模块
 * @author Shen
 */
@Api(description = "风险证据描述模块")
@RestController
@RequestMapping(value = Constants.CONTEXT_PATH + "/riskEvidence")
public class RiskEvidenceController extends WebController {

    @Autowired
    private RiskEvidenceService riskEvidenceService;

    @ApiOperation(value="新增风险证据描述", notes="新增风险证据描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "附件", dataType = "MultipartFile", paramType = "query", defaultValue = "")
    })
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResponseResult add(RiskEvidence riskEvidence, MultipartFile file){
        try{
            return riskEvidenceService.add(riskEvidence, file);
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value="删除风险证据描述", notes="删除风险证据描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType = "query", defaultValue = ""),
    })
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ApiResponseResult delete(Long id){
        try{
            return riskEvidenceService.delete(id);
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "获取风险证据描述", notes = "获取风险证据描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bsPrId", value = "产品ID", dataType = "Long", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "bsSuppId", value = "供应商ID", dataType = "Long", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "bsRiskNo", value = "风险序号", dataType = "Integer", paramType = "query", defaultValue = "")
    })
    @RequestMapping(value = "/getlist", method = RequestMethod.GET)
    public ApiResponseResult getlist(Long bsPrId, Long bsSuppId, Integer bsRiskNo){
        try{
            Sort sort = new Sort(Sort.Direction.DESC, "id");
            return riskEvidenceService.getlist(bsPrId, bsSuppId, bsRiskNo, super.getPageRequest(sort));
        }catch(BusinessException e){
            e.printStackTrace();
            return ApiResponseResult.failure(e.getMessage());
        }
    }

}