package edu.etime.yqxdc.test;

import edu.etime.yqxdc.dao.SysFunctionMapper;
import edu.etime.yqxdc.dao.SysRoleMapper;
import edu.etime.yqxdc.dao.SysUserMapper;
import edu.etime.yqxdc.dto.SysRoleDto;
import edu.etime.yqxdc.pojo.SysFunction;
import edu.etime.yqxdc.pojo.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * created by 1
 */
@ContextConfiguration(locations = "classpath:spring-dao.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDao {
    @Autowired
    private SysRoleMapper mapper;

    @Test
    public void testDao(){
        SysRoleDto sysRoleDto = mapper.selectByPrimaryKeyWithFunctions("9b620176-f6d9-49dd-949c-b43f0dfa2edc");
        System.out.println(sysRoleDto.getFunctions().size());
    }
}
