package edu.etime.yqxdc.dao;

import edu.etime.yqxdc.pojo.SysLog;

import java.util.List;

public interface SysLogMapper {

    void insertSelective(SysLog log);

    List<SysLog> selectSysLogs();

}