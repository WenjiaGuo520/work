package edu.etime.yqxdc.dao;

import java.util.List;

import edu.etime.yqxdc.dto.DetailDto;
import edu.etime.yqxdc.dto.OrderDetailDto;
import edu.etime.yqxdc.pojo.OrderDetail;

public interface OrderDetailMapper {
	int deleteByPrimaryKey(String odid);

	int insert(OrderDetail record);

	int insertSelective(OrderDetail record);

	OrderDetail selectByPrimaryKey(String odid);

	int updateByPrimaryKeySelective(OrderDetail record);

	int updateByPrimaryKey(OrderDetail record);

	/**
	 * 查询订单详情列表
	 * 
	 * @param orderid
	 * @return
	 */
	List<OrderDetailDto> selectOrderDetailByOrderId(String orderid);

	/*
	 * 查询订单详细
	 */
	List<DetailDto> selectDetail(String orderid);
}