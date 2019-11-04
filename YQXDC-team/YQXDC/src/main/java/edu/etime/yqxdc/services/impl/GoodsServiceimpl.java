package edu.etime.yqxdc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import edu.etime.yqxdc.dao.GoodsMapper;
import edu.etime.yqxdc.dto.GoodsDto;
import edu.etime.yqxdc.pojo.Goods;
import edu.etime.yqxdc.pojo.SysRole;
import edu.etime.yqxdc.services.interfaces.GoodsService;
import edu.etime.yqxdc.tools.Pagers;

@Service
public class GoodsServiceimpl implements GoodsService {

	@Autowired
	public GoodsMapper mapper;

	@Override
	public int insertGoods(Goods goods) {
		return mapper.insertSelective(goods);
	}

	@Override
	public List<GoodsDto> selectGoods(Goods goods) {
		return mapper.selectGoods(goods);
	}

	@Override
	public Pagers<GoodsDto> selectSysRolepage(Goods goods, int pagenum, int pagesize) {
		PageHelper.startPage(pagenum,pagesize);
		List<GoodsDto> list =mapper.selectGoods(goods);
		Pagers<GoodsDto> p =new Pagers<>(list);
		return p;
	}

	@Override
	public Goods selectGoodsById(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateGoods(Goods goods) {
		return mapper.updateByPrimaryKey(goods);
	}

    
	
}
