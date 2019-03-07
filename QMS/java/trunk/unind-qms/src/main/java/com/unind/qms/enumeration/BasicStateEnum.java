package com.unind.qms.enumeration;

/**
 * Basic包枚举类
 * @author chen
 *
 */
public enum BasicStateEnum {

	/**
	 * 未完成 0
	 */
	TODO_NOFINISH(BasicEnumConstants.TODO_NOFINISH),
	/**
	 * 已完成 1
	 */
	TODO_ISFINISH(BasicEnumConstants.TODO_ISFINISH),
	/**
	 * 已取消 2
	 */
	TODO_CANCEL(BasicEnumConstants.TODO_CANCEL),
	/**
	 * QMS 1
	 */
	TODO_QMSTYPE(BasicEnumConstants.TODO_QMSTYPE),
	/**
	 * 供应商审核 11
	 */
	TODO_SUPP(BasicEnumConstants.TODO_SUPP),
	/**
	 * 供应商风险管理 12
	 */
	TODO_SUPP_RISK(BasicEnumConstants.TODO_SUPP_RISK),
	/**
	 * 供应商体系审核 13
	 */
	TODO_SUPP_SETUP(BasicEnumConstants.TODO_SUPP_SETUP),
	/**
	 * 产品审核 21
	 */
	TODO_PR(BasicEnumConstants.TODO_PR),
	/**
	 * 产品成品检验 22
	 */
	TODO_PR_PRO(BasicEnumConstants.TODO_PR_PRO),
	/**
	 * 产品风险管理 23
	 */
	TODO_PR_RISK(BasicEnumConstants.TODO_PR_RISK),
	/**
	 * 出货检验报告核查 24
	 */
	TODO_SHIPMENT(BasicEnumConstants.TODO_SHIPMENT),
	/**
	 * 出货检验报告核查 25
	 */
	TODO_CREATEPRO(BasicEnumConstants.TODO_CREATEPRO),
	/**
	 * 客诉 31
	 */
	TODO_FEEDBACK(BasicEnumConstants.TODO_FEEDBACK),
	/**
	 * 待处理 1
	 */
	FEEDBACK_START(BasicEnumConstants.FEEDBACK_START),
	/**
	 * 处理中 2
	 */
	FEEDBACK_IN(BasicEnumConstants.FEEDBACK_IN),
	/**
	 * 已处理 3
	 */
	FEEDBACK_FINISH(BasicEnumConstants.FEEDBACK_FINISH),
	/**
	 * 处理人员 1
	 */
	FEEDBACK_HANDLER1(BasicEnumConstants.FEEDBACK_HANDLER1),
	/**
	 * 原因分析 2
	 */
	FEEDBACK_HANDLER2(BasicEnumConstants.FEEDBACK_HANDLER2),
	/**
	 * 临时措施 3
	 */
	FEEDBACK_HANDLER3(BasicEnumConstants.FEEDBACK_HANDLER3),
	/**
	 * 根本原因分析 4
	 */
	FEEDBACK_HANDLER4(BasicEnumConstants.FEEDBACK_HANDLER4),
	/**
	 * 选择的永久的纠正措施 5
	 */
	FEEDBACK_HANDLER5(BasicEnumConstants.FEEDBACK_HANDLER5),
	/**
	 * 执行的永久的纠正措施 6
	 */
	FEEDBACK_HANDLER6(BasicEnumConstants.FEEDBACK_HANDLER6),
	/**
	 * 预防行动 7
	 */
	FEEDBACK_HANDLER7(BasicEnumConstants.FEEDBACK_HANDLER7),
	/**
	 * 供应商审核（初评、体系）
	 */
	EXCELTEMP_SUPP(BasicEnumConstants.EXCELTEMP_SUPP),
	/**
	 * 产品过程审核
	 */
	EXCELTEMP_PRODUCT(BasicEnumConstants.EXCELTEMP_PRODUCT),
	/**
	 * 出货检验
	 */
	EXCELTEMP_SHIPMENT(BasicEnumConstants.EXCELTEMP_SHIPMENT),
	/**
	 * 客诉
	 */
	EXCELTEMP_FEEDBACL(BasicEnumConstants.EXCELTEMP_FEEDBACL),
	/**
	 * 退款
	 */
	EXCELTEMP_REFUND(BasicEnumConstants.EXCELTEMP_REFUND);

	private int value;
	BasicStateEnum(int value) {
		this.value = value;
	}

	public int intValue() {
		return this.value;
	}
}
