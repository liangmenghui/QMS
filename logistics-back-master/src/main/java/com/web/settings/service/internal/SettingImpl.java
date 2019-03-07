package com.web.settings.service.internal;


import com.system.user.entity.SysUser;
import com.utils.BaseService;
import com.utils.UserUtil;
import com.utils.enumeration.BasicStateEnum;
import com.utils.enumeration.SettingsStateEnum;
import com.web.settings.entity.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.base.data.ApiResponseResult;
import com.app.base.data.DataGrid;
import com.web.settings.dao.SettingDao;
import com.web.settings.service.SettingService;
import org.springside.modules.persistence.SearchFilter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 基础设置
 *
 */
@Service(value = "SettingService")
@Transactional(propagation = Propagation.REQUIRED)
public class SettingImpl implements SettingService {

    @Autowired
    private SettingDao settingDao;

    @Override
    @Transactional(readOnly = true)
	public ApiResponseResult getlist(PageRequest pageRequest) throws Exception {
		// TODO Auto-generated method stub
        List<SearchFilter> filters = new ArrayList<SearchFilter>();
        filters.add(new SearchFilter("isDel", SearchFilter.Operator.EQ, BasicStateEnum.FALSE.intValue()));
        Specification<Setting> spec = Specification.where(BaseService.and(filters, Setting.class));
        List<Setting> list = settingDao.findAll(spec);
		return ApiResponseResult.success().data(list);
	}

    /**
     * 修改配置
     * @param bomCheck
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public ApiResponseResult updateSetting(Float bomCheck) throws Exception{
        SysUser currUser = UserUtil.getCurrUser();  //获取当前用户
        List<Setting> settingList = new ArrayList<Setting>();

        //1.修改匹配率
        List<Setting> oList = settingDao.findByIsDelAndCode(BasicStateEnum.FALSE.intValue(), SettingsStateEnum.CUSTOMER_BOM_CHECK.stringValue());
        if(oList.size() > 0 || oList.get(0) != null){
            Setting o = oList.get(0);
            //转换
            String value = bomCheck != null ? bomCheck.toString() : null;
            o.setValue(value);
            o.setModifiedTime(new Date());
            o.setPkModifiedBy((currUser!=null) ? (currUser.getId()) : null);
            settingList.add(o);
        }

        if(settingList.size() > 0){
            settingDao.saveAll(settingList);
        }

        return ApiResponseResult.success("修改配置成功！");
    }

}
