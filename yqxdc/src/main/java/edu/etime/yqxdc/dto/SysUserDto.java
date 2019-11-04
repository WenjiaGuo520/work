package edu.etime.yqxdc.dto;

import edu.etime.yqxdc.pojo.SysRole;
import edu.etime.yqxdc.pojo.SysUser;

import java.util.List;

/**
 * SysUser的扩展类
 */
public class SysUserDto extends SysUser {

    private List<SysRole> roles;

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}
