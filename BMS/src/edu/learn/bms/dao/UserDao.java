package edu.learn.bms.dao;

import java.util.List;

import edu.learn.bms.projo.User;
import edu.learn.bms.utils.MySqlHelper;

public class UserDao {
	
	public User selectUser(User user){
		String sql="select userid,username,userpwd,usertruename,userstate from sysusers where username=? and userpwd=?";
		return MySqlHelper.executeQueryForBean(sql, User.class, user.getUsername(),user.getUserpwd());
	}
	public User selectUserById(String userid){
		String sql="select userid,username,userpwd,usertruename,userstate from sysusers where userid=?";
		return MySqlHelper.executeQueryForBean(sql, User.class, userid);
	}
	public Long getCountForUserName(User user) {
		String sql="select count(userid) from sysusers where username=?";
		return MySqlHelper.executeQueryForObject(sql, Long.class, user.getUsername());
	}
	public List<User> selectUserForBeanList(User user) {
		String sql="select userid,username,userpwd,usertruename,userstate from sysusers where 1=1";
		if(user!=null && user.getUsertruename()!=null && !user.getUsertruename().equals("")) {
			sql+=" AND usertruename like '%"+user.getUsertruename()+"%'";
		}
		return MySqlHelper.executeQueryForBeanList(sql, User.class);
	}
	
	public int insertUser(User user) {
		String sql ="insert into sysusers values(?,?,?,?,?)";
		return MySqlHelper.executeUpdate(sql, user.getUserid(),user.getUsername(),user.getUserpwd(),user.getUsertruename(),user.getUserstate());
	}
	public int updateUser(User user) {
		String sql ="update sysusers set username=?,userpwd=?,usertruename=?,userstate=? where userid=?";
		return MySqlHelper.executeUpdate(sql, user.getUsername(),user.getUserpwd(),user.getUsertruename(),user.getUserstate(),user.getUserid());
	}
}
