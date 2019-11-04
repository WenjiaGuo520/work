package edu.learn.bms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.learn.bms.dao.BookDao;
import edu.learn.bms.dao.SaleDetailsDao;
import edu.learn.bms.dao.StockDao;
import edu.learn.bms.projo.Book;
import edu.learn.bms.projo.SaleDetails;
import edu.learn.bms.projo.Stock;
import edu.learn.bms.utils.MySqlHelper;

public class SaleDetailService {
	private SaleDetailsDao detailDao = new SaleDetailsDao();
	private StockDao stockDao = new StockDao();
	private BookDao bookDao = new BookDao();
	
	public List<SaleDetails> getList(SaleDetails detail){
		return detailDao.selectBeanList(detail);
	}

	public boolean add(SaleDetails details) {
		int count = detailDao.insertDetails(details);
		return count>0?true:false;
	}
	
	/**
	 * 出库
	 * @param stocks
	 * @return
	 */
	public boolean operStore(List<Stock> stocks) {
		
		Connection conn = null;
		try {
			conn = MySqlHelper.getConnection();
			conn.setAutoCommit(false);
			for(Stock s : stocks) {
				int num = bookDao.getStoreById(conn,s.getBookid());
				if(num+s.getStocknum()<0) {
					conn.commit();
					return false;
				}
				stockDao.insertStock(conn, s);
				//改变图书数量
				bookDao.operStore(conn, s.getStocknum());
			}
			conn.commit();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			conn.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
		
	}
}
