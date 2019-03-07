package com.unind.base.web.admin.agg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BusinessException;
import com.unind.base.provider.admin.agg.ResrcePermsOptionVO;
import com.unind.base.provider.admin.agg.RoleResrcePermsVO;
import com.unind.base.provider.admin.agg.SysRolePermsAggService;
import com.unind.base.web.Constants;
import com.unind.base.web.WebController;

/**
 * 角色权限
 * @author tanxiang
 *
 */
@RestController
@RequestMapping(value=Constants.CONTEXT_PATH + "/roleperms")
public class SysRolePermsAggController extends WebController {
	@Autowired
	private SysRolePermsAggService sysRolePermsAggService;

	/**
	 * 保存
	 * @param rrpvo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ApiResponseResult save(Long roleId, String rrpvo) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<Map<String, Object>> list = null;
			try {
				list = mapper.readValue(rrpvo, ArrayList.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Map<Long, List<Long>> uniqueMap = new HashMap<Long, List<Long>>();
			List<Long> pkPerms;
			for (Object object : list) {
				Map<String, Object> optionMap = (Map<String, Object>)object;
				if(null==optionMap.get("pkResrce") || StringUtils.isEmpty(String.valueOf(optionMap.get("pkResrce")))) {
					continue;
				}
				Long pkResrce = Long.valueOf(String.valueOf(optionMap.get("pkResrce")));
				if(!uniqueMap.containsKey(pkResrce)) {
					pkPerms = new ArrayList<Long>();
				}else {
					pkPerms = uniqueMap.get(pkResrce);
				}
				uniqueMap.put(pkResrce, pkPerms);
				if(null==optionMap.get("pkPerms") || StringUtils.isEmpty(String.valueOf(optionMap.get("pkPerms")))) {
					continue;
				}
				String[] perms = String.valueOf(optionMap.get("pkPerms")).split(",");
				for (String perm : perms) {
					if(StringUtils.isEmpty(perm)) {
						continue;
					}
					pkPerms.add(Long.parseLong(perm.trim()));
				}
			}
			List<ResrcePermsOptionVO> options = new ArrayList<ResrcePermsOptionVO>();
			ResrcePermsOptionVO option;
			for(Entry<Long, List<Long>> entry: uniqueMap.entrySet()) {
				option = new ResrcePermsOptionVO();
				option.setResrceId(entry.getKey());
				option.setPermIds(entry.getValue().toArray(new Long[]{}));
				options.add(option);
			}
			RoleResrcePermsVO vo = new RoleResrcePermsVO();
			vo.setRoleId(roleId);
			vo.setOptions(options);
			return sysRolePermsAggService.save(vo);
		} catch (BusinessException e) {
			e.printStackTrace();
			return ApiResponseResult.failure(e.getMessage());
		}
	}

	/**
	 * 获取角色权限列表
	 * @param sysGroup
	 * @return
	 */
	@RequestMapping(value = "/getlist")
	public ApiResponseResult getlist(Long pkRole) {
		return sysRolePermsAggService.getlist(pkRole);
	}
}
