package edu.etime.cms.services.impl;

import java.util.List;
import java.util.Map;

import edu.etime.cms.dao.ArtTypeDao;
import edu.etime.cms.dao.ArticleDao;
import edu.etime.cms.dto.ArticleDto;
import edu.etime.cms.pojo.ArtType;
import edu.etime.cms.pojo.Article;
import edu.etime.cms.pojo.PageBean;
import edu.etime.cms.services.interfaces.ArticleService;

public class ArticleServiceImpl implements ArticleService {
	private ArticleDao dao = new ArticleDao();
	//private ArtTypeDao typeDao = new ArtTypeDao();
	
	@Override
	public boolean add(Article art) {
		int count = dao.insertArticle(art);
		return count==0?false:true;
	}

	@Override
	public PageBean<ArticleDto> getArticleList(Map<String, String[]> map) {
		//默认当前页为1
		int currentPage = 1;
		//默认每页显示5条记录
		int rows = 5;
		//创建PageBean对象
        PageBean<ArticleDto> pb = new PageBean<>();
        //如果map中没有currentPage,rows
        if(map.containsKey("currentPage")){
            currentPage = Integer.parseInt(map.get("currentPage")[0]);
        }
        if(map.containsKey("rows")){
            rows = Integer.parseInt(map.get("rows")[0]);
        }
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //limit 查询开始位置
        int start = (currentPage-1) * rows;
        //结束位置
        int totalCount = dao.selectCount(map);
        List<ArticleDto> list = dao.selectAllByPage(start,rows,map);
        //得到总页数
        int totalPage =totalCount % rows == 0?totalCount/rows:totalCount/rows+1;
        pb.setTotalPage(totalPage);
        pb.setTotalCount(totalCount);
        pb.setList(list);

        return pb;

	}

	@Override
	public boolean deleteArticleByIds(String[] ids) {
		
		int count = dao.updateStateById(ids);
		return count==0?false:true;
	}

	@Override
	public Article getArticle(String aid) {
		
		return dao.selectById(aid);
	}

	@Override
	public int edit(Article art) {
		return dao.updateArticleById(art);
	}

	@Override
	public List<Article> getArticleListByTid(String tid) {
		
		return dao.selectByTypeId(tid);
	}

	

}
