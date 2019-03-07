package com.unind.base.provider.context;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;

import com.unind.base.domain.admin.user.SysUser;

public class SessionContextUtils {

	/**
	 * 当前登录用户
	 * @return
	 */
	public static SysUser getCurrentUser() {
		return (SysUser) SecurityUtils.getSubject().getPrincipal();
	}

	/**
	 * 当前用户所属组织
	 * @return
	 */
	public static Long getPkGroup() {
		return getCurrentUser().getPkGroup();
	}

	/**
	 * 当前用户所属组织
	 * @return
	 */
	public static Long getPkOrg() {
		return getCurrentUser().getPkOrg();
	}

	/**
	 * 当前登录用户用户名
	 * @return
	 */
	public static String getUsername() {
		return getCurrentUser().getBsCode();
	}

	/**
	 * 是否超级管理员：用户名为 "administrator"
	 * @return
	 */
	public static boolean isSupervisor() {
		if(StringUtils.equals(getUsername(), "administrator")) {
			return true;
		}
		return false;
	}
	/**
	 * 判断用户名是否超级管理员
	 * @param bsCode
	 * @return
	 */
	public static boolean isSupervisor(String username) {
		if(StringUtils.equals(username, "administrator")) {
			return true;
		}
		return false;
	}

}
