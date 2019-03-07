package com.unind.base.web.admin;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.role.SysRolegroup;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.admin.role.SysRolegroupService;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;

/**
 * 角色管理
 * @author tanxiang
 *
 */
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/rolegroup")
public class SysRolegroupController extends WebController {
	@Autowired
	private SysRolegroupService sysRolegroupService;

	@Autowired
	DataSource dataSource;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(SysRolegroup sysRolegroup) {
		try {
			return sysRolegroupService.add(sysRolegroup);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ApiResponseResult edit(SysRolegroup sysRolegroup) {
		try {
			return sysRolegroupService.edit(sysRolegroup);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApiResponseResult delete(SysRolegroup sysRolegroup) {
		try {
			return sysRolegroupService.delete(sysRolegroup);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@RequestMapping(value = "/getlist")
	public ApiResponseResult getlist(SysRolegroup sysRolegroup) {
		try {
			return sysRolegroupService.getlist(sysRolegroup, super.getPageRequest());
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

}
