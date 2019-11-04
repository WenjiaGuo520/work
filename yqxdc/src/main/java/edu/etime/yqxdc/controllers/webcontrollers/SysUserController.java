package edu.etime.yqxdc.controllers.webcontrollers;

import edu.etime.yqxdc.dto.SysUserDto;
import edu.etime.yqxdc.pojo.SysRole;
import edu.etime.yqxdc.pojo.SysUser;
import edu.etime.yqxdc.services.interfaces.SysUserService;
import edu.etime.yqxdc.tools.Pagers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * created by 1
 */
@Controller
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService service;

    @RequestMapping("/login")
    public String login(){
        return "main";
    }

    @PreAuthorize("permitAll()")
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    /**
     * 分页带条件查询
     * @param sysUser
     * @param index
     * @param pageSize
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView page(SysUser sysUser,
                             @RequestParam(required = false,defaultValue = "1") Integer index,
                             @RequestParam(required = false,defaultValue = "5") Integer pageSize){
        ModelAndView mav = new ModelAndView();
        if(index==null || index <1 ){index = 1;}
        if(pageSize == null || pageSize<1){pageSize=5;}
        if(sysUser!=null && sysUser.getUsertruename()!=null && !"".equals(sysUser.getUsertruename())){
            mav.addObject("condition",sysUser.getUsertruename());
            sysUser.setUsertruename("%"+sysUser.getUsertruename()+"%");
        }
        //System.out.println(sysUser.getUsertruename());
        if (sysUser==null){sysUser = new SysUser();}
        Pagers<SysUser> page = service.page(sysUser, index, pageSize);
        mav.addObject("page",page);
        mav.setViewName("sysuser/user-list");
        return mav;
    }

    @RequestMapping("/find/{id}")
    public ModelAndView find(@PathVariable("id") String userid){
        ModelAndView mav = new ModelAndView();
        SysUserDto dto = service.find(userid);

        mav.addObject("sysUser",dto);
        mav.setViewName("sysuser/user-show");

        return mav;
    }

    @RequestMapping("/toEdit/{id}")
    public ModelAndView toEdit(@PathVariable("id") String userid){
        ModelAndView mav = new ModelAndView();
        SysUser sysUser = service.findForUser(userid);
        mav.addObject("sysUser",sysUser);
        mav.setViewName("sysuser/user-edit");

        return mav;
    }


    @RequestMapping("/edit")
    public ModelAndView edit(SysUser sysUser){
        ModelAndView mav = new ModelAndView();
        try {
            service.edit(sysUser);
            mav.addObject("info",1);
            mav.setViewName("sysuser/user-edit");
        }catch (Exception e){
            mav.addObject("info",0);
            mav.addObject(sysUser);
            mav.setViewName("sysuser/user-edit");
        }
        return mav;
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "sysuser/user-add";
    }

    @RequestMapping("/add")
    public ModelAndView add(SysUser sysUser){
        ModelAndView mav = new ModelAndView();
        if (sysUser ==null){
            mav.addObject("info",0);
            mav.setViewName("sysuser/user-add");
        }else {
            try {
                service.add(sysUser);
                mav.addObject("info",1);
                mav.setViewName("sysuser/user-add");
            } catch (Exception e) {
                e.printStackTrace();
                mav.addObject("info",0);
                mav.setViewName("sysuser/user-add");
            }
        }
        return mav;
    }

    @RequestMapping("/toPerm/{id}")
    public ModelAndView toPerm(@PathVariable("id") String userid){
        ModelAndView mav = new ModelAndView();
        SysUserDto dto = service.find(userid);
        mav.addObject("sysUser",dto);
        mav.setViewName("sysuser/user-role-add");

        return mav;
    }

    @RequestMapping("/del")
    public ModelAndView del(String[] ids){
        ModelAndView mav = new ModelAndView();
        try {
            service.deprecatedById(ids);
            Pagers<SysUser> page = service.page(new SysUser(), 1, 5);
            mav.addObject("info",1);
            mav.addObject("page",page);
            mav.setViewName("sysuser/user-list");
        }catch (Exception e){
            mav.addObject("info",0);
        }
        return mav;
    }

    @RequestMapping("/toEditPwd")
    public String toEditPwd(String nowUsername, Model model){
        model.addAttribute("nowUsername",nowUsername);
        return "sysuser/edit-pwd";
    }


    @RequestMapping("/editPwd")
    @ResponseBody
    public Map<String,Integer> editPwd(String nowUsername,String userpwd,String oldUserpwd){
        Map<String,Integer> map = new HashMap<>();
        try {
            map = service.editPwd(nowUsername,userpwd,oldUserpwd);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("editState",-2);
        }
        return map;
    }

    @RequestMapping("/toResetPwd")
    public String toResetPwd(Model model){
        return "sysuser/reset-pwd";
    }
    @RequestMapping("/findByName")
    @ResponseBody
    public Map<String,String> findByName(String username){
        SysUser sysUser = service.findByName(username);
        Map<String,String> userMsg= new HashMap<>();
        if(sysUser!=null){
            userMsg.put("usertruename",sysUser.getUsertruename());
            userMsg.put("findState","1");
        }else {
            userMsg.put("usertruename","账户名不存在");
            userMsg.put("findState","0");
        }
        return userMsg;
    }

    @RequestMapping("/resetPwd")
    @ResponseBody
    public Map<String,Integer> resetPwd(SysUser sysUser){
        Map<String,Integer> state = new HashMap<>();
        try {
            state = service.resetPwd(sysUser);
        } catch (Exception e) {
            e.printStackTrace();
            state.put("editState",-2);
        }
        return state;
    }
}
