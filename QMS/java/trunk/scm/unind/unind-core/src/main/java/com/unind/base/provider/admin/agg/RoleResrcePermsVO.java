package com.unind.base.provider.admin.agg;

import java.util.List;

public class RoleResrcePermsVO {

	/**
	 * 角色id
	 */
	private Long roleId;

	/**
	 * 资源id列表
	 */
	private List<ResrcePermsOptionVO> options;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public List<ResrcePermsOptionVO> getOptions() {
		return options;
	}

	public void setOptions(List<ResrcePermsOptionVO> options) {
		this.options = options;
	}

}
