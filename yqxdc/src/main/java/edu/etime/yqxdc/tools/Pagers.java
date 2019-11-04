package edu.etime.yqxdc.tools;

import java.util.List;

import com.github.pagehelper.PageInfo;

public class Pagers<T> extends PageInfo<T> {
	private int maxpage;
	private int minpage;
	private int showpcount=10;

	public int getMaxpage() {
		return maxpage;
	}

	public void setMaxpage(int maxpage) {
		this.maxpage = maxpage;
	}

	public int getMinpage() {
		return minpage;
	}

	public void setMinpage(int minpage) {
		this.minpage = minpage;
	}

	public int getShowpcount() {
		return showpcount;
	}

	public void setShowpcount(int showpcount) {
		this.showpcount = showpcount;
	}

	public Pagers(List<T> list) {
		super(list);
		Integer pagecount = super.getPages();
		Integer maxpage = 0;
		Integer minpage = 0;
		Integer index =super.getPageNum();
		if (pagecount <= showpcount) {
			maxpage = pagecount;
			minpage = 1;
		} else {
			Integer buff = showpcount / 2;
			buff = index + buff;
			if (buff <= showpcount) {
				maxpage = showpcount;
				minpage = 1;
			} else if (buff < pagecount) {
				maxpage = buff;
				minpage = maxpage - showpcount + 1;

			} else if (buff >= pagecount) {
				maxpage = pagecount;
				minpage = maxpage - showpcount + 1;
			}
		}
		this.maxpage = maxpage;
		this.minpage = minpage;
	}
}
