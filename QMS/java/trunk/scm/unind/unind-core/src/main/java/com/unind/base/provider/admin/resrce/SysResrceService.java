package com.unind.base.provider.admin.resrce;

import org.springframework.data.domain.PageRequest;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.resrce.SysResrce;
import com.unind.base.provider.BusinessException;

/**
 * 资源
 * @author tanxiang
 *
 */
public interface SysResrceService {

	public ApiResponseResult add(SysResrce resrce) throws BusinessException;

	public ApiResponseResult edit(SysResrce resrce) throws BusinessException;

	public ApiResponseResult delete(SysResrce resrce) throws BusinessException;

	public ApiResponseResult getlist(SysResrce resrce, PageRequest pageRequest) throws BusinessException;

	public ApiResponseResult gettree(SysResrce resrce);
}
