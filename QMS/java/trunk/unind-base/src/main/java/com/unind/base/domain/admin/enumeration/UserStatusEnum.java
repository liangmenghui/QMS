package com.unind.base.domain.admin.enumeration;

/**
 * 帐号状态
 * @author tanxiang
 *
 */
public enum UserStatusEnum {
	/**
	 * 启用
	 */
	USER_ENABLED(BaseEnumConstants.USER_ENABLED),
	/**
	 * 停用
	 */
	USER_DISABLED(BaseEnumConstants.USER_DISABLED),
	/**
	 * 锁定
	 */
	USER_LOCKED(BaseEnumConstants.USER_LOCKED);

	private int value;
	UserStatusEnum(int value) {
		this.value = value;
	}

	public int intValue() {
		return this.value;
	}
}
