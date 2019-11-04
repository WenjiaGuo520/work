package edu.learn.bms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.learn.bms.dao.BookDao;
import edu.learn.bms.dao.PurDetailDao;
import edu.learn.bms.dao.PurchaseDao;
import edu.learn.bms.projo.Purchase;
import edu.learn.bms.projo.Purchasedetails;
import edu.learn.bms.projo.SaleDetails;
import edu.learn.bms.utils.MySqlHelper;

public class PurchaseService {
	private PurchaseDao purDao = new PurchaseDao();
	private PurDetailDao detailDao = new PurDetailDao();
	private BookDao bookDao = new BookDao();
	
	public List<Purchase> getList(Purchase pur){
		return purDao.selectForBeanList(pur);
	}
	
	/**
	 * 	增加订单，并保存订单明细
	 * @param pur
	 * @param list
	 * @return
	 */
	public boolean addPurForPurdetails(Purchase pur, List<Purchasedetails> list) {
		//1.创建一个订单
		purDao.insertSale(pur);
		//事务
		Connection conn = null;
		try {
			conn = MySqlHelper.getConnection();
			conn.setAutoCommit(false);
			for(Purchasedetails pd : list) {
				detailDao.insertDetails(conn,pd);
			}
			conn.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return false;
	}
	/**
	 * 删除指定的pur
	 * @param pur
	 */
	public void deletePur(String purid) {
		purDao.deleteSaleById(purid);
	}

	public void updateState(String id, Integer state) {
		purDao.updateForState(id,state);
	}
}
