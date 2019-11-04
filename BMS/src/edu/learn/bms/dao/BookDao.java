package edu.learn.bms.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.learn.bms.projo.Book;
import edu.learn.bms.utils.MySqlHelper;

public class BookDao {
	/**
	 * 查询所有满足条件的book
	 * @param book
	 * @return
	 */
	public List<Book> selectForBeanList(Book book){
		String sql="SELECT bookid,btid,bookstate,bookstore,bookname,bookprice FROM book WHERE 1=1 ";
		if(book!=null) {
			if(book.getBookname()!=null && !book.getBookname().equals("")) {
				sql+=" AND bookname like '%"+book.getBookname()+"%'";
			}
			if(book.getBtid()!=null && !book.getBtid().equals("") && !book.getBtid().equals("-1")) {
				sql+=" AND btid='"+book.getBtid()+"'";
			}
		}
		//System.out.println(sql);
		
		return MySqlHelper.executeQueryForBeanList(sql, Book.class);
	}

	public int updateBook(Book book) {
		String sql="UPDATE book SET btid=?,bookstate=?,bookstore=?, bookname=?,bookprice=? where bookid=?";
		return MySqlHelper.executeUpdate(sql,book.getBtid(),book.getBookstate(),book.getBookstore(),book.getBookname(),book.getBookprice(),book.getBookid());
	}
	
	public Book selectForBeanById(String id) {
		String sql="SELECT bookid,btid,bookstate,bookstore,bookname,bookprice FROM book WHERE bookid='"+id+"'";
		return MySqlHelper.executeQueryForBean(sql, Book.class);
	}
	public Book selectForBeanById(Connection conn,String id) throws Exception {
		String sql="SELECT bookid,btid,bookstate,bookstore,bookname,bookprice FROM book WHERE bookid='"+id+"'";
		return MySqlHelper.executeQueryForBean(conn,sql, Book.class);
	}
	
	/**
	 * 图书出入库
	 * @param num
	 * @return
	 * @throws SQLException 
	 */
	public int operStore(Connection conn,Integer num) throws SQLException {
		String sql="update book set bookstore=bookstore+"+num;
		return MySqlHelper.executeUpdate(conn, sql);
	}
	/**
	 * 得到库存
	 * @param bookid
	 * @return
	 * @throws SQLException 
	 */
	public int getStoreById(Connection conn,String bookid) throws SQLException {
		String sql="select bookstore from book where bookid=?";
		return MySqlHelper.executeQueryForObject(conn,sql, Integer.class, bookid);
	}
	
	public int insertBook(Connection conn, Book book) throws SQLException {
		String sql="insert into book values(?,null,1,?,?,?)";
		return MySqlHelper.executeUpdate(conn, sql, book.getBookid(),book.getBookstore(),book.getBookname(),book.getBookprice());
	}

	public void deleteById(String bookid) {
		String sql="delete from book where bookid=?";
		MySqlHelper.executeUpdate(sql, bookid);
	}

}
