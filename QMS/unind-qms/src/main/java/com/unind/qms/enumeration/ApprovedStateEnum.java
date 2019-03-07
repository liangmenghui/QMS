package com.unind.qms.enumeration;

/**
 * 审批流枚举类
 * @author chen
 *
 */
public enum ApprovedStateEnum {

	/**
	 * 产品过程审核 11
	 */
	PR_APPROVED(ApprovedEnumConstants.PR_APPROVED),
	/**
	 * 成品检验 12
	 */
	PRED_APPROVED(ApprovedEnumConstants.PRED_APPROVED),
	/**
	 * 供应商体系审核 21
	 */
	SUPP_APPROVED(ApprovedEnumConstants.SUPP_APPROVED),
	/**
	 * 供应商资格审核 22
	 */
	SUPPQUAL_APPROVED(ApprovedEnumConstants.SUPPQUAL_APPROVED),
	/**
	 * 进行中 0
	 */
	FLOW_IN(ApprovedEnumConstants.FLOW_IN),
	/**
	 * 待确定 1
	 */
	FLOW_CONFIRM(ApprovedEnumConstants.FLOW_CONFIRM),
	/**
	 * 关闭 2
	 */
	FLOW_CLOSE(ApprovedEnumConstants.FLOW_CLOSE),
	/**
	 * 进行中 0
	 */
	RESULT_IN(ApprovedEnumConstants.RESULT_IN),
	/**
	 * 通过 1
	 */
	RESULT_PASS(ApprovedEnumConstants.RESULT_PASS),
	/**
	 * 未通过 2
	 */
	RESULT_NOPASS(ApprovedEnumConstants.RESULT_NOPASS),
	/**
	 * 关闭 3
	 */
	RESULT_CLOSE(ApprovedEnumConstants.RESULT_CLOSE),
	/**
	 * 未开始 0
	 */
	ITEMS_START(ApprovedEnumConstants.ITEMS_START),
	/**
	 * 进行中 1
	 */
	ITEMS_IN(ApprovedEnumConstants.ITEMS_IN),
	/**
	 * 通过 2
	 */
	ITEMS_PASS(ApprovedEnumConstants.ITEMS_PASS),
	/**
	 * 未通过 3
	 */
	ITEMS_NOPASS(ApprovedEnumConstants.ITEMS_NOPASS),
	/**
	 * 未通过 4
	 */
	ITEMS_CLOSE(ApprovedEnumConstants.ITEMS_CLOSE),
	/**
	 * 通过 1
	 */
	ITEMS_RESULT_PASS(ApprovedEnumConstants.ITEMS_RESULT_PASS),
	/**
	 * 驳回 2
	 */
	ITEMS_RESULT_BACK(ApprovedEnumConstants.ITEMS_RESULT_BACK),
	/**
	 * 不通过 3
	 */
	ITEMS_RESULT_NOPASS(ApprovedEnumConstants.ITEMS_RESULT_NOPASS),
	/**
	 * 未审批 0
	 */
	NOT_APPROVE(ApprovedEnumConstants.NOT_APPROVE),
	/**
	 * 审批中 1
	 */
	IS_APPROVE(ApprovedEnumConstants.IS_APPROVE);

	private int value;
	ApprovedStateEnum(int value) {
		this.value = value;
	}

	public int intValue() {
		return this.value;
	}
}
