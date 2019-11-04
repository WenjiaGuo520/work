package edu.etime.yqxdc.dao;

import edu.etime.yqxdc.pojo.SysFunction;

import java.util.List;

public interface SysFunctionMapper {
    int deleteByPrimaryKey(String funid);

    int insert(SysFunction record);

    int insertSelective(SysFunction record);

    SysFunction selectByPrimaryKey(String funid);

    int updateByPrimaryKeySelective(SysFunction record);

    int updateByPrimaryKey(SysFunction record);

    List<SysFunction> selectFunsByRoleId(String roleId);

    List<SysFunction> selectFuns();

    void updateStateById(String funid);
}