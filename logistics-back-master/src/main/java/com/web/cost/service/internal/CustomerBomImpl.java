package com.web.cost.service.internal;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.app.base.data.DataGrid;
import com.system.file.dao.FsFileDao;
import com.system.user.dao.SysUserDao;
import com.system.user.entity.SysUser;
import com.utils.BaseService;
import com.utils.UserUtil;
import com.utils.enumeration.SettingsStateEnum;
import com.web.cost.dao.*;
import com.web.cost.entity.*;
import com.web.materiel.dao.MaterielCategoryK3Dao;
import com.web.materiel.entity.MaterielCategoryK3;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.base.data.ApiResponseResult;
import com.system.file.entity.FsFile;
import com.system.file.service.FileService;
import com.utils.CompareString;
import com.utils.enumeration.BasicStateEnum;
import com.web.cost.service.CustomerBomService;
import com.web.materiel.dao.MaterielInfoK3Dao;
import com.web.materiel.entity.MaterielInfoK3;
import com.web.settings.dao.SettingDao;
import com.web.settings.entity.Setting;
import com.web.supplier.entity.SupplierScoreRule;

import org.springside.modules.persistence.SearchFilter;

/**
 * 客户BOM成本预估
 *
 */
@Service(value = "CustomerBomService")
@Transactional(propagation = Propagation.REQUIRED)
public class CustomerBomImpl implements CustomerBomService {

    @Autowired
    private FileService fileService;
    @Autowired
    private CustomerBomDao customerBomDao;
    @Autowired
    private BomParamsDao bomParamsDao;
    @Autowired
    private MaterielInfoK3Dao materielInfoK3Dao;
    @Autowired
    private CustomerBomMatchDao customerBomMatchDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private FsFileDao fsFileDao;
    @Autowired
    private SettingDao settingDao;
    @Autowired
    private MaterielCategoryK3Dao materielCategoryK3Dao;

