package edu.etime.yqxdc.services.impl;

import com.github.pagehelper.PageHelper;
import edu.etime.yqxdc.dao.SysRoleMapper;
import edu.etime.yqxdc.dao.SysUserMapper;
import edu.etime.yqxdc.dto.SysUserDto;
import edu.etime.yqxdc.pojo.SysRole;
import edu.etime.yqxdc.pojo.SysUser;
import edu.etime.yqxdc.services.interfaces.SysUserService;
import edu.etime.yqxdc.tools.BCryptPasswordEncoderUtils;
import edu.etime.yqxdc.tools.Pagers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * created by 1
 */
@Service("userService")
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper mapper;

    /**
     * 用户认证
     *      根据用户名，查出这个账户的密码，权限和表单输入的用户名、密码比较成功就将该用户信息存入principal中
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //System.out.println("loadUserByUsername.....");
        SysUserDto sysUser = mapper.selectByUserName(username);
        User user = new User(sysUser.getUsername(), sysUser.getUserpwd(),
                sysUser.getUserstate()==1?true:false,true,true,true,
                getAutorityList(sysUser.getRoles()));

        return user;
    }

    /**
     * 根据角色列表获取user的权限集合
     * @param roles
     * @return
     */
    private List<GrantedAuthority> getAutorityList(List<SysRole> roles){
        List<GrantedAuthority> list = new ArrayList<>();
        for (SysRole role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRolename()));
        }

        return list;
    }

    @Override
    public Pagers<SysUser> page(SysUser sysUser, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysUser> list = mapper.selectSysUserList(sysUser);
        Pagers<SysUser> pagers= new Pagers<>(list);
        return pagers;
    }

    @Override
    public SysUserDto find(String userid) {
        return mapper.selectByPrimaryKeyWithRoles(userid);
    }

    @Override
    public SysUser findForUser(String userid) {
        return mapper.selectByPrimaryKey(userid);
    }

    @Transactional
    @Override
    public void edit(SysUser sysUser) throws Exception {
        //防止手动输入密码字段
        sysUser.setUserpwd(null);
        mapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    public void add(SysUser sysUser) throws Exception{
        sysUser.setUserstate(1);
        sysUser.setUserid(UUID.randomUUID().toString());
        sysUser.setUserpwd(BCryptPasswordEncoderUtils.encodePassword(sysUser.getUserpwd()));
        mapper.insertSelective(sysUser);
    }

    @Transactional
    @Override
    public void deprecatedById(String[] ids) {
        for (String id : ids) {
            mapper.updateSysUserStateById(id);
        }
    }


    @Transactional
    @Override
    public Map<String, Integer> editPwd(String username, String userpwd, String oldUserpwd) {
        Map<String,Integer> state = new HashMap<>();
        SysUserDto sysUserDto = mapper.selectByUserName(username);
        if(BCryptPasswordEncoderUtils.matches(sysUserDto.getUserpwd(),oldUserpwd)){
            //密码一致允许修改
            userpwd = BCryptPasswordEncoderUtils.encodePassword(userpwd);
            SysUser sysUser = new SysUser();
            sysUser.setUsername(username);
            sysUser.setUserpwd(userpwd);
            mapper.updateUserPwd(sysUser);
            state.put("editState",1);
        }else {
            state.put("editState",-1);
        }
        return state;
    }

    @Override
    public SysUser findByName(String username) {
        return mapper.selectByUserNameForSysUser(username);
    }
    @Transactional
    @Override
    public Map<String, Integer> resetPwd(SysUser sysUser) throws Exception {
        Map<String,Integer> state = new HashMap<>();
        if (sysUser.getUsername() == null || sysUser.getUserpwd()==null){
            state.put("editState",-1);
            return state;
        }
        sysUser.setUserpwd(BCryptPasswordEncoderUtils.encodePassword(sysUser.getUserpwd()));
        mapper.updateUserPwd(sysUser);
        state.put("editState",1);
        return state;
    }
}
