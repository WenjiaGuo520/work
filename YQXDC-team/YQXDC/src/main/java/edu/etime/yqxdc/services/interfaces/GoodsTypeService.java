package edu.etime.yqxdc.services.interfaces;

import java.util.List;

import edu.etime.yqxdc.pojo.GoodsType;
import edu.etime.yqxdc.tools.Pagers;

public interface GoodsTypeService {
	/**
	 * 查询商品类型
	 * 
	 * @param goodstype
	 * @return
	 */
	List<GoodsType> selectGoodsTypeList(GoodsType gt);


	/*
	 * 添加菜品类型信息
	 */
	int add(GoodsType gt);

	/*
	 * 通过id查询菜品类型信息
	 */
	GoodsType selectGoodsTypeById(String gtid);

	/*
	 * 修改菜品类型信息
	 */
	int updateGoodsType(GoodsType gt);

	Pagers<GoodsType> selectPages(GoodsType gt, Integer index, Integer pageSize);

}
