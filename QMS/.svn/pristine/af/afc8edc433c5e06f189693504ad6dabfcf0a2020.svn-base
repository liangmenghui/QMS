package com.unind.qms.web.risk.service.internal;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BusinessException;
import com.unind.qms.web.risk.dao.ProductPpmDao;
import com.unind.qms.web.risk.entity.ProductPpm;
import com.unind.qms.web.risk.service.ProductPpmService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 产品风险ppm的不良品数
 * @author Shen
 */
@Service
@Transactional(rollbackFor = BusinessException.class)
public class ProductPpmImpl extends BaseOprService implements ProductPpmService {

    @Autowired
    private ProductPpmDao productPpmDao;

    @Override
    @Transactional
    public ApiResponseResult add(ProductPpm productPpm) throws BusinessException {
        if(productPpm == null || productPpm.getBsPrId() == null){
            return ApiResponseResult.failure("产品ID不能为空");
        }
        List<ProductPpm> o = productPpmDao.findByBsPrIdAndBsYear(productPpm.getBsPrId(), Calendar.getInstance().get(Calendar.YEAR));
        if(o.size() > 0){
            return ApiResponseResult.failure("该产品今年的不良品数已存在，不能重复添加！");
        }
        productPpm.setBsYear(Calendar.getInstance().get(Calendar.YEAR));
        productPpm.setBsCreatedTime(new Date());
        productPpmDao.save(productPpm);
        return ApiResponseResult.success("新增成功！").data(productPpm);
    }

    @Override
    @Transactional
    public ApiResponseResult edit(ProductPpm productPpm) throws BusinessException {
        if(productPpm == null || productPpm.getId() == null){
            return ApiResponseResult.failure("记录ID不能为空");
        }
        if(productPpm.getBsPrId() == null){
            return ApiResponseResult.failure("产品ID不能为空");
        }
        ProductPpm o = productPpmDao.findOne(productPpm.getId());
        if(o == null){
            return ApiResponseResult.failure("记录ID不存在或已被删除");
        }
        o.setBsModifiedTime(new Date());
        //自己的不良数
        o.setBsMonthResult1(productPpm.getBsMonthResult1());
        o.setBsMonthResult2(productPpm.getBsMonthResult2());
        o.setBsMonthResult3(productPpm.getBsMonthResult3());
        o.setBsMonthResult4(productPpm.getBsMonthResult4());
        o.setBsMonthResult5(productPpm.getBsMonthResult5());
        o.setBsMonthResult6(productPpm.getBsMonthResult6());
        o.setBsMonthResult7(productPpm.getBsMonthResult7());
        o.setBsMonthResult8(productPpm.getBsMonthResult8());
        o.setBsMonthResult9(productPpm.getBsMonthResult9());
        o.setBsMonthResult10(productPpm.getBsMonthResult10());
        o.setBsMonthResult11(productPpm.getBsMonthResult11());
        o.setBsMonthResult12(productPpm.getBsMonthResult12());
        //客户的不良数
        o.setBsCusMonthResult1(productPpm.getBsCusMonthResult1());
        o.setBsCusMonthResult2(productPpm.getBsCusMonthResult2());
        o.setBsCusMonthResult3(productPpm.getBsCusMonthResult3());
        o.setBsCusMonthResult4(productPpm.getBsCusMonthResult4());
        o.setBsCusMonthResult5(productPpm.getBsCusMonthResult5());
        o.setBsCusMonthResult6(productPpm.getBsCusMonthResult6());
        o.setBsCusMonthResult7(productPpm.getBsCusMonthResult7());
        o.setBsCusMonthResult8(productPpm.getBsCusMonthResult8());
        o.setBsCusMonthResult9(productPpm.getBsCusMonthResult9());
        o.setBsCusMonthResult10(productPpm.getBsCusMonthResult10());
        o.setBsCusMonthResult11(productPpm.getBsCusMonthResult11());
        o.setBsCusMonthResult12(productPpm.getBsCusMonthResult12());

        productPpmDao.save(o);
        return ApiResponseResult.success("修改成功！").data(o);
    }

    @Override
    @Transactional
    public ApiResponseResult delete(Long id) throws BusinessException {
        if(id == null){
            return ApiResponseResult.failure("记录ID为必填项");
        }
        ProductPpm productPpm = productPpmDao.findOne(id);
        if(productPpm == null){
            return ApiResponseResult.failure("记录ID不存在或已被删除");
        }
        productPpmDao.delete(productPpm);
        return ApiResponseResult.success("删除成功！");
    }

    @Override
    @Transactional
    public ApiResponseResult getlist(String keyWord, Long id, Long bsPrId, Integer bsYear, PageRequest pageRequest) throws BusinessException {
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("bsIsDel", SearchFilter.Operator.EQ, BaseEnumConstants.FALSE));
        if(id != null){
            filters.add(new SearchFilter("id", SearchFilter.Operator.EQ, id));
        }
        if(bsPrId != null){
            filters.add(new SearchFilter("bsPrId", SearchFilter.Operator.EQ, bsPrId));
        }
        if(bsYear != null){
            filters.add(new SearchFilter("bsYear", SearchFilter.Operator.EQ, bsYear));
        }else{
            filters.add(new SearchFilter("bsYear", SearchFilter.Operator.EQ, Calendar.getInstance().get(Calendar.YEAR)));
        }

        List<SearchFilter> filters1 = new ArrayList<SearchFilter>();
        if(StringUtils.isNotEmpty(keyWord)){
            filters1.add(new SearchFilter("productBy.bsPrName", SearchFilter.Operator.LIKE, keyWord));
            filters1.add(new SearchFilter("productBy.bsSuppChieseName", SearchFilter.Operator.LIKE, keyWord));
        }

        Specification<ProductPpm> spec = Specifications.where(super.and(filters, ProductPpm.class));
        Specification<ProductPpm> spec1 = ((Specifications<ProductPpm>) spec).and(super.or(filters1, ProductPpm.class));
        Page<ProductPpm> page = productPpmDao.findAll(spec1, pageRequest);
        return ApiResponseResult.success().data(Datagrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
    }
}
