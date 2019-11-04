package edu.etime.cms.dto;

import edu.etime.cms.pojo.User;

/**
 * 系统角色扩展类
 * @author 1
 *
 */
public class SysUserDto extends User {
	//扩展角色名
	private String rname;

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}
	
}
