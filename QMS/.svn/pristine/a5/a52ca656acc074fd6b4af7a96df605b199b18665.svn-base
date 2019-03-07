package com.unind.base.web.mrp.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 物料信息VO
 * @author tanxiang
 *
 */
public class ItemInfoVO implements Serializable {
	private static final long serialVersionUID = 6067232118972046837L;

	/**
	 * 物料编号
	 */
	protected String itemNo;
	/**
	 * 物料描述
	 */
	protected String comment;
	/**
	 * 客户部件号
	 */
	protected String partNo;
	/**
	 * 最小发运
	 */
	protected Double minShipment;
	/**
	 * 生产周期
	 */
	protected Integer periodOfProd;
	/**
	 * 运输周期
	 */
	protected Integer periodOfDelivery;
	/**
	 * 当前库存
	 */
	protected Double numOfInv;
	/**
	 * 未接收PO
	 */
	protected Double numOfNotRecivedPO;
	/**
	 * 预期未入库
	 */
	protected Double outOfDateInv;

	protected List<MRPInfoVO> mrpInfoVOList;

	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getPartNo() {
		return partNo;
	}
	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}
	public Double getMinShipment() {
		return minShipment;
	}
	public void setMinShipment(Double minShipment) {
		this.minShipment = minShipment;
	}
	public Integer getPeriodOfProd() {
		return periodOfProd;
	}
	public void setPeriodOfProd(Integer periodOfProd) {
		this.periodOfProd = periodOfProd;
	}
	public Integer getPeriodOfDelivery() {
		return periodOfDelivery;
	}
	public void setPeriodOfDelivery(Integer periodOfDelivery) {
		this.periodOfDelivery = periodOfDelivery;
	}
	public Double getNumOfInv() {
		return numOfInv;
	}
	public void setNumOfInv(Double numOfInv) {
		this.numOfInv = numOfInv;
	}
	public Double getNumOfNotRecivedPO() {
		return numOfNotRecivedPO;
	}
	public void setNumOfNotRecivedPO(Double numOfNotRecivedPO) {
		this.numOfNotRecivedPO = numOfNotRecivedPO;
	}
	public Double getOutOfDateInv() {
		return outOfDateInv;
	}
	public void setOutOfDateInv(Double outOfDateInv) {
		this.outOfDateInv = outOfDateInv;
	}
	public List<MRPInfoVO> getMrpInfoVOList() {
		return mrpInfoVOList;
	}
	public void setMrpInfoVOList(List<MRPInfoVO> mrpInfoVOList) {
		this.mrpInfoVOList = mrpInfoVOList;
	}


	/**
	 * 初始化计算库存周期
	 */
	public void init() {
		if(null == mrpInfoVOList || mrpInfoVOList.isEmpty()) {
			return;
		}
		mrpInfoVOList.get(0).setNumOfShipment(mrpInfoVOList.get(0).getNumOfShipment());
	}
}
