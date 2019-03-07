package com.unind.base.provider.admin.role.internal;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;

import com.unind.base.dao.admin.SysRoleDao;
import com.unind.base.data.ApiResponseResult;
import com.unind.base.dbconnection.query.BaseDao;
import com.unind.base.dbconnection.query.Datagrid;
import com.unind.base.dbconnection.query.SQLParameter;
import com.unind.base.domain.admin.role.SysRole;
import com.unind.base.provider.BaseService;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.admin.role.SysRoleService;
import com.unind.base.provider.context.SessionContextUtils;

@Service
@Transactional(rollbackFor=BusinessException.class)
public class SysRoleImpl extends BaseService implements SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private BaseDao baseDao;

	public ApiResponseResult add(SysRole sysRole) throws BusinessException {
		if(StringUtils.isEmpty(sysRole.getBsCode()) || StringUtils.isEmpty(sysRole.getBsCode().trim())) {
			return ApiResponseResult.failure("编码不能为空");
		}
		if(StringUtils.isEmpty(sysRole.getBsName()) || StringUtils.isEmpty(sysRole.getBsName().trim())) {
			return ApiResponseResult.failure("名称不能为空");
		}
		int counts = sysRoleDao.countByBsCode(sysRole.getBsCode());
		if(counts > 0) {
			return ApiResponseResult.failure("编码不能重复");
		}
		sysRole.setBsCode(sysRole.getBsCode().trim());
		sysRole.setBsName(sysRole.getBsName().trim());
		sysRole.setBsCreatedTime(new Date());
		sysRole.setPkCreatedBy(SessionContextUtils.getCurrentUser().getId());
		sysRoleDao.save(sysRole);
		return ApiResponseResult.success();
	}

	@Transactional
	public ApiResponseResult edit(SysRole sysRole) throws BusinessException {
		if (null == sysRole || null == sysRole.getId()) {
			return ApiResponseResult.failure("记录ID为必填项");
		}
		SysRole o = sysRoleDao.findOne(sysRole.getId());
		if (null == o) {
			return ApiResponseResult.failure("记录ID不存在或已被删除");
		}
		if(!StringUtils.equals(sysRole.getBsCode(), o.getBsCode())) {
			int counts = sysRoleDao.countByBsCode(sysRole.getBsCode());
			if(counts > 0) {
				return ApiResponseResult.failure("编码不能重复");
			}
		}
		o.setPkSysRolegroup(sysRole.getPkSysRolegroup());
		o.setBsComment(sysRole.getBsComment());
		o.setBsCode(sysRole.getBsCode().trim());
		o.setBsName(sysRole.getBsName().trim());
		o.setBsModifiedTime(new Date());
		o.setPkModifiedBy(SessionContextUtils.getCurrentUser().getId());
		sysRoleDao.save(o);
		return ApiResponseResult.success();
	}

	@Transactional
	public ApiResponseResult delete(SysRole sysRole) throws BusinessException {
		if (null == sysRole || null == sysRole.getId()) {
			return ApiResponseResult.failure("记录ID为必填项");
		}
		SysRole o = sysRoleDao.findOne(sysRole.getId());
		if (null == o) {
			return ApiResponseResult.failure("记录ID不存在或已被删除");
		}
		sysRoleDao.delete(o);
		return ApiResponseResult.success();
	}

	@Transactional(readOnly = true)
	public ApiResponseResult getlist(SysRole sysRole, PageRequest pageRequest) {
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		if(StringUtils.isNotEmpty(sysRole.getBsCode())) {
			filters.add(new SearchFilter("bsCode", Operator.EQ, sysRole.getBsCode()));
		}
		if(StringUtils.isNotEmpty(sysRole.getBsName())) {
			filters.add(new SearchFilter("bsName", Operator.LIKE, "%"+sysRole.getBsName()+"%"));
		}
		Specifications<SysRole> spec = Specifications.where(super.and(filters, SysRole.class));
		Page<SysRole> page = sysRoleDao.findAll(spec, pageRequest);
		return ApiResponseResult.success().data(Datagrid.create(page.getContent(), (int)page.getTotalElements(), pageRequest.getPageNumber()+1, pageRequest.getPageSize()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public ApiResponseResult gettree() throws BusinessException {
		String hql = "select r from " + SysRole.class.getName() + " r order by r.rolegroup.bsCreatedTime, r.bsCreatedTime asc";
		SQLParameter<Object> parameters = SQLParameter.newInstance();
		List<SysRole> list = baseDao.findAll(hql, parameters);

		Map<Long, Map<String, Object>> parents = new HashMap<Long, Map<String, Object>>();

		Map<String, Object> root;
		List<Map<String, Object>> children;
		Map<String, Object> parent;

		Map<String, Object> child;
		for (SysRole role : list) {
			child = new HashMap<String, Object>();
			child.put("id", role.getId());
			child.put("title", role.getBsName());

			root = new HashMap<String, Object>();
			root.put("id", role.getPkSysRolegroup());
			root.put("title", role.getRolegroup().getBsName());
			child.put("parent", root);

			if(parents.containsKey(role.getPkSysRolegroup())) {
				parent = parents.get(role.getPkSysRolegroup());
				children = (List<Map<String, Object>>) parent.get("children");
				children.add(child);
			}else {
				parent = new HashMap<String, Object>();
				parent.put("id", role.getPkSysRolegroup());
				parent.put("title", role.getRolegroup().getBsName());
				parent.put("bsLevel", 1);
				children = new ArrayList<Map<String, Object>>();
				children.add(child);
				parent.put("children", children);
				parents.put(role.getPkSysRolegroup(), parent);
			}
		}

		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		Set<Long> set = parents.keySet();
		Iterator<Long> it = set.iterator();
		Long id;
		while (it.hasNext()) {
			id = it.next();
			resList.add(parents.get(id));
		}
		return ApiResponseResult.success().data(resList);
	}

}
