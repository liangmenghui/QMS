package com.unind.qms.provider;

import com.unind.base.components.file.FtpClientService;
import com.unind.base.configure.AppConfig;
import com.unind.base.dao.admin.SysRoleDao;
import com.unind.base.dao.admin.agg.SysUserRolesAggDao;
import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.agg.SysUserRolesAgg;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.domain.admin.role.SysRole;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BusinessException;
import com.unind.fs.dao.file.FsFileDao;
import com.unind.fs.domain.file.FsFile;
import com.unind.qms.enumeration.BasicEnumConstants;
import com.unind.qms.enumeration.BasicStateEnum;
import com.unind.qms.web.basic.dao.*;
import com.unind.qms.web.basic.entity.*;
import com.unind.qms.web.product.dao.ProductInfoDao;
import com.unind.qms.web.product.entity.ProductInfo;
import com.unind.qms.web.supplier.dao.SupplierInfoDao;
import com.unind.qms.web.supplier.entity.SupplierInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTDataValidationsImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Component;
import org.springside.modules.persistence.SearchFilter;

import javax.naming.directory.SearchControls;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.Region;
import java.io.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

@Component
public class ExcelFeedbackService {
    public final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FeedbackInfoDao feedbackInfoDao;
    @Autowired
    private FeedbackHandlerDao feedbackHandlerDao;
    @Autowired
    private FeedbackRefundDao feedbackRefundDao;
    @Autowired
    private FeedbackInfoExtraDao feedbackInfoExtraDao;
    @Autowired
    private SupplierInfoDao supplierInfoDao;
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private SysUserRolesAggDao sysUserRolesAggDao;
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private ExcelTempDao excelTempDao;
    @Autowired
    private FsFileDao fsFileDao;
    @Autowired
    private FtpClientService ftpClientService;
    @Autowired
    private AppConfig appConfig;

