package edu.etime.yqxdc.dao;

import edu.etime.yqxdc.dto.SysUserDto;
import edu.etime.yqxdc.pojo.SysUser;

import java.util.List;

public interface SysUserMapper {
    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUserDto selectByUserName(String username);

    List<SysUser> selectSysUserList(SysUser sysUser);

    SysUserDto selectByPrimaryKeyWithRoles(String userid);

    void updateSysUserStateById(String id);

    void updateUserPwd(SysUser sysUser);

    SysUser selectByUserNameForSysUser(String username);
}