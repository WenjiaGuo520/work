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
	 * ����ID�õ�ͼ������
	 * @param id
	 * @return
	 */
	public BookType getBookType(String id) {
		return typeDao.selectForBeanById(id);
	}
	/**
	 * �޸�������Ϣ
	 * @param bookType
	 * @return
	 */
	public boolean updateBookType(BookType bookType) {
		int count = typeDao.updateBookType(bookType);
		return count>0?true:false;
	}
	/**
	 * ����ͼ������
	 * @param bookType
	 */
	public boolean add(BookType bookType) {
		int count = typeDao.add(bookType);
		return count>0?true:false;
	}
}
