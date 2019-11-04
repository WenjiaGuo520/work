package edu.etime.yqxdc.services.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import edu.etime.yqxdc.dao.CusOrdersMapper;
import edu.etime.yqxdc.dao.CustomerMapper;
import edu.etime.yqxdc.dao.OrderDetailMapper;
import edu.etime.yqxdc.dto.CusOrderDto;
import edu.etime.yqxdc.dto.GoodsDto;
import edu.etime.yqxdc.pojo.CusOrders;
import edu.etime.yqxdc.pojo.Customer;
import edu.etime.yqxdc.pojo.OrderDetail;
import edu.etime.yqxdc.services.interfaces.CustomerService;
import edu.etime.yqxdc.services.interfaces.OrderService;
import edu.etime.yqxdc.tools.Pagers;

/**
 * 下订单业务逻辑实现类
 * 
 * @author 孤心
 *
 */
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private CustomerMapper cusmapper;
	@Autowired
	private CusOrdersMapper ordermapper;
	@Autowired
	private OrderDetailMapper orderdetailmapper;

	@Override
	public int inserOrder(List<GoodsDto> goodslist, String openid, Double totalprice) {
		int rtn = 1;
		// 根据openid获取用户信息
		List<Customer> cuslist = cusmapper.selectCustomerByOpenid(openid);
		System.out.println("顾客idcuslist:" + cuslist);
		if (cuslist.size() > 0) {
			Customer cus = cuslist.get(0);
			String cusid = cus.getCusid();
			String orderid = UUID.randomUUID().toString();
			// 创建订单
			CusOrders orders = new CusOrders();
			orders.setOrderid(orderid);
			orders.setCusid(cusid);
			orders.setOrderstate(1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			orders.setOrdertotleprice(new BigDecimal(totalprice));
			Date date = new Date();

			// System.out.println(date);
			String time = sdf.format(date);
			System.out.println(sdf.format(date));
			orders.setOrdertime(time);
			rtn = ordermapper.insert(orders);
			System.out.println(rtn);

			// 遍历商品列表
			if (rtn > 0) {
				// 若商品列表不为空，就显示点餐的详细信息
				for (GoodsDto goods : goodslist) {
					if (goods.getGcount() > 0) {
						OrderDetail detail = new OrderDetail();
						detail.setOdid(UUID.randomUUID().toString());
						detail.setGid(goods.getGid());
						detail.setOdcount(new BigDecimal(goods.getGcount()));
						detail.setOdprice(goods.getGprice());
						detail.setOrderid(orderid);
						rtn = orderdetailmapper.insert(detail);
						System.out.println(rtn);
						if (rtn < 1) {
							break;
						}
					}
				}
			} else {
				rtn = 2;
			}
		}
		return rtn;
	}

	@Override
	public List<CusOrderDto> selectCusOrderList(CusOrderDto dto) {
		return ordermapper.selectCusOrder(dto);
	}

	@Override
	public Pagers<CusOrderDto> selectCusOrderPage(CusOrderDto cusorder, int pagenum, int pagesize) {
		PageHelper.startPage(pagenum, pagesize);
		List<CusOrderDto> list = ordermapper.selectCusOrder(cusorder);
		for (CusOrderDto order : list) {
			System.out.println(order);
		}
		Pagers<CusOrderDto> p = new Pagers<>(list);
		return p;
	}

	/**
	 * 更新订单状态
	 * 
	 * @param order
	 * @return
	 */
	@Override
	public int UpdateCusOrder(CusOrders order) {
		return ordermapper.updateByPrimaryKey(order);
	}

	@Override
	public CusOrders selectById(String cusid) {
		return ordermapper.selectByPrimaryKey(cusid);
	}
}
