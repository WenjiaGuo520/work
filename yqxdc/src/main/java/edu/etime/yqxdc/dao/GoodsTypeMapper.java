package edu.etime.yqxdc.dao;

import edu.etime.yqxdc.pojo.GoodsType;

public interface GoodsTypeMapper {
    int deleteByPrimaryKey(String gtid);

    int insert(GoodsType record);

    int insertSelective(GoodsType record);

    GoodsType selectByPrimaryKey(String gtid);

    int updateByPrimaryKeySelective(GoodsType record);

    int updateByPrimaryKey(GoodsType record);
}