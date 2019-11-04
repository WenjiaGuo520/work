package edu.learn.bms.dao;

import java.sql.Connection;
import java.sql.SQLException;

import edu.learn.bms.projo.Stock;
import edu.learn.bms.utils.MySqlHelper;

/**
 * �����Ǽ�
 * @author 1
 *
 */
public class StockDao {
	/**
	 * ���
	 * @param conn
	 * @param stock
	 * @return
	 * @throws SQLException
	 */
	public int insertStock(Connection conn,Stock stock) throws SQLException {
		String sql="insert into stock values(?,?,?,?,?)";
		return MySqlHelper.executeUpdate(conn, sql,stock.getStockid(),stock.getBookid(),
				stock.getStocknum(),stock.getStockdate(),stock.getStockreason());
	}
	/**
	 * �����鼮��ѯ�Ƿ����
	 * @param bookid
	 * @return
	 */
	public long selectCountByBookid(String bookid) {
		String sql="select count(stockid) from stock where bookid=?";
		return MySqlHelper.executeQueryForObject(sql, Long.class, bookid);
	}
	
	
}
