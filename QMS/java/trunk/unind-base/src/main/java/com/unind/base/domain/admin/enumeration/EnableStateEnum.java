package com.unind.base.domain.admin.enumeration;

/**
 * 启用停用枚举类
 * @author tanxiang
 *
 */
public enum EnableStateEnum {

	/**
	 * 启用
	 */
	ENABLED(BaseEnumConstants.ENABLED),
	/**
	 * 停用
	 */
	DISABLED(BaseEnumConstants.DISABLED);

	private int value;
	EnableStateEnum(int value) {
		this.value = value;
	}

	public int intValue() {
		return this.value;
	}
}
