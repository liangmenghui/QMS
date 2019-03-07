package com.unind.base.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.user.SysUser;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.admin.user.SysUserService;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;

/**
 * 用户管理
 * @author tanxiang
 *
 */
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/user")
public class SysUserController extends WebController {
	@Autowired
	private SysUserService sysUserService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(SysUser user) {
		try {
			return sysUserService.add(user);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ApiResponseResult edit(SysUser user) {
		try {
			return sysUserService.edit(user);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@RequestMapping(value = "/editpassword", method = RequestMethod.POST)
	public ApiResponseResult editpassword(SysUser user) {
		try {
			return sysUserService.editpassword(user);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApiResponseResult delete(SysUser user) {
		try {
			if(null!=user.getId() && user.getId()==-1) {
				return ApiResponseResult.failure("没有删除权限");
			}
			return sysUserService.delete(user);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@RequestMapping(value = "/getlist")
	public ApiResponseResult getlist(SysUser user) {
		try {
			return sysUserService.getlist(user, super.getPageRequest());
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}
}
