package edu.learn.bms.service;

import java.util.List;

import edu.learn.bms.dao.BookTypeDao;
import edu.learn.bms.projo.BookType;

public class BookTypeService {
	private BookTypeDao typeDao = new BookTypeDao();
	
	public List<BookType> getBookTypeList(BookType type){
		return typeDao.selectForBeanList(type);
	}
	/**
	 * 根据ID得到图书类型
	 * @param id
	 * @return
	 */
	public BookType getBookType(String id) {
		return typeDao.selectForBeanById(id);
	}
	/**
	 * 修改类型信息
	 * @param bookType
	 * @return
	 */
	public boolean updateBookType(BookType bookType) {
		int count = typeDao.updateBookType(bookType);
		return count>0?true:false;
	}
	/**
	 * 增加图书类型
	 * @param bookType
	 */
	public boolean add(BookType bookType) {
		int count = typeDao.add(bookType);
		return count>0?true:false;
	}
}
