package edu.etime.yqxdc.dao;

import java.util.List;

import edu.etime.yqxdc.dto.CusOrderDto;
import edu.etime.yqxdc.dto.QueueDto;
import edu.etime.yqxdc.pojo.CusOrders;

public interface CusOrdersMapper {
	int deleteByPrimaryKey(String orderid);

	int insert(CusOrders record);

	int insertSelective(CusOrders record);

	CusOrders selectByPrimaryKey(String orderid);

	int updateByPrimaryKeySelective(CusOrders record);

	int updateByPrimaryKey(CusOrders record);

	/**
	 * 通过客户ID查询订单列表
	 * 
	 * @param cusid
	 * @return
	 */
	List<CusOrders> selectOrderByCustomerId(String cusid);

	/**
	 * 查询等候队列
	 * 
	 * @param date
	 * @return
	 */
	QueueDto selectQueue(String date);

	/**
	 * 查询订单信息
	 * 
	 * @param dto
	 * @return
	 */
	List<CusOrderDto> selectCusOrder(CusOrderDto dto);
}