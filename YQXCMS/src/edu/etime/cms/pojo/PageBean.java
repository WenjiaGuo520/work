package edu.etime.cms.pojo;

import java.util.List;

/**
 * 分页数据 封装类
 * @author 1
 *
 */
public class PageBean<T> {
    private Integer currentPage;//当前页
    private Integer totalPage;//总页数
    private Integer totalCount;//总记录数
    private Integer rows;//每页的记录数

    private List<T> list;//每页的数据

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", rows=" + rows +
                ", list=" + list +
                '}';
    }
}