    /**
     * 上传客户BOM
     * @param file
     * @param startRow
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ApiResponseResult importBom(MultipartFile file, Integer startRow) throws Exception {
        //文件和起始行数不能为空
        if(file == null || file.isEmpty()) {
            return ApiResponseResult.failure("上传文件不能为空！");
        }
        if(startRow == null){
            return ApiResponseResult.failure("起始行数不能为空！");
        }
        SysUser currUser = UserUtil.getCurrUser();  //获取当前用户

        //1.上传文件
        FsFile fsFile = new FsFile();
        ApiResponseResult result = fileService.upload(fsFile, file);
        if(!result.isResult()){
            return ApiResponseResult.failure("文件上传失败！请重新上传！");
        }
        fsFile = (FsFile) result.getData();
        if(fsFile.getId() == null){
            return ApiResponseResult.failure("上传文件不存在！请重新上传！");
        }

        //2.获取Excel数据
        Long fileId = fsFile.getId();
        Workbook wb = null;
        String fileName = file.getOriginalFilename();
        //判断excel版本
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            //xlsx版本
            wb = new XSSFWorkbook(file.getInputStream());
        } else {
            //xls版本
            wb = new HSSFWorkbook(file.getInputStream());
        }

        //获取第一个sheet
        Sheet sheet = wb.getSheetAt(0);
        List<CustomerBom> customerBomList = new ArrayList<CustomerBom>();
        //从 startRow-1 行开始读取（表头和表数据都要读取）
        for (int i = startRow - 1; i < sheet.getLastRowNum(); i++){
            Row row = sheet.getRow(i);
            if ((row.getCell(0) == null || row.getCell(0).getCellType() == Cell.CELL_TYPE_BLANK)
                    && (row.getCell(1) == null || row.getCell(1).getCellType() == Cell.CELL_TYPE_BLANK)) {
                break;
            }

            CustomerBom customerBom = new CustomerBom();
            List<String> list = new ArrayList<String>();

            //3.数据库定义了20个字段来存储BOM数据
            for(int j = 0; j < 20; j++){
                String prop = "";
                if(row.getCell(j) != null){
                    int cellType = row.getCell(j).getCellType();
                    if(cellType == Cell.CELL_TYPE_NUMERIC){
                        Long propTemp = (long) row.getCell(j).getNumericCellValue();
                        prop = propTemp.toString();
                    }
                    if(cellType == Cell.CELL_TYPE_STRING){
                        String propTemp = row.getCell(j).getStringCellValue();
                        prop = StringUtils.isNotEmpty(propTemp) ? propTemp.trim() : "";
                    }
                    if(cellType == Cell.CELL_TYPE_FORMULA){
                        try{
                            String propTemp = row.getCell(j).getStringCellValue();
                            prop = StringUtils.isNotEmpty(propTemp) ? propTemp.trim() : "";
                        }catch (IllegalStateException e){
                            try{
                                //Boolean abc = row.getCell(j).getBooleanCellValue();
                                Long propTemp = (long) row.getCell(j).getNumericCellValue();
                                prop = propTemp.toString();
                            }catch (IllegalStateException ex){
                                //System.out.println("行数：" + i +","+ j);
                                prop = "";
                            }
                        }
                    }
                }
                list.add(prop);
            }

            //4.将获取到的每一行数据list封装到CustomerBom对象中
            customerBom.setCreatedTime(new Date());
            customerBom.setPkCreatedBy((currUser!=null) ? (currUser.getId()) : null);  //创建时，创建人和修改人信息一致
            customerBom.setCreatedName((currUser!=null) ? (currUser.getUserName()) : null);
            customerBom.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
            customerBom.setModifiedName((currUser!=null) ? (currUser.getUserName()) : null);
            customerBom.setFileId(fileId);
            customerBom.setFileName(fsFile.getBsName());
            customerBom.setStartRow(startRow);
            //如果是表头，则bomType为1
            if(i == startRow - 1){
                customerBom.setBomType(1);
            }else{
                customerBom.setBomType(0);
            }
            customerBom.setBomProp(list.get(0));
            customerBom.setBomProp2(list.get(1));
            customerBom.setBomProp3(list.get(2));
            customerBom.setBomProp4(list.get(3));
            customerBom.setBomProp5(list.get(4));
            customerBom.setBomProp6(list.get(5));
            customerBom.setBomProp7(list.get(6));
            customerBom.setBomProp8(list.get(7));
            customerBom.setBomProp9(list.get(8));
            customerBom.setBomProp10(list.get(9));
            customerBom.setBomProp11(list.get(10));
            customerBom.setBomProp12(list.get(11));
            customerBom.setBomProp13(list.get(12));
            customerBom.setBomProp14(list.get(13));
            customerBom.setBomProp15(list.get(14));
            customerBom.setBomProp16(list.get(15));
            customerBom.setBomProp17(list.get(16));
            customerBom.setBomProp18(list.get(17));
            customerBom.setBomProp19(list.get(18));
            customerBom.setBomProp20(list.get(19));
            customerBomList.add(customerBom);
        }

        //5.保存数据
        customerBomDao.saveAll(customerBomList);
        return ApiResponseResult.success("上传文件成功！").data(fsFile);
    }

    //匹配客户BOM所有K3物料数据
    private List<CustomerBom> matchK3Bom(List<CustomerBom> customerBomList, String fileId, SysUser currUser, float matchNum, Integer topNum){
        if(customerBomList == null){
            return customerBomList;
        }

        //1.获取K3物料数据
        List<MaterielInfoK3> materielInfoK3List =  materielInfoK3Dao.findAllByFNumberIsNotNull();
        CompareString lt = new CompareString();

        //2.获取表头
        List<CustomerBom> listHeader = customerBomList.stream().filter(s -> s.getBomType() == 1).collect(Collectors.toList());
        if(listHeader.size() <= 0){
            return customerBomList;
        }

        //3.获取表数据
        List<CustomerBom> listBody = customerBomList.stream().filter(s -> s.getBomType() == 0).collect(Collectors.toList());

        //4.获取客户BOM参数数据
        List<BomParams> bomParamsList = bomParamsDao.findByIsDelAndFileIdOrderByIdDesc(BasicStateEnum.FALSE.intValue(), Long.parseLong(fileId));
        if(bomParamsList.size() <= 0){
            return customerBomList;
        }
        BomParams bomParams = bomParamsList.get(0);
        if(bomParams == null){
            return customerBomList;
        }

        //5.通过FileId删除关联的CustomerBomMatch数据信息，所有物料重新匹配
        List<CustomerBomMatch> customerBomMatchList = customerBomMatchDao.findByIsDelAndFileId(BasicStateEnum.FALSE.intValue(), Long.parseLong(fileId));
        if(customerBomMatchList.size() > 0){
            for(CustomerBomMatch customerBomMatch : customerBomMatchList){
                customerBomMatch.setIsDel(BasicStateEnum.TRUE.intValue());
                customerBomMatch.setModifiedTime(new Date());
                customerBomMatch.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
                customerBomMatch.setModifiedName((currUser!=null) ? (currUser.getUserName()) : null);
            }
            customerBomMatchDao.saveAll(customerBomMatchList);
        }

        //6.循环匹配数据
        for(int i = 0; i < listBody.size(); i++){
            CustomerBom customerBom = listBody.get(i);
            customerBom.setCheckStatus(0);
            customerBom.setCheckCode(null);
            //6.1获取BOM单个物料规格
            String modelValue = getModelValue(customerBom, listHeader.get(0), bomParams);

            List<CustomerBomMatch> mapList = new ArrayList<CustomerBomMatch>();
            List<MaterielInfoK3> mateList = materielInfoK3List;
            //6.2获取匹配值高于某值的物料
            //6.2.1通过“大类”筛选K3物料
            String mateCategory = customerBom.getMateCategory();
            if(StringUtils.isNotEmpty(mateCategory)){
                mateList = mateList.stream().filter(s -> s.getfNumber().startsWith(mateCategory+".")).collect(Collectors.toList());
            }
            //计算匹配率
            for(MaterielInfoK3 mk:mateList){
                float ratio = lt.getSimilarityRatio(modelValue, mk.getfModel());
                if(Float.compare(ratio, matchNum) >= 1){
                    CustomerBomMatch customerBomMatch = new CustomerBomMatch();
                    customerBomMatch.setCreatedTime(new Date());
                    customerBomMatch.setPkCreatedBy((currUser!=null) ? (currUser.getId()) : null);
                    customerBomMatch.setCusBomId(customerBom.getId());
                    customerBomMatch.setBomParamsId(bomParams.getId());
                    customerBomMatch.setFileId(customerBom.getFileId());
                    customerBomMatch.setRatio(ratio);
                    customerBomMatch.setfItemId(mk.getfItemId());;
                    customerBomMatch.setfNumber(mk.getfNumber());
                    customerBomMatch.setfName(mk.getfName());
                    customerBomMatch.setfModel(mk.getfModel());
                    //开始填充金额
                    customerBomMatch.setfPrice(mk.getfPrice());
                    customerBomMatch.setfAuxPriceDiscount(mk.getfAuxPriceDiscount());
                    customerBomMatch.setfPrice3MonthMax(mk.getfPrice3MonthMax());
                    customerBomMatch.setfAuxPrice3MonthMax(mk.getfAuxPrice3MonthMax());
                    customerBomMatch.setfPrice3MonthMin(mk.getfPrice3MonthMin());
                    customerBomMatch.setfAuxPrice3MonthMin(mk.getfAuxPrice3MonthMin());
                    //end
                    mapList.add(customerBomMatch);
                }
            }

            //6.3排序并获取匹配值前十的物料
            Collections.sort(mapList, new Comparator<CustomerBomMatch>(){
                @Override
                public int compare(CustomerBomMatch o1, CustomerBomMatch o2) {
                    if(Float.compare(o1.getRatio(), o2.getRatio()) < 0){
                        return 1;
                    }
                    if(Float.compare(o1.getRatio(), o2.getRatio()) == 0){
                        return 0;
                    }
                    return -1;
                    //return Float.compare(o1.getRatio(), o2.getRatio()) >= 0 ? -1 : 1;  //这种写法JDK7及以后的版本有问题
                }
            });
            mapList = mapList.subList(0, topNum);

            //6.4匹配率高于某值的物料自动选中
            Float settingValue = new Float(1);
            List<Setting> setting = settingDao.findByIsDelAndCode(BasicStateEnum.FALSE.intValue(), SettingsStateEnum.CUSTOMER_BOM_CHECK.stringValue());
            if(setting.size() > 0){
                settingValue = Float.valueOf(setting.get(0).getValue());
                if(mapList.size() > 0){
                    CustomerBomMatch o = mapList.get(0);
                    //比较匹配到的物料中匹配率最高的一位，如果它的匹配率大于设置里的匹配率，则自动选中；否则不选中
                    if(settingValue.compareTo(o.getRatio()) <= 0){
                        o.setCheckStatus(1);
                        //计算价格
                        BigDecimal qtyValue = new BigDecimal(0);  //物料数量
                        qtyValue = getQtyValue(customerBom, listHeader.get(0), bomParams);
                        customerBom = getPriceWithQty(o, customerBom, qtyValue);
                        customerBom.setCheckStatus(1);  //customerBom的状态为“选中”
                        customerBom.setCheckCode(o.getfNumber());  ////customerBom的CheckCode值为CustomerBomMatch选中的物料
                        customerBomDao.save(customerBom);
                    }
                }
            }

            //7.保存
            if(mapList.size() > 0){
                customerBomMatchDao.saveAll(mapList);
            }
        }

        return customerBomList;
    }

    /**
     * 获取客户BOM（匹配K3物料数据）
     * @param fileId
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ApiResponseResult getK3Bom(String standardCol, String categoryCol, String quantityCol, String packageCol,
			String makerCol, String splitList,String fileId) throws Exception {
        if(fileId == null){
            return ApiResponseResult.failure("文件ID不能为空！");
        }
        SysUser currUser = UserUtil.getCurrUser();  //获取当前用户
        
        //保存上传参数
  		this.saveBomParams(standardCol, categoryCol, quantityCol, packageCol, makerCol, splitList, fileId, currUser);
  		
        List<CustomerBom> customerBomList = customerBomDao.findByIsDelAndFileIdOrderByIdAsc(BasicStateEnum.FALSE.intValue(), Long.parseLong(fileId));
        int endColumn = 0;  //结束列

        //20190305-Shen 匹配客户BOM所有物料
        customerBomList = matchK3Bom(customerBomList, fileId, currUser, (float) 0.4, 10);

        //1.获取表头
        List<CustomerBom> listHeader = customerBomList.stream().filter(s -> s.getBomType() == 1).collect(Collectors.toList());
        if(listHeader.size() <= 0){
            return ApiResponseResult.failure("获取信息有误！");
        }
        CustomerBom oHeader = listHeader.get(0);
        List<String> headerList = new ArrayList<String>();
        headerList = bomPropToList(headerList, oHeader);   //将CustomerBom的BomProp属性按顺序存入List集合中

        //循环判断在那一列结束，获取结束列前的数据
        for(int i = 0; i < headerList.size(); i++){
            if(StringUtils.isNotEmpty(headerList.get(i))){
                endColumn++;
            }else{
                break;
            }
        }
        headerList = headerList.subList(0, endColumn);
        //20190218-截取表头List后，添加多一列，名称为“checkCode”（选中的物料号）
        headerList.add("checkCode");

        //2.获取表数据
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        List<CustomerBom> listBody = customerBomList.stream().filter(s -> s.getBomType() == 0).collect(Collectors.toList());
        for(int j = 0; j < listBody.size(); j++){
            List<String> resultList = new ArrayList<String>();
            CustomerBom oBody = listBody.get(j);
            resultList = bomPropToList(resultList, oBody);  //将CustomerBom的BomProp属性按顺序存入List集合中
            resultList = resultList.subList(0, endColumn);
            //20190218-截取表数据List后，添加多一列，存储选中的物料号
            resultList.add(oBody.getCheckCode());

            Map<String, String> mapBody = new HashMap<String, String>();
            mapBody.put("CusBomId", (oBody.getId()!=null?oBody.getId().toString():""));
            for(int k = 0; k < resultList.size(); k++){
                mapBody.put(headerList.get(k), resultList.get(k));
            }
            //20190114-fyx
            mapBody.put("checkStatus", (oBody.getCheckStatus()!=null?oBody.getCheckStatus().toString():"0"));
            mapList.add(mapBody);
        }

        //3.封装Map
        Map<String, Object> mapResult = new HashMap<String, Object>();
        mapResult.put("header", headerList);
        mapResult.put("results", mapList);

        return ApiResponseResult.success().data(mapResult);
    }

    //将CustomerBom的BomProp属性按顺序存入List集合中
    private List<String> bomPropToList(List<String> list, CustomerBom customerBom){
        if(customerBom != null){
            list.add(customerBom.getBomProp());
            list.add(customerBom.getBomProp2());
            list.add(customerBom.getBomProp3());
            list.add(customerBom.getBomProp4());
            list.add(customerBom.getBomProp5());
            list.add(customerBom.getBomProp6());
            list.add(customerBom.getBomProp7());
            list.add(customerBom.getBomProp8());
            list.add(customerBom.getBomProp9());
            list.add(customerBom.getBomProp10());
            list.add(customerBom.getBomProp11());
            list.add(customerBom.getBomProp12());
            list.add(customerBom.getBomProp13());
            list.add(customerBom.getBomProp14());
            list.add(customerBom.getBomProp15());
            list.add(customerBom.getBomProp16());
            list.add(customerBom.getBomProp17());
            list.add(customerBom.getBomProp18());
            list.add(customerBom.getBomProp19());
            list.add(customerBom.getBomProp20());
        }
        return list;
    }

    //保存上传参数
    private void saveBomParams(String standardCol, String categoryCol, String quantityCol, String packageCol,
                               String makerCol, String splitList,String fileId, SysUser currUser) throws Exception {
        //保存上传的参数

        List<BomParams> bomParamsList = bomParamsDao.findByFileId( Long.parseLong(fileId));
        
        BomParams bomParams = new BomParams();
        if(bomParamsList.size()>0){
            //如果存在就修改参数
            bomParams.setId(bomParamsList.get(0).getId());
            bomParams.setCreatedTime(bomParamsList.get(0).getCreatedTime());
            bomParams.setModifiedTime(new Date());
            bomParams.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
        }else{
            bomParams.setFileId(Long.parseLong(fileId));
            bomParams.setCreatedTime(new Date());
            bomParams.setPkCreatedBy((currUser!=null) ? (currUser.getId()) : null);
        }

        bomParams.setStandardCol(standardCol);
        bomParams.setCategoryCol(categoryCol);
        bomParams.setQuantityCol(quantityCol);
        bomParams.setPackageCol(packageCol);
        bomParams.setMakerCol(makerCol);
        bomParams.setCheckList(splitList);

        bomParamsDao.save(bomParams);
        ///--end-保存上传的参数
	}

    //根据fileId获取客户BOM参数配置
    private BomParams getBomParams(Long fileId) throws Exception {
    	BomParams bomParams = new BomParams();
        List<BomParams> bomParamsList = bomParamsDao.findByFileId(fileId);
        if(bomParamsList.size() == 0){
            return bomParams;
        }
        bomParams = bomParamsList.get(0);
        return bomParams;
    }

    /**
     * 获取物料匹配数据
     * @param cusBomId
     * @param matchNum
     * @param topNum
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ApiResponseResult getBomMatch(Long cusBomId,String mateCategory, float matchNum, Integer topNum) throws Exception{
        if(cusBomId == null){
            return ApiResponseResult.failure("客户BOM表ID不能为空！");
        }
        SysUser currUser = UserUtil.getCurrUser();  //获取当前用户
        
        //20190301-mateCategory匹配的大类如果不为空则保存到cusBom里面去
        if(mateCategory != null && mateCategory.length() != 0) {
        	customerBomDao.updateCategoryById(cusBomId, mateCategory);
        }
        
        //判断该物料是否已经匹配过数据
        //1.如果已经匹配过了，直接获取匹配过的数据返回
        List<CustomerBomMatch> customerBomMatchList = customerBomMatchDao.findByIsDelAndAndCusBomIdOrderByRatioDesc(BasicStateEnum.FALSE.intValue(), cusBomId);
        if(customerBomMatchList.size() > 0){
            return ApiResponseResult.success().data(customerBomMatchList);
        }

        //2.如果没有匹配过，则匹配数据，再返回
        ApiResponseResult result = getK3MatchResult(cusBomId, matchNum, topNum, currUser, mateCategory);

        return result;
    }

    //匹配数据，获取从K3物料表匹配到的前几条数据
    //匹配规则：先通过大类筛选出相应的物料，再按类别、品牌料号、规格这样的顺序进行匹配
    @Transactional(propagation= Propagation.REQUIRED)
    public ApiResponseResult getK3MatchResult(Long cusBomId, float matchNum, Integer topNum, SysUser currUser, String mateCategory){
        String modelValue = ""; //物料规格
        //1.获取客户BOM单个物料表数据
        CustomerBom customerBom = customerBomDao.findById((long) cusBomId);
        if(customerBom == null){
            return ApiResponseResult.failure("客户BOM不存在！");
        }
        //2.获取客户BOM表头
        List<CustomerBom> customerBomList = customerBomDao.findByIsDelAndFileIdAndBomType(BasicStateEnum.FALSE.intValue(), customerBom.getFileId(), 1);
        CustomerBom customerBom2 = customerBomList.get(0);
        if(customerBom2 == null){
            return ApiResponseResult.failure("客户BOM不存在！");
        }
        //3.获取客户BOM参数数据
        List<BomParams> bomParamsList = bomParamsDao.findByIsDelAndFileIdOrderByIdDesc(BasicStateEnum.FALSE.intValue(), customerBom.getFileId());
        if(bomParamsList.size() <= 0){
            return ApiResponseResult.failure("未匹配K3数据，请手动点击匹配！");
        }
        BomParams bomParams = bomParamsList.get(0);
        if(bomParams == null){
            return ApiResponseResult.failure("未匹配K3数据，请手动点击匹配！");
        }

        //4.获取BOM单个物料规格
        modelValue = getModelValue(customerBom, customerBom2, bomParams);

        //5.获取K3物料数据并且进行规格匹配
        List<CustomerBomMatch> mapList = new ArrayList<CustomerBomMatch>();
        List<MaterielInfoK3> materielInfoK3List =  materielInfoK3Dao.findAllByFNumberIsNotNull();
        //20190306-Shen 通过“大类”筛选K3物料
        if(StringUtils.isNotEmpty(mateCategory)){
            materielInfoK3List = materielInfoK3List.stream().filter(s -> s.getfNumber().startsWith(mateCategory+".")).collect(Collectors.toList());
        }
        CompareString lt = new CompareString();

        //获取匹配值高于某值的物料
        for(MaterielInfoK3 mk:materielInfoK3List){
            float ratio = lt.getSimilarityRatio(modelValue, mk.getfModel());
            if(Float.compare(ratio, matchNum) >= 1){
                CustomerBomMatch customerBomMatch = new CustomerBomMatch();
                customerBomMatch.setCreatedTime(new Date());
                customerBomMatch.setPkCreatedBy((currUser!=null) ? (currUser.getId()) : null);
                customerBomMatch.setCusBomId(cusBomId);
                customerBomMatch.setBomParamsId(bomParams.getId());
                customerBomMatch.setFileId(customerBom.getFileId());
                customerBomMatch.setRatio(ratio);
                customerBomMatch.setfItemId(mk.getfItemId());;
                customerBomMatch.setfNumber(mk.getfNumber());
                customerBomMatch.setfName(mk.getfName());
                customerBomMatch.setfModel(mk.getfModel());
                //开始填充金额
                customerBomMatch.setfPrice(mk.getfPrice());
                customerBomMatch.setfAuxPriceDiscount(mk.getfAuxPriceDiscount());
                customerBomMatch.setfPrice3MonthMax(mk.getfPrice3MonthMax());
                customerBomMatch.setfAuxPrice3MonthMax(mk.getfAuxPrice3MonthMax());
                customerBomMatch.setfPrice3MonthMin(mk.getfPrice3MonthMin());
                customerBomMatch.setfAuxPrice3MonthMin(mk.getfAuxPrice3MonthMin());
                //end
                mapList.add(customerBomMatch);
            }
        }

        //排序并获取匹配值前十的物料
        Collections.sort(mapList, new Comparator<CustomerBomMatch>(){
            @Override
            public int compare(CustomerBomMatch o1, CustomerBomMatch o2) {
                if(Float.compare(o1.getRatio(), o2.getRatio()) < 0){
                    return 1;
                }
                if(Float.compare(o1.getRatio(), o2.getRatio()) == 0){
                    return 0;
                }
                return -1;
                //return Float.compare(o1.getRatio(), o2.getRatio()) >= 0 ? -1 : 1;  //这种写法JDK7及以后的版本有问题
            }
        });
        mapList = mapList.subList(0, topNum);

        //6.匹配率高于某值的物料自动选中
        Float settingValue = new Float(1);
        List<Setting> setting = settingDao.findByIsDelAndCode(BasicStateEnum.FALSE.intValue(), SettingsStateEnum.CUSTOMER_BOM_CHECK.stringValue());
        if(setting.size() > 0){
            settingValue = Float.valueOf(setting.get(0).getValue());
            if(mapList.size() > 0){
                CustomerBomMatch o = mapList.get(0);
                //比较匹配到的物料中匹配率最高的一位，如果它的匹配率大于设置里的匹配率，则自动选中；否则不选中
                if(settingValue.compareTo(o.getRatio()) <= 0){
                    o.setCheckStatus(1);
                    //计算价格
                    BigDecimal qtyValue = new BigDecimal(0);  //物料数量
                    qtyValue = getQtyValue(customerBom, customerBom2, bomParams);
                    customerBom = getPriceWithQty(o, customerBom, qtyValue);
                    customerBom.setCheckStatus(1);  //customerBom的状态为“选中”
                    customerBom.setCheckCode(o.getfNumber());  ////customerBom的CheckCode值为CustomerBomMatch选中的物料
                    customerBomDao.save(customerBom);
                }
            }
        }

        //7.保存
        if(mapList.size() > 0){
            customerBomMatchDao.saveAll(mapList);
        }

        return ApiResponseResult.success().data(mapList);
    }

    //获取BOM单个物料规格
    private String getModelValue(CustomerBom customerBom, CustomerBom customerBom2, BomParams bomParams){
        //物料规格
        String modelValue = "";
        //物料规格的属性名称
        String modelName = "";

        //1.获取CustomerBom的所有属性名称
        Field[] fields = customerBom2.getClass().getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for(int i=0;i<fields.length;i++){
            fieldNames[i]=fields[i].getName();
        }

        //2.获取BomParams规格列的名称
        String standardCol = bomParams.getStandardCol();

        //3.获取物料规格的属性名称
        for(int i = 0; i < fieldNames.length; i++){
            Object object = getFieldValueByName(fieldNames[i], customerBom2);
            if(object != null && standardCol.equals(object.toString())){
                modelName = fieldNames[i];
                break;
            }
        }

        //4.获取物料规格
        Object object2 = getFieldValueByName(modelName, customerBom);
        modelValue = object2 != null ? object2.toString() : "";

        return modelValue;
    }

    //根据属性名获取属性值
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 获取客户BOM历史记录
     * @param pageRequest
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ApiResponseResult getBomList(String keyWord, PageRequest pageRequest) throws Exception{
        //查询客户BOM的表头
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("isDel", SearchFilter.Operator.EQ, BasicStateEnum.FALSE.intValue()));
        filters.add(new SearchFilter("bomType", SearchFilter.Operator.EQ, 1));
        List<SearchFilter> filters1 = new ArrayList<SearchFilter>();
        if(StringUtils.isNotEmpty(keyWord)){
            //可以根据文件名称、BOM编号、备注进行模糊匹配
            filters1.add(new SearchFilter("fileName", SearchFilter.Operator.LIKE, keyWord));
            filters1.add(new SearchFilter("bomCode", SearchFilter.Operator.LIKE, keyWord));
            filters1.add(new SearchFilter("remark", SearchFilter.Operator.LIKE, keyWord));
            filters1.add(new SearchFilter("createdName", SearchFilter.Operator.LIKE, keyWord));
            filters1.add(new SearchFilter("modifiedName", SearchFilter.Operator.LIKE, keyWord));
        }

        Specification<CustomerBom> spec = Specification.where(BaseService.and(filters, CustomerBom.class));
        Specification<CustomerBom> spec1 = spec.and(BaseService.or(filters1, CustomerBom.class));
        Page<CustomerBom> page = customerBomDao.findAll(spec1, pageRequest);

        List<CustomerBom> list = page.getContent();
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for(int i = 0; i < list.size(); i++){
            CustomerBom customerBom = list.get(i);
            Map<String, Object> map = new HashMap<>();
            map.put("id",  customerBom.getId());

            //时间格式化
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            String createdTime = customerBom.getCreatedTime() != null ? simpleDateFormat.format(customerBom.getCreatedTime()) : "";
            String modifiedTime = customerBom.getModifiedTime() != null ? simpleDateFormat.format(customerBom.getModifiedTime()) : "";
            map.put("createdTime", createdTime);
            map.put("modifiedTime", modifiedTime);
            map.put("fileId", customerBom.getFileId());
            map.put("fileName", customerBom.getFileName());
            map.put("remark", customerBom.getRemark());

            //如果创建人和修改人的名称为空，则根据ID获取创建人和修改人名称
            String createdName = customerBom.getCreatedName();
            String modifiedName = customerBom.getModifiedName();
            if(StringUtils.isEmpty(createdName) && customerBom.getPkCreatedBy() != null){
                List<SysUser> userCreated = sysUserDao.findById((long) customerBom.getPkCreatedBy());
                createdName = userCreated.get(0).getUserName();
            }
            if(StringUtils.isEmpty(modifiedName) && customerBom.getPkModifiedBy() != null){
                List<SysUser> userModified = sysUserDao.findById((long) customerBom.getPkModifiedBy());
                modifiedName = userModified.get(0).getUserName();
            }
            map.put("createdName", createdName);
            map.put("modifiedName", modifiedName);
            mapList.add(map);
        }

        return ApiResponseResult.success().data(DataGrid.create(mapList, (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
    }

    //客户BOM添加备注
    @Override
    @Transactional
    public ApiResponseResult addRemark(Long id, String remark) throws Exception{
        if(id == null){
            return ApiResponseResult.failure("记录ID不能为空！");
        }
        CustomerBom o = customerBomDao.findById((long) id);
        if(o == null){
            return ApiResponseResult.failure("客户BOM不存在！");
        }
        SysUser currUser = UserUtil.getCurrUser();

        o.setRemark(remark);
        o.setModifiedTime(new Date());
        o.setPkModifiedBy((currUser != null) ? currUser.getId() : null);
        o.setModifiedName((currUser != null) ? currUser.getUserName() : null);
        customerBomDao.save(o);
        return ApiResponseResult.success("修改成功！");
    }

    /**
     * 删除BOM
     * @param fileId
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ApiResponseResult delete(Long fileId) throws Exception{
        if(fileId == null){
            return ApiResponseResult.failure("文件ID不能为空！");
        }
        SysUser currUser = UserUtil.getCurrUser();  //获取当前用户

        //删除关联文件
        FsFile fsFile = fsFileDao.findById((long) fileId);
        if(fsFile != null){
            fsFile.setIsDel(BasicStateEnum.TRUE.intValue());
            fsFile.setModifiedTime(new Date());
            fsFile.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
            fsFileDao.save(fsFile);
        }

        //删除客户BOM表
        List<CustomerBom> customerBomList = customerBomDao.findByFileId(fileId);
        for(int i = 0; i < customerBomList.size(); i++){
            CustomerBom customerBom = customerBomList.get(i);
            customerBom.setIsDel(BasicStateEnum.TRUE.intValue());
            customerBom.setModifiedTime(new Date());
            customerBom.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
            customerBom.setModifiedName((currUser!=null) ? (currUser.getUserName()) : null);
            customerBomDao.save(customerBom);
        }

        //删除客户BOM的参数表
        List<BomParams> bomParamsList = bomParamsDao.findByFileId(fileId);
        for(int j = 0; j < bomParamsList.size(); j++){
            BomParams bomParams = bomParamsList.get(j);
            bomParams.setIsDel(BasicStateEnum.TRUE.intValue());
            bomParams.setModifiedTime(new Date());
            bomParams.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
            bomParamsDao.save(bomParams);
        }

        return ApiResponseResult.success("删除成功！");
    }

    /**
     * 获取客户BOM参数和列表
     * @param fileId
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ApiResponseResult getBomData(Long fileId) throws Exception{
        if(fileId == null){
            return ApiResponseResult.failure("文件ID不能为空！");
        }

        //1.获取客户BOM列表
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("isDel", SearchFilter.Operator.EQ, BasicStateEnum.FALSE.intValue()));
        filters.add(new SearchFilter("fileId", SearchFilter.Operator.EQ, fileId));
        Specification<CustomerBom> spec = Specification.where(BaseService.and(filters, CustomerBom.class));
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        List<CustomerBom> customerBomList = customerBomDao.findAll(spec, sort);

        int endColumn = 0;  //结束列

        //2.获取表头
        List<CustomerBom> listHeader = customerBomList.stream().filter(s -> s.getBomType() == 1).collect(Collectors.toList());
        if(listHeader.size() <= 0){
            return ApiResponseResult.failure("获取信息有误！");
        }
        CustomerBom oHeader = listHeader.get(0);
        List<String> headerList = new ArrayList<String>();
        headerList = bomPropToList(headerList, oHeader);   //将CustomerBom的BomProp属性按顺序存入List集合中

        //循环判断在那一列结束，获取结束列前的数据
        for(int i = 0; i < headerList.size(); i++){
            if(StringUtils.isNotEmpty(headerList.get(i))){
                endColumn++;
            }else{
                break;
            }
        }
        headerList = headerList.subList(0, endColumn);
        //20190218-截取表头List后，添加多一列，名称为“checkCode”（选中的物料号）
        headerList.add("checkCode");
        //20190301-截取表头List后，添加多一列，名称为“mateCategory”（物料大类）
        headerList.add("mateCategory");

        //3.获取表数据
        List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
        List<CustomerBom> listBody = customerBomList.stream().filter(s -> s.getBomType() == 0).collect(Collectors.toList());
        for(int j = 0; j < listBody.size(); j++){
            List<String> resultList = new ArrayList<String>();
            CustomerBom oBody = listBody.get(j);
            resultList = bomPropToList(resultList, oBody);  //将CustomerBom的BomProp属性按顺序存入List集合中
            resultList = resultList.subList(0, endColumn);
            //20190218-截取表数据List后，添加多一列，名称为“checkCode”，存储选中的物料号
            resultList.add(oBody.getCheckCode());
            //20190301-截取表头List后，添加多一列，名称为“mateCategory”，存储需要筛选的物料大类
            resultList.add(oBody.getMateCategory());

            Map<String, String> mapBody = new HashMap<String, String>();
            mapBody.put("CusBomId", (oBody.getId()!=null?oBody.getId().toString():""));
            for(int k = 0; k < resultList.size(); k++){
                mapBody.put(headerList.get(k), resultList.get(k));
            }
          //20190114-fyx
            mapBody.put("checkStatus", (oBody.getCheckStatus()!=null?oBody.getCheckStatus().toString():"0"));
            mapList.add(mapBody);
        }

        //4.统计当前导入的客户BOM的成本总价格
        Map<String, Object> mapCost = getTotalCostPrice(listBody);

        //5.获取筛选大类列表
        List<MaterielCategoryK3> mateCateList = materielCategoryK3Dao.findByFLevelOrderByFItemIdAsc(1);

        //6.封装Map
        Map<String, Object> mapResult = new HashMap<String, Object>();
        mapResult.put("header", headerList);
        mapResult.put("results", mapList);
        mapResult.put("bomParams", this.getBomParams(fileId));//设置的参数
        mapResult.put("totalCost", mapCost);  //统计的成本总价格
        mapResult.put("listCategory", mateCateList);  //筛选大类列表

        return ApiResponseResult.success().data(mapResult);
    }

    /**
     * 选中/取消匹配的物料
     * （1）修改CustomerBomMatch的checkStatus
     * （2）修改CustomerBom的checkStatus和计算各个价格的总和
     * @param id
     * @param checkStatus
     * @return
     * @throws Exception
     */
	@Override
    @Transactional
	public ApiResponseResult doCheckMateriel(Long id, int checkStatus) throws Exception {
		if(id == null){
            return ApiResponseResult.failure("记录ID不能为空！");
        }
        CustomerBomMatch o = customerBomMatchDao.findById((long) id);
        if(o == null){
            return ApiResponseResult.failure("匹配的物料不存在！");
        }
        SysUser currUser = UserUtil.getCurrUser();

        //1.修改CustomerBomMatch信息
        o.setCheckStatus(checkStatus);
        o.setModifiedTime(new Date());
        o.setPkModifiedBy((currUser != null) ? currUser.getId() : null);
        o.setModifiedName((currUser != null) ? currUser.getUserName() : null);
        
        List<CustomerBomMatch> listAdd = new ArrayList<CustomerBomMatch>();
        //选中的话则取消别的选项
        if(checkStatus == 1){
            //获取所有列表
            List<CustomerBomMatch> list = customerBomMatchDao.findByIsDelAndCheckStatusAndCusBomId(BasicStateEnum.FALSE.intValue(),1, o.getCusBomId());

            for(CustomerBomMatch cus:list){
            	if(cus.getId() != o.getId()){
            		cus.setCheckStatus(0);
                    listAdd.add(cus);
            	}
            }
        }
        listAdd.add(o);
        customerBomMatchDao.saveAll(listAdd);

        //2.修改关联的CustomerBom信息
        //（1）如果选中，则checkStatus为1；
        //（2）如果取消，则循环看是否还存在选中的，有则checkStatus为1，没有则checkStatus为0
        CustomerBom customerBom = new CustomerBom();
        if(o.getCusBomId() != null){
            //2.1获取关联的CustomerBom
            customerBom = customerBomDao.findById((long) o.getCusBomId());
            if(customerBom != null){
                //2.2获取表头CustomerBom信息
                CustomerBom customerBomTitle = new CustomerBom();
                List<CustomerBom> customerBomTitleList = customerBomDao.findByIsDelAndFileIdAndBomType(BasicStateEnum.FALSE.intValue(), o.getFileId(), 1);
                if(customerBomTitleList.size() > 0){
                    customerBomTitle = customerBomTitleList.get(0);
                }
                //物料数量
                BigDecimal qtyValue = new BigDecimal(0);
                //2.3获取参数表BomParams信息
                List<BomParams> bomParamsList = bomParamsDao.findByIsDelAndFileIdOrderByIdDesc(BasicStateEnum.FALSE.intValue(), o.getFileId());
                if(bomParamsList.size() > 0 && bomParamsList.get(0) != null){
                    BomParams bomParams = bomParamsList.get(0);
                    qtyValue = getQtyValue(customerBom, customerBomTitle, bomParams);
                }

                //2.4修改CustomerBomMatch的checkStatus、修改checkCode和计算价格
                if(checkStatus == 1){
                    customerBom.setCheckStatus(1);
                    customerBom.setCheckCode(o.getfNumber());
                    //计算价格
                    customerBom = getPriceWithQty(o, customerBom, qtyValue);
                }
                if(checkStatus == 0){
                    //获取CustomerBomMatch信息
                    List<CustomerBomMatch> list2 = customerBomMatchDao.findByIsDelAndCheckStatusAndCusBomId(BasicStateEnum.FALSE.intValue(),1, o.getCusBomId());
                    if(list2.size() > 0){
                        customerBom.setCheckStatus(1);
                        customerBom.setCheckCode(list2.get(0).getfNumber());
                        //计算价格
                        CustomerBomMatch match2 = list2.get(0);
                        if(match2 != null){
                            customerBom = getPriceWithQty(match2, customerBom, qtyValue);
                        }
                    }else{
                        customerBom.setCheckStatus(0);
                        customerBom.setCheckCode(null);
                        //计算价格，此时所有价格为0
                        customerBom.setfPrice(new BigDecimal(0));
                        customerBom.setfAuxPriceDiscount(new BigDecimal(0));
                        customerBom.setfPrice3MonthMax(new BigDecimal(0));
                        customerBom.setfAuxPrice3MonthMax(new BigDecimal(0));
                        customerBom.setfPrice3MonthMin(new BigDecimal(0));
                        customerBom.setfAuxPrice3MonthMin(new BigDecimal(0));
                    }
                }
            }
            customerBom.setModifiedTime(new Date());
            customerBom.setPkModifiedBy((currUser != null) ? currUser.getId() : null);
            customerBom.setModifiedName((currUser != null) ? currUser.getUserName() : null);
            customerBomDao.save(customerBom);
        }

        //3.获取匹配的数据
        ApiResponseResult result = this.getBomMatch(o.getCusBomId(),"", (float) 0.001, 10);

        //4.统计当前导入的客户BOM的成本总价格
        List<CustomerBom> listBody = customerBomDao.findByIsDelAndFileIdAndBomType(BasicStateEnum.FALSE.intValue(), o.getFileId(), 0);
        Map<String, Object> mapCost = getTotalCostPrice(listBody);

        //5.封装数据
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("bomMatchList", result.getData());
        map.put("bomList", customerBom);
        map.put("totalCost", mapCost);  //统计的成本总价格
        return ApiResponseResult.success().data(map);

        //return this.getBomMatch(o.getCusBomId(), (float) 0.001, 10);
	}

