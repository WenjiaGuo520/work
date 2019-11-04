package edu.etime.yqxdc.dto;

import edu.etime.yqxdc.pojo.SysFunction;
import edu.etime.yqxdc.pojo.SysRole;

import java.util.List;

/**
 * created by 1
 */
public class SysRoleDto extends SysRole {

    private List<SysFunction> functions;

    public List<SysFunction> getFunctions() {
        return functions;
    }

    public void setFunctions(List<SysFunction> functions) {
        this.functions = functions;
    }
}
