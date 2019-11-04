package edu.etime.yqxdc.controllers.wxcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.etime.yqxdc.dto.MenuDto;
import edu.etime.yqxdc.pojo.Goods;
import edu.etime.yqxdc.pojo.GoodsType;
import edu.etime.yqxdc.services.interfaces.MenuService;

@Controller
@RequestMapping("/wx")
public class WXMenuController {

	@Autowired
	private MenuService service;
	@RequestMapping("/menu")
	@ResponseBody
	public MenuDto menu(GoodsType goodstype,Goods goods){
		goodstype.setGtstate(1);
		goods.setGstate(1);
		MenuDto dto = service.selectMenu(goodstype, goods);
		System.out.println(dto);
		return dto;
	}
}
