package edu.etime.yqxdc.dao;

import edu.etime.yqxdc.pojo.RoleRight;

public interface RoleRightMapper {
    int deleteByPrimaryKey(String rrid);

    int insert(RoleRight record);

    int insertSelective(RoleRight record);

    RoleRight selectByPrimaryKey(String rrid);

    int updateByPrimaryKeySelective(RoleRight record);

    int updateByPrimaryKey(RoleRight record);
}