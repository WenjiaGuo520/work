package edu.etime.yqxdc.controllers.webcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 主界面控制器
 */
@Controller
public class MainController {

    @RequestMapping("/toMain")
    public String toMain(){
        return "main";
    }
    
    @RequestMapping("/forgetPwd")
    @ResponseBody
    public String forgetPwd(){
        return "<h1>暂未开启次功能 !  <a href='${pageContext.request.contextPath}/sys/user/toLogin'>前往登录</a></h1>";
    }
}
