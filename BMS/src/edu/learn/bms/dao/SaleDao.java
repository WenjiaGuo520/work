package edu.learn.bms.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.learn.bms.projo.Sale;
import edu.learn.bms.utils.MySqlHelper;

public class SaleDao {
	
	public List<Sale> selectBeanList(Sale sale){
		String sql="select saleid,userid,saletime,salestate from sale where 1=1 ";
		if(sale!=null) {
			if(sale.getSalestate()!=null && sale.getSalestate()!=-1) {
				sql+="and salestate="+sale.getSalestate();
			}
		}
		return MySqlHelper.executeQueryForBeanList(sql, Sale.class);
	}

	public int insertSale(Connection conn, Sale sale) throws SQLException {
		String sql="insert into sale values(?,?,?,?)";
		return MySqlHelper.executeUpdate(conn, sql, sale.getSaleid(),sale.getUserid(),sale.getSaletime(),sale.getSalestate());
	}
	public int insertSale(Sale sale) {
		String sql="insert into sale values(?,?,?,?)";
		return MySqlHelper.executeUpdate(sql, sale.getSaleid(),sale.getUserid(),sale.getSaletime(),sale.getSalestate());
	}

	public int deleteSaleById(String saleid) {
		String sql="delete from sale where saleid=?";
		return MySqlHelper.executeUpdate(sql, saleid);
	}
	
	public void updateForState(String id, Integer state) {
		String sql="update sale set salestate=? where saleid=?";
		MySqlHelper.executeUpdate(sql, state,id);
	}
}
