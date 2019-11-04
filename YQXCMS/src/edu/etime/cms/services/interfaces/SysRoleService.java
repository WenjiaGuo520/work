package edu.etime.cms.services.interfaces;
/**
 * 系统角色管理业务逻辑层
 * @author 1
 *
 */

import java.util.List;
import java.util.Map;

import edu.etime.cms.pojo.PageBean;
import edu.etime.cms.pojo.ReturnBean;
import edu.etime.cms.pojo.SysRole;

public interface SysRoleService {
	/**
	 * 根据当前页,每页显示的行数,以及查询条件获得一页数据
	 * @param map
	 * @return
	 */
	PageBean<SysRole> getPage(Map<String, String[]> map);
	/**
	 * 根据id弃用角色
	 * @param ids
	 * @return
	 */
	boolean deleteRoleByIds(String[] ids);
	/**
	 * 增加角色
	 * @param role
	 * @return 增加状态信息
	 */
	ReturnBean add(SysRole role);
	/**
	 * 修改角色信息
	 * @param role
	 * @return
	 */
	boolean edit(SysRole role);
	/**
	 * 获得所有的角色
	 * @return
	 */
	List<SysRole> getAll();
}
