package com.unind.base.web.admin.agg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.admin.agg.SysUserRolesAggService;
import com.unind.base.provider.context.SessionContextUtils;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;

/**
 * 用户资源
 * @author tanxiang
 *
 */
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/userresrce")
public class SysUserResrceAggController extends WebController {
	@Autowired
	private SysUserRolesAggService sysUserRolesAggService;

	/**
	 * 获取用户拥有的资源权限列表
	 * @param sysGroup
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getlist")
	public ApiResponseResult getlist(Long pkSysUser) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(SessionContextUtils.isSupervisor()) {
			//超级管理员
			list = sysUserRolesAggService.findSuperAdminResrcePermList();
		}else {
			pkSysUser = SessionContextUtils.getCurrentUser().getId();
			list = sysUserRolesAggService.findUserResrcePermList(pkSysUser);
		}

		Map<Long, Map<String, Object>> uniqueMap = new HashMap<Long, Map<String, Object>>();
		Map<String, Object> resrceMap;
		Map<String, String> permMap;
		for (Map<String, Object> map : list) {
			long pkResrce = Long.parseLong(String.valueOf(map.get("resrceId")));
			String permCode = String.valueOf(map.get("permCode"));
			if(uniqueMap.containsKey(pkResrce)) {
				resrceMap = uniqueMap.get(pkResrce);
				permMap = (Map<String, String>) resrceMap.get("perms");
				if(StringUtils.isNotEmpty(permCode)) {
					permMap.put(permCode, permCode);
				}
			}else {
				resrceMap = map;
				permMap = new HashMap<String, String>();
				if(StringUtils.isNotEmpty(permCode)) {
					permMap.put(permCode, permCode);
				}
				resrceMap.put("perms", permMap);
				uniqueMap.put(pkResrce, resrceMap);
			}
		}
		List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
		for(Entry<Long, Map<String, Object>> entry: uniqueMap.entrySet()) {
			resList.add(entry.getValue());
		}
		List<Map<String, Object>> res = wrap(-1, resList);
		return ApiResponseResult.success().data(res);
	}

	protected List<Map<String, Object>> wrap(long id, List<Map<String, Object>> list){
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			long pkParent = Long.parseLong(String.valueOf(map.get("pkParent")));
			if(pkParent==id) {
				List<Map<String, Object>> children = wrap(Long.parseLong(String.valueOf(map.get("resrceId"))), list);
				List<String> perms = new ArrayList<String>();
				if(children.size() > 0) {
					map.put("children", children);
					for (Map<String, Object> m : children) {
						perms.add(String.valueOf(m.get("permCode")));
					}
					map.put("perms", perms);
				}
				res.add(map);
			}
			continue;
		}
		return res;
	}
}
