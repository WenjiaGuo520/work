package edu.learn.bms.projo;

import java.util.Date;
import java.util.List;

public class Sale {

	private String saleid;
	private String userid;
	private Date saletime;
	private Integer salestate;
	
	
	private User user;
	
	public Sale() {
		super();
	}
	public Sale(Integer state) {
		this.salestate=state;
	}
	public Sale(String saleid, String userid, Date saletime, Integer salestate) {
		super();
		this.saleid = saleid;
		this.userid = userid;
		this.saletime = saletime;
		this.salestate = salestate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	

	public String getSaleid() {
		return saleid;
	}

	public void setSaleid(String saleid) {
		this.saleid = saleid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getSaletime() {
		return saletime;
	}

	public void setSaletime(Date saletime) {
		this.saletime = saletime;
	}

	public Integer getSalestate() {
		return salestate;
	}

	public void setSalestate(Integer salestate) {
		this.salestate = salestate;
	}

	

}
