package edu.learn.bms.dao;

import java.util.List;

import edu.learn.bms.projo.Book;
import edu.learn.bms.projo.BookType;
import edu.learn.bms.utils.MySqlHelper;

public class BookTypeDao {
	/**
	 * 查询所有满足条件的type
	 * @param btype
	 * @return
	 */
	public List<BookType> selectForBeanList(BookType btype){
		String sql="SELECT btid,btname,btstate FROM booktype WHERE 1=1 ";
		if(btype!=null && btype.getBtname()!=null) {
			sql+=" AND btname like '%"+btype.getBtname()+"%'";
		}
		return MySqlHelper.executeQueryForBeanList(sql, BookType.class);
	}
	/**
	 * 根据ID查询图书类型
	 * @param id
	 * @return
	 */
	public BookType selectForBeanById(String id) {
		String sql="SELECT btid,btname,btstate FROM booktype WHERE btid='"+id+"'";
		//System.out.println(sql);
		return MySqlHelper.executeQueryForBean(sql, BookType.class);
	}
	/**
	 * 修改类型信息
	 * @param bookType
	 * @return
	 */
	public int updateBookType(BookType bookType) {
		String sql="UPDATE booktype SET btname=?,btstate=? where btid=?";
		return MySqlHelper.executeUpdate(sql,bookType.getBtname(),bookType.getBtstate(),bookType.getBtid());
	}
	/**
	 * 	增加图书类型
	 * @param bookType
	 * @return
	 */
	public int add(BookType bookType) {
		if(selectForBeanById(bookType.getBtid())!=null) {
			return 0;
		}
		String sql="INSERT INTO booktype(btid,btname,btstate) VALUES(?,?,?)";
		return MySqlHelper.executeUpdate(sql, bookType.getBtid(),bookType.getBtname(),bookType.getBtstate());
	}
}
