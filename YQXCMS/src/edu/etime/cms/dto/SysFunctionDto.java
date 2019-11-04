package edu.etime.cms.dto;

import edu.etime.cms.pojo.SysFunction;
/**
 * 系统功能封装类,扩展了pfname(其父功能名)
 * @author 1
 *
 */
public class SysFunctionDto extends SysFunction {
	//此功能是否被用户拥有
	private Integer isRoleFun = 0;

	public Integer getIsRoleFun() {
		return isRoleFun;
	}

	public void setIsRoleFun(Integer isRoleFun) {
		this.isRoleFun = isRoleFun;
	}

	private String pfname;

	public String getPfname() {
		return pfname;
	}

	public void setPfname(String pfname) {
		this.pfname = pfname;
	}
	
	
}