	//获取BOM单个物料数量
	public BigDecimal getQtyValue(CustomerBom customerBom, CustomerBom customerBom2, BomParams bomParams){
        String qtyName = "";
        String qtyValue = "";
        BigDecimal qtyNum = new BigDecimal(0);

        try{
            //1.获取CustomerBom的所有属性名称
            Field[] fields = customerBom2.getClass().getDeclaredFields();
            String[] fieldNames = new String[fields.length];
            for(int i=0;i<fields.length;i++){
                fieldNames[i]=fields[i].getName();
            }

            //2.获取BomParams数量列的名称
            String quantityCol = bomParams.getQuantityCol();

            //3.获取物料数量的属性名称
            for(int j = 0; j < fieldNames.length; j++){
                Object object = getFieldValueByName(fieldNames[j], customerBom2);
                if(object != null && quantityCol.equals(object.toString())){
                    qtyName = fieldNames[j];
                    break;
                }
            }

            //4.获取物料数量
            Object object2 = getFieldValueByName(qtyName, customerBom);
            qtyValue = object2 != null ? object2.toString() : "";

            //5.转换成数字的格式
            if(StringUtils.isNotEmpty(qtyValue)){
                qtyNum = new BigDecimal(qtyValue);
            }
        }catch (Exception e){
            return qtyNum;
        }

        return qtyNum;
    }

