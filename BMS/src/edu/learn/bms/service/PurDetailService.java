package edu.learn.bms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.learn.bms.dao.BookDao;
import edu.learn.bms.dao.PurDetailDao;
import edu.learn.bms.dao.StockDao;
import edu.learn.bms.projo.Book;
import edu.learn.bms.projo.Purchasedetails;
import edu.learn.bms.projo.Stock;
import edu.learn.bms.utils.MySqlHelper;

public class PurDetailService {
	
	private PurDetailDao detailDao = new PurDetailDao();
	private StockDao stockDao = new StockDao();
	private BookDao bookDao = new BookDao();
	
	public List<Purchasedetails> getList(Purchasedetails details) {
		return detailDao.selectBeanList(details);
	}

	public List<Purchasedetails> getListByPurid(String purid) {
		return detailDao.selectBeanListByPurid(purid);
	}

	/**
	 * 入库
	 * @param stocks
	 * @return
	 */
	public boolean operStore(List<Stock> stocks) {
		
		Connection conn = null;
		try {
			conn = MySqlHelper.getConnection();
			conn.setAutoCommit(false);
			for(Stock s : stocks) {
				Book flag = bookDao.selectForBeanById(conn, s.getBookid());
				if(flag!=null) {
					stockDao.insertStock(conn, s);
					//改变图书数量
					bookDao.operStore(conn, s.getStocknum());
				}else{
					//插入图书
					bookDao.insertBook(conn, s.getBook());
					conn.commit();
					
					stockDao.insertStock(conn, s);
				}
				
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
