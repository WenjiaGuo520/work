package edu.etime.yqxdc.pojo;

import java.math.BigDecimal;

public class OrderDetail {
    private String odid;

    private String gid;

    private String orderid;

    private BigDecimal odprice;

    private BigDecimal odcount;

    public String getOdid() {
        return odid;
    }

    public void setOdid(String odid) {
        this.odid = odid == null ? null : odid.trim();
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid == null ? null : gid.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public BigDecimal getOdprice() {
        return odprice;
    }

    public void setOdprice(BigDecimal odprice) {
        this.odprice = odprice;
    }

    public BigDecimal getOdcount() {
        return odcount;
    }

    public void setOdcount(BigDecimal odcount) {
        this.odcount = odcount;
    }
}