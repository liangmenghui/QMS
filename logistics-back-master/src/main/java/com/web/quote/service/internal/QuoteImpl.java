package com.web.quote.service.internal;

import com.app.base.data.ApiResponseResult;
import com.app.base.data.DataGrid;
import com.system.user.entity.SysUser;
import com.utils.BaseService;
import com.utils.UserUtil;
import com.utils.enumeration.BasicStateEnum;
import com.web.enquiry.dao.EnquiryDao;
import com.web.enquiry.dao.EnquirySupplierDao;
import com.web.enquiry.entity.Enquiry;
import com.web.enquiry.entity.EnquirySupplier;
import com.web.quote.dao.QuoteDao;
import com.web.quote.dao.QuoteMaterielDao;
import com.web.quote.entity.Quote;
import com.web.quote.entity.QuoteMateriel;
import com.web.quote.service.QuoteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 新料报价表
 */
@Service(value = "QuoteService")
@Transactional(propagation = Propagation.REQUIRED)
public class QuoteImpl implements QuoteService {

    @Autowired
    private QuoteDao quoteDao;
    @Autowired
    private QuoteMaterielDao quoteMaterielDao;
    @Autowired
    private EnquiryDao enquiryDao;
    @Autowired
    private EnquirySupplierDao enquirySupplierDao;

    @Override
    @Transactional
    public ApiResponseResult add(Quote quote) throws Exception {
        if(quote == null){
            return ApiResponseResult.failure("记录不能为空！");
        }
        if(quote.getEqId() == null){
            return ApiResponseResult.failure("询价记录ID不能为空！");
        }
        SysUser currUser = UserUtil.getCurrUser();  //获取当前用户

        quote.setCreatedTime(new Date());
        quote.setPkCreatedBy((currUser!=null) ? (currUser.getId()) : null);
        quoteDao.save(quote);

        return ApiResponseResult.success("报价新增成功！");
    }

    @Override
    @Transactional
    public ApiResponseResult edit(Quote quote) throws Exception {
        if(quote == null || quote.getId() == null){
            return ApiResponseResult.failure("记录ID不能为空！");
        }
        Quote o = quoteDao.findById((long) quote.getId());
        if(o == null){
            return ApiResponseResult.failure("记录不存在！");
        }
        SysUser currUser = UserUtil.getCurrUser();  //获取当前用户

        o.setQtTitle(quote.getQtTitle());
        o.setQtStartDate(quote.getQtStartDate());
        o.setQtDeadLine(quote.getQtDeadLine());
        o.setQtDelDeadline(quote.getQtDelDeadline());
        o.setQtDelLocation(quote.getQtDelLocation());
        o.setQtAcceptType(quote.getQtAcceptType());
        o.setQtPayMethod(quote.getQtPayMethod());
        o.setQtDesc(quote.getQtDesc());
        o.setQtTotalPrice(quote.getQtTotalPrice());
        o.setModifiedTime(new Date());
        o.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
        quoteDao.save(o);

        return ApiResponseResult.success("报价修改成功！");
    }

