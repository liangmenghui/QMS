package com.unind.qms.web.risk.service.internal;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.unind.base.components.mail.MailSenderService;
import com.unind.base.configure.AppConfig;
import com.unind.base.dao.admin.SysUserDao;
import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.domain.admin.user.SysUser;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.context.SessionContextUtils;
import com.unind.qms.enumeration.ApprovedStateEnum;
import com.unind.qms.enumeration.BasicEnumConstants;
import com.unind.qms.enumeration.BasicStateEnum;
import com.unind.qms.provider.HttpClientService;
import com.unind.qms.web.approved.dao.ApprovedEHSMapDao;
import com.unind.qms.web.approved.dao.ApprovedEHSRecordDao;
import com.unind.qms.web.approved.dao.ApprovedFlowRecordDao;
import com.unind.qms.web.approved.entity.ApprovedEHSMap;
import com.unind.qms.web.approved.entity.ApprovedEHSRecord;
import com.unind.qms.web.approved.entity.ApprovedFlowRecord;
import com.unind.qms.web.basic.dao.FeedbackInfoDao;
import com.unind.qms.web.basic.dao.FeedbackInfoExtraDao;
import com.unind.qms.web.basic.dao.TodoInfoDao;
import com.unind.qms.web.basic.entity.FeedbackInfo;
import com.unind.qms.web.basic.entity.FeedbackInfoExtra;
import com.unind.qms.web.basic.entity.TodoInfo;
import com.unind.qms.web.product.dao.ProductInfoDao;
import com.unind.qms.web.product.entity.ProductInfo;
import com.unind.qms.web.risk.dao.ProductRiskDao;
import com.unind.qms.web.risk.dao.RiskApprovedRecordDao;
import com.unind.qms.web.risk.dao.SupplierRiskDao;
import com.unind.qms.web.risk.entity.ProductRisk;
import com.unind.qms.web.risk.entity.SupplierRisk;
import com.unind.qms.web.risk.service.SupplierRiskService;
import com.unind.qms.web.supplier.dao.CustomerApprovedRecordDao;
import com.unind.qms.web.supplier.dao.SupplierInfoDao;
import com.unind.qms.web.supplier.entity.CustomerApprovedRecord;
import com.unind.qms.web.supplier.entity.SupplierInfo;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 供应商风险管理信息
 * @author Shen
 */
@Service
@Transactional(rollbackFor = BusinessException.class)
public class SupplierRiskImpl extends BaseOprService implements SupplierRiskService {

    @Autowired
    private SupplierRiskDao supplierRiskDao;
    @Autowired
    private SupplierInfoDao supplierInfoDao;
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private ProductRiskDao productRiskDao;
    @Autowired
    private FeedbackInfoDao feedbackInfoDao;
    @Autowired
    private FeedbackInfoExtraDao feedbackInfoExtraDao;
    @Autowired
    private RiskApprovedRecordDao riskApprovedRecordDao;
    @Autowired
    private TodoInfoDao todoInfoDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private CustomerApprovedRecordDao customerApprovedRecordDao;
    @Autowired
    private ApprovedFlowRecordDao approvedFlowRecordDao;
    @Autowired
    private HttpClientService httpClientService;
    @Autowired
    private ApprovedEHSMapDao approvedEHSMapDao;
    @Autowired
    private ApprovedEHSRecordDao approvedEHSRecordDao;
    @Autowired
    private AppConfig appConfig;

