package edu.etime.yqxdc.services.interfaces;
/**
 * 客户服务层
 * @author 孤心
 *
 */

import java.util.List;

import edu.etime.yqxdc.pojo.Customer;

public interface CustomerService {
	/**
	 * 根据ID查询客户
	 * @param openid
	 * @return
	 */
	List<Customer> selectCusByOpenid(String openid);
	/**
	 * 增加客户
	 * @param cus
	 * @return
	 */
	int insertCustomer(Customer cus);
	
	//Customer selectCustome(String openid);
}
