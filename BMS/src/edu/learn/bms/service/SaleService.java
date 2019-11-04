package edu.learn.bms.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import edu.learn.bms.dao.SaleDao;
import edu.learn.bms.dao.SaleDetailsDao;
import edu.learn.bms.dao.UserDao;
import edu.learn.bms.projo.Sale;
import edu.learn.bms.projo.SaleDetails;
import edu.learn.bms.utils.MySqlHelper;

public class SaleService {
	private SaleDao saleDao = new SaleDao();
	private UserDao userDao = new UserDao();
	private SaleDetailsDao detailDao = new SaleDetailsDao();
	/**
	 * 	获得销售列表
	 * @param sale
	 * @return
	 */
	public List<Sale> getList(Sale sale) {
		List<Sale> list = saleDao.selectBeanList(sale);
		for(Sale s : list) {
			s.setUser(userDao.selectUserById(s.getUserid()));
		}
		return list;
	}
	/**
	 * 	根据saleid获得所有销售明细
	 * @param saleid
	 * @return 
	 */
	public List<SaleDetails> getSaleDetailsBySaleid(String saleid) {
		return detailDao.selectBeanListBySaleid(saleid);
		
	}
	/**
	 * 保存订单
	 * @param sale 订单
	 * @param list 明细
	 * @return
	 */
	public boolean addSaleDetailsForSale(Sale sale, List<SaleDetails> list) {
		//1.创建一个订单
		saleDao.insertSale(sale);
		//事务
		Connection conn = null;
		try {
			conn = MySqlHelper.getConnection();
			conn.setAutoCommit(false);
			for(SaleDetails sd : list) {
				detailDao.insertDetails(conn,sd);
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
	 * 刪除指定的Sale
	 * @param sale
	 */
	public boolean deleteSale(Sale sale) {
		if(sale!=null) {
			int count = saleDao.deleteSaleById(sale.getSaleid());
			if(count>0 )return true;
		}
		return false;
	}
	/**
	 * 修改订单状态
	 * @param id 
	 */
	public void updateState(String id, Integer state) {
		saleDao.updateForState(id,state);
	}
}