    //根据CustomerBoomMatch价格信息计算CustomerBom的单个物料总价格
    public CustomerBom getPriceWithQty(CustomerBomMatch customerBomMatch, CustomerBom customerBom, BigDecimal qtyValue){
	    try{
            //从CustomerBomMatch获取各个价格
            BigDecimal fPrice = customerBomMatch.getfPrice();
            BigDecimal fAuxPriceDiscount = customerBomMatch.getfAuxPriceDiscount();
            BigDecimal fPrice3MonthMax = customerBomMatch.getfPrice3MonthMax();
            BigDecimal fAuxPrice3MonthMax = customerBomMatch.getfAuxPrice3MonthMax();
            BigDecimal fPrice3MonthMin = customerBomMatch.getfPrice3MonthMin();
            BigDecimal fAuxPrice3MonthMin = customerBomMatch.getfAuxPrice3MonthMin();

            //各个价格乘以数量qtyValueDe得到单个物料总价格
            if(fPrice != null){
                customerBom.setfPrice(fPrice.multiply(qtyValue));
            }
            if(fAuxPriceDiscount != null){
                customerBom.setfAuxPriceDiscount(fAuxPriceDiscount.multiply(qtyValue));
            }
            if(fPrice3MonthMax != null){
                customerBom.setfPrice3MonthMax(fPrice3MonthMax.multiply(qtyValue));
            }
            if(fAuxPrice3MonthMax != null){
                customerBom.setfAuxPrice3MonthMax(fAuxPrice3MonthMax.multiply(qtyValue));
            }
            if(fPrice3MonthMin != null){
                customerBom.setfPrice3MonthMin(fPrice3MonthMin.multiply(qtyValue));
            }
            if(fAuxPrice3MonthMin != null){
                customerBom.setfAuxPrice3MonthMin(fAuxPrice3MonthMin.multiply(qtyValue));
            }
        }catch (Exception e){
	        return customerBom;
        }

        return customerBom;
    }

