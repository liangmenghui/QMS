package com.unind.base.web.admin.agg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.admin.agg.SysResrcePermAggService;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;

/**
 * 资源权限
 * @author tanxiang
 *
 */
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/resrceperm")
public class SysResrcePermAggController extends WebController {
	@Autowired
	private SysResrcePermAggService sysResrcePermAggService;

	/**
	 * 保存
	 * @param sysGroup
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ApiResponseResult add(Long pkResrce, Long[] pkPerms) {
		try {
			return sysResrcePermAggService.save(pkResrce, pkPerms);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	/**
	 * 获取资源权限列表
	 * @param sysGroup
	 * @return
	 */
	@RequestMapping(value = "/getlist")
	public ApiResponseResult getlist(Long pkResrce) {
		return sysResrcePermAggService.getlist(pkResrce);
	}
}
