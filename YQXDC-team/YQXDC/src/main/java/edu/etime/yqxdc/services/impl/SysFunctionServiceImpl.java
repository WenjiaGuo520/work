package edu.etime.yqxdc.services.impl;

import edu.etime.yqxdc.dao.SysFunctionMapper;
import edu.etime.yqxdc.pojo.SysFunction;
import edu.etime.yqxdc.services.interfaces.SysFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * created by 1
 */
@Service
public class SysFunctionServiceImpl implements SysFunctionService {

    @Autowired
    private SysFunctionMapper mapper;
    @Override
    public List<SysFunction> findAll() {
        return mapper.selectFuns();
    }
    @Transactional
    @Override
    public void add(SysFunction function) throws Exception {
        function.setFunid(UUID.randomUUID().toString());
        function.setFunstate(1);
        mapper.insertSelective(function);
    }

    @Override
    public void updateStateById(String[] funids) throws Exception {
        if(funids!=null && funids.length>0){

            for (String funid : funids) {
                mapper.updateStateById(funid);
            }
        }
    }
}
