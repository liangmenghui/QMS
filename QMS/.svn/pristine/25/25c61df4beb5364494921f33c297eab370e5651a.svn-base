package com.unind.base.provider.admin.agg;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;

public interface SysResrcePermAggService {

	/**
	 * 给资源配置权限 ,用以资源关联权限
	 * 
	 * @param pkResrce
	 *            资源主键id
	 * @param pkPerms
	 *            权限主键id
	 * @return
	 */
	public ApiResponseResult save(Long pkResrce, Long[] pkPerms) throws BusinessException;

	/**
	 * 查询资源权限及分配的权限列表
	 * @param pkResrce
	 * @return
	 */
	public ApiResponseResult getlist(Long pkResrce);
}
