package com.web.quote.service.internal;

import com.app.base.data.ApiResponseResult;
import com.system.user.entity.SysUser;
import com.utils.UserUtil;
import com.web.quote.dao.QuoteMaterielDao;
import com.web.quote.entity.QuoteMateriel;
import com.web.quote.service.QuoteMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 新料报价物料关联表（报价明细）
 *
 */
@Service(value = "QuoteMaterielService")
@Transactional(propagation = Propagation.REQUIRED)
public class QuoteMaterielImpl implements QuoteMaterielService {

    @Autowired
    private QuoteMaterielDao quoteMaterielDao;

    @Override
    @Transactional
    public ApiResponseResult add(QuoteMateriel quoteMateriel) throws Exception {
        if(quoteMateriel == null){
            return ApiResponseResult.failure("记录不能为空！");
        }
        SysUser currUser = UserUtil.getCurrUser();  //获取当前用户

        quoteMateriel.setCreatedTime(new Date());
        quoteMateriel.setPkCreatedBy((currUser!=null) ? (currUser.getId()) : null);
        quoteMaterielDao.save(quoteMateriel);

        return ApiResponseResult.success("报价物料关联表新增成功！");
    }

    @Override
    @Transactional
    public ApiResponseResult edit(QuoteMateriel quoteMateriel) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public ApiResponseResult delete(Long id) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public ApiResponseResult getlist(String keyword, PageRequest pageRequest) throws Exception {
        return null;
    }
}
