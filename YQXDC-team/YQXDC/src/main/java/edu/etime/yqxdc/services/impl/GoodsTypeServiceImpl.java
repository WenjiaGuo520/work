package edu.etime.yqxdc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import edu.etime.yqxdc.dao.GoodsTypeMapper;
import edu.etime.yqxdc.pojo.GoodsType;
import edu.etime.yqxdc.services.interfaces.GoodsTypeService;
import edu.etime.yqxdc.tools.Pagers;
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService{

	@Autowired
	private GoodsTypeMapper goodstypemapper;

	@Override
	public List<GoodsType> selectGoodsTypeList(GoodsType gt) {
		return goodstypemapper.selectGoodsTypeList(gt);
	}

	@Override
	public int add(GoodsType gt) {
		return goodstypemapper.insert(gt);
	}

	@Override
	public GoodsType selectGoodsTypeById(String gtid) {
		return goodstypemapper.selectByPrimaryKey(gtid);
	}

	@Override
	public int updateGoodsType(GoodsType gt) {
		return goodstypemapper.updateByPrimaryKey(gt);
	}

	@Override
	public Pagers<GoodsType> selectPages(GoodsType gt, Integer index, Integer pageSize) {
		PageHelper.startPage(index,pageSize);
		List<GoodsType> list = goodstypemapper.selectGoodsTypeList(gt);
		Pagers<GoodsType> p = new Pagers<>(list);
		return p;
	}

}
