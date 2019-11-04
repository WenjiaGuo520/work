package edu.etime.cms.services.impl;

import java.util.List;
import java.util.Map;

import edu.etime.cms.dao.UserDao;
import edu.etime.cms.dto.SysUserDto;
import edu.etime.cms.pojo.PageBean;
import edu.etime.cms.pojo.SysRole;
import edu.etime.cms.pojo.User;
import edu.etime.cms.services.interfaces.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao = new UserDao();
	@Override
	public User login(String username, String userpwd) {
		return userDao.selectByUsernameAndPwd(username,userpwd);
	}
	@Override
	public PageBean<SysUserDto> getPage(Map<String, String[]> map) {
		PageBean<SysUserDto> pb = new PageBean<SysUserDto>();
		int currentPage = 1;//默认为1
		int rows = 5;//默认为5
		if (map.containsKey("currentPage")) {
			currentPage = Integer.parseInt(map.get("currentPage")[0].toString());
		}
		if(map.containsKey("rows")) {
			rows = Integer.parseInt(map.get("rows")[0].toString());
		}
		pb.setCurrentPage(currentPage);
		pb.setRows(rows);
		int totalCount = userDao.selectCount(map);
		pb.setTotalCount(totalCount);
		int totalPage = totalCount % rows == 0?totalCount/rows:totalCount/rows+1;
		pb.setTotalPage(totalPage);
		
		int start = (currentPage-1)*rows;
	
		List<SysUserDto> list = userDao.selectAllByPage(start, rows, map);
		pb.setList(list);
		return pb;
	}
	@Override
	public boolean deleteUserById(String[] userids) {
		//修改user的状态,达到假删除效果
		int count = 0;
		for (String userid : userids) {
			count += userDao.updateStateById(0, userid);
		}
		return count==0?false:true;
	}
	@Override
	public boolean addUser(User user) {
		int count = userDao.insertUser(user);
		return count==0?false:true;
	}
	@Override
	public boolean editUser(User user) {
		int count = userDao.updateUser(user);
		return count == 0?false:true;
	}
	@Override
	public int selectIsExists(String username) {
		return userDao.selectCountByUserName(username);
	}

}
