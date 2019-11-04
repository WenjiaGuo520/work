package edu.learn.bms.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.learn.bms.projo.SaleDetails;
import edu.learn.bms.utils.MySqlHelper;

public class SaleDetailsDao {
	
	public List<SaleDetails> selectBeanList(SaleDetails detail){
		String sql="select sdid,saleid,bookid,sdnum,sdprice from saledetails where 1=1 ";
		if(detail != null) {
			if(detail.getBookid()!=null) {
				sql+=" and bookid="+detail.getBookid();
			}
		}
		return MySqlHelper.executeQueryForBeanList(sql, SaleDetails.class);
	}
	
	public List<SaleDetails> selectBeanListBySaleid(String saleid){
		String sql="select sdid,saleid,bookid,sdnum,sdprice from saledetails where saleid=? ";
		return MySqlHelper.executeQueryForBeanList(sql, SaleDetails.class,saleid);
	}

	public int insertDetails(SaleDetails details) {
		String sql="insert into saledetails values(?,?,?,?,?)";
		return MySqlHelper.executeUpdate(sql,details.getSdid(), details.getSaleid(),
				details.getBookid(),details.getSdnum(),details.getSdprice());
	}
	/**
	 * ´øÊÂÎñ
	 * @param conn
	 * @param details
	 * @return
	 * @throws SQLException 
	 */
	public int insertDetails(Connection conn,SaleDetails details) throws SQLException {
		String sql="insert into saledetails values(?,?,?,?,?)";
		return MySqlHelper.executeUpdate(conn,sql, details.getSdid(),details.getSaleid(),
				details.getBookid(),details.getSdnum(),details.getSdprice());
	}
}
