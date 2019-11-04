package edu.learn.bms.projo;

public class Book {
	private String bookid;
	private String btid;
	private Integer bookstate;//0-下架，1-上架
	private Integer bookstore;
	private String bookname;
	private Double bookprice;
	
	//类型名称
	private BookType type;
	
	public Book() {}
	
	public Book(String bookName,String btid) {
		this.bookname=bookName;
		this.btid=btid;
	}
	
	
	
	
	public Book(String bookid, String btid, Integer bookstate, Integer bookstore, String bookname, Double bookprice) {
		super();
		this.bookid = bookid;
		this.btid = btid;
		this.bookstate = bookstate;
		this.bookstore = bookstore;
		this.bookname = bookname;
		this.bookprice = bookprice;
	}
	public Book(String bookid, Integer bookstore, String bookname, Double bookprice) {
		super();
		this.bookid = bookid;
		this.bookstore = bookstore;
		this.bookname = bookname;
		this.bookprice = bookprice;
	}

	public String getBookid() {
		return bookid;
	}
	public void setBookid(String bookid) {
		this.bookid = bookid;
	}
	public String getBtid() {
		return btid;
	}
	public void setBtid(String btid) {
		this.btid = btid;
	}
	public Integer getBookstate() {
		return bookstate;
	}
	public void setBookstate(Integer bookstate) {
		this.bookstate = bookstate;
	}
	public Integer getBookstore() {
		return bookstore;
	}
	public void setBookstore(Integer bookstore) {
		this.bookstore = bookstore;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public Double getBookprice() {
		return bookprice;
	}
	public void setBookprice(Double bookprice) {
		this.bookprice = bookprice;
	}
	public BookType getType() {
		return type;
	}
	public void setType(BookType type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return bookname;
	}

	
	
	
	
	
	
	
}
