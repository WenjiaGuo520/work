package edu.etime.cms.pojo;
/**
 * 权限实体类
 * @author 1
 *
 */
public class SysPermission {
	private String pid;
	private String rid;//角色id
	private String fid;//功能id
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	
}
