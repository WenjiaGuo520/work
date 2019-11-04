package edu.etime.yqxdc.pojo;

import java.math.BigDecimal;

public class Goods {
    private String gid;

    private String gtid;

    private String gname;

    private BigDecimal gprice;

    private Integer gstate;

    private String gimg;

    private Integer gtime;

    private String gcontent;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid == null ? null : gid.trim();
    }

    public String getGtid() {
        return gtid;
    }

    public void setGtid(String gtid) {
        this.gtid = gtid == null ? null : gtid.trim();
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname == null ? null : gname.trim();
    }

    public BigDecimal getGprice() {
        return gprice;
    }

    public void setGprice(BigDecimal gprice) {
        this.gprice = gprice;
    }

    public Integer getGstate() {
        return gstate;
    }

    public void setGstate(Integer gstate) {
        this.gstate = gstate;
    }

    public String getGimg() {
        return gimg;
    }

    public void setGimg(String gimg) {
        this.gimg = gimg == null ? null : gimg.trim();
    }

    public Integer getGtime() {
        return gtime;
    }

    public void setGtime(Integer gtime) {
        this.gtime = gtime;
    }

    public String getGcontent() {
        return gcontent;
    }

    public void setGcontent(String gcontent) {
        this.gcontent = gcontent == null ? null : gcontent.trim();
    }
}