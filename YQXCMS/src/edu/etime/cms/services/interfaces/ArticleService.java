package edu.etime.cms.services.interfaces;

import java.util.List;
import java.util.Map;

import edu.etime.cms.dto.ArticleDto;
import edu.etime.cms.pojo.ArtType;
import edu.etime.cms.pojo.Article;
import edu.etime.cms.pojo.PageBean;

/**
 * 文章相关业务操作逻辑接口
 * @author 1
 *
 */
public interface ArticleService {
	/**
	 * 增加文章
	 * @param art
	 * @return
	 */
	boolean add(Article art);
	/**
	 * 得到分页数据
	 * @param map
	 * @return
	 */
	PageBean<ArticleDto> getArticleList(Map<String, String[]> map);
	/**
	 * 根据id删除
	 * @param ids
	 * @return
	 */
	boolean deleteArticleByIds(String[] ids);
	/**
	 * 根据id获取article
	 * @param aid
	 * @return
	 */
	Article getArticle(String aid);
	/**
	 * 修改article
	 * @param art
	 * @return
	 */
	int edit(Article art);
	/**
	 * 根据文章类型的id获取文章集合
	 * 
	 * @param tid
	 * @return
	 */
	List<Article> getArticleListByTid(String tid);
	
}
