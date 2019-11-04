package edu.etime.yqxdc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.etime.yqxdc.dao.CustomerMapper;
import edu.etime.yqxdc.pojo.Customer;
import edu.etime.yqxdc.services.interfaces.CustomerService;

/**
 * 客户实现类
 * @author 孤心
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerMapper mapper;
	@Override
	public int insertCustomer(Customer cus) {
		return mapper.insert(cus);
	}
	@Override
	public List<Customer> selectCusByOpenid(String openid) {
		
		return mapper.selectByPrimarkey(openid);
	}
//	@Override
//	public List<Customer> selectCusByOpenid(String openid) {
//		
//		return mapper.selectByPrimarkey(openid);
//	}

//	@Override
//	public int insertCustomer(Customer cus) {
//		
//		return mapper.insert(cus);
//	}

//	@Override
//	public Customer selectCustome(String openid) {
//		
//		return mapper.selectByPrimaryKey(openid);
//	}

}
