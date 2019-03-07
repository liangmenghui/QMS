package com.unind.base.web.admin;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.user.SysUser;
import com.unind.base.provider.context.SessionContextUtils;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;

@Controller
@RequestMapping(value=Constants.CONTEXT_PATH)
public class IndexController extends WebController {

	@RequestMapping("")
	public String index() {
		if(!SecurityUtils.getSubject().isAuthenticated()) {
			return autoView("admin/login");
		}
		return autoView("admin/index");
	}

	@RequestMapping("login")
	@ResponseBody
	public ApiResponseResult login() {
//		return autoView("admin/login");
		return ApiResponseResult.failure("帐号未登录或登录失效").data(90000);
	}

	@RequestMapping("logout")
	@ResponseBody
	public ApiResponseResult logout() {
		SysUser user2 = SessionContextUtils.getCurrentUser();
		if(!SecurityUtils.getSubject().isAuthenticated()) {
//			return autoView("admin/login");
			return ApiResponseResult.success("退出成功");
		}
		SecurityUtils.getSubject().logout();
		return ApiResponseResult.success("退出成功");
	}
}
