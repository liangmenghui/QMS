package com.unind.qms.web.shipment.service.internal;

import com.unind.base.dao.admin.SysRoleDao;
import com.unind.base.dao.admin.agg.SysUserRolesAggDao;
import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.agg.SysUserRolesAgg;
import com.unind.base.domain.admin.enumeration.BooleanStateEnum;
import com.unind.base.domain.admin.role.SysRole;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.BusinessException;
import com.unind.qms.enumeration.BasicEnumConstants;
import com.unind.qms.enumeration.BasicStateEnum;
import com.unind.qms.web.basic.dao.TodoInfoDao;
import com.unind.qms.web.basic.entity.TodoInfo;
import com.unind.qms.web.product.dao.ProductInfoDao;
import com.unind.qms.web.product.entity.ProductInfo;
import com.unind.qms.web.shipment.dao.OrderInspectDao;
import com.unind.qms.web.shipment.entity.OrderInspect;
import com.unind.qms.web.shipment.service.OrderInspectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = BusinessException.class)
public class OrderInspectImpl extends BaseOprService implements OrderInspectService {
    @Autowired
    private OrderInspectDao orderInspectDao;
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private SysUserRolesAggDao sysUserRolesAggDao;
    @Autowired
    private TodoInfoDao todoInfoDao;
    @Autowired
    private SysRoleDao sysRoleDao;

    @Transactional
    public ApiResponseResult add(OrderInspect orderInspect) throws BusinessException {
        if (null == orderInspect || null == orderInspect.getBsPrCode()) {
            return ApiResponseResult.failure("产品编号为必填项");
        }
        if (null == orderInspect || null == orderInspect.getBsSuppCode()) {
            return ApiResponseResult.failure("供应商编号为必填项");
        }
        List<ProductInfo> productInfoList = productInfoDao.findByBsPrCodeAndBsSuppCodeAndBsIsDelOrderByIdDesc(orderInspect.getBsPrCode(), orderInspect.getBsSuppCode(),BooleanStateEnum.FALSE.intValue());
        if(productInfoList.size()<=0){
            return ApiResponseResult.failure("无该产品和供应商的订单");
        }
        orderInspect.setBsPrId(productInfoList.get(0).getId());
        orderInspect.setBsCreatedTime(new Date());
        orderInspectDao.save(orderInspect);

        //2.给角色为质量经理的用户发送待办事项
        //2.1找到该角色下的角色ID
        List<SysRole> sysRoleList = sysRoleDao.findByBsCode("QMS_QUALITY_MANAGER");
        if(sysRoleList.size()==0){
            return ApiResponseResult.failure("无质量保证经理角色！");
        }
        Long roleId = sysRoleList.get(0).getId();
        //2.2找到该角色下的用户ID
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("pkRole", SearchFilter.Operator.EQ, roleId));
        Specifications<SysUserRolesAgg> spec = Specifications.where(super.and(filters, SysUserRolesAgg.class));
        List<SysUserRolesAgg> sysUserRolesAggList = sysUserRolesAggDao.findAll(spec);
        if(sysUserRolesAggList.size() <= 0){
            return ApiResponseResult.failure("供应商新增成功，发送待办事项失败，该角色下的用户不存在！").data(roleId);
        }
        //2.3给该角色的用户添加待办事项
        List<TodoInfo> todoInfoList = new ArrayList<TodoInfo>();
        for(int i = 0; i < sysUserRolesAggList.size(); i++){
            TodoInfo todoInfo = new TodoInfo();
            todoInfo.setBsUserId(sysUserRolesAggList.get(i).getPkUser());  //用户ID
            todoInfo.setBsReferId(orderInspect.getBsPrId());  //关联ID
            todoInfo.setBsExtend(orderInspect.getId());   //扩展内容
            todoInfo.setBsRouter("/qms/product/approved");  //页面路由
            todoInfo.setBsTitle("[" + orderInspect.getBsPrName() +"]" + orderInspect.getBsPoNo() +"待进行成品检验");   //标题
            todoInfo.setBsStatus(BasicEnumConstants.TODO_NOFINISH);  //状态
            todoInfo.setBsSystemType(BasicStateEnum.TODO_QMSTYPE.intValue());  //系统状态
            todoInfo.setBsType(BasicEnumConstants.TODO_CREATEPRO);   //类型
            todoInfo.setBsStartTime(new Date());      //有效开始时间
            //计算一周后的时间
            Calendar curr = Calendar.getInstance();
            curr.setTime(todoInfo.getBsStartTime());
            curr.add(Calendar.WEEK_OF_YEAR, 1);
            Date after7Days=curr.getTime();
            todoInfo.setBsEndTime(after7Days);    //有效结束时间
            todoInfo.setBsCreatedTime(new Date());    //创建时间
            todoInfo.setPkCreatedBy(Long.valueOf((long)1));
            todoInfoList.add(todoInfo);
        }
        todoInfoDao.save(todoInfoList);

        return ApiResponseResult.success("新增成功！");
    }
}
