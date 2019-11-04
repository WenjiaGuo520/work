package edu.etime.yqxdc.controllers.webcontrollers;

import edu.etime.yqxdc.pojo.SysFunction;
import edu.etime.yqxdc.services.interfaces.SysFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by 1
 */
@Controller
@RequestMapping("/sys/fun")
public class SysFunController {
    @Autowired
    private SysFunctionService service;

    @RequestMapping("/list")
    public ModelAndView list(){
        List<SysFunction> funs = service.findAll();
        ModelAndView mav = new ModelAndView();
        mav.addObject("functions",funs);
        mav.setViewName("sysfuns/fun-list");
        return mav;
    }


    @RequestMapping("/toAdd")
    public String toAdd(String pfid, String pfname,String state,Model model){
        model.addAttribute("pfid",pfid);
        model.addAttribute("pfname",pfname);
        //如果state为0则是添加子节点，1位根节点
        model.addAttribute("isChild","".equals(state));
        return "sysfuns/fun-add";
    }
    @RequestMapping("/add")
    public @ResponseBody Map<String,Boolean>  add(SysFunction function){
        Map<String,Boolean> map = new HashMap<>();
        try {
            service.add(function);
            map.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success",false);
        }
        return map;
    }

    @RequestMapping("/del")
    public @ResponseBody Map<String,Boolean> del(String[] funids){
        Map<String,Boolean> map = new HashMap<>();
        try {
            service.updateStateById(funids);
            map.put("delState",true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("delState",false);
        }
        return map;
    }
}
