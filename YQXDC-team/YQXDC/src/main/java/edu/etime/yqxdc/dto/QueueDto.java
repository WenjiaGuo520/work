package edu.etime.yqxdc.dto;

import java.util.List;
import edu.etime.yqxdc.pojo.CusOrders;
/**
 * 订单队列数据传输对象
 * @author 孤心
 *
 */

public class QueueDto {

	
	//我的订单信息
	private CusOrders orders;
	//我的订单明细
	private List<OrderDetailDto> orderdetail;
	//队列位置
	private Integer queuecount;
	//等待时间
	private Integer queuetime;
	public CusOrders getOrders() {
		return orders;
	}
	public void setOrders(CusOrders orders) {
		this.orders = orders;
	}
	public List<OrderDetailDto> getOrderdetail() {
		return orderdetail;
	}
	public void setOrderdetail(List<OrderDetailDto> orderdetail) {
		this.orderdetail = orderdetail;
	}
	public Integer getQueuecount() {
		return queuecount;
	}
	public void setQueuecount(Integer queuecount) {
		this.queuecount = queuecount;
	}
	public Integer getQueuetime() {
		return queuetime;
	}
	public void setQueuetime(Integer queuetime) {
		this.queuetime = queuetime;
	}
	@Override
	public String toString() {
		return "QueueDto [orders=" + orders + ", orderdetail=" + orderdetail + ", queuecount=" + queuecount
				+ ", queuetime=" + queuetime + "]";
	}
}
