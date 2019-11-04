package edu.etime.yqxdc.tools;

import edu.etime.yqxdc.controllers.webcontrollers.SysLogController;
import edu.etime.yqxdc.pojo.SysLog;
import edu.etime.yqxdc.services.interfaces.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

/**
 * 日志记录切面
 */
@Component
@Aspect
public class LogAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService service;

    private Date visitTime; //开始时间
    private Class clazz; //访问的类
    private Method method;//访问的方法

    @Pointcut("execution(* edu.etime.yqxdc.controllers.*.*.*(..))")
    public void pt() {
    }

    /**
     * 前置记录，记录url，访问时间等
     */
    @Before("pt()")
    public void beforeLog(JoinPoint jp) throws NoSuchMethodException {
        //请求时间
        visitTime = new Date();
        //访问的类
        clazz = jp.getTarget().getClass();
        //访问的方法
        String methodName = jp.getSignature().getName();
        //方法参数
        Object[] args = jp.getArgs();
        if (args == null || args.length == 0) {
            //无参数构造
            method = clazz.getMethod(methodName);
        } else {

            Class[] methodClazz = new Class[args.length];

            for (int i = 0; i < args.length; i++) {
                methodClazz[i] = args[i].getClass();
            }
            //获得Method
            method = clazz.getMethod(methodName, methodClazz);

        }

    }

    /**
     * 最终通知，记录url，访问时长，IP地址，当前用户名，并将记录插入数据库
     */
    @After("pt()")
    public void AfterLog(JoinPoint jp) {
        //访问时长
        int executiontime = (int) (new Date().getTime() - visitTime.getTime());

        //不记录本类，以及访问查看日志的操作
        if (clazz != null && method != null && clazz != LogAspect.class && clazz != SysLogController.class && !method.getName().equals("toLogin")) {
            //url
            RequestMapping clazzReq = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (clazzReq != null) {
                //类上的requestMapping值
                String[] clazzReqVal = clazzReq.value();
                //获取方法上的映射路径
                RequestMapping methodReq = method.getAnnotation(RequestMapping.class);
                String[] methodReqVal = methodReq.value();
                //完整url
                String reqUrl = clazzReqVal[0] + methodReqVal[0];
                //获取IP地址
                String ip = request.getRemoteAddr();
                //从security上下文获取当前用户
                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                User user = (User) principal;
                String username = user.getUsername();
                //构造SysLog
                SysLog log = new SysLog();
                log.setVisittime(visitTime);
                log.setUsername(username);
                log.setUrl(reqUrl);
                log.setExecutiontime(executiontime);
                log.setIp(ip);
                log.setMethod("[class:] " + clazz.getName() + " [method:] " + method.getName());
                log.setLid(UUID.randomUUID().toString());
                //插入数据
                service.addSysLog(log);
            }


        }


    }


}
