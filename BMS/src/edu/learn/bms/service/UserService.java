package edu.learn.bms.service;

import java.util.List;

import edu.learn.bms.dao.UserDao;
import edu.learn.bms.projo.User;

public class UserService {
	private UserDao userDao = new UserDao();
	/**
	 * 检验登录用户，0：账户名不存在，1：密码错误,2:登录成功
	 * @param user
	 * @return
	 */
	public int checkLogin(User user) {
		long countForUserName = userDao.getCountForUserName(user);
		if(countForUserName==0) {return 0;}
		User selectUser = userDao.selectUser(user);
		if(selectUser==null) {
			return 1;
		}
		return 2;
	}
	
	public User getUser(String username,String pwd) {
		return userDao.selectUser(new User(username,pwd));
	}
	
	/**
	 * 得到user列表
	 * @param user
	 * @return
	 */
	public List<User> getUserList(User user) {
		return userDao.selectUserForBeanList(user);
	}
	/**
	 * 增加用户
	 * @param user
	 */
	public boolean addUser(User user) {
		Long isExists = userDao.getCountForUserName(user);
		if(isExists>0) {
			//用户名存在
			return false;
		}
		int count = userDao.insertUser(user);
		
		return count>0?true:false;
	}
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user) {
		int count = userDao.updateUser(user);
		return count>0?true:false;
	}
}
