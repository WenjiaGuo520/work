package edu.etime.yqxdc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.etime.yqxdc.dao.OrderDetailMapper;
import edu.etime.yqxdc.dto.DetailDto;
import edu.etime.yqxdc.dto.OrderDetailDto;
import edu.etime.yqxdc.services.interfaces.OrderDetailService;

/**
 * 订单详情实现类
 * 
 * @author 孤心
 *
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private OrderDetailMapper odmapper;

	@Override
	public List<OrderDetailDto> selectOrderList(String orderid) {
		return odmapper.selectOrderDetailByOrderId(orderid);
	}

	@Override
	public List<DetailDto> selectDetail(String orderid) {
		return odmapper.selectDetail(orderid);
	}
}
