package edu.etime.yqxdc.pojo;

public class SysFunction {
    private String funid;

    private String funname;

    private String funpid;

    private String funurl;

    private Integer funstate;

    public String getFunid() {
        return funid;
    }

    public void setFunid(String funid) {
        this.funid = funid == null ? null : funid.trim();
    }

    public String getFunname() {
        return funname;
    }

    public void setFunname(String funname) {
        this.funname = funname == null ? null : funname.trim();
    }

    public String getFunpid() {
        return funpid;
    }

    public void setFunpid(String funpid) {
        this.funpid = funpid == null ? null : funpid.trim();
    }

    public String getFunurl() {
        return funurl;
    }

    public void setFunurl(String funurl) {
        this.funurl = funurl == null ? null : funurl.trim();
    }

    public Integer getFunstate() {
        return funstate;
    }

    public void setFunstate(Integer funstate) {
        this.funstate = funstate;
    }
}