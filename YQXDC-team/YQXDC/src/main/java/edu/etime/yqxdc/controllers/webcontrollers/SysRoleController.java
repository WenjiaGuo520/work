package edu.etime.yqxdc.controllers.webcontrollers;

import com.github.pagehelper.util.StringUtil;
import edu.etime.yqxdc.dto.SysFunctionDto;
import edu.etime.yqxdc.dto.SysRoleDto;
import edu.etime.yqxdc.pojo.SysRole;
import edu.etime.yqxdc.services.interfaces.SysRoleService;
import edu.etime.yqxdc.tools.Pagers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.management.relation.Role;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by 1
 */
@Controller
@RequestMapping("/sys/role")
public class SysRoleController {

    @Autowired
    private SysRoleService service;

    @RequestMapping("/list")
    public ModelAndView list(SysRole role,Integer index,Integer pageSize){
        ModelAndView mav = new ModelAndView();
        if(index ==null || index<1){index=1;}
        if(pageSize==null){pageSize=5;}
        if (role==null){role = new SysRole();}
        if(role!=null && !StringUtil.isEmpty(role.getRolename())){
            mav.addObject("condition",role.getRolename());
            role.setRolename("%"+role.getRolename()+"%");
        }

        Pagers<SysRole> page = service.page(role, index, pageSize);
        mav.addObject("page",page);
        mav.setViewName("sysrole/role-list");

        return mav;
    }

    @RequestMapping("/getAll")
    public @ResponseBody List<SysRole> getAll(){
        ModelAndView mav = new ModelAndView();
        List<SysRole> roles = service.findAll();
        return roles;
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "sysrole/role-add";
    }

    @RequestMapping("/add")
    public ModelAndView add(SysRole role){
        ModelAndView mav = new ModelAndView();
        try {
            service.add(role);
            mav.addObject("info",1);
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("info",0);
        }
        mav.setViewName("sysrole/role-add");
        return mav;
    }

    /**
     * 根据roleid查询RoleDto
     * @param roleid
     * @return
     */
    @RequestMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable("id") String roleid){
        ModelAndView mav = new ModelAndView();
        SysRoleDto roleDto = service.findRoleDtoById(roleid);
        mav.addObject("role",roleDto);
        mav.setViewName("sysrole/role-show");
        return mav;
    }

    /**
     * 校验角色名是否存在
     * @return
     */
    @RequestMapping("/checkName")
    public @ResponseBody Map<String,Integer> checkName(String rolename){
        boolean b = service.checkName(rolename);
        Map<String,Integer> isHas = new HashMap<>();
        isHas.put("isHas",b?0:1);
        return isHas;
    }

    @RequestMapping("/del")
    public ModelAndView del(String[] ids){
        ModelAndView mav = new ModelAndView();
        try {
            service.updateStateById(ids);
            mav.addObject("delInfo",1);
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("delInfo",0);
        }
        mav.setViewName("forward:/sys/role/toList");
        return mav;
    }


    @RequestMapping("/toList")
    public ModelAndView toList(){
        ModelAndView mav = new ModelAndView();
        Pagers<SysRole> page = service.page(new SysRole(), 1, 5);
        mav.addObject("page",page);
        mav.setViewName("sysrole/role-list");
        return mav;
    }


    @RequestMapping("/toEdit/{id}")
    public ModelAndView toEdit(@PathVariable("id") String id){
        ModelAndView mav = new ModelAndView();
        SysRole role = service.findRoleById(id);
        List<SysFunctionDto> funDtos = service.selectFunctionsWithUsestate(id);
        mav.addObject("role",role);
        mav.addObject("funs",funDtos);
        mav.setViewName("sysrole/role-edit");
        return mav;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(SysRole role,String[] funids,String editFunState){
        ModelAndView mav = new ModelAndView();
        System.out.println(role);
        System.out.println(funids);

        try {

            service.editRoleAndRoleRight(role,funids,editFunState);
            mav.addObject("editInfo",1);
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("editInfo",0);
        }

        mav.setViewName("forward:/sys/role/toList");

        return mav;
    }
}
