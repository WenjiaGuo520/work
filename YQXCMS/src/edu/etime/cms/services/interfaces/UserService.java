package edu.etime.cms.services.interfaces;

import java.util.Map;

import edu.etime.cms.dto.SysUserDto;
import edu.etime.cms.pojo.PageBean;
import edu.etime.cms.pojo.User;
/**
 * 用户相关操作逻辑接口
 * @author 1
 *
 */
public interface UserService {
	/**
	 * 登录,返回登录的用户信息
	 * @param username
	 * @param userpwd
	 * @return
	 */
	User login(String username, String userpwd);
	/**
	 * 根据条件获得一页记录
	 * @param map
	 * @return
	 */
	PageBean<SysUserDto> getPage(Map<String, String[]> map);
	/**
	 * 根据userid删除user
	 * @param userid
	 * @return
	 */
	boolean deleteUserById(String[] userids);
	/**
	 * 	增加用户
	 * @param user
	 * @return
	 */
	boolean addUser(User user);
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	boolean editUser(User user);
	/**
	 * 	查询是否有该用户名
	 * @param username
	 * @return
	 */
	int selectIsExists(String username);
}
