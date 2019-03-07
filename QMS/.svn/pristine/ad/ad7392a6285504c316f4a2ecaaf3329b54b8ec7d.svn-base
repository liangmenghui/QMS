package com.unind.base.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.resrce.SysResrce;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.admin.resrce.SysResrceService;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;

/**
 * 资源管理
 * @author tanxiang
 *
 */
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/resrce")
public class SysResrceController extends WebController {
	@Autowired
	private SysResrceService sysResrceService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(SysResrce sysResrce) {
		try {
			return sysResrceService.add(sysResrce);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ApiResponseResult edit(SysResrce sysResrce) {
		try {
			return sysResrceService.edit(sysResrce);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApiResponseResult delete(SysResrce sysResrce) {
		try {
			return sysResrceService.delete(sysResrce);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@RequestMapping(value = "/getlist")
	public ApiResponseResult getlist(SysResrce sysResrce) {
		try {
			Sort sort = new Sort("bsSortNo");
			return sysResrceService.getlist(sysResrce, super.getPageRequest(sort));
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@RequestMapping(value = "/gettree")
	public ApiResponseResult gettree(SysResrce sysResrce) {
		return sysResrceService.gettree(sysResrce);
	}

}
