package edu.etime.yqxdc.pojo;

public class GoodsType {
    private String gtid;

    private String gtname;

    private Integer gtstate;

    public String getGtid() {
        return gtid;
    }

    public void setGtid(String gtid) {
        this.gtid = gtid == null ? null : gtid.trim();
    }

    public String getGtname() {
        return gtname;
    }

    public void setGtname(String gtname) {
        this.gtname = gtname == null ? null : gtname.trim();
    }

    public Integer getGtstate() {
        return gtstate;
    }

    public void setGtstate(Integer gtstate) {
        this.gtstate = gtstate;
    }
}