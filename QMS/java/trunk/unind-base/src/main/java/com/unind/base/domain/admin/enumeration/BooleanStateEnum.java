package com.unind.base.domain.admin.enumeration;

/**
 * Boolean值枚举类
 * @author tanxiang
 *
 */
public enum BooleanStateEnum {

	/**
	 * 否 0
	 */
	FALSE(BaseEnumConstants.FALSE),
	/**
	 * 是 1
	 */
	TRUE(BaseEnumConstants.TRUE);

	private int value;
	BooleanStateEnum(int value) {
		this.value = value;
	}

	public int intValue() {
		return this.value;
	}
}
