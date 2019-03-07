package com.unind.base.web.admin;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.org.SysOrg;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.admin.org.SysOrgService;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;

/**
 * 组织管理
 * @author tanxiang
 *
 */
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/org")
public class SysOrgController extends WebController {
	@Autowired
	private SysOrgService sysOrgService;

	@Autowired
	DataSource dataSource;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(SysOrg sysOrg) {
		try {
			return sysOrgService.add(sysOrg);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ApiResponseResult edit(SysOrg sysOrg) {
		try {
			return sysOrgService.edit(sysOrg);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApiResponseResult delete(SysOrg sysOrg) {
		try {
			if(null!=sysOrg.getId() && sysOrg.getId()==-1) {
				return ApiResponseResult.failure("没有删除权限");
			}
			return sysOrgService.delete(sysOrg);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@RequestMapping(value = "/getlist")
	public ApiResponseResult getlist(SysOrg sysOrg) {
		try {
			return sysOrgService.getlist(sysOrg, super.getPageRequest());
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

}
