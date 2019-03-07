package com.unind.qms.provider;

import com.google.gson.JsonObject;
import com.unind.base.components.mail.MailSenderService;
import com.unind.base.configure.AppConfig;
import com.unind.base.dao.admin.SysUserDao;
import com.unind.base.domain.admin.agg.SysUserRolesAgg;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.domain.admin.user.SysUser;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.context.SessionContextUtils;
import com.unind.erp.item.dao.ErpProductInfoAggDao;
import com.unind.erp.item.domain.ErpProductInfoAgg;
import com.unind.qms.enumeration.BasicEnumConstants;
import com.unind.qms.enumeration.BasicStateEnum;
import com.unind.qms.web.approved.dao.ApprovedFlowRecordDao;
import com.unind.qms.web.approved.entity.ApprovedFlowRecord;
import com.unind.qms.web.basic.dao.TodoInfoDao;
import com.unind.qms.web.basic.entity.TodoInfo;
import com.unind.qms.web.product.dao.ProductInfoDao;
import com.unind.qms.web.product.entity.ProductInfo;
import com.unind.qms.web.risk.dao.ProductRiskDao;
import com.unind.qms.web.risk.dao.RiskApprovedRecordDao;
import com.unind.qms.web.risk.dao.SupplierRiskDao;
import com.unind.qms.web.risk.entity.ProductRisk;
import com.unind.qms.web.risk.entity.RiskApprovedRecord;
import com.unind.qms.web.risk.entity.SupplierRisk;
import com.unind.qms.web.risk.service.ProductRiskService;
import com.unind.qms.web.risk.service.SupplierRiskService;
import com.unind.qms.web.risk.service.internal.SupplierRiskImpl;
import com.unind.qms.web.supplier.dao.SupplierInfoDao;
import com.unind.qms.web.supplier.entity.SupplierInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import javax.persistence.EntityManager;
import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class HttpScheduledService {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private ProductRiskDao productRiskDao;
    @Autowired
    private SupplierInfoDao supplierInfoDao;
    @Autowired
    private SupplierRiskDao supplierRiskDao;
    @Autowired
    private ProductRiskService productRiskService;
    @Autowired
    private SupplierRiskService supplierRiskService;
    @Autowired
    private HttpClientService httpClientService;
    @Autowired
    private ApprovedFlowRecordDao approvedFlowRecordDao;
    @Autowired
    private TodoInfoDao todoInfoDao;
    @Autowired
    private ErpProductInfoAggDao erpProductInfoAggDao;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private RiskApprovedRecordDao riskApprovedRecordDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private AppConfig appConfig;

    @Scheduled(cron = "0 0 0 * * ? ")
//    @Scheduled(cron = "0 0/1 * * * ? ")
    @Transactional
//    @Transactional(value = "txManager2")
    public void getProductInfo() {
        try {
            //每天0点获取前一天的,或者已删除、产品不可用、供应商不可用的成品
            Map<String, Object> param = new HashMap<String, Object>();
            StringBuffer sqlbf1 = new StringBuffer();
            sqlbf1.append(" SELECT a.* FROM erp_product_info_agg a");
            sqlbf1.append(" WHERE (to_char(a.bs_created_time,'yyyy-MM-dd') = to_char(sysdate-1,'yyyy-MM-dd')");
            sqlbf1.append(" OR a.bs_is_del = '1' OR a.bs_item_status = 'Inactive' OR a.bs_vendor_status = 'N')");
            sqlbf1.append(" AND a.bs_vendor_code not in ('PS-0102','10')");
            sqlbf1.append(" AND a.bs_item_code like '1%' order by id asc");
            List<ErpProductInfoAgg> erpProductInfoAggList = createSQLQuery(sqlbf1.toString(), param, ErpProductInfoAgg.class);

            List<ProductInfo> productInfoList = new ArrayList<ProductInfo>();
            for(ErpProductInfoAgg erpProductInfoAgg : erpProductInfoAggList){
                //产品编号、供应商编号是否为空
                if(StringUtils.isEmpty(erpProductInfoAgg.getBsItemCode())||StringUtils.isEmpty(erpProductInfoAgg.getBsVendorCode())){
                    continue;
                }
                ProductInfo productInfo = new ProductInfo();

                //判断是否存在此产品
                List<ProductInfo> productInfos = productInfoDao.findByBsPrCodeAndBsSuppCodeAndBsOrganizationId(erpProductInfoAgg.getBsItemCode(), erpProductInfoAgg.getBsVendorCode(), erpProductInfoAgg.getBsOrganizationId());
                if (productInfos.size() > 0) {
                    productInfo.setId(productInfos.get(0).getId());
                }
                productInfo.setBsPrCode(erpProductInfoAgg.getBsItemCode());
                productInfo.setBsPrName(erpProductInfoAgg.getBsItemName());
                productInfo.setBsItemId(erpProductInfoAgg.getBsItemId());
                productInfo.setBsItemStatus(erpProductInfoAgg.getBsItemStatus());
                productInfo.setBsCateDesc(erpProductInfoAgg.getBsCateDesc());
                productInfo.setBsSuppCode(erpProductInfoAgg.getBsVendorCode());
                productInfo.setBsSuppChieseName(erpProductInfoAgg.getBsVendorName());
                productInfo.setBsVendorId(erpProductInfoAgg.getBsVendorId());
                productInfo.setBsVendorSite(erpProductInfoAgg.getBsVendorSite());
                productInfo.setBsVendorStatus(erpProductInfoAgg.getBsVendorStatus());
                productInfo.setBsOrganizationId(erpProductInfoAgg.getBsOrganizationId());
                productInfo.setBsOrganizationName(erpProductInfoAgg.getBsOrganizationName());
                productInfo.setBsPaperNum(erpProductInfoAgg.getBsPaperNum());
                productInfo.setBsPaperVersion(erpProductInfoAgg.getBsPaperVersion());
                productInfo.setBsQc(erpProductInfoAgg.getBsQc());
                productInfo.setBsSqe(erpProductInfoAgg.getBsSqe());
                productInfo.setBsQcManager(erpProductInfoAgg.getBsQcManager());
                productInfo.setBsIsDel(erpProductInfoAgg.getBsIsDel());
                productInfo.setBsCreatedTime(erpProductInfoAgg.getBsCreatedTime());

                productInfoList.add(productInfo);
            }
            productInfoDao.save(productInfoList);

            for(ProductInfo productInfo:productInfoList){
                ProductRisk productRisk = new ProductRisk();
                productRisk.setBsPrId(productInfo.getId());
                productRiskService.add(productRisk);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("定时导入产品信息失败", e);
        }
    }

    private <T> List<T> createSQLQuery(String sql, Map<String, Object> paramMap, Class<T> cls) {
        Session session = entityManager.unwrap(Session.class);
        SQLQuery query = session.createSQLQuery(sql);

        if(cls != null){
            query.addEntity(cls);
        }
        if (paramMap != null && paramMap.size() > 0) {
            for (String key : paramMap.keySet()) {
                query.setParameter(key, paramMap.get(key));
            }
        }
//        query.setResultTransformer(null==cls? Transformers.ALIAS_TO_ENTITY_MAP : Transformers.aliasToBean(cls));
        List<T> list = query.list();
        return list;
    }

    public static String get(JsonObject jsonObject, String key, boolean flag, String defVal) {
        try {
            return jsonObject.get(key).isJsonNull() ? "" : jsonObject.get(key).getAsString();
        } catch (NullPointerException e) {
            if (flag) {
                return defVal;
            }else {
                throw new NullPointerException(" property: "+key+" is not found");
            }
        }
    }

    /**
     * 供应商风险等级得分定时更新（每个供应商新增对应的风险等级记录，计算出新的等级得分并更新）
     * @throws BusinessException
     */
    @Scheduled(cron = "0 0 2 1 * ? ")
//    @Scheduled(cron = "0 02 14 * * ? ")
    @Transactional
//    @Transactional(value = "txManager2")
    public void updateSupplierRisk(){
        try{
            List<SupplierRisk> supplierRiskList = new ArrayList<SupplierRisk>();
            List<SupplierInfo> supplierInfoList = new ArrayList<SupplierInfo>();

            //获取批准人
            Long approverId = (long) 1;
            Map<String, Object> param = new HashMap<>();
            param.put("roleCode", "QMS_QUALITY_MANAGER");  //风险-----宁总
            StringBuffer sqlbf1 = new StringBuffer();
            sqlbf1.append(" SELECT a.* FROM sys_user_roles_agg a LEFT JOIN sys_role b ON a.pk_role = b.id");
            sqlbf1.append(" LEFT JOIN sys_user c on c.id = a.pk_user");
            sqlbf1.append(" WHERE b.bs_code = :roleCode AND c.bs_is_del = '0'");
            List<SysUserRolesAgg> sysUserRolesAggList = createSQLQuery(sqlbf1.toString(), param, SysUserRolesAgg.class);
            if (sysUserRolesAggList.size() > 0) {
                approverId = sysUserRolesAggList.get(0).getPkUser();
            }

            //遍历供应商信息表所有有效记录
            Iterator iterator = supplierInfoDao.findByBsIsDel(BooleanStateEnum.FALSE.intValue()).iterator();
            while(iterator.hasNext()){
                SupplierInfo supplierInfo = (SupplierInfo) iterator.next();
                if(supplierInfo == null){
                    continue;
                }

                //原风险等级
                int originalRiskLevel = 0;

                //1.获取供应商对应的风险信息记录（按时间倒序排序）
                Sort sort = new Sort(Sort.Direction.DESC, "bsCreatedTime");
                List<SearchFilter> filters = new ArrayList<>();
                filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BooleanStateEnum.FALSE.intValue()));
                filters.add(new SearchFilter("bsSuppId", SearchFilter.Operator.EQ, supplierInfo.getId()));
                Specification<SupplierRisk> spec = Specifications.where(BaseOprService.and(filters, SupplierRisk.class));
                List<SupplierRisk> supplierRiskList2 = supplierRiskDao.findAll(spec, sort);
                //2.新增供应商风险信息
                SupplierRisk o = new SupplierRisk();
                if(supplierRiskList2.size() == 0){
                    //2.1如果供应商对应风险信息记录不存在，则所有type值默认为1，value值默认为0
                    originalRiskLevel = 0;  //风险等级默认为0
                    o.setBsCreatedTime(new Date());
                    o.setBsSuppId(supplierInfo.getId());
                    //type值默认为1
                    o.setBsFeedbackType(1);
                    o.setBsPpmType(1);
                    o.setBsDeliveryType(1);
                    o.setBsInspectType(1);
                    o.setBsPaymentType(1);
                    o.setBsCpkType(1);
                    o.setBsDangerProType(1);
                    o.setBsApproveType(1);
                    o.setBsEhsType(1);
                    //value值默认为0
                    o.setBsPpmValue(0);
                    o.setBsInspectValue(0);
                    o.setBsCpkValue(0);
                    o.setBsEhsValue(0);
                }else{
                    //2.2如果供应商对应的风险信息记录存在，则复制上一条风险信息记录
                    SupplierRisk o2 = supplierRiskList2.get(0);
                    originalRiskLevel = o2.getBsRiskLevel();   //获取原风险等级
                    o.setBsCreatedTime(new Date());
                    o.setBsSuppId(o2.getBsSuppId());
                    o.setBsRiskLevel(o2.getBsRiskLevel());
                    //type值
                    o.setBsFeedbackType(o2.getBsFeedbackType());
                    o.setBsPpmType(o2.getBsPpmType());
                    o.setBsDeliveryType(o2.getBsDeliveryType());
                    o.setBsInspectType(o2.getBsInspectType());
                    o.setBsPaymentType(o2.getBsPaymentType());
                    o.setBsCpkType(o2.getBsCpkType());
                    o.setBsDangerProType(o2.getBsDangerProType());
                    o.setBsApproveType(o2.getBsApproveType());
                    o.setBsEhsType(o2.getBsEhsType());
                    o.setBsComStability(o2.isBsComStability());
                    o.setBsAntiCorruptProtocol(o2.isBsAntiCorruptProtocol());
                    o.setBsLongTermProtocol(o2.isBsLongTermProtocol());
                    o.setBsCooperProtocol(o2.isBsCooperProtocol());
                    o.setBsBusinessBroScore(o2.isBsBusinessBroScore());
                    //value值
                    o.setBsPpmValue(o2.getBsPpmValue());
                    o.setBsInspectValue(o2.getBsInspectValue());
                    o.setBsCpkValue(o2.getBsCpkValue());
                    o.setBsDangerProCount(o2.getBsDangerProCount());
                    o.setBsEhsValue(o2.getBsEhsValue());
                }

//                //3.更新ppm的value值，并修改对应type值（其他的value值在这里不必修改）
//                o = supplierRiskService.updatePpmValueType(o);
                //3.更新value值，并修改对应type值
                o = supplierRiskService.updateValueType(o);

                //4.统计各项得分
                o.setBsFeedbackScore(o.getBsFeedbackType() * 3);
                o.setBsPpmScore(o.getBsPpmType() * 2);
                o.setBsDeliveryScore(o.getBsDeliveryType() * 3);
                o.setBsInspectScore(o.getBsInspectType() * 2);
                o.setBsPaymentScore(o.getBsPaymentType() * 1);
                o.setBsCpkScore(o.getBsCpkType() * 2);
                o.setBsDangerProScore(o.getBsDangerProType() * 2);
                o.setBsApprovedScore(o.getBsApproveType() * 2);
                o.setBsEhsScore(o.getBsEhsType() * 2);
                o.setBsBusinessScore(((o.isBsComStability() ? 1 : 0) + (o.isBsAntiCorruptProtocol() ? 1 : 0 )
                        + (o.isBsLongTermProtocol() ? 1 : 0 ) + (o.isBsCooperProtocol() ? 1 : 0 )
                        + (o.isBsBusinessBroScore() ? 1 : 0)) * 2);

                //5.统计总得分
                o.setBsRiskScore(o.getBsFeedbackScore() + o.getBsPpmScore() + o.getBsDeliveryScore() + o.getBsInspectScore()
                        + o.getBsPaymentScore() + o.getBsCpkScore() + o.getBsDangerProScore() + o.getBsApprovedScore()
                        + o.getBsEhsScore() + o.getBsBusinessScore());

                //6.统计风险等级（1-5级）
                //判断直升高风险类型是否达到高风险（type为5），满足要求直升高风险
                int level = 1;
                if(o.getBsFeedbackType() == 5 || o.getBsDeliveryType() == 5){
                    //直升高风险
                    level = 5;
                }else{
                    int totalScore = o.getBsRiskScore();
                    if(totalScore >= 0 && totalScore <= 40){
                    }
                    if(totalScore >= 41 && totalScore <= 55){
                        level = 2;
                    }
                    if(totalScore >= 56 && totalScore <= 70){
                        level = 3;
                    }
                    if(totalScore >= 71 && totalScore <= 85){
                        level = 4;
                    }
                    if(totalScore >= 86 && totalScore <= 100){
                        level = 5;
                    }
                }
                o.setBsRiskLevel(level);
                supplierRiskList.add(o);

                //7.添加待办事项，给宁总
                //如果原来等级与现在等级不同，发待办事项给宁总
                //注意：这个待办事项只是通知宁总，不需要批准，看了之后手动关闭就行
                if(originalRiskLevel != level){
                    TodoInfo todoInfo = new TodoInfo();
                    todoInfo.setBsUserId(approverId);
                    todoInfo.setBsReferId(o.getBsSuppId());   //关联ID（存放供应商ID）
                    todoInfo.setBsExtend(o.getBsSuppId());   //扩展（同关联ID）
                    todoInfo.setBsRouter("/qms/supplier/riskManagement");
                    //设置待办标题
                    String title = "[风险等级变更批准]" + supplierInfo.getBsSuppChieseName() + "风险等级由" + originalRiskLevel +"级变更为" + level + "级";
                    todoInfo.setBsTitle(title);
                    todoInfo.setBsStatus(BasicEnumConstants.TODO_NOFINISH);
                    todoInfo.setBsSystemType(BasicStateEnum.TODO_QMSTYPE.intValue());
                    todoInfo.setBsType(BasicEnumConstants.TODO_SUPP_RISK);
                    todoInfo.setBsCreatedTime(new Date());   //创建时间
                    todoInfo.setBsStartTime(new Date());     //有效起始时间
                    //计算一周后的时间
                    Calendar curr = Calendar.getInstance();
                    curr.setTime(todoInfo.getBsStartTime());
                    curr.add(Calendar.WEEK_OF_YEAR, 1);
                    Date after7Days=curr.getTime();
                    todoInfo.setBsEndTime(after7Days);                  //有效结束时间
                    todoInfoDao.save(todoInfo);

                    //发送邮件
                    //先判断是否开启了邮件功能，开启了就可以发邮件
                    String flagMail = appConfig.getString("scm.mail.enabled");
                    if(StringUtils.isNotEmpty(flagMail) && flagMail.equals("true")){
                        SysUser sysUser = sysUserDao.findOne(todoInfo.getBsUserId());
                        if(sysUser != null && StringUtils.isNotEmpty(sysUser.getBsEmail())){
                            String nameTo = sysUser.getBsName();    //收件人名
                            String mailTo = sysUser.getBsEmail().trim();   //收件人邮箱
                            String subject = "待办事项提醒";    //主题
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                            String dateStr = simpleDateFormat.format(new Date());
                            StringBuffer text = new StringBuffer();   //邮件内容
                            text.append("<div style='padding: 5px;'>" + nameTo + "，" + "</div>");
                            text.append("<div style='padding: 5px;'>" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你好！" + title +"。请及时处理！</div>");
                            text.append("<div style='padding: 5px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请<a href='http://www.unind.net'>点击此处</a>进入QMS系统。</div>");
                            text.append("<div style='padding: 5px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;此致</div>");
                            text.append("<div style='padding: 5px;'>敬礼</div>");
                            text.append("<div style='padding: 5px; float: right; margin: 0 50px 0 0;'>QMS系统</div><br>");
                            text.append("<div style='padding: 5px; float: right; margin: 0 20px 0 0;clear: both;'>" + dateStr + "</div><br>");
                            mailSenderService.send(mailTo, subject, text.toString());
                        }
                    }
                }

                //8.更新到供应商信息表的系统风险等级
                supplierInfo.setBsRiskLevelSys(level);
                supplierInfo.setBsRiskTimeSys(new Date());
                supplierInfo.setBsRiskScore(o.getBsRiskScore());
                supplierInfo.setBsRiskLevel(level);
                supplierInfoList.add(supplierInfo);
            }

            if(supplierRiskList.size() > 0){
                supplierRiskDao.save(supplierRiskList);
            }
            if(supplierInfoList.size() > 0){
                supplierInfoDao.save(supplierInfoList);
            }
            logger.info("供应商自动更新等级成功！");
        }catch(Exception e){
            e.printStackTrace();
            logger.error("供应商自动更新等级失败！", e);
        }
    }

    /**
     * 产品风险等级得分定时更新
     * @throws BusinessException
     */
    @Scheduled(cron = "0 0 1 1 * ? ")
//    @Scheduled(cron = "0 04 11 * * ? ")
    @Transactional
//    @Transactional(value = "txManager2")
    public void updateProductRisk(){
        try{
            List<ProductRisk> productRiskList = new ArrayList<ProductRisk>();
            List<ProductInfo> productInfoList = new ArrayList<ProductInfo>();

            //遍历产品信息表所有有效记录
            Iterator iterator = productInfoDao.findByBsIsDel(BooleanStateEnum.FALSE.intValue()).iterator();
            while(iterator.hasNext()){
                ProductInfo productInfo = (ProductInfo) iterator.next();
                if(productInfo == null){
                    continue;
                }

                //原风险等级
                int originalRiskLevel = 0;

                //1.获取产品对应的风险信息记录（按时间倒序排序）
                Sort sort = new Sort(Sort.Direction.DESC, "bsCreatedTime");
                List<SearchFilter> filters = new ArrayList<>();
                filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BooleanStateEnum.FALSE.intValue()));
                filters.add(new SearchFilter("bsPrId", SearchFilter.Operator.EQ, productInfo.getId()));
                Specification<ProductRisk> spec = Specifications.where(BaseOprService.and(filters, ProductRisk.class));
                List<ProductRisk> productRiskList2 = productRiskDao.findAll(spec, sort);
                //2.新增产品风险信息
                ProductRisk o = new ProductRisk();
                if(productRiskList2.size() == 0){
                    //2.1如果产品的对应的风险信息记录不存在，则所有type值默认为1，value值默认为0
                    originalRiskLevel = 0;   //风险等级默认为0
                    o.setBsCreatedTime(new Date());
                    o.setBsPrId(productInfo.getId());
                    //type值默认为1
                    o.setBsMassProductType(1);
                    o.setBsApprovedType(1);
                    o.setBsCpkType(1);
                    o.setBsGrrType(1);
                    o.setBsEngineeringType(1);
                    o.setBsInspectType(1);
                    o.setBsFeedbackType(1);
                    o.setBsPpmType(1);
                    o.setBsProjectType(1);
                    //value值默认为0
                    o.setBsCpkValue(0);
                    o.setBsGrrValue(0);
                    o.setBsInspectValue(0);
                    o.setBsPpmValue(0);
                }else{
                    //2.2如果产品对应的风险信息记录存在，则复制上一条风险记录
                    ProductRisk o2 = productRiskList2.get(0);
                    originalRiskLevel = o2.getBsRiskLevel();   //获取原风险等级
                    o.setBsCreatedTime(new Date());
                    o.setBsPrId(o2.getBsPrId());
                    o.setBsRiskLevel(o2.getBsRiskLevel());
                    //type值
                    o.setBsMassProductType(o2.getBsMassProductType());
                    o.setBsApprovedType(o2.getBsApprovedType());
                    o.setBsCpkType(o2.getBsCpkType());
                    o.setBsGrrType(o2.getBsGrrType());
                    o.setBsEngineeringType(o2.getBsEngineeringType());
                    o.setBsInspectType(o2.getBsInspectType());
                    o.setBsFeedbackType(o2.getBsFeedbackType());
                    o.setBsPpmType(o2.getBsPpmType());
                    o.setBsProjectType(o2.getBsProjectType());
                    //value值
                    o.setBsCpkValue(o2.getBsCpkValue());
                    o.setBsGrrValue(o2.getBsGrrValue());
                    o.setBsInspectValue(o2.getBsInspectValue());
                    o.setBsPpmValue(o2.getBsPpmValue());
                }

//                //3.更新ppm的value值，并修改对应type值（其他的value值在这里不必修改）
//                o = productRiskService.updatePpmValueType(o);
                //3.更新value值，并修改对应type值
                o = productRiskService.updateValueType(o);

                //4.统计各项得分
                o.setBsMassProductScore(o.getBsMassProductType() * 3);
                o.setBsApprovedScore(o.getBsApprovedType() * 2);
                o.setBsCpkScore(o.getBsCpkType() * 2);
                o.setBsGrrScore(o.getBsGrrType() * 2);
                o.setBsEngineeringScore(o.getBsEngineeringType() * 3);
                o.setBsInspectScore(o.getBsInspectType() *3);
                o.setBsFeedbackScore(o.getBsFeedbackType() * 3);
                o.setBsPpmScore(o.getBsPpmType() * 2);

                //5.统计总得分
                o.setBsRiskScore(o.getBsMassProductScore() + o.getBsApprovedScore() + o.getBsCpkScore()
                        + o.getBsGrrScore() + o.getBsEngineeringScore() + o.getBsInspectScore()
                        + o.getBsFeedbackScore() + o.getBsPpmScore());

                //6.统计风险等级（1-5级）
                //判断直升高风险类型是否达到高风险（type为5），满足要求直升高风险
                int level = 1;
                //新产品3个月内type为1
                int subDays = getDays(productInfo.getBsCreatedTime(), new Date());
                if(subDays <= 90){
                    o.setBsProjectType(1);
                }
                if(o.getBsMassProductType() == 5 || o.getBsEngineeringType() == 5 || o.getBsInspectType() == 5 ||
                        o.getBsFeedbackType() == 5 || o.getBsProjectType() == 1 || o.getBsInspectType() == 3){
                    //直升高风险
                    level = 5;
                }else{
                    int totalScore = o.getBsRiskScore();
                    if(totalScore >= 0 && totalScore <= 40){
                    }
                    if(totalScore >= 41 && totalScore <= 55){
                        level = 2;
                    }
                    if(totalScore >= 56 && totalScore <= 70){
                        level = 3;
                    }
                    if(totalScore >= 71 && totalScore <= 85){
                        level = 4;
                    }
                    if(totalScore >= 86 && totalScore <= 100){
                        level = 5;
                    }
                }
                o.setBsRiskLevel(level);
                o.setBsStatus(0);   //状态默认为完成（0.完成 1.进行中），如果需要批准，则在后面状态改为进行中
                //productRiskList.add(o);

                //如果原来等级与现在等级不同，则需要批准
                //此时，需要将风险信息表中的状态改为进行中（0.完成 1.进行中）
                if(originalRiskLevel != level){
                    //风险信息表中的状态改为进行中（0.完成 1.进行中）
                    o.setBsStatus(1);
                    //7.新增到风险批准记录表
                    Long approverId = (long) 1;
                    RiskApprovedRecord riskApprovedRecord = new RiskApprovedRecord();
                    //7.1如果有正在进行中的批准记录，则修改其新等级即可
                    List<RiskApprovedRecord> list = riskApprovedRecordDao.findByBsPrIdAndBsStatus(o.getBsPrId(), 0);
                    if(list.size() > 0){
                        riskApprovedRecord = list.get(0);
                        riskApprovedRecord.setBsNewRiskLevel(level);
                        riskApprovedRecordDao.save(riskApprovedRecord);
                    }else{
                        //7.2如果没有正在进行中的批准记录，则添加一条新纪录
                        riskApprovedRecord.setBsPrId(o.getBsPrId());
                        riskApprovedRecord.setBsOriginalRiskLevel(originalRiskLevel);
                        riskApprovedRecord.setBsNewRiskLevel(level);
                        //添加批准人
                        Map<String, Object> param = new HashMap<>();
                        param.put("roleCode", "QMS_QUALITY_MANAGER");  //风险-----宁总
                        StringBuffer sqlbf1 = new StringBuffer();
                        sqlbf1.append(" SELECT a.* FROM sys_user_roles_agg a LEFT JOIN sys_role b ON a.pk_role = b.id");
                        sqlbf1.append(" LEFT JOIN sys_user c on c.id = a.pk_user");
                        sqlbf1.append(" WHERE b.bs_code = :roleCode AND c.bs_is_del = '0'");
                        List<SysUserRolesAgg> sysUserRolesAggList = createSQLQuery(sqlbf1.toString(), param, SysUserRolesAgg.class);
                        if(sysUserRolesAggList.size() > 0){
                            approverId = sysUserRolesAggList.get(0).getPkUser();
                            riskApprovedRecord.setBsApproverId(approverId);
                        }
                        riskApprovedRecord.setBsStatus(0);   //状态为进行中（0:进行中, 1:同意, 2:拒绝）
                        riskApprovedRecord.setBsCreatedTime(new Date());
                        riskApprovedRecordDao.save(riskApprovedRecord);
                    }

                    //8.添加待办事项，给宁总
                    TodoInfo todoInfo = new TodoInfo();
                    todoInfo.setBsUserId(approverId);
                    todoInfo.setBsReferId(productInfo.getId());   //关联ID（存放产品ID）
                    todoInfo.setBsExtend(riskApprovedRecord.getId());   //扩展（存放风险批准记录ID）
                    todoInfo.setBsRouter("/qms/product/riskManagement");
                    //设置待办标题
                    String title = "[风险等级变更批准]" + productInfo.getBsPrName() + "风险等级由" + originalRiskLevel +"级变更为" + level + "级";
                    todoInfo.setBsTitle(title);
                    todoInfo.setBsStatus(BasicEnumConstants.TODO_NOFINISH);
                    todoInfo.setBsSystemType(BasicStateEnum.TODO_QMSTYPE.intValue());
                    todoInfo.setBsType(BasicEnumConstants.TODO_PR_RISK);
                    todoInfo.setBsCreatedTime(new Date());    //创建时间
                    todoInfo.setBsStartTime(new Date());      //有效开始时间
                    //计算一周后的时间
                    Calendar curr = Calendar.getInstance();
                    curr.setTime(todoInfo.getBsStartTime());
                    curr.add(Calendar.WEEK_OF_YEAR, 1);
                    Date after7Days=curr.getTime();
                    todoInfo.setBsEndTime(after7Days);                  //有效结束时间
                    todoInfoDao.save(todoInfo);

                    //发送邮件
                    //先判断是否开启了邮件功能，开启了就可以发邮件
                    String flagMail = appConfig.getString("scm.mail.enabled");
                    if(StringUtils.isNotEmpty(flagMail) && flagMail.equals("true")){
                        SysUser sysUser = sysUserDao.findOne(todoInfo.getBsUserId());
                        if(sysUser != null && StringUtils.isNotEmpty(sysUser.getBsEmail())){
                            String nameTo = sysUser.getBsName();    //收件人名
                            String mailTo = sysUser.getBsEmail().trim();   //收件人邮箱
                            String subject = "待办事项提醒";    //主题
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                            String dateStr = simpleDateFormat.format(new Date());
                            StringBuffer text = new StringBuffer();   //邮件内容
                            text.append("<div style='padding: 5px;'>" + nameTo + "，" + "</div>");
                            text.append("<div style='padding: 5px;'>" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你好！" + title +"。请及时处理！</div>");
                            text.append("<div style='padding: 5px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请<a href='http://www.unind.net'>点击此处</a>进入QMS系统。</div>");
                            text.append("<div style='padding: 5px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;此致</div>");
                            text.append("<div style='padding: 5px;'>敬礼</div>");
                            text.append("<div style='padding: 5px; float: right; margin: 0 50px 0 0;'>QMS系统</div><br>");
                            text.append("<div style='padding: 5px; float: right; margin: 0 20px 0 0;clear: both;'>" + dateStr + "</div><br>");
                            mailSenderService.send(mailTo, subject, text.toString());
                        }
                    }
                }
                //保存到风险信息表
                productRiskList.add(o);

                //9.更新到产品信息表的系统风险等级
                productInfo.setBsRiskLevelSys(level);
                productInfo.setBsRiskTimeSys(new Date());
                productInfo.setBsRiskScore(o.getBsRiskScore());
                productInfo.setBsRiskLevel(level);
                productInfoList.add(productInfo);
            }

            if(productRiskList.size() > 0){
                productRiskDao.save(productRiskList);
            }
            if(productInfoList.size() > 0){
                productInfoDao.save(productInfoList);
            }
            logger.info("产品自动更新等级成功！");
        }catch(Exception e){
            e.printStackTrace();
            logger.error("产品自动更新等级失败", e);
        }
    }

    /**
     * 供应商体系审核定时提醒
     */
    @Scheduled(cron = "0 0 7 * * ? ")
    @Transactional
