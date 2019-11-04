package edu.etime.yqxdc.dto;

import java.math.BigDecimal;

public class DetailDto {
	private String orderid;

	private String cusid;

	private String ordertime;

	private Integer orderstate;

	private BigDecimal ordertotleprice;
	private String odid;

	private String gid;

	private BigDecimal odprice;

	private BigDecimal odcount;
	private String gname;

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getCusid() {
		return cusid;
	}

	public void setCusid(String cusid) {
		this.cusid = cusid;
	}

	public String getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}

	public Integer getOrderstate() {
		return orderstate;
	}

	public void setOrderstate(Integer orderstate) {
		this.orderstate = orderstate;
	}

	public BigDecimal getOrdertotleprice() {
		return ordertotleprice;
	}

	public void setOrdertotleprice(BigDecimal ordertotleprice) {
		this.ordertotleprice = ordertotleprice;
	}

	public String getOdid() {
		return odid;
	}

	public void setOdid(String odid) {
		this.odid = odid;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public BigDecimal getOdprice() {
		return odprice;
	}

	public void setOdprice(BigDecimal odprice) {
		this.odprice = odprice;
	}

	public BigDecimal getOdcount() {
		return odcount;
	}

	public void setOdcount(BigDecimal odcount) {
		this.odcount = odcount;
	}
}
