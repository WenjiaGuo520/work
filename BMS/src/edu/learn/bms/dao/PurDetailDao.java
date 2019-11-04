package edu.learn.bms.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.learn.bms.projo.Purchasedetails;
import edu.learn.bms.utils.MySqlHelper;

public class PurDetailDao {
	
	public List<Purchasedetails> selectBeanList(Purchasedetails detail){
		String sql="select pdid,purid,bookid,pdplannum,pdnum,pdprice,bookname from purchasedetails where 1=1 ";
		if(detail != null) {
			if(detail.getBookid()!=null) {
				sql+=" and bookid="+detail.getBookid();
			}
		}
		return MySqlHelper.executeQueryForBeanList(sql, Purchasedetails.class);
	}
	
	public List<Purchasedetails> selectBeanListByPurid(String purid){
		String sql="select pdid,purid,bookid,pdplannum,pdnum,pdprice,bookname from purchasedetails where purid=? ";
		return MySqlHelper.executeQueryForBeanList(sql, Purchasedetails.class,purid);
	}

	public int insertDetails(Purchasedetails details) {
		String sql="insert into purchasedetails values(?,?,?,?,?,?,?)";
		return MySqlHelper.executeUpdate(sql,details.getPdid(), details.getPurid(),
				details.getBookid(),details.getPdplannum(),details.getPdnum(),details.getPdprice(),details.getBookname());
	}
	/**
	 * ´øÊÂÎñ
	 * @param conn
	 * @param details
	 * @return
	 * @throws SQLException 
	 */
	public int insertDetails(Connection conn,Purchasedetails details) throws SQLException {
		String sql="insert into purchasedetails values(?,?,?,?,?,?,?)";
		return MySqlHelper.executeUpdate(conn,sql,details.getPdid(), details.getPurid(),
				details.getBookid(),details.getPdplannum(),details.getPdnum(),details.getPdprice(),details.getBookname());
	}
}
