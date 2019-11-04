package edu.etime.yqxdc.dao;

import java.util.List;

import edu.etime.yqxdc.dto.SysFunctionDto;
import edu.etime.yqxdc.dto.SysRoleDto;
import edu.etime.yqxdc.pojo.RoleRight;
import edu.etime.yqxdc.pojo.SysRole;
import org.apache.ibatis.annotations.Param;

public interface SysRoleMapper {
    int deleteByPrimaryKey(String roleid);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String roleid);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> selectSysRoleList(SysRole role);

    SysRoleDto selectByPrimaryKeyWithFunctions(String roleid);

    SysRole selectCountByRoleName(String rolename);

    void updateStateById(String id);

    List<SysFunctionDto> selectFunctions();

    void deleteRightByRoleId(String roleid);

    void insertRoleRight(RoleRight roleRight);
}