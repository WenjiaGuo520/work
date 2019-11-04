package edu.etime.yqxdc.services.interfaces;

import java.util.List;

import edu.etime.yqxdc.dto.CusOrderDto;
import edu.etime.yqxdc.dto.GoodsDto;
import edu.etime.yqxdc.pojo.CusOrders;
import edu.etime.yqxdc.tools.Pagers;

/**
 * 订单业务逻辑层
 * @author 孤心
 *
 */
public interface OrderService {

	/**
	 * 下订单
	 * @param openid
	 * @param goodslist
	 * @param totleprice
	 * @return
	 */
	int inserOrder(List<GoodsDto> goodslist,String openid,Double totleprice);
	/**
	 * 查询订单列表
	 * @param dto
	 * @return
	 */
	List<CusOrderDto> selectCusOrderList(CusOrderDto dto);
	
	/**
	 * 带分页的查询
	 *
	 * @param pagenum
	 * @param pagesize
	 * @return
	 */
	Pagers<CusOrderDto> selectCusOrderPage(CusOrderDto cusorder,int pagenum,int pagesize);

	/**
	 * 更新信息
	 * @param order
	 * @return
	 */
	int UpdateCusOrder(CusOrders order);

	/**
	 * 查询用户id
	 * @param cusid
	 * @return
	 */
	CusOrders selectById(String cusid);
}
