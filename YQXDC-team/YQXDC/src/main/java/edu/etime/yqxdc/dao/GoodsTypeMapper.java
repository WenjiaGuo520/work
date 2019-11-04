package edu.etime.yqxdc.dao;

import java.util.List;

import edu.etime.yqxdc.pojo.GoodsType;

public interface GoodsTypeMapper {
	int deleteByPrimaryKey(String gtid);

	int insert(GoodsType record);

	int insertSelective(GoodsType record);

	GoodsType selectByPrimaryKey(String gtid);

	int updateByPrimaryKeySelective(GoodsType record);

	int updateByPrimaryKey(GoodsType record);

	/**
	 * 查询类型详细
	 * 
	 * @param gt
	 * @return
	 */
	List<GoodsType> selectGoodsTypeList(GoodsType gt);

	/**
	 * 微信端查询类型列表
	 * 
	 * @param gt
	 * @return
	 */
	List<GoodsType> selectGoodsType(GoodsType gt);

}