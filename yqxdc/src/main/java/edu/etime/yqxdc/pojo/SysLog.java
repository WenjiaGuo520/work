package edu.etime.yqxdc.pojo;

import java.util.Date;

public class SysLog {
    private String lid;

    private Date visittime;

    private String username;

    private String ip;

    private String url;

    private Integer executiontime;

    private String method;

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public Date getVisittime() {
        return visittime;
    }

    public void setVisittime(Date visittime) {
        this.visittime = visittime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getExecutiontime() {
        return executiontime;
    }

    public void setExecutiontime(Integer executiontime) {
        this.executiontime = executiontime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}