    /**
     * 添加供应商风险管理信息
     * @param supplierRisk
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public ApiResponseResult add(SupplierRisk supplierRisk) throws BusinessException {
        if(null == supplierRisk || null == supplierRisk.getBsSuppId()){
            return ApiResponseResult.failure("供应商ID为必填项");
        }
        int count = supplierRiskDao.countByBsSuppId(supplierRisk.getBsSuppId());
        if(count > 0){
            return ApiResponseResult.failure("供应商已存在");
        }
        //1.添加创建时间
        supplierRisk.setBsCreatedTime(new Date());

        //2.添加type值
        //所有得分类型默认为1
        supplierRisk.setBsFeedbackType((supplierRisk.getBsFeedbackType() == 0) ? 1 : supplierRisk.getBsFeedbackType());
        supplierRisk.setBsPpmType((supplierRisk.getBsPpmType() == 0) ? 1 : supplierRisk.getBsPpmType());
        supplierRisk.setBsDeliveryType((supplierRisk.getBsDeliveryType() == 0) ? 1 : supplierRisk.getBsDeliveryType());
        supplierRisk.setBsInspectType((supplierRisk.getBsInspectType() == 0) ? 1 : supplierRisk.getBsInspectType());
        supplierRisk.setBsPaymentType((supplierRisk.getBsPaymentType() == 0) ? 1 : supplierRisk.getBsPaymentType());
        supplierRisk.setBsCpkType((supplierRisk.getBsCpkType() == 0) ? 1 : supplierRisk.getBsCpkType());
        supplierRisk.setBsDangerProType((supplierRisk.getBsDangerProType() == 0) ? 1 : supplierRisk.getBsDangerProType());
        supplierRisk.setBsApproveType((supplierRisk.getBsApproveType() == 0) ? 1 : supplierRisk.getBsApproveType());
        supplierRisk.setBsEhsType((supplierRisk.getBsEhsType() == 0) ? 1 : supplierRisk.getBsEhsType());

        //3.添加value值，并修改对应type值
        supplierRisk = updateValueType(supplierRisk);

        //4.计算各项得分和总得分
        supplierRisk.setBsFeedbackScore(supplierRisk.getBsFeedbackType() * 3);
        supplierRisk.setBsPpmScore(supplierRisk.getBsPpmType() * 2);
        supplierRisk.setBsDeliveryScore(supplierRisk.getBsDeliveryType() * 3);
        supplierRisk.setBsInspectScore(supplierRisk.getBsInspectType() * 2);
        supplierRisk.setBsPaymentScore(supplierRisk.getBsPaymentType() * 1);
        supplierRisk.setBsCpkScore(supplierRisk.getBsCpkType() * 2);
        supplierRisk.setBsDangerProScore(supplierRisk.getBsDangerProType() * 2);
        supplierRisk.setBsApprovedScore(supplierRisk.getBsApproveType() * 2);
        supplierRisk.setBsEhsScore(supplierRisk.getBsEhsType() * 2);
        supplierRisk.setBsBusinessScore(((supplierRisk.isBsComStability() ? 1 : 0) + (supplierRisk.isBsAntiCorruptProtocol() ? 1 : 0 )
                + (supplierRisk.isBsLongTermProtocol() ? 1 : 0 ) + (supplierRisk.isBsCooperProtocol() ? 1 : 0 )
                + (supplierRisk.isBsBusinessBroScore() ? 1 : 0)) * 2);
        supplierRisk.setBsRiskScore(supplierRisk.getBsFeedbackScore() + supplierRisk.getBsPpmScore() + supplierRisk.getBsDeliveryScore() + supplierRisk.getBsInspectScore()
                + supplierRisk.getBsPaymentScore() + supplierRisk.getBsCpkScore() + supplierRisk.getBsDangerProScore() + supplierRisk.getBsApprovedScore()
                + supplierRisk.getBsEhsScore() + supplierRisk.getBsBusinessScore());

        //5.统计风险等级（1-5级）
        //判断直升高风险类型是否达到高风险（type为5），满足要求直升高风险
        int level = 1;
        if(supplierRisk.getBsFeedbackType() == 5 || supplierRisk.getBsDeliveryType() == 5){
            //直升高风险
            level = 5;
        }else{
            int totalScore = supplierRisk.getBsRiskScore();
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
        supplierRisk.setBsRiskLevel(level);
        supplierRiskDao.save(supplierRisk);

        //6.更新到供应商信息表的系统风险等级
        SupplierInfo supplierInfo = supplierInfoDao.findOne(supplierRisk.getBsSuppId());
        if(supplierInfo != null){
            supplierInfo.setBsRiskLevelSys(level);
            supplierInfo.setBsRiskTimeSys(new Date());
            supplierInfo.setBsModifiedTime(new Date());
            supplierInfo.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
            supplierInfo.setBsRiskScore(supplierRisk.getBsRiskScore());
            supplierInfo.setBsRiskLevel(level);
//            //如果供应商风险管理模式为自动，则修改风险等级；如果为手动，则不必修改
//            if(supplierInfo.getBsRiskManual() == BooleanStateEnum.FALSE.intValue()){
//                supplierInfo.setBsRiskScore(supplierRisk.getBsRiskScore());
//                supplierInfo.setBsRiskLevel(level);
//            }
        }

        return ApiResponseResult.success("新增成功！").data(supplierRisk);
    }

    /**
     * 修改供应商风险管理信息
     * @param supplierRisk
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional
    public ApiResponseResult edit(SupplierRisk supplierRisk) throws BusinessException {
        if(null == supplierRisk || null == supplierRisk.getId()){
            return ApiResponseResult.failure("记录ID为必填项");
        }
        if(null == supplierRisk.getBsSuppId()){
            return ApiResponseResult.failure("供应商ID为必填项");
        }
        SupplierRisk o = supplierRiskDao.findOne(supplierRisk.getId());
        if(null == o){
            return ApiResponseResult.failure("记录ID不存在或已被删除");
        }

        //原风险等级
        int originalRiskLevel = o.getBsRiskLevel();

        //1.修改时间
        o.setBsModifiedTime(new Date());
        o.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
        o.setBsFeedbackType(supplierRisk.getBsFeedbackType());
        o.setBsPpmType(supplierRisk.getBsPpmType());
        o.setBsDeliveryType(supplierRisk.getBsDeliveryType());
        o.setBsInspectType(supplierRisk.getBsInspectType());
        o.setBsPaymentType(supplierRisk.getBsPaymentType());
        o.setBsCpkType(supplierRisk.getBsCpkType());
        o.setBsDangerProType(supplierRisk.getBsDangerProType());
        o.setBsApproveType(supplierRisk.getBsApproveType());
        o.setBsEhsType(supplierRisk.getBsEhsType());
        o.setBsComStability(supplierRisk.isBsComStability());
        o.setBsAntiCorruptProtocol(supplierRisk.isBsAntiCorruptProtocol());
        o.setBsLongTermProtocol(supplierRisk.isBsLongTermProtocol());
        o.setBsCooperProtocol(supplierRisk.isBsCooperProtocol());
        o.setBsBusinessBroScore(supplierRisk.isBsBusinessBroScore());
        //修改value值
        o.setBsEhsValue(supplierRisk.getBsEhsValue());
        o.setBsPpmValue(supplierRisk.getBsPpmValue());
        o.setBsCpkValue(supplierRisk.getBsCpkValue());
        o.setBsInspectValue(supplierRisk.getBsInspectValue());

        //2.添加value值，并修改对应type值
        o = updateValueType(o);

        //3.计算各项得分和总得分
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
        o.setBsRiskScore(o.getBsFeedbackScore() + o.getBsPpmScore() + o.getBsDeliveryScore() + o.getBsInspectScore()
                + o.getBsPaymentScore() + o.getBsCpkScore() + o.getBsDangerProScore() + o.getBsApprovedScore()
                + o.getBsEhsScore() + o.getBsBusinessScore());

        //4.统计风险等级（1-5级）
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
        supplierRiskDao.save(o);

        //5.添加待办事项，给宁总
        //如果原来等级与现在等级不同，发待办事项给宁总
        //注意：这个待办事项只是通知宁总，不需要批准，看了之后手动关闭就行
        if(originalRiskLevel != level) {
            //获取批准人
            Long approverId = (long) 1;
            Map<String, Object> param = new HashMap<>();
            param.put("roleCode", "QMS_QUALITY_MANAGER");  //风险-----宁总
            StringBuffer sqlbf1 = new StringBuffer();
            sqlbf1.append(" SELECT a.pk_user FROM sys_user_roles_agg a LEFT JOIN sys_role b ON a.pk_role = b.id");
            sqlbf1.append(" LEFT JOIN sys_user c on c.id = a.pk_user");
            sqlbf1.append(" WHERE b.bs_code = :roleCode AND c.bs_is_del = '0'");
            Map<String, Object> recorderMap = super.findOneBySql(sqlbf1.toString(), param);
            if(recorderMap != null){
                BigDecimal userId = (BigDecimal) recorderMap.get("PK_USER");
                approverId = userId.longValue();
            }

            //添加待办事项
            TodoInfo todoInfo = new TodoInfo();
            todoInfo.setBsUserId(approverId);
            todoInfo.setBsReferId(o.getBsSuppId());   //关联ID（存放供应商ID）
            todoInfo.setBsExtend(o.getBsSuppId());   //扩展（同关联ID）
            todoInfo.setBsRouter("/qms/supplier/riskManagement");
            SupplierInfo supplierInfo = supplierInfoDao.findOne(o.getBsSuppId());
            String supplierName = "";
            if(supplierInfo != null){
                supplierName = supplierInfo.getBsSuppChieseName();
            }
            //设置待办标题
            String title = "[风险等级变更批准]" + supplierName + "风险等级由" + originalRiskLevel +"级变更为" + level + "级";
            todoInfo.setBsTitle(title);
            todoInfo.setBsStatus(BasicEnumConstants.TODO_NOFINISH);
            todoInfo.setBsSystemType(BasicStateEnum.TODO_QMSTYPE.intValue());
            todoInfo.setBsType(BasicEnumConstants.TODO_SUPP_RISK);
            todoInfo.setBsCreatedTime(new Date());   //创建时间
            todoInfo.setPkCreatedBy(SessionContextUtils.getCurrentUser().getId());
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
                SysUser sysUser = sysUserDao.findOne(approverId);
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

        //6.更新到供应商信息表的系统风险等级
        SupplierInfo supplierInfo = supplierInfoDao.findOne(o.getBsSuppId());
        if(supplierInfo != null){
            supplierInfo.setBsRiskLevelSys(level);
            supplierInfo.setBsRiskTimeSys(new Date());
            supplierInfo.setBsModifiedTime(new Date());
            supplierInfo.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
            supplierInfo.setBsRiskScore(o.getBsRiskScore());
            supplierInfo.setBsRiskLevel(level);
//            //如果供应商风险管理模式为自动，则修改风险等级；如果为手动，则不必修改
//            if(supplierInfo.getBsRiskManual() == BooleanStateEnum.FALSE.intValue()){
//                supplierInfo.setBsRiskScore(o.getBsRiskScore());
//                supplierInfo.setBsRiskLevel(level);
//            }
            supplierInfoDao.save(supplierInfo);
        }

        return ApiResponseResult.success("修改成功！").data(o);
    }

    /**
     * 删除供应商风险管理信息
     * @param id
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional
    public ApiResponseResult delete(Long id) throws BusinessException {
        SupplierRisk o = supplierRiskDao.findOne(id);
        if(null == o){
            return ApiResponseResult.failure("记录ID不存在或已被删除");
        }
        if (o.getId() <= 0) {
            return ApiResponseResult.failure("没有操作权限");
        }
        o.setBsIsDel(BooleanStateEnum.TRUE.intValue());
        supplierRiskDao.save(o);
        return ApiResponseResult.success("删除成功！");
    }

    /**
     * 获取供应商风险管理信息
     * @param bsSuppId
     * @param pageRequest
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional(readOnly = true)
    public ApiResponseResult getlist(Long bsSuppId, PageRequest pageRequest) throws BusinessException {
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BaseEnumConstants.FALSE));
        if(bsSuppId != null){
            filters.add(new SearchFilter("bsSuppId", SearchFilter.Operator.EQ, bsSuppId));
        }
        Specifications<SupplierRisk> spec = Specifications.where(super.and(filters, SupplierRisk.class));
        Page<SupplierRisk> page = supplierRiskDao.findAll(spec, pageRequest);
        return ApiResponseResult.success().data(Datagrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));

    }

    /**
     * 更新产品风险信息表的value及type
     * @param supplierRisk
     * @return
     * @throws BusinessException
     */
    public SupplierRisk updateValueType(SupplierRisk supplierRisk) throws BusinessException{
        //供应商的ppm值取该供应商下的所有产品的不良品之和与检验数之和的比值，计算公式：(不良品/检验数) * 10^6
        //供应商cpk值取该供应商下的所有产品的最小cpk值
        //供应商验货不合格次数取该供应商下所有产品之和
        //供应商高风险产品占比，用户自己在页面选择
        //供应商最近一次客诉
        //供应商付款期
        //供应商EHS，根据EHS审核的不符合项的条数判断
        //供应商体系审核项，客户审核+金合联审核
        double ppmValue = 0;
        double ppmResult = 0;  //PPM不良品
        double ppmNum = 0;     //PPM检验数
        double cpkValue = 0;
        double inspectValue = 0;
        boolean isTriple = false; //是否连续三次验货不合格
        int ehsValue = 0;      //EHS不符合项条数
        double dangerPro = 0;  //高风险产品产品产值（单位：元）
        int dangerProCount = 0;  //高风险产品数量
        Sort sort = new Sort(Sort.Direction.DESC, "bsCreatedTime");
        //1.通过suppId从供应商信息表找到suppCode
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BooleanStateEnum.FALSE.intValue()));
        filters.add(new SearchFilter("id", SearchFilter.Operator.EQ, supplierRisk.getBsSuppId()));
        Specification<SupplierInfo> spec = Specifications.where(super.and(filters, SupplierInfo.class));
        SupplierInfo supplierInfo = supplierInfoDao.findOne(spec);
        if(supplierInfo != null){
            //2.通过suppCode从产品信息表找到供应商对应的多个产品prId
            List<ProductInfo> productInfoList = productInfoDao.findByBsIsDelAndBsSuppCode(BooleanStateEnum.FALSE.intValue(), supplierInfo.getBsSuppCode());
            if(productInfoList.size() > 0){
                //定义cpkValue的List集合，获取所有cpkValue，以便后面比较的到最小值使用
                List<Double> cpkValueList = new ArrayList<Double>();

                for(ProductInfo productInfo : productInfoList) {
                    //3.通过产品prId从产品风险信息表找到ppm值，并计算所有ppm值之和
                    //比较cpk值，得到最小值
                    //计算所有不合格次数之和
                    List<ProductRisk> productRiskList = productRiskDao.findByBsIsDelAndBsPrIdOrderByBsCreatedTimeDesc(BooleanStateEnum.FALSE.intValue(), productInfo.getId());
                    if (productRiskList.size() > 0) {
                        //3.1供应商ppm值,获取所有产品的不良数之和及检验数之和
                        ppmResult = ppmResult + productRiskList.get(0).getBsPpmResult();
                        ppmNum = ppmNum + productRiskList.get(0).getBsPpmNum();
                        //3.2获取所有产品的cpk值
                        cpkValueList.add(productRiskList.get(0).getBsCpkValue());
                        //3.3验货不合格次数
                        inspectValue = inspectValue + productRiskList.get(0).getBsInspectValue();
                        if (productRiskList.get(0).getBsInspectType() == 5) {
                            isTriple = true;
                        }
                        //3.4统计高风险产品数量
                        if(productRiskList.get(0).getBsRiskLevel() == 5){
                            dangerProCount ++;
                            //比较高风险产品产值，取较大者（单位：元）
                            double dangerProTemp = getDangerPro(productInfo.getBsPrName());
                            if(dangerProTemp > dangerPro){
                                dangerPro = dangerProTemp;
                            }
                        }
                    }
                }
                //4修改value和type
                //4.1修改ppm的value和type
                //计算公式：(不良品/检验数) * 10^6
                if(ppmNum != 0){
                    ppmValue = (ppmResult / ppmNum) * 1000000;
                }
                supplierRisk.setBsPpmValue(ppmValue);
                if (ppmValue <= 50) {
                    supplierRisk.setBsPpmType(1);
                }
                if (ppmValue > 50 && ppmValue <= 200) {
                    supplierRisk.setBsPpmType(2);
                }
                if (ppmValue > 200 && ppmValue <= 500) {
                    supplierRisk.setBsPpmType(3);
                }
                if (ppmValue > 500 && ppmValue <= 1000) {
                    supplierRisk.setBsPpmType(4);
                }
                if (ppmValue > 1000) {
                    supplierRisk.setBsPpmType(5);
                }
                //4.2修改cpk的value和type
                //根据得到的cpkValueList集合，比较得到最小值
                if(cpkValueList.size() > 0){
                    cpkValue = cpkValueList.get(0);
                }
                for(int i = 0; i < cpkValueList.size(); i++){
                    if(cpkValueList.get(i) < cpkValue){
                        cpkValue = cpkValueList.get(i);
                    }
                }
                supplierRisk.setBsCpkValue(cpkValue);
                if (cpkValue < 0.8) {
                    supplierRisk.setBsCpkType(5);
                }
                if (cpkValue >= 0.8 && cpkValue < 1) {
                    supplierRisk.setBsCpkType(4);
                }
                if (cpkValue >= 1 && cpkValue < 1.33) {
                    supplierRisk.setBsCpkType(3);
                }
                if (cpkValue >= 1.33 && cpkValue < 1.67) {
                    supplierRisk.setBsCpkType(2);
                }
                if (cpkValue >= 1.67) {
                    supplierRisk.setBsCpkType(1);
                }
                //4.3修改不合格次数的value和type
                supplierRisk.setBsInspectValue(inspectValue);
                if (inspectValue == 0) {
                    supplierRisk.setBsInspectType(1);
                }
                if (inspectValue == 1) {
                    supplierRisk.setBsInspectType(2);
                }
                if (inspectValue >= 2) {
                    supplierRisk.setBsInspectType(3);
                }
                if (inspectValue >= 3) {
                    supplierRisk.setBsInspectType(4);
                }
                if (isTriple) {
                    //连续发生三次以上
                    supplierRisk.setBsInspectType(5);
                }
                //4.4修改高风险产品占比的count和type
                supplierRisk.setBsDangerProCount(dangerProCount);
                if(dangerPro < 100*10000 && dangerProCount == 0){
                    supplierRisk.setBsDangerProType(1);
                }
                if((dangerPro >= 100*10000 && dangerPro < 300*10000) || dangerProCount == 1){
                    supplierRisk.setBsDangerProType(3);
                }
                if((dangerPro >= 300*10000 && dangerPro < 600*10000) || dangerProCount == 2){
                    supplierRisk.setBsDangerProType(4);
                }
                if(dangerPro >= 600*10000 || dangerProCount >= 3){
                    supplierRisk.setBsDangerProType(5);
                }
            }
        }

        //5.取最近一次客诉时间判断type（通过供应商ID在客诉附加表找到客诉信息）
        List<FeedbackInfoExtra> feedbackInfoExtraList = feedbackInfoExtraDao.findByBsIsDelAndBsSuppIdOrderByBsCreatedTimeDesc(BooleanStateEnum.FALSE.intValue(), supplierRisk.getBsSuppId());
        if(feedbackInfoExtraList.size() <= 0){
            supplierRisk.setBsFeedbackType(1);
        }else{
            FeedbackInfoExtra feedbackInfoExtra = feedbackInfoExtraList.get(0);
            if(feedbackInfoExtra.getBsFeedbackId() != null && feedbackInfoExtra.getFeedbackInfo() != null){
                FeedbackInfo feedbackInfo = feedbackInfoExtra.getFeedbackInfo();
                //状态（1.未处理 2.处理中 3.已关闭）
                if(feedbackInfo.getBsStatus() == 1 || feedbackInfo.getBsStatus() == 2){
                    supplierRisk.setBsFeedbackType(5);
                }
                if(feedbackInfo.getBsStatus() == 3){
                    int feedbackDays = getDays(feedbackInfo.getBsCreatedTime(), new Date());
                    if(feedbackDays > 180){
                        supplierRisk.setBsFeedbackType(2);
                    }
                    if(feedbackDays > 90 && feedbackDays <= 180){
                        supplierRisk.setBsFeedbackType(3);
                    }
                    if(feedbackDays <= 90){
                        supplierRisk.setBsFeedbackType(4);
                    }
                }
            }
        }

        //6.根据供应商付款期来判断type（付款期从供应商信息表的bsPayCondition获取）
        //包括类型：立即付款、5天、7天、10天、15天、30天、45天、60天、90天、120天
        if(supplierInfo != null){
            String paymentDays = supplierInfo.getBsPayCondition().trim();
            if(paymentDays.equals("120天")){
                supplierRisk.setBsPaymentType(2);
            }
            if(paymentDays.equals("90天") || paymentDays.equals("60天") || paymentDays.equals("45天")){
                supplierRisk.setBsPaymentType(3);
            }
            if(paymentDays.equals("30天")){
                supplierRisk.setBsPaymentType(4);
            }
            if(paymentDays.equals("15天") || paymentDays.equals("10天") || paymentDays.equals("7天") ||
                    paymentDays.equals("5天") || paymentDays.equals("立即付款")){
                supplierRisk.setBsPaymentType(5);
            }
        }

        //7.根据EHS的value值来判断type
        //先在EHS审核条款记录关联表中筛选出该供应商下的最新一条记录ID，
        //接着在EHS审核条款记录中查找相关的审核条款记录，统计出不符合项（审核结论ehsEval为N时，该条款为不符合项）
        List<ApprovedEHSMap> approvedEHSMapList = approvedEHSMapDao.findByBsIsDelAndSupplierIdOrderByBsCreatedTimeDesc(BooleanStateEnum.FALSE.intValue(), supplierRisk.getBsSuppId());
        if(approvedEHSMapList.size() > 0){
            ApprovedEHSMap approvedEHSMap = approvedEHSMapList.get(0);
            if(approvedEHSMap != null){
                //获取到该供应商下的最新EHS审核的审核结论ehsEval为N审核条款记录
                List<ApprovedEHSRecord> approvedEHSRecordList = approvedEHSRecordDao.findByMapIdAndEhsEval(approvedEHSMap.getId(), "N");
                ehsValue = approvedEHSRecordList.size();
            }
        }
        supplierRisk.setBsEhsValue(ehsValue);
        if(ehsValue <= 2){
            supplierRisk.setBsEhsType(1);
        }
        if(ehsValue > 2 && ehsValue <= 3){
            supplierRisk.setBsEhsType(2);
        }
        if(ehsValue > 3 && ehsValue <= 4){
            supplierRisk.setBsEhsType(3);
        }
        if(ehsValue > 4 && ehsValue <= 5){
            supplierRisk.setBsEhsType(4);
        }
        if(ehsValue >= 6){
            supplierRisk.setBsEhsType(5);
        }

        //8.根据客户审核的结果 + 金合联体系审核的结果来判断体系审核的type
        //取最近的一次审核结果
        int isCusApproved = 1;   //客户审核结论（1.通过 2.不通过）
        int isOwnApproved = 1;   //金合联体系审核结论（0.未审核 1.通过 2.不通过）
        //8.1客户审核
        List<CustomerApprovedRecord> customerApprovedRecordList = customerApprovedRecordDao.findByBsIsDelAndBsSuppIdOrderByIdDesc(BooleanStateEnum.FALSE.intValue(), supplierRisk.getBsSuppId());
        if(customerApprovedRecordList.size() == 0){
            //当没有客户审核时，默认通过
            isCusApproved = 1;
        }else{
            //当客户审核结果为1时，通过；为2时，不通过
            CustomerApprovedRecord customerApprovedRecord = customerApprovedRecordList.get(0);
            if(customerApprovedRecord.getBsResult() ==1){
                isCusApproved = 1;
            }
            if(customerApprovedRecord.getBsResult() == 2){
                isCusApproved = 2;
            }
        }
        //8.2金合联体系审核
        List<ApprovedFlowRecord> approvedFlowRecordList = approvedFlowRecordDao.findByBsSuppIdAndBsType(supplierRisk.getBsSuppId(), ApprovedStateEnum.SUPP_APPROVED.intValue());
        if(approvedFlowRecordList.size() == 0){
            //当没有体系审核时，未审核
            isOwnApproved = 0;
        }else {
            //当体系审核结论为3时，通过；为1、2时，不通过
            ApprovedFlowRecord approvedFlowRecord = approvedFlowRecordList.get(0);
            if(approvedFlowRecord.getBsConclusion() == ApprovedStateEnum.CONCLUSION_SUPP_ALLOW.intValue()){
                isOwnApproved = 1;
            }
            if(approvedFlowRecord.getBsConclusion() == ApprovedStateEnum.CONCLUSION_SUPP_DISABLE.intValue()
                    || approvedFlowRecord.getBsConclusion() == ApprovedStateEnum.CONCLUSION_SUPP_POTENTIAL.intValue()){
                isOwnApproved = 2;
            }
        }
        //得出体系审核的type
        if(isCusApproved == 1 && isOwnApproved == 1){
            supplierRisk.setBsApproveType(1);
        }
        if(isCusApproved == 1 && isOwnApproved == 0){
            supplierRisk.setBsApproveType(2);
        }
        if(isCusApproved == 1 && isOwnApproved == 2){
            supplierRisk.setBsApproveType(3);
        }
        if(isCusApproved == 2 && isOwnApproved == 1){
            supplierRisk.setBsApproveType(4);
        }
        if(isCusApproved == 2 && isOwnApproved == 2){
            supplierRisk.setBsApproveType(5);
        }

        return supplierRisk;
    }

    /**
     * 计算相差天数
     * @param start
     * @param end
     * @return
     */
    public int getDays(Date start, Date end) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return (int) ((df.parse(df.format(end)).getTime() - df.parse(df.format(start)).getTime()) / (24 * 60 * 60 * 1000));
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 更新供应商风险信息表ppm的value值和type值
     * @param supplierRisk
     * @return
     * @throws BusinessException
     */
    public SupplierRisk updatePpmValueType(SupplierRisk supplierRisk) throws BusinessException{
        //供应商的ppm值取该供应商下的所有产品的不良品之和与检验数之和的比值，计算公式：(不良品/检验数) * 10^6
        double ppmValue = 0;
        double ppmResult = 0;  //PPM不良品
        double ppmNum = 0;     //PPM检验数

        //1.通过suppId从供应商信息表找到suppCode
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BooleanStateEnum.FALSE.intValue()));
        filters.add(new SearchFilter("id", SearchFilter.Operator.EQ, supplierRisk.getBsSuppId()));
        Specification<SupplierInfo> spec = Specifications.where(super.and(filters, SupplierInfo.class));
        SupplierInfo supplierInfo = supplierInfoDao.findOne(spec);
        if(supplierInfo != null){
            //2.通过suppCode从产品信息表找到供应商对应的多个产品prId
            List<ProductInfo> productInfoList = productInfoDao.findByBsIsDelAndBsSuppCode(BooleanStateEnum.FALSE.intValue(), supplierInfo.getBsSuppCode());
            if(productInfoList.size() > 0){

                for(ProductInfo productInfo : productInfoList) {
                    //3.通过产品prId从产品风险信息表找到ppm值，并计算所有ppm值之和
                    List<ProductRisk> productRiskList = productRiskDao.findByBsIsDelAndBsPrIdOrderByBsCreatedTimeDesc(BooleanStateEnum.FALSE.intValue(), productInfo.getId());
                    if (productInfoList.size() > 0) {
                        //3.1供应商ppm值,获取所有产品的不良数之和及检验数之和
                        ppmResult = ppmResult + productRiskList.get(0).getBsPpmResult();
                        ppmNum = ppmNum + productRiskList.get(0).getBsPpmNum();
                    }
                }

                //4修改ppm的value和type
                //计算公式：(不良品/检验数) * 10^6
                if(ppmNum != 0){
                    ppmValue = (ppmResult / ppmNum) * 1000000;
                }
                supplierRisk.setBsPpmValue(ppmValue);
                if (ppmValue <= 50) {
                    supplierRisk.setBsPpmType(1);
                }
                if (ppmValue > 50 && ppmValue <= 200) {
                    supplierRisk.setBsPpmType(2);
                }
                if (ppmValue > 200 && ppmValue <= 500) {
                    supplierRisk.setBsPpmType(3);
                }
                if (ppmValue > 500 && ppmValue <= 1000) {
                    supplierRisk.setBsPpmType(4);
                }
                if (ppmValue > 1000) {
                    supplierRisk.setBsPpmType(5);
                }
            }
        }
        return supplierRisk;
    }

    /**
     * 从PMP系统获取产品产值
     * @param prName
     * @return
     * @throws BusinessException
     */
    public double getDangerPro(String prName) throws BusinessException{
        double dangerPro = 0;
        StringBuffer url = new StringBuffer();

        //访问地址：http://192.168.21.173:8165/openapi/v1.0/prod/GET_MULTPO_AMOUNT?p_cust_item_num=242198303&p_date_from=20170814&p_date_to=20180814
        //返回数据：{"result":true,"msg":"0","data":{"x_return_amount":65944.62},"status":"0","count":0}，其中字段x_return_amount的单位是元
        if(StringUtils.isNotEmpty(prName)){
            //1.从产品名称中获取部件号（部件号可能存在的符号：- ？数字 字母）
            prName = prName.trim();
            String itemNum = "";  //部件号
            String chineseName = "";  //产品名称
            for(int i = 0; i < prName.length(); i++){
                if(!(prName.charAt(i) >= '-' && prName.charAt(i) <= 'z')){
                    itemNum = prName.substring(0, i);
                    chineseName = prName.substring(i, prName.length());
                    break;
                }
            }

            //2.调用接口路径
            if(StringUtils.isNotEmpty(itemNum)){
                //获取pmp项目路径
                String pmpAddress = appConfig.getString("pmp.address");
                if(StringUtils.isNotEmpty(pmpAddress)){
                    return dangerPro;
                }
                //获取日期，计算去年的产品产值
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int lastYear = year - 1;
                String dateFrom = lastYear + "0101";
                String dateTo = lastYear + "1231";
                //接口
                url.append(pmpAddress + "/openapi/v1.0/prod/GET_MULTPO_AMOUNT");
                url.append("?p_cust_item_num=" + itemNum);
                url.append("&p_date_from=" + dateFrom + "&p_date_to=" + dateTo);
            }

            //接口不存在
            if(url == null || url.length() <= 0){
                return dangerPro;
            }

            //3.从PMP系统获取数据
            ApiResponseResult result = httpClientService.getHttpClient(url.toString());
            if(result == null || result.getData() == null){
                return dangerPro;
            }
            JSONObject data= new JSONObject((String)result.getData());
            if(data != null){
                JSONObject data2 = data.getJSONObject("data");
                if(data2 != null){
                    String value = data2.getString("x_return_amount");
                    if(StringUtils.isNotEmpty(value) && value != "null"){
                        dangerPro = Double.parseDouble(value);
                    }
                }
            }
        }

        return dangerPro;
    }

}
