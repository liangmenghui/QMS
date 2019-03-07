package com.unind.base.provider.admin.resrce.internal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;

import com.unind.base.dao.admin.SysResrceDao;
import com.unind.base.data.ApiResponseResult;
import com.unind.base.data.DataHelper;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.domain.admin.resrce.SysResrce;
import com.unind.base.provider.BaseService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.admin.resrce.SysResrceService;
import com.unind.base.provider.context.SessionContextUtils;

@Service
@Transactional(rollbackFor=BusinessException.class)
public class SysResrceImpl extends BaseService implements SysResrceService {
	@Autowired
	private SysResrceDao sysResrceDao;

	public ApiResponseResult add(SysResrce sysResrce) throws BusinessException {
		if(StringUtils.isEmpty(sysResrce.getBsCode()) || StringUtils.isEmpty(sysResrce.getBsCode().trim())) {
			return ApiResponseResult.failure("编码不能为空");
		}
		if(StringUtils.isEmpty(sysResrce.getBsName()) || StringUtils.isEmpty(sysResrce.getBsName().trim())) {
			return ApiResponseResult.failure("名称不能为空");
		}
		int counts = sysResrceDao.countByBsCode(sysResrce.getBsCode());
		if(counts > 0) {
			return ApiResponseResult.failure("编码已存在");
		}
		if(null==sysResrce.getBsLeaf()) {
			sysResrce.setBsLeaf(BaseEnumConstants.FALSE);
		}else {
			sysResrce.setBsLeaf(BaseEnumConstants.TRUE);
		}
		sysResrce.setBsCode(sysResrce.getBsCode().trim());
		sysResrce.setBsName(sysResrce.getBsName().trim());
		sysResrce.setBsCreatedTime(new Date());
		sysResrceDao.save(sysResrce);
		return ApiResponseResult.success();
	}

	@Transactional
	public ApiResponseResult edit(SysResrce sysResrce) throws BusinessException {
		if (null == sysResrce || null == sysResrce.getId()) {
			return ApiResponseResult.failure("记录ID为必填项");
		}
		if(StringUtils.isEmpty(sysResrce.getBsCode()) || StringUtils.isEmpty(sysResrce.getBsCode().trim())) {
			return ApiResponseResult.failure("编码不能为空");
		}
		if(StringUtils.isEmpty(sysResrce.getBsName()) || StringUtils.isEmpty(sysResrce.getBsName().trim())) {
			return ApiResponseResult.failure("名称不能为空");
		}
		SysResrce o = sysResrceDao.findOne(sysResrce.getId());
		if (null == o) {
			return ApiResponseResult.failure("记录ID不存在或已被删除");
		}
		if(!StringUtils.equals(sysResrce.getBsCode(), o.getBsCode())) {
			int counts = sysResrceDao.countByBsCode(sysResrce.getBsCode());
			if(counts > 0) {
				return ApiResponseResult.failure("编码不能重复");
			}
		}
		o.setBsComment(sysResrce.getBsComment());
		o.setBsCode(sysResrce.getBsCode().trim());
		o.setBsName(sysResrce.getBsName().trim());
		o.setBsComment(sysResrce.getBsComment());
		o.setBsEnableState(sysResrce.getBsEnableState());
		o.setBsSortNo(sysResrce.getBsSortNo());
		o.setBsLeaf(sysResrce.getBsLeaf());
		o.setBsIconCls(sysResrce.getBsIconCls());
		o.setBsUrl(sysResrce.getBsUrl());
		o.setBsModifiedTime(new Date());
		o.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
		sysResrceDao.save(o);
		return ApiResponseResult.success();
	}

	@Transactional
	public ApiResponseResult delete(SysResrce sysResrce) throws BusinessException {
		if (null == sysResrce || null == sysResrce.getId()) {
			return ApiResponseResult.failure("记录ID为必填项");
		}
		SysResrce o = sysResrceDao.findOne(sysResrce.getId());
		if (null == o) {
			return ApiResponseResult.failure("记录ID不存在或已被删除");
		}
		sysResrceDao.delete(o);
		return ApiResponseResult.success();
	}

	@Transactional(readOnly = true)
	public ApiResponseResult getlist(SysResrce sysResrce, PageRequest pageRequest) {
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		if(null!=sysResrce.getPkParent()) {
			filters.add(new SearchFilter("pkParent", Operator.EQ, sysResrce.getPkParent()));
		}
		if(StringUtils.isNotEmpty(sysResrce.getBsName())) {
			filters.add(new SearchFilter("bsName", Operator.EQ, sysResrce.getBsName()));
		}
		Specifications<SysResrce> spec = Specifications.where(super.and(filters, SysResrce.class));
		Page<SysResrce> page = sysResrceDao.findAll(spec, pageRequest);
		return ApiResponseResult.success().data(Datagrid.create(page.getContent(), (int)page.getTotalElements(), pageRequest.getPageNumber()+1, pageRequest.getPageSize()));
	}

	public ApiResponseResult gettree(SysResrce resrce) {
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		Specifications<SysResrce> spec = Specifications.where(super.and(filters, SysResrce.class));
		Sort sort = new Sort("bsSortNo");
		List<SysResrce> list = sysResrceDao.findAll(spec, sort);
		List<Map<String, Object>> resrces = DataHelper.convertListToTreeData(list, -1l, "id", "bsName", SysResrce.class);
		return ApiResponseResult.success().data(resrces);
	}

}
