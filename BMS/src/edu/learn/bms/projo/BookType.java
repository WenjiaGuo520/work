package edu.learn.bms.projo;

import java.util.List;

public class BookType {
	private String btid;
	private String btname;
	private Integer btstate;
	private List<Book> books;
	
	
	public BookType() {
		super();
	}


	public BookType(String typeName) {
		this.btname=typeName;
	}

	public BookType(String btid, String btname, Integer btstate) {
		super();
		this.btid = btid;
		this.btname = btname;
		this.btstate = btstate;
	}


	public String getBtid() {
		return btid;
	}


	public void setBtid(String btid) {
		this.btid = btid;
	}


	public String getBtname() {
		return btname;
	}


	public void setBtname(String btname) {
		this.btname = btname;
	}


	public Integer getBtstate() {
		return btstate;
	}


	public void setBtstate(Integer btstate) {
		this.btstate = btstate;
	}


	public List<Book> getBooks() {
		return books;
	}


	public void setBooks(List<Book> books) {
		this.books = books;
	}


	/**
	 * 重写toString，让这个类型名代表这个对象
	 */
	@Override
	public String toString() {
		return btname;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null) return false;
		
		if(obj instanceof BookType) {
			return this.btid.equals(((BookType) obj).getBtid());
		}
		System.out.println("................................");
		return false;
	}
	
}
