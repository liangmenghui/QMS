package com.unind.base.web.admin;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.role.SysRole;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.admin.role.SysRoleService;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;

/**
 * 角色管理
 * @author tanxiang
 *
 */
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/role")
public class SysRoleController extends WebController {
	@Autowired
	private SysRoleService sysRoleService;

	@Autowired
	DataSource dataSource;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(SysRole sysRole) {
		try {
			return sysRoleService.add(sysRole);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ApiResponseResult edit(SysRole sysRole) {
		try {
			return sysRoleService.edit(sysRole);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApiResponseResult delete(SysRole sysRole) {
		try {
			return sysRoleService.delete(sysRole);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@RequestMapping(value = "/getlist")
	public ApiResponseResult getlist(SysRole sysRole) {
		try {
			return sysRoleService.getlist(sysRole, super.getPageRequest());
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@RequestMapping(value = "/gettree")
	public ApiResponseResult gettree(SysRole sysRole) {
		try {
			return sysRoleService.gettree();
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

}
