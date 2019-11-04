package edu.etime.yqxdc.controllers.wxcontrollers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;

/**
 * 获取微信授权登录
 * @author 孤心
 *
 */
@Controller
@RequestMapping("/wx")
public class WXGetOpenidController {

	@RequestMapping("/getopenid")
	public void GetOpenid(HttpServletRequest request, HttpServletResponse response){
		String code = request.getParameter("code");
		System.out.println(code);
		String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=wx9c21432f02a4805a&secret=766453a5c4d6079d2bae46a7f87593f9&js_code="
				+ code + "&grant_type=authorization_code";
		String returnvalue = GETURL(WX_URL);
		JSONObject json = JSONObject.parseObject(returnvalue);
		System.out.println("json:"+json);
		String openid = json.get("openid").toString();
		try {
			response.getWriter().println(openid);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
//	public void getOpenid(String code){
//		String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=wx93295eecf03769eb&secret=441cf8ebaef250af3ee3b62499576bb4&js_code="
//				+ code + "&grant_type=authorization_code"; 
//		String returnvalue = GETURL(WX_URL);
//		JSONObject json = JSONObject.parseObject(returnvalue);
//		System.out.println("json:"+json);
//		System.out.println("code:"+code);
//	}
	
	public static String GETURL(String url){
		String result = "";
		BufferedReader br = null;
		InputStream is = null;
		InputStreamReader isr = null;
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.connect();
			Map<String, List<String>> map = conn.getHeaderFields();
			is = conn.getInputStream();
		    isr = new InputStreamReader(is);
		    br = new BufferedReader(isr);
		    String line;
		    while((line = br.readLine()) != null){
		    	result += line;
		    }
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
				try {
					if(br != null){
					br.close();
					}
					if(is != null){
						is.close();
					}
					if(isr != null){
						isr.close();
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		return result;
		}
		
	
}
