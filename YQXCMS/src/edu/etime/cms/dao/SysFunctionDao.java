package edu.etime.cms.dao;
/**
 * 系统功能数据交互
 * @author 1
 *
 */

import java.util.List;

import edu.etime.cms.dto.SysFunctionDto;
import edu.etime.cms.pojo.SysFunction;
import edu.etime.cms.utils.DBHelper;

public class SysFunctionDao {
	/**
	 * 返回一个SysFunctionDto类型的list集合
	 * SysFunctionDto对应sql查询的表结构
	 * @return
	 */
	public List<SysFunctionDto> selectForList(){
		String sql = "SELECT c.fid,c.fname,c.faddr,c.pfid,c.fstate, p.fname AS pfname FROM sysfunction c LEFT OUTER JOIN sysfunction p ON c.pfid=p.fid";
		return DBHelper.queryForList(sql, SysFunctionDto.class);
	}
	/**
	 * 插入功能数据到数据库
	 * @param fun
	 * @return
	 */
	public int insertSysFunction(SysFunction fun) {
		String sql = "insert into sysfunction values(?,?,?,?,?)";
		return DBHelper.update(sql, fun.getFid(),fun.getFname(),fun.getFaddr(),fun.getPfid(),fun.getFstate());
	}
	/**
	 * 查询fun再在表中的fid,fname,faddr有无重复
	 * @param fun
	 * @return 大于1位有重复的值
	 */
	public int selectCountColumnByFun(SysFunction fun) {
		String sql1 = "select count(fid) from sysfunction where fid = ?";
		int count1 = DBHelper.queryForNumber(sql1, fun.getFid()).intValue();
		String sql2="select count(fid) from sysfunction where fname = ?";
		int count2 = DBHelper.queryForNumber(sql2, fun.getFname()).intValue();
		String sql3 = "select count(fid) from sysfunction where faddr = ?";
		int count3 = DBHelper.queryForNumber(sql3, fun.getFaddr()).intValue();
		return count1+count2+count3;
	}
	/**
	 * 	根据角色返回该角色下的所有功能
	 * @param roleid
	 * @return
	 */
	public List<SysFunctionDto> selectForListByRid(String roleid) {
		String sql = "SELECT c.fid,c.fname,c.faddr,c.pfid,c.fstate, p.fname AS pfname FROM sysfunction c LEFT OUTER JOIN sysfunction p ON c.pfid=p.fid "
				+ " WHERE c.fid IN(SELECT fid FROM syspermission WHERE rid=?) AND c.fstate=1";
		return DBHelper.queryForList(sql, SysFunctionDto.class, roleid);
	}
	/**
	 * 	查询所有的根节点数
	 * @return
	 */
	public int selectForRootFunction() {
		String sql = "select count(fid) from sysfunction where pfid=-1";
		return DBHelper.queryForNumber(sql).intValue();
	}
	/**
	 * 查询出根节点列表
	 * @return
	 */
	public List<SysFunction> selectRoot(){
		String sql= "select * from sysfunction where pfid=-1";
		return DBHelper.queryForList(sql, SysFunction.class);
	}
	/**
	 * 修改功能
	 * @param fun
	 * @return
	 */
	public int updateFunctionBuId(SysFunction fun) {
		String sql = "update sysfunction set fname=?,faddr=?,fstate=? where fid=?";
		return DBHelper.update(sql, fun.getFname(),fun.getFaddr(),fun.getFstate(),fun.getFid());
	}
	/**
	 * 	根据id查询功能
	 * @param fid
	 * @return
	 */
	public int selectCountById(String fid) {
		String sql = "select count(fid) from sysfunction where fid = ?";
		return DBHelper.queryForNumber(sql, fid).intValue();
	}
}
