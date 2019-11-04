package edu.learn.bms.projo;

import java.util.Date;

public class Purchase {

	private String purid;
	private String userid;
	private Date purdate;
	private Integer purstate;
	
	
	public Purchase(Integer purstate) {
		super();
		this.purstate = purstate;
	}
	public Purchase() {
		super();
	}
	
	public Purchase(String purid, String userid, Date purdate, Integer purstate) {
		super();
		this.purid = purid;
		this.userid = userid;
		this.purdate = purdate;
		this.purstate = purstate;
	}
	public String getPurid() {
		return purid;
	}
	public void setPurid(String purid) {
		this.purid = purid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getPurdate() {
		return purdate;
	}
	public void setPurdate(Date purdate) {
		this.purdate = purdate;
	}
	public Integer getPurstate() {
		return purstate;
	}
	public void setPurstate(Integer purstate) {
		this.purstate = purstate;
	}
	
	
}
