package edu.etime.cms.dao;

import java.util.List;

import edu.etime.cms.pojo.SysPermission;
import edu.etime.cms.utils.DBHelper;

/**
 * 系统权限数据持久化层
 * @author 1
 *
 */
public class SysPermissionDao {
	/**
	 * 返回用户下的所有权限
	 * @param roleid
	 * @return
	 */
	public List<SysPermission> selectForListByRid(String roleid){
		String sql = "select * from  syspermission where rid = ?";
		return DBHelper.queryForList(sql, SysPermission.class, roleid);
	}
	
	/**
	 * 插入权限
	 * @param string
	 * @param rid
	 * @param fid
	 * @return 
	 */
	public int insertPerssion(String pid, String rid, String fid) {
		String sql = "insert into syspermission values(?,?,?)";
		return DBHelper.update(sql, pid,rid,fid);
		
	}
	/**
	 * 根据rid删除权限
	 * @param rid
	 * @return
	 */
	public int deleteByRid(String rid) {
		String sql="delete from syspermission where rid=?";
		return DBHelper.update(sql, rid);
		
	}
	
}
