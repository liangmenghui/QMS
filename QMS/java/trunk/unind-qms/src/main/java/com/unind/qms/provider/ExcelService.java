package com.unind.qms.provider;

import com.unind.base.components.file.FtpClientService;
import com.unind.base.configure.AppConfig;
import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.domain.admin.user.SysUser;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BusinessException;
import com.unind.fs.dao.file.FsFileDao;
import com.unind.fs.domain.file.FsFile;
import com.unind.qms.enumeration.BasicEnumConstants;
import com.unind.qms.enumeration.BasicStateEnum;
import com.unind.qms.web.approved.dao.ApprovedFlowRecordDao;
import com.unind.qms.web.approved.dao.ApprovedItemsRecordDao;
import com.unind.qms.web.approved.dao.ApprovedTermsScoreDao;
import com.unind.qms.web.approved.entity.*;
import com.unind.qms.web.basic.dao.ExcelTempDao;
import com.unind.qms.web.basic.entity.ExcelTemp;
import com.unind.qms.web.product.dao.ProductInfoDao;
import com.unind.qms.web.product.entity.ProductInfo;
import com.unind.qms.web.sample.dao.SampleRegularRecordDao;
import com.unind.qms.web.sample.entity.SampleRegularRecord;
import com.unind.qms.web.shipment.dao.ShipmentInspectDao;
import com.unind.qms.web.shipment.dao.ShipmentInspectRecordDao;
import com.unind.qms.web.shipment.entity.ShipmentInspect;
import com.unind.qms.web.shipment.entity.ShipmentInspectRecord;
import com.unind.qms.web.supplier.dao.PromoteDao;
import com.unind.qms.web.supplier.dao.SupplierInfoDao;
import com.unind.qms.web.supplier.entity.Promote;
import com.unind.qms.web.supplier.entity.SupplierInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Component;
import org.springside.modules.persistence.SearchFilter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@Component
public class ExcelService extends BaseOprService {

    @Autowired
    private FsFileDao fsFileDao;
    @Autowired
    private FtpClientService ftpClientService;
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private ApprovedFlowRecordDao approvedFlowRecordDao;
    @Autowired
    private ApprovedItemsRecordDao approvedItemsRecordDao;
    @Autowired
    private ApprovedTermsScoreDao approvedTermsScoreDao;
    @Autowired
    private PromoteDao promoteDao;
    @Autowired
    private ShipmentInspectDao shipmentInspectDao;
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private SampleRegularRecordDao sampleRegularRecordDao;
    @Autowired
    private ShipmentInspectRecordDao shipmentInspectRecordDao;
    @Autowired
    private ExcelTempDao excelTempDao;
    @Autowired
    private SupplierInfoDao supplierInfoDao;

    /**
     * 导出初期审核报告
     * @param bsFlowRecordId
     * @param response
     * @return
     * @throws BusinessException
     */
    public ApiResponseResult writeInitialReview(Long bsFlowRecordId, HttpServletResponse response) throws BusinessException {
        ExcelTemp excelTemp = excelTempDao.findFirstByBsType(BasicStateEnum.EXCELTEMP_SUPP.intValue());
        FsFile fsFile = fsFileDao.findOne(excelTemp.getFsFileId());
        String path = appConfig.getString("fs.qms.path")+fsFile.getBsFilePath();
//        ApiResponseResult result = ftpClientService.download(path, fsFile.getBsFileName()+fsFile.getBsFileType());
//        String path = fsFile.getBsFilePath();
        ApiResponseResult result = ftpClientService.download(path, fsFile.getBsFileName());

        InputStream inputStream = new ByteArrayInputStream((byte[]) result.getData());
        try {
            HSSFWorkbook workbook = null;
            HSSFSheet sheet = null;
            HSSFRow row = null;
            HSSFCell cell = null;

            workbook = new HSSFWorkbook(inputStream);

            sheet = workbook.getSheetAt(2); //sheet3，审核报告

            List<HSSFCellStyle> cellStyleList = getSuppCellStyle(workbook);

            int getScore = 0;
            int totalScore = 0;

            //获取项目记录数据
            List<ApprovedItemsRecord> approvedItemsRecordList = approvedItemsRecordDao.findByBsFlowRecordIdOrderByBsPriorityAsc(bsFlowRecordId);//5200

            //审核员
            List<ApprovedRecorderMap> approvedRecorderMapList = new ArrayList<ApprovedRecorderMap>(approvedItemsRecordList.get(0).getRecorderSet());
            //条款按序号排序
            Collections.sort(approvedRecorderMapList, new Comparator<ApprovedRecorderMap>() {
                @Override
                public int compare(ApprovedRecorderMap o1, ApprovedRecorderMap o2) {
                    return o1.getBsPriority()<o2.getBsPriority() ? -1 :1;
                }
            });

            row = sheet.getRow(4);
            //审核员
            cell = row.getCell(18);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(approvedRecorderMapList.get(0).getRecorderBy().getBsName());

            if(approvedRecorderMapList.size()>2){
                //审核员
                cell = row.getCell(20);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(approvedRecorderMapList.get(1).getRecorderBy().getBsName());
            }

            row = sheet.getRow(5);
            //联系方式
            cell = row.getCell(18);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(approvedRecorderMapList.get(0).getRecorderBy().getBsMobile()+"/"+approvedRecorderMapList.get(0).getRecorderBy().getBsEmail());

            row = sheet.getRow(6);
            //审核日期
            cell = row.getCell(18);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(approvedItemsRecordList.get(0).getBsCreatedTime());

            //获取供应商信息
            SupplierInfo supplierInfo = supplierInfoDao.findOne(approvedItemsRecordList.get(0).getBsSuppId());

            row = sheet.getRow(7);
            //供应商名称
            cell = row.getCell(3);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(supplierInfo.getBsSuppChieseName());
            //联系人
            cell = row.getCell(18);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(supplierInfo.getBsSuppContactName());

            row = sheet.getRow(8);
            //供应商地址
            cell = row.getCell(3);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(supplierInfo.getBsSuppAddress());
            //职务
            cell = row.getCell(18);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(supplierInfo.getBsSuppPosition());

            row = sheet.getRow(9);
            //联系方式
            cell = row.getCell(18);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(supplierInfo.getBsSuppMobile()+"/"+supplierInfo.getBsSuppEmail());


            //循环生成审核报告内容
            for(int i=0;i<approvedItemsRecordList.size()-1;i++){
                sheet.addMergedRegion(new CellRangeAddress(15+i*2, 15+i*2, 1, 6));
                sheet.addMergedRegion(new CellRangeAddress(15+i*2, 15+i*2, 17, 18));
                sheet.addMergedRegion(new CellRangeAddress(15+i*2, 15+i*2, 19, 20));
                row = sheet.createRow(15+i*2);

                //项目名列
                cell = row.createCell(1);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue((i+1)+"--"+approvedItemsRecordList.get(i).getItemsBy().getBsName());
                cell.setCellStyle(cellStyleList.get(0));

                //获取当前项目的分数
                if(StringUtils.isEmpty(approvedItemsRecordList.get(i).getBsScoreNum())){
                    return ApiResponseResult.failure("当前项目无得分，无法导出excel");
                }
                String[] scoreNum = approvedItemsRecordList.get(i).getBsScoreNum().split("/");
                getScore += Integer.parseInt(scoreNum[0]);
                totalScore += Integer.parseInt(scoreNum[1]);
                int scoreFieldNum = 10*Integer.parseInt(scoreNum[0])/Integer.parseInt(scoreNum[1]);
                int scoreColor = 0;
                if(scoreFieldNum<3){
                    scoreColor = 7;
                }else if(scoreFieldNum<5){
                    scoreColor = 6;
                }else{
                    scoreColor = 5;
                }

                //得分列
                cell = row.createCell(17);
                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                cell.setCellValue(scoreNum[0]);
                cell.setCellStyle(cellStyleList.get(scoreColor));

                //得分率列
                DecimalFormat df = new DecimalFormat("0.00");
                String scoreRate = df.format((float)(100*Integer.parseInt(scoreNum[0]))/(float)(Integer.parseInt(scoreNum[1])));
                cell = row.createCell(19);
                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                cell.setCellValue(scoreRate+"%");
                cell.setCellStyle(cellStyleList.get(scoreColor));

                //能力状态列
                for(int j=1;j<=10;j++){
                    cell = row.createCell(6+j);
                    cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
                    if(j<=scoreFieldNum){
                        cell.setCellStyle(cellStyleList.get(scoreColor-3));
                    }else cell.setCellStyle(cellStyleList.get(1));
                }

                //创建条款sheet
                HSSFSheet termSheet = workbook.createSheet((i+1)+"--"+approvedItemsRecordList.get(i).getItemsBy().getBsName());
                termSheet.setColumnWidth(0, 256*8+184);//列宽8
                termSheet.setColumnWidth(1, 256*48+184);//列宽48
                termSheet.setColumnWidth(2, 256*8+184);//列宽8
                termSheet.setColumnWidth(3, 256*30+184);//列宽30
                termSheet.setColumnWidth(4, 256*65+184);//列宽65
                row = termSheet.createRow(1);
                termSheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 1));

                //条款标题
                cell = row.createCell(0);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue((i+1)+"."+approvedItemsRecordList.get(i).getItemsBy().getBsName());
                cell.setCellStyle(cellStyleList.get(15));

                row = termSheet.createRow(2);
                //条款号
                cell = row.createCell(0);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue("条款号");
                cell.setCellStyle(cellStyleList.get(16));
                //条款内容
                cell = row.createCell(1);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue("条款内容");
                cell.setCellStyle(cellStyleList.get(16));
                //审核得分
                cell = row.createCell(2);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue("审核得分");
                cell.setCellStyle(cellStyleList.get(16));
                //审核证据/审核发现/说明
                cell = row.createCell(3);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue("审核证据/审核发现/说明");
                cell.setCellStyle(cellStyleList.get(16));
                //评分标准
                cell = row.createCell(4);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue("评分标准");
                cell.setCellStyle(cellStyleList.get(16));

                //获取条款及得分
                List<ApprovedTermsScore> approvedTermsScoreList = approvedTermsScoreDao.findByBsItemsRecordIdOrderByIdAsc(approvedItemsRecordList.get(i).getId());

                //条款按序号排序
                Collections.sort(approvedTermsScoreList, new Comparator<ApprovedTermsScore>() {
                    @Override
                    public int compare(ApprovedTermsScore o1, ApprovedTermsScore o2) {
                        String[] o1NoStr = o1.getApprovedTerms().getBsNo().split("[.]");
                        String[] o2NoStr = o2.getApprovedTerms().getBsNo().split("[.]");
                        if(Integer.parseInt(o1NoStr[0])<Integer.parseInt(o2NoStr[0])){
                            return -1;
                        }else if(Integer.parseInt(o1NoStr[0])==Integer.parseInt(o2NoStr[0])){
                            if(Integer.parseInt(o1NoStr[1])<Integer.parseInt(o2NoStr[1])){
                                return -1;
                            }else return 1;
                        }else return 1;
//                        return o1.getId()<o2.getId() ? -1 :1;
                    }
                });


                //不适合项NA数
                int NaNum = 0;

                //循环生成条款页内容
                for(int j=0;j<approvedTermsScoreList.size();j++){
                    ApprovedTermsScore approvedTermsScore = approvedTermsScoreList.get(j);

                    row = termSheet.createRow(3+j);
                    row.setHeight((short)(80*20));
                    //条款号
                    cell = row.createCell(0);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setCellValue(approvedTermsScore.getApprovedTerms().getBsNo());
                    cell.setCellStyle(cellStyleList.get(1));
                    //条款内容
                    cell = row.createCell(1);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setCellValue(approvedTermsScore.getApprovedTerms().getBsContent());
                    cell.setCellStyle(cellStyleList.get(13));
                    //审核得分
                    cell = row.createCell(2);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setCellValue(approvedTermsScore.getBsScore()==-1?"NA":approvedTermsScore.getBsScore()+"");

                    if(approvedTermsScore.getBsScore()>=3){//绿色居中
                        cell.setCellStyle(cellStyleList.get(2));
                    }else if(approvedTermsScore.getBsScore()>0){//黄色居中
                        cell.setCellStyle(cellStyleList.get(3));
                    }else if(approvedTermsScore.getBsScore()==0){//红色居中
                        cell.setCellStyle(cellStyleList.get(4));
                    }else{//默认居中
                        NaNum++;
                        cell.setCellStyle(cellStyleList.get(1));
                    }

                    //审核证据/审核发现/说明
                    cell = row.createCell(3);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setCellValue(approvedTermsScore.getBsDesc());
                    cell.setCellStyle(cellStyleList.get(13));
                    //评分标准
                    cell = row.createCell(4);
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    cell.setCellValue(approvedTermsScore.getApprovedTerms().getBsStandard());
                    cell.setCellStyle(cellStyleList.get(13));
                }
                //条款页得分和得分率
                row = termSheet.createRow(3+approvedTermsScoreList.size());
                //供应商审核得分
                cell = row.createCell(1);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue("供应商审核得分");
                cell.setCellStyle(cellStyleList.get(17));
                //供应商审核得分分数
                cell = row.createCell(2);
                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                cell.setCellValue(scoreNum[0]);
                cell.setCellStyle(cellStyleList.get(18));
                //不适合项NA数
                cell = row.createCell(3);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue("不适合项NA数");
                cell.setCellStyle(cellStyleList.get(13));

                row = termSheet.createRow(4+approvedTermsScoreList.size());
                //供应商审核得分
                cell = row.createCell(1);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue("供应商审核得分率");
                cell.setCellStyle(cellStyleList.get(17));
                //供应商审核得分分数
                cell = row.createCell(2);
                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                cell.setCellValue(scoreRate+"%");
                cell.setCellStyle(cellStyleList.get(18));
                //不适合项NA数
                cell = row.createCell(3);
                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                cell.setCellValue(NaNum);
                cell.setCellStyle(cellStyleList.get(1));
            }

