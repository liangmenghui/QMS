package com.unind.base.domain.admin.enumeration;

/**
 * 基础（枚举）常量类
 * @author Administrator
 *
 */
public class BaseEnumConstants {

	/**
	 * Boolean类型枚举值<br/>
	 * 否
	 * @see BooleanStateEnum
	 */
	public static final int FALSE = 0;
	/**
	 * Boolean类型枚举值<br/>
	 * 是
	 * @see BooleanStateEnum
	 */
	public static final int TRUE = 1;



	/**
	 * 启用状态枚举值<br/>
	 * 启用
	 * @see EnableStateEnum
	 */
	public static final int ENABLED = 1;
	/**
	 * 启用状态枚举值<br/>
	 * 停用
	 * @see EnableStateEnum
	 */
	public static final int DISABLED = 2;


	/**
	 * 帐号 - 启用状态枚举值<br/>
	 * 启用
	 * @see UserStatusEnum
	 */
	public static final int USER_ENABLED = 1;
	/**
	 * 帐号 - 停用状态枚举值<br/>
	 * 停用
	 * @see UserStatusEnum
	 */
	public static final int USER_DISABLED = 2;

	/**
	 * 帐号 - 锁定状态枚举值<br/>
	 * 锁定
	 * @see UserStatusEnum
	 */
	public static final int USER_LOCKED = 3;
}
