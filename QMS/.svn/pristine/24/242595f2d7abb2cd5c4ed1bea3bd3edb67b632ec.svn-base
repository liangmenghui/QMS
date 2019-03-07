package com.unind.base.provider.admin.agg.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.persistence.SearchFilter.Operator;

import com.unind.base.dao.admin.SysResrceDao;
import com.unind.base.dao.admin.SysRoleDao;
import com.unind.base.dao.admin.agg.SysRolePermsAggDao;
import com.unind.base.data.ApiResponseResult;
import com.unind.base.data.DataHelper;
import com.unind.base.domain.admin.agg.SysResrcePermAgg;
import com.unind.base.domain.admin.agg.SysRolePermsAgg;
import com.unind.base.domain.admin.perm.SysPerm;
import com.unind.base.domain.admin.resrce.SysResrce;
import com.unind.base.domain.admin.role.SysRole;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.admin.agg.ResrcePermsOptionVO;
import com.unind.base.provider.admin.agg.RoleResrcePermsVO;
import com.unind.base.provider.admin.agg.SysRolePermsAggService;

@Service
public class SysRolePermsAggServiceImpl extends BaseOprService implements SysRolePermsAggService {
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysResrceDao sysResrceDao;
	@Autowired
	private SysRolePermsAggDao sysRolePermsAggDao;

	/**
	 * 给角色分配资源与权限
	 * 
	 * @param rrpvo
	 * @return
	 */
	@Transactional
	public ApiResponseResult save(RoleResrcePermsVO rrpvo) {
		if (rrpvo == null || rrpvo.getRoleId() == null) {
			return ApiResponseResult.failure("参数异常");
		}
		long roleId = rrpvo.getRoleId();
		SysRole sysRole = sysRoleDao.findOne(roleId);
		if (sysRole == null) {
			return ApiResponseResult.failure("角色不存在");
		}

		// 先删除已分配,在插入现在需分配的
		sysRolePermsAggDao.deleteByPkRole(roleId);

		if (rrpvo.getOptions() != null || rrpvo.getOptions().size() > 0) {
			SysRolePermsAgg sysRolePermsAgg;
			for (ResrcePermsOptionVO optitem : rrpvo.getOptions()) {
				if (optitem.getResrceId() != null) {
					if (optitem.getPermIds() == null || optitem.getPermIds().length == 0) {
						sysRolePermsAgg = new SysRolePermsAgg();
						sysRolePermsAgg.setPkRole(roleId);
						sysRolePermsAgg.setPkResrce(optitem.getResrceId());
						sysRolePermsAggDao.save(sysRolePermsAgg);
					} else {
						for (Long permid : optitem.getPermIds()) {
							sysRolePermsAgg = new SysRolePermsAgg();
							sysRolePermsAgg.setPkRole(roleId);
							sysRolePermsAgg.setPkResrce(optitem.getResrceId());
							sysRolePermsAgg.setPkPerm(permid);
							sysRolePermsAggDao.save(sysRolePermsAgg);
						}
					}
				}
			}
		}
		return ApiResponseResult.success();
	}

	@Override
	public ApiResponseResult getlist(Long pkRole) {
		Map<String, Object> map = new HashMap<String, Object>();

		//查询角色下可用资源列表
		List<SysResrce> list = sysResrceDao.findAll();
		List<Map<String, Object>> resrces = DataHelper.convertListToTreeData(list, -1l, "id", "bsName", SysResrce.class);
		map.put("resrces", resrces);

		//查询所有资源权限列表
		String sql = "select rp.pk_resrce as \"pkResrce\", r.bs_code as \"resrceCode\", r.bs_name as \"resrceName\", rp.pk_perm as \"pkPerm\", p.bs_code as \"permCode\", p.bs_name as \"permName\""
				+ " from " + SysResrcePermAgg.TABLE_NAME + " rp"
				+ " left join " + SysResrce.TABLE_NAME + " r on r.id = rp.pk_resrce"
				+ " left join " + SysPerm.TABLE_NAME + " p on p.id = rp.pk_perm";
		List<Map<String, Object>> permList = findBySql(sql, new HashMap<String, Object>());
		Map<String, List<Map<String, Object>>> perms = new HashMap<String, List<Map<String, Object>>>();
		List<Map<String, Object>> children;
		String resrceCode;
		for (Map<String, Object> permMap : permList) {
			if(null==permMap.get("resrceCode")) {
				continue;
			}
			resrceCode = (String) permMap.get("resrceCode");
			if(perms.containsKey(resrceCode)) {
				perms.get(resrceCode).add(permMap);
			}else {
				children = new ArrayList<Map<String, Object>>();
				children.add(permMap);
				perms.put(resrceCode, children);
			}
		}
		map.put("perms", perms);

		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter("pkRole", Operator.EQ, pkRole));
		Specifications<SysRolePermsAgg> spec = Specifications.where(super.and(filters, SysRolePermsAgg.class));
		List<SysRolePermsAgg> rolePerms = sysRolePermsAggDao.findAll(spec);
		Map<String, List<SysPerm>> alloced = new HashMap<String, List<SysPerm>>();
		List<SysPerm> allocedPerms;
		for (SysRolePermsAgg rolePerm : rolePerms) {
			if(null==rolePerm.getResrce()) {
				continue;
			}
			if(alloced.containsKey(rolePerm.getResrce().getBsCode())) {
				alloced.get(rolePerm.getResrce().getBsCode()).add(rolePerm.getPerm());
				continue;
			}
			allocedPerms = new ArrayList<SysPerm>();
			allocedPerms.add(rolePerm.getPerm());
			alloced.put(rolePerm.getResrce().getBsCode(), allocedPerms);
		}
		map.put("alloced", alloced);
		return ApiResponseResult.success().data(map);
	}

	/**
	 * 查询角色拥有资源 去重
	 */
	public List<Map<String, Object>> findRoleResrceList(long roleId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("roleId", roleId);

		StringBuffer sqlbf = new StringBuffer();
		sqlbf.append(" SELECT DISTINCT");
		sqlbf.append(" e.id as resrceId,e.bs_code as resrceCode,e.bs_name as resrceName");
		sqlbf.append(" from sys_role_perms_agg a");
		sqlbf.append(" LEFT JOIN sys_resrce e ON (a.pk_resrce = e.id)");
		sqlbf.append(" where a.pk_role = :roleId");

		return super.findBySql(sqlbf.toString(), param);
	}

	/**
	 * 查询角色拥有资源权限 去重
	 */
	public List<Map<String, Object>> findRoleResrcePermList(long roleId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("roleId", roleId);

		StringBuffer sqlbf = new StringBuffer();
		sqlbf.append(" SELECT DISTINCT");
		sqlbf.append(" e.id AS resrceId, e.bs_code AS resrceCode, e.bs_name AS resrceName");
		sqlbf.append(" ,m.id AS permId, m.bs_code AS permCode, m.bs_name AS permName");
		sqlbf.append(" from sys_role_perms_agg a");
		sqlbf.append(" LEFT JOIN sys_perm m ON (a.pk_perm = m.id)");
		sqlbf.append(" LEFT JOIN sys_resrce e ON (a.pk_resrce = e.id)");
		sqlbf.append(" where a.pk_role = :roleId");
		sqlbf.append(" ORDER BY resrceId , permId");

		return super.findBySql(sqlbf.toString(), param);
	}

}
