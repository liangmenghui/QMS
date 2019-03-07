package com.unind.qms.web.basic.controller;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;
import com.unind.fs.domain.file.FsFile;
import com.unind.qms.provider.ExcelFeedbackService;
import com.unind.qms.provider.ExcelService;
import com.unind.qms.web.basic.entity.FeedbackHandler;
import com.unind.qms.web.basic.service.FeedbackHandlerService;
import com.unind.qms.web.basic.service.FileQmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * Qms文件管理
 * @author chen
 *
 */
@Api(description = "Qms文件管理")
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/fileQms")
public class FileQmsController extends WebController {
	@Autowired
	private FileQmsService fileQmsService;
	@Autowired
	private ExcelService excelService;
	@Autowired
	private ExcelFeedbackService excelFeedbackService;

	@ApiOperation(value="上传文件", notes="上传文件")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file", value = "附件", dataType = "MultipartFile", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ApiResponseResult upload(MultipartFile file) {
		try {
			FsFile fsFile = new FsFile();
			return fileQmsService.upload(fsFile, file);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@ApiOperation(value="下载文件", notes="下载文件")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "fsFileId", value = "文件ID", required = true, dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public void get(@RequestParam(value = "fsFileId", required = true) Long fsFileId) {
		try {
			fileQmsService.get(fsFileId, getResponse());
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@ApiOperation(value="下载初评报告", notes="下载初评报告")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bsFlowRecordId", value = "流程记录ID", required = true, dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/getReviewExcel", method = RequestMethod.GET)
	public void getReviewExcel(Long bsFlowRecordId) {
		try {
			excelService.writeInitialReview(bsFlowRecordId,getResponse());
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@ApiOperation(value="下载客诉报告", notes="下载客诉报告")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bsFeedbackId", value = "客诉ID", required = true, dataType = "Long", paramType = "query", defaultValue = "")
	})
	@RequestMapping(value = "/getFeedbackExcel", method = RequestMethod.GET)
	public void getFeedbackExcel(Long bsFeedbackId){
		try{
			excelFeedbackService.exportFeedbackReport(bsFeedbackId, getResponse());
		}catch(BusinessException e){
			e.printStackTrace();
		}
	}

	@ApiOperation(value="下载客诉退款报告", notes="下载客诉退款报告")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bsFeedbackId", value = "客诉ID", required = true, dataType = "Long", paramType = "query", defaultValue = "")
	})
	@RequestMapping(value = "/getRefundExcel", method = RequestMethod.GET)
	public void getRefundExcel(Long bsFeedbackId){
		try{
			excelFeedbackService.exportFeedbackRefund(bsFeedbackId, getResponse());
		}catch(BusinessException e){
			e.printStackTrace();
		}
	}

	@ApiOperation(value="下载出货检验报告", notes="下载出货检验报告")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bsShipmentId", value = "出货检验ID", required = true, dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/getShipmentExcel", method = RequestMethod.GET)
	public void getShipmentExcel(Long bsShipmentId) {
		try {
			excelService.writeShipmentInspect(bsShipmentId,getResponse());
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@ApiOperation(value="下载产品过程审核报告", notes="下载产品过程审核报告")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "bsFlowRecordId", value = "流程记录ID", required = true, dataType = "Long", paramType="query",defaultValue=""),
	})
	@RequestMapping(value = "/getProductExcel", method = RequestMethod.GET)
	public void getProductExcel(Long bsFlowRecordId) {
		try {
			excelService.writeProductApproved(bsFlowRecordId,getResponse());
		} catch (BusinessException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
