package edu.etime.cms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.etime.cms.dto.SysUserDto;
import edu.etime.cms.pojo.ArtType;
import edu.etime.cms.pojo.User;
import edu.etime.cms.utils.DBHelper;

public class UserDao {
	
	/**
	 * 根据用户名密码,得到User的个数
	 * @param username
	 * @param userpwd
	 * @return
	 */
	public User selectByUsernameAndPwd(String username, String userpwd) {
		String sql = "select userid,rid,usertruename from sysuser where username=? and userpwd=?";
		return DBHelper.queryForObject(sql, User.class, username,userpwd);
	}
	
	/**
	 * 	根据条件得到总记录数
	 * @param map 条件
	 * @return
	 */
	public int selectCount(Map<String, String[]> map) {
		String sql = "select count(userid) from sysuser where 1=1 ";
		// 条件
		StringBuilder sb = new StringBuilder(sql);
		// 参数
		List<Object> params = new ArrayList<>();
		// 填充条件,设置参数
		setWhereAndParams(map, sb, params);
		sql = sb.toString();
		return DBHelper.queryForNumber(sql,params.toArray()).intValue();
	}
	/**
	 * 根据条件得到一页记录
	 * @param start limit的开始位置
	 * @param rows  这一页显示的记录数
	 * @param map 条件
	 * @return
	 */
	public List<SysUserDto> selectAllByPage(int start, int rows, Map<String, String[]> map) {
		String sql = "SELECT userid,rname,username,userpwd,usertruename,sex,phone,useraddress,carid,ustate FROM sysuser u LEFT OUTER JOIN sysrole r ON u.rid=r.rid WHERE 1=1 ";
        //条件
        StringBuilder sb = new StringBuilder(sql);
        //参数
        List<Object> params = new ArrayList<>();
        //填充条件,设置参数
        setWhereAndParams(map, sb, params);
        //添加分页条件
        sb.append(" limit ?,? ");
        params.add(start);
        params.add(rows);
        sql = sb.toString();
		return DBHelper.queryForList(sql, SysUserDto.class, params.toArray());
	}

	/**
	 * 设置条件,添加参数 条件 like 参数
	 * 
	 * @param map    查询的条件参数
	 * @param sb     需要增加的条件
	 * @param params 需要增加的查询条件参数
	 */
	private void setWhereAndParams(Map<String, String[]> map, StringBuilder sb, List<Object> params) {
		if (map==null || map.keySet().size()==0) {
			return;
		}
		// 遍历map
		for (String key : map.keySet()) {
			// 排除其他参数
			if ("currentPage".equals(key) || "rows".equals(key) || "cmd".equals(key) || "userid".equals(key) || "rid".equals(key)) {
				continue;
			}
			// 添加模糊查询条件
			String value = map.get(key)[0];
			if (value != null && !"".equals(value)) {
				sb.append(" and " + key + " like ?");
				params.add("%" + value + "%");
			}
		}
	}
	/**
	 * 修改user的状态为
	 * @param userid
	 * @return
	 */
	public int updateStateById(Integer ustate,String userid) {
		String sql = "update sysuser set ustate=? where userid=?";
		return DBHelper.update(sql,ustate, userid);
	}
	
	/**
	 * 	增加用户
	 * @param user
	 * @return
	 */
	public int insertUser(User user) {	
		String sql="insert into sysuser values(?,?,?,?,?,?,?,?,?,?)";
		return DBHelper.update(sql, user.getUserid(),user.getRid(),user.getUsername(),user.getUserpwd(),user.getUsertruename(),user.getSex(),
				user.getPhone(),user.getUseraddress(),user.getCarid(),user.getUstate());
	}
	
	/**
	 * 	修改用户
	 * @param user
	 * @return
	 */
	public int updateUser(User user) {
		String sql = "update sysuser set rid=?,username=?,userpwd=?,usertruename=?,sex=?,phone=?,useraddress=?,carid=?,ustate=? where userid=?";
		return DBHelper.update(sql, user.getRid(),user.getUsername(),user.getUserpwd(),user.getUsertruename(),user.getSex(),
				user.getPhone(),user.getUseraddress(),user.getCarid(),user.getUstate(),user.getUserid());
	}
	/**
	 * 	根据用户名查询是否有该用户
	 * @param username
	 * @return
	 */
	public int selectCountByUserName(String username) {
		String sql = "select count(userid) from sysuser where username = ?";
		return DBHelper.queryForNumber(sql, username).intValue();
	}
}