    @Override
    @Transactional
    public ApiResponseResult delete(Long id) throws Exception {
        if(id == null){
            return ApiResponseResult.failure("记录ID不能为空！");
        }
        Quote o = quoteDao.findById((long) id);
        if(o == null){
            return ApiResponseResult.failure("记录不存在！");
        }
        SysUser currUser = UserUtil.getCurrUser();  //获取当前用户

        o.setIsDel(BasicStateEnum.TRUE.intValue());
        o.setModifiedTime(new Date());
        o.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
        quoteDao.save(o);

        return ApiResponseResult.failure("报价删除成功！");
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponseResult getlist(Integer qtStatus, String keyword, Date startDate, Date endDate, PageRequest pageRequest) throws Exception {
        //1.精准查询
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("isDel", SearchFilter.Operator.EQ, BasicStateEnum.FALSE.intValue()));
        //状态(null或小于等于0时查询所有数据)
        if(qtStatus != null && qtStatus > 0){
            filters.add(new SearchFilter("qtStatus", SearchFilter.Operator.EQ, qtStatus));
        }
        if(startDate != null){
            filters.add(new SearchFilter("qtStartDate", SearchFilter.Operator.GTE, startDate));
        }
        if(endDate != null){
            filters.add(new SearchFilter("qtDeadLine", SearchFilter.Operator.LTE, endDate));
        }

        //2.模糊查询
        List<SearchFilter> filters1 = new ArrayList<SearchFilter>();
        if(StringUtils.isNotEmpty(keyword)){
            //报价单编号、供应商编号、询价单标题、报价人
            filters1.add(new SearchFilter("qtCode", SearchFilter.Operator.LIKE, keyword));
            filters1.add(new SearchFilter("suppChineseName", SearchFilter.Operator.LIKE, keyword));
            filters1.add(new SearchFilter("eqTitle", SearchFilter.Operator.LIKE, keyword));
            filters1.add(new SearchFilter("suppContactName", SearchFilter.Operator.LIKE, keyword));
        }

        Specification<Quote> spec = Specification.where(BaseService.and(filters, Quote.class));
        Specification<Quote> spec1 = spec.and(BaseService.or(filters1, Quote.class));
        Page<Quote> page = quoteDao.findAll(spec1, pageRequest);

        return ApiResponseResult.success().data(DataGrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
    }

    /**
     * 获取报价单详情
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public ApiResponseResult getQuoteInfo(Long id) throws Exception{
        if(id == null){
            return ApiResponseResult.failure("记录ID不能为空！");
        }
        Quote o = quoteDao.findById((long) id);
        if(o == null){
            return ApiResponseResult.failure("记录不存在！");
        }

        //1.获取关联物料报价明细信息
        List<QuoteMateriel> list1 = quoteMaterielDao.findByIsDelAndQtIdOrderByIdAsc(BasicStateEnum.FALSE.intValue(), id);
        //获取询价单详情信息
        Enquiry entity1 = enquiryDao.findById((long) o.getEqId());
        //2.获取询价供应商信息
        List<EnquirySupplier> list2 = new ArrayList<EnquirySupplier>();
        if(entity1 != null){
            list2 = enquirySupplierDao.findByIsDelAndEqIdOrderByIdAsc(BasicStateEnum.FALSE.intValue(), entity1.getId());
            //2.1统计关联供应商的“报价总金额”
            for(int j = 0; j < list2.size(); j++){
                EnquirySupplier enquirySupplier = list2.get(j);
                List<Quote> quoteList = quoteDao.findByIsDelAndEqIdAndSuppId(BasicStateEnum.FALSE.intValue(), enquirySupplier.getEqId(), enquirySupplier.getSuppId());
                if(quoteList != null && quoteList.size() > 0) {
                    enquirySupplier.setEqTotalPrice(quoteList.get(0).getQtTotalPrice());
                }
            }
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("quoteData", o);
        map.put("mateList", list1);
        map.put("enquiryData", entity1);
        map.put("suppList", list2);

        return ApiResponseResult.success().data(map);
    }

    /**
     * 确认报价
     * @param quote
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ApiResponseResult doQuote(Quote quote) throws Exception{
        if(quote == null || quote.getId() == null){
            return ApiResponseResult.failure("记录ID不能为空！");
        }
        Quote o = quoteDao.findById((long) quote.getId());
        if(o == null){
            return ApiResponseResult.failure("记录不存在！");
        }
        SysUser currUser = UserUtil.getCurrUser();  //获取当前用户
        Float totalPrice = Float.valueOf(0);  //报价总额

        //1.修改物料关联信息
        List<QuoteMateriel> mateList = quote.getQtMateList();
        if(mateList != null){
            for(int i = 0; i < mateList.size(); i++){
                QuoteMateriel mateItem = quoteMaterielDao.findById((long) mateList.get(i).getId());
                if(mateItem != null){
                    if(mateList.get(i).getQtTotalPrice() != null){  //计算报价总额，值为各个物料总价之和
                        totalPrice += mateList.get(i).getQtTotalPrice();
                    }
                    mateItem.setQtUnitPrice(mateList.get(i).getQtUnitPrice());
                    mateItem.setQtTotalPrice(mateList.get(i).getQtTotalPrice());
                    mateItem.setQtMateDesc(mateList.get(i).getQtMateDesc());
                    mateItem.setModifiedTime(new Date());
                    mateItem.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
                    quoteMaterielDao.save(mateItem);
                }
            }
        }

        //2.修改报价单
        o.setQtStatus(2);  //2为已报价
        o.setQtTitle(quote.getQtTitle());
        o.setQtStartDate(quote.getQtStartDate());
        o.setQtDeadLine(quote.getQtDeadLine());
        o.setQtDelDeadline(quote.getQtDelDeadline());
        o.setQtDelLocation(quote.getQtDelLocation());
        o.setQtAcceptType(quote.getQtAcceptType());
        o.setQtPayMethod(quote.getQtPayMethod());
        o.setQtDesc(quote.getQtDesc());
        o.setQtTotalPrice(totalPrice);
        o.setModifiedTime(new Date());
        o.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
        quoteDao.save(o);

        //3.修改询价单的“完成报价数量”和“询价状态”
        Enquiry enquiry = enquiryDao.findById((long) o.getEqId());
        if(enquiry != null){
            int suppNum = enquiry.getEqSuppNum();  //询价供应商数量
            int completeNum = 0;  //完成报价数量

            //从报价单中统计已报价的供应商数量
            List<Quote> qtList = quoteDao.findByIsDelAndEqId(BasicStateEnum.FALSE.intValue(), o.getEqId());
            if(qtList != null){
                for(Quote item : qtList){
                    if(item.getQtStatus() == 2){
                        completeNum++;
                    }
                }
            }

            enquiry.setEqCompleteNum(completeNum);
            if(completeNum >= suppNum){  //如果完成报价的数量大于等于供应商数量，则询价单的状态修改为“询价完成”
                enquiry.setEqStatus(2);
            }
            enquiryDao.save(enquiry);
        }

        return ApiResponseResult.success("确认报价成功！").data(o);
    }
}
