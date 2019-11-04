package edu.learn.bms.projo;

import java.util.Date;

/**
 * 仓库记录
 * @author 1
 *
 */
public class Stock {

	private String stockid;
	private String bookid;
	private Integer stocknum; //入库 正数  出库 负数
	private Date stockdate;
	private String stockreason; //1 销售  2 进货  3 退货  4 换货  5 盘盈  6 盘亏
	
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
