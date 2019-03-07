package com.unind.base.web.mrp.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * 计划
 * @author Lenovo
 *
 */
public class ShipmentPlanVO implements Serializable {
	private static final long serialVersionUID = 462010474780323296L;

	/**
	 * 发货日期
	 */
	protected Date dateOfShipment;
	/**
	 * 实际发货数量
	 */
	protected Double numOfShipment;
	/**
	 * 最小发货数量
	 */
	protected Double minNumOfShipment;
	/**
	 * 最小发货数量的安全库存周数
	 */
	protected Double minNumOfWeek;
	/**
	 * 最大发货数量
	 */
	protected Double maxNumOfShipment;
	/**
	 * 最大发货数量的安全库存周数
	 */
	protected Double maxNumOfWeek;

	protected transient MRPInfoVO mrpInfoVO;
	protected transient ShipmentPlanVO first;
	protected transient ShipmentPlanVO prev;
	protected transient ShipmentPlanVO next;
	protected transient ShipmentPlanVO last;

	public Date getDateOfShipment() {
		return dateOfShipment;
	}
	public void setDateOfShipment(Date dateOfShipment) {
		this.dateOfShipment = dateOfShipment;
	}
	public Double getNumOfShipment() {
		return numOfShipment;
	}
	public void setNumOfShipment(Double numOfShipment) {
		this.numOfShipment = numOfShipment;
	}
	public Double getMinNumOfShipment() {
		return minNumOfShipment;
	}
	public void setMinNumOfShipment(Double minNumOfShipment) {
		this.minNumOfShipment = minNumOfShipment;
	}
	public Double getMinNumOfWeek() {
		return minNumOfWeek;
	}
	public void setMinNumOfWeek(Double minNumOfWeek) {
		this.minNumOfWeek = minNumOfWeek;
	}
	public Double getMaxNumOfShipment() {
		return maxNumOfShipment;
	}
	public void setMaxNumOfShipment(Double maxNumOfShipment) {
		this.maxNumOfShipment = maxNumOfShipment;
	}
	public Double getMaxNumOfWeek() {
		return maxNumOfWeek;
	}
	public void setMaxNumOfWeek(Double maxNumOfWeek) {
		this.maxNumOfWeek = maxNumOfWeek;
	}
	public MRPInfoVO getMrpInfoVO() {
		return mrpInfoVO;
	}
	public void setMrpInfoVO(MRPInfoVO mrpInfoVO) {
		this.mrpInfoVO = mrpInfoVO;
	}
	public ShipmentPlanVO getPrev() {
		return prev;
	}
	public void setPrev(ShipmentPlanVO prev) {
		this.prev = prev;
	}
	public ShipmentPlanVO getNext() {
		return next;
	}
	public void setNext(ShipmentPlanVO next) {
		this.next = next;
	}

	public ShipmentPlanVO(MRPInfoVO mrpInfoVO) {
		super();
		this.mrpInfoVO = mrpInfoVO;
	}

	public ShipmentPlanVO copy() {
		ShipmentPlanVO shipmentPlanVO = this;
		first = this;
		ShipmentPlanVO lastShipmentPlanVO = new ShipmentPlanVO(null);
		while(null != shipmentPlanVO.getMrpInfoVO()) {
			shipmentPlanVO.next = new ShipmentPlanVO(shipmentPlanVO.getMrpInfoVO().getNext());
			shipmentPlanVO.next.prev = shipmentPlanVO;
			shipmentPlanVO.next.first = this.first;
//			shipmentPlanVO.next.last = lastShipmentPlanVO;
			shipmentPlanVO = shipmentPlanVO.next;
		}
		lastShipmentPlanVO.setMrpInfoVO(this.getMrpInfoVO());
		return this;
	}

