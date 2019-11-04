package edu.etime.yqxdc.dto;

import java.util.List;

import edu.etime.yqxdc.pojo.GoodsType;

/**
 * 菜品传输数据实体类
 * @author 孤心
 *
 */
public class MenuDto {

	private List<GoodsType> goodstypelist;
	
	private List<GoodsDto> goodslist;

	public List<GoodsType> getGoodstypelist() {
		return goodstypelist;
	}

	public void setGoodstypelist(List<GoodsType> goodstypelist) {
		this.goodstypelist = goodstypelist;
	}

	public List<GoodsDto> getGoodslist() {
		return goodslist;
	}

	public void setGoodslist(List<GoodsDto> goodslist) {
		this.goodslist = goodslist;
	}

	@Override
	public String toString() {
		return "MenuDto [goodstypelist=" + goodstypelist + ", goodslist=" + goodslist + "]";
	}	
}
