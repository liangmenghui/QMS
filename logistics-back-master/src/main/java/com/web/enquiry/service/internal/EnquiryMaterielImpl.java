package com.web.enquiry.service.internal;

import com.app.base.data.ApiResponseResult;
import com.app.base.data.DataGrid;
import com.system.user.entity.SysUser;
import com.utils.BaseService;
import com.utils.UserUtil;
import com.utils.enumeration.BasicStateEnum;
import com.web.enquiry.dao.EnquiryMaterielDao;
import com.web.enquiry.entity.Enquiry;
import com.web.enquiry.entity.EnquiryMateriel;
import com.web.enquiry.service.EnquiryMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 新料询价物料关联表
 *
 */
@Service(value = "EnquiryMaterielService")
@Transactional(propagation = Propagation.REQUIRED)
public class EnquiryMaterielImpl implements EnquiryMaterielService {

    @Autowired
    private EnquiryMaterielDao enquiryMaterielDao;

    @Override
    @Transactional
    public ApiResponseResult add(EnquiryMateriel enquiryMateriel) throws Exception {
        if(enquiryMateriel == null){
            return ApiResponseResult.failure("记录不能为空！");
        }
        SysUser currUser = UserUtil.getCurrUser();  //获取当前用户

        enquiryMateriel.setCreatedTime(new Date());
        enquiryMateriel.setPkCreatedBy((currUser!=null) ? (currUser.getId()) : null);
        enquiryMaterielDao.save(enquiryMateriel);

        return ApiResponseResult.success("关联物料新增成功！").data(enquiryMateriel);
    }

    @Override
    @Transactional
    public ApiResponseResult edit(EnquiryMateriel enquiryMateriel) throws Exception {
        if(enquiryMateriel == null || enquiryMateriel.getId() == null){
            return ApiResponseResult.failure("记录ID不能为空！");
        }
        EnquiryMateriel o = enquiryMaterielDao.findById((long) enquiryMateriel.getId());
        if(o == null){
            return ApiResponseResult.failure("记录不存在！");
        }
        SysUser currUser = UserUtil.getCurrUser();  //获取当前用户

        o.setMateId(enquiryMateriel.getMateId());
        o.setMateCode(enquiryMateriel.getMateCode());
        o.setMateName(enquiryMateriel.getMateName());
        o.setMateModel(enquiryMateriel.getMateModel());
        o.setEqUnit(enquiryMateriel.getEqUnit());
        o.setEqMateNum(enquiryMateriel.getEqMateNum());
        o.setEqMateDesc(enquiryMateriel.getEqMateDesc());
        o.setEqBasePrice(enquiryMateriel.getEqBasePrice());
        o.setModifiedTime(new Date());
        o.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
        enquiryMaterielDao.save(o);

        return ApiResponseResult.success("关联物料修改成功！").data(o);
    }

    @Override
    @Transactional
    public ApiResponseResult delete(Long id) throws Exception {
        if(id == null){
            return ApiResponseResult.failure("记录ID不能为空！");
        }
        EnquiryMateriel o = enquiryMaterielDao.findById((long) id);
        if(o == null){
            return ApiResponseResult.failure("记录不存在！");
        }
        SysUser currUser = UserUtil.getCurrUser();  //获取当前用户

        o.setIsDel(BasicStateEnum.TRUE.intValue());
        o.setModifiedTime(new Date());
        o.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
        enquiryMaterielDao.save(o);

        return ApiResponseResult.success("关联物料删除成功！");
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponseResult getlist(Long eqId, PageRequest pageRequest) throws Exception{
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("isDel", SearchFilter.Operator.EQ, BasicStateEnum.FALSE.intValue()));
        if(eqId != null){
            filters.add(new SearchFilter("eqId", SearchFilter.Operator.EQ, eqId));
        }
        Specification<EnquiryMateriel> spec = Specification.where(BaseService.and(filters, EnquiryMateriel.class));
        Page<EnquiryMateriel> page = enquiryMaterielDao.findAll(spec, pageRequest);

        return ApiResponseResult.success().data(DataGrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
    }
}
