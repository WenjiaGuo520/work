package edu.learn.bms.dao;

import java.sql.Connection;
import java.sql.SQLException;

import edu.learn.bms.projo.Stock;
import edu.learn.bms.utils.MySqlHelper;

/**
 * 出入库登记
 * @author 1
 *
 */
public class StockDao {
	/**
	 * 添加
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
	 * 根据书籍查询是否存在
	 * @param bookid
	 * @return
	 */
	public long selectCountByBookid(String bookid) {
		String sql="select count(stockid) from stock where bookid=?";
		return MySqlHelper.executeQueryForObject(sql, Long.class, bookid);
	}
	
	
}
