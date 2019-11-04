package edu.etime.cms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.etime.cms.dto.ArticleDto;
import edu.etime.cms.pojo.ArtType;
import edu.etime.cms.utils.DBHelper;

/**
 * 文章类型持久层
 * 
 * @author 1
 *
 */
public class ArtTypeDao {
	/**
	 * 将artType插入yqxcms数据库arttype表
	 * 
	 * @param artType 要插入的数据
	 * @return
	 */
	public int insertByArtType(ArtType artType) {
		String sql = "insert into arttype values(?,?,?,?,?)";
		return DBHelper.update(sql, artType.getTid(), artType.getTname(), artType.getTdesc(), artType.getTstate(),
				artType.getTsort());
	}
	/**
	 * 根据条件得到总记录数
	 * @param map 条件
	 * @return
	 */
	public int selectCount(Map<String, String[]> map) {
		String sql = "select count(tid) from arttype where 1=1 ";
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
	public List<ArtType> selectAllByPage(int start, int rows, Map<String, String[]> map) {
		String sql = "select * from arttype where 1=1 ";
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
		return DBHelper.queryForList(sql, ArtType.class, params.toArray());
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
			if ("currentPage".equals(key) || "rows".equals(key) || "cmd".equals(key) || "tid".equals(key)) {
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
	 * 根据id属性删除相应的arttype,假删除,只需要将其state改变
	 * @param tids
	 * @return 影响的行数
	 */
	public int deleteById(String[] tids) {
		String sql = "update arttype set tstate=0 where tid=? ";
		if(tids== null || tids.length==0) {return 0;}
		int count = 0;
		for(String tid:tids) {
			count += DBHelper.update(sql, tid);
		}
		return count;
	}
	/**
	 * 得到所有的文章类型
	 * @return
	 */
	public List<ArtType> selectAll() {
		String sql = "select tid,tname,tdesc,tstate,tsort from arttype";
		return DBHelper.queryForList(sql, ArtType.class);
	}
	/**
	 * 根据文章类型id修改文章类型信息
	 * @param type
	 * @return
	 */
	public int updateById(ArtType type) {
		String  sql = "";
		if(type.getTid() == null || type.getTid().equals("")) {
			return 0;
		}
		StringBuilder sb = new StringBuilder("update arttype set ");
		List<Object> params = new ArrayList<Object>();
		if(type.getTname()!=null && !"".equals(type.getTname())) {
			sb.append(" tname=? ,");
			params.add(type.getTname());
		}
		if(type.getTdesc()!=null && !"".equals(type.getTdesc())) {
			sb.append(" tdesc=? ,");
			params.add(type.getTdesc());
		}
		if (type.getTsort()!=null && !"".equals(type.getTsort())) {
			sb.append(" tsort=? ,");
			params.add(type.getTsort());
		}
		if(type.getTstate()!= null && !"".equals(type.getTstate())) {
			sb.append(" tstate=? ,");
			params.add(type.getTstate());
		}
		if (params.size()>0) {
			//去掉最后一个逗号
			sql = sb.substring(0,sb.length()-1);			
			sql+=" where tid=?";
			params.add(type.getTid());
			return DBHelper.update(sql, params.toArray());
		}else {
			return 0;
		}
	}
	/**
	 * 获得一个根据tsort字段排序的集合
	 * @return
	 */
	public List<ArtType> selectAllOrderByTsort() {
		String sql="select * from arttype order by tsort";
		return DBHelper.queryForList(sql, ArtType.class);
	}
	

}
