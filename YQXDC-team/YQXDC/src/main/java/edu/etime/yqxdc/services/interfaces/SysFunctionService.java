package edu.etime.yqxdc.services.interfaces;

import edu.etime.yqxdc.pojo.SysFunction;

import java.util.List;

/**
 * created by 1
 */
public interface SysFunctionService {

    List<SysFunction> findAll();

    void add(SysFunction function) throws Exception;

    void updateStateById(String[] funids) throws Exception;
}
