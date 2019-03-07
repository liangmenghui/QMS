package com.unind.base.web.admin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;

//@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = Constants.CONTEXT_PATH)
public class LoginController extends WebController {

	/*@RequestMapping(value = "/logon")
	public String logon(
			@RequestParam(value = "username") String bsUserName,
			@RequestParam(value = "password") String bsPassword,
			@RequestParam(value = "rememberme", defaultValue="false") boolean rememberMe) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(bsUserName, bsPassword);
		try {
			token.setRememberMe(rememberMe);
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return autoView("admin/login");
		}
		return autoView("admin/index");
	}*/

	@RequestMapping(value = "/logon", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponseResult logon(
			@RequestParam(value = "username") String bsUserName,
			@RequestParam(value = "password") String bsPassword,
			@RequestParam(value = "rememberme", defaultValue="false") boolean rememberMe) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(bsUserName, bsPassword);
		try {
			token.setRememberMe(rememberMe);
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
//			return ApiResponseResult.failure("登录认证失败");
		}
		return ApiResponseResult.success("登录成功").data(subject.getPrincipal());
	}
}