	public void calc() {
		Date begin = null, end = null;
		Date begin1 = null, end1 = null;
		//发货提前期(天)
		int PERIOD_SAFETY = 42;
		//安全库存周数连续7天在安全库存之内或小于安全库存周数则为需求日期
		int days = 7;
		Calendar calendar = Calendar.getInstance();
		ShipmentPlanVO shipmentPlanVO = first.getNext();
		while(null != shipmentPlanVO) {
			if(shipmentPlanVO.getMrpInfoVO().getNumOfShipment() > 0) {
				int interval = 7;
				while(interval >= 0) {
					if(null != shipmentPlanVO) {
						shipmentPlanVO = shipmentPlanVO.getNext();
					}else {
						break;
					}
					interval--;
				}
			}else if(shipmentPlanVO.getMrpInfoVO().getNumOfShipment() == 0
					&& shipmentPlanVO.getMrpInfoVO().getNumOfWeek().doubleValue() <= 5d
					&& shipmentPlanVO.getMrpInfoVO().getNumOfWeek().doubleValue() >= 2.5d) {
				if(null == begin || days == 7) {
					//安全库存周数<=5周时，推算发货日期
					calendar.setTime(shipmentPlanVO.getMrpInfoVO().getDate());
					calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - PERIOD_SAFETY);
					begin = calendar.getTime();
					begin1 = shipmentPlanVO.getMrpInfoVO().getDate();
				}
				days--;
			}else if(shipmentPlanVO.getMrpInfoVO().getNumOfShipment() == 0
				&& shipmentPlanVO.getMrpInfoVO().getNumOfWeek().doubleValue() < 2.5d) {
				calendar.setTime(shipmentPlanVO.getMrpInfoVO().getDate());
				calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - PERIOD_SAFETY);
//				shipmentPlanVO.setEnd(calendar.getTime());
				if(null == begin || days == 7) {
					calendar.setTime(shipmentPlanVO.getMrpInfoVO().getPrev().getDate());
					calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - PERIOD_SAFETY);
					begin = calendar.getTime();
					begin1 = shipmentPlanVO.getMrpInfoVO().getPrev().getDate();
				}
				end = calendar.getTime();
				end1 = shipmentPlanVO.getMrpInfoVO().getDate();
				days--;
				break;
			}else {
				begin = null;
				begin1 = null;
				end = null;
				end1 = null;
				days = 7;
			}
			
			/*if(this.minNumOfWeek <= 2.5d && prev.getNumOfShipment().doubleValue() == 0) {
				//安全库存低于2.5周, 按照运输期、提前期等计划发货;计算公式：预计库存周数 =（发货数量+当前库存）/（最近7天客户需求量之和（含当天））
				//最小/最大发货数量 = 最小/最大预计库存周数  * （最近7天客户需求量之和（含当天））- 当前库存
				double minNumOfWeek = 2.5d;
				double maxNumOfWeek = 5d;
				BigDecimal sumOfWeek = new BigDecimal(mrpInfoVO.getPrev().getNumOfReq());
				int days = 7;
				MRPInfoVO vo = mrpInfoVO.getNext();
				while(days-1 > 0) {
					if(null==vo) {
						break;
					}
					sumOfWeek = sumOfWeek.add(new BigDecimal(vo.getNumOfReq()));
					vo = vo.getNext();
					days--;
				}
				double minNumOfShipment = new BigDecimal(minNumOfWeek).multiply(sumOfWeek).subtract(new BigDecimal(mrpInfoVO.getPrev().getFutureInv())).doubleValue();
				double maxNumOfShipment = new BigDecimal(maxNumOfWeek).multiply(sumOfWeek).subtract(new BigDecimal(mrpInfoVO.getPrev().getFutureInv())).doubleValue();
	
				prev.setNumOfShipment(minNumOfShipment);
	//			prev.setNumOfShipment(maxNumOfShipment);
			}*/
			shipmentPlanVO = shipmentPlanVO.getNext();
		}
		System.out.println(begin1 + "~" + end1);
	}
}
