package edu.etime.yqxdc.controllers.webcontrollers;

import edu.etime.yqxdc.pojo.SysLog;
import edu.etime.yqxdc.pojo.SysUser;
import edu.etime.yqxdc.services.interfaces.SysLogService;
import edu.etime.yqxdc.tools.Pagers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * created by 1
 */
@Controller
@RequestMapping("sys/log")
public class SysLogController {

    @Autowired
    private SysLogService service;

    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(required = false,defaultValue = "1") Integer index,
                             @RequestParam(required = false,defaultValue = "5") Integer pageSize){
        ModelAndView mav = new ModelAndView();
        if(index==null || index <1 ){index = 1;}
        if(pageSize == null){pageSize=5;}

        Pagers<SysLog> page =  service.page(index,pageSize);
        mav.addObject("page",page);
        mav.setViewName("syslog/log-list");
        return mav;
    }

}
