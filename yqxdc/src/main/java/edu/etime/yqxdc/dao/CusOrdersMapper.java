package edu.etime.yqxdc.dao;

import edu.etime.yqxdc.pojo.CusOrders;

public interface CusOrdersMapper {
    int deleteByPrimaryKey(String orderid);

    int insert(CusOrders record);

    int insertSelective(CusOrders record);

    CusOrders selectByPrimaryKey(String orderid);

    int updateByPrimaryKeySelective(CusOrders record);

    int updateByPrimaryKey(CusOrders record);
}