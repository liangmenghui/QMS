package com.unind.base.web.mrp;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

import com.unind.base.web.mrp.domain.ItemInfoVO;
import com.unind.base.web.mrp.domain.MRPInfoVO;
import com.unind.base.web.mrp.domain.ShipmentPlanVO;

public final class ShipmentCalc {

	public static List<MRPInfoVO> calc(ItemInfoVO itemInfoVO) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2017, 10, 7, 0, 0, 0);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(2017, 10, 7, 0, 0, 0);
		calendar2.set(Calendar.MONTH, calendar2.get(Calendar.MONTH) + PLAN_MONTH);
		Date endDate = calendar2.getTime();
		Date date = calendar.getTime();
		List<MRPInfoVO> mrpInfoVOList = new ArrayList<MRPInfoVO>();
		MRPInfoVO prev = null;
		while (true) {
			if(date.compareTo(endDate)>=0) {
				break;
			}
			MRPInfoVO mrpInfoVO = new MRPInfoVO();
			mrpInfoVO.setDate(new Date(date.getTime()));
//			mrpInfoVO.setNumOfReq(0d);
			mrpInfoVO.setPrev(prev);
			if(null==prev) {
				//预计库存=期初库存
				mrpInfoVO.setFutureInv(itemInfoVO.getNumOfInv());
				mrpInfoVO.setNumOfWeek(3.3d);
			}else {
				prev.setNext(mrpInfoVO);
			}
			mrpInfoVOList.add(mrpInfoVO);
			prev = mrpInfoVO;

			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
			date = calendar.getTime();
		}
		return mrpInfoVOList;
	}

	public static void main(String[] args) throws Exception {
		readXls();
		/*List<ItemInfoVO> itemList = initItem();
		if(null == itemList || itemList.size() == 0) {
			return;
		}
		for (ItemInfoVO itemInfoVO : itemList) {
			List<MRPInfoVO> mrpInfoVOList = calc(itemInfoVO);
			itemInfoVO.setMrpInfoVOList(mrpInfoVOList);
		}

		//设置需求
		System.out.println("--------------------");
		itemList.get(0).getMrpInfoVOList().get(1).setNumOfReq(8160d);
		System.out.println("--------------------");*/
	}
	
	
	
	

	public static List<ItemInfoVO> initItem() {
		List<ItemInfoVO> itemList = new ArrayList<ItemInfoVO>();
		for (String[] strs : items) {
			ItemInfoVO itemVO = new ItemInfoVO();
			itemVO.setItemNo(strs[0]);
			itemVO.setComment(strs[1]);
			itemVO.setPeriodOfProd(Integer.parseInt(strs[2]));
			itemVO.setPartNo(strs[3]);
			itemVO.setMinShipment(Double.parseDouble(strs[4]));
			itemVO.setPeriodOfDelivery(Integer.parseInt(strs[5]));
			itemVO.setNumOfInv(Double.parseDouble(strs[6]));
			itemVO.setNumOfNotRecivedPO(Double.parseDouble(strs[7]));
			itemVO.setOutOfDateInv(Double.parseDouble(strs[8]));
			itemList.add(itemVO);
		}
		return itemList;
	}
	private final static int PLAN_MONTH = 6;

	private final static Integer[][] NUM_OF_REQ = {
		{2, 8160, 1296, 384, 360, 2240, 1344, 6272, 0, 40320, 0},
		{7, 7980, 1296, 384, 360, 896, 896, 12096, 0, 68040, 0},
		{14, 3360, 864, 384, 360, 0, 448, 6720, 896, 35280, 0},
		
		{21, 6300, 1728, 0, 0, 0, 1344, 6720, 896, 35280, 0},
		{28, 7140, 1728, 384, 360, 0, 896, 6720, 896, 35280, 0},
		{35, 7140, 1728, 384, 360, 0, 448, 6720, 896, 35280, 0},
		{42, 6300, 1296, 384, 720, 896, 896, 6720, 896, 35280, 0},
		{49, 2520, 432, 384, 360, 448, 448, 2688, 896, 35280, 0},
		{56, 4200, 864, 0, 360, 896, 448, 4480, 896, 35280, 0},
	};

	private final static String[][] items = {
		{"1010100065","W10786161不锈钢滚花把手","12","W10786161","288","36","54000","0","0"},
		{"1010100068","W10786142不锈钢拉丝钝化把手","12","W10786142","432","36","5760","10080","0"},
		{"1010100070","W10788019不锈钢拉丝把手","12","W10788019","0","36","2304","1536","0"},
		{"1010100078","W10833961不锈钢拉丝把手（座子为锌压铸）","12","W10833961","0","36","2160","1440","0"},
		{"1010500043","W11024062铝合金黑色喷涂把手","12","W11024062","0","36","4928","9300","0"},
		{"1010500044","W11024063铝合金白色喷涂把手","12","W11024063","0","36","9016","33792","0"},
		{"1010500045","W10874342铝合金拉丝阳极把手","12","W10874342","0","36","54208","41505","0"},
		{"1010500046","W10874349铝合金拉丝镜面氧化把手","12","W10874349","0","36","1344","3160","0"},
		{"1060100011","W10544004不锈钢抛光弹片","12","W10544004","840","36","404040","0","0"},
		{"1060400013","W10584473镀锌板无表面处理阀门支架普通冲压件","12","W10584473","0","36","0","0","0"}
	};

	private static FormulaEvaluator evaluator;
	public final static List<ItemInfoVO> readXls() throws Exception {
		String filePath = "F:\\其它\\test.xls";
		InputStream in = new FileInputStream(new File(filePath));
		HSSFWorkbook workbook = new HSSFWorkbook(in);
		evaluator = workbook.getCreationHelper().createFormulaEvaluator();
		HSSFSheet sheet = workbook.getSheetAt(0);
		int numOfRows = sheet.getLastRowNum();

		ItemInfoVO itemInfoVO;

		HSSFRow row1 = sheet.getRow(0);
		HSSFRow row2 = sheet.getRow(1);
		HSSFRow row3 = sheet.getRow(2);
		HSSFRow row4 = sheet.getRow(3);
		int numOfCols = row1.getLastCellNum();
		int cols = Math.round((numOfCols-1) / 9);
		List<ItemInfoVO> itemInfoVOList = new ArrayList<ItemInfoVO>();
		for (int i = 0; i < cols; i++) {
			itemInfoVO = new ItemInfoVO();
			String itemNo = extractCellValue(row1.getCell(i*9 + 1));
			if(StringUtils.isEmpty(itemNo)) {
				continue;
			}
			itemInfoVO.setItemNo(itemNo);

			String comment = extractCellValue(row2.getCell(i*9 + 1));
			itemInfoVO.setComment(comment);

			String periodOfProd = extractCellValue(row2.getCell(i*9 + 6 + 1));
			if(StringUtils.isEmpty(periodOfProd)) {
				periodOfProd = "0";
			}
			itemInfoVO.setPeriodOfProd(Integer.parseInt(periodOfProd));

			String partNo = extractCellValue(row3.getCell(i*9 + 1));
			if(StringUtils.isEmpty(partNo)) {
				partNo = "";
			}
			itemInfoVO.setPartNo(partNo);

			String minShipment = extractCellValue(row3.getCell(i*9 + 3 + 1));
			if(StringUtils.isEmpty(minShipment)) {
				minShipment = "0";
			}
			itemInfoVO.setMinShipment(Double.parseDouble(minShipment));

			String periodOfDelivery = extractCellValue(row3.getCell(i*9 + 6 + 1));
			if(StringUtils.isEmpty(periodOfDelivery)) {
				periodOfDelivery = "0";
			}
			itemInfoVO.setPeriodOfDelivery(Integer.parseInt(periodOfDelivery));

			String numOfInv = extractCellValue(row4.getCell(i*9 + 1));
			if(StringUtils.isEmpty(numOfInv)) {
				numOfInv = "0";
			}
			itemInfoVO.setNumOfInv(Double.parseDouble(numOfInv));

			String numOfNotReceivedPO = extractCellValue(row4.getCell(i*9 + 3 + 1));
			if(StringUtils.isEmpty(numOfNotReceivedPO)) {
				numOfNotReceivedPO = "0";
			}
			itemInfoVO.setNumOfNotRecivedPO(Double.parseDouble(numOfNotReceivedPO));

			String outOfDateInv = extractCellValue(row4.getCell(i*9 + 6 + 1));
			if(StringUtils.isEmpty(outOfDateInv)) {
				outOfDateInv = "0";
			}
			itemInfoVO.setOutOfDateInv(Double.parseDouble(outOfDateInv));
			itemInfoVO.setMrpInfoVOList(new ArrayList<MRPInfoVO>());
			itemInfoVOList.add(itemInfoVO);
		}

		HSSFRow row;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		List<MRPInfoVO> prevs = new ArrayList<MRPInfoVO>();
		for (int i = 5; i < numOfRows; i++) {
			row = sheet.getRow(i);
			for (int j = 0; j < cols; j++) {
				if(i==5) {
					prevs.add(null);
				}
				MRPInfoVO mrpInfoVO = new MRPInfoVO();
				String date = extractCellValue(row.getCell(0));
				if(StringUtils.isEmpty(date)) {
					System.out.println("rownum:"+(i+1)+",cellnum: "+(j+1)+"; date is empty!");
					break;
				}
				mrpInfoVO.setDate(format.parse(date.trim()));
				calendar.setTime(mrpInfoVO.getDate());
				mrpInfoVO.setDay(calendar.getFirstDayOfWeek() + 1);
//				String day = extractCellValue(row.getCell(j * 9 + 1));
//				if(StringUtils.isEmpty(day)) {
//					mrpInfoVO.setDay(calendar.getFirstDayOfWeek());
//				}else {
//					mrpInfoVO.setDay(Integer.parseInt(day.trim()));
//				}

				String dateOfShipment = extractCellValue(row.getCell(j * 9 + 3));
				if(StringUtils.isNotEmpty(dateOfShipment) && StringUtils.isNotEmpty(dateOfShipment.trim())) {
					mrpInfoVO.setDateOfShipment(format.parse(dateOfShipment));
				}

				String containerNo = extractCellValue(row.getCell(j * 9 + 4));
				mrpInfoVO.setContainerNo(containerNo);

				String numOfShipment = extractCellValue(row.getCell(j * 9 + 5));
				if(StringUtils.isEmpty(numOfShipment)) {
					numOfShipment = "0";
				}
				mrpInfoVO.setNumOfShipment(Double.parseDouble(numOfShipment));
//				String futureInv = extractCellValue(row.getCell(6));
//				System.out.print(futureInv);
//				if(StringUtils.isEmpty(futureInv)) {
//					futureInv = "0";
//				}
//				mrpInfoVO.setFutureInv(Double.parseDouble(futureInv));

//				String numOfWeek = extractCellValue(row.getCell(j * 9 + 7));
//				if(StringUtils.isEmpty(numOfWeek)) {
//					numOfWeek = "0";
//				}
//				mrpInfoVO.setNumOfWeek(Double.parseDouble(numOfWeek));

				ItemInfoVO vo = itemInfoVOList.get(j);
				mrpInfoVO.setItemInfoVO(vo);

				String numOfReq = extractCellValue(row.getCell(j * 9 + 2));
				if(StringUtils.isEmpty(numOfReq)) {
					numOfReq = "0";
				}
				mrpInfoVO.setNumOfReq(Double.parseDouble(numOfReq));

				mrpInfoVO.setPrev(prevs.get(j));
				if(null==prevs.get(j)) {
					//预计库存=期初库存
					mrpInfoVO.setFutureInv(vo.getNumOfInv());
					mrpInfoVO.setNumOfWeek(0.0d);
				}else {
					mrpInfoVO.setFutureInv(new BigDecimal(prevs.get(j).getFutureInv()).add(new BigDecimal(prevs.get(j).getNumOfShipment()).subtract(new BigDecimal(mrpInfoVO.getNumOfReq()))).doubleValue());
					prevs.get(j).setNext(mrpInfoVO);
				}
				prevs.set(j, mrpInfoVO);
				vo.getMrpInfoVOList().add(mrpInfoVO);
			}
		}
		itemInfoVOList.get(1).getMrpInfoVOList().get(0).setNumOfShipment(0d);
		for (ItemInfoVO vo : itemInfoVOList) {
			//初始化库存周期
			vo.init();
			ShipmentPlanVO shipmentPlanVO = new ShipmentPlanVO(vo.getMrpInfoVOList().get(0));
			//计算需求日期
			shipmentPlanVO.copy().calc();
		}
		return itemInfoVOList;
	}

	public final static String extractCellValue(HSSFCell cell) {
//		cell.setCellType(Cell.CELL_TYPE_STRING);
		switch(cell.getCellType()) {
			case Cell.CELL_TYPE_FORMULA:
				return getCellValue(evaluator.evaluate(cell));
			case Cell.CELL_TYPE_BLANK:
				return "";
			case Cell.CELL_TYPE_NUMERIC:
				return String.valueOf(new BigDecimal(cell.getNumericCellValue()));
			case Cell.CELL_TYPE_STRING:
				return cell.getStringCellValue();
			default:
				return cell.getStringCellValue();
		}
	}

	private static String getCellValue(CellValue cell) {
		String cellValue = null;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			cellValue = cell.getStringValue();
			break;

		case Cell.CELL_TYPE_NUMERIC:
			cellValue = String.valueOf(cell.getNumberValue());
			break;
		case Cell.CELL_TYPE_FORMULA:
			break;
		default:
			break;
		}
		return cellValue;
	}
}
