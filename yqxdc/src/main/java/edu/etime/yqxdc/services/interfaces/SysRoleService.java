package edu.etime.yqxdc.services.interfaces;

import edu.etime.yqxdc.dto.SysFunctionDto;
import edu.etime.yqxdc.dto.SysRoleDto;
import edu.etime.yqxdc.pojo.SysRole;
import edu.etime.yqxdc.tools.Pagers;

import java.util.List;

/**
 * created by 1
 */
public interface SysRoleService {

    Pagers<SysRole> page(SysRole role,Integer pageNum,Integer pageSize);

    List<SysRole> findAll();

    void add(SysRole role) throws Exception;

    SysRoleDto findRoleDtoById(String roleid);

    /**
     * 校验角色名是否存在
     * @param rolename
     * @return
     */
    boolean checkName(String rolename);

    void updateStateById(String[] ids) throws Exception;

    /**
     * 查询角色封装类Dto
     * @param id
     * @return
     */
    SysRoleDto selectSysRoleDto(String id);

    SysRole findRoleById(String id);

    /**
     * 根据角色id给查询到的功能赋一个使用状态
     * @param id
     * @return
     */
    List<SysFunctionDto> selectFunctionsWithUsestate(String id);

    /**
     * 修改角色以及该角色的功能
     * @param role
     * @param funids
     * @param editFunState
     */
    void editRoleAndRoleRight(SysRole role, String[] funids, String editFunState)throws Exception;
}
