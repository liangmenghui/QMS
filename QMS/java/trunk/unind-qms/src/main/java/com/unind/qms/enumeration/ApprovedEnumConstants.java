package com.unind.qms.enumeration;

/**
 * 审批流（枚举）常量类
 * @author chen
 *
 */
public class ApprovedEnumConstants {

	/**
	 * 审批类型 - 产品过程审核类型枚举值<br/>
	 * 产品过程审核
	 * @see ApprovedStateEnum
	 */
	public static final int PR_APPROVED = 11;
	/**
	 * 审批类型 - 成品检验类型枚举值<br/>
	 * 成品检验
	 * @see ApprovedStateEnum
	 */
	public static final int PRED_APPROVED = 12;
	/**
	 * 审批类型 - 供应商体系审核类型枚举值<br/>
	 * 供应商体系审核
	 * @see ApprovedStateEnum
	 */
	public static final int SUPP_APPROVED = 21;
	/**
	 * 审批类型 - 供应商资格审核类型枚举值<br/>
	 * 供应商资格审核
	 * @see ApprovedStateEnum
	 */
	public static final int SUPPQUAL_APPROVED = 22;

	/**
	 * 审批流程状态 - 进行中状态枚举值<br/>
	 * 进行中
	 * @see ApprovedStateEnum
	 */
	public static final int FLOW_IN = 0;
	/**
	 * 审批流程状态 - 待确认状态枚举值<br/>
	 * 待确认
	 * @see ApprovedStateEnum
	 */
	public static final int FLOW_CONFIRM = 1;
	/**
	 * 审批流程状态 - 关闭状态枚举值<br/>
	 * 关闭
	 * @see ApprovedStateEnum
	 */
	public static final int FLOW_CLOSE = 2;

	/**
	 * 审批流程结果 - 进行中结果枚举值<br/>
	 * 进行中
	 * @see ApprovedStateEnum
	 */
	public static final int RESULT_IN = 0;
	/**
	 * 审批流程结果 - 通过结果枚举值<br/>
	 * 通过
	 * @see ApprovedStateEnum
	 */
	public static final int RESULT_PASS = 1;
	/**
	 * 审批流程结果 - 未通过结果枚举值<br/>
	 * 未通过
	 * @see ApprovedStateEnum
	 */
	public static final int RESULT_NOPASS = 2;
	/**
	 * 审批流程结果 - 关闭结果枚举值<br/>
	 * 关闭
	 * @see ApprovedStateEnum
	 */
	public static final int RESULT_CLOSE = 3;

	/**
	 * 审批项目状态 - 未开始状态枚举值<br/>
	 * 未开始
	 * @see ApprovedStateEnum
	 */
	public static final int ITEMS_START = 0;
	/**
	 * 审批项目状态 - 进行中状态枚举值<br/>
	 * 进行中
	 * @see ApprovedStateEnum
	 */
	public static final int ITEMS_IN = 1;
	/**
	 * 审批项目状态 - 通过状态枚举值<br/>
	 * 通过
	 * @see ApprovedStateEnum
	 */
	public static final int ITEMS_PASS = 2;
	/**
	 * 审批项目状态 - 未通过状态枚举值<br/>
	 * 未通过
	 * @see ApprovedStateEnum
	 */
	public static final int ITEMS_NOPASS = 3;
	/**
	 * 审批项目状态 - 关闭状态枚举值<br/>
	 * 关闭
	 * @see ApprovedStateEnum
	 */
	public static final int ITEMS_CLOSE = 4;

	/**
	 * 审批项目结果状态 - 通过状态枚举值<br/>
	 * 通过
	 * @see ApprovedStateEnum
	 */
	public static final int ITEMS_RESULT_PASS = 1;
	/**
	 * 审批项目结果状态 - 驳回状态枚举值<br/>
	 * 驳回
	 * @see ApprovedStateEnum
	 */
	public static final int ITEMS_RESULT_BACK = 2;
	/**
	 * 审批项目结果状态 - 不通过状态枚举值<br/>
	 * 不通过
	 * @see ApprovedStateEnum
	 */
	public static final int ITEMS_RESULT_NOPASS = 3;

	/**
	 * 审批项目是否审批中 - 否状态枚举值<br/>
	 * 否
	 * @see ApprovedStateEnum
	 */
	public static final int NOT_APPROVE = 0;
	/**
	 * 审批项目是否审批中 - 是状态枚举值<br/>
	 * 是
	 * @see ApprovedStateEnum
	 */
	public static final int IS_APPROVE = 1;
	/**
	 * 审批结论（供应商审核） - 禁用结论枚举值<br/>
	 * 禁用
	 * @see ApprovedStateEnum
	 */
	public static final int CONCLUSION_SUPP_DISABLE = 1;
	/**
	 * 审批结论（供应商审核） - 潜在结论枚举值<br/>
	 * 潜在
	 * @see ApprovedStateEnum
	 */
	public static final int CONCLUSION_SUPP_POTENTIAL = 2;
	/**
	 * 审批结论（供应商审核） - 暂准结论枚举值<br/>
	 * 暂准
	 * @see ApprovedStateEnum
	 */
	public static final int CONCLUSION_SUPP_ALLOW = 3;
	/**
	 * 审批结论（过程审核） - 合格结论枚举值<br/>
	 * 合格
	 * @see ApprovedStateEnum
	 */
	public static final int CONCLUSION_PROCESS_YES = 4;
	/**
	 * 审批结论（过程审核） - 不合格结论枚举值<br/>
	 * 不合格
	 * @see ApprovedStateEnum
	 */
	public static final int CONCLUSION_PROCESS_NO = 5;
	/**
	 * 审批结论（成品检验） - 可以接受结论枚举值<br/>
	 * 可以接受
	 * @see ApprovedStateEnum
	 */
	public static final int CONCLUSION_PRODUCT_ACCEPT = 6;
	/**
	 * 审批结论（成品检验） - 不可接受结论枚举值<br/>
	 * 不可接受
	 * @see ApprovedStateEnum
	 */
	public static final int CONCLUSION_PRODUCT_UNACCEPT = 7;
	/**
	 * 审批结论（成品检验） - 让步结论枚举值<br/>
	 * 让步
	 * @see ApprovedStateEnum
	 */
	public static final int CONCLUSION_PRODUCT_REFERENCE = 8;

}