            //总得分等级栏
            row = sheet.createRow(15+(approvedItemsRecordList.size()-1)*2);
            row.setHeight((short)(27*20));
            sheet.addMergedRegion(new CellRangeAddress(15+(approvedItemsRecordList.size()-1)*2, 15+(approvedItemsRecordList.size()-1)*2, 8, 9));
            sheet.addMergedRegion(new CellRangeAddress(15+(approvedItemsRecordList.size()-1)*2, 15+(approvedItemsRecordList.size()-1)*2, 10, 11));
            sheet.addMergedRegion(new CellRangeAddress(15+(approvedItemsRecordList.size()-1)*2, 15+(approvedItemsRecordList.size()-1)*2, 12, 13));
            sheet.addMergedRegion(new CellRangeAddress(15+(approvedItemsRecordList.size()-1)*2, 15+(approvedItemsRecordList.size()-1)*2, 14, 16));
            sheet.addMergedRegion(new CellRangeAddress(15+(approvedItemsRecordList.size()-1)*2, 15+(approvedItemsRecordList.size()-1)*2, 17, 18));
            sheet.addMergedRegion(new CellRangeAddress(15+(approvedItemsRecordList.size()-1)*2, 15+(approvedItemsRecordList.size()-1)*2, 19, 21));

            for(int i=1;i<22;i++){
                cell = row.createCell(i);
                cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
                cell.setCellStyle(cellStyleList.get(11));
            }

            cell = row.createCell(8);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("得分总和");
            cell.setCellStyle(cellStyleList.get(11));

            cell = row.createCell(10);
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue(getScore);
            cell.setCellStyle(cellStyleList.get(11));

            cell = row.createCell(12);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("总得分率");
            cell.setCellStyle(cellStyleList.get(11));

            DecimalFormat df = new DecimalFormat("0.00");
            String totalScoreRate = df.format((float)(100*getScore)/(float)totalScore);
            int totalScoreFieldNum = 10*getScore/totalScore;
            cell = row.createCell(14);
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue(totalScoreRate+"%");
            cell.setCellStyle(cellStyleList.get(11));

            cell = row.createCell(17);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("最终结论");
            cell.setCellStyle(cellStyleList.get(11));

            cell = row.createCell(19);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            int totalScoreColor = 0;
            if(totalScoreFieldNum<3){
                totalScoreColor = 10;
                cell.setCellValue("禁用");
            }else if(totalScoreFieldNum<5){
                totalScoreColor = 9;
                cell.setCellValue("潜在");
            }else{
                totalScoreColor = 8;
                cell.setCellValue("暂准");
            }
            cell.setCellStyle(cellStyleList.get(totalScoreColor));

            List<Promote> promoteList = promoteDao.findByBsFlowRecordIdOrderByIdAsc(approvedItemsRecordList.get(0).getBsFlowRecordId());
            //需要改进项标题
            row = sheet.createRow(16+(approvedItemsRecordList.size()-1)*2);
            row.setHeight((short)(27*20));
            sheet.addMergedRegion(new CellRangeAddress(16+(approvedItemsRecordList.size()-1)*2, 16+(approvedItemsRecordList.size()-1)*2, 1, 21));

            cell = row.createCell(1);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("需要改进提高项");
            cell.setCellStyle(cellStyleList.get(12));

            //改进项列头
            row = sheet.createRow(17+(approvedItemsRecordList.size()-1)*2);
            sheet.addMergedRegion(new CellRangeAddress(17+(approvedItemsRecordList.size()-1)*2, 17+(approvedItemsRecordList.size()-1)*2, 2, 10));
            sheet.addMergedRegion(new CellRangeAddress(17+(approvedItemsRecordList.size()-1)*2, 17+(approvedItemsRecordList.size()-1)*2, 11, 19));
            sheet.addMergedRegion(new CellRangeAddress(17+(approvedItemsRecordList.size()-1)*2, 17+(approvedItemsRecordList.size()-1)*2, 20, 21));

            for(int i=1;i<22;i++){
                cell = row.createCell(i);
                cell.setCellStyle(cellStyleList.get(1));
            }
            cell = row.createCell(1);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("条款号");
            cell.setCellStyle(cellStyleList.get(1));

            cell = row.createCell(2);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("不符合项描述");
            cell.setCellStyle(cellStyleList.get(1));

            cell = row.createCell(11);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("改进措施");
            cell.setCellStyle(cellStyleList.get(1));

            cell = row.createCell(20);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("完成日期");
            cell.setCellStyle(cellStyleList.get(1));

