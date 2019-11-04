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
	 * 	��������б�
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
	 * 	����saleid�������������ϸ
	 * @param saleid
	 * @return 
	 */
	public List<SaleDetails> getSaleDetailsBySaleid(String saleid) {
		return detailDao.selectBeanListBySaleid(saleid);
		
	}
	/**
	 * ���涩��
	 * @param sale ����
	 * @param list ��ϸ
	 * @return
	 */
	public boolean addSaleDetailsForSale(Sale sale, List<SaleDetails> list) {
		//1.����һ������
		saleDao.insertSale(sale);
		//����
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
	 * �h��ָ����Sale
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
	 * �޸Ķ���״̬
	 * @param id 
	 */
	public void updateState(String id, Integer state) {
		saleDao.updateForState(id,state);
	}
}
