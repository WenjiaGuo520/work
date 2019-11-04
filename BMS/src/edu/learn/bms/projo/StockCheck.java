package edu.learn.bms.projo;

import java.util.Date;
/**
 * ²Ö¿âÅÌµã¼ÇÂ¼
 * @author 1
 *
 */
public class StockCheck {
	private String scid;
	private String userid;
	private Date sctime;
	private Integer scstate;
	
	public String getScid() {
		return scid;
	}
	public void setScid(String scid) {
		this.scid = scid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Date getSctime() {
		return sctime;
	}
	public void setSctime(Date sctime) {
		this.sctime = sctime;
	}
	public Integer getScstate() {
		return scstate;
	}
	public void setScstate(Integer scstate) {
		this.scstate = scstate;
	}
	
	
}
