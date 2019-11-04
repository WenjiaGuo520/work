package edu.etime.yqxdc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.etime.yqxdc.dao.GoodsMapper;
import edu.etime.yqxdc.dao.GoodsTypeMapper;
import edu.etime.yqxdc.dto.GoodsDto;
import edu.etime.yqxdc.dto.MenuDto;
import edu.etime.yqxdc.pojo.Goods;
import edu.etime.yqxdc.pojo.GoodsType;
import edu.etime.yqxdc.services.interfaces.MenuService;

/**
 * 菜单业务逻辑实现类
 * @author 孤心
 *
 */
@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	private GoodsTypeMapper goodstypemapper;
	
	@Autowired
	private GoodsMapper goodsmapper;
	
	@Override
	public MenuDto selectMenu(GoodsType goodstype, Goods goods) {
		List<GoodsType> goodstypelist = goodstypemapper.selectGoodsType(goodstype);
		List<GoodsDto> goodslist = goodsmapper.selectGoods(goods);
		MenuDto dto = new MenuDto();
		dto.setGoodstypelist(goodstypelist);
		dto.setGoodslist(goodslist);
		return dto;
	}

	
	
}
