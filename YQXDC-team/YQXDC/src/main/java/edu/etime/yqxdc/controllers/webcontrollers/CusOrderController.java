package edu.etime.yqxdc.controllers.webcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.etime.yqxdc.dto.CusOrderDto;
import edu.etime.yqxdc.dto.DetailDto;
import edu.etime.yqxdc.pojo.CusOrders;
import edu.etime.yqxdc.services.interfaces.OrderDetailService;
import edu.etime.yqxdc.services.interfaces.OrderService;
import edu.etime.yqxdc.services.interfaces.QueueService;
import edu.etime.yqxdc.tools.Pagers;

/**
 * 客户订单信息
 * 
 * @author 孤心
 *
 */
@Controller
@RequestMapping("/cusorder")
public class CusOrderController {
	@Autowired
	private OrderService service;

	@Autowired
	private QueueService queueService;
	@Autowired
	private OrderDetailService detailservice;

	/**
	 * 分页查询订单列表
	 * 
	 * @param tag
	 * @param index
	 * @param order
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String listOrder(Integer tag, Integer index, CusOrderDto order, Model model) {
		if (index == null) {
			index = 1;
		}
		if (tag != null && tag != -1) {
			System.out.println("完成");
			order.setOrderstate(3);

		} else {
			order.setOrderstate(null);
		}
		Pagers<CusOrderDto> p = service.selectCusOrderPage(order, index, 10);
		model.addAttribute("p", p);
		return "cusorder/list";

	}

	/**
	 * 修改状态
	 * 
	 * @param orderid
	 * @param tag
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit/{orderid}/{tag}")
	public String edit(@PathVariable(value = "orderid") String orderid, @PathVariable(value = "tag") String tag,
			Model model) {
		CusOrders order = service.selectById(orderid);
		if (tag.equals("2")) {
			order.setOrderstate(Integer.parseInt(tag));
		} else if (tag.equals("3")) {
			order.setOrderstate(Integer.parseInt(tag));
		}
		int rtn = service.UpdateCusOrder(order);
		order = service.selectById(orderid);
		if (rtn > 0) {
			model.addAttribute("msg", "修改成功");
			return "redirect:/cusorder/list";
		} else {
			model.addAttribute("msg", "修改失败");
			return "/cusorder/list";
		}
	}

	@RequestMapping("/detail/{orderid}")
	public String detail(@PathVariable(value="orderid")String orderid, Model model) {
		List<DetailDto> list = detailservice.selectDetail(orderid);
		model.addAttribute("list", list);
		return "detail/detail";

	}

	/*
	 * @RequestMapping("/list") private String list(CusOrderDto dto, Model
	 * model,Integer index){ if(index == null){ index = 1; } List<CusOrderDto>
	 * orderlist = service.selectCusOrderList(dto); Pagers<CusOrderDto> p =
	 * service.selectCusOrderPage(dto, index, 8);
	 * model.addAttribute("orderlist", orderlist); model.addAttribute("p", p);
	 * System.out.println("orderlist:"+orderlist); return "cusorder/list"; }
	 */
	/*
	 * @RequestMapping("/toedit/{id}") public String toedit(@PathVariable(value
	 * = "id") String id, Model model){ CusOrders role = service.selectById(id);
	 * model.addAttribute("role", role); System.out.println(id); return
	 * "sysrole/edit";
	 * 
	 * }
	 */
	/**
	 * 更新信息
	 * 
	 * @param order
	 * @param model
	 * @return
	 */
	@RequestMapping("/update")
	private String edit(CusOrders order, Model model) {
		int rtn = service.UpdateCusOrder(order);
		if (rtn > 0) {
			model.addAttribute("type", "success");
			model.addAttribute("msg", "更新信息成功");
			return "redirect:/cusorder/list";
		} else {
			model.addAttribute("type", "error");
			return "cusorder/edit";
		}

	}

}