            //循环生成不符合项
            for(int i=0;i<promoteList.size();i++){
                row = sheet.createRow(18+(approvedItemsRecordList.size()-1)*2+i);
                sheet.addMergedRegion(new CellRangeAddress(18+(approvedItemsRecordList.size()-1)*2+i, 18+(approvedItemsRecordList.size()-1)*2+i, 2, 10));
                sheet.addMergedRegion(new CellRangeAddress(18+(approvedItemsRecordList.size()-1)*2+i, 18+(approvedItemsRecordList.size()-1)*2+i, 11, 19));
                sheet.addMergedRegion(new CellRangeAddress(18+(approvedItemsRecordList.size()-1)*2+i, 18+(approvedItemsRecordList.size()-1)*2+i, 20, 21));

                for(int j=1;j<22;j++){
                    cell = row.createCell(j);
                    cell.setCellStyle(cellStyleList.get(1));
                }

                cell = row.createCell(1);
                cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                cell.setCellValue(promoteList.get(i).getBsTermsNo());
                cell.setCellStyle(cellStyleList.get(1));

                cell = row.createCell(2);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(promoteList.get(i).getBsContent());
                cell.setCellStyle(cellStyleList.get(13));

                cell = row.createCell(11);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(promoteList.get(i).getBsAction());
                cell.setCellStyle(cellStyleList.get(13));

                cell = row.createCell(20);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(promoteList.get(i).getBsDeadline()==null?"":promoteList.get(i).getBsDeadline().toString());
                cell.setCellStyle(cellStyleList.get(14));
            }

            OutputStream output = response.getOutputStream();
            response.reset();
            response.setContentType(fsFile.getBsContentType());
            String fileName = URLEncoder.encode("供应商审核报告", "UTF-8")+ ".xls";
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            workbook.write(output);
            output.flush();
            output.close();
            inputStream.close();
            workbook = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ApiResponseResult.success();
    }

    /**
     * 导出出货检验报告
     * @param response
     * @return
     * @throws BusinessException
     */
    public ApiResponseResult writeShipmentInspect(Long bsShipmentId, HttpServletResponse response) throws BusinessException {
        ExcelTemp excelTemp = excelTempDao.findFirstByBsType(BasicStateEnum.EXCELTEMP_SHIPMENT.intValue());
        FsFile fsFile = fsFileDao.findOne(excelTemp.getFsFileId());

        String path = appConfig.getString("fs.qms.path")+fsFile.getBsFilePath();
        ApiResponseResult result = ftpClientService.download(path, fsFile.getBsFileName());

        InputStream inputStream = new ByteArrayInputStream((byte[]) result.getData());
        try {
            XSSFWorkbook workbook = null;
            XSSFSheet sheet = null;
            XSSFRow row = null;
            XSSFCell cell = null;

            workbook = new XSSFWorkbook(inputStream);

            sheet = workbook.getSheetAt(0); //sheet1，审核报告

//            List<HSSFCellStyle> cellStyleList = getCellStyle(workbook);

            //获取出货检验记录
            ShipmentInspect shipmentInspect = shipmentInspectDao.findOne(bsShipmentId);
            //获取项目记录数据
            List<ApprovedItemsRecord> approvedItemsRecordList = approvedItemsRecordDao.findByBsFlowRecordIdOrderByBsPriorityAsc(shipmentInspect.getBsFlowRecordId());
            //审核员
            List<ApprovedRecorderMap> approvedRecorderMapList = new ArrayList<ApprovedRecorderMap>(approvedItemsRecordList.get(0).getRecorderSet());

            Collections.sort(approvedRecorderMapList, new Comparator<ApprovedRecorderMap>() {
                @Override
                public int compare(ApprovedRecorderMap o1, ApprovedRecorderMap o2) {
                    return o1.getBsPriority()<o2.getBsPriority() ? -1 :1;
                }
            });
            //获取产品信息
            ProductInfo productInfo = productInfoDao.findOne(shipmentInspect.getBsPrId());

            row = sheet.getRow(2);
            //部件名称
            cell = row.getCell(2);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(productInfo.getBsPrName());
            //客户
            cell = row.getCell(7);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getBsCustomer());
            //生产日期
            cell = row.getCell(13);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getBsProductDate()==null?"":shipmentInspect.getBsProductDate().toString());

            row = sheet.getRow(3);
            //部件号
            cell = row.getCell(2);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(productInfo.getBsPrCode());
            //合同号
            cell = row.getCell(7);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getBsContractNo());
            //验货日期
            cell = row.getCell(13);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getBsInspectDate()==null?"":shipmentInspect.getBsInspectDate().toString());

            row = sheet.getRow(4);
            //图纸号
            cell = row.getCell(2);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(productInfo.getBsPaperNum());
            //批次号
            cell = row.getCell(7);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getBsBatchNo());
            //检查者
            cell = row.getCell(13);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(approvedRecorderMapList.get(0).getRecorderBy().getBsName());

            row = sheet.getRow(5);
            //编号
            cell = row.getCell(2);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getBsContractNo());
            //供应商
            cell = row.getCell(7);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(productInfo.getBsSuppChieseName());
            //产品数量
            cell = row.getCell(13);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getBsProductNum());

            row = sheet.getRow(6);
            //检验标准
            cell = row.getCell(2);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getBsInspectStandard());
            //AQL水平
            cell = row.getCell(7);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getBsAqlLevel());
            //箱子数量
            cell = row.getCell(13);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getBsBoxesNum());

            row = sheet.getRow(7);
            //检验水平
            cell = row.getCell(2);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getBsInspectLevel());
            //接收数
            cell = row.getCell(6);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getBsAcceptNum());
            //不接收数
            cell = row.getCell(9);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getBsRejectNum());
            //抽样数
            cell = row.getCell(12);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getBsSamplesNum());
            //Ins Time
            cell = row.getCell(14);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspectDao.countByBsOrderIdAndBsCreatedTimeIsLessThanEqual(shipmentInspect.getBsOrderId(), shipmentInspect.getBsCreatedTime()));

            //文件审查
            row = sheet.getRow(10);
            //性能测试时间1
            cell = row.getCell(9);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getBsTestDeviceDate()==null?"":shipmentInspect.getBsTestDeviceDate().toString());
            //性能测试时间2
            cell = row.getCell(11);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getBsTestDeviceDate1()==null?"":shipmentInspect.getBsTestDeviceDate1().toString());
            //性能测试时间3
            cell = row.getCell(13);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getBsTestDeviceDate2()==null?"":shipmentInspect.getBsTestDeviceDate2().toString());

