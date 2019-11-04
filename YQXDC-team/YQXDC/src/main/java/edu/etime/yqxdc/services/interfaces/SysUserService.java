package edu.etime.yqxdc.services.interfaces;

import edu.etime.yqxdc.dto.SysUserDto;
import edu.etime.yqxdc.pojo.SysUser;
import edu.etime.yqxdc.tools.Pagers;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;


/**
 * created by 1
 */
public interface SysUserService extends UserDetailsService {

    Pagers<SysUser> page(SysUser sysUser,int pageNum,int pageSize);

    SysUserDto find(String userid);

    SysUser findForUser(String userid);

    void edit(SysUser sysUser) throws Exception;

    void add(SysUser sysUser)throws Exception;

    /**
     * 弃用用户
     * @param ids
     */
    void deprecatedById(String[] ids);

    /**
     * 修改密码，返回修改状态
     * map : editState
     *      1 :修改成功
     *      -1:密码错误
     *      -2：其他错误
     * @return
     */
    Map<String,Integer> editPwd(String username, String userpwd, String oldUserpwd)throws Exception;

    SysUser findByName(String username);
    /**
     * 重置密码，返回修改状态
     * map : editState
     *      1 :修改成功
     *      -1:用户名，密码为空
     *      -2 ：其他错误
     * @return
     */
    Map<String,Integer> resetPwd(SysUser sysUser)throws Exception;
}
