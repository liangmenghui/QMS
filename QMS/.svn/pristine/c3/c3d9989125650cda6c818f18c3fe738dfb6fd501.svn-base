package com.unind.base.provider.admin.agg;

import java.util.List;
import java.util.Map;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;

public interface SysRolePermsAggService {

	/**
	 * 给角色分配资源与权限
	 * 
	 * @param rrpvo
	 * @return
	 */
	public ApiResponseResult save(RoleResrcePermsVO rrpvo) throws BusinessException;

	/**
	 * 查询角色可分配的资源及其操作权限
	 * @param pkRole
	 * @return
	 */
	public ApiResponseResult getlist(Long pkRole);

	/**
	 * 查询角色拥有资源 去重
	 */
	public List<Map<String, Object>> findRoleResrceList(long roleId);

	/**
	 * 查询角色拥有资源权限 去重
	 */
	public List<Map<String, Object>> findRoleResrcePermList(long roleId);
}
