package com.unind.base.web.admin;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.perm.SysPerm;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.admin.perm.SysPermService;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;

/**
 * 权限管理
 * @author tanxiang
 *
 */
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/perm")
public class SysPermController extends WebController {
	@Autowired
	private SysPermService sysPermService;

	@Autowired
	DataSource dataSource;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(SysPerm sysPerm) {
		try {
			return sysPermService.add(sysPerm);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ApiResponseResult edit(SysPerm sysPerm) {
		try {
			return sysPermService.edit(sysPerm);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApiResponseResult delete(SysPerm sysPerm) {
		try {
			return sysPermService.delete(sysPerm);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@RequestMapping(value = "/getlist")
	public ApiResponseResult getlist(SysPerm sysPerm) {
		try {
			return sysPermService.getlist(sysPerm, super.getPageRequest());
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

}
