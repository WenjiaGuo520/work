package edu.learn.bms.dao;

import java.util.List;

import edu.learn.bms.projo.Purchase;
import edu.learn.bms.projo.Sale;
import edu.learn.bms.utils.MySqlHelper;

public class PurchaseDao {
	
	public List<Purchase> selectForBeanList(Purchase pur){
		String sql="select purid,userid,purdate,purstate from purchase where 1=1 ";
		if(pur!=null) {
			if(pur.getPurstate()!=null && pur.getPurstate()!=-1) {
				sql+=" and purstate="+pur.getPurstate();
			}
		}
		return MySqlHelper.executeQueryForBeanList(sql, Purchase.class);
	}
	
	public int insertSale(Purchase pur) {
		String sql="insert into purchase values(?,?,?,?)";
		return MySqlHelper.executeUpdate(sql, pur.getPurid(),pur.getUserid(),pur.getPurdate(),pur.getPurstate());
	}

	public int deleteSaleById(String purid) {
		String sql="delete from purchase where purid=?";
		return MySqlHelper.executeUpdate(sql, purid);
	}
	
	public void updateForState(String id, Integer state) {
		String sql="update purchase set purstate=? where purid=?";
		MySqlHelper.executeUpdate(sql, state,id);
	}
}
