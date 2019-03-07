package com.unind.base.provider.admin.role.internal;

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

import com.unind.base.dao.admin.SysRolegroupDao;
import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.role.SysRolegroup;
import com.unind.base.provider.BaseService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.admin.role.SysRolegroupService;
import com.unind.base.provider.context.SessionContextUtils;

@Service
@Transactional(rollbackFor=BusinessException.class)
public class SysRolegroupImpl extends BaseService implements SysRolegroupService {
	@Autowired
	private SysRolegroupDao sysRolegroupDao;

	@Transactional
	public ApiResponseResult add(SysRolegroup sysRolegroup) throws BusinessException {
		if(StringUtils.isEmpty(sysRolegroup.getBsCode()) || StringUtils.isEmpty(sysRolegroup.getBsCode().trim())) {
			return ApiResponseResult.failure("编码不能为空");
		}
		if(StringUtils.isEmpty(sysRolegroup.getBsName()) || StringUtils.isEmpty(sysRolegroup.getBsName().trim())) {
			return ApiResponseResult.failure("名称不能为空");
		}
		int counts = sysRolegroupDao.countByBsCode(sysRolegroup.getBsCode());
		if(counts > 0) {
			return ApiResponseResult.failure("编码不能重复");
		}
		sysRolegroup.setBsCode(sysRolegroup.getBsCode().trim());
		sysRolegroup.setBsName(sysRolegroup.getBsName().trim());
		sysRolegroup.setBsCreatedTime(new Date());
		sysRolegroup.setPkCreatedBy(SessionContextUtils.getCurrentUser().getId());
		sysRolegroupDao.save(sysRolegroup);
		return ApiResponseResult.success();
	}

	@Transactional
	public ApiResponseResult edit(SysRolegroup sysRolegroup) throws BusinessException {
		if (null == sysRolegroup || null == sysRolegroup.getId()) {
			return ApiResponseResult.failure("记录ID为必填项");
		}
		if(StringUtils.isEmpty(sysRolegroup.getBsCode()) || StringUtils.isEmpty(sysRolegroup.getBsCode().trim())) {
			return ApiResponseResult.failure("编码不能为空");
		}
		if(StringUtils.isEmpty(sysRolegroup.getBsName()) || StringUtils.isEmpty(sysRolegroup.getBsName().trim())) {
			return ApiResponseResult.failure("名称不能为空");
		}
		SysRolegroup o = sysRolegroupDao.findOne(sysRolegroup.getId());
		if (null == o) {
			return ApiResponseResult.failure("记录ID不存在或已被删除");
		}
		if(!StringUtils.equals(sysRolegroup.getBsCode(), o.getBsCode())) {
			int counts = sysRolegroupDao.countByBsCode(sysRolegroup.getBsCode());
			if(counts > 0) {
				return ApiResponseResult.failure("编码不能重复");
			}
			o.setBsCode(sysRolegroup.getBsCode());
		}
		o.setBsComment(sysRolegroup.getBsComment());
		o.setBsCode(sysRolegroup.getBsCode().trim());
		o.setBsName(sysRolegroup.getBsName().trim());
		o.setBsModifiedTime(new Date());
		o.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
		sysRolegroupDao.save(o);
		return ApiResponseResult.success();
	}

	@Transactional
	public ApiResponseResult delete(SysRolegroup sysRolegroup) throws BusinessException {
		if (null == sysRolegroup || null == sysRolegroup.getId()) {
			return ApiResponseResult.failure("记录ID为必填项");
		}
		SysRolegroup o = sysRolegroupDao.findOne(sysRolegroup.getId());
		if (null == o) {
			return ApiResponseResult.failure("记录ID不存在或已被删除");
		}
		sysRolegroupDao.delete(o);
		return ApiResponseResult.success();
	}

	@Transactional(readOnly = true)
	public ApiResponseResult getlist(SysRolegroup sysRolegroup, PageRequest pageRequest) {
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		if(StringUtils.isNotEmpty(sysRolegroup.getBsCode())) {
			filters.add(new SearchFilter("bsCode", Operator.EQ, sysRolegroup.getBsCode()));
		}
		if(StringUtils.isNotEmpty(sysRolegroup.getBsName())) {
			filters.add(new SearchFilter("bsName", Operator.LIKE, "%"+sysRolegroup.getBsName()+"%"));
		}
		Specifications<SysRolegroup> spec = Specifications.where(super.and(filters, SysRolegroup.class));
		Page<SysRolegroup> page = sysRolegroupDao.findAll(spec, pageRequest);
		return ApiResponseResult.success().data(Datagrid.create(page.getContent(), (int)page.getTotalElements(), pageRequest.getPageNumber()+1, pageRequest.getPageSize()));
	}

}
