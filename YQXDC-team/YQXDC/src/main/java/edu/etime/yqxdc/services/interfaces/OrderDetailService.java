package edu.etime.yqxdc.services.interfaces;

import java.util.List;

import edu.etime.yqxdc.dto.DetailDto;
import edu.etime.yqxdc.dto.OrderDetailDto;

/**
 * 查询订单详情服务层
 * 
 * @author 孤心
 *
 */
public interface OrderDetailService {

	List<OrderDetailDto> selectOrderList(String orderid);

	List<DetailDto> selectDetail(String orderid);
}