//            row = sheet.getRow(11);
//            //性能测试结果1
//            cell = row.getCell(9);
//            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//            cell.setCellValue(shipmentInspect.getBsIsTestResult());
//            //性能测试结果2
//            cell = row.getCell(11);
//            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//            cell.setCellValue(shipmentInspect.getBsIsTestResult1());
//            //性能测试结果3
//            cell = row.getCell(13);
//            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//            cell.setCellValue(shipmentInspect.getBsIsTestResult2());

            List<SampleRegularRecord> sampleRegularRecordList = sampleRegularRecordDao.findByBsShipmentInspectIdAndAndBsIsDelOrderByIdAsc(shipmentInspect.getId(), BooleanStateEnum.FALSE.intValue());
            Long sampleRegularId = null;
            int nowRow = 0;//规格行数
            int nowColumn = 4;//规格数据当前列
            if(sampleRegularRecordList.size()>0){

                XSSFFont font = workbook.createFont();
                font.setFontName("Times New Roman");
                font.setFontHeightInPoints((short) 10);// 设置字体大小

                //边框居中单元格
                XSSFCellStyle cellStyle = workbook.createCellStyle();
                cellStyle.setBorderBottom((short) 1);   //设置下边框
                cellStyle.setBorderLeft((short) 1);   //设置左边框
                cellStyle.setBorderRight((short) 1);   //设置有边框
                cellStyle.setBorderTop((short) 1); //设置上边框
                cellStyle.setWrapText(true); //设置自动换行
                cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
                cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
                cellStyle.setFont(font);

                for(int i=0;i<sampleRegularRecordList.size();i++){
                    if(null == sampleRegularId||!sampleRegularId.equals(sampleRegularRecordList.get(i).getBsSampleRegularId())){
                        sheet.shiftRows(18+nowRow, sheet.getLastRowNum(), 1); //18+nowRow---最后一行，向下移动一行
                        row = sheet.createRow(18+nowRow);
                        row.setHeight((short)(35*20));
                        //序号
                        cell = row.createCell(0);
                        cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                        cell.setCellValue(nowRow+1);
                        cell.setCellStyle(cellStyle);
                        //规格名称
                        cell = row.createCell(1);
                        cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                        cell.setCellValue(sampleRegularRecordList.get(i).getSampleRegularBy().getBsName());
                        cell.setCellStyle(cellStyle);
                        //规格上限
                        cell = row.createCell(2);
                        cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                        cell.setCellValue(sampleRegularRecordList.get(i).getSampleRegularBy().getBsUpLimit()==null?"":sampleRegularRecordList.get(i).getSampleRegularBy().getBsUpLimit().toString());
                        cell.setCellStyle(cellStyle);
                        //规格下限
                        cell = row.createCell(3);
                        cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                        cell.setCellValue(sampleRegularRecordList.get(i).getSampleRegularBy().getBsLowLimit()==null?"":sampleRegularRecordList.get(i).getSampleRegularBy().getBsLowLimit().toString());
                        cell.setCellStyle(cellStyle);
                        //最大偏差
                        cell = row.createCell(14);
                        cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                        cell.setCellStyle(cellStyle);

                        nowRow++;
                        sampleRegularId = sampleRegularRecordList.get(i).getBsSampleRegularId();
                        nowColumn = 4;
                    }
                    //样品
                    cell = row.createCell(nowColumn);
                    cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                    cell.setCellValue(sampleRegularRecordList.get(i).getBsMeasureResult()==null?"":sampleRegularRecordList.get(i).getBsMeasureResult()==-1?"Go":sampleRegularRecordList.get(i).getBsMeasureResult()==-2?"NoGo":sampleRegularRecordList.get(i).getBsMeasureResult().toString());
                    cell.setCellStyle(cellStyle);

                    nowColumn++;
                }
            }

            row = sheet.getRow(18+nowRow);
            row.setHeight((short)(23*20));
            row = sheet.getRow(19+nowRow);
            row.setHeight((short)(18*20));
            //包装方式
            cell = row.getCell(2);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getBsPackWay());
            //标签
            cell = row.getCell(7);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getBsPackTag());
            //每栈板数量
            cell = row.getCell(13);
            cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue(shipmentInspect.getBsPackStackNum());

            row = sheet.getRow(20+nowRow);
            row.setHeight((short)(18*20));
            //密封方式
            cell = row.getCell(2);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getBsSealWay());
            //包装外观
            cell = row.getCell(7);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getBsPackAppearance());
            //每箱数量
            cell = row.getCell(13);
            cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue(shipmentInspect.getBsPackBoxNum());

            row = sheet.getRow(21+nowRow);
            row.setHeight((short)(23*20));
            //检验结果
            cell = row.getCell(5);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            int bsInspectResult = shipmentInspect.getBsInspectResult();
            cell.setCellValue(bsInspectResult==1?"Pass(可以接受)":bsInspectResult==2?"Fail(不可接受)":bsInspectResult==3?"reference(参考)":"");

            row = sheet.getRow(22+nowRow);
            row.setHeight((short)(23*20));
            //检验说明
            cell = row.getCell(0);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getBsInspectDesc());

            row = sheet.getRow(23+nowRow);
            row.setHeight((short)(23*20));
            //检验结果签名
            cell = row.getCell(11);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(approvedRecorderMapList.get(0).getRecorderBy().getBsName());
            //检验结果时间
            cell = row.getCell(14);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            //时间格式化
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            cell.setCellValue(shipmentInspect.getBsModifiedTime()==null?"":formatter.format(shipmentInspect.getBsModifiedTime()));

            //核查记录
            List<ShipmentInspectRecord> shipmentInspectRecordList = shipmentInspectRecordDao.findByBsShipmentIdOrderByIdAsc(shipmentInspect.getId());

            for(int n=0;n<shipmentInspectRecordList.size();n++){
                row = sheet.getRow(24+nowRow+n*3);
                row.setHeight((short)(27*20));
                //核查结果
                cell = row.getCell(0);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                bsInspectResult = shipmentInspectRecordList.get(n).getBsResult();
                String cellValue = cell.getStringCellValue();
                cell.setCellValue(cell.getStringCellValue() + "   " + (bsInspectResult==1?"Pass(可以接受)":bsInspectResult==2?"Fail(不可接受)":bsInspectResult==3?"reference(参考)":""));

                row = sheet.getRow(25+nowRow+n*3);
                row.setHeight((short)(27*20));
                //核查说明
                cell = row.getCell(0);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(shipmentInspectRecordList.get(n).getBsDesc());

                row = sheet.getRow(26+nowRow+n*3);
                row.setHeight((short)(27*20));
                //核查结果审核者
                cell = row.getCell(11);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(shipmentInspectRecordList.get(n).getInspectBy().getBsName());
                //检验结果时间
                cell = row.getCell(14);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(shipmentInspectRecordList.get(n).getBsCreatedTime());
            }

            OutputStream output = response.getOutputStream();
            response.reset();
            response.setContentType(fsFile.getBsContentType());
            String fileName = URLEncoder.encode("出货检验报告", "UTF-8")+ ".xlsx";
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            workbook.write(output);
            output.flush();
            output.close();
            inputStream.close();
            workbook = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ApiResponseResult.success();
    }

    /**
     * 导出产品过程审核报告
     * @param bsFlowRecordId
     * @param response
     * @return
     * @throws BusinessException
     */
    public ApiResponseResult writeProductApproved(Long bsFlowRecordId, HttpServletResponse response) throws BusinessException {
        ExcelTemp excelTemp = excelTempDao.findFirstByBsType(BasicStateEnum.EXCELTEMP_PRODUCT.intValue());
        FsFile fsFile = fsFileDao.findOne(excelTemp.getFsFileId());

        String path = appConfig.getString("fs.qms.path")+fsFile.getBsFilePath();
        ApiResponseResult result = ftpClientService.download(path, fsFile.getBsFileName());

        InputStream inputStream = new ByteArrayInputStream((byte[]) result.getData());
        try {
            XSSFWorkbook workbook = null;
            XSSFSheet sheet = null;
            XSSFRow row = null;
            XSSFCell cell = null;

            workbook = new XSSFWorkbook(inputStream);

            sheet = workbook.getSheetAt(0); //sheet1，审核报告

            List<XSSFCellStyle> cellStyleList = getProductCellStyle(workbook);
            //获取流程记录数据
            ApprovedFlowRecord approvedFlowRecord = approvedFlowRecordDao.findOne(bsFlowRecordId);

            //获取项目记录数据
            List<ApprovedItemsRecord> approvedItemsRecordList = approvedItemsRecordDao.findByBsFlowRecordIdOrderByBsPriorityAsc(bsFlowRecordId);

            //获取产品信息
            ProductInfo productInfo = productInfoDao.findOne(approvedItemsRecordList.get(0).getBsPrId());

            //获取不符合项
            List<Promote> promoteList = promoteDao.findByBsFlowRecordIdOrderByIdAsc(approvedFlowRecord.getId());

            //得分小于等于4分的条款
            List<ApprovedTermsScore> approvedTermsScoreListPromote = new ArrayList<ApprovedTermsScore>();

            SysUser sysUser = new SysUser();
            //获取SQE
            for(ApprovedRecorderMap approvedRecorderMap:approvedItemsRecordList.get(0).getRecorderSet()){
                if(approvedRecorderMap.getBsPriority() == 1){
                    sysUser = approvedRecorderMap.getRecorderBy();
                    break;
                }
            }
            //时间格式化
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            //客户信息
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("bs_item_code", productInfo.getBsPrCode());

            StringBuffer sqlbf1 = new StringBuffer();
            sqlbf1.append(" SELECT a.* FROM erp_item_customer_agg a");
            sqlbf1.append(" WHERE a.bs_item_code = :bs_item_code AND a.bs_is_del = '0' AND bs_status = 'A'");
            List<Map<String, Object>> recordList = super.findBySql(sqlbf1.toString(), param);
            String customer = "";
            if(recordList.size()>0) {
                for(Map<String, Object> map:recordList){
                    if(customer.equals("")){
                        customer += map.get("BS_CUSTOMER_NAME");
                    }else customer += "/" + map.get("BS_CUSTOMER_NAME");
                }
            }

            row = sheet.getRow(3);
            //产品名称
            cell = row.getCell(1);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(productInfo.getBsPrName());
            //客户
            cell = row.getCell(3);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(customer);
            //过程审核开始日期
            cell = row.getCell(6);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(approvedItemsRecordList.get(0).getBsCreatedTime());
            //过程类型
            cell = row.getCell(9);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(BasicEnumConstants.getProductApprovedType(approvedFlowRecord.getBsProductType()));

            row = sheet.getRow(4);
            //产品编号
            cell = row.getCell(1);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(productInfo.getBsPrCode());
            //供应商
            cell = row.getCell(3);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(productInfo.getBsSuppChieseName());
            //审核总结会议时间
            cell = row.getCell(6);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(approvedFlowRecord.getBsModifiedTime()==null?"":formatter.format(approvedFlowRecord.getBsModifiedTime()));
            //陪同审核人员
            cell = row.getCell(9);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(sysUser.getBsName());

            int nowRow = 7;
            int getScore = 0;
            int totalScore = 0;
            Boolean totalResult = true;

            for(int i=0;i<approvedItemsRecordList.size()-1;i++){
                ApprovedItemsRecord approvedItemsRecord = approvedItemsRecordList.get(i);

                //获取当前项目的分数
                String[] scoreNum = approvedItemsRecord.getBsScoreNum().split("/");
                getScore += Integer.parseInt(scoreNum[0]);
                totalScore += Integer.parseInt(scoreNum[1]);
                //当前项目是否合格
                Boolean isPass = true;
                if(approvedItemsRecord.getItemsBy().getBsScoreLine()>Integer.parseInt(scoreNum[0])){
                    isPass = false;
                    totalResult = false;
                }

                sheet.shiftRows(nowRow, sheet.getLastRowNum(), 1); //nowRow---最后一行，向下移动一行
                sheet.addMergedRegion(new CellRangeAddress(nowRow, nowRow, 0, 7));
                sheet.addMergedRegion(new CellRangeAddress(nowRow, nowRow, 8, 9));
                sheet.addMergedRegion(new CellRangeAddress(nowRow, nowRow, 10, 21));

                row = sheet.createRow(nowRow);
                //项目名
                cell = row.createCell(0);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue((i+1)+"."+approvedItemsRecord.getItemsBy().getBsName());
                cell.setCellStyle(cellStyleList.get(5));
                while(cell.getColumnIndex()<9){
                    cell = row.createCell(cell.getColumnIndex()+1);
                    cell.setCellStyle(cellStyleList.get(5));
                }
                //评分标准项目名
//                cell = row.createCell(10);
//                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//                cell.setCellValue((i+1)+"."+approvedItemsRecord.getItemsBy().getBsName());
//                cell.setCellStyle(cellStyleList.get(5));
//                while(cell.getColumnIndex()<21){
//                    cell = row.createCell(cell.getColumnIndex()+1);
//                    cell.setCellStyle(cellStyleList.get(5));
//                }
                ++nowRow;

                //条款按序号排序
                List<ApprovedTermsScore> approvedTermsScoreList= new ArrayList<ApprovedTermsScore>(approvedItemsRecord.getTermsScoreSet());
                Collections.sort(approvedTermsScoreList, new Comparator<ApprovedTermsScore>() {
                    @Override
                    public int compare(ApprovedTermsScore o1, ApprovedTermsScore o2) {
                        String[] o1NoStr = o1.getApprovedTerms().getBsNo().split("[.]");
                        String[] o2NoStr = o2.getApprovedTerms().getBsNo().split("[.]");
                        if(Integer.parseInt(o1NoStr[0])<Integer.parseInt(o2NoStr[0])){
                            return -1;
                        }else if(Integer.parseInt(o1NoStr[0])==Integer.parseInt(o2NoStr[0])){
                            if(Integer.parseInt(o1NoStr[1])<Integer.parseInt(o2NoStr[1])){
                                return -1;
                            }else return 1;
                        }else return 1;
//                        return o1.getId()<o2.getId() ? -1 :1;
                    }
                });
                sheet.shiftRows(nowRow, sheet.getLastRowNum(), approvedTermsScoreList.size()); //nowRow---最后一行，向下移动size行

                //遍历条款
                for(int j=0;j<approvedTermsScoreList.size();j++){
                    ApprovedTermsScore approvedTermsScore = approvedTermsScoreList.get(j);

                    //当前条款是否在不符合项中
                    int count = promoteDao.countByBsItemsRecordIdAndBsTermsId(approvedItemsRecord.getId(), approvedTermsScore.getBsTermsId());
                    if(count == 0){
                        approvedTermsScoreListPromote.add(approvedTermsScore);
                    }

                    sheet.addMergedRegion(new CellRangeAddress(nowRow, nowRow, 0, 6));
                    sheet.addMergedRegion(new CellRangeAddress(nowRow, nowRow, 8, 9));
                    sheet.addMergedRegion(new CellRangeAddress(nowRow, nowRow, 10, 21));

                    row = sheet.createRow(nowRow);
                    row.setHeight((short)(23*20));
                    //条款内容
                    cell = row.createCell(0);
                    cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                    cell.setCellValue(approvedTermsScore.getApprovedTerms().getBsNo()+approvedTermsScore.getApprovedTerms().getBsContent());
                    cell.setCellStyle(cellStyleList.get(4));
                    while(cell.getColumnIndex()<6){
                        cell = row.createCell(cell.getColumnIndex()+1);
                        cell.setCellStyle(cellStyleList.get(4));
                    }
                    //条款得分
                    cell = row.createCell(7);
                    cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                    cell.setCellValue(approvedTermsScore.getBsScore()==-1?"NA":approvedTermsScore.getBsScore()+"");
                    cell.setCellStyle(cellStyleList.get(1));
                    //审核证据
                    cell = row.createCell(8);
                    cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                    cell.setCellValue(approvedTermsScore.getBsDesc());
                    cell.setCellStyle(cellStyleList.get(4));
                    while(cell.getColumnIndex()<9){
                        cell = row.createCell(cell.getColumnIndex()+1);
                        cell.setCellStyle(cellStyleList.get(4));
                    }
                    //评分标准
//                    cell = row.createCell(10);
//                    cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//                    cell.setCellValue(approvedTermsScore.getApprovedTerms().getBsStandard());
//                    cell.setCellStyle(cellStyleList.get(4));
//                    while(cell.getColumnIndex()<21){
//                        cell = row.createCell(cell.getColumnIndex()+1);
//                        cell.setCellStyle(cellStyleList.get(4));
//                    }
                    ++nowRow;

                    if(isPass && approvedTermsScore.getBsScore()<approvedTermsScore.getApprovedTerms().getBsScoreLine()){
                        isPass = false;
                        totalResult = false;
                    }
                }

                sheet.shiftRows(nowRow, sheet.getLastRowNum(), 2); //nowRow---最后一行，向下移动2行

                row = sheet.createRow(nowRow);
                //得分
                cell = row.createCell(6);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue("得分");
                cell.setCellStyle(cellStyleList.get(6));
                //得分
                cell = row.createCell(7);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(scoreNum[0]);
                cell.setCellStyle(cellStyleList.get(1));
                ++nowRow;

                row = sheet.createRow(nowRow);
                //结论
                cell = row.createCell(6);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue("结论");
                cell.setCellStyle(cellStyleList.get(6));
                //结论
                cell = row.createCell(7);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(isPass?"合格":"不合格");
                cell.setCellStyle(isPass?cellStyleList.get(2):cellStyleList.get(3));
                ++nowRow;
            }

            //分数要求
//            sheet.addMergedRegion(new CellRangeAddress(nowRow-2, nowRow-2, 10, 21));
//            row = sheet.getRow(nowRow-2);
//            cell = row.createCell(10);
//            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//            cell.setCellValue("一、必须同时满足下列要求，才能判定过程审核合格");
//            cell.setCellStyle(cellStyleList.get(8));
//
//            sheet.addMergedRegion(new CellRangeAddress(nowRow-1, nowRow-1, 10, 12));
//            sheet.addMergedRegion(new CellRangeAddress(nowRow-1, nowRow-1, 14, 21));
//            row = sheet.getRow(nowRow-1);
//            cell = row.createCell(10);
//            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//            cell.setCellValue("1.试产场地得分≥5");
//            cell.setCellStyle(cellStyleList.get(9));
//
//            cell = row.createCell(14);
//            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//            cell.setCellValue("4.人员资源得分≥6；");
//            cell.setCellStyle(cellStyleList.get(0));
//
//            row = sheet.getRow(nowRow);
//            cell = row.getCell(10);
//            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//            cell.setCellValue("2. 工装、模具和检具得分≥12；");
//
//            cell = row.getCell(14);
//            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//            cell.setCellValue("5.文件得分≥21；");
//
//            row = sheet.getRow(nowRow+1);
//            cell = row.getCell(10);
//            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//            cell.setCellValue("3.原材料和外协件得分≥9；");
//
//            cell = row.getCell(14);
//            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//            cell.setCellValue("6.产品符合得分≥17分，样品尺寸符合图纸≥3分，外观符合要求≥3分，性能符合要求≥3分，可靠性5分，关键特性的CPk≥4分；");
//
//            row = sheet.getRow(nowRow+2);
//            cell = row.getCell(10);
//            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
//            cell.setCellValue("二、PPAP试产，SQE进行的产品过程审核不合格时，必须立即停止PPAP送样。");

            //总得分情况
            row = sheet.getRow(nowRow+1);
            row.setHeight((short)(25*20));
            //总得分
            cell = row.getCell(1);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(getScore);
            //总得分率
            DecimalFormat df = new DecimalFormat("0.00");
            String scoreRate = df.format((float)(100*getScore)/(float)(totalScore));
            cell = row.getCell(4);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(scoreRate+"%");
            //总得分
            cell = row.getCell(6);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(totalResult?"合格":"不合格");
            cell.setCellStyle(totalResult?cellStyleList.get(2):cellStyleList.get(3));
            //审批人
            cell = row.getCell(9);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(sysUser.getBsName()+(approvedFlowRecord.getBsModifiedTime()==null?"":formatter.format(approvedFlowRecord.getBsModifiedTime())));

            //批准情况
            row = sheet.getRow(nowRow+2);
            row.setHeight((short)(25*20));

            //不符合项sheet
            sheet = workbook.getSheetAt(1); //sheet2，不符合项
            row = sheet.getRow(3);
            //产品名称
            cell = row.getCell(1);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(productInfo.getBsPrName());
            //部件号
            cell = row.getCell(4);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(productInfo.getBsPrCode());
            //客户
            cell = row.getCell(7);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(customer);

            row = sheet.getRow(4);
            //试产类型
            cell = row.getCell(1);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(BasicEnumConstants.getProductApprovedType(approvedFlowRecord.getBsProductType()));
            //供应商
            cell = row.getCell(3);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(productInfo.getBsSuppChieseName());
            //试产/审核时间
            cell = row.getCell(6);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(approvedItemsRecordList.get(0).getBsCreatedTime());
            //审核人
            cell = row.getCell(8);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(sysUser.getBsName()+(approvedFlowRecord.getBsModifiedTime()==null?"":formatter.format(approvedFlowRecord.getBsModifiedTime())));

            //遍历不符合项
            for(int m=0;m<promoteList.size();m++){
                Promote promote = promoteList.get(m);

                sheet.addMergedRegion(new CellRangeAddress(6+m, 6+m, 1, 3));
                sheet.addMergedRegion(new CellRangeAddress(6+m, 6+m, 4, 5));
                row = sheet.createRow(6+m);
                row.setHeight((short)(56*20));
                //条款编号
                cell = row.createCell(0);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(promote.getBsTermsNo());
                cell.setCellStyle(cellStyleList.get(1));
                //不符合项
                cell = row.createCell(1);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(promote.getBsContent());
                cell.setCellStyle(cellStyleList.get(4));

                cell = row.createCell(2);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(cellStyleList.get(4));

                cell = row.createCell(3);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(cellStyleList.get(4));
                //改进措施
                cell = row.createCell(4);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(promote.getBsAction());
                cell.setCellStyle(cellStyleList.get(4));

                cell = row.createCell(5);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(cellStyleList.get(4));
                //责任人
                cell = row.createCell(6);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(promote.getBsResponsible());
                cell.setCellStyle(cellStyleList.get(1));
                //完成期限
                cell = row.createCell(7);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(promote.getBsDeadline()==null?"":promote.getBsDeadline().toString());
                cell.setCellStyle(cellStyleList.get(1));
                //改善证据
                cell = row.createCell(8);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(cellStyleList.get(1));
                //结案验证
                cell = row.createCell(9);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(cellStyleList.get(1));
            }

            //遍历得分小于等于4分
            for(int n=0;n<approvedTermsScoreListPromote.size();n++){
                ApprovedTermsScore approvedTermsScore = approvedTermsScoreListPromote.get(n);

                sheet.addMergedRegion(new CellRangeAddress(6+promoteList.size()+n, 6+promoteList.size()+n, 1, 3));
                sheet.addMergedRegion(new CellRangeAddress(6+promoteList.size()+n, 6+promoteList.size()+n, 4, 5));
                row = sheet.createRow(6+promoteList.size()+n);
                row.setHeight((short)(56*20));
                //条款编号
                cell = row.createCell(0);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(approvedTermsScore.getApprovedTerms().getBsNo());
                cell.setCellStyle(cellStyleList.get(1));
                //不符合项
                cell = row.createCell(1);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(approvedTermsScore.getBsDesc());
                cell.setCellStyle(cellStyleList.get(4));

                cell = row.createCell(2);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(cellStyleList.get(4));

                cell = row.createCell(3);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(cellStyleList.get(4));
                //改进措施
                cell = row.createCell(4);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(cellStyleList.get(4));

                cell = row.createCell(5);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(cellStyleList.get(4));
                //责任人
                cell = row.createCell(6);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(cellStyleList.get(1));
                //完成期限
                cell = row.createCell(7);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(cellStyleList.get(1));
                //改善证据
                cell = row.createCell(8);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(cellStyleList.get(1));
                //结案验证
                cell = row.createCell(9);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(cellStyleList.get(1));
            }

            OutputStream output = response.getOutputStream();
            response.reset();
            response.setContentType(fsFile.getBsContentType());
            String fileName = URLEncoder.encode("产品过程审核报告", "UTF-8")+ ".xlsx";
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            workbook.write(output);
            output.flush();
            output.close();
            inputStream.close();
            workbook = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ApiResponseResult.success();
    }

    /**
     * 导出过程审核产品清单
     * @param keyWord
     * @param startDate
     * @param endDate
     * @param bsConclusion
     * @param response
     * @return
     * @throws BusinessException
     */
    public ApiResponseResult writeProcessApprovedExcel(String keyWord, Date startDate, Date endDate, Integer bsConclusion, HttpServletResponse response) throws BusinessException {
        try {
            List<SearchFilter> filters = new ArrayList<SearchFilter>();
            filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BaseEnumConstants.FALSE));
            filters.add(new SearchFilter("bsItemStatus", SearchFilter.Operator.EQ, "Active"));
            filters.add(new SearchFilter("bsVendorStatus", SearchFilter.Operator.EQ, "Y"));
            filters.add(new SearchFilter("approvedFlowRecordBy.bsResult", SearchFilter.Operator.GTE, 1));

            if(null != startDate){
                filters.add(new SearchFilter("approvedFlowRecordBy.bsCreatedTime", SearchFilter.Operator.GTE, startDate));
            }

            if(null != endDate){
                filters.add(new SearchFilter("approvedFlowRecordBy.bsCreatedTime", SearchFilter.Operator.LTE, endDate));
            }

            if(null != bsConclusion && bsConclusion != 0){
                filters.add(new SearchFilter("approvedFlowRecordBy.bsConclusion", SearchFilter.Operator.EQ, bsConclusion));
            }

            List<SearchFilter> filters1 = new ArrayList<SearchFilter>();
            if (StringUtils.isNotEmpty(keyWord)) {
                filters1.add(new SearchFilter("bsPrCode", SearchFilter.Operator.LIKE, keyWord));
                filters1.add(new SearchFilter("bsPrName", SearchFilter.Operator.LIKE, keyWord));
                filters1.add(new SearchFilter("bsCateDesc", SearchFilter.Operator.LIKE, keyWord));
                filters1.add(new SearchFilter("bsSuppChieseName", SearchFilter.Operator.LIKE, keyWord));
                filters1.add(new SearchFilter("bsSqe", SearchFilter.Operator.LIKE, keyWord));
            }
            Specifications<ProductInfo> spec = Specifications.where(super.and(filters, ProductInfo.class));
            Specifications<ProductInfo> spec1 = spec.and(super.or(filters1, ProductInfo.class));

            Sort sort = new Sort(Sort.Direction.DESC,"approvedFlowRecordBy.id");
            List<ProductInfo> productInfoList = productInfoDao.findAll(spec1, sort);

            XSSFWorkbook workbook = null;
            XSSFSheet sheet = null;
            XSSFRow row = null;
            XSSFCell cell = null;

            workbook = new XSSFWorkbook();

            sheet = workbook.createSheet("清单"); //sheet1
            sheet.setDisplayGridlines(false);
            sheet.setColumnWidth(0, 256*5+184);
            sheet.setColumnWidth(1, 256*11+184);
            sheet.setColumnWidth(2, 256*20+184);
            sheet.setColumnWidth(3, 256*15+184);
            sheet.setColumnWidth(4, 256*20+184);
            sheet.setColumnWidth(5, 256*8+184);
            sheet.setColumnWidth(6, 256*8+184);
            sheet.setColumnWidth(7, 256*11+184);
            sheet.setColumnWidth(8, 256*9+184);
            sheet.setColumnWidth(9, 256*22+184);

            List<XSSFCellStyle> cellStyleList = getProductBillCellStyle(workbook);

            //标题
            row = sheet.createRow(1);
            row.setHeight((short)(27*20));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 8));
            //标题
            cell = row.createCell(2);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("过程审核产品清单");
            cell.setCellStyle(cellStyleList.get(0));

            //统计条件
            row = sheet.createRow(2);
            row.setHeight((short)(23*20));
            //统计期间
            cell = row.createCell(2);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("统计期间：");
            cell.setCellStyle(cellStyleList.get(1));

            //初始时间
            cell = row.createCell(3);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(startDate);
            cell.setCellStyle(cellStyleList.get(2));

            //截止时间
            cell = row.createCell(4);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(endDate);
            cell.setCellStyle(cellStyleList.get(2));

            //表格列头
            row = sheet.createRow(3);
            row.setHeight((short)(36*20));
            //序号
            cell = row.createCell(0);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("NO");
            cell.setCellStyle(cellStyleList.get(3));

            //产品编号
            cell = row.createCell(1);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("产品编号");
            cell.setCellStyle(cellStyleList.get(3));

            //产品名称
            cell = row.createCell(2);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("产品名称");
            cell.setCellStyle(cellStyleList.get(3));

            //产品类型
            cell = row.createCell(3);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("产品类型");
            cell.setCellStyle(cellStyleList.get(3));

            //供应商名称
            cell = row.createCell(4);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("供应商名称");
            cell.setCellStyle(cellStyleList.get(3));

            //审核类别
            cell = row.createCell(5);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("审核类别");
            cell.setCellStyle(cellStyleList.get(3));

            //SQE
            cell = row.createCell(6);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("SQE");
            cell.setCellStyle(cellStyleList.get(3));

            //审核完成日期
            cell = row.createCell(7);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("审核完成日期");
            cell.setCellStyle(cellStyleList.get(3));

            //审核结果
            cell = row.createCell(8);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("审核结果");
            cell.setCellStyle(cellStyleList.get(3));

            //备注
            cell = row.createCell(9);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("备注");
            cell.setCellStyle(cellStyleList.get(3));

            for(int i=0;i<productInfoList.size();i++){
                ProductInfo productInfo = productInfoList.get(i);

                //表格列头
                row = sheet.createRow(4+i);
                row.setHeight((short)(27*20));
                //序号
                cell = row.createCell(0);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(i+1);
                cell.setCellStyle(cellStyleList.get(4));

                //产品编号
                cell = row.createCell(1);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(productInfo.getBsPrCode());
                cell.setCellStyle(cellStyleList.get(4));

                //产品名称
                cell = row.createCell(2);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(productInfo.getBsPrName());
                cell.setCellStyle(cellStyleList.get(4));

                //产品类型
                cell = row.createCell(3);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(productInfo.getBsCateDesc());
                cell.setCellStyle(cellStyleList.get(4));

                //供应商名称
                cell = row.createCell(4);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(productInfo.getBsSuppChieseName());
                cell.setCellStyle(cellStyleList.get(4));

                //审核类别
                cell = row.createCell(5);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(productInfo.getApprovedFlowRecordBy().getFlowBy().getBsName());
                cell.setCellStyle(cellStyleList.get(4));

                //SQE
                cell = row.createCell(6);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(productInfo.getBsSqe());
                cell.setCellStyle(cellStyleList.get(4));

                //审核完成日期
                cell = row.createCell(7);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(productInfo.getApprovedFlowRecordBy().getBsModifiedTime());
                cell.setCellStyle(cellStyleList.get(5));

                //审核结果
                cell = row.createCell(8);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(productInfo.getApprovedFlowRecordBy().getBsConclusion()==4?"合格":"不合格");
                cell.setCellStyle(cellStyleList.get(4));

                //备注
                cell = row.createCell(9);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(cellStyleList.get(4));
            }

            OutputStream output = response.getOutputStream();
            response.reset();
            String fileName = URLEncoder.encode("过程审核产品清单", "UTF-8")+ ".xlsx";
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            workbook.write(output);
            output.flush();
            output.close();
            workbook = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ApiResponseResult.success();
    }

    /**
     * 导出成品检验产品清单
     * @param keyWord
     * @param startDate
     * @param endDate
     * @param bsConclusion
     * @param response
     * @return
     * @throws BusinessException
     */
    public ApiResponseResult writeProductApprovedExcel(String keyWord, Date startDate, Date endDate, Integer bsConclusion, HttpServletResponse response) throws BusinessException {
        try {
            List<SearchFilter> filters = new ArrayList<SearchFilter>();
            filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BaseEnumConstants.FALSE));
            filters.add(new SearchFilter("bsItemStatus", SearchFilter.Operator.EQ, "Active"));
            filters.add(new SearchFilter("bsVendorStatus", SearchFilter.Operator.EQ, "Y"));
            filters.add(new SearchFilter("approvedFlowRecordByProduct.bsResult", SearchFilter.Operator.GTE, 1));

            if(null != startDate){
                filters.add(new SearchFilter("approvedFlowRecordByProduct.bsCreatedTime", SearchFilter.Operator.GTE, startDate));
            }

            if(null != endDate){
                filters.add(new SearchFilter("approvedFlowRecordByProduct.bsCreatedTime", SearchFilter.Operator.LTE, endDate));
            }

            if(null != bsConclusion && bsConclusion != 0){
                filters.add(new SearchFilter("approvedFlowRecordByProduct.bsConclusion", SearchFilter.Operator.EQ, bsConclusion));
            }

            List<SearchFilter> filters1 = new ArrayList<SearchFilter>();
            if (StringUtils.isNotEmpty(keyWord)) {
                filters1.add(new SearchFilter("bsPrCode", SearchFilter.Operator.LIKE, keyWord));
                filters1.add(new SearchFilter("bsPrName", SearchFilter.Operator.LIKE, keyWord));
                filters1.add(new SearchFilter("bsCateDesc", SearchFilter.Operator.LIKE, keyWord));
                filters1.add(new SearchFilter("bsSuppChieseName", SearchFilter.Operator.LIKE, keyWord));
                filters1.add(new SearchFilter("bsSqe", SearchFilter.Operator.LIKE, keyWord));
            }
            Specifications<ProductInfo> spec = Specifications.where(super.and(filters, ProductInfo.class));
            Specifications<ProductInfo> spec1 = spec.and(super.or(filters1, ProductInfo.class));

            Sort sort = new Sort(Sort.Direction.DESC,"approvedFlowRecordByProduct.id");
            List<ProductInfo> productInfoList = productInfoDao.findAll(spec1, sort);

            XSSFWorkbook workbook = null;
            XSSFSheet sheet = null;
            XSSFRow row = null;
            XSSFCell cell = null;

            workbook = new XSSFWorkbook();

            sheet = workbook.createSheet("清单"); //sheet1
            sheet.setDisplayGridlines(false);
            sheet.setColumnWidth(0, 256*5+184);
            sheet.setColumnWidth(1, 256*11+184);
            sheet.setColumnWidth(2, 256*20+184);
            sheet.setColumnWidth(3, 256*15+184);
            sheet.setColumnWidth(4, 256*20+184);
            sheet.setColumnWidth(5, 256*8+184);
            sheet.setColumnWidth(6, 256*8+184);
            sheet.setColumnWidth(7, 256*11+184);
            sheet.setColumnWidth(8, 256*9+184);
            sheet.setColumnWidth(9, 256*22+184);

            List<XSSFCellStyle> cellStyleList = getProductBillCellStyle(workbook);

            //标题
            row = sheet.createRow(1);
            row.setHeight((short)(27*20));
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 8));
            //标题
            cell = row.createCell(2);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("成品检验产品清单");
            cell.setCellStyle(cellStyleList.get(0));

            //统计条件
            row = sheet.createRow(2);
            row.setHeight((short)(23*20));
            //统计期间
            cell = row.createCell(2);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("统计期间：");
            cell.setCellStyle(cellStyleList.get(1));

            //初始时间
            cell = row.createCell(3);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(startDate);
            cell.setCellStyle(cellStyleList.get(2));

            //截止时间
            cell = row.createCell(4);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(endDate);
            cell.setCellStyle(cellStyleList.get(2));

            //表格列头
            row = sheet.createRow(3);
            row.setHeight((short)(36*20));
            //序号
            cell = row.createCell(0);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("NO");
            cell.setCellStyle(cellStyleList.get(3));

            //产品编号
            cell = row.createCell(1);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("产品编号");
            cell.setCellStyle(cellStyleList.get(3));

            //产品名称
            cell = row.createCell(2);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("产品名称");
            cell.setCellStyle(cellStyleList.get(3));

            //产品类型
            cell = row.createCell(3);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("产品类型");
            cell.setCellStyle(cellStyleList.get(3));

            //供应商名称
            cell = row.createCell(4);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("供应商名称");
            cell.setCellStyle(cellStyleList.get(3));

            //审核类别
            cell = row.createCell(5);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("审核类别");
            cell.setCellStyle(cellStyleList.get(3));

            //SQE
            cell = row.createCell(6);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("SQE");
            cell.setCellStyle(cellStyleList.get(3));

            //审核完成日期
            cell = row.createCell(7);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("审核完成日期");
            cell.setCellStyle(cellStyleList.get(3));

            //审核结果
            cell = row.createCell(8);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("审核结果");
            cell.setCellStyle(cellStyleList.get(3));

            //备注
            cell = row.createCell(9);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("备注");
            cell.setCellStyle(cellStyleList.get(3));

            for(int i=0;i<productInfoList.size();i++){
                ProductInfo productInfo = productInfoList.get(i);

                //表格列头
                row = sheet.createRow(4+i);
                row.setHeight((short)(27*20));
                //序号
                cell = row.createCell(0);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(i+1);
                cell.setCellStyle(cellStyleList.get(4));

                //产品编号
                cell = row.createCell(1);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(productInfo.getBsPrCode());
                cell.setCellStyle(cellStyleList.get(4));

                //产品名称
                cell = row.createCell(2);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(productInfo.getBsPrName());
                cell.setCellStyle(cellStyleList.get(4));

                //产品类型
                cell = row.createCell(3);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(productInfo.getBsCateDesc());
                cell.setCellStyle(cellStyleList.get(4));

                //供应商名称
                cell = row.createCell(4);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(productInfo.getBsSuppChieseName());
                cell.setCellStyle(cellStyleList.get(4));

                //审核类别
                cell = row.createCell(5);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(productInfo.getApprovedFlowRecordByProduct().getFlowBy().getBsName());
                cell.setCellStyle(cellStyleList.get(4));

                //SQE
                cell = row.createCell(6);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(productInfo.getBsSqe());
                cell.setCellStyle(cellStyleList.get(4));

                //审核完成日期
                cell = row.createCell(7);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(productInfo.getApprovedFlowRecordByProduct().getBsModifiedTime());
                cell.setCellStyle(cellStyleList.get(5));

                //审核结果
                cell = row.createCell(8);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                if(productInfo.getApprovedFlowRecordByProduct().getBsConclusion()==0){
                    cell.setCellValue("不可接受");
                }
                if(productInfo.getApprovedFlowRecordByProduct().getBsConclusion()==6){
                    cell.setCellValue("可以接受");
                }
                if(productInfo.getApprovedFlowRecordByProduct().getBsConclusion()==7){
                    cell.setCellValue("不可接受");
                }
                if(productInfo.getApprovedFlowRecordByProduct().getBsConclusion()==8){
                    cell.setCellValue("让步");
                }
                cell.setCellStyle(cellStyleList.get(4));

                //备注
                cell = row.createCell(9);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellStyle(cellStyleList.get(4));
            }

            OutputStream output = response.getOutputStream();
            response.reset();
            String fileName = URLEncoder.encode("成品检验产品清单", "UTF-8")+ ".xlsx";
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            workbook.write(output);
            output.flush();
            output.close();
            workbook = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ApiResponseResult.success();
    }

    /**
     * 供应商审核单元格样式集合
     * @param workbook
     * @return
     */
    public List<HSSFCellStyle> getSuppCellStyle(HSSFWorkbook workbook) {
        List<HSSFCellStyle> cellStyleList = new ArrayList<HSSFCellStyle>();

        HSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 8);// 设置字体大小

        //默认样式---cellStyleList0
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //边框居中单元格---cellStyleList1
        cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom((short) 1);   //设置下边框
        cellStyle.setBorderLeft((short) 1);   //设置左边框
        cellStyle.setBorderRight((short) 1);   //设置有边框
        cellStyle.setBorderTop((short) 1); //设置上边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //边框绿色居中单元格---cellStyleList2
        cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom((short) 1);   //设置下边框
        cellStyle.setBorderLeft((short) 1);   //设置左边框
        cellStyle.setBorderRight((short) 1);   //设置有边框
        cellStyle.setBorderTop((short) 1); //设置上边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//填充单元格

        String color = "00b050";
        //转为RGB码
        int r = Integer.parseInt((color.substring(0, 2)), 16);   //转为16进制
        int g = Integer.parseInt((color.substring(2, 4)), 16);
        int b = Integer.parseInt((color.substring(4, 6)), 16);

        HSSFPalette palette = workbook.getCustomPalette();//自定义cell颜色
        palette.setColorAtIndex((short) 9, (byte) r, (byte) g, (byte) b);//这里的9是索引

        cellStyle.setFillForegroundColor((short) 9);//填充颜色
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //边框黄色居中单元格---cellStyleList3
        cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom((short) 1);   //设置下边框
        cellStyle.setBorderLeft((short) 1);   //设置左边框
        cellStyle.setBorderRight((short) 1);   //设置有边框
        cellStyle.setBorderTop((short) 1); //设置上边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//填充单元格
        cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);//填充颜色
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //边框红色居中单元格---cellStyleList4
        cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom((short) 1);   //设置下边框
        cellStyle.setBorderLeft((short) 1);   //设置左边框
        cellStyle.setBorderRight((short) 1);   //设置有边框
        cellStyle.setBorderTop((short) 1); //设置上边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//填充单元格
        cellStyle.setFillForegroundColor(HSSFColor.RED.index);//填充颜色
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //绿色居中单元格---cellStyleList5
        cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//填充单元格
        cellStyle.setFillForegroundColor((short) 9);//填充颜色
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //黄色居中单元格---cellStyleList6
        cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//填充单元格
        cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);//填充颜色
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //红色居中单元格---cellStyleList7
        cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//填充单元格
        cellStyle.setFillForegroundColor(HSSFColor.RED.index);//填充颜色
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //绿色加粗居中下边框单元格---cellStyleList8
        font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 8);// 设置字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗字体

        cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom((short) 2);   //设置下边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//填充单元格
        cellStyle.setFillForegroundColor((short) 9);//填充颜色
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //黄色加粗居中下边框单元格---cellStyleList9
        cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom((short) 2);   //设置下边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//填充单元格
        cellStyle.setFillForegroundColor(HSSFColor.YELLOW.index);//填充颜色
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //红色加粗居中下边框单元格---cellStyleList10
        cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom((short) 2);   //设置下边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//填充单元格
        cellStyle.setFillForegroundColor(HSSFColor.RED.index);//填充颜色
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //总得分栏灰色样式---cellStyleList11
        cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom((short) 2);   //设置下边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//填充单元格
        cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);//填充颜色
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //加粗居中样式---cellStyleList12
        cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //靠左居中边框样式---cellStyleList13
        font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 8);// 设置字体大小

        cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom((short) 1);   //设置下边框
        cellStyle.setBorderLeft((short) 1);   //设置左边框
        cellStyle.setBorderRight((short) 1);   //设置有边框
        cellStyle.setBorderTop((short) 1); //设置上边框
        cellStyle.setWrapText(true); //设置自动换行
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //日期居中边框样式---cellStyleList14
        cellStyle = workbook.createCellStyle();
        HSSFDataFormat format = workbook.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("yyyy-MM-dd"));
        cellStyle.setBorderBottom((short) 1);   //设置下边框
        cellStyle.setBorderLeft((short) 1);   //设置左边框
        cellStyle.setBorderRight((short) 1);   //设置有边框
        cellStyle.setBorderTop((short) 1); //设置上边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //靠左加粗样式---cellStyleList15
        font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 8);// 设置字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗字体

        cellStyle = workbook.createCellStyle();
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //居中加粗边框样式---cellStyleList16
        cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom((short) 1);   //设置下边框
        cellStyle.setBorderLeft((short) 1);   //设置左边框
        cellStyle.setBorderRight((short) 1);   //设置有边框
        cellStyle.setBorderTop((short) 1); //设置上边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //靠右边框蓝色样式---cellStyleList17
        font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 8);// 设置字体大小

        cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom((short) 1);   //设置下边框
        cellStyle.setBorderLeft((short) 1);   //设置左边框
        cellStyle.setBorderRight((short) 1);   //设置有边框
        cellStyle.setBorderTop((short) 1); //设置上边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);//靠右
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//填充单元格
        cellStyle.setFillForegroundColor(HSSFColor.CORNFLOWER_BLUE.index);//填充颜色
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //居中边框蓝色样式---cellStyleList18
        cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom((short) 1);   //设置下边框
        cellStyle.setBorderLeft((short) 1);   //设置左边框
        cellStyle.setBorderRight((short) 1);   //设置有边框
        cellStyle.setBorderTop((short) 1); //设置上边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//填充单元格
        cellStyle.setFillForegroundColor(HSSFColor.CORNFLOWER_BLUE.index);//填充颜色
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        return cellStyleList;
    }

    /**
     * 产品审核单元格样式集合
     * @param workbook
     * @return
     */
    public List<XSSFCellStyle> getProductCellStyle(XSSFWorkbook workbook) {
        List<XSSFCellStyle> cellStyleList = new ArrayList<XSSFCellStyle>();

        XSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 8);// 设置字体大小

        //默认样式---cellStyleList0
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //边框居中单元格---cellStyleList1
        cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom((short) 1);   //设置下边框
        cellStyle.setBorderLeft((short) 1);   //设置左边框
        cellStyle.setBorderRight((short) 1);   //设置有边框
        cellStyle.setBorderTop((short) 1); //设置上边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //边框绿色居中单元格---cellStyleList2
        cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom((short) 1);   //设置下边框
        cellStyle.setBorderLeft((short) 1);   //设置左边框
        cellStyle.setBorderRight((short) 1);   //设置有边框
        cellStyle.setBorderTop((short) 1); //设置上边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//填充单元格
        cellStyle.setFillForegroundColor(new XSSFColor( new Color(0, 176, 80)));//填充颜色
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //边框红色居中单元格---cellStyleList3
        cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom((short) 1);   //设置下边框
        cellStyle.setBorderLeft((short) 1);   //设置左边框
        cellStyle.setBorderRight((short) 1);   //设置有边框
        cellStyle.setBorderTop((short) 1); //设置上边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//填充单元格
        cellStyle.setFillForegroundColor(HSSFColor.RED.index);//填充颜色
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //靠左居中边框样式---cellStyleList4
        font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 8);// 设置字体大小

        cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom((short) 1);   //设置下边框
        cellStyle.setBorderLeft((short) 1);   //设置左边框
        cellStyle.setBorderRight((short) 1);   //设置有边框
        cellStyle.setBorderTop((short) 1); //设置上边框
        cellStyle.setWrapText(true); //设置自动换行
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //靠左加粗居中边框样式---cellStyleList5
        font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 8);// 设置字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗字体

        cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom((short) 1);   //设置下边框
        cellStyle.setBorderLeft((short) 1);   //设置左边框
        cellStyle.setBorderRight((short) 1);   //设置有边框
        cellStyle.setBorderTop((short) 1); //设置上边框
        cellStyle.setWrapText(true); //设置自动换行
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //居中加粗边框样式---cellStyleList6
        cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom((short) 1);   //设置下边框
        cellStyle.setBorderLeft((short) 1);   //设置左边框
        cellStyle.setBorderRight((short) 1);   //设置有边框
        cellStyle.setBorderTop((short) 1); //设置上边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //靠左垂直居中加粗样式---cellStyleList7
        cellStyle = workbook.createCellStyle();
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //靠左垂直居中左边框加粗样式---cellStyleList8
        cellStyle = workbook.createCellStyle();
        cellStyle.setBorderLeft((short) 1);   //设置左边框
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //靠左垂直居中左边框样式---cellStyleList9
        font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setFontHeightInPoints((short) 8);// 设置字体大小

        cellStyle = workbook.createCellStyle();
        cellStyle.setBorderLeft((short) 1);   //设置左边框
        cellStyle.setWrapText(true); //设置自动换行
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        return cellStyleList;
    }

    /**
     * 过程审核产品清单单元格样式集合
     * @param workbook
     * @return
     */
    public List<XSSFCellStyle> getProductBillCellStyle(XSSFWorkbook workbook) {
        List<XSSFCellStyle> cellStyleList = new ArrayList<XSSFCellStyle>();

        XSSFFont font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 14);// 设置字体大小

        //14字体居中单元格---cellStyleList0
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //11字体靠右单元格---cellStyleList1
        font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 11);// 设置字体大小

        cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);//靠右
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //11字体靠左日期单元格---cellStyleList2
        cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);//靠左
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        XSSFDataFormat format= workbook.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("yyyy-MM-dd"));
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //10字体边框加粗居中单元格---cellStyleList3
        font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);// 设置字体大小
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗字体

        cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom((short) 1);   //设置下边框
        cellStyle.setBorderLeft((short) 1);   //设置左边框
        cellStyle.setBorderRight((short) 1);   //设置有边框
        cellStyle.setBorderTop((short) 1); //设置上边框
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //9字体边框居中单元格---cellStyleList4
        font = workbook.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 9);// 设置字体大小

        cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom((short) 1);   //设置下边框
        cellStyle.setBorderLeft((short) 1);   //设置左边框
        cellStyle.setBorderRight((short) 1);   //设置有边框
        cellStyle.setBorderTop((short) 1); //设置上边框
        cellStyle.setWrapText(true); //设置自动换行
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        //9字体边框居中日期单元格---cellStyleList5
        cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom((short) 1);   //设置下边框
        cellStyle.setBorderLeft((short) 1);   //设置左边框
        cellStyle.setBorderRight((short) 1);   //设置有边框
        cellStyle.setBorderTop((short) 1); //设置上边框
        cellStyle.setWrapText(true); //设置自动换行
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        format= workbook.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("yyyy-MM-dd"));
        cellStyle.setFont(font);
        cellStyleList.add(cellStyle);

        return cellStyleList;
    }
}