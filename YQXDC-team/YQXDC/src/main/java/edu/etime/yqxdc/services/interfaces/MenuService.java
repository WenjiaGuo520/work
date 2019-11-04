package edu.etime.yqxdc.services.interfaces;

import edu.etime.yqxdc.dto.MenuDto;
import edu.etime.yqxdc.pojo.Goods;
import edu.etime.yqxdc.pojo.GoodsType;

/**
 * 菜单业务逻辑层
 * @author 孤心
 *
 */
public interface MenuService {
	/**
	 * 查询菜单
	 * @param goodstype
	 * @param goods
	 * @return
	 */
	MenuDto selectMenu(GoodsType goodstype,Goods goods);

}
