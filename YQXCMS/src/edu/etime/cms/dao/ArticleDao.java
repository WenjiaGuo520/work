package edu.etime.cms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

import edu.etime.cms.dto.ArticleDto;
import edu.etime.cms.pojo.ArtType;
import edu.etime.cms.pojo.Article;
import edu.etime.cms.utils.DBHelper;

/**
 * 文章数据持久层
 * @author 1
 *
 */
public class ArticleDao {
	/**
	 * 根据条件增加文章,文章状态直接赋值为1
	 * @param art
	 * @return
	 */
	public int insertArticle(Article art) {
		String sql = "insert into article values(?,?,?,?,?,?,?,?,?,?,1)";
		return DBHelper.update(sql,art.getAid(),art.getTid(),art.getUserid(),art.getArttitle(),art.getArtimg(),art.getArtabs(),
				art.getArttext(),art.getPubDate(),art.getAuthor(),art.getArtsource());
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
			if ("currentPage".equals(key) || "rows".equals(key) || "cmd".equals(key) 
					|| "aid".equals(key) || "tid".equals(key) || "userid".equals(key)) {
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
	 * 查询总记录数
	 * @param map
	 * @return
	 */
	public int selectCount(Map<String, String[]> map) {
		String sql = "SELECT count(aid) "
				+ " FROM article a LEFT JOIN arttype t ON a.tid=t.tid "
				+ " LEFT JOIN sysuser u ON a.userid=u.userid "
				+ " WHERE 1=1 ";
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
	 * 查询limit start end的数据
	 * @param start
	 * @param rows
	 * @param map
	 * @return
	 */
	public List<ArticleDto> selectAllByPage(int start, int rows, Map<String, String[]> map) {
		String sql = "SELECT aid,a.tid,a.userid,arttitle,artimg,artabs,pubDate,author,artsource,astate,tname,usertruename "
				+ " FROM article a LEFT JOIN arttype t ON a.tid=t.tid "
				+ " LEFT JOIN sysuser u ON a.userid=u.userid "
				+ " WHERE 1=1 ";
        //条件arttitle like ?  AND tname like ? AND username like ? and
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
		return DBHelper.queryForList(sql, ArticleDto.class, params.toArray());
	}
	/**
	 * 根据id删除
	 * @param ids
	 * @return
	 */
	public int deleteByIds(String[] ids) {
		int count = 0;
		String sql="delete from article where aid=?";
		for (String id : ids) {
			count += DBHelper.update(sql, id);
		}
		return count;
	}
	/**
	 * 根据id查询
	 * @param aid
	 * @return
	 */
	public Article selectById(String aid) {
		String sql = "SELECT aid,tid,arttitle,artimg,artabs,arttext,pubDate,author,artsource,astate FROM article WHERE aid = ? ";
		return DBHelper.queryForObject(sql, Article.class, aid);
	}
	
	/**
	 * 根据id修改article
	 * @param art
	 * @return
	 */
	public int updateArticleById(Article art) {
		String sql ="update article set tid=?,arttitle=?,artimg=?,artabs=?,pubDate=?,author=?,artsource=? where aid=?";
		return DBHelper.update(sql, art.getTid(),art.getArttitle(),art.getArtimg(),
				art.getArtabs(),art.getPubDate(),art.getAuthor(),art.getArtsource(),art.getAid());
		
//		String sql="update article set ";
//		StringBuilder sb = new StringBuilder(sql);
//		List<Object> params = new ArrayList<Object>();	
//		if (art != null) {
//			if(art.getArttitle()!=null && !"".equals(art.getArttitle())) {
//				sb.append(" arttitle= ? ,");
//				params.add(art.getArttitle());
//			}
//			if(art.getArtimg() != null && !"".equals(art.getArtimg())) {
//				sb.append(" artimg= ? ,");
//				params.add(art.getArtimg());
//			}
//			if (art.getTid()!= null && !"".equals(art.getTid())) {
//				sb.append(" tid= ? ,");
//				params.add(art.getTid());
//			}
//			if(art.getArttext()!=null && !"".equals(art.getArttext())) {
//				sb.append(" arttext= ? ,");
//				params.add(art.getArttext());
//			}
//			
//			sql = sb.substring(0, sb.length()-1);
//		}
//		sql+=" where aid=?";
//		params.add(art.getAid());
//		if(params != null && params.size() >0) {
//			return DBHelper.update(sql, params.toArray());
//		}
//		return 0;
	}
	/**
	 * 得到指定的文章类型下的文章集合
	 * @param tid
	 * @return
	 */
	public List<Article> selectByTypeId(String tid) {
		String sql = "select * from article where tid = ?";
		return DBHelper.queryForList(sql, Article.class, tid);
		
	}
	/**
	 *	假删除
	 * @param id
	 * @return
	 */
	public int updateStateById(String[] ids) {
		String sql = "update article set astate =0 where aid=?";
		int count = 0;
		for (String id : ids) {
			count+=DBHelper.update(sql, id);
			
		}
		return count;
	}
}
