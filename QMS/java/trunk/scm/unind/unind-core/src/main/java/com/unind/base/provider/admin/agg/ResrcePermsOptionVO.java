package com.unind.base.provider.admin.agg;

public class ResrcePermsOptionVO {

	/**
	 * 资源id
	 */
	private Long resrceId;

	/**
	 * 权限id 列表
	 */
	private Long[] permIds;

	public Long getResrceId() {
		return resrceId;
	}

	public void setResrceId(Long resrceId) {
		this.resrceId = resrceId;
	}

	public Long[] getPermIds() {
		return permIds;
	}

	public void setPermIds(Long[] permIds) {
		this.permIds = permIds;
	}

}
