package com.unind.base.web.mrp.domain;

import java.io.Serializable;
import java.util.Date;

public class ShipmentDateScopeVO implements Serializable {
	private static final long serialVersionUID = -4641626354744611000L;

	protected Date date;
	protected Date begin;
	protected Date end;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getBegin() {
		return begin;
	}
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}

}