    /**
     * 导出客诉报告
     * @param bsFeedbackId
     * @param response
     * @throws BusinessException
     */
    public ApiResponseResult exportFeedbackReport(Long bsFeedbackId, HttpServletResponse response) throws BusinessException{
        try {
            if(bsFeedbackId == null){
                return ApiResponseResult.failure("客诉ID为必填项！");
            }
//            String templatePath = "D:" + File.separator + "Report of feedback.xlsx";
//            String exportPath = "E:" + File.separator + "Feedback report.xlsx";
//            InputStream inputStream = new FileInputStream(templatePath);
//            OutputStream outputStream = new FileOutputStream(exportPath);

            //从ftp获取模板文件
            ExcelTemp excelTemp = excelTempDao.findFirstByBsType(BasicStateEnum.EXCELTEMP_FEEDBACL.intValue());
            FsFile fsFile = fsFileDao.findOne(excelTemp.getFsFileId());
            String path = appConfig.getString("fs.qms.path")+fsFile.getBsFilePath();
            ApiResponseResult result = ftpClientService.download(path, fsFile.getBsFileName());
            InputStream inputStream = new ByteArrayInputStream((byte[]) result.getData());
            OutputStream outputStream = response.getOutputStream();

            XSSFWorkbook workbook = null;   //创建一个工作簿
            workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            CreationHelper creationHelper = workbook.getCreationHelper();

            //获取该客诉相关产品和供应商，如果存在多个，则创建多个sheet
            List<FeedbackInfoExtra> feedbackInfoExtraList = feedbackInfoExtraDao.findByBsIsDelAndBsFeedbackIdOrderByIdDesc(BooleanStateEnum.FALSE.intValue(), bsFeedbackId);
            if(feedbackInfoExtraList.size() > 0){
                for (int number = 0; number < feedbackInfoExtraList.size(); number++){
                    //当前sheet的名称
                    String sheetName = "8D Worksheet-" + (number+1) ;
                    if(feedbackInfoExtraList.size() == 1){
                        sheetName = "8D Worksheet";
                    }
                    Sheet toSheet = workbook.createSheet(sheetName);  //创建另外一个sheet
                    int addRows = 0;  //增加的行数

                    //复制Excel模板 开始
                    int startRow = 0;
                    int endRow = 15;
                    int position = 0;
                    //获取sheet所有单元格样式
                    List<CellRangeAddress> oldRanges = new ArrayList<>();
                    sheet.getNumMergedRegions();
                    for(int i = 0; i < sheet.getNumMergedRegions(); i ++){
                        oldRanges.add(sheet.getMergedRegion(i));
                    }
                    //拷贝合并的单元格
                    for(CellRangeAddress oldRange : oldRanges){
                        CellRangeAddress newRange = new CellRangeAddress(oldRange.getFirstRow(), oldRange.getLastRow(),
                                oldRange.getFirstColumn(), oldRange.getLastColumn());
                        if(oldRange.getFirstRow() >= startRow && oldRange.getLastRow() <= endRow){
                            int targetRowFrom = oldRange.getFirstRow() - startRow + position;
                            int targetRowTo = oldRange.getLastRow() - startRow + position;
                            oldRange.setFirstRow(targetRowFrom);
                            oldRange.setLastRow(targetRowTo);
                            toSheet.addMergedRegion(oldRange);
                            //sheet.addMergedRegion(newRange);
                        }
                    }
                    //设置列宽
                    for(int i = startRow; i <= endRow; i++){
                        XSSFRow fromRow =  ((XSSFSheet) sheet).getRow(i);
                        if (fromRow != null) {
                            for (int j = fromRow.getLastCellNum(); j >= fromRow.getFirstCellNum(); j--) {
                                toSheet.setColumnWidth(j, sheet.getColumnWidth(j));
                                toSheet.setColumnHidden(j, false);
                            }
                            break;
                        }
                    }
                    //拷贝行并填充数据
                    for(int i = startRow; i <= endRow; i++){
                        XSSFRow fromRow = (XSSFRow) sheet.getRow(i);
                        if (fromRow == null) {
                            continue;
                        }
                        XSSFRow toRow = ((XSSFSheet) toSheet).createRow(i - startRow + position);
                        toRow.setHeight(fromRow.getHeight());
                        for(int j = fromRow.getFirstCellNum(); j <= fromRow.getPhysicalNumberOfCells(); j++){
                            XSSFCell fromCell = fromRow.getCell(j);
                            if(fromCell == null){
                                continue;
                            }
                            XSSFCell toCell = toRow.createCell(j);
                            toCell.setCellStyle(fromCell.getCellStyle());
                            int cType = fromCell.getCellType();
                            toCell.setCellType(cType);
                            switch (cType) {
                                case XSSFCell.CELL_TYPE_BOOLEAN:
                                    toCell.setCellValue(fromCell.getBooleanCellValue());
                                    break;
                                case XSSFCell.CELL_TYPE_ERROR:
                                    toCell.setCellErrorValue(fromCell.getErrorCellValue());
                                    break;
                                case XSSFCell.CELL_TYPE_FORMULA:
                                    toCell.setCellFormula(parseFormula(fromCell.getCellFormula()));
                                    break;
                                case XSSFCell.CELL_TYPE_NUMERIC:
                                    toCell.setCellValue(fromCell.getNumericCellValue());
                                    break;
                                case XSSFCell.CELL_TYPE_STRING:
                                    toCell.setCellValue(fromCell.getRichStringCellValue());
                                    break;
                            }
                        }
                    }
                    //获取图片
                    Map<String, PictureData> sheetIndexPicMap;
                    sheetIndexPicMap = getSheetPictrues07(0, (XSSFSheet) sheet, workbook);
                    if(sheetIndexPicMap.size() > 0){
                        XSSFDrawing drawing = (XSSFDrawing) toSheet.createDrawingPatriarch();
                        XSSFClientAnchor author = new XSSFClientAnchor(0, 0, 0, 0, 0, 0, 2, 2);
                        Picture picture = drawing.createPicture(author, workbook.addPicture(sheetIndexPicMap.get("0_0_0").getData(), XSSFWorkbook.PICTURE_TYPE_PNG));
                        picture.resize(0.69);  //缩放（比例）
                    }
                    //复制Excel模板 end

                    //填写基本信息
                    //1.客诉基本信息
                    FeedbackInfo feedbackInfo = feedbackInfoDao.findOne(bsFeedbackId);
                    FeedbackInfoExtra extra = feedbackInfoExtraList.get(number);
                    if(feedbackInfo != null) {
                        Long suppId = extra.getBsSuppId();  //供应商ID
                        String suppName = extra.getBsSuppCompanyName();   //供应商名称
                        String suppAddress = "";                               //供应商地址
                        String suppContact = extra.getBsSuppCompanyPerson();  //供应商联系人名称
                        String suppPhone = extra.getBsSuppCompanyMobile();  //供应商联系人电话
                        String suppEmail = extra.getBsSuppCompanyEmail();  //供应商联系人邮箱
                        SupplierInfo supplierInfo = supplierInfoDao.findOne(suppId);
                        if (supplierInfo != null) {
                            suppAddress = supplierInfo.getBsSuppAddress();
                        }
                        String title = feedbackInfo.getBsTitle();   //标题
                        String prName = extra.getBsPrName();  //产品名称
                        String prNum = extra.getBsPrNum();   //产品数量
                        String reportLocation = extra.getBsReportLocation();  //投诉地点
                        String problemDes = feedbackInfo.getBsProblemDes();  //问题描述
                        String productLocation = extra.getBsProductLocation();  //生产地点
                        String productDateStr = extra.getBsProductDate();  //生产日期
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

                        toSheet.getRow(6).getCell(2).setCellValue(suppName + "\n" + suppAddress);
                        toSheet.getRow(7).getCell(2).setCellValue(suppContact);
                        toSheet.getRow(7).getCell(7).setCellValue(suppPhone);
                        //邮箱加超链接（符合邮箱格式后添加链接，不符合不添加链接）
                        if(suppEmail != null && suppEmail.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")){
                            suppEmail = suppEmail.trim();
                            toSheet.getRow(8).getCell(2).setCellValue(suppEmail);
                            XSSFHyperlink hyperlink = (XSSFHyperlink) creationHelper.createHyperlink(XSSFHyperlink.LINK_EMAIL);
                            hyperlink.setAddress("mailto:" + suppEmail);
                            toSheet.getRow(8).getCell(2).setHyperlink(hyperlink);
                        }
                        toSheet.getRow(11).getCell(0).setCellValue(title);
                        toSheet.getRow(11).getCell(5).setCellValue(prName);
                        toSheet.getRow(11).getCell(8).setCellValue(prNum + " pcs " + reportLocation);
                        toSheet.getRow(13).getCell(0).setCellValue(problemDes);
                        toSheet.getRow(13).getCell(5).setCellValue(productLocation);
                        toSheet.getRow(13).getCell(8).setCellValue(productDateStr);

                        //2.客诉处理人员相关信息
                        //2.1处理人员 开始
                        List<FeedbackHandler> feedbackHandlerList = feedbackHandlerDao.findByBsIsDelAndBsFeedbackIdAndBsType(BooleanStateEnum.FALSE.intValue(), bsFeedbackId, BasicEnumConstants.FEEDBACK_HANDLER1);
                        if(feedbackHandlerList.size() >= 0){  //此判断仅区分不同模块
                            //初始行数为13行（人数少于10时创建13行，多于10时增加行数）
                            if(feedbackHandlerList.size() <= 10){
                                //创建行
                                for(int i = 0 + 16; i <= 13 + 16; i++){
                                    Row createRow = toSheet.createRow(i);
                                    for(int j = 0;j < 10; j++){
                                        createRow.createCell(j);
                                    }
                                }
                                //设置行高
                                toSheet.getRow(28).setHeightInPoints((float) 21.8);

                                //添加样式与数据
                                //第1部分
                                Cell cell1 = toSheet.getRow(16).getCell(0);
                                cell1.setCellType(sheet.getRow(16).getCell(0).getCellType());
                                cell1.setCellValue(sheet.getRow(16).getCell(0).getNumericCellValue());
                                cell1.setCellStyle(sheet.getRow(16).getCell(0).getCellStyle());

                                Cell cell2 = toSheet.getRow(16).getCell(1);
                                cell2.setCellType(sheet.getRow(16).getCell(1).getCellType());
                                cell2.setCellValue(sheet.getRow(16).getCell(1).getStringCellValue());
                                cell2.setCellStyle(sheet.getRow(16).getCell(1).getCellStyle());

                                Cell cell3 = toSheet.getRow(17).getCell(1);
                                cell3.setCellType(sheet.getRow(17).getCell(1).getCellType());
                                cell3.setCellValue(sheet.getRow(17).getCell(1).getStringCellValue());
                                cell3.setCellStyle(sheet.getRow(17).getCell(1).getCellStyle());

                                Cell cell4 = toSheet.getRow(17).getCell(3);
                                cell4.setCellType(sheet.getRow(17).getCell(3).getCellType());
                                cell4.setCellValue(sheet.getRow(17).getCell(3).getStringCellValue());
                                cell4.setCellStyle(sheet.getRow(17).getCell(3).getCellStyle());

                                Cell cell5 = toSheet.getRow(17).getCell(4);
                                cell5.setCellType(sheet.getRow(17).getCell(4).getCellType());
                                cell5.setCellValue(sheet.getRow(17).getCell(4).getStringCellValue());
                                cell5.setCellStyle(sheet.getRow(17).getCell(4).getCellStyle());

                                //处理人员
                                for(int i = 0;i < 10; i++){
                                    toSheet.getRow(18 + i).getCell(1).setCellType(sheet.getRow(18).getCell(1).getCellType());
                                    toSheet.getRow(18 + i).getCell(1).setCellStyle(sheet.getRow(18).getCell(1).getCellStyle());
                                }

                                for(int i = 0;i < 10; i++){
                                    toSheet.getRow(18 + i).getCell(3).setCellType(sheet.getRow(18).getCell(3).getCellType());
                                    toSheet.getRow(18 + i).getCell(3).setCellStyle(sheet.getRow(18).getCell(3).getCellStyle());
                                }

                                for(int i = 0;i < 10; i++){
                                    toSheet.getRow(18 + i).getCell(4).setCellType(sheet.getRow(18).getCell(4).getCellType());
                                    toSheet.getRow(18 + i).getCell(4).setCellStyle(sheet.getRow(18).getCell(4).getCellStyle());
                                }

                                for (int i = 0; i < feedbackHandlerList.size(); i++){
                                    //当处理人ID存在时，取ID对应的用户为处理人；否则获取当前处理人信息。
                                    String personName = feedbackHandlerList.get(i).getBsHandlerName();  //处理人员名称
                                    String personEmail = feedbackHandlerList.get(i).getBsHandlerEmail();  //处理人员邮箱
                                    if(feedbackHandlerList.get(i).getBsHandlerId() != null && feedbackHandlerList.get(i).getHandlerBy() != null){
                                        personName = feedbackHandlerList.get(i).getHandlerBy().getBsName();
                                        personEmail = feedbackHandlerList.get(i).getHandlerBy().getBsEmail();
                                    }

                                    toSheet.getRow(18 + i).getCell(1).setCellValue(personName);
                                    //邮箱加超链接（符合邮箱格式后添加链接，不符合不添加链接）
                                    if(personEmail != null && personEmail.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")) {
                                        personEmail = personEmail.trim();
                                        toSheet.getRow(18 + i).getCell(4).setCellValue(personEmail);
                                        XSSFHyperlink hyperlink2 = (XSSFHyperlink) creationHelper.createHyperlink(XSSFHyperlink.LINK_EMAIL);
                                        hyperlink2.setAddress("mailto:" + personEmail);
                                        toSheet.getRow(18 + i).getCell(4).setHyperlink(hyperlink2);
                                    }
                                }

                                //29行
                                //后面cell9，cell13需要合并单元格，边框样式都要设置
                                Cell cell9 = toSheet.getRow(28).getCell(1);
                                CellStyle cellStyle9 = sheet.getRow(28).getCell(1).getCellStyle(); //获取样式
                                cellStyle9.setBorderTop(CellStyle.BORDER_THIN);  //上边框
                                cellStyle9.setBorderRight(CellStyle.BORDER_THIN);  //右边框
                                cellStyle9.setBorderBottom(CellStyle.BORDER_THIN);  //下边框
                                cellStyle9.setBorderLeft(CellStyle.BORDER_THIN);  //左边框
                                cell9.setCellType(sheet.getRow(28).getCell(1).getCellType());
                                cell9.setCellValue(sheet.getRow(28).getCell(1).getStringCellValue());
                                cell9.setCellStyle(cellStyle9);

                                Cell cell13 = toSheet.getRow(28).getCell(4);
                                CellStyle cellStyle13 = sheet.getRow(28).getCell(1).getCellStyle(); //获取样式
                                cellStyle13.setBorderRight(CellStyle.BORDER_THIN);  //右边框
                                cell13.setCellType(sheet.getRow(28).getCell(1).getCellType());
                                cell13.setCellValue(sheet.getRow(28).getCell(1).getStringCellValue());
                                cell13.setCellStyle(cellStyle13);

                                //16行
                                Cell cell10 = toSheet.getRow(16).getCell(5);
                                cell10.setCellType(sheet.getRow(16).getCell(5).getCellType());
                                cell10.setCellValue(sheet.getRow(16).getCell(5).getNumericCellValue());
                                cell10.setCellStyle(sheet.getRow(16).getCell(5).getCellStyle());

                                //后面cell11，cell14需要合并单元格，边框样式都要设置
                                Cell cell11 = toSheet.getRow(16).getCell(6);
                                CellStyle cellStyle11 = sheet.getRow(16).getCell(6).getCellStyle(); //获取样式
                                cellStyle11.setBorderTop(CellStyle.BORDER_MEDIUM);  //上边框
                                cellStyle11.setBorderLeft(CellStyle.BORDER_THIN);  //左边框
                                cell11.setCellType(sheet.getRow(16).getCell(6).getCellType());
                                cell11.setCellValue(sheet.getRow(16).getCell(6).getStringCellValue());
                                cell11.setCellStyle(cellStyle11);

                                Cell cell14 = toSheet.getRow(16).getCell(9);
                                CellStyle cellStyle14 = sheet.getRow(16).getCell(6).getCellStyle(); //获取样式
                                cellStyle14.setBorderRight(CellStyle.BORDER_MEDIUM); //右边框
                                cell14.setCellType(sheet.getRow(16).getCell(6).getCellType());
                                cell14.setCellValue(sheet.getRow(16).getCell(6).getStringCellValue());
                                cell14.setCellStyle(cellStyle14);

                                //循环设置每个单元格的边框(处于边框位置的单元格需要设置边框)，后面需要合并这些单元格
                                CellStyle cellStyle15 = sheet.getRow(17).getCell(6).getCellStyle();  //获取样式
                                cellStyle15.setBorderLeft(CellStyle.BORDER_THIN);  //左边框
                                for(int x = 0; x < 12; x++){
                                    Cell cell12 = toSheet.getRow(17 + x).getCell(6);
                                    cell12.setCellType(sheet.getRow(17).getCell(6).getCellType());
                                    cell12.setCellStyle(cellStyle15);
                                }
                                CellStyle cellStyle16 = sheet.getRow(17).getCell(6).getCellStyle();  //获取样式
                                cellStyle16.setBorderRight(CellStyle.BORDER_MEDIUM);  //右边框
                                for(int x = 0; x < 12; x++){
                                    Cell cell15 = toSheet.getRow(17 + x).getCell(9);
                                    cell15.setCellType(sheet.getRow(17).getCell(6).getCellType());
                                    cell15.setCellStyle(cellStyle16);
                                }

                                //2.2原因分析
                                List<FeedbackHandler> feedbackHandlerList2 = feedbackHandlerDao.findByBsIsDelAndBsFeedbackIdAndBsType(BooleanStateEnum.FALSE.intValue(), bsFeedbackId, BasicEnumConstants.FEEDBACK_HANDLER2);
                                if(feedbackHandlerList2.size() >= 0){
                                    String totalReason = "";
                                    for(int i = 0; i < feedbackHandlerList2.size(); i++){
                                        String personName = feedbackHandlerList2.get(i).getBsHandlerName();   //处理人员名称
                                        if(feedbackHandlerList2.get(i).getBsHandlerId() != null && feedbackHandlerList2.get(i).getHandlerBy() != null){
                                            personName = feedbackHandlerList2.get(i).getHandlerBy().getBsName();
                                        }
                                        String personDate = simpleDateFormat.format(feedbackHandlerList2.get(i).getBsCreatedTime());  //处理时间
                                        String personDesc = feedbackHandlerList2.get(i).getBsDesc();   //描述信息
                                        totalReason = totalReason + (personDate + " " + personName + " 反馈：" + personDesc + "\n");
                                    }
                                    toSheet.getRow(17).getCell(6).setCellValue(totalReason);
                                }

                                //合并单元格（注意顺序,从后往前合并，这样保证下标不乱），并设置合并单元格的边框
                                //region1
                                CellRangeAddress region1 = new CellRangeAddress(17,28,6,9);
                                toSheet.addMergedRegion(region1);
                                RegionUtil.setBorderRight(CellStyle.BORDER_MEDIUM, region1, toSheet, workbook);
                                RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, region1, toSheet, workbook);
                                RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, region1, toSheet, workbook);
                                //region2
                                CellRangeAddress region2 = new CellRangeAddress(16,16,6,9);
                                toSheet.addMergedRegion(region2);
                                RegionUtil.setBorderTop(CellStyle.BORDER_MEDIUM, region2, toSheet, workbook);
                                RegionUtil.setBorderRight(CellStyle.BORDER_MEDIUM, region2, toSheet, workbook);
                                RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, region2, toSheet, workbook);
                                //region3
                                CellRangeAddress region3 = new CellRangeAddress(16,28,5,5);
                                toSheet.addMergedRegion(region3);
                                RegionUtil.setBorderTop(CellStyle.BORDER_MEDIUM, region3, toSheet, workbook);
                                RegionUtil.setBorderRight(CellStyle.BORDER_THIN, region3, toSheet, workbook);
                                RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, region3, toSheet, workbook);
                                RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, region3, toSheet, workbook);
                                //region4
                                CellRangeAddress region4 = new CellRangeAddress(28, 28, 1, 4);
                                toSheet.addMergedRegion(region4);
                                RegionUtil.setBorderTop(CellStyle.BORDER_THIN, region4, toSheet, workbook);
                                RegionUtil.setBorderRight(CellStyle.BORDER_THIN, region4, toSheet, workbook);
                                RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, region4, toSheet, workbook);
                                RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, region4, toSheet, workbook);
                                //region5
                                for(int k = 10; k >= 0; k--){
                                    CellRangeAddress region5 = new CellRangeAddress(17 + k, 17 + k, 1,2);
                                    toSheet.addMergedRegion(region5);
                                    RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region5, toSheet, workbook);
                                    RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region5, toSheet, workbook);
                                }
                                //region6
                                CellRangeAddress region6 = new CellRangeAddress(16 , 16, 1, 4);
                                toSheet.addMergedRegion(region6);
                                //region7
                                CellRangeAddress region7 = new CellRangeAddress(16 , 28, 0, 0);
                                toSheet.addMergedRegion(region7);
                            }else{
                                //创建行
                                int addRowsPerson = feedbackHandlerList.size() - 10;   //本模块增加行数

                                for(int i = 0 + 16; i <= 13 + 16 + addRowsPerson; i++){
                                    Row createRow = toSheet.createRow(i);
                                    for(int j = 0;j < 10; j++){
                                        createRow.createCell(j);
                                    }
                                }
                                //行高
                                toSheet.getRow(28 + addRowsPerson).setHeightInPoints((float) 21.8);

                                //添加样式与数据
                                //第1部分
                                Cell cell1 = toSheet.getRow(16).getCell(0);
                                cell1.setCellType(sheet.getRow(16).getCell(0).getCellType());
                                cell1.setCellValue(sheet.getRow(16).getCell(0).getNumericCellValue());
                                cell1.setCellStyle(sheet.getRow(16).getCell(0).getCellStyle());

                                Cell cell2 = toSheet.getRow(16).getCell(1);
                                cell2.setCellType(sheet.getRow(16).getCell(1).getCellType());
                                cell2.setCellValue(sheet.getRow(16).getCell(1).getStringCellValue());
                                cell2.setCellStyle(sheet.getRow(16).getCell(1).getCellStyle());

                                Cell cell3 = toSheet.getRow(17).getCell(1);
                                cell3.setCellType(sheet.getRow(17).getCell(1).getCellType());
                                cell3.setCellValue(sheet.getRow(17).getCell(1).getStringCellValue());
                                cell3.setCellStyle(sheet.getRow(17).getCell(1).getCellStyle());

                                Cell cell4 = toSheet.getRow(17).getCell(3);
                                cell4.setCellType(sheet.getRow(17).getCell(3).getCellType());
                                cell4.setCellValue(sheet.getRow(17).getCell(3).getStringCellValue());
                                cell4.setCellStyle(sheet.getRow(17).getCell(3).getCellStyle());

                                Cell cell5 = toSheet.getRow(17).getCell(4);
                                cell5.setCellType(sheet.getRow(17).getCell(4).getCellType());
                                cell5.setCellValue(sheet.getRow(17).getCell(4).getStringCellValue());
                                cell5.setCellStyle(sheet.getRow(17).getCell(4).getCellStyle());

                                //处理人员
                                for(int i = 0;i < feedbackHandlerList.size(); i++){
                                    toSheet.getRow(18 + i).getCell(1).setCellType(sheet.getRow(18).getCell(1).getCellType());
                                    toSheet.getRow(18 + i).getCell(1).setCellStyle(sheet.getRow(18).getCell(1).getCellStyle());
                                }

                                for(int i = 0;i < feedbackHandlerList.size(); i++){
                                    toSheet.getRow(18 + i).getCell(3).setCellType(sheet.getRow(18).getCell(3).getCellType());
                                    toSheet.getRow(18 + i).getCell(3).setCellStyle(sheet.getRow(18).getCell(3).getCellStyle());
                                }

                                for(int i = 0;i < feedbackHandlerList.size(); i++){
                                    toSheet.getRow(18 + i).getCell(4).setCellType(sheet.getRow(18).getCell(4).getCellType());
                                    toSheet.getRow(18 + i).getCell(4).setCellStyle(sheet.getRow(18).getCell(4).getCellStyle());
                                }

                                for (int i = 0; i < feedbackHandlerList.size(); i++){
                                    String personName = feedbackHandlerList.get(i).getBsHandlerName();  //处理人员名称
                                    String personEmail = feedbackHandlerList.get(i).getBsHandlerEmail();  //处理人员邮箱
                                    if(feedbackHandlerList.get(i).getBsHandlerId() != null && feedbackHandlerList.get(i).getHandlerBy() != null){
                                        personName = feedbackHandlerList.get(i).getHandlerBy().getBsName();
                                        personEmail = feedbackHandlerList.get(i).getHandlerBy().getBsEmail();
                                    }

                                    toSheet.getRow(18 + i).getCell(1).setCellValue(personName);
                                    //邮箱加超链接（符合邮箱格式后添加链接，不符合不添加链接）
                                    if(personEmail != null && personEmail.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")) {
                                        personEmail = personEmail.trim();
                                        toSheet.getRow(18 + i).getCell(4).setCellValue(personEmail);
                                        XSSFHyperlink hyperlink2 = (XSSFHyperlink) creationHelper.createHyperlink(XSSFHyperlink.LINK_EMAIL);
                                        hyperlink2.setAddress("mailto:" + personEmail);
                                        toSheet.getRow(18 + i).getCell(4).setHyperlink(hyperlink2);
                                    }
                                }

                                //29行
                                //后面cell9，cell13需要合并单元格，边框样式都要设置
                                Cell cell9 = toSheet.getRow(28 + addRowsPerson).getCell(1);
                                CellStyle cellStyle9 = sheet.getRow(28).getCell(1).getCellStyle(); //获取样式
                                cellStyle9.setBorderTop(CellStyle.BORDER_THIN);  //上边框
                                cellStyle9.setBorderRight(CellStyle.BORDER_THIN);  //右边框
                                cellStyle9.setBorderBottom(CellStyle.BORDER_THIN);  //下边框
                                cellStyle9.setBorderLeft(CellStyle.BORDER_THIN);  //左边框
                                cell9.setCellType(sheet.getRow(28).getCell(1).getCellType());
                                cell9.setCellValue(sheet.getRow(28).getCell(1).getStringCellValue());
                                cell9.setCellStyle(cellStyle9);

                                Cell cell13 = toSheet.getRow(28 + addRowsPerson).getCell(4);
                                CellStyle cellStyle13 = sheet.getRow(28).getCell(1).getCellStyle(); //获取样式
                                cellStyle13.setBorderRight(CellStyle.BORDER_THIN);  //右边框
                                cell13.setCellType(sheet.getRow(28).getCell(1).getCellType());
                                cell13.setCellValue(sheet.getRow(28).getCell(1).getStringCellValue());
                                cell13.setCellStyle(cellStyle13);

                                //16行
                                Cell cell10 = toSheet.getRow(16).getCell(5);
                                cell10.setCellType(sheet.getRow(16).getCell(5).getCellType());
                                cell10.setCellValue(sheet.getRow(16).getCell(5).getNumericCellValue());
                                cell10.setCellStyle(sheet.getRow(16).getCell(5).getCellStyle());

                                //后面cell11，cell14需要合并单元格，边框样式都要设置
                                Cell cell11 = toSheet.getRow(16).getCell(6);
                                cell11.setCellType(sheet.getRow(16).getCell(6).getCellType());
                                cell11.setCellValue(sheet.getRow(16).getCell(6).getStringCellValue());
                                cell11.setCellStyle(sheet.getRow(16).getCell(6).getCellStyle());
                                cell9.getCellStyle().setBorderRight((short)1);

                                Cell cell14 = toSheet.getRow(16).getCell(9);
                                CellStyle cellStyle14 = sheet.getRow(16).getCell(6).getCellStyle(); //获取样式
                                cellStyle14.setBorderRight(CellStyle.BORDER_MEDIUM); //右边框
                                cell14.setCellType(sheet.getRow(16).getCell(6).getCellType());
                                cell14.setCellValue(sheet.getRow(16).getCell(6).getStringCellValue());
                                cell14.setCellStyle(cellStyle14);

                                //循环设置每个单元格的边框(处于边框位置的单元格需要设置边框)，后面需要合并这些单元格
                                CellStyle cellStyle15 = sheet.getRow(17).getCell(6).getCellStyle();  //获取样式
                                cellStyle15.setBorderLeft(CellStyle.BORDER_THIN);  //左边框
                                for(int x = 0; x < 12 + addRowsPerson; x++){
                                    Cell cell12 = toSheet.getRow(17 + x).getCell(6);
                                    cell12.setCellType(sheet.getRow(17).getCell(6).getCellType());
                                    cell12.setCellStyle(cellStyle15);
                                }
                                CellStyle cellStyle16 = sheet.getRow(17).getCell(6).getCellStyle();  //获取样式
                                cellStyle16.setBorderRight(CellStyle.BORDER_MEDIUM);  //右边框
                                for(int x = 0; x < 12 + addRowsPerson; x++){
                                    Cell cell15 = toSheet.getRow(17 + x).getCell(9);
                                    cell15.setCellType(sheet.getRow(17).getCell(6).getCellType());
                                    cell15.setCellStyle(cellStyle16);
                                }

                                //2.2原因分析
                                List<FeedbackHandler> feedbackHandlerList2 = feedbackHandlerDao.findByBsIsDelAndBsFeedbackIdAndBsType(BooleanStateEnum.FALSE.intValue(), bsFeedbackId, BasicEnumConstants.FEEDBACK_HANDLER2);
                                if(feedbackHandlerList2.size() >= 0){
                                    String totalReason = "";
                                    for(int i = 0; i < feedbackHandlerList2.size(); i++){
                                        String personName = feedbackHandlerList2.get(i).getBsHandlerName();   //处理人员名称
                                        if(feedbackHandlerList2.get(i).getBsHandlerId() != null && feedbackHandlerList.get(i).getHandlerBy() != null){
                                            personName = feedbackHandlerList.get(i).getHandlerBy().getBsName();
                                        }
                                        String personDate = simpleDateFormat.format(feedbackHandlerList2.get(i).getBsCreatedTime());  //处理时间
                                        String personDesc = feedbackHandlerList2.get(i).getBsDesc();   //描述信息
                                        totalReason = totalReason + (personDate + " " + personName + " 反馈：" + personDesc + "\n");
                                    }
                                    toSheet.getRow(17).getCell(6).setCellValue(totalReason);
                                }
                                //合并单元格（注意顺序,从后往前合并，这样保证下标不乱），并设置合并单元格的边框
                                //region1
                                CellRangeAddress region1 = new CellRangeAddress(17,28 + addRowsPerson,6,9);
                                toSheet.addMergedRegion(region1);
                                RegionUtil.setBorderRight(CellStyle.BORDER_MEDIUM, region1, toSheet, workbook);
                                RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, region1, toSheet, workbook);
                                RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, region1, toSheet, workbook);
                                //region2
                                CellRangeAddress region2 = new CellRangeAddress(16,16,6,9);
                                toSheet.addMergedRegion(region2);
                                RegionUtil.setBorderTop(CellStyle.BORDER_MEDIUM, region2, toSheet, workbook);
                                RegionUtil.setBorderRight(CellStyle.BORDER_MEDIUM, region2, toSheet, workbook);
                                RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, region2, toSheet, workbook);
                                //region3
                                CellRangeAddress region3 = new CellRangeAddress(16,28 + addRowsPerson,5,5);
                                toSheet.addMergedRegion(region3);
                                RegionUtil.setBorderTop(CellStyle.BORDER_MEDIUM, region3, toSheet, workbook);
                                RegionUtil.setBorderRight(CellStyle.BORDER_THIN, region3, toSheet, workbook);
                                RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, region3, toSheet, workbook);
                                RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, region3, toSheet, workbook);
                                //region4
                                CellRangeAddress region4 = new CellRangeAddress(28 + addRowsPerson, 28 + addRowsPerson, 1, 4);
                                toSheet.addMergedRegion(region4);
                                RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region4, toSheet, workbook);
                                RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region4, toSheet, workbook);
                                RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region4, toSheet, workbook);
                                RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, region4, toSheet, workbook);
                                //region5
                                for(int k = feedbackHandlerList.size(); k >= 0; k--){
                                    CellRangeAddress region5 = new CellRangeAddress(17 + k, 17 + k, 1,2);
                                    toSheet.addMergedRegion(region5);
                                    RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region5, toSheet, workbook);
                                    RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region5, toSheet, workbook);
                                }
                                //region6
                                CellRangeAddress region6 = new CellRangeAddress(16 , 16, 1, 4);
                                toSheet.addMergedRegion(region6);
                                //region7
                                CellRangeAddress region7 = new CellRangeAddress(16 , 28 + addRowsPerson, 0, 0);
                                toSheet.addMergedRegion(region7);

                                addRows = addRows + addRowsPerson;                     //总增加行数
                            }
                        }
                        //2.1处理人员 结束

                        //2.3临时措施 开始
                        List<FeedbackHandler> feedbackHandlerList3 = feedbackHandlerDao.findByBsIsDelAndBsFeedbackIdAndBsType(BooleanStateEnum.FALSE.intValue(), bsFeedbackId, BasicEnumConstants.FEEDBACK_HANDLER3);
                        if(feedbackHandlerList3.size() >= 0){
                            //创建行
                            int addRowsPerson = feedbackHandlerList3.size() <= 0 ? 0 : feedbackHandlerList3.size() - 1;   //本模块增加行数，最小为0

                            for(int i = 29 + addRows; i <= 30 + addRows + addRowsPerson; i++){
                                Row createRow = toSheet.createRow(i);
                                for(int j = 0;j < 10; j++){
                                    createRow.createCell(j);
                                }
                            }
                            //设置行高
                            for(int i = 0; i < addRowsPerson + 1; i++){
                                toSheet.getRow(30 + addRows + i).setHeightInPoints(70);
                            }

                            //添加样式与数据
                            //第3部分
                            Cell cell1 = toSheet.getRow(29 + addRows).getCell(0);
                            cell1.setCellType(sheet.getRow(29).getCell(0).getCellType());
                            cell1.setCellValue(sheet.getRow(29).getCell(0).getNumericCellValue());
                            cell1.setCellStyle(sheet.getRow(29).getCell(0).getCellStyle());

                            Cell cell2 = toSheet.getRow(29 + addRows).getCell(1);
                            cell2.setCellType(sheet.getRow(29).getCell(1).getCellType());
                            cell2.setCellValue(sheet.getRow(29).getCell(1).getStringCellValue());
                            cell2.setCellStyle(sheet.getRow(29).getCell(1).getCellStyle());

                            Cell cell3 = toSheet.getRow(29 + addRows).getCell(8);
                            cell3.setCellType(sheet.getRow(29).getCell(8).getCellType());
                            cell3.setCellValue(sheet.getRow(29).getCell(8).getStringCellValue());
                            cell3.setCellStyle(sheet.getRow(29).getCell(8).getCellStyle());

                            Cell cell4 = toSheet.getRow(29 + addRows).getCell(9);
                            cell4.setCellType(sheet.getRow(29).getCell(9).getCellType());
                            cell4.setCellValue(sheet.getRow(29).getCell(9).getStringCellValue());
                            cell4.setCellStyle(sheet.getRow(29).getCell(9).getCellStyle());

                            //临时措施内容
                            for(int i = 0; i < addRowsPerson + 1; i++){
                                toSheet.getRow(30 + addRows + i).getCell(1).setCellType(sheet.getRow(30).getCell(1).getCellType());
                                toSheet.getRow(30 + addRows + i).getCell(1).setCellStyle(sheet.getRow(30).getCell(1).getCellStyle());
                            }

                            CellStyle cellStyle6 = sheet.getRow(30).getCell(8).getCellStyle();  //获取样式
                            cellStyle6.setBorderLeft(CellStyle.BORDER_THIN);   //左边框
                            for(int i = 0; i < addRowsPerson + 1; i++){
                                toSheet.getRow(30 + addRows + i).getCell(8).setCellType(sheet.getRow(30).getCell(8).getCellType());
                                toSheet.getRow(30 + addRows + i).getCell(8).setCellStyle(cellStyle6);
                            }

                            for(int i = 0; i < addRowsPerson + 1; i++){
                                toSheet.getRow(30 + addRows + i).getCell(9).setCellType(sheet.getRow(30).getCell(9).getCellType());
                                toSheet.getRow(30 + addRows + i).getCell(9).setCellStyle(sheet.getRow(30).getCell(9).getCellStyle());
                            }

                            for(int i = 0; i < feedbackHandlerList3.size(); i++){
                                String personName = feedbackHandlerList3.get(i).getBsHandlerName();   //处理人员名称
                                if(feedbackHandlerList3.get(i).getBsHandlerId() != null && feedbackHandlerList3.get(i).getHandlerBy() != null){
                                    personName = feedbackHandlerList3.get(i).getHandlerBy().getBsName();
                                }
                                String personDate = simpleDateFormat.format(feedbackHandlerList3.get(i).getBsCreatedTime());   //处理时间
                                String personDesc = feedbackHandlerList3.get(i).getBsDesc();   //描述信息

                                toSheet.getRow(30 + addRows + i).getCell(1).setCellValue(personDesc);
                                toSheet.getRow(30 + addRows + i).getCell(8).setCellValue(personName);
                                toSheet.getRow(30 + addRows + i).getCell(9).setCellValue(personDate);
                            }

                            //合并单元格（注意顺序,从后往前合并，这样保证下标不乱），并设置合并单元格的边框
                            //region1
                            for(int k = addRowsPerson; k >= 0; k--){
                                CellRangeAddress region1 = new CellRangeAddress(30 + addRows + k, 30 + addRows + k, 1, 7);
                                toSheet.addMergedRegion(region1);
                                RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region1, toSheet, workbook);
                                RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region1, toSheet, workbook);
                                RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region1, toSheet, workbook);
                            }
                            //region2
                            CellRangeAddress region2 = new CellRangeAddress(29 + addRows, 29 + addRows, 1, 7);
                            toSheet.addMergedRegion(region2);
                            RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region2, toSheet, workbook);
                            //region3
                            CellRangeAddress region3 = new CellRangeAddress(29 + addRows, 30 + addRows + addRowsPerson, 0, 0);
                            toSheet.addMergedRegion(region3);

                            addRows = addRows + addRowsPerson;                     //总增加行数
                        }
                        //2.3临时措施 结束

                        //2.4根本原因分析 开始
                        List<FeedbackHandler> feedbackHandlerList4 = feedbackHandlerDao.findByBsIsDelAndBsFeedbackIdAndBsType(BooleanStateEnum.FALSE.intValue(), bsFeedbackId, BasicEnumConstants.FEEDBACK_HANDLER4);
                        if(feedbackHandlerList4.size() >= 0){
                            //创建行
                            //三类（1: Prevent 2: Protect 3: Predict）
                            List<FeedbackHandler> preventList = new ArrayList<FeedbackHandler>();
                            List<FeedbackHandler> protectList = new ArrayList<FeedbackHandler>();
                            List<FeedbackHandler> predictList = new ArrayList<FeedbackHandler>();
                            for(int i = 0; i < feedbackHandlerList4.size(); i++){
                                if(feedbackHandlerList4.get(i).getBsActionType() == 1){
                                    preventList.add(feedbackHandlerList4.get(i));
                                }
                                if(feedbackHandlerList4.get(i).getBsActionType() == 2){
                                    protectList.add(feedbackHandlerList4.get(i));
                                }
                                if(feedbackHandlerList4.get(i).getBsActionType() == 3){
                                    predictList.add(feedbackHandlerList4.get(i));
                                }
                            }
                            int addRowsPrevent = preventList.size() <= 0 ? 0 : preventList.size() - 1;   //预防类型增加的行数
                            int addRowsProtect = protectList.size() <= 0 ? 0 : protectList.size() - 1;   //保护类型增加的行数
                            int addRowsPredict = predictList.size() <= 0 ? 0 : predictList.size() - 1;   //预测类型增加的行数
                            int addRowsPerson = addRowsPrevent +  addRowsProtect + addRowsPredict;   //本模块增加的行数，最小为0

                            for(int i = 31 + addRows; i <= 38 + addRows + addRowsPerson; i++){
                                Row createRow = toSheet.createRow(i);
                                for(int j = 0;j < 10; j++){
                                    createRow.createCell(j);
                                }
                            }
                            //设置行高
                            toSheet.getRow(32 + addRows).setHeightInPoints(6);
                            for(int i = 0; i < addRowsPrevent + 1; i++){
                                toSheet.getRow(34 + addRows + i).setHeightInPoints(40);
                            }
                            for(int i = 0; i < addRowsProtect + 1; i ++){
                                toSheet.getRow(36 + addRows + addRowsPrevent + i).setHeightInPoints(40);
                            }
                            for(int i = 0; i < addRowsPredict + 1; i++){
                                toSheet.getRow(38 + addRows + addRowsPrevent + addRowsProtect + i).setHeightInPoints(40);
                            }

                            //添加样式与数据
                            //第4部分
                            Cell cell1 = toSheet.getRow(31 + addRows).getCell(0);
                            cell1.setCellType(sheet.getRow(31).getCell(0).getCellType());
                            cell1.setCellValue(sheet.getRow(31).getCell(0).getNumericCellValue());
                            cell1.setCellStyle(sheet.getRow(31).getCell(0).getCellStyle());
                            //32行
                            Cell cell2 = toSheet.getRow(31 + addRows).getCell(1);
                            cell2.setCellType(sheet.getRow(31).getCell(1).getCellType());
                            cell2.setCellValue(sheet.getRow(31).getCell(1).getStringCellValue());
                            cell2.setCellStyle(sheet.getRow(31).getCell(1).getCellStyle());

                            Cell cell3 = toSheet.getRow(31 + addRows).getCell(8);
                            CellStyle cellStyle3 = sheet.getRow(31).getCell(8).getCellStyle();  //获取样式
                            cellStyle3.setBorderLeft(CellStyle.BORDER_THIN);  //左边框
                            cell3.setCellType(sheet.getRow(31).getCell(8).getCellType());
                            cell3.setCellValue(sheet.getRow(31).getCell(8).getStringCellValue());
                            cell3.setCellStyle(cellStyle3);

                            Cell cell4 = toSheet.getRow(31 + addRows).getCell(9);
                            cell4.setCellType(sheet.getRow(31).getCell(9).getCellType());
                            cell4.setCellValue(sheet.getRow(31).getCell(9).getStringCellValue());
                            cell4.setCellStyle(sheet.getRow(31).getCell(9).getCellStyle());
                            //33行
                            Cell cell5 = toSheet.getRow(32 + addRows).getCell(1);
                            cell5.setCellType(sheet.getRow(32).getCell(1).getCellType());
                            cell5.setCellValue(sheet.getRow(32).getCell(1).getStringCellValue());
                            cell5.setCellStyle(sheet.getRow(32).getCell(1).getCellStyle());

                            Cell cell6 = toSheet.getRow(32 + addRows).getCell(8);
                            CellStyle cellStyle6 = sheet.getRow(32).getCell(8).getCellStyle();  //获取样式
                            cellStyle6.setBorderLeft(CellStyle.BORDER_THIN);  //左边框
                            cell6.setCellType(sheet.getRow(32).getCell(8).getCellType());
                            cell6.setCellValue(sheet.getRow(32).getCell(8).getStringCellValue());
                            cell6.setCellStyle(cellStyle6);

                            Cell cell7 = toSheet.getRow(32 + addRows).getCell(9);
                            cell7.setCellType(sheet.getRow(32).getCell(9).getCellType());
                            cell7.setCellValue(sheet.getRow(32).getCell(9).getStringCellValue());
                            cell7.setCellStyle(sheet.getRow(32).getCell(9).getCellStyle());
                            //34行
                            Cell cell8 = toSheet.getRow(33 + addRows).getCell(1);
                            cell8.setCellType(sheet.getRow(33).getCell(1).getCellType());
                            cell8.setCellValue(sheet.getRow(33).getCell(1).getStringCellValue());
                            cell8.setCellStyle(sheet.getRow(33).getCell(1).getCellStyle());

                            Cell cell9 = toSheet.getRow(33 + addRows).getCell(8);
                            CellStyle cellStyle9 = sheet.getRow(33).getCell(8).getCellStyle();  //获取样式
                            cellStyle9.setBorderLeft(CellStyle.BORDER_THIN);  //左边框
                            cell9.setCellType(sheet.getRow(33).getCell(8).getCellType());
                            cell9.setCellValue(sheet.getRow(33).getCell(8).getStringCellValue());
                            cell9.setCellStyle(cellStyle9);

                            Cell cell10 = toSheet.getRow(33 + addRows).getCell(9);
                            cell10.setCellType(sheet.getRow(33).getCell(9).getCellType());
                            cell10.setCellValue(sheet.getRow(33).getCell(9).getStringCellValue());
                            cell10.setCellStyle(sheet.getRow(33).getCell(9).getCellStyle());
                            //35行
                            //根本原因分析预防内容
                            for(int i = 0; i < addRowsPrevent + 1; i++){
                                toSheet.getRow(34 + addRows + i).getCell(1).setCellType(sheet.getRow(34).getCell(1).getCellType());
                                toSheet.getRow(34 + addRows + i).getCell(1).setCellStyle(sheet.getRow(34).getCell(1).getCellStyle());
                            }

                            CellStyle cellStyle12 = sheet.getRow(34).getCell(8).getCellStyle();  //获取样式
                            cellStyle12.setBorderLeft(CellStyle.BORDER_THIN);   //左边框
                            for(int i = 0; i < addRowsPrevent + 1; i++){
                                toSheet.getRow(34 + addRows + i).getCell(8).setCellType(sheet.getRow(34).getCell(8).getCellType());
                                toSheet.getRow(34 + addRows + i).getCell(8).setCellStyle(cellStyle12);
                            }

                            for(int i = 0; i < addRowsPrevent + 1; i++){
                                toSheet.getRow(34 + addRows + i).getCell(9).setCellType(sheet.getRow(34).getCell(9).getCellType());
                                toSheet.getRow(34 + addRows + i).getCell(9).setCellStyle(sheet.getRow(34).getCell(9).getCellStyle());
                            }

                            for(int i = 0; i < preventList.size(); i++){
                                String personName = preventList.get(i).getBsHandlerName();   //处理人员名称
                                if(preventList.get(i).getBsHandlerId() != null && preventList.get(i).getHandlerBy() != null){
                                    personName = preventList.get(i).getHandlerBy().getBsName();
                                }
                                String personDate = simpleDateFormat.format(preventList.get(i).getBsCreatedTime());   //处理时间
                                String personDesc = preventList.get(i).getBsDesc();   //描述信息

                                toSheet.getRow(34 + addRows + i).getCell(1).getCellStyle().setVerticalAlignment(XSSFCellStyle.VERTICAL_TOP);
                                toSheet.getRow(34 + addRows + i).getCell(1).setCellValue(personDesc);
                                toSheet.getRow(34 + addRows + i).getCell(8).setCellValue(personName);
                                toSheet.getRow(34 + addRows + i).getCell(9).setCellValue(personDate);
                            }
                            //36行
                            Cell cell14 = toSheet.getRow(35 + addRows + addRowsPrevent).getCell(1);
                            cell14.setCellType(sheet.getRow(35).getCell(1).getCellType());
                            cell14.setCellValue(sheet.getRow(35).getCell(1).getStringCellValue());
                            cell14.setCellStyle(sheet.getRow(35).getCell(1).getCellStyle());

                            Cell cell15 = toSheet.getRow(35 + addRows + addRowsPrevent).getCell(8);
                            CellStyle cellStyle15 = sheet.getRow(35).getCell(8).getCellStyle();  //获取样式
                            cellStyle15.setBorderLeft(CellStyle.BORDER_THIN);  //左边框
                            cell15.setCellType(sheet.getRow(35).getCell(8).getCellType());
                            cell15.setCellValue(sheet.getRow(35).getCell(8).getStringCellValue());
                            cell15.setCellStyle(cellStyle15);

                            Cell cell16 = toSheet.getRow(35 + addRows + addRowsPrevent).getCell(9);
                            cell16.setCellType(sheet.getRow(35).getCell(9).getCellType());
                            cell16.setCellValue(sheet.getRow(35).getCell(9).getStringCellValue());
                            cell16.setCellStyle(sheet.getRow(35).getCell(9).getCellStyle());
                            //37行
                            //根本原因分析保护内容
                            for(int i = 0; i < addRowsProtect + 1; i++){
                                toSheet.getRow(36 + addRows + addRowsPrevent + i).getCell(1).setCellType(sheet.getRow(36).getCell(1).getCellType());
                                toSheet.getRow(36 + addRows + addRowsPrevent + i).getCell(1).setCellStyle(sheet.getRow(36).getCell(1).getCellStyle());
                            }

                            for(int i = 0; i < addRowsProtect + 1; i++){
                                toSheet.getRow(36 + addRows + addRowsPrevent + i).getCell(8).setCellType(sheet.getRow(36).getCell(8).getCellType());
                                toSheet.getRow(36 + addRows + addRowsPrevent + i).getCell(8).setCellStyle(sheet.getRow(36).getCell(8).getCellStyle());
                            }

                            for(int i = 0; i < addRowsProtect + 1; i++){
                                toSheet.getRow(36 + addRows + addRowsPrevent + i).getCell(9).setCellType(sheet.getRow(36).getCell(9).getCellType());
                                toSheet.getRow(36 + addRows + addRowsPrevent + i).getCell(9).setCellStyle(sheet.getRow(36).getCell(9).getCellStyle());
                            }

                            for(int i = 0; i < protectList.size(); i++){
                                String personName = protectList.get(i).getBsHandlerName();   //处理人员名称
                                if(protectList.get(i).getBsHandlerId() != null && protectList.get(i).getHandlerBy() != null){
                                    personName = protectList.get(i).getHandlerBy().getBsName();
                                }
                                String personDate = simpleDateFormat.format(protectList.get(i).getBsCreatedTime());   //处理时间
                                String personDesc = protectList.get(i).getBsDesc();   //描述信息

                                toSheet.getRow(36 + addRows + addRowsPrevent + i).getCell(1).getCellStyle().setVerticalAlignment(XSSFCellStyle.VERTICAL_TOP);
                                toSheet.getRow(36 + addRows + addRowsPrevent + i).getCell(1).setCellValue(personDesc);
                                toSheet.getRow(36 + addRows + addRowsPrevent + i).getCell(8).setCellValue(personName);
                                toSheet.getRow(36 + addRows + addRowsPrevent + i).getCell(9).setCellValue(personDate);
                            }
                            //38行
                            Cell cell20 = toSheet.getRow(37 + addRows + addRowsPrevent + addRowsProtect).getCell(1);
                            cell20.setCellType(sheet.getRow(37).getCell(1).getCellType());
                            cell20.setCellValue(sheet.getRow(37).getCell(1).getStringCellValue());
                            cell20.setCellStyle(sheet.getRow(37).getCell(1).getCellStyle());

                            Cell cell21 = toSheet.getRow(37 + addRows + addRowsPrevent + addRowsProtect).getCell(8);
                            CellStyle cellStyle21 = sheet.getRow(37).getCell(8).getCellStyle();  //获取样式
                            cellStyle21.setBorderLeft(CellStyle.BORDER_THIN);  //左边框
                            cell21.setCellType(sheet.getRow(37).getCell(8).getCellType());
                            cell21.setCellValue(sheet.getRow(37).getCell(8).getStringCellValue());
                            cell21.setCellStyle(cellStyle21);

                            Cell cell22 = toSheet.getRow(37 + addRows + addRowsPrevent + addRowsProtect).getCell(9);
                            cell22.setCellType(sheet.getRow(37).getCell(9).getCellType());
                            cell22.setCellValue(sheet.getRow(37).getCell(9).getStringCellValue());
                            cell22.setCellStyle(sheet.getRow(37).getCell(9).getCellStyle());
                            //39行
                            //根本原因分析保护内容
                            for(int i = 0; i < addRowsPredict + 1; i++){
                                toSheet.getRow(38 + addRows + addRowsPrevent + addRowsProtect + i).getCell(1).setCellType(sheet.getRow(38).getCell(1).getCellType());
                                toSheet.getRow(38 + addRows + addRowsPrevent + addRowsProtect + i).getCell(1).setCellStyle(sheet.getRow(38).getCell(1).getCellStyle());
                            }

                            for(int i = 0; i < addRowsPredict + 1; i++){
                                toSheet.getRow(38 + addRows + addRowsPrevent + addRowsProtect + i).getCell(8).setCellType(sheet.getRow(38).getCell(8).getCellType());
                                toSheet.getRow(38 + addRows + addRowsPrevent + addRowsProtect + i).getCell(8).setCellStyle(sheet.getRow(38).getCell(8).getCellStyle());
                            }

                            for(int i = 0; i < addRowsPredict + 1; i++){
                                toSheet.getRow(38 + addRows + addRowsPrevent + addRowsProtect + i).getCell(9).setCellType(sheet.getRow(38).getCell(9).getCellType());
                                toSheet.getRow(38 + addRows + addRowsPrevent + addRowsProtect + i).getCell(9).setCellStyle(sheet.getRow(38).getCell(9).getCellStyle());
                            }

                            for(int i = 0; i < predictList.size(); i++){
                                String personName = predictList.get(i).getBsHandlerName();   //处理人员名称
                                if(predictList.get(i).getBsHandlerId() != null && predictList.get(i).getHandlerBy() != null){
                                    personName = predictList.get(i).getHandlerBy().getBsName();
                                }
                                String personDate = simpleDateFormat.format(predictList.get(i).getBsCreatedTime());   //处理时间
                                String personDesc = predictList.get(i).getBsDesc();   //描述信息

                                toSheet.getRow(38 + addRows + addRowsPrevent + addRowsProtect + i).getCell(1).getCellStyle().setVerticalAlignment(XSSFCellStyle.VERTICAL_TOP);
                                toSheet.getRow(38 + addRows + addRowsPrevent + addRowsProtect + i).getCell(1).setCellValue(personDesc);
                                toSheet.getRow(38 + addRows + addRowsPrevent + addRowsProtect + i).getCell(8).setCellValue(personName);
                                toSheet.getRow(38 + addRows + addRowsPrevent + addRowsProtect + i).getCell(9).setCellValue(personDate);
                            }

                            //合并单元格
                            //region8
                            for(int k = addRowsPredict; k >= 0; k--){
                                CellRangeAddress region8 = new CellRangeAddress(38 + addRows + addRowsPrevent + addRowsProtect + k, 38 + addRows + addRowsPrevent + addRowsProtect + k, 1 , 7);
                                toSheet.addMergedRegion(region8);
                                RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region8, toSheet, workbook);
                                RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region8, toSheet, workbook);
                            }
                            //region9
                            CellRangeAddress region9 = new CellRangeAddress(37 + addRows + addRowsPrevent + addRowsProtect, 37 + addRows + addRowsPrevent + addRowsProtect, 1, 7);
                            toSheet.addMergedRegion(region9);
                            RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region9, toSheet, workbook);
                            RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region9, toSheet, workbook);
                            //region1
                            for(int k = addRowsProtect; k >= 0; k--){
                                CellRangeAddress region1 = new CellRangeAddress(36 + addRows + addRowsPrevent + k, 36 + addRows + addRowsPrevent + k, 1, 7);
                                toSheet.addMergedRegion(region1);
                                RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region1, toSheet, workbook);
                                RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region1, toSheet, workbook);
                            }
                            //region2
                            CellRangeAddress region2 = new CellRangeAddress(35 + addRows + addRowsPrevent, 35 + addRows + addRowsPrevent, 1, 7);
                            toSheet.addMergedRegion(region2);
                            RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region2, toSheet, workbook);
                            RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region2, toSheet, workbook);
                            //region3
                            for(int k = addRowsPrevent; k >= 0; k--){
                                CellRangeAddress region3 = new CellRangeAddress(34 + addRows + k, 34 + addRows + k, 1, 7);
                                toSheet.addMergedRegion(region3);
                                RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region3, toSheet, workbook);
                                RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region3, toSheet, workbook);
                            }
                            //region4
                            CellRangeAddress region4 = new CellRangeAddress(33 + addRows, 33 + addRows, 1, 7);
                            toSheet.addMergedRegion(region4);
                            RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region4, toSheet, workbook);
                            RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region4, toSheet, workbook);
                            //region5
                            CellRangeAddress region5 = new CellRangeAddress(32 + addRows, 32 + addRows, 1, 7);
                            toSheet.addMergedRegion(region5);
                            RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region5, toSheet, workbook);
                            //region6
                            CellRangeAddress region6 = new CellRangeAddress(31 + addRows, 31 + addRows, 1, 7);
                            toSheet.addMergedRegion(region6);
                            RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region6, toSheet, workbook);
                            //region7
                            CellRangeAddress region7 = new CellRangeAddress(31 + addRows, 38 + addRows + addRowsPerson, 0, 0);
                            toSheet.addMergedRegion(region7);

                            addRows = addRows + addRowsPerson;                     //总增加行数
                        }
                        //2.4根本原因分析 结束

                        //2.5选择的永久的纠正措施 开始
                        List<FeedbackHandler> feedbackHandlerList5 = feedbackHandlerDao.findByBsIsDelAndBsFeedbackIdAndBsType(BooleanStateEnum.FALSE.intValue(), bsFeedbackId, BasicEnumConstants.FEEDBACK_HANDLER5);
                        if(feedbackHandlerList5.size() >= 0){
                            //创建行
                            int addRowsPerson = feedbackHandlerList5.size() <= 0 ? 0 : feedbackHandlerList5.size() - 1;    //本模块增加的行数，最小为0

                            for(int i = 39 + addRows; i <= 41 + addRows + addRowsPerson; i++){
                                Row createRow = toSheet.createRow(i);
                                for(int j = 0;j < 10; j++){
                                    createRow.createCell(j);
                                }
                            }
                            //设置行高
                            for(int i = 0; i < addRowsPerson + 1; i++){
                                toSheet.getRow(40 + addRows + i).setHeightInPoints(70);
                            }

                            //添加样式与数据
                            //第5部分
                            Cell cell1 = toSheet.getRow(39 + addRows).getCell(0);
                            cell1.setCellType(sheet.getRow(39).getCell(0).getCellType());
                            cell1.setCellValue(sheet.getRow(39).getCell(0).getNumericCellValue());
                            cell1.setCellStyle(sheet.getRow(39).getCell(0).getCellStyle());

                            Cell cell2 = toSheet.getRow(39 + addRows).getCell(1);
                            cell2.setCellType(sheet.getRow(39).getCell(1).getCellType());
                            cell2.setCellValue(sheet.getRow(39).getCell(1).getStringCellValue());
                            cell2.setCellStyle(sheet.getRow(39).getCell(1).getCellStyle());

                            Cell cell3 = toSheet.getRow(39 + addRows).getCell(8);
                            cell3.setCellType(sheet.getRow(39).getCell(8).getCellType());
                            cell3.setCellValue(sheet.getRow(39).getCell(8).getStringCellValue());
                            cell3.setCellStyle(sheet.getRow(39).getCell(8).getCellStyle());

                            Cell cell4 = toSheet.getRow(39 + addRows).getCell(9);
                            cell4.setCellType(sheet.getRow(39).getCell(9).getCellType());
                            cell4.setCellValue(sheet.getRow(39).getCell(9).getStringCellValue());
                            cell4.setCellStyle(sheet.getRow(39).getCell(9).getCellStyle());

                            //选择的永久的纠正措施内容
                            for(int i = 0; i < addRowsPerson + 1; i++){
                                toSheet.getRow(40 + addRows + i).getCell(1).setCellType(sheet.getRow(40).getCell(1).getCellType());
                                toSheet.getRow(40 + addRows + i).getCell(1).setCellStyle(sheet.getRow(40).getCell(1).getCellStyle());
                            }

                            CellStyle cellStyle5 = sheet.getRow(40).getCell(8).getCellStyle();  //获取样式
                            cellStyle5.setBorderLeft(CellStyle.BORDER_THIN);   //左边框
                            for(int i = 0; i < addRowsPerson + 1; i++){
                                toSheet.getRow(40 + addRows + i).getCell(8).setCellType(sheet.getRow(40).getCell(8).getCellType());
                                toSheet.getRow(40 + addRows + i).getCell(8).setCellStyle(cellStyle5);
                            }

                            for(int i = 0; i < addRowsPerson + 1; i++){
                                toSheet.getRow(40 + addRows + i).getCell(9).setCellType(sheet.getRow(40).getCell(9).getCellType());
                                toSheet.getRow(40 + addRows + i).getCell(9).setCellStyle(sheet.getRow(40).getCell(9).getCellStyle());
                            }

                            for(int i = 0; i < feedbackHandlerList5.size(); i++){
                                String personName = feedbackHandlerList5.get(i).getBsHandlerName();   //处理人员名称
                                if(feedbackHandlerList5.get(i).getBsHandlerId() != null && feedbackHandlerList5.get(i).getHandlerBy() != null){
                                    personName = feedbackHandlerList5.get(i).getHandlerBy().getBsName();
                                }
                                String personDate = simpleDateFormat.format(feedbackHandlerList5.get(i).getBsCreatedTime());   //处理时间
                                String personDesc = feedbackHandlerList5.get(i).getBsDesc();   //描述信息

                                toSheet.getRow(40 + addRows + i).getCell(1).setCellValue(personDesc);
                                toSheet.getRow(40 + addRows + i).getCell(8).setCellValue(personName);
                                toSheet.getRow(40 + addRows + i).getCell(9).setCellValue(personDate);
                            }

                            Cell cell8 = toSheet.getRow(41 + addRows + addRowsPerson).getCell(1);
                            cell8.setCellType(sheet.getRow(41).getCell(1).getCellType());
                            cell8.setCellValue(sheet.getRow(41).getCell(1).getStringCellValue());
                            cell8.setCellStyle(sheet.getRow(41).getCell(1).getCellStyle());

                            Cell cell9 = toSheet.getRow(41 + addRows + addRowsPerson).getCell(8);
                            cell9.setCellType(sheet.getRow(41).getCell(8).getCellType());
                            cell9.setCellValue(sheet.getRow(41).getCell(8).getStringCellValue());
                            cell9.setCellStyle(sheet.getRow(41).getCell(8).getCellStyle());

                            Cell cell10 = toSheet.getRow(41 + addRows + addRowsPerson).getCell(9);
                            cell10.setCellType(sheet.getRow(41).getCell(9).getCellType());
                            cell10.setCellValue(sheet.getRow(41).getCell(9).getStringCellValue());
                            cell10.setCellStyle(sheet.getRow(41).getCell(9).getCellStyle());

                            //合并单元格
                            //region1
                            CellRangeAddress region1 = new CellRangeAddress(41 + addRows + addRowsPerson, 41 + addRows + addRowsPerson, 8, 9);
                            toSheet.addMergedRegion(region1);
                            RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region1, toSheet, workbook);
                            RegionUtil.setBorderRight(XSSFCellStyle.BORDER_MEDIUM, region1, toSheet, workbook);
                            RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region1, toSheet, workbook);
                            //region2
                            CellRangeAddress region2 = new CellRangeAddress(41 + addRows + addRowsPerson, 41 + addRows + addRowsPerson, 1, 7);
                            toSheet.addMergedRegion(region2);
                            RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region2, toSheet, workbook);
                            RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region2, toSheet, workbook);
                            RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region2, toSheet, workbook);
                            //region3
                            for(int k = addRowsPerson; k >= 0; k--){
                                CellRangeAddress region3 = new CellRangeAddress(40 + addRows + k, 40 + addRows + k, 1, 7);
                                toSheet.addMergedRegion(region3);
                                RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region3, toSheet, workbook);
                                RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region3, toSheet, workbook);
                                RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region3, toSheet, workbook);
                            }
                            //region4
                            CellRangeAddress region4 = new CellRangeAddress(39 + addRows, 39 + addRows, 1, 7);
                            toSheet.addMergedRegion(region4);
                            RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region4, toSheet, workbook);
                            RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region4, toSheet, workbook);
                            //region5
                            CellRangeAddress region5 = new CellRangeAddress(39 + addRows, 41 + addRows + addRowsPerson, 0, 0);
                            toSheet.addMergedRegion(region5);

                            addRows = addRows + addRowsPerson;                     //总增加行数
                        }
                        //2.5选择的永久的纠正措施 结束

                        //2.6执行的永久的纠正措施 开始
                        List<FeedbackHandler> feedbackHandlerList6 = feedbackHandlerDao.findByBsIsDelAndBsFeedbackIdAndBsType(BooleanStateEnum.FALSE.intValue(), bsFeedbackId, BasicEnumConstants.FEEDBACK_HANDLER6);
                        if(feedbackHandlerList6.size() >= 0){
                            //创建行
                            int addRowsPerson = feedbackHandlerList6.size() <= 0 ? 0 : feedbackHandlerList6.size() - 1;   //本模块增加的行数，最小为0

                            for(int i = 42 + addRows; i <= 43 + addRows + addRowsPerson; i++){
                                Row createRow = toSheet.createRow(i);
                                for(int j = 0;j < 10; j++){
                                    createRow.createCell(j);
                                }
                            }
                            //设置行高
                            for(int i = 0; i < addRowsPerson + 1; i++){
                                toSheet.getRow(43 + addRows + i).setHeightInPoints(70);
                            }

                            //添加样式与数据
                            //第6部分
                            Cell cell1 = toSheet.getRow(42 + addRows).getCell(0);
                            cell1.setCellType(sheet.getRow(42).getCell(0).getCellType());
                            cell1.setCellValue(sheet.getRow(42).getCell(0).getNumericCellValue());
                            cell1.setCellStyle(sheet.getRow(42).getCell(0).getCellStyle());

                            Cell cell2 = toSheet.getRow(42 + addRows).getCell(1);
                            cell2.setCellType(sheet.getRow(42).getCell(1).getCellType());
                            cell2.setCellValue(sheet.getRow(42).getCell(1).getStringCellValue());
                            cell2.setCellStyle(sheet.getRow(42).getCell(1).getCellStyle());

                            Cell cell3 = toSheet.getRow(42 + addRows).getCell(8);
                            cell3.setCellType(sheet.getRow(42).getCell(8).getCellType());
                            cell3.setCellValue(sheet.getRow(42).getCell(8).getStringCellValue());
                            cell3.setCellStyle(sheet.getRow(42).getCell(8).getCellStyle());

                            Cell cell4 = toSheet.getRow(42 + addRows).getCell(9);
                            cell4.setCellType(sheet.getRow(42).getCell(9).getCellType());
                            cell4.setCellValue(sheet.getRow(42).getCell(9).getStringCellValue());
                            cell4.setCellStyle(sheet.getRow(42).getCell(9).getCellStyle());


                            //执行的的永久的纠正措施内容
                            for(int i = 0; i < addRowsPerson + 1; i++){
                                toSheet.getRow(43 + addRows + i).getCell(1).setCellType(sheet.getRow(43).getCell(1).getCellType());
                                toSheet.getRow(43 + addRows + i).getCell(1).setCellStyle(sheet.getRow(43).getCell(1).getCellStyle());
                            }

                            CellStyle cellStyle5 = sheet.getRow(43).getCell(8).getCellStyle();  //获取样式
                            cellStyle5.setBorderLeft(CellStyle.BORDER_THIN);   //左边框
                            for(int i = 0; i < addRowsPerson + 1; i++){
                                toSheet.getRow(43 + addRows + i).getCell(8).setCellType(sheet.getRow(43).getCell(8).getCellType());
                                toSheet.getRow(43 + addRows + i).getCell(8).setCellStyle(cellStyle5);
                            }

                            for(int i = 0; i < addRowsPerson + 1; i++){
                                toSheet.getRow(43 + addRows + i).getCell(9).setCellType(sheet.getRow(43).getCell(9).getCellType());
                                toSheet.getRow(43 + addRows + i).getCell(9).setCellStyle(sheet.getRow(43).getCell(9).getCellStyle());
                            }

                            for(int i = 0; i < feedbackHandlerList6.size(); i++ ){
                                String personName = feedbackHandlerList6.get(i).getBsHandlerName();   //处理人员名称
                                if(feedbackHandlerList6.get(i).getBsHandlerId() != null && feedbackHandlerList6.get(i).getHandlerBy() != null){
                                    personName = feedbackHandlerList6.get(i).getHandlerBy().getBsName();
                                }
                                String personDate = simpleDateFormat.format(feedbackHandlerList6.get(i).getBsCreatedTime());   //处理时间
                                String personDesc = feedbackHandlerList6.get(i).getBsDesc();   //描述信息

                                toSheet.getRow(43 + addRows + i).getCell(1).setCellValue(personDesc);
                                toSheet.getRow(43 + addRows + i).getCell(8).setCellValue(personName);
                                toSheet.getRow(43 + addRows + i).getCell(9).setCellValue(personDate);
                            }

                            //合并单元格
                            //region1
                            for(int k = addRowsPerson; k >= 0; k--){
                                CellRangeAddress region1 = new CellRangeAddress(43 + addRows + k, 43 + addRows + k, 1, 7);
                                toSheet.addMergedRegion(region1);
                                RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region1, toSheet, workbook);
                                RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region1, toSheet, workbook);
                                RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region1, toSheet, workbook);
                            }
                            //region2
                            CellRangeAddress region2 = new CellRangeAddress(42 + addRows, 42 + addRows, 1, 7);
                            toSheet.addMergedRegion(region2);
                            RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region2, toSheet, workbook);
                            RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region2, toSheet, workbook);
                            //region3
                            CellRangeAddress region3 = new CellRangeAddress(42 + addRows, 43 + addRows + addRowsPerson, 0, 0);
                            toSheet.addMergedRegion(region3);

                            addRows = addRows + addRowsPerson;                     //总增加行数
                        }
                        //2.6执行的永久的纠正措施 结束

                        //2.7预防行动 开始
                        List<FeedbackHandler> feedbackHandlerList7 = feedbackHandlerDao.findByBsIsDelAndBsFeedbackIdAndBsType(BooleanStateEnum.FALSE.intValue(), bsFeedbackId, BasicEnumConstants.FEEDBACK_HANDLER7);
                        if(feedbackHandlerList7.size() >= 0){
                            //创建行
                            int addRowsPerson = feedbackHandlerList7.size() <= 0 ? 0 : feedbackHandlerList7.size() - 1;   //本模块增加的行数，最小为0

                            for(int i = 44 + addRows; i <= 46 + addRows + addRowsPerson; i++){
                                Row createRow = toSheet.createRow(i);
                                for(int j = 0;j < 10; j++){
                                    createRow.createCell(j);
                                }
                            }
                            //设置行高
                            for(int i = 0; i < addRowsPerson + 1; i++){
                                toSheet.getRow(46 + addRows + i).setHeightInPoints((float) 29.3);
                            }

                            //添加样式与数据
                            //第7部分
                            Cell cell1 = toSheet.getRow(44 + addRows).getCell(0);
                            cell1.setCellType(sheet.getRow(44).getCell(0).getCellType());
                            cell1.setCellValue(sheet.getRow(44).getCell(0).getNumericCellValue());
                            cell1.setCellStyle(sheet.getRow(44).getCell(0).getCellStyle());
                            //45行
                            Cell cell2 = toSheet.getRow(44 + addRows).getCell(1);
                            cell2.setCellType(sheet.getRow(44).getCell(1).getCellType());
                            cell2.setCellValue(sheet.getRow(44).getCell(1).getStringCellValue());
                            cell2.setCellStyle(sheet.getRow(44).getCell(1).getCellStyle());

                            Cell cell3 = toSheet.getRow(44 + addRows).getCell(6);
                            cell3.setCellType(sheet.getRow(44).getCell(6).getCellType());
                            cell3.setCellValue(sheet.getRow(44).getCell(6).getStringCellValue());
                            cell3.setCellStyle(sheet.getRow(44).getCell(6).getCellStyle());

                            Cell cell4 = toSheet.getRow(44 + addRows).getCell(7);
                            cell4.setCellType(sheet.getRow(44).getCell(7).getCellType());
                            cell4.setCellValue(sheet.getRow(44).getCell(7).getNumericCellValue());
                            cell4.setCellStyle(sheet.getRow(44).getCell(7).getCellStyle());

                            Cell cell5 = toSheet.getRow(44 + addRows).getCell(8);
                            cell5.setCellType(sheet.getRow(44).getCell(8).getCellType());
                            cell5.setCellValue(sheet.getRow(44).getCell(8).getStringCellValue());
                            cell5.setCellStyle(sheet.getRow(44).getCell(8).getCellStyle());

                            Cell cell6 = toSheet.getRow(44 + addRows).getCell(9);
                            cell6.setCellType(sheet.getRow(44).getCell(9).getCellType());
                            cell6.setCellValue(sheet.getRow(44).getCell(9).getStringCellValue());
                            cell6.setCellStyle(sheet.getRow(44).getCell(9).getCellStyle());
                            //46行
                            Cell cell7 = toSheet.getRow(45 + addRows).getCell(1);
                            cell7.setCellType(sheet.getRow(45).getCell(1).getCellType());
                            cell7.setCellValue(sheet.getRow(45).getCell(1).getStringCellValue());
                            cell7.setCellStyle(sheet.getRow(45).getCell(1).getCellStyle());

                            Cell cell8 = toSheet.getRow(45 + addRows).getCell(6);
                            cell8.setCellType(sheet.getRow(45).getCell(6).getCellType());
                            cell8.setCellValue(sheet.getRow(45).getCell(6).getStringCellValue());
                            cell8.setCellStyle(sheet.getRow(45).getCell(6).getCellStyle());

                            Cell cell9 = toSheet.getRow(45 + addRows).getCell(8);
                            CellStyle cellStyle9 = sheet.getRow(45).getCell(8).getCellStyle();  //获取样式
                            cellStyle9.setBorderLeft(CellStyle.BORDER_THIN);   //左边框
                            cell9.setCellType(sheet.getRow(45).getCell(8).getCellType());
                            cell9.setCellValue(sheet.getRow(45).getCell(8).getStringCellValue());
                            cell9.setCellStyle(cellStyle9);

                            Cell cell10 = toSheet.getRow(45 + addRows).getCell(9);
                            cell10.setCellType(sheet.getRow(45).getCell(9).getCellType());
                            cell10.setCellValue(sheet.getRow(45).getCell(9).getStringCellValue());
                            cell10.setCellStyle(sheet.getRow(45).getCell(9).getCellStyle());

                            //预防行动内容
                            for(int i = 0; i < addRowsPerson + 1; i++){
                                toSheet.getRow(46 + addRows + i).getCell(1).setCellType(sheet.getRow(46).getCell(1).getCellType());
                                toSheet.getRow(46 + addRows + i).getCell(1).setCellStyle(sheet.getRow(46).getCell(1).getCellStyle());
                            }

                            for(int i = 0; i < addRowsPerson + 1; i++){
                                toSheet.getRow(46 + addRows + i).getCell(8).setCellType(sheet.getRow(46).getCell(8).getCellType());
                                toSheet.getRow(46 + addRows + i).getCell(8).setCellStyle(sheet.getRow(46).getCell(8).getCellStyle());
                            }

                            for(int i = 0; i < addRowsPerson + 1; i++){
                                toSheet.getRow(46 + addRows + i).getCell(9).setCellType(sheet.getRow(46).getCell(9).getCellType());
                                toSheet.getRow(46 + addRows + i).getCell(9).setCellStyle(sheet.getRow(46).getCell(9).getCellStyle());
                            }

                            for(int i = 0; i < feedbackHandlerList7.size(); i++ ){
                                String personName = feedbackHandlerList7.get(i).getBsHandlerName();   //处理人员名称
                                if(feedbackHandlerList7.get(i).getBsHandlerId() != null && feedbackHandlerList7.get(i).getHandlerBy() != null){
                                    personName = feedbackHandlerList7.get(i).getHandlerBy().getBsName();
                                }
                                String personDate = simpleDateFormat.format(feedbackHandlerList7.get(i).getBsCreatedTime());   //处理时间
                                String personDesc = feedbackHandlerList7.get(i).getBsDesc();   //描述信息

                                toSheet.getRow(46 + addRows + i).getCell(1).setCellValue(personDesc);
                                toSheet.getRow(46 + addRows + i).getCell(8).setCellValue(personName);
                                toSheet.getRow(46 + addRows + i).getCell(9).setCellValue(personDate);
                            }
                            //合并单元格
                            //region1
                            for(int k = addRowsPerson; k >= 0; k--){
                                CellRangeAddress region1 = new CellRangeAddress(46 + addRows + k, 46 + addRows + k, 1, 7);
                                toSheet.addMergedRegion(region1);
                                RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region1, toSheet, workbook);
                                RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region1, toSheet, workbook);
                                RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region1, toSheet, workbook);
                            }
                            //region2
                            CellRangeAddress region2 = new CellRangeAddress(45 + addRows, 45 + addRows, 6, 7);
                            toSheet.addMergedRegion(region2);
                            RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region2, toSheet, workbook);
                            RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region2, toSheet, workbook);
                            RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region2, toSheet, workbook);
                            //region3
                            CellRangeAddress region3 = new CellRangeAddress(45 + addRows, 45 + addRows, 1, 5);
                            toSheet.addMergedRegion(region3);
                            RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region3, toSheet, workbook);
                            RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region3, toSheet, workbook);
                            RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region3, toSheet, workbook);
                            //region4
                            CellRangeAddress region4 = new CellRangeAddress(44 + addRows, 44 + addRows, 1, 5);
                            toSheet.addMergedRegion(region4);
                            RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region3, toSheet, workbook);
                            RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region3, toSheet, workbook);
                            RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region3, toSheet, workbook);
                            //region5
                            CellRangeAddress region5 = new CellRangeAddress(44 + addRows, 46 + addRows + addRowsPerson, 0, 0);
                            toSheet.addMergedRegion(region5);

                            addRows = addRows + addRowsPerson;                     //总增加行数
                        }
                        //2.7预防行动 结束

                        //2.8 开始
                        //创建行
                        for(int i = 47 + addRows; i <= 51 + addRows; i++){
                            Row createRow = toSheet.createRow(i);
                            for(int j = 0;j < 10; j++){
                                createRow.createCell(j);
                            }
                        }
                        //设置行高
                        toSheet.getRow(48 + addRows).setHeightInPoints((float) 30.8);
                        toSheet.getRow(49 + addRows).setHeightInPoints((float) 5.2);
                        toSheet.getRow(50 + addRows).setHeightInPoints((float) 20.3);
                        toSheet.getRow(51 + addRows).setHeightInPoints((float) 20.3);

                        //添加样式与数据
                        //第8部分
                        Cell cell1 = toSheet.getRow(47 + addRows).getCell(0);
                        cell1.setCellType(sheet.getRow(47).getCell(0).getCellType());
                        cell1.setCellValue(sheet.getRow(47).getCell(0).getNumericCellValue());
                        cell1.setCellStyle(sheet.getRow(47).getCell(0).getCellStyle());
                        //48行
                        Cell cell2 = toSheet.getRow(47 + addRows).getCell(1);
                        cell2.setCellType(sheet.getRow(47).getCell(1).getCellType());
                        cell2.setCellValue(sheet.getRow(47).getCell(1).getStringCellValue());
                        cell2.setCellStyle(sheet.getRow(47).getCell(1).getCellStyle());

                        Cell cell3 = toSheet.getRow(47 + addRows).getCell(8);
                        cell3.setCellType(sheet.getRow(47).getCell(8).getCellType());
                        cell3.setCellValue(sheet.getRow(47).getCell(8).getStringCellValue());
                        cell3.setCellStyle(sheet.getRow(47).getCell(8).getCellStyle());

                        Cell cell4 = toSheet.getRow(47 + addRows).getCell(9);
                        cell4.setCellType(sheet.getRow(47).getCell(9).getCellType());
                        cell4.setCellValue(sheet.getRow(47).getCell(9).getStringCellValue());
                        cell4.setCellStyle(sheet.getRow(47).getCell(9).getCellStyle());
                        //49行
                        Cell cell5 = toSheet.getRow(48 + addRows).getCell(1);
                        cell5.setCellType(sheet.getRow(48).getCell(1).getCellType());
                        cell5.setCellStyle(sheet.getRow(48).getCell(1).getCellStyle());

                        Cell cell6 = toSheet.getRow(48 + addRows).getCell(8);
                        cell6.setCellType(sheet.getRow(48).getCell(8).getCellType());
                        cell6.setCellStyle(sheet.getRow(48).getCell(8).getCellStyle());

                        Cell cell7 = toSheet.getRow(48 + addRows).getCell(9);
                        cell7.setCellType(sheet.getRow(48).getCell(9).getCellType());
                        cell7.setCellStyle(sheet.getRow(48).getCell(9).getCellStyle());
                        //50行
                        for(int i = 0; i < 10; i++){
                            Cell cell = toSheet.getRow(49 + addRows).getCell(i);
                            cell.getCellStyle().setBorderRight(XSSFCellStyle.BORDER_THIN);
                            cell.getCellStyle().setBorderLeft(XSSFCellStyle.BORDER_THIN);
                            cell.getCellStyle().setRightBorderColor((short) 0x9);
                            cell.getCellStyle().setLeftBorderColor((short) 0x9);
                        }
                        //51行
                        for(int i = 0; i < 5; i++){
                            Cell cell8 = toSheet.getRow(50 + addRows).getCell(i);
                            cell8.setCellType(sheet.getRow(50).getCell(i).getCellType());
                            cell8.setCellValue(sheet.getRow(50).getCell(i).getStringCellValue());
                            cell8.setCellStyle(sheet.getRow(50).getCell(i).getCellStyle());
                        }

                        Cell cell9 = toSheet.getRow(51 + addRows).getCell(0);
                        cell9.setCellType(sheet.getRow(51).getCell(0).getCellType());
                        cell9.setCellValue(sheet.getRow(51).getCell(0).getStringCellValue());
                        cell9.setCellStyle(sheet.getRow(51).getCell(0).getCellStyle());

                        Cell cell10 = toSheet.getRow(51 + addRows).getCell(1);
                        cell10.setCellType(sheet.getRow(51).getCell(1).getCellType());
                        cell10.setCellValue(sheet.getRow(51).getCell(1).getStringCellValue());
                        cell10.setCellStyle(sheet.getRow(51).getCell(1).getCellStyle());

                        Cell cell11 = toSheet.getRow(51 + addRows).getCell(3);
                        cell11.setCellType(sheet.getRow(51).getCell(3).getCellType());
                        cell11.setCellValue(sheet.getRow(51).getCell(3).getStringCellValue());
                        cell11.setCellStyle(sheet.getRow(51).getCell(3).getCellStyle());

                        Cell cell12 = toSheet.getRow(51 + addRows).getCell(4);
                        cell12.setCellType(sheet.getRow(51).getCell(4).getCellType());
                        cell12.setCellValue(sheet.getRow(51).getCell(4).getStringCellValue());
                        cell12.setCellStyle(sheet.getRow(51).getCell(4).getCellStyle());

                        //合并单元格
                        //region1
                        CellRangeAddress region1 = new CellRangeAddress(51 + addRows, 51 + addRows, 4, 9);
                        toSheet.addMergedRegion(region1);
                        RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region1, toSheet, workbook);
                        RegionUtil.setBorderRight(XSSFCellStyle.BORDER_MEDIUM, region1, toSheet, workbook);
                        RegionUtil.setRightBorderColor(0x8, region1, toSheet, workbook);
                        RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM, region1, toSheet, workbook);
                        //region2
                        CellRangeAddress region2 = new CellRangeAddress(51 + addRows, 51 + addRows, 1, 2);
                        toSheet.addMergedRegion(region2);
                        RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region2, toSheet, workbook);
                        RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region2, toSheet, workbook);
                        RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM, region2, toSheet, workbook);
                        //region3
                        CellRangeAddress region3 = new CellRangeAddress(50 + addRows, 50 + addRows, 4, 9);
                        toSheet.addMergedRegion(region3);
                        RegionUtil.setBorderTop(XSSFCellStyle.BORDER_MEDIUM, region3, toSheet, workbook);
                        RegionUtil.setBorderRight(XSSFCellStyle.BORDER_MEDIUM, region3, toSheet, workbook);
                        RegionUtil.setRightBorderColor(0x8, region3, toSheet, workbook);
                        RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region3, toSheet, workbook);
                        //region4
                        CellRangeAddress region4 = new CellRangeAddress(50 + addRows, 50 + addRows, 0, 1);
                        toSheet.addMergedRegion(region4);
                        RegionUtil.setBorderTop(XSSFCellStyle.BORDER_MEDIUM, region4, toSheet, workbook);
                        RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region4, toSheet, workbook);
                        RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region4, toSheet, workbook);
                        //region5
                        CellRangeAddress region5 = new CellRangeAddress(48 + addRows, 48 + addRows, 1, 7);
                        toSheet.addMergedRegion(region5);
                        RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region5, toSheet, workbook);
                        RegionUtil.setRightBorderColor(0x8, region5, toSheet, workbook);
                        RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM, region5, toSheet, workbook);
                        //region6
                        CellRangeAddress region6 = new CellRangeAddress(47 + addRows, 47 + addRows, 1, 7);
                        toSheet.addMergedRegion(region6);
                        RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region6, toSheet, workbook);
                        RegionUtil.setRightBorderColor(0x8, region6, toSheet, workbook);
                        RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region6, toSheet, workbook);
                        //region7
                        CellRangeAddress region7 = new CellRangeAddress(47 + addRows, 48 + addRows, 0, 0);
                        toSheet.addMergedRegion(region7);
                        RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM, region7, toSheet, workbook);
                        //2.8 结束
                    }
                }
            }

            workbook.removeSheetAt(0);
            response.reset();
            response.setContentType("multipart/form-data");
            String fileName = URLEncoder.encode("客诉报告", "UTF-8")+ ".xlsx";
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            logger.info("导出完成！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ApiResponseResult.success();
    }

    //导出客诉退款信息
    public ApiResponseResult exportFeedbackRefund(Long bsFeedbackId, HttpServletResponse response) throws BusinessException{
        if(bsFeedbackId == null){
            return ApiResponseResult.failure("客诉ID为必填项！");
        }
        try {
//            String templatePath = "E:" + File.separator + "质量扣款通知单.xlsx";
//            String exportPath = "E:" + File.separator + "Refund.xlsx";
//            InputStream inputStream = new FileInputStream(templatePath);
//            OutputStream outputStream = new FileOutputStream(exportPath);

            //从ftp获取模板文件
            ExcelTemp excelTemp = excelTempDao.findFirstByBsType(BasicStateEnum.EXCELTEMP_REFUND.intValue());
            FsFile fsFile = fsFileDao.findOne(excelTemp.getFsFileId());
            String path = appConfig.getString("fs.qms.path")+fsFile.getBsFilePath();
            ApiResponseResult result = ftpClientService.download(path, fsFile.getBsFileName());
            InputStream inputStream = new ByteArrayInputStream((byte[]) result.getData());
            OutputStream outputStream = response.getOutputStream();

            XSSFWorkbook workbook = null;   //创建一个工作薄
            workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            CreationHelper creationHelper = workbook.getCreationHelper();
            int addRows = 0;  //增加的行数
            BigDecimal sumTotal = new BigDecimal(0);  //扣款总额
            List<CellStyle> styleList = getStyle(workbook);  //获取单元格样式

            //填写信息
            FeedbackInfo feedbackInfo = feedbackInfoDao.findOne(bsFeedbackId);
            if(feedbackInfo != null){
                //1.退款单基本信息 开始
                String suppName = feedbackInfo.getBsSuppCompanyName();  //供应商名称
                String refundOrder = feedbackInfo.getBsRefundOrder();  //扣款单号

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date refundDate = feedbackInfo.getBsRefundDate();  //扣款日期
                String refundDateStr = refundDate != null ? simpleDateFormat.format(refundDate) : "";
                Date qualtyAccidentDate = feedbackInfo.getBsQualityAccidentDate();  //质量事故发生日期
                String qualtyAccidentDateStr = qualtyAccidentDate != null ? simpleDateFormat.format(qualtyAccidentDate) : "";

                String customerCode = feedbackInfo.getBsCusCompanyCode();  //客户代码
                String suppContact = feedbackInfo.getBsSuppCompanyPerson();   //通知人（供应商联系人）
                String suppEmail = feedbackInfo.getBsSuppCompanyEmail();  //供应商联系人邮箱
                String suppPhone = feedbackInfo.getBsSuppCompanyMobile();  //供应商联系人电话

                //填写
                sheet.getRow(2).getCell(2).setCellValue(suppName);
                sheet.getRow(2).getCell(13).setCellValue(refundOrder);
                sheet.getRow(4).getCell(2).setCellValue(refundDateStr);
                sheet.getRow(4).getCell(8).setCellValue(qualtyAccidentDateStr);
                sheet.getRow(4).getCell(13).setCellValue(customerCode);
                sheet.getRow(6).getCell(2).setCellValue(suppContact);
                //邮箱添加超链接（符合邮箱格式后添加链接，不符合不添加链接）
                if(suppEmail != null && suppEmail.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")) {
                    suppEmail = suppEmail.trim();
                    sheet.getRow(6).getCell(6).setCellValue(suppEmail);
                    XSSFHyperlink hyperlink = (XSSFHyperlink) creationHelper.createHyperlink(XSSFHyperlink.LINK_EMAIL);
                    hyperlink.setAddress("mailto:" + suppEmail);
                    sheet.getRow(6).getCell(6).setHyperlink(hyperlink);
                }
                sheet.getRow(6).getCell(13).setCellValue(suppPhone);
                //1.退款单基本信息 结束

                //2.客诉退款信息详情 开始
                List<FeedbackRefund> feedbackRefundList = feedbackRefundDao.findByBsIsDelAndBsFeedbackId(BooleanStateEnum.FALSE.intValue(), bsFeedbackId);
                if(feedbackRefundList.size() > 0){
                    addRows = feedbackRefundList.size() - 1;
                }
                //2.1添加行
                if(addRows > 0){
                    //向下移动
                    sheet.shiftRows(10, 35, addRows, false, false);
                    //创建行
                    for(int i = 10; i < 10 + addRows; i++){
                        Row createRow = sheet.createRow(i);
                        for(int j = 0;j < 17; j++){
                            createRow.createCell(j);
                        }
                    }
                    //序号
                    int type = sheet.getRow(9).getCell(0).getCellType();
                    CellStyle style = sheet.getRow(9).getCell(0).getCellStyle();
                    //客户报告号
                    int type2 = sheet.getRow(9).getCell(1).getCellType();
                    CellStyle style2 = sheet.getRow(9).getCell(1).getCellStyle();
                    //产品号
                    int type3 = sheet.getRow(9).getCell(4).getCellType();
                    CellStyle style3 = sheet.getRow(9).getCell(4).getCellStyle();
                    //数量
                    int type4 = sheet.getRow(9).getCell(7).getCellType();
                    CellStyle style4 = sheet.getRow(9).getCell(7).getCellStyle();
                    //单价
                    int type5 = sheet.getRow(9).getCell(8).getCellType();
                    CellStyle style5 = sheet.getRow(9).getCell(8).getCellStyle();
                    //扣款金额
                    int type6 = sheet.getRow(9).getCell(10).getCellType();
                    CellStyle style6 = sheet.getRow(9).getCell(10).getCellStyle();
                    //库款原因
                    int type7 = sheet.getRow(9).getCell(13).getCellType();
                    CellStyle style7 = sheet.getRow(9).getCell(13).getCellStyle();
                    for(int i = 0; i < addRows; i++){
                        //设置行高
                        sheet.getRow(10 + i).setHeightInPoints(75);
                        //添加样式
                        sheet.getRow(10 + i).getCell(0).setCellType(type);
                        sheet.getRow(10 + i).getCell(0).setCellStyle(style);

                        sheet.getRow(10 + i).getCell(1).setCellType(type2);
                        sheet.getRow(10 + i).getCell(1).setCellStyle(style2);

                        sheet.getRow(10 + i).getCell(4).setCellType(type3);
                        sheet.getRow(10 + i).getCell(4).setCellStyle(style3);

                        sheet.getRow(10 + i).getCell(7).setCellType(type4);
                        sheet.getRow(10 + i).getCell(7).setCellStyle(style4);

                        sheet.getRow(10 + i).getCell(8).setCellType(type5);
                        sheet.getRow(10 + i).getCell(8).setCellStyle(style5);

                        sheet.getRow(10 + i).getCell(10).setCellType(type6);
                        sheet.getRow(10 + i).getCell(10).setCellStyle(style6);

                        sheet.getRow(10 + i).getCell(13).setCellType(type7);
                        sheet.getRow(10 + i).getCell(13).setCellStyle(style7);
                    }
                }
                //2.2添加数据
                if(feedbackRefundList.size() > 0){
                    for(int i = 0; i < feedbackRefundList.size(); i++){
                        FeedbackRefund refund = feedbackRefundList.get(i);
                        if(refund != null){
                            String reportNo = refund.getBsReportNo() != null ? refund.getBsReportNo() : "/";  //客户报告号
                            String prCode = "/";   //产品号
                            ProductInfo productInfo = productInfoDao.findOne(refund.getBsPrId());
                            if(productInfo != null){
                                prCode = productInfo.getBsPrCode();
                            }
                            String num = refund.getBsNum() != null ? refund.getBsNum() : "/";  //数量
                            String price = refund.getBsPrice() != null ? refund.getBsPrice() : "/";  //单价
                            String sum = refund.getBsSum() != null ? refund.getBsSum() : "/";   //扣款金额
                            //计算扣款总额
                            if(StringUtils.isNumeric(sum) || sum.matches("\\d+\\.\\d+$")){
                                sumTotal = sumTotal.add(new BigDecimal(sum));
                            }
                            String reason = refund.getBsReason() != null ? refund.getBsReason() : "/";  //扣款原因

                            sheet.getRow(9 + i).getCell(0).setCellValue(i + 1);
                            sheet.getRow(9 + i).getCell(1).setCellValue(reportNo);
                            sheet.getRow(9 + i).getCell(4).setCellValue(prCode);
                            sheet.getRow(9 + i).getCell(7).setCellValue(num);
                            sheet.getRow(9 + i).getCell(8).setCellValue(price);
                            sheet.getRow(9 + i).getCell(10).setCellValue(sum);
                            sheet.getRow(9 + i).getCell(13).setCellValue(reason);
                        }
                    }
                }
                //2.3合并单元格
                for(int i = addRows; i > 0; i--){
                    //region1
                    CellRangeAddress region1 = new CellRangeAddress(9 + i, 9 + i,13,16);
                    sheet.addMergedRegion(region1);
                    RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region1, sheet, workbook);
                    RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region1, sheet, workbook);
                    RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region1, sheet, workbook);
                    //region2
                    CellRangeAddress region2 = new CellRangeAddress(9 + i, 9 + i, 10, 12);
                    sheet.addMergedRegion(region2);
                    RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region2, sheet, workbook);
                    RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region2, sheet, workbook);
                    RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region2, sheet, workbook);
                    //region3
                    CellRangeAddress region3 = new CellRangeAddress(9 + i, 9 + i, 8, 9);
                    sheet.addMergedRegion(region3);
                    RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region3, sheet, workbook);
                    RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region3, sheet, workbook);
                    RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region3, sheet, workbook);
                    //region4
                    CellRangeAddress region4 = new CellRangeAddress(9 + i, 9 + i, 4, 6);
                    sheet.addMergedRegion(region4);
                    RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region4, sheet, workbook);
                    RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region4, sheet, workbook);
                    RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region4, sheet, workbook);
                    //region5
                    CellRangeAddress region5 = new CellRangeAddress(9 + i, 9 + i, 1, 3);
                    sheet.addMergedRegion(region5);
                    RegionUtil.setBorderTop(XSSFCellStyle.BORDER_THIN, region5, sheet, workbook);
                    RegionUtil.setBorderRight(XSSFCellStyle.BORDER_THIN, region5, sheet, workbook);
                    RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region5, sheet, workbook);
                }
                //2.4扣款总额（将前面的扣款金额相加）
                sheet.getRow(10 + addRows).setHeightInPoints((float) 30.8);
                sheet.getRow(10 + addRows).getCell(8).setCellValue(sumTotal.toString());

                //2.客诉退款信息详情 结束

                //3.产生质量扣款的情况 开始
                //3.1设置行高
                sheet.getRow(11 + addRows).setHeightInPoints((float) 11.3);
                sheet.getRow(12 + addRows).setHeightInPoints((float) 14.2);
                for(int i = 13 + addRows; i < 30 + addRows; i = i + 2){   //13行至30行设置行高为6的行
                    sheet.getRow(i).setHeightInPoints(6);
                }
                for(int i = 14 + addRows; i < 29 +addRows; i = i + 2){
                    sheet.getRow(i).setHeightInPoints(15);
                }
                //3.2设置样式
                //货物拒绝或报废
                if(feedbackInfo.getBsEvent13() == 1){
                    sheet.getRow(14 + addRows).getCell(0).setCellStyle(styleList.get(0));
                }else{
                    sheet.getRow(14 + addRows).getCell(0).setCellStyle(styleList.get(1));
                }
                //货物紧急替换
                if(feedbackInfo.getBsEvent1() == 1){
                    sheet.getRow(14 + addRows).getCell(8).setCellStyle(styleList.get(0));
                } else{
                    sheet.getRow(14 + addRows).getCell(8).setCellStyle(styleList.get(1));
                }
                //客户现场分选货物
                if(feedbackInfo.getBsEvent7() == 1){
                    sheet.getRow(16 + addRows).getCell(0).setCellStyle(styleList.get(0));
                }else{
                    sheet.getRow(16 + addRows).getCell(0).setCellStyle(styleList.get(1));
                }
                //国外仓库分选货物
                if(feedbackInfo.getBsEvent2() == 1){
                    sheet.getRow(16 + addRows).getCell(8).setCellStyle(styleList.get(0));
                }else{
                    sheet.getRow(16 + addRows).getCell(8).setCellStyle(styleList.get(1));
                }
                //标签错误
                if(feedbackInfo.getBsEvent8() == 1){
                    sheet.getRow(18 + addRows).getCell(0).setCellStyle(styleList.get(0));
                }else{
                    sheet.getRow(18 + addRows).getCell(0).setCellStyle(styleList.get(1));
                }
                //客户让步接收差异
                if(feedbackInfo.getBsEvent3() == 1){
                    sheet.getRow(18 + addRows).getCell(8).setCellStyle(styleList.get(0));
                }else{
                    sheet.getRow(18 + addRows).getCell(8).setCellStyle(styleList.get(1));
                }
                //空运费
                if(feedbackInfo.getBsEvent9() == 1){
                    sheet.getRow(20 + addRows).getCell(0).setCellStyle(styleList.get(0));
                }else{
                    sheet.getRow(20 + addRows).getCell(0).setCellStyle(styleList.get(1));
                }
                //返工
                if(feedbackInfo.getBsEvent4() == 1){
                    sheet.getRow(20 + addRows).getCell(8).setCellStyle(styleList.get(0));
                }else{
                    sheet.getRow(20 + addRows).getCell(8).setCellStyle(styleList.get(1));
                }
                //客户罚款
                if(feedbackInfo.getBsEvent10() == 1){
                    sheet.getRow(22 + addRows).getCell(0).setCellStyle(styleList.get(0));
                }else{
                    sheet.getRow(22 + addRows).getCell(0).setCellStyle(styleList.get(1));
                }
                //货物退回供应商
                if(feedbackInfo.getBsEvent5() == 1){
                    sheet.getRow(22 + addRows).getCell(8).setCellStyle(styleList.get(0));
                }else{
                    sheet.getRow(22 + addRows).getCell(8).setCellStyle(styleList.get(1));
                }
                //货物参杂质
                if(feedbackInfo.getBsEvent11() == 1){
                    sheet.getRow(24 + addRows).getCell(0).setCellStyle(styleList.get(0));
                }else{
                    sheet.getRow(24 + addRows).getCell(0).setCellStyle(styleList.get(1));
                }
                //客户生产线断货
                if(feedbackInfo.getBsEvent6() == 1){
                    sheet.getRow(24 + addRows).getCell(8).setCellStyle(styleList.get(0));
                }else{
                    sheet.getRow(24 + addRows).getCell(8).setCellStyle(styleList.get(1));
                }
                //箱内货品数量不符
                if(feedbackInfo.getBsEvent12() == 1){
                    sheet.getRow(26 + addRows).getCell(0).setCellStyle(styleList.get(0));
                }else{
                    sheet.getRow(26 + addRows).getCell(0).setCellStyle(styleList.get(1));
                }
                //其他可能的费用（附件）
                if(feedbackInfo.getBsEvent14() == 1){
                    sheet.getRow(28 + addRows).getCell(0).setCellStyle(styleList.get(0));
                }else{
                    sheet.getRow(28 + addRows).getCell(0).setCellStyle(styleList.get(1));
                }
                //3.产生质量扣款的情况 结束

                //4.审批人信息 开始
                int addRowsPerson = 0;   //第四部分怎加的行数，默认有3行
                List<FeedbackHandler> feedbackHandlerList = feedbackHandlerDao.findByBsIsDelAndBsFeedbackIdAndBsType(BooleanStateEnum.FALSE.intValue(), bsFeedbackId, 0);
                //设置行高，默认存在3行
                sheet.getRow(30 + addRows).setHeightInPoints((float) 15.8);
                sheet.getRow(31 + addRows).setHeightInPoints((float) 15.8);
                sheet.getRow(32 + addRows).setHeightInPoints((float) 15.8);
                sheet.getRow(33 + addRows).setHeightInPoints((float) 15.8);
                sheet.getRow(34 + addRows).setHeightInPoints(9);
                //4.1审批人少于等于3人
                if(feedbackHandlerList.size() > 0 && feedbackHandlerList.size() <= 3){
                    for(int i = 0; i < feedbackHandlerList.size(); i++){
                        String approvedOrg = "";  //审批人所在部门
                        String approvedName = "";  //审批人姓名
                        String approvedRole = "";  //审批人职位
                        String approvedDate = "";  //审批日期
                        FeedbackHandler feedbackHandler = feedbackHandlerList.get(i);
                        if(feedbackHandler != null && feedbackHandler.getBsHandlerId() != null && feedbackHandler.getHandlerBy() != null){
                            approvedOrg = (feedbackHandler.getHandlerBy().getOrg() != null) ? (feedbackHandler.getHandlerBy().getOrg().getBsName() + "：") : ("");
                            approvedName = feedbackHandler.getHandlerBy().getBsName();
                            //通过角色表与用户表的联系找出第一个角色名称作为审批人职位
                            List<SearchFilter> filters =new ArrayList<SearchFilter>();
                            filters.add(new SearchFilter("pkUser", SearchFilter.Operator.EQ, feedbackHandler.getBsHandlerId()));
                            Specification<SysUserRolesAgg> spec = Specifications.where(BaseOprService.and(filters, SysUserRolesAgg.class));
                            List<SysUserRolesAgg> userRolesAggList = sysUserRolesAggDao.findAll(spec);
                            if(userRolesAggList.size() > 0){
                                userRolesAggList.get(0).getPkRole();
                                SysRole sysRole = sysRoleDao.findOne(userRolesAggList.get(0).getPkRole());
                                if(sysRole != null){
                                    approvedRole = sysRole.getBsName();
                                }
                            }
                            approvedDate = simpleDateFormat.format(feedbackHandler.getBsCreatedTime());
                        }
                        //数据写入Excel表
                        sheet.getRow(31 + addRows + i).getCell(1).setCellValue(approvedOrg);
                        sheet.getRow(31 + addRows + i).getCell(3).setCellValue(approvedName);
                        sheet.getRow(31 + addRows + i).getCell(8).setCellValue(approvedRole);
                        sheet.getRow(31 + addRows + i).getCell(14).setCellValue(approvedDate);
                    }
                }

                //4.2审批人多于3人
                if(feedbackHandlerList.size() > 3){
                    addRowsPerson = feedbackHandlerList.size() - 3;
                    //向下移动
                    sheet.shiftRows(34 + addRows, 35 + addRows, addRowsPerson, false, false);
                    //创建行
                    for(int i = 34 + addRows; i < 34 + addRows + addRowsPerson; i++){
                        Row createRow = sheet.createRow(i);
                        for(int j = 0;j < 17; j++){
                            createRow.createCell(j);
                        }
                    }
                    //设置行高
                    sheet.getRow(34 + addRows + addRowsPerson).setHeightInPoints(9);
                    //审批格式
                    int type1 = sheet.getRow(31 + addRows).getCell(1).getCellType();
                    CellStyle style1 = sheet.getRow(31 + addRows).getCell(1).getCellStyle();
                    int type2 = sheet.getRow(31 + addRows).getCell(3).getCellType();
                    CellStyle style2 = sheet.getRow(31 + addRows).getCell(3).getCellStyle();
                    for(int i = 0; i < addRowsPerson; i++){
                        //设置行高
                        sheet.getRow(34 + addRows + i).setHeightInPoints((float) 15.8);
                        //设置格式
                        sheet.getRow(34 + addRows + i).getCell(1).setCellType(type1);
                        sheet.getRow(34 + addRows + i).getCell(1).setCellStyle(style1);
                        sheet.getRow(34 + addRows + i).getCell(3).setCellType(type2);
                        sheet.getRow(34 + addRows + i).getCell(3).setCellStyle(style2);
                        sheet.getRow(34 + addRows + i).getCell(8).setCellType(type2);
                        sheet.getRow(34 + addRows + i).getCell(8).setCellStyle(style2);
                        sheet.getRow(34 + addRows + i).getCell(14).setCellType(type2);
                        sheet.getRow(34 + addRows + i).getCell(14).setCellStyle(style2);
                    }
                    //添加数据
                    for(int i = 0; i < feedbackHandlerList.size(); i++){
                        String approvedOrg = "";  //审批人所在部门
                        String approvedName = "";  //审批人姓名
                        String approvedRole = "";  //审批人职位
                        String approvedDate = "";  //审批日期
                        FeedbackHandler feedbackHandler = feedbackHandlerList.get(i);
                        if(feedbackHandler != null && feedbackHandler.getBsHandlerId() != null && feedbackHandler.getHandlerBy() != null){
                            approvedOrg = (feedbackHandler.getHandlerBy().getOrg() != null) ? (feedbackHandler.getHandlerBy().getOrg().getBsName() + "：") : ("");
                            approvedName = feedbackHandler.getHandlerBy().getBsName();
                            //通过角色表与用户表的联系找出第一个角色名称作为审批人职位
                            List<SearchFilter> filters =new ArrayList<SearchFilter>();
                            filters.add(new SearchFilter("pkUser", SearchFilter.Operator.EQ, feedbackHandler.getBsHandlerId()));
                            Specification<SysUserRolesAgg> spec = Specifications.where(BaseOprService.and(filters, SysUserRolesAgg.class));
                            List<SysUserRolesAgg> userRolesAggList = sysUserRolesAggDao.findAll(spec);
                            if(userRolesAggList.size() > 0){
                                userRolesAggList.get(0).getPkRole();
                                SysRole sysRole = sysRoleDao.findOne(userRolesAggList.get(0).getPkRole());
                                if(sysRole != null){
                                    approvedRole = sysRole.getBsName();
                                }
                            }
                            approvedDate = simpleDateFormat.format(feedbackHandler.getBsCreatedTime());
                        }
                        //数据写入Excel表
                        sheet.getRow(31 + addRows + i).getCell(1).setCellValue(approvedOrg);
                        sheet.getRow(31 + addRows + i).getCell(3).setCellValue(approvedName);
                        sheet.getRow(31 + addRows + i).getCell(8).setCellValue(approvedRole);
                        sheet.getRow(31 + addRows + i).getCell(14).setCellValue(approvedDate);
                    }
                    //合并单元格
                    for(int i = addRowsPerson - 1; i >= 0; i--) {
                        //region1
                        CellRangeAddress region1 = new CellRangeAddress(34 + addRows + i, 34 + addRows + i, 14, 16);
                        sheet.addMergedRegion(region1);
                        RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region1, sheet, workbook);
                        //region2
                        CellRangeAddress region2 = new CellRangeAddress(34 + addRows + i, 34 + addRows + i, 8, 12);
                        sheet.addMergedRegion(region2);
                        RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region2, sheet, workbook);
                        //region3
                        CellRangeAddress region3 = new CellRangeAddress(34 + addRows + i, 34 + addRows + i, 3, 6);
                        sheet.addMergedRegion(region3);
                        RegionUtil.setBorderBottom(XSSFCellStyle.BORDER_THIN, region3, sheet, workbook);
                    }
                }
                //4.审批人信息 结束
            }

            response.reset();
            response.setContentType("multipart/form-data;charset=utf-8");
            String fileName = URLEncoder.encode("客诉退款报告", "UTF-8")+ ".xlsx";
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            System.out.println("导出成功！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ApiResponseResult.success();
    }

    private static String parseFormula(String pPOIFormula) {
        final String cstReplaceString = "ATTR(semiVolatile)"; //$NON-NLS-1$
        StringBuffer result;
        int index;

        result = new StringBuffer();
        index = pPOIFormula.indexOf(cstReplaceString);
        if (index >= 0) {
            result.append(pPOIFormula.substring(0, index));
            result.append(pPOIFormula.substring(index + cstReplaceString.length()));
        } else {
            result.append(pPOIFormula);
        }

        return result.toString();
    }

    /**
     * 获取Excel2007图片
     * @param sheetNum 当前sheet编号
     * @param sheet 当前sheet对象
     * @param workbook 工作簿对象
     * @return Map key:图片单元格索引（0_1_1）String，value:图片流PictureData
     */
    public static Map<String, PictureData> getSheetPictrues07(int sheetNum,
                                                              XSSFSheet sheet, XSSFWorkbook workbook) {
        Map<String, PictureData> sheetIndexPicMap = new HashMap<String, PictureData>();

        if(sheet.getRelations().size() > 0){
            for (POIXMLDocumentPart dr : sheet.getRelations()) {
                if (dr instanceof XSSFDrawing) {
                    XSSFDrawing drawing = (XSSFDrawing) dr;
                    List<XSSFShape> shapes = drawing.getShapes();
                    if(shapes.size() > 0){
                        for (XSSFShape shape : shapes) {
                            if(shape instanceof XSSFPicture){
                                XSSFPicture pic = (XSSFPicture) shape;
                                XSSFClientAnchor anchor = pic.getPreferredSize();
                                CTMarker ctMarker = anchor.getFrom();
                                String picIndex = String.valueOf(sheetNum) + "_"
                                        + ctMarker.getRow() + "_" + ctMarker.getCol();
                                sheetIndexPicMap.put(picIndex, pic.getPictureData());
                            }
                        }
                    }
                }
            }
        }

        return sheetIndexPicMap;
    }

    //退款样式
    public List<CellStyle> getStyle(XSSFWorkbook workbook){
        List<CellStyle> cellStyleList = new ArrayList<CellStyle>();
        Sheet sheet = workbook.getSheetAt(0);
        XSSFFont font = workbook.createFont();
        font.setFontName("宋体");   //字体名称
        font.setFontHeightInPoints((short) 10);   //字体大小

        //0.边框加粗
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setBorderTop(CellStyle.BORDER_MEDIUM);   //上边框
        cellStyle.setBorderRight(CellStyle.BORDER_MEDIUM);  //右边框
        cellStyle.setBorderBottom(CellStyle.BORDER_MEDIUM);  //下边框
        cellStyle.setBorderLeft(CellStyle.BORDER_MEDIUM);   //左边框
        cellStyleList.add(cellStyle);

        //1.边框加粗，交叉线
        CellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1 = sheet.getRow(16).getCell(8).getCellStyle();
        cellStyleList.add(cellStyle1);

        return cellStyleList;
    }

}
