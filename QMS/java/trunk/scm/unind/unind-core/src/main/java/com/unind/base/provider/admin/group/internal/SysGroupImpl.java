package com.unind.base.provider.admin.group.internal;

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

import com.unind.base.dao.admin.SysGroupDao;
import com.unind.base.data.ApiResponseResult;
import com.unind.base.data.DataHelper;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.domain.admin.group.SysGroup;
import com.unind.base.provider.BaseService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.admin.group.SysGroupService;
import com.unind.base.provider.context.SessionContextUtils;

@Service
@Transactional(rollbackFor=BusinessException.class)
public class SysGroupImpl extends BaseService implements SysGroupService {
	@Autowired
	private SysGroupDao sysGroupDao;

	public ApiResponseResult add(SysGroup sysGroup) throws BusinessException {
		if(StringUtils.isEmpty(sysGroup.getBsCode()) || StringUtils.isEmpty(sysGroup.getBsCode().trim())) {
			return ApiResponseResult.failure("编号不能为空");
		}
		if(StringUtils.isEmpty(sysGroup.getBsName()) || StringUtils.isEmpty(sysGroup.getBsName().trim())) {
			return ApiResponseResult.failure("名称不能为空");
		}
		int counts = sysGroupDao.countByBsCode(sysGroup.getBsCode());
		if(counts > 0) {
			return ApiResponseResult.failure("编号不能重复");
		}
		sysGroup.setBsCreatedTime(new Date());
		sysGroupDao.save(sysGroup);
		return ApiResponseResult.success();
	}

	@Transactional
	public ApiResponseResult edit(SysGroup sysGroup) throws BusinessException {
		if (null == sysGroup || null == sysGroup.getId()) {
			return ApiResponseResult.failure("记录ID为必填项");
		}
		if(StringUtils.isEmpty(sysGroup.getBsCode()) || StringUtils.isEmpty(sysGroup.getBsCode().trim())) {
			return ApiResponseResult.failure("编号不能为空");
		}
		if(StringUtils.isEmpty(sysGroup.getBsName()) || StringUtils.isEmpty(sysGroup.getBsName().trim())) {
			return ApiResponseResult.failure("名称不能为空");
		}
		SysGroup o = sysGroupDao.findOne(sysGroup.getId());
		if (null == o) {
			return ApiResponseResult.failure("记录ID不存在或已被删除");
		}
		if(!StringUtils.equals(sysGroup.getBsCode().trim(), o.getBsCode())) {
			int counts = sysGroupDao.countByBsCode(sysGroup.getBsCode());
			if(counts > 0) {
				return ApiResponseResult.failure("编号不能重复");
			}
			o.setBsCode(sysGroup.getBsCode().trim());
		}
		o.setBsName(sysGroup.getBsName());
		o.setBsComment(sysGroup.getBsComment());
		o.setBsModifiedTime(new Date());
		o.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
		sysGroupDao.save(o);
		return ApiResponseResult.success();
	}

	@Transactional
	public ApiResponseResult delete(SysGroup sysGroup) throws BusinessException {
		SysGroup o = sysGroupDao.findOne(sysGroup.getId());
		if (null == o) {
			return ApiResponseResult.failure("记录ID不存在或已被删除");
		}
		if(o.getId() <= 0) {
			return ApiResponseResult.failure("没有操作权限");
		}
		o.setBsIsDel(BaseEnumConstants.TRUE);
		sysGroupDao.save(o);
		return ApiResponseResult.success();
	}

	@Transactional(readOnly = true)
	public ApiResponseResult getlist(SysGroup sysGroup, PageRequest pageRequest) {
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		if(StringUtils.isNotEmpty(sysGroup.getBsName())) {
			filters.add(new SearchFilter("bsName", Operator.EQ, sysGroup.getBsName()));
		}
		Specifications<SysGroup> spec = Specifications.where(super.and(filters, SysGroup.class));
		Page<SysGroup> page = sysGroupDao.findAll(spec, pageRequest);
		return ApiResponseResult.success().data(DataHelper.convertToDatagrid(page.getContent(), page.getTotalElements()));
	}

}
