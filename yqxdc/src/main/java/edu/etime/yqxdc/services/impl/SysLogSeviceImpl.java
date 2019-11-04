package edu.etime.yqxdc.services.impl;

import com.github.pagehelper.PageHelper;
import edu.etime.yqxdc.dao.SysLogMapper;
import edu.etime.yqxdc.pojo.SysLog;
import edu.etime.yqxdc.services.interfaces.SysLogService;
import edu.etime.yqxdc.tools.Pagers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * created by 1
 */
@Service
public class SysLogSeviceImpl implements SysLogService {
    @Autowired
    private SysLogMapper mapper;

    @Transactional
    @Override
    public void addSysLog(SysLog log) {
        mapper.insertSelective(log);
    }

    @Override
    public Pagers<SysLog> page(Integer index, Integer pageSize) {
        PageHelper.startPage(index,pageSize);
        List<SysLog> list = mapper.selectSysLogs();
        Pagers<SysLog> pagers = new Pagers<>(list);
        return pagers;
    }
}
