package edu.etime.yqxdc.dto;

import edu.etime.yqxdc.pojo.Goods;

public class GoodsDto extends Goods {

	private String gtname;
	//商品总数
	private Integer gcount = 0;

	public String getGtname() {
		return gtname;
	}

	public void setGtname(String gtname) {
		this.gtname = gtname;
	}

	public Integer getGcount() {
		return gcount;
	}

	public void setGcount(Integer gcount) {
		this.gcount = gcount;
	}
	
	
}
