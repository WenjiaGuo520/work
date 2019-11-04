package edu.etime.cms.dto;
/**
 * Article扩展
 * 	将日期转换为字符串便于传递给html界面
 * @author 1
 *
 */

import edu.etime.cms.pojo.Article;

public class ArticleDto2 extends Article{
	private String strDate;

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}
	
	
}