//    @Transactional(value = "txManager2")
    public void approvedRemindSys() {
        try{
            List<TodoInfo> todoInfoList = new ArrayList<TodoInfo>();

            //供应商体系审核，获取焦总信息
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("roleCode", "QMS_DEVELOPMENT_MANAGER");//体系-----焦总
            StringBuffer sqlbf1 = new StringBuffer();
            sqlbf1.append(" SELECT a.* FROM sys_user a LEFT JOIN sys_user_roles_agg b ON a.id = b.pk_user");
            sqlbf1.append(" LEFT JOIN sys_role c ON c.id = b.pk_role");
            sqlbf1.append(" WHERE c.bs_code = :roleCode AND a.bs_is_del = '0'");
            List<SysUser> sysUserList = createSQLQuery(sqlbf1.toString(), param, SysUser.class);
            if(sysUserList.size() > 0){
                //邮件信息
                Long userId = sysUserList.get(0).getId();
                String nameTo = sysUserList.get(0).getBsName();    //收件人名
                String mailTo = sysUserList.get(0).getBsEmail().trim();   //收件人邮箱
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                String dateStr = simpleDateFormat.format(new Date());

                //1.找出不在审核中的供应商
                List<SearchFilter> filters = new ArrayList<SearchFilter>();
                filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BaseEnumConstants.FALSE));
                filters.add(new SearchFilter("bsIsApprove", SearchFilter.Operator.EQ, BaseEnumConstants.FALSE));
                Specifications<SupplierInfo> spec = Specifications.where(BaseOprService.and(filters, SupplierInfo.class));
                List<SupplierInfo> supplierInfoList = supplierInfoDao.findAll(spec);
                //2.找出各个供应商对应的体系审核流程记录
                for(int i = 0; i < supplierInfoList.size(); i++){
                    Long suppId = supplierInfoList.get(i).getId();             //供应商ID
                    String suppName = supplierInfoList.get(i).getBsSuppChieseName();   //供应商名称
                    Long recordId = supplierInfoList.get(i).getBsSuppRecordId();   //审核流程记录ID
                    Integer riskLevel = supplierInfoList.get(i).getBsRiskLevel();  //风险等级
                    //3.在审核流程记录表查找对应审核记录，并根据风险等级判断是否需要再次审核
                    //假设风险等级需要再次审核的天数，1-5级分别为：1080，720，360，180，90（单位：天）
                    ApprovedFlowRecord approvedFlowRecord = approvedFlowRecordDao.findOne(recordId);
                    if(approvedFlowRecord != null){
                        Date recordDate = approvedFlowRecord.getBsModifiedTime();
                        Date nowDate = new Date();
                        Integer subDays = getDays(recordDate, nowDate);

                        //邮件信息
                        String subject = "待办事项提醒";    //主题
                        StringBuffer text = new StringBuffer();   //邮件内容
                        text.append("<div style='padding: 5px;'>" + nameTo + "，" + "</div>");
                        text.append("<div style='padding: 5px;'>" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你好！" + suppName +"体系审核到期。请及时处理！</div>");
                        text.append("<div style='padding: 5px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;此致</div>");
                        text.append("<div style='padding: 5px;'>敬礼</div>");
                        text.append("<div style='padding: 5px; float: right; margin: 0 50px 0 0;'>QMS系统</div><br>");
                        text.append("<div style='padding: 5px; float: right; margin: 0 20px 0 0;clear: both;'>" + dateStr + "</div><br>");

                        if(riskLevel == 1){
                            if(subDays > 1080){
                                //3.1发邮件
                                mailSenderService.send(mailTo, subject, text.toString());
                                //3.2添加待办事项
                                TodoInfo todoInfo =createTodoInfo(suppId, userId, suppName);
                                todoInfoList.add(todoInfo);
                            }else{
                                continue;
                            }
                        }
                        if(riskLevel == 2){
                            if(subDays > 720){
                                //3.1发邮件
                                mailSenderService.send(mailTo, subject, text.toString());
                                //3.2添加待办事项
                                TodoInfo todoInfo =createTodoInfo(suppId, userId, suppName);
                                todoInfoList.add(todoInfo);
                            }else{
                                continue;
                            }
                        }
                        if(riskLevel == 3){
                            if(subDays > 360){
                                //3.1发邮件
                                mailSenderService.send(mailTo, subject, text.toString());
                                //3.2添加待办事项
                                TodoInfo todoInfo =createTodoInfo(suppId, userId, suppName);
                                todoInfoList.add(todoInfo);
                            }else{
                                continue;
                            }
                        }
                        if(riskLevel == 4){
                            if(subDays > 180){
                                //3.1发邮件
                                mailSenderService.send(mailTo, subject, text.toString());
                                //3.2添加待办事项
                                TodoInfo todoInfo =createTodoInfo(suppId, userId, suppName);
                                todoInfoList.add(todoInfo);
                            }else{
                                continue;
                            }
                        }
                        if(riskLevel == 5){
                            if(subDays > 90){
                                //3.1发邮件
                                mailSenderService.send(mailTo, subject, text.toString());
                                //3.2添加待办事项
                                TodoInfo todoInfo =createTodoInfo(suppId, userId, suppName);
                                todoInfoList.add(todoInfo);
                            }else{
                                continue;
                            }
                        }
                    }
                    //
                }
            }
            if(todoInfoList.size() > 0){
                todoInfoDao.save(todoInfoList);
            }
            logger.info("供应商审核提醒成功！");
        }catch(Exception e){
            e.printStackTrace();
            logger.error("供应商审核提醒失败！", e);
        }
    }

    //计算相差天数
    public int getDays(Date start, Date end) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return (int) ((df.parse(df.format(end)).getTime() -df.parse(df.format(start)).getTime()) / (24 * 60 * 60 * 1000));
    }

    //创建待办事项（供应商体系审核定时提醒）
    public TodoInfo createTodoInfo(Long suppId, Long userId, String suppName){
        TodoInfo todoInfo = new TodoInfo();
        todoInfo.setBsUserId(userId);   //系统用户
        todoInfo.setBsReferId(suppId);
        todoInfo.setBsRouter("/qms/supplier/createApprovedRecord");
        todoInfo.setBsTitle("[供应商体系审核]" + suppName + "需要发起新的审核");                   //标题
        todoInfo.setBsStatus(BasicEnumConstants.TODO_NOFINISH);
        todoInfo.setBsSystemType(BasicStateEnum.TODO_QMSTYPE.intValue());
        todoInfo.setBsType(BasicEnumConstants.TODO_SUPP_SETUP);      //待办类型
        todoInfo.setBsExtend(suppId);
        todoInfo.setBsCreatedTime(new Date());
        todoInfo.setPkCreatedBy(new Long(1));
        todoInfo.setBsStartTime(new Date());     //有效起始时间
        //计算一周后的时间
        Calendar curr = Calendar.getInstance();
        curr.setTime(todoInfo.getBsStartTime());
        curr.add(Calendar.WEEK_OF_YEAR, 1);
        Date after7Days=curr.getTime();
        todoInfo.setBsEndTime(after7Days);                  //有效结束时间
        return todoInfo;
    }

    /**
     * 待办提醒功能
     */
    @Scheduled(cron = "0 12 14 * * ? ")
    @Transactional
    public void todoRemindSys(){
        try{
            //查询未处理的待办事项
            //(1)同一个关联ID和同一个用户ID的有多个未处理的待办事项,(2)同一个关联ID和不同用户ID的有多个未处理的待办事项
            List<SearchFilter> filters = new ArrayList<SearchFilter>();
            filters.add(new SearchFilter("bsStatus", SearchFilter.Operator.EQ, BaseEnumConstants.FALSE));
            Specifications<TodoInfo> spec = Specifications.where(BaseOprService.and(filters, TodoInfo.class));
            List<TodoInfo> todoInfoList = todoInfoDao.findAll(spec);

            //循环判断是否需要提醒
            for(int i = 0; i < todoInfoList.size(); i++){
                TodoInfo todoInfo = todoInfoList.get(i);
                Date endDate = todoInfo.getBsEndTime();   //待办事项截止时间
                if(endDate == null){
                    continue;
                }
                Date nowDate = new Date();
                Integer subDays = getDays(nowDate, endDate);
                if(subDays <= 3){
                    //发送邮件
                    //先判断是否开启了邮件功能，开启了就可以发邮件
                    String flagMail = appConfig.getString("scm.mail.enabled");
                    if(StringUtils.isNotEmpty(flagMail) && flagMail.equals("true")){
                        SysUser sysUser = sysUserDao.findOne(todoInfo.getBsUserId());
                        if(sysUser != null && StringUtils.isNotEmpty(sysUser.getBsEmail())){
                            String nameTo = sysUser.getBsName();    //收件人名
                            String mailTo = sysUser.getBsEmail().trim();   //收件人邮箱
                            String subject = "待办事项提醒";    //主题
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                            String dateStr = simpleDateFormat.format(new Date());
                            StringBuffer text = new StringBuffer();   //邮件内容
                            text.append("<div style='padding: 5px;'>" + nameTo + "，" + "</div>");
                            text.append("<div style='padding: 5px;'>" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;你好！" + todoInfo.getBsTitle() +"。距离待办事项截止时间不足三天，请及时处理！</div>");
                            text.append("<div style='padding: 5px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请<a href='http://www.unind.net'>点击此处</a>进入QMS系统。</div>");
                            text.append("<div style='padding: 5px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;此致</div>");
                            text.append("<div style='padding: 5px;'>敬礼</div>");
                            text.append("<div style='padding: 5px; float: right; margin: 0 50px 0 0;'>QMS系统</div><br>");
                            text.append("<div style='padding: 5px; float: right; margin: 0 20px 0 0;clear: both;'>" + dateStr + "</div><br>");
                            mailSenderService.send(mailTo, subject, text.toString());
                        }
                    }
                }else{
                    continue;
                }
            }
            logger.info("待办事项提醒成功！");
        }catch(Exception e){
            e.printStackTrace();
            logger.error("待办事项提醒失败！",e);
        }
    }

    /**
     * 更新新产品创建时间
     */
//    @Scheduled(cron = "0 17 11 * * ? ")
//    @Scheduled(cron = "0 0/3 * * * ? ")
    @Transactional
    public void updateProductDate(){
        String templatePath = "D:" + File.separator + "三个月内新产品.xlsx";
        try {
            InputStream inputStream = new FileInputStream(templatePath);
            XSSFWorkbook wb = null;   //创建一个工作簿
            wb = new XSSFWorkbook(inputStream);
            Sheet sheet = wb.getSheetAt(0); //获取第一张表
            for(int i = 1; i < sheet.getLastRowNum(); i++){
                String prCode = "";  //产品编号
                Date date = new Date();  //产品时间

                //获取产品编号
                RichTextString prCodeText = sheet.getRow(i).getCell(2).getRichStringCellValue();
                if(prCodeText != null){
                    prCode = prCodeText.getString();
                }
                //获取产品时间
                Cell cell = sheet.getRow(i).getCell(4);
                if(DateUtil.isCellDateFormatted(cell)){
                    date = cell.getDateCellValue();
                }

                List<ProductInfo> productInfoList = productInfoDao.findByBsIsDelAndBsPrCode(BooleanStateEnum.FALSE.intValue(), prCode);
                if(productInfoList.size() > 0){
                    for(int j=0;j<productInfoList.size();j++) {
                        ProductInfo productInfo = productInfoList.get(j);
                        productInfo.setBsCreatedTime(date);
                        productInfo.setBsRiskLevel(5);
                        productInfo.setBsRiskLevelSys(5);
                        productInfo.setBsRiskTimeSys(new Date());
                        productInfoDao.save(productInfo);

                        List<ProductRisk> productRiskList = productRiskDao.findByBsIsDelAndBsPrIdOrderByBsCreatedTimeDesc(BooleanStateEnum.FALSE.intValue(), productInfo.getId());
                        if (productRiskList.size() > 0) {
                            ProductRisk productRisk = productRiskList.get(0);
                            productRisk.setBsProjectType(1);
                            productRisk.setBsRiskLevel(5);
                            productRisk.setBsModifiedTime(new Date());
                            productRiskDao.save(productRisk);
                        }
                    }
                }
            }
            logger.info("新产品时间更新完成！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

