package edu.etime.cms.dto;

import java.util.List;

import edu.etime.cms.pojo.SysFunction;
import edu.etime.cms.pojo.SysRole;
/**
 * 一个role可以有多个权限
 * @author 1
 *
 */
public class SysRoleDto extends SysRole{
	
	
	private List<String> list;

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	
}
