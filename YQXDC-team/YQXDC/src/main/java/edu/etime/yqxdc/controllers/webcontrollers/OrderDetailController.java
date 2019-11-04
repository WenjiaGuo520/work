package edu.etime.yqxdc.controllers.webcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.etime.yqxdc.dto.DetailDto;
import edu.etime.yqxdc.services.interfaces.OrderDetailService;

@Controller
@RequestMapping("web/order")
public class OrderDetailController {
	@Autowired
	private OrderDetailService detailservice;
	@RequestMapping("/detail")
	public String detail(String id,Model model){
		List<DetailDto> list =detailservice.selectDetail(id);
		model.addAttribute("list", list);
		return "detail/detail";
		
	}
}
