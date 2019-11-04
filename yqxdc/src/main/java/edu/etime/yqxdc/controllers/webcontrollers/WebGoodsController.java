package edu.etime.yqxdc.controllers.webcontrollers;

import edu.etime.yqxdc.pojo.WebGoods;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * created by 1
 */
@Controller
@RequestMapping("/web/goods")
public class WebGoodsController {

    @RequestMapping("/add")
    public String add(@RequestParam("file") CommonsMultipartFile file, DefaultMultipartHttpServletRequest request,WebGoods webGoods,BindingAwareModelMap model) throws IOException {
        String filename = file.getOriginalFilename();
        String realPath = request.getSession().getServletContext().getRealPath("/file/");

        String savePath = realPath+filename;
        //保存
        file.transferTo(new File(savePath));
        webGoods.setGimg(filename);
        model.addAttribute("goods",webGoods);
        return "webgoods/list";
    }
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "webgoods/goods-add";
    }

}