    //根据fileId统计当前导入的客户BOM的成本总价格
    public Map<String, Object> getTotalCostPrice(List<CustomerBom> customerBomList){
	    //需要返回的数据：物料总数，已选中的物料数，物料价格总和（6个价格）
        Integer totalNum = 0;  //物料总数
        Integer chosenNum = 0;  //已选中的物料数
        BigDecimal fPrice = new BigDecimal(0);
        BigDecimal fAuxPriceDiscount = new BigDecimal(0);
        BigDecimal fPrice3MonthMax = new BigDecimal(0);
        BigDecimal fAuxPrice3MonthMax = new BigDecimal(0);
        BigDecimal fPrice3MonthMin = new BigDecimal(0);
        BigDecimal fAuxPrice3MonthMin = new BigDecimal(0);

        if(customerBomList != null){
            //1.获取物料总数
            totalNum = customerBomList.size();

            for(int i = 0; i < customerBomList.size(); i++){
                CustomerBom customerBom = customerBomList.get(i);
                if(customerBom != null){
                    //2.获取已选中的物料数
                    if(customerBom.getCheckStatus() != null && customerBom.getCheckStatus() == 1){
                        chosenNum++;
                    }

                    //3.获取物料价格总和
                    //3.1 fPrice最新采购价总和（不含税）
                    BigDecimal price1 = customerBom.getfPrice();
                    if(price1 != null){
                        fPrice = fPrice.add(price1);
                    }

                    //3.2 fAuxPriceDiscount最新采购价总和（不含税）
                    BigDecimal price2 = customerBom.getfAuxPriceDiscount();
                    if(price2 != null){
                        fAuxPriceDiscount = fAuxPriceDiscount.add(price2);
                    }

                    //3.3 fPrice3MonthMax3个月内的最高采购价总和（不含税）
                    BigDecimal price3 = customerBom.getfPrice3MonthMax();
                    if(price3 != null){
                        fPrice3MonthMax = fPrice3MonthMax.add(price3);
                    }

                    //3.4 fAuxPrice3MonthMax3个月内的最高采购价总和（含税）
                    BigDecimal price4 = customerBom.getfAuxPrice3MonthMax();
                    if(price4 != null){
                        fAuxPrice3MonthMax = fAuxPrice3MonthMax.add(price4);
                    }

                    //3.5 fPrice3MonthMin3个月内的最低采购价总和（不含税）
                    BigDecimal price5 = customerBom.getfPrice3MonthMin();
                    if(price5 != null){
                        fPrice3MonthMin = fPrice3MonthMin.add(price5);
                    }

                    //3.6 fAuxPrice3MonthMin3个月内的最低采购价总和（含税）
                    BigDecimal price6 = customerBom.getfAuxPrice3MonthMin();
                    if(price6 != null){
                        fAuxPrice3MonthMin = fAuxPrice3MonthMin.add(price6);
                    }
                }
            }
        }

        //4.封装数据
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("totalNum", totalNum);
        map.put("chosenNum", chosenNum);
        map.put("fPrice", fPrice);
        map.put("fAuxPriceDiscount", fAuxPriceDiscount);
        map.put("fPrice3MonthMax", fPrice3MonthMax);
        map.put("fAuxPrice3MonthMax", fAuxPrice3MonthMax);
        map.put("fPrice3MonthMin", fPrice3MonthMin);
        map.put("fAuxPrice3MonthMin", fAuxPrice3MonthMin);

        return map;
    }

}
