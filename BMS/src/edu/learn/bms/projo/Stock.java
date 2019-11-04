package edu.learn.bms.projo;

import java.util.Date;

/**
 * �ֿ��¼
 * @author 1
 *
 */
public class Stock {

	private String stockid;
	private String bookid;
	private Integer stocknum; //��� ����  ���� ����
	private Date stockdate;
	private String stockreason; //1 ����  2 ����  3 �˻�  4 ����  5 ��ӯ  6 �̿�
	
	private Book book;
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
	public Stock(String stockid, String bookid, Integer stocknum, Date stockdate, String stockreason) {
		super();
		this.stockid = stockid;
		this.bookid = bookid;
		this.stocknum = stocknum;
		this.stockdate = stockdate;
		this.stockreason = stockreason;
	}
	public Stock() {
		super();
	}
	public String getStockid() {
		return stockid;
	}
	public void setStockid(String stockid) {
		this.stockid = stockid;
	}
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public Integer getStocknum() {
		return stocknum;
	}
	public void setStocknum(Integer stocknum) {
		this.stocknum = stocknum;
	}
	public Date getStockdate() {
		return stockdate;
	}
	public void setStockdate(Date stockdate) {
		this.stockdate = stockdate;
	}
	public String getStockreason() {
		return stockreason;
	}
	public void setStockreason(String stockreason) {
		this.stockreason = stockreason;
	}
	
	
}
