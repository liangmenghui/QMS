package com.unind.base.web.mrp.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 计划预测
 * @author Lenovo
 *
 */
public class MRPInfoVO implements Serializable {
	private static final long serialVersionUID = 9102217975996936523L;

	/**
	 * 当前日期
	 */
	protected Date date;
	/**
	 * 星期
	 */
	protected Integer day;
	/**
	 * 需求量
	 */
	protected Double numOfReq = 0.00d;
	/**
	 * 发货日期
	 */
	protected Date dateOfShipment;
	/**
	 * 柜号
	 */
	protected String containerNo;
	/**
	 * 待入库
	 */
	protected Double numOfShipment = 0.00d;
	/**
	 * 预计库存
	 */
	protected Double futureInv = 0.00d;
	/**
	 * 库存周数
	 */
	protected Double numOfWeek = 0.0d;

	protected transient MRPInfoVO prev;

	protected transient MRPInfoVO next;

	protected transient ItemInfoVO itemInfoVO;

	/**
	 * 最后一次已确定的发货时间
	 */
	protected Date lastDateOfShipment;

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Double getNumOfReq() {
		return numOfReq;
	}
	public void setNumOfReq(Double numOfReq) {
		this.numOfReq = numOfReq;
		/*if(null!=prev) {
			//预计库存=期初库存+预计入库-需求量
			double fi = new BigDecimal(prev.getFutureInv()).add(new BigDecimal(prev.getNumOfShipment())).subtract(new BigDecimal(this.getNumOfReq())).doubleValue();
			this.setFutureInv(fi);
			BigDecimal sumOfWeek = new BigDecimal(this.getNumOfReq());
			int days = 7;
			MRPInfoVO vo = next;
			while(days-1 > 0) {
				if(null==vo) {
					break;
				}
				sumOfWeek = sumOfWeek.add(new BigDecimal(vo.getNumOfReq()));
				vo = vo.getNext();
				days--;
			}
			double weeks = new BigDecimal(this.getFutureInv()).divide(sumOfWeek, 1, RoundingMode.HALF_UP).doubleValue();
			this.setNumOfWeek(weeks);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(format.format(date)+",预计库存："+fi+",库存周数："+weeks);
		}else {
			this.setFutureInv(itemInfoVO.getNumOfInv());
		}

		if(null!=next) {
			//预计库存=期初库存+预计入库-需求量
			double fi = new BigDecimal(this.getFutureInv()).add(new BigDecimal(this.getNumOfShipment())).subtract(new BigDecimal(next.getNumOfReq())).doubleValue();
			next.setFutureInv(fi);
			BigDecimal sumOfWeek = new BigDecimal(next.getNumOfReq());
			int days = 7;
			MRPInfoVO vo = next.getNext();
			while(days-1 > 0) {
				if(null==vo) {
					break;
				}
				sumOfWeek = sumOfWeek.add(new BigDecimal(vo.getNumOfReq()));
				vo = vo.getNext();
				days--;
			}
			double weeks = new BigDecimal(next.getFutureInv()).divide(sumOfWeek, 1, RoundingMode.HALF_UP).doubleValue();
			this.setNumOfWeek(weeks);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(this.getItemInfoVO().getItemNo()+":"+format.format(date)+",预计库存："+fi+",库存周数："+weeks);
		}*/
	}
	public Date getDateOfShipment() {
		return dateOfShipment;
	}
	public void setDateOfShipment(Date dateOfShipment) {
		this.dateOfShipment = dateOfShipment;
	}
	public String getContainerNo() {
		return containerNo;
	}
	public void setContainerNo(String containerNo) {
		this.containerNo = containerNo;
	}
	public Double getNumOfShipment() {
		return numOfShipment;
	}
	/**
	 * 设置待入库
	 * <p>排计划数量并自动根据运输周期计算发货日期，自动计算未来的计划数量保证安全库存</p>
	 * @param numOfShipment
	 */
	public void setNumOfShipment(Double numOfShipment) {
		this.numOfShipment = numOfShipment;

		if(null!=next) {
			//预计库存=期初库存+预计入库-需求量
			double fi = new BigDecimal(this.getFutureInv()).add(new BigDecimal(this.getNumOfShipment())).subtract(new BigDecimal(next.getNumOfReq())).doubleValue();
			next.setFutureInv(fi);
			//计算当天库存周数
			BigDecimal sumOfWeek = new BigDecimal(this.getNumOfReq());
			int days = 7;
			MRPInfoVO vo = this.getNext();
			while(days-1 > 0) {
				if(null==vo) {
					break;
				}
				sumOfWeek = sumOfWeek.add(new BigDecimal(vo.getNumOfReq()));
				vo = vo.getNext();
				days--;
			}
			double weeks = 0.0d;
			if(sumOfWeek.compareTo(new BigDecimal(0))==0) {
				weeks = this.getFutureInv().doubleValue() > 0.0d ? 999d:0.0d;
			}else {
				weeks = new BigDecimal(this.getFutureInv()).divide(sumOfWeek, 1, RoundingMode.HALF_UP).doubleValue();
				this.setNumOfWeek(weeks);
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(this.getItemInfoVO().getItemNo()+":"+format.format(this.getDate())+",预计库存："+this.getFutureInv()+",库存周数："+this.getNumOfWeek());
		}
	}
	public Double getFutureInv() {
		return futureInv;
	}
	public void setFutureInv(Double futureInv) {
		this.futureInv = futureInv;
		if(null!=next) {
			//预计库存=期初库存+预计入库-需求量
			double fi = new BigDecimal(this.getFutureInv()).add(new BigDecimal(this.getNumOfShipment())).subtract(new BigDecimal(next.getNumOfReq())).doubleValue();
//			System.out.println(this.getNumOfShipment()+" + "+this.getFutureInv()+" - " +next.getNumOfReq() + " = "+ fi);
			next.setFutureInv(fi);
			BigDecimal sumOfWeek = new BigDecimal(this.getNumOfReq());
			int days = 7;
			MRPInfoVO vo = this.getNext();
			while(days-1 > 0) {
				if(null==vo) {
					break;
				}
				sumOfWeek = sumOfWeek.add(new BigDecimal(vo.getNumOfReq()));
				vo = vo.getNext();
				days--;
			}
			double weeks = 0.0d;
			if(sumOfWeek.compareTo(new BigDecimal(0))==0) {
				weeks = this.getFutureInv().doubleValue() > 0.0d ? 999d:0.0d;
			}else {
				weeks = new BigDecimal(this.getFutureInv()).divide(sumOfWeek, 1, RoundingMode.HALF_UP).doubleValue();
			}
			this.setNumOfWeek(weeks);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(this.getItemInfoVO().getItemNo()+":"+format.format(this.getDate())+",预计库存："+this.getFutureInv()+",库存周数："+this.getNumOfWeek());
		}

		//计算最后一次发货时间
		if(this.getNumOfShipment().doubleValue() > 0.0d) {
			lastDateOfShipment = this.getDateOfShipment();
		}
	}
	public Double getNumOfWeek() {
		return numOfWeek;
	}
	public void setNumOfWeek(Double numOfWeek) {
		this.numOfWeek = numOfWeek;
	}
	public MRPInfoVO getPrev() {
		return prev;
	}
	public void setPrev(MRPInfoVO prev) {
		this.prev = prev;
	}
	public MRPInfoVO getNext() {
		return next;
	}
	public void setNext(MRPInfoVO next) {
		this.next = next;
	}
	public ItemInfoVO getItemInfoVO() {
		return itemInfoVO;
	}
	public void setItemInfoVO(ItemInfoVO itemInfoVO) {
		this.itemInfoVO = itemInfoVO;
	}
	public Date getLastDateOfShipment() {
		return lastDateOfShipment;
	}
	public void setLastDateOfShipment(Date lastDateOfShipment) {
		this.lastDateOfShipment = lastDateOfShipment;
	}

}
