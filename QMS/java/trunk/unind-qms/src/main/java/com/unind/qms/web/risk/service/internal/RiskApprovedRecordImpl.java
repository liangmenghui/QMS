package com.unind.qms.web.risk.service.internal;

import com.google.gson.JsonObject;
import com.unind.base.components.mail.MailSenderService;
import com.unind.base.configure.AppConfig;
import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.domain.admin.user.SysUser;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.context.SessionContextUtils;
import com.unind.qms.enumeration.BasicEnumConstants;
import com.unind.qms.enumeration.BasicStateEnum;
import com.unind.qms.web.basic.dao.TodoInfoDao;
import com.unind.qms.web.basic.entity.TodoInfo;
import com.unind.qms.web.product.dao.ProductInfoDao;
import com.unind.qms.web.product.entity.ProductInfo;
import com.unind.qms.web.risk.dao.ProductRiskDao;
import com.unind.qms.web.risk.dao.RiskApprovedRecordDao;
import com.unind.qms.web.risk.dao.SupplierRiskDao;
import com.unind.qms.web.risk.entity.ProductRisk;
import com.unind.qms.web.risk.entity.RiskApprovedRecord;
import com.unind.qms.web.risk.service.RiskApprovedRecordService;
import com.unind.qms.web.supplier.dao.SupplierInfoDao;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 风险批准记录表
 * @author Shen
 */
@Service
@Transactional(rollbackFor = BusinessException.class)
public class RiskApprovedRecordImpl extends BaseOprService implements RiskApprovedRecordService {
    @Autowired
    private RiskApprovedRecordDao riskApprovedRecordDao;
    @Autowired
    private ProductRiskDao productRiskDao;
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private SupplierRiskDao supplierRiskDao;
    @Autowired
    private SupplierInfoDao supplierInfoDao;
    @Autowired
    private TodoInfoDao todoInfoDao;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private AppConfig appConfig;

    @Override
    @Transactional
    public ApiResponseResult edit(RiskApprovedRecord riskApprovedRecord) throws BusinessException {
        if(riskApprovedRecord == null){
            return ApiResponseResult.failure("不能为空！");
        }
        if(riskApprovedRecord.getId() == null){
            return ApiResponseResult.failure("记录ID为必填项");
        }
        RiskApprovedRecord o = riskApprovedRecordDao.findOne(riskApprovedRecord.getId());
        o.setBsNewRiskLevel(riskApprovedRecord.getBsNewRiskLevel());
        o.setBsApprovedAdvice(riskApprovedRecord.getBsApprovedAdvice());
        o.setBsStatus(riskApprovedRecord.getBsStatus());
        o.setBsModifiedTime(new Date());
        return ApiResponseResult.success("修改成功！");
    }

    @Override
    @Transactional
    public ApiResponseResult getlist(Long id, Long bsPrId, Long bsSuppId, Integer bsStatus, PageRequest pageRequest) throws BusinessException {
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BaseEnumConstants.FALSE));
        if (id != null) {
            filters.add(new SearchFilter("id", SearchFilter.Operator.EQ, id));
        }
        if (bsPrId != null) {
            filters.add(new SearchFilter("bsPrId", SearchFilter.Operator.EQ, bsPrId));
        }
        if(bsSuppId != null){
            filters.add(new SearchFilter("bsSuppId", SearchFilter.Operator.EQ, bsSuppId));
        }
        if(bsStatus != null){
            filters.add(new SearchFilter("bsStatus", SearchFilter.Operator.EQ, bsStatus));
        }
        Specifications<RiskApprovedRecord> spec = Specifications.where(super.and(filters, RiskApprovedRecord.class));
        Page<RiskApprovedRecord> page = riskApprovedRecordDao.findAll(spec, pageRequest);
        return ApiResponseResult.success().data(Datagrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));

    }

    /**
     * 产品风险等级更改批准
     * @param id
     * @param bsApprovedAdvice
     * @param bsStatus
     * @return
     * @throws BusinessException
     */
    @Override
    @Transactional
    public ApiResponseResult approve(Long id, String bsApprovedAdvice, Integer bsStatus) throws BusinessException{
        if(id == null){
            return ApiResponseResult.failure("记录ID不能为零！");
        }

        //1.更新风险批准记录表
        RiskApprovedRecord o = riskApprovedRecordDao.findOne(id);
        if(o == null){
            return ApiResponseResult.failure("记录ID不存在！");
        }
        o.setBsApprovedAdvice(bsApprovedAdvice);
        o.setBsStatus(bsStatus);   //状态（0:进行中, 1:同意, 2:拒绝）
        o.setBsModifiedTime(new Date());
        riskApprovedRecordDao.save(o);

        //2.关闭待办事项
        if(o.getBsPrId() != null){
            todoInfoDao.closeByBsReferIdAndBsType(o.getBsPrId(), BasicEnumConstants.TODO_PR_RISK);
        }

        //3.更新产品风险信息表的风险
        if (o.getBsPrId() != null) {
            List<ProductRisk> productRiskList = productRiskDao.findByBsIsDelAndBsPrIdOrderByBsCreatedTimeDesc(BooleanStateEnum.FALSE.intValue(), o.getBsPrId());
            if (productRiskList.size() > 0) {
                ProductRisk productRisk = productRiskList.get(0);
                productRisk.setBsStatus(0);   //状态改为完成（0.完成 1.进行中）
                productRiskDao.save(productRisk);
            }
        }

        //4.发待办事项给SQE
        //如果同意则不用，拒绝发送代办给SQE
        if(bsStatus == 2){
            //获取SQE信息
            Map<String, Object> param = new HashMap<>();
            param.put("bsPrId", o.getBsPrId());  //产品ID
            StringBuffer sqlbf1 = new StringBuffer();
            sqlbf1.append("SELECT a.* FROM sys_user a LEFT JOIN qms_product_info b ON a.bs_name = b.bs_sqe WHERE b.id = :bsPrId");
            List<SysUser> sysUserList = createSQLQuery(sqlbf1.toString(), param, SysUser.class);
            if(sysUserList.size() > 0){
                //添加待办事项
                TodoInfo todoInfo = new TodoInfo();
                todoInfo.setBsUserId(sysUserList.get(0).getId());
                todoInfo.setBsReferId(o.getBsPrId());   //关联ID（存放供应商ID）
                todoInfo.setBsExtend(o.getId());   //扩展（存放风险批准记录ID）
                todoInfo.setBsRouter("/qms/product/riskManagement");
                ProductInfo productInfo = productInfoDao.findOne(o.getBsPrId());
                String productName = "";
                if(productInfo != null){
                    productName = productInfo.getBsPrName();
                }
                //设置待办标题
                String title = "[风险等级变更拒绝]" + productName + "风险等级由" + o.getBsOriginalRiskLevel() +"级变更为" + o.getBsNewRiskLevel() + "级经理批准不通过";
                todoInfo.setBsTitle(title);
                todoInfo.setBsStatus(BasicEnumConstants.TODO_NOFINISH);
                todoInfo.setBsSystemType(BasicStateEnum.TODO_QMSTYPE.intValue());
                todoInfo.setBsType(BasicEnumConstants.TODO_PR_RISK);
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
                    if(StringUtils.isNotEmpty(sysUserList.get(0).getBsEmail())){
                        String nameTo = sysUserList.get(0).getBsName();    //收件人名
                        String mailTo = sysUserList.get(0).getBsEmail().trim();   //收件人邮箱
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
        }

        return ApiResponseResult.success().data(o);
    }

    //SQL查询
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
}
