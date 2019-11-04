package edu.etime.cms.services.interfaces;
/**
 * 系统功能管理业务逻辑接口
 * @author 1
 *
 */

import java.util.List;

import edu.etime.cms.dto.SysFunctionDto;
import edu.etime.cms.pojo.ReturnBean;
import edu.etime.cms.pojo.SysFunction;

public interface SysFunctionService {
	/**
	 * 得到功能列表 SysFunctionDto集合
	 * @return
	 */
	List<SysFunctionDto> getList();
	
	/**
	 * 添加系统功能
	 * @param fun
	 * @return 返回一个添加的状态,以及信息
	 */
	ReturnBean add(SysFunction fun);
	/**
	 * 根据角色返回该角色的功能
	 * @param roleid
	 * @return
	 */
	List<SysFunctionDto> getListByRid(String roleid);
	/**
	 * 返回根节点列表
	 * @return
	 */
	List<SysFunction> getRootList();
	/**
	 * 修改功能
	 * @param fun
	 * @return
	 */
	boolean editFunction(SysFunction fun);
	/**
	 * 	保存功能
	 * @param fun
	 * @return
	 */
	ReturnBean save(SysFunction fun);
}
