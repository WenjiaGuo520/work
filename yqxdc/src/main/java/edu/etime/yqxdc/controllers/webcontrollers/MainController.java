package edu.etime.yqxdc.controllers.webcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主界面控制器
 */
@Controller
public class MainController {

    @RequestMapping("/toMain")
    public String toMain(){
        return "main";
    }
}
