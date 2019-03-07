package com.unind.base.provider.admin.perm.internal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;

import com.unind.base.dao.admin.SysPermDao;
import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.domain.admin.perm.SysPerm;
import com.unind.base.provider.BaseService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.admin.perm.SysPermService;
import com.unind.base.provider.context.SessionContextUtils;

@Service
@Transactional(rollbackFor=BusinessException.class)
public class SysPermImpl extends BaseService implements SysPermService {
	@Autowired
	private SysPermDao sysPermDao;

	public ApiResponseResult add(SysPerm sysPerm) throws BusinessException {
		if(StringUtils.isEmpty(sysPerm.getBsCode()) || StringUtils.isEmpty(sysPerm.getBsCode().trim())) {
			return ApiResponseResult.failure("编码不能为空");
		}
		if(StringUtils.isEmpty(sysPerm.getBsName()) || StringUtils.isEmpty(sysPerm.getBsName().trim())) {
			return ApiResponseResult.failure("名称不能为空");
		}
		sysPerm.setBsCode(sysPerm.getBsCode().trim().toUpperCase());
		int counts = sysPermDao.countByBsCode(sysPerm.getBsCode());
		if(counts > 0) {
			return ApiResponseResult.failure("编码不能重复");
		}

		sysPerm.setBsCode(sysPerm.getBsCode().trim());
		sysPerm.setBsName(sysPerm.getBsName().trim());
		sysPerm.setBsCreatedTime(new Date());
		sysPerm.setPkCreatedBy(SessionContextUtils.getCurrentUser().getId());
		sysPermDao.save(sysPerm);
		return ApiResponseResult.success();
	}

	@Transactional
	public ApiResponseResult edit(SysPerm sysPerm) throws BusinessException {
		if (null == sysPerm || null == sysPerm.getId()) {
			return ApiResponseResult.failure("记录ID为必填项");
		}
		if(StringUtils.isEmpty(sysPerm.getBsCode()) || StringUtils.isEmpty(sysPerm.getBsCode().trim())) {
			return ApiResponseResult.failure("编码不能为空");
		}
		sysPerm.setBsCode(sysPerm.getBsCode().trim().toUpperCase());
		if(StringUtils.isEmpty(sysPerm.getBsName()) || StringUtils.isEmpty(sysPerm.getBsName().trim())) {
			return ApiResponseResult.failure("名称不能为空");
		}
		SysPerm o = sysPermDao.findOne(sysPerm.getId());
		if (null == o) {
			return ApiResponseResult.failure("记录ID不存在或已被删除");
		}
		if(!StringUtils.equals(sysPerm.getBsCode(), o.getBsCode())) {
			int counts = sysPermDao.countByBsCode(sysPerm.getBsCode());
			if(counts > 0) {
				return ApiResponseResult.failure("编码不能重复");
			}
		}
		o.setBsComment(sysPerm.getBsComment());
		o.setBsCode(sysPerm.getBsCode().trim());
		o.setBsName(sysPerm.getBsName().trim());
		o.setBsModifiedTime(new Date());
		o.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
		sysPermDao.save(o);
		return ApiResponseResult.success();
	}

	@Transactional
	public ApiResponseResult delete(SysPerm sysPerm) throws BusinessException {
		SysPerm o = sysPermDao.findOne(sysPerm.getId());
		if (null == o) {
			return ApiResponseResult.failure("记录ID不存在或已被删除");
		}
		if(o.getId() <= 0) {
			return ApiResponseResult.failure("没有操作权限");
		}
		o.setBsIsDel(BaseEnumConstants.TRUE);
		sysPermDao.save(o);
		return ApiResponseResult.success();
	}

	@Transactional(readOnly = true)
	public ApiResponseResult getlist(SysPerm sysPerm, PageRequest pageRequest) {
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		if(StringUtils.isNotEmpty(sysPerm.getBsCode())) {
			sysPerm.setBsCode(sysPerm.getBsCode().trim().toUpperCase());
			filters.add(new SearchFilter("bsCode", Operator.EQ, sysPerm.getBsCode()));
		}
		if(StringUtils.isNotEmpty(sysPerm.getBsName())) {
			filters.add(new SearchFilter("bsName", Operator.LIKE, "%"+sysPerm.getBsName()+"%"));
		}
		Specifications<SysPerm> spec = Specifications.where(super.and(filters, SysPerm.class));
		Page<SysPerm> page = sysPermDao.findAll(spec, pageRequest);
		return ApiResponseResult.success().data(Datagrid.create(page.getContent(), (int)page.getTotalElements(), pageRequest.getPageNumber()+1, pageRequest.getPageSize()));
	}

}
