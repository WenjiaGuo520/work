package edu.etime.yqxdc.dto;

import edu.etime.yqxdc.pojo.SysFunction;

/**
 * created by 1
 */
public class SysFunctionDto extends SysFunction {
    private Integer isUsed;//1 为角色已拥有 0 为未拥有

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }
}
