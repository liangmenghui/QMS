package com.unind.base.provider.admin.perm;

import org.springframework.data.domain.PageRequest;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.perm.SysPerm;
import com.unind.base.provider.BusinessException;

/**
 * 权限
 * @author tanxiang
 *
 */
public interface SysPermService {

	public ApiResponseResult add(SysPerm perm) throws BusinessException;

	public ApiResponseResult edit(SysPerm perm) throws BusinessException;

	public ApiResponseResult delete(SysPerm perm) throws BusinessException;

	public ApiResponseResult getlist(SysPerm perm, PageRequest pageRequest) throws BusinessException;
}
