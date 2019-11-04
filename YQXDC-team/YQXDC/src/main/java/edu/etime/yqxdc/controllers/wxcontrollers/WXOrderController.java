package edu.etime.yqxdc.controllers.wxcontrollers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import edu.etime.yqxdc.dto.GoodsDto;
import edu.etime.yqxdc.dto.OrderDetailDto;
import edu.etime.yqxdc.dto.QueueDto;
import edu.etime.yqxdc.services.interfaces.OrderService;
import edu.etime.yqxdc.services.interfaces.QueueService;

/**
 * 微信端的订单接口
 * @author 孤心
 *
 */
@Controller
@RequestMapping("/wx/order")
public class WXOrderController {

	@Autowired
	private OrderService orderservce;
	@Autowired
	private QueueService queueservice;
	/**
	 *创建订单
	 * @param list
	 * @param openid
	 * @param totleprice
	 * @return
	 */
	@RequestMapping(method={RequestMethod.POST},value="/add")
	@ResponseBody
	public int addorder(String goodslist,String openid,Double totleprice){
			openid = openid.replaceAll("\\r\\n", "");
		  System.out.println("订单详情。。。。"+totleprice);
		  System.out.println("订单详情。。。。。。openid:"+openid);
		  System.out.println("订单详情。。。。。。list:"+goodslist);
		 
		  List<GoodsDto> goodsDtos = JSONObject.parseArray(goodslist,GoodsDto.class);
		
		return orderservce.inserOrder(goodsDtos, openid, totleprice);
	}
	/**
	 *  获取订单排队
	 * @param openid
	 * @return
	 */
	@RequestMapping("/queue")
	@ResponseBody
	public  QueueDto queue(String openid){
		System.out.println("排队等候。。。。。。"+openid);
		QueueDto selectQueue = queueservice.selectQueue(openid);
		return selectQueue;
	}
	
	
}
