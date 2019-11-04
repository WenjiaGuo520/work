package edu.etime.yqxdc.controllers.webcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.etime.yqxdc.dto.OrderDetailDto;
import edu.etime.yqxdc.services.interfaces.OrderDetailService;


/**
 * 订单详情
 * @author 孤心
 *
 */
@Controller
@RequestMapping("/order")
public class WebOrderListController {

	@Autowired
	private OrderDetailService service;
	@RequestMapping("/list")
	@ResponseBody
	public String list(OrderDetailDto order,Model model){
		
		return "order/list";
	}
	
}
