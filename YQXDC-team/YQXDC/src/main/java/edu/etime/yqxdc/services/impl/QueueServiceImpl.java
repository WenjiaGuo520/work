package edu.etime.yqxdc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.etime.yqxdc.dao.CusOrdersMapper;
import edu.etime.yqxdc.dao.CustomerMapper;
import edu.etime.yqxdc.dao.OrderDetailMapper;
import edu.etime.yqxdc.dto.OrderDetailDto;
import edu.etime.yqxdc.dto.QueueDto;
import edu.etime.yqxdc.pojo.CusOrders;
import edu.etime.yqxdc.pojo.Customer;
import edu.etime.yqxdc.pojo.OrderDetail;
import edu.etime.yqxdc.services.interfaces.QueueService;
/**
 * 排队等候队列服务层
 * @author 孤心
 *
 */
@Service
public class QueueServiceImpl implements QueueService{

	@Autowired
	private CustomerMapper cusmapper;
	@Autowired
	private CusOrdersMapper ordermapper;
	@Autowired
	private OrderDetailMapper detailmapper;
	@Override
	public QueueDto selectQueue(String openid) {
		/*
		 * 1、根据用户openid获取顾客id
		 * 由cusid获取订单信息
		 * 根据订单id获取订单明细
		 * 根据下单时间获取排队信息
		 */
		
		QueueDto dto = null;
		//根据openid获取顾客id
		List<Customer> cuslist = cusmapper.selectCustomerByOpenid(openid);
		if(cuslist.size()>0){
			Customer customer = cuslist.get(0);
			String cusid = customer.getCusid();
			//根据cusid获取订单信息
			List<CusOrders> orderlist = ordermapper.selectOrderByCustomerId(cusid);
			System.out.println("orderlist:"+orderlist);
			
			if(orderlist.size()>0){
				CusOrders order = orderlist.get(0);
				String orderid = order.getOrderid();
				System.out.println("订单号是orderid："+orderid);
				//根据订单号获取订单的详细信息
				List<OrderDetailDto> detaillist = detailmapper.selectOrderDetailByOrderId(orderid);
				//将所有信息放入到dto中
				System.out.println("订单详情列表detaillist:"+detaillist);
				dto = ordermapper.selectQueue(order.getOrdertime().toString());
				dto.setOrderdetail(detaillist);
				dto.setOrders(order);
				System.out.println(dto);
			}
		}
		return dto;
	}

}
