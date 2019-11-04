package edu.etime.yqxdc.dao;

import java.util.List;

import edu.etime.yqxdc.dto.GoodsDto;
import edu.etime.yqxdc.pojo.Goods;

public interface GoodsMapper {
    int deleteByPrimaryKey(String gid);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(String gid);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);
    
    /**
     * ��ѯ��Ʒ�б�ķ���
     * @param goods
     * @return
     */
    List<GoodsDto> selectGoods(Goods goods);
}