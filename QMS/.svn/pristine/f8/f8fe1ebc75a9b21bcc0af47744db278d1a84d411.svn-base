package com.unind.base.provider.admin.org.internal;

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

import com.unind.base.dao.admin.SysOrgDao;
import com.unind.base.data.ApiResponseResult;
import com.unind.base.data.DataHelper;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.domain.admin.org.SysOrg;
import com.unind.base.provider.BaseService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.admin.org.SysOrgService;
import com.unind.base.provider.context.SessionContextUtils;

@Service
@Transactional(rollbackFor=BusinessException.class)
public class SysOrgImpl extends BaseService implements SysOrgService {
	@Autowired
	private SysOrgDao sysOrgDao;

	public ApiResponseResult add(SysOrg sysOrg) throws BusinessException {
		if(StringUtils.isEmpty(sysOrg.getBsCode()) || StringUtils.isEmpty(sysOrg.getBsCode().trim())) {
			return ApiResponseResult.failure("编号不能为空");
		}
		if(StringUtils.isEmpty(sysOrg.getBsName()) || StringUtils.isEmpty(sysOrg.getBsName().trim())) {
			return ApiResponseResult.failure("名称不能为空");
		}
		int counts = sysOrgDao.countByBsCode(sysOrg.getBsCode());
		if(counts > 0) {
			return ApiResponseResult.failure("名称不能重复");
		}
		sysOrg.setBsCode(sysOrg.getBsCode().trim());
		sysOrg.setBsName(sysOrg.getBsName().trim());
		sysOrg.setBsCreatedTime(new Date());
		sysOrgDao.save(sysOrg);
		return ApiResponseResult.success();
	}

	@Transactional
	public ApiResponseResult edit(SysOrg sysOrg) throws BusinessException {
		if (null == sysOrg || null == sysOrg.getId()) {
			return ApiResponseResult.failure("记录ID为必填项");
		}
		if(StringUtils.isEmpty(sysOrg.getBsCode()) || StringUtils.isEmpty(sysOrg.getBsCode().trim())) {
			return ApiResponseResult.failure("编号不能为空");
		}
		if(StringUtils.isEmpty(sysOrg.getBsName()) || StringUtils.isEmpty(sysOrg.getBsName().trim())) {
			return ApiResponseResult.failure("名称不能为空");
		}
		SysOrg o = sysOrgDao.findOne(sysOrg.getId());
		if (null == o) {
			return ApiResponseResult.failure("记录ID不存在或已被删除");
		}
		if(!StringUtils.equals(sysOrg.getBsCode(), o.getBsCode())) {
			int counts = sysOrgDao.countByBsCode(sysOrg.getBsCode());
			if(counts > 0) {
				return ApiResponseResult.failure("名称不能重复");
			}
			o.setBsCode(sysOrg.getBsCode());
		}
		o.setBsComment(sysOrg.getBsComment());
		o.setBsCode(sysOrg.getBsCode().trim());
		o.setBsName(sysOrg.getBsName().trim());
		o.setBsModifiedTime(new Date());
		o.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
		sysOrgDao.save(o);
		return ApiResponseResult.success();
	}

	@Transactional
	public ApiResponseResult delete(SysOrg sysOrg) throws BusinessException {
		SysOrg o = sysOrgDao.findOne(sysOrg.getId());
		if (null == o) {
			return ApiResponseResult.failure("记录ID不存在或已被删除");
		}
		if(o.getId() <= 0) {
			return ApiResponseResult.failure("没有操作权限");
		}
		o.setBsIsDel(BaseEnumConstants.TRUE);
		sysOrgDao.save(o);
		return ApiResponseResult.success();
	}

	@Transactional(readOnly = true)
	public ApiResponseResult getlist(SysOrg sysOrg, PageRequest pageRequest) {
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		if(null!=sysOrg.getPkGroup()) {
			filters.add(new SearchFilter("pkGroup", Operator.EQ, sysOrg.getPkGroup()));
		}
		if(StringUtils.isNotEmpty(sysOrg.getBsName())) {
			filters.add(new SearchFilter("bsName", Operator.EQ, sysOrg.getBsName()));
		}
		Specifications<SysOrg> spec = Specifications.where(super.and(filters, SysOrg.class));
		Page<SysOrg> page = sysOrgDao.findAll(spec, pageRequest);
		return ApiResponseResult.success().data(DataHelper.convertToDatagrid(page.getContent(), page.getTotalElements()));
	}

}
