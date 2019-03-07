package com.unind.base.web.admin;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.domain.admin.group.SysGroup;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.admin.group.SysGroupService;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;

/**
 * 角色管理
 * @author tanxiang
 *
 */
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/group")
public class SysGroupController extends WebController {
	@Autowired
	private SysGroupService sysGroupService;

	@Autowired
	DataSource dataSource;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ApiResponseResult add(SysGroup sysGroup) {
		try {
			return sysGroupService.add(sysGroup);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ApiResponseResult edit(SysGroup sysGroup) {
		try {
			return sysGroupService.edit(sysGroup);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ApiResponseResult delete(SysGroup sysGroup) {
		try {
			if(null!=sysGroup.getId() && sysGroup.getId()==-1) {
				return ApiResponseResult.failure("没有删除权限");
			}
			return sysGroupService.delete(sysGroup);
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	@RequestMapping(value = "/getlist")
	public ApiResponseResult getlist(SysGroup sysGroup) {
		try {
			return sysGroupService.getlist(sysGroup, super.getPageRequest());
		} catch (BusinessException e) {
			return ApiResponseResult.failure(e.getMessage());
		}
	}

}
