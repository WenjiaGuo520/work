package edu.learn.bms.service;

import java.util.List;

import edu.learn.bms.dao.BookDao;
import edu.learn.bms.dao.BookTypeDao;
import edu.learn.bms.projo.Book;
import edu.learn.bms.projo.BookType;

public class BookService {
	private BookDao bookDao = new BookDao();
	private BookTypeDao typeDao = new BookTypeDao();
	
	/**
	 * 根据条件得到所有的book集合
	 * @param book
	 */
	public List<Book> getBookList(Book book) {
		
		List<Book> books = bookDao.selectForBeanList(book);
		//将book中的btname填充上
		for(Book b:books) {
			BookType type = typeDao.selectForBeanById(b.getBtid());
			b.setType(type);
		}
		return books;
	}
	/**
	 * 	根据条件更新图书信息
	 * @param book
	 */
	public boolean updateBook(Book book) {
		int count = bookDao.updateBook(book);
		return count>0?true:false;
	}
	
	public Book getBookById(String id) {
		return bookDao.selectForBeanById(id);
	}
	public void deletBook(String bookid) {
		bookDao.deleteById(bookid);
	}
	
}
