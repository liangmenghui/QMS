package com.unind.base.provider.admin.agg.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unind.base.dao.admin.SysRoleDao;
import com.unind.base.dao.admin.SysUserDao;
import com.unind.base.dao.admin.agg.SysUserRolesAggDao;
import com.unind.base.data.ApiResponseResult;
import com.unind.base.data.DataHelper;
import com.unind.base.dbconnection.query.BaseDao;
import com.unind.base.dbconnection.query.SQLParameter;
import com.unind.base.domain.admin.agg.SysUserRolesAgg;
import com.unind.base.domain.admin.enumeration.BaseEnumConstants;
import com.unind.base.domain.admin.role.SysRole;
import com.unind.base.domain.admin.user.SysUser;
import com.unind.base.provider.BaseOprService;
import com.unind.base.provider.admin.agg.SysUserRolesAggService;

@Service
public class SysUserRolesAggServiceImpl extends BaseOprService implements SysUserRolesAggService {
	@Autowired
	private BaseDao baseDao;

	@Autowired
	private SysUserRolesAggDao sysUserRolesAggDao;

	@Autowired
	private SysUserDao sysUserDao;

	@Autowired
	private SysRoleDao sysRoleDao;

	/**
	 * 给用户分配角色
	 * 
	 * @param sysUserId
	 *            用户主键id
	 * @param sysRoleIds
	 *            角色主键id
	 * @return
	 */
	@Transactional
	public ApiResponseResult saveUserRoleAgg(Long sysUserId, Long[] sysRoleIds) {
		if (sysUserId == null || sysRoleIds == null) {
			return ApiResponseResult.failure("参数异常");
		}

		SysUser sysUser = sysUserDao.findOne(sysUserId);
		if (sysUser == null) {
			return ApiResponseResult.failure("帐号不存在");
		}

		// 删除用户拥有的角色
		sysUserRolesAggDao.deleteByPkUser(sysUserId);

		SysRole sysRole = null;
		SysUserRolesAgg sysUserRolesAgg = null;
		for (Long roleId : sysRoleIds) {
			sysRole = sysRoleDao.findOne(roleId);
			if (sysRole != null) {
				sysUserRolesAgg = new SysUserRolesAgg();
				sysUserRolesAgg.setPkUser(sysUserId);
				sysUserRolesAgg.setPkRole(roleId);
				// 插入用户选择的角色
				sysUserRolesAggDao.save(sysUserRolesAgg);
			}
		}

		return ApiResponseResult.success();
	}

	/**
	 * 查询用户拥有角色 去重
	 */
	public List<Map<String, Object>> findUserRoleList(long sysUserId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sysUserId", sysUserId);

		StringBuffer sqlbf = new StringBuffer();
		sqlbf.append(" SELECT DISTINCT");
		sqlbf.append(" r.id AS roleId, r.bs_code AS roleCode, r.bs_name AS roleName");
		sqlbf.append(" from sys_user_roles_agg a");
		sqlbf.append(" LEFT JOIN sys_role r ON (a.pk_role = r.id)");
		sqlbf.append(" WHERE a.pk_user = :sysUserId");

		return super.findBySql(sqlbf.toString(), param);
	}

	/**
	 * 查询用户拥有资源 去重
	 */
	public List<Map<String, Object>> findUserResrceList(long sysUserId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sysUserId", sysUserId);

		StringBuffer sqlbf = new StringBuffer();
		sqlbf.append(" SELECT DISTINCT");
		sqlbf.append(" e.id AS resrceId, e.bs_code AS resrceCode, e.bs_name AS resrceName");
		sqlbf.append(" FROM sys_role_perms_agg a");
		sqlbf.append(" LEFT JOIN sys_perm m ON (a.pk_perm = m.id)");
		sqlbf.append(" LEFT JOIN sys_resrce e ON (a.pk_resrce = e.id)");
		sqlbf.append(" LEFT JOIN sys_role r ON (a.pk_role = r.id)");
		sqlbf.append(" LEFT JOIN sys_user_roles_agg g ON (g.pk_role = r.id)");
		sqlbf.append(" LEFT JOIN sys_user u ON (g.pk_user = u.id)");
		sqlbf.append(" WHERE u.id = :sysUserId");
		sqlbf.append(" ORDER BY e.id, m.id");

		return super.findBySql(sqlbf.toString(), param);
	}

