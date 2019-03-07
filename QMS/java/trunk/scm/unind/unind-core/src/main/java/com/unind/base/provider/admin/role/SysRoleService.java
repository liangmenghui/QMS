package com.unind.base.provider.admin.role;

import org.springframework.data.domain.PageRequest;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.role.SysRole;
import com.unind.base.provider.BusinessException;

/**
 * 角色
 * @author tanxiang
 *
 */
public interface SysRoleService {

	public ApiResponseResult add(SysRole role) throws BusinessException;

	public ApiResponseResult edit(SysRole role) throws BusinessException;

	public ApiResponseResult delete(SysRole role) throws BusinessException;

	public ApiResponseResult getlist(SysRole role, PageRequest pageRequest) throws BusinessException;

	public ApiResponseResult gettree() throws BusinessException;
}
