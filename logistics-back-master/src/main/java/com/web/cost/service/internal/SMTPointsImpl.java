package com.web.cost.service.internal;

import com.app.base.data.ApiResponseResult;
import com.app.base.data.DataGrid;
import com.system.user.entity.SysUser;
import com.utils.BaseService;
import com.utils.UserUtil;
import com.utils.enumeration.BasicStateEnum;
import com.web.cost.dao.SMTPointsDao;
import com.web.cost.entity.SMTPoints;
import com.web.cost.service.SMTPointsService;
import com.web.materiel.dao.MaterielCategoryK3Dao;
import com.web.materiel.dao.MaterielInfoK3Dao;
import com.web.materiel.entity.MaterielCategoryK3;
import com.web.materiel.entity.MaterielInfoK3;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * SMT点数
 *
 */
@Service(value = "SMTPointsService")
@Transactional(propagation = Propagation.REQUIRED)
public class SMTPointsImpl extends BaseService implements SMTPointsService {

    @Autowired
    private SMTPointsDao smtPointsDao;
    @Autowired
    private MaterielCategoryK3Dao materielCategoryK3Dao;
    @Autowired
    private MaterielInfoK3Dao materielInfoK3Dao;

    @Override
    @Transactional(readOnly = true)
    public ApiResponseResult getTreeList(Integer setStatus, String keyword, Integer parentId, Integer sLevel, PageRequest pageRequest) throws Exception {
        if(sLevel != null && sLevel == 3){
            Page<SMTPoints> smtPointsList = getPoints(setStatus, keyword, parentId, pageRequest);
            return ApiResponseResult.success().data(DataGrid.create(smtPointsList.getContent(), (int) smtPointsList.getTotalElements(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
        }else{
            List<SMTPoints> smtPointsList = getCategory(parentId);
            return ApiResponseResult.success().data(DataGrid.create(smtPointsList, smtPointsList.size(), pageRequest.getPageNumber() + 1, pageRequest.getPageSize()));
        }
    }

    //获取第一大类和第二大类信息
    @Override
    @Transactional(readOnly = true)
    public List<SMTPoints> getCategory(Integer parentId) throws Exception{
        List<MaterielCategoryK3> list = materielCategoryK3Dao.findByFParentIdOrderByFItemIdAsc(parentId);
        List<SMTPoints> listSMT = new ArrayList<>();
        for(MaterielCategoryK3 item : list){
            //注意封装SMTPoints时的parentId取item的记录Id
            SMTPoints smtPoints = new SMTPoints(item.getfNumber(), item.getfName(), null, item.getfItemId(), null, null, item.getfLevel(), item.getfItemId());
            listSMT.add(smtPoints);
        }

        return listSMT;
    }

    //获取物料SMT点数信息
    @Override
    @Transactional(readOnly = true)
    public Page<SMTPoints> getPoints(Integer setStatus, String keyword, Integer categoryId, PageRequest pageRequest) throws Exception{
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("categoryId", categoryId);
        String sql = "select t2.id id,t1.f_number sCode,t1.f_name sName,t2.s_points sPoints,t1.f_category_id sCategoryId,t1.f_category_number sCategory,t2.is_special isSpecial,3 as sLevel from "+MaterielInfoK3.TABLE_NAME+" t1 " +
                " left join "+SMTPoints.TABLE_NAME+" t2 on t1.f_number = t2.s_code where t1.f_category_id = :categoryId";

        //1.是否设置
        if(setStatus != null && setStatus == 1){
            sql += " and t2.id is null";
        }
        if(setStatus != null && setStatus == 2){
            sql += " and t2.id is not null";
        }
        //2.关键字
        if(StringUtils.isNotEmpty(keyword)){
            sql += " and (t1.f_number like '%"+keyword+"%' or t1.f_name like '%"+keyword+"%')";
        }

        Page<SMTPoints> page = super.findPageBySql(sql, pageRequest, param, null);
        return page;
    }

    /**
     * 设置SMT点数
     * @param smtPoints
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ApiResponseResult updatePoints(SMTPoints smtPoints) throws Exception{
        SysUser currUser = UserUtil.getCurrUser();  //获取当前用户
        if(smtPoints == null){
            return ApiResponseResult.failure("记录不能为空！");
        }

        //1.批量设置SMT点数
        if(smtPoints.getsLevel() != null && (smtPoints.getsLevel() == 1 ||  smtPoints.getsLevel() == 2)){
            if(smtPoints.getsLevel() == 1){
                return ApiResponseResult.failure("该大类无法设置SMT点数！");
            }
            //添加该类下的所有物料
            List<SMTPoints> smtPointsList = new ArrayList<SMTPoints>();
            List<MaterielInfoK3> mateList = materielInfoK3Dao.findByFCategoryIdOrderByFItemIdAsc(smtPoints.getsCategoryId());
            List<SMTPoints> tempList = smtPointsDao.findByIsDelAndSCategoryId(BasicStateEnum.FALSE.intValue(), smtPoints.getsCategoryId());
            for(MaterielInfoK3 item : mateList){
                List<SMTPoints> tempList2 = tempList.stream().filter(s -> s.getsCode().equals(item.getfNumber())).collect(Collectors.toList());
                if(tempList2.size() > 0){
                    SMTPoints o = tempList2.get(0);
                    //设置了单独录入的，此处不修改其SMT值
                    if(o.getIsSpecial() != null && o.getIsSpecial() == 1){
                        continue;
                    }
                    o.setsPoints(smtPoints.getsPoints());
                    o.setModifiedTime(new Date());
                    o.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
                    smtPointsList.add(o);
                }else{
                    SMTPoints o = new SMTPoints(item.getfNumber(), item.getfName(), smtPoints.getsPoints(), item.getfCategoryId(), item.getfCategoryNumber(), 0, null, null );
                    o.setCreatedTime(new Date());
                    o.setPkCreatedBy((currUser!=null) ? (currUser.getId()) : null);
                    smtPointsList.add(o);
                }
            }
            smtPointsDao.saveAll(smtPointsList);
        }else{
            List<SMTPoints> tempList3 = smtPointsDao.findByIsDelAndSCode(BasicStateEnum.FALSE.intValue(), smtPoints.getsCode());
            //2.单个物料设置SMT点数
            if(tempList3.size() <= 0){
                smtPoints.setCreatedTime(new Date());
                smtPoints.setPkCreatedBy((currUser!=null) ? (currUser.getId()) : null);
                smtPointsDao.save(smtPoints);
            }else {
                SMTPoints o = tempList3.get(0);
                if(o == null){
                    return ApiResponseResult.failure("记录不存在！");
                }
                o.setsPoints(smtPoints.getsPoints());
                o.setIsSpecial(smtPoints.getIsSpecial());
                o.setModifiedTime(new Date());
                o.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
                smtPointsDao.save(o);
            }
        }

        return ApiResponseResult.success("修改成功！").data(smtPoints);
    }

    //测试数据
    public List<SMTPoints> test(){
        List<SMTPoints> list = new ArrayList<>();
        if(1==1){
            SMTPoints s1 = new SMTPoints("01","原材料", (float) 2);

            //
            List<SMTPoints> list2 = new ArrayList<>();
            SMTPoints s3 = new SMTPoints("01.10","贴片电阻", (float) 2.3);
            //
            List<SMTPoints> list3 = new ArrayList<>();
            SMTPoints s5 = new SMTPoints("01.10.00120","电阻", (float) 6.3);
            list3.add(s5);
            SMTPoints s6 = new SMTPoints("01.10.00140","贴片电阻", (float) 3.95);
            list3.add(s6);
            s3.setChildren(list3);
            list2.add(s3);
            SMTPoints s4 = new SMTPoints("01.17","压敏电阻", (float) 4.3);
            list2.add(s4);
            s1.setChildren(list2);

            list.add(s1);
        }

        if(2==2){
            SMTPoints s1 = new SMTPoints("02","周转材料", (float) 3.4);

            //
            List<SMTPoints> list2 = new ArrayList<>();
            SMTPoints s3 = new SMTPoints("02.03","夹具", (float) 5);
            list2.add(s3);
            SMTPoints s4 = new SMTPoints("02.04","钢网", (float) 1);
            list2.add(s4);
            s1.setChildren(list2);

            list.add(s1);
        }

        return list;
    }
}
