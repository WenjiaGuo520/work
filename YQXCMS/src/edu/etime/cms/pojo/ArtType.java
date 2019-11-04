package edu.etime.cms.pojo;

public class ArtType{
	private String tid;
	private String tname;
	private String tdesc;
	private String tstate;
	private String tsort;
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTdesc() {
		return tdesc;
	}
	public void setTdesc(String tdesc) {
		this.tdesc = tdesc;
	}
	public String getTstate() {
		return tstate;
	}
	public void setTstate(String tstate) {
		this.tstate = tstate;
	}
	public String getTsort() {
		return tsort;
	}
	public void setTsort(String tsort) {
		this.tsort = tsort;
	}
	@Override
	public String toString() {
		return "ArtType [tid=" + tid + ", tname=" + tname + ", tdesc=" + tdesc + ", tstate=" + tstate + ", tsort="
				+ tsort + "]";
	}
	
	
	
}
