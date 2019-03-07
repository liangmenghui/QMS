package com.unind.base.provider.admin.agg;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
public interface SysUserRolesAggService {

	/**
	 * 给用户分配角色
	 * 
	 * @param sysUserId
	 *            用户主键id
	 * @param sysRoleIds
	 *            角色主键id
	 * @return
	 */
	public ApiResponseResult saveUserRoleAgg(Long sysUserId, Long[] sysRoleIds) throws BusinessException;

	/**
	 * 查询用户拥有角色 去重
	 */
	public List<Map<String, Object>> findUserRoleList(long sysUserId);

	/**
	 * 查询用户拥有资源 去重
	 */
	public List<Map<String, Object>> findUserResrceList(long sysUserId);

	/**
	 * 查询用户拥有资源权限 去重
	 */
	public List<Map<String, Object>> findUserResrcePermList(long sysUserId);

	/**
	 * 查询已分配&未分配的角色列表
	 * @return
	 */
	public ApiResponseResult getlist(Long pkSysUser);

	/**
	 * 查询角色下的用户列表
	 * @param pkRole	角色id
	 * @param bsCode	用户账号
	 * @param bsName	用户名称
	 * @param pageable	分页参数
	 * @return
	 */
	public ApiResponseResult getuserlist(Long pkRole, String bsCode, String bsName, Pageable pageable);

	/**
	 * 超级管理员查询所有资源权限列表
	 * @return
	 */
	public List<Map<String, Object>> findSuperAdminResrcePermList();
}
