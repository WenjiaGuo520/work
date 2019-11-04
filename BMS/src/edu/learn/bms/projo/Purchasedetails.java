package edu.learn.bms.projo;

import java.util.List;

public class Purchasedetails {

	private String pdid;
	private String purid;
	private String bookid;
	private Integer pdplannum;
	private Integer pdnum;
	private Double pdprice;
	private String bookname;
	
	private Book book;
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
	public String getPdid() {
		return pdid;
	}
	public void setPdid(String pdid) {
		this.pdid = pdid;
	}
	public String getPurid() {
		return purid;
	}
	public void setPurid(String purid) {
		this.purid = purid;
	}
	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public Integer getPdplannum() {
		return pdplannum;
	}
	public void setPdplannum(Integer pdplannum) {
		this.pdplannum = pdplannum;
	}
	public Integer getPdnum() {
		return pdnum;
	}
	public void setPdnum(Integer pdnum) {
		this.pdnum = pdnum;
	}
	public Double getPdprice() {
		return pdprice;
	}
	public void setPdprice(Double pdprice) {
		this.pdprice = pdprice;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	
	
	
	
}
