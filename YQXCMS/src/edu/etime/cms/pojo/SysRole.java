package edu.etime.cms.pojo;
/**
 * 系统角色实体类
 * @author 1
 *
 */
public class SysRole {
	private String rid;
	private String rname;
	private String rdesc;
	private Integer rstate;
	
	
	public SysRole() {
		super();
	}
	public SysRole(String rid, String rname, String rdesc, Integer rstate) {
		super();
		this.rid = rid;
		this.rname = rname;
		this.rdesc = rdesc;
		this.rstate = rstate;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getRdesc() {
		return rdesc;
	}
	public void setRdesc(String rdesc) {
		this.rdesc = rdesc;
	}
	public Integer getRstate() {
		return rstate;
	}
	public void setRstate(Integer rstate) {
		this.rstate = rstate;
	}
	
	
}