	/**
	 * 查询用户拥有资源权限 去重
	 */
	public List<Map<String, Object>> findUserResrcePermList(long sysUserId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sysUserId", sysUserId);

		StringBuffer sqlbf = new StringBuffer();
		sqlbf.append(" SELECT DISTINCT");
		sqlbf.append(" e.id AS \"resrceId\", e.bs_code AS \"resrceCode\", e.bs_name AS \"resrceName\", e.bs_url as \"bsUrl\", e.pk_parent as \"pkParent\", e.bs_level as \"bsLevel\"");
		sqlbf.append(" ,m.id AS \"permId\", m.bs_code AS \"permCode\", m.bs_name AS \"permName\"");
		sqlbf.append(" FROM sys_role_perms_agg a");
		sqlbf.append(" LEFT JOIN sys_perm m ON (a.pk_perm = m.id)");
		sqlbf.append(" LEFT JOIN sys_resrce e ON (a.pk_resrce = e.id)");
		sqlbf.append(" LEFT JOIN sys_role r ON (a.pk_role = r.id)");
		sqlbf.append(" LEFT JOIN sys_user_roles_agg g ON (g.pk_role = r.id)");
		sqlbf.append(" LEFT JOIN sys_user u ON (g.pk_user = u.id)");
		sqlbf.append(" WHERE u.id = :sysUserId");
		sqlbf.append(" ORDER BY E.ID, M.ID");

		return super.findBySql(sqlbf.toString(), param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ApiResponseResult getlist(Long pkSysUser) {
		Map<String, Object> map = new HashMap<String, Object>();
		//查询已分配的角色列表
		String roleids_hql = "select uragg.pkRole from " + SysUserRolesAgg.class.getName() + " uragg where uragg.pkUser=?";
		SQLParameter<Object> parameters = SQLParameter.newInstance().add(pkSysUser);
		List<Long> roleids = baseDao.findAll(roleids_hql, parameters);

		String hql = "select r from " + SysRole.class.getName() + " r where r.bsIsDel=? order by r.rolegroup.bsCreatedTime, r.bsCreatedTime asc";
		parameters.reset().add(BaseEnumConstants.FALSE);
		List<SysRole> list = baseDao.findAll(hql, parameters);

		List<SysRole> allocedList = new ArrayList<SysRole>();
		List<SysRole> unallocedList = new ArrayList<SysRole>();
		for (SysRole sysRole : list) {
			if(roleids.contains(sysRole.getId())) {
				allocedList.add(sysRole);
			}else {
				unallocedList.add(sysRole);
			}
		}
		map.put("alloced", warpData(allocedList));
		map.put("unalloced", warpData(unallocedList));
		return ApiResponseResult.success().data(map);
	}

	@SuppressWarnings("unchecked")
	protected List<Map<String, Object>> warpData(List<SysRole> list) {
		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		Map<Long, Map<String, Object>> parents = new HashMap<Long, Map<String, Object>>();

		List<Map<String, Object>> children;
		Map<String, Object> parent;

		Map<String, Object> root;
		Map<String, Object> child;
		for (SysRole role : list) {
			child = new HashMap<String, Object>();
			child.put("id", role.getId());
			child.put("title", role.getBsName());
			child.put("isLeaf", true);

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
				parent.put("isLeaf", false);
				parent.put("expand", true);
				children = new ArrayList<Map<String, Object>>();
				children.add(child);
				parent.put("children", children);
				parents.put(role.getPkSysRolegroup(), parent);
			}
		}

		Set<Long> set = parents.keySet();
		Iterator<Long> it = set.iterator();
		Long id;
		while (it.hasNext()) {
			id = it.next();
			resList.add(parents.get(id));
		}
		return resList;
	}

	@Override
	public ApiResponseResult getuserlist(Long pkRole, String bsCode, String bsName, Pageable pageable) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pkRole", pkRole);
		StringBuilder sqlbf = new StringBuilder("select b.bs_Code as bsCode, b.bs_Name bsName, b.bs_Created_Time as bsCreatedTime, b.bs_Modified_Time as bsModifiedTime from sys_user_roles_agg a left join sys_user b on b.id=a.pk_user where a.pk_role=:pkRole");
		if(StringUtils.isNotEmpty(bsCode) && StringUtils.isNotEmpty(bsCode.trim())) {
			sqlbf.append(" and b.bs_code=:bsCode");
			param.put("bsCode", bsCode);
		}
		if(StringUtils.isNotEmpty(bsName) && StringUtils.isNotEmpty(bsName.trim())) {
			sqlbf.append(" and b.bs_name=:bsName");
			param.put("bsName", bsName);
		}
		sqlbf.append(" order by b.bs_code asc");
		Page<Map<String, Object>> page = super.findPageBySql(sqlbf.toString(), pageable, param);
		return ApiResponseResult.success().data(DataHelper.convertToDatagrid(page.getContent(), page.getTotalElements()));
	}

	/**
	 * 超级管理员查询所有资源权限 去重
	 */
	public List<Map<String, Object>> findSuperAdminResrcePermList() {
		Map<String, Object> param = new HashMap<String, Object>();

		StringBuffer sqlbf = new StringBuffer();
		sqlbf.append(" SELECT DISTINCT");
		sqlbf.append(" e.id AS \"resrceId\", e.bs_code AS \"resrceCode\", e.bs_name AS \"resrceName\", e.bs_url as \"bsUrl\", e.pk_parent as \"pkParent\", e.bs_level as \"bsLevel\"");
		sqlbf.append(" ,m.id AS \"permId\", m.bs_code AS \"permCode\", m.bs_name AS \"permName\"");
		sqlbf.append(" FROM sys_resrce e");
		sqlbf.append(" LEFT JOIN SYS_RESRCE_PERM_AGG X ON E.ID=X.PK_RESRCE");
		sqlbf.append(" LEFT JOIN SYS_PERM M ON X.PK_PERM=M.ID");
		sqlbf.append(" ORDER BY E.ID, M.ID");

		return super.findBySql(sqlbf.toString(), param);
	}
}
