package com.web.materiel.service.internal;

import com.system.user.entity.SysUser;
import com.utils.BaseService;
import com.utils.UserUtil;
import com.web.materiel.dao.MaterielInfoK3Dao;
import com.web.materiel.entity.MaterielInfoK3;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.base.data.ApiResponseResult;
import com.app.base.data.DataGrid;
import com.utils.enumeration.BasicStateEnum;
import com.web.materiel.dao.MaterielInfoDao;
import com.web.materiel.entity.MaterielInfo;
import com.web.materiel.service.MaterielInfoService;
import org.springside.modules.persistence.SearchFilter;

import java.util.*;

@Service(value = "MaterielInfoService")
@Transactional(propagation = Propagation.REQUIRED)
public class MaterielInfoImpl implements MaterielInfoService {

    @Autowired
    private MaterielInfoDao materielInfoDao;
    @Autowired
    private MaterielInfoK3Dao materielInfoK3Dao;

    /**
     * 新增物料
     * @param materielInfo
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ApiResponseResult add(MaterielInfo materielInfo) throws Exception {
        if(materielInfo == null || materielInfo.getMateName() == null){
            return ApiResponseResult.failure("物料名称不能为空！");
        }
        SysUser currUser = UserUtil.getCurrUser();  //获取当前用户

        materielInfo.setMateName(materielInfo.getMateName().trim());
        materielInfo.setCreatedTime(new Date());
        materielInfo.setPkCreatedBy((currUser!=null) ? (currUser.getId()) : null);
        materielInfoDao.save(materielInfo);
        return ApiResponseResult.success("物料新增成功！");
    }

    /**
     * 编辑物料
     * @param materielInfo
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ApiResponseResult edit(MaterielInfo materielInfo) throws Exception{
        if(materielInfo == null || materielInfo.getId() == null){
            return ApiResponseResult.failure("记录ID不能为空！");
        }
        if(materielInfo.getMateName() == null){
            return ApiResponseResult.failure("物料名称不能为空！");
        }
        MaterielInfo o = materielInfoDao.findById((long) materielInfo.getId());
        if(o == null) {
            return ApiResponseResult.failure("物料不存在！");
        }
        SysUser currUser = UserUtil.getCurrUser();  //获取当前用户

        o.setCategoryName(materielInfo.getCategoryName());
        o.setMateName(materielInfo.getMateName().trim());
        o.setMateModel(materielInfo.getMateModel());
        o.setSuppCode(materielInfo.getSuppCode());
        o.setSuppChineseName(materielInfo.getSuppChineseName());
        o.setRemark(materielInfo.getRemark());
        o.setModifiedTime(new Date());
        o.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
        materielInfoDao.save(o);
        return ApiResponseResult.success("物料编辑成功！");
    }

    /**
     * 删除物料
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ApiResponseResult delete(Long id) throws Exception{
        if(id == null){
            return ApiResponseResult.failure("记录ID不能为空！");
        }
        MaterielInfo o = materielInfoDao.findById((long) id);
        if(o == null){
            return ApiResponseResult.failure("物料不存在！");
        }
        SysUser currUser = UserUtil.getCurrUser();  //获取当前用户

        o.setIsDel(BasicStateEnum.TRUE.intValue());
        o.setModifiedTime(new Date());
        o.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
        materielInfoDao.save(o);
        return ApiResponseResult.success("物料删除成功！");
    }

    /**
     * 获取SRM物料列表
     * @param mateK3Code
     * @param mateName
     * @param pageRequest
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ApiResponseResult getlist(String mateK3Code, String mateName, PageRequest pageRequest) throws Exception {
        MaterielInfo demoBean = new MaterielInfo();
        demoBean.setIsDel(BasicStateEnum.FALSE.intValue());
        if(StringUtils.isNotEmpty(mateK3Code)){
            demoBean.setMateK3Code(mateK3Code);
        }
        if(StringUtils.isNotEmpty(mateName)){
            demoBean.setMateName(mateName);
        }

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("mateK3Code", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("mateName", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<MaterielInfo> example = Example.of(demoBean, matcher);
        Page<MaterielInfo> page = materielInfoDao.findAll(example, pageRequest);

        return ApiResponseResult.success().data(DataGrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
    }

    /**
     * 获取SRM物料和K3物料信息
     * @param mateK3Code
     * @param mateName
     * @param pageRequest
     * @param pageRequest2
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(readOnly = true)
    public ApiResponseResult getlistAll(String mateK3Code, String mateName, PageRequest pageRequest, PageRequest pageRequest2) throws Exception{
        //1.SRM物料信息查询
        MaterielInfo demoBean = new MaterielInfo();
        demoBean.setIsDel(BasicStateEnum.FALSE.intValue());
        if(StringUtils.isNotEmpty(mateK3Code)){
            demoBean.setMateK3Code(mateK3Code);
        }
        if(StringUtils.isNotEmpty(mateName)){
            demoBean.setMateName(mateName);
        }
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("mateK3Code", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("mateName", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<MaterielInfo> example = Example.of(demoBean, matcher);
        Page<MaterielInfo> page = materielInfoDao.findAll(example, pageRequest);

        //2.K3物料信息查询
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        if(StringUtils.isNotEmpty(mateK3Code)){
            filters.add(new SearchFilter("fNumber", SearchFilter.Operator.LIKE, mateK3Code));
        }
        if(StringUtils.isNotEmpty(mateName)){
            filters.add(new SearchFilter("fName", SearchFilter.Operator.LIKE, mateName));
        }
        Specification<MaterielInfoK3> spec = Specification.where(BaseService.and(filters, MaterielInfoK3.class));
        Page<MaterielInfoK3> page2 = materielInfoK3Dao.findAll(spec, pageRequest2);

        //将两个表的物料信息封装到Map里
        Map<String, Object> map = new HashMap<String, Object>();
        DataGrid dataGrid = DataGrid.create(page.getContent(), (int) page.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize());
        map.put("listSrm", dataGrid);
        DataGrid dataGrid2 = DataGrid.create(page2.getContent(), (int) page2.getTotalElements(), pageRequest2.getPageNumber() + 1, pageRequest2.getPageSize());
        map.put("listK3", dataGrid2);

        return ApiResponseResult.success().data(map);
    }

}
