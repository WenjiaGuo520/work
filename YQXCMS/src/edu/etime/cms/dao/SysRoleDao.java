package edu.etime.cms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.etime.cms.pojo.ArtType;
import edu.etime.cms.pojo.SysRole;
import edu.etime.cms.utils.DBHelper;

/**
 * 系统角色管理数据交互
 * @author 1
 *
 */
public class SysRoleDao {
	
	/**
	 * 根据条件得到总记录数
	 * @param map 条件
	 * @return
	 */
	public int selectCount(Map<String, String[]> map) {
		String sql = "select count(rid) from sysrole where 1=1 ";
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
	public List<SysRole> selectAllByPage(int start, int rows, Map<String, String[]> map) {
		String sql = "select * from sysrole where 1=1 ";
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
		return DBHelper.queryForList(sql, SysRole.class, params.toArray());
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
			if ("currentPage".equals(key) || "rows".equals(key) || "cmd".equals(key) || "rid".equals(key)) {
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
	 * 修改role的状态为
	 * @param userid
	 * @return
	 */
	public int updateStateById(Integer rstate,String rid) {
		String sql = "update sysrole set rstate=? where rid=?";
		return DBHelper.update(sql,rstate, rid);
	}
	/**
	 * 根据id修改role
	 * @param rid
	 * @return
	 */
	public int updateRoleById(SysRole role) {
		String sql = "update sysrole set rname=?,rdesc=?,rstate=? where rid=?";
		return DBHelper.update(sql,role.getRname(),role.getRdesc(),role.getRstate(),role.getRid());
	}
	
	/**
	 * 插入一条角色记录
	 * @param role
	 * @return
	 */
	public int insertRole(SysRole role) {
		String sql="insert into sysrole values(?,?,?,?)";
		return DBHelper.update(sql, role.getRid(),role.getRname(),role.getRdesc(),role.getRstate());
	}
	/**
	 * 	得到所有的角色
	 * @return
	 */
	public List<SysRole> selectForList(){
		String sql = "select * from sysrole";
		return DBHelper.queryForList(sql, SysRole.class);
	}
}
