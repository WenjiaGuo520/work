package edu.etime.yqxdc.services.interfaces;

import edu.etime.yqxdc.pojo.SysLog;
import edu.etime.yqxdc.tools.Pagers;

/**
 * 日志记录服务层接口
 */
public interface SysLogService {


    void addSysLog(SysLog log);

    Pagers<SysLog> page(Integer index, Integer pageSize);
}
