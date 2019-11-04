package edu.etime.yqxdc.dao;

import java.util.List;

import edu.etime.yqxdc.pojo.Customer;

public interface CustomerMapper {
    int deleteByPrimaryKey(String cusid);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(String cusid);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
    
    List<Customer> selectCustomerByOpenid(String openid); 
    /**
     * 根据主键查询客户端信息
     * @param cusid
     * @return
     */
    List<Customer> selectByPrimarkey(String cusid);
}