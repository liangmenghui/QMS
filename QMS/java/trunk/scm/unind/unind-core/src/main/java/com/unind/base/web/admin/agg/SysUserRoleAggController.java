package com.unind.base.web.admin.agg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.admin.agg.SysUserRolesAggService;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;

/**
 * 用户角色
 * @author tanxiang
 *
 */
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/userrole")
public class SysUserRoleAggController extends WebController {
	@Autowired
	private SysUserRolesAggService sysUserRolesAggService;

	/**
	 * 保存
	 * @param pkUser
	 * @param pkRoles
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ApiResponseResult save(Long pkUser, Long[] pkRoles) {
		try {
			return sysUserRolesAggService.saveUserRoleAgg(pkUser, pkRoles);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	/**
	 * 获取用户角色列表
	 * @param pkUser
	 * @return
	 */
	@RequestMapping(value = "/getlist")
	public ApiResponseResult getlist(long pkUser) {
		return sysUserRolesAggService.getlist(pkUser);
	}

	/**
	 * 查询角色下的用户列表
	 * @param pkParent	所属角色id
	 * @param bsCode	用户账号
	 * @param bsName	用户名称
	 * @return
	 */
	@RequestMapping(value = "/getuserlist")
	public ApiResponseResult getlist(Long pkParent, String bsCode, String bsName) {
		return sysUserRolesAggService.getuserlist(pkParent, bsCode, bsName, super.getPageRequest());
	}
}
