package edu.etime.yqxdc.test;

import edu.etime.yqxdc.controllers.webcontrollers.WebGoodsController;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * created by 1
 */
public class TestAop {
    @Test
    public void testAop(){
        Class<WebGoodsController> clazz = WebGoodsController.class;
        try {
            Method add = clazz.getMethod("add", CommonsMultipartFile.class, DefaultMultipartHttpServletRequest.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
