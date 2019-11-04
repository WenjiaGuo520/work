package edu.etime.cms.pojo;

import java.util.Date;

/**
 * 	文章实体类
 * @author 1
 *
 */
public class Article {
	private String aid;
	private String tid;
	private String userid;
	private String arttitle;
	private String artimg;
	private String artabs;
	private String arttext;
	private Date pubDate;
	private String author;
	private String artsource;
	private Integer astate;
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getArttitle() {
		return arttitle;
	}
	public void setArttitle(String arttitle) {
		this.arttitle = arttitle;
	}
	public String getArtimg() {
		return artimg;
	}
	public void setArtimg(String artimg) {
		this.artimg = artimg;
	}
	public String getArtabs() {
		return artabs;
	}
	public void setArtabs(String artabs) {
		this.artabs = artabs;
	}
	public String getArttext() {
		return arttext;
	}
	public void setArttext(String arttext) {
		this.arttext = arttext;
	}
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getArtsource() {
		return artsource;
	}
	public void setArtsource(String artsource) {
		this.artsource = artsource;
	}
	public Integer getAstate() {
		return astate;
	}
	public void setAstate(Integer astate) {
		this.astate = astate;
	}
	
	
}
