package edu.etime.cms.services.interfaces;

import edu.etime.cms.dto.SysRoleDto;
import edu.etime.cms.pojo.ReturnBean;
import edu.etime.cms.pojo.SysPermission;

/**
 * 权限业务逻辑层接口
 * @author 1
 *
 */
public interface SysPermissionService {
	/**
	 * 添加用户的权限到数据库
	 * @param pers 一个用户的所有权限
	 * @return
	 */
	boolean editPermission(SysRoleDto pers);
}
