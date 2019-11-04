package edu.etime.cms.dto;

import edu.etime.cms.pojo.Article;
/**
 * article实体类的封装,扩展
 * @author 1
 *
 */
public class ArticleDto extends Article {
	private String tname;
	private String usertruename;
	
	
	public String getUsertruename() {
		return usertruename;
	}

	public void setUsertruename(String usertruename) {
		this.usertruename = usertruename;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}
	
	
}
