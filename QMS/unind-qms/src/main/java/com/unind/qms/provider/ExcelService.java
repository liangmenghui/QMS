package com.unind.qms.provider;

import com.unind.base.components.file.FtpClientService;
import com.unind.base.configure.AppConfig;
import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.provider.BusinessException;
import com.unind.fs.dao.file.FsFileDao;
import com.unind.fs.domain.file.FsFile;
import com.unind.qms.web.approved.dao.ApprovedItemsRecordDao;
import com.unind.qms.web.approved.dao.ApprovedTermsScoreDao;
import com.unind.qms.web.approved.entity.ApprovedItemsRecord;
import com.unind.qms.web.approved.entity.ApprovedTermsScore;
import com.unind.qms.web.product.dao.ProductInfoDao;
import com.unind.qms.web.product.entity.ProductInfo;
import com.unind.qms.web.sample.dao.SampleRegularRecordDao;
import com.unind.qms.web.sample.entity.SampleRegularRecord;
import com.unind.qms.web.shipment.dao.ShipmentInspectDao;
import com.unind.qms.web.shipment.dao.ShipmentInspectRecordDao;
import com.unind.qms.web.shipment.entity.ShipmentInspect;
import com.unind.qms.web.shipment.entity.ShipmentInspectRecord;
import com.unind.qms.web.supplier.dao.PromoteDao;
import com.unind.qms.web.supplier.entity.Promote;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelService {

    @Autowired
    private FsFileDao fsFileDao;
    @Autowired
    private FtpClientService ftpClientService;
    @Autowired
    private AppConfig appConfig;
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

    /**
     * 导出初期审核报告
     * @param bsFlowRecordId
     * @param response
     * @return
     * @throws BusinessException
     */
    public ApiResponseResult writeInitialReview(Long bsFlowRecordId, HttpServletResponse response) throws BusinessException {
//        FsFile fsFile = fsFileDao.findOne(Long.valueOf((long)5302));
        FsFile fsFile = fsFileDao.findOne(Long.valueOf((long)5054));
        String path = appConfig.getString("fs.qms.path")+fsFile.getBsFilePath();
        ApiResponseResult result = ftpClientService.downloadFile(path, fsFile.getBsFileName()+fsFile.getBsFileType());

        InputStream inputStream = (InputStream) result.getData();
        try {
            HSSFWorkbook workbook = null;
            HSSFSheet sheet = null;
            HSSFRow row = null;
            HSSFCell cell = null;

            workbook = new HSSFWorkbook(inputStream);

            sheet = workbook.getSheetAt(2); //sheet3，审核报告

            List<HSSFCellStyle> cellStyleList = getCellStyle(workbook);

            int getScore = 0;
            int totalScore = 0;

            //获取项目记录数据
            List<ApprovedItemsRecord> approvedItemsRecordList = approvedItemsRecordDao.findByBsFlowRecordIdOrderByBsPriorityAsc(bsFlowRecordId);//5200

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
                String scoreRate = df.format((float)(100*Integer.parseInt(scoreNum[0])/Integer.parseInt(scoreNum[1])));
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
                List<ApprovedTermsScore> approvedTermsScoreList = approvedTermsScoreDao.findByBsItemsRecoreIdOrderByIdAsc(approvedItemsRecordList.get(i).getId());

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
            String totalScoreRate = df.format((float)(100*getScore/totalScore));
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

            List<Promote> promoteList = promoteDao.findByBsItemsRecordIdOrderByIdAsc(approvedItemsRecordList.get(approvedItemsRecordList.size()-1).getId());
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
            cell.setCellValue("序号");
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
                cell.setCellValue(i+1);
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
                cell.setCellValue(promoteList.get(i).getBsDeadline());
                cell.setCellStyle(cellStyleList.get(14));
            }

            OutputStream output = response.getOutputStream();
            response.reset();
            response.setContentType(fsFile.getBsContentType());
            String fileName = URLEncoder.encode("初期评估报告", "UTF-8")+ ".xls";
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

    public List<HSSFCellStyle> getCellStyle(HSSFWorkbook workbook) {
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
     * 导出出货检验报告
     * @param response
     * @return
     * @throws BusinessException
     */
    public ApiResponseResult writeShipmentInspect(Long bsShipmentId, HttpServletResponse response) throws BusinessException {
        FsFile fsFile = fsFileDao.findOne(Long.valueOf((long)5304));
        String path = appConfig.getString("fs.qms.path")+fsFile.getBsFilePath();
        ApiResponseResult result = ftpClientService.downloadFile(path, fsFile.getBsFileName()+fsFile.getBsFileType());

        InputStream inputStream = (InputStream) result.getData();
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
            cell.setCellValue(shipmentInspect.getBsProductDate());

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
            cell.setCellValue(shipmentInspect.getBsInspectDate());

            row = sheet.getRow(4);
            //图纸号
            cell = row.getCell(2);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(productInfo.getBsBlueprintNo());
            //批次号
            cell = row.getCell(7);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getBsBatchNo());
            //检查者
            cell = row.getCell(13);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue("检查者");

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
                        cell.setCellValue(sampleRegularRecordList.get(i).getSampleRegularBy().getBsUpLimit());
                        cell.setCellStyle(cellStyle);
                        //规格下限
                        cell = row.createCell(3);
                        cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                        cell.setCellValue(sampleRegularRecordList.get(i).getSampleRegularBy().getBsLowLimit());
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
                    cell.setCellValue(sampleRegularRecordList.get(i).getBsMeasureResult());
                    cell.setCellStyle(cellStyle);

                    nowColumn++;
                }
            }

            row = sheet.getRow(19+nowRow);
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
            //检验结果
            cell = row.getCell(5);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            int bsInspectResult = shipmentInspect.getBsInspectResult();
            cell.setCellValue(bsInspectResult==1?"Pass(可以接受)":bsInspectResult==2?"Fail(不可接受)":bsInspectResult==3?"reference(参考)":"");

            row = sheet.getRow(22+nowRow);
            //检验说明
            cell = row.getCell(0);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getBsInspectDesc());

            row = sheet.getRow(23+nowRow);
            //检验结果签名
            cell = row.getCell(11);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getModifiedBy().getBsName());
            //检验结果时间
            cell = row.getCell(14);
            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(shipmentInspect.getBsModifiedTime());

            //核查记录
            List<ShipmentInspectRecord> shipmentInspectRecordList = shipmentInspectRecordDao.findByBsShipmentIdOrderByIdAsc(shipmentInspect.getId());

            for(int n=0;n<3;n++){
                row = sheet.getRow(24+nowRow+n*3);
                //核查结果
                cell = row.getCell(0);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                bsInspectResult = shipmentInspectRecordList.get(n).getBsResult();
                String cellValue = cell.getStringCellValue();
                cell.setCellValue(cell.getStringCellValue() + "   " + (bsInspectResult==1?"Pass(可以接受)":bsInspectResult==2?"Fail(不可接受)":bsInspectResult==3?"reference(参考)":""));

                row = sheet.getRow(25+nowRow+n*3);
                //核查说明
                cell = row.getCell(0);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(shipmentInspectRecordList.get(n).getBsDesc());

                row = sheet.getRow(26+nowRow+n*3);
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
}
