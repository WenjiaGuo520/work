package edu.etime.yqxdc.services.impl;

import com.github.pagehelper.PageHelper;
import edu.etime.yqxdc.dao.SysRoleMapper;
import edu.etime.yqxdc.dto.SysFunctionDto;
import edu.etime.yqxdc.dto.SysRoleDto;
import edu.etime.yqxdc.pojo.RoleRight;
import edu.etime.yqxdc.pojo.SysFunction;
import edu.etime.yqxdc.pojo.SysRole;
import edu.etime.yqxdc.services.interfaces.SysRoleService;
import edu.etime.yqxdc.tools.Pagers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * created by 1
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper mapper;

    @Override
    public Pagers<SysRole> page(SysRole role, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysRole> sysRoles = mapper.selectSysRoleList(role);
        Pagers<SysRole> pagers = new Pagers<>(sysRoles);
        return pagers;
    }

    @Override
    public List<SysRole> findAll() {
        return mapper.selectSysRoleList(new SysRole());
    }

    @Transactional
    @Override
    public void add(SysRole role) throws Exception {
        role.setRoleid(UUID.randomUUID().toString());
        mapper.insertSelective(role);
    }

    @Override
    public SysRoleDto findRoleDtoById(String roleid) {
        return mapper.selectByPrimaryKeyWithFunctions(roleid);
    }

    @Override
    public boolean checkName(String rolename) {
        SysRole role = mapper.selectCountByRoleName(rolename);
        return role==null?true:false;
    }

    @Transactional
    @Override
    public void updateStateById(String[] ids) throws Exception {
        for (String id : ids) {
            mapper.updateStateById(id);
        }
    }

    @Override
    public SysRoleDto selectSysRoleDto(String id) {
        return mapper.selectByPrimaryKeyWithFunctions(id);
    }

    @Override
    public SysRole findRoleById(String id) {
        return mapper.selectByPrimaryKey(id);
    }


    @Override
    public List<SysFunctionDto> selectFunctionsWithUsestate(String id) {
        List<SysFunctionDto> funs = mapper.selectFunctions();
        List<SysFunction> hasFuns = mapper.selectByPrimaryKeyWithFunctions(id).getFunctions();
        for (SysFunctionDto fun : funs) {
            for (SysFunction hasFun : hasFuns) {
                if(hasFun.getFunid().equals(fun.getFunid())){
                    fun.setIsUsed(1);
                }
            }
        }
        return funs;
    }

    @Transactional
    @Override
    public void editRoleAndRoleRight(SysRole role, String[] funids, String editFunState) throws Exception {
        //修改角色信息
        mapper.updateByPrimaryKeySelective(role);
        //判断系统功能树是否改变
        if (editFunState.equals("1") && funids!=null){
            //删除当前角色所有权限
            mapper.deleteRightByRoleId(role.getRoleid());
            //添加权限
            for (String funid : funids) {
                RoleRight roleRight = new RoleRight();
                roleRight.setRoleid(role.getRoleid());
                roleRight.setFunid(funid);
                roleRight.setRrid(UUID.randomUUID().toString());
                mapper.insertRoleRight(roleRight);
            }
        }

    }
}
