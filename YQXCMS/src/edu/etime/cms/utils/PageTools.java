package edu.etime.cms.utils;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * 封装分页的url参数
 * @author 1
 *
 */
public class PageTools {
	/**
	 * 根据请求参数得到访问路径
	 * @param req
	 * @return
	 */
	public static String parseUrl(HttpServletRequest req) {
		//得到参数
        Map<String, String[]> map = req.getParameterMap();
		 //封装page参数
        StringBuilder sb = new StringBuilder();
        String uri = req.getRequestURI().toString();//     uri:  /YQXCMS/art/news
        
        sb.append(uri+"?");
        Set<String> keySet = map.keySet();
        
        for (String key : keySet) {
        	if("currentPage".equals(key) || "rows".equals(key)) {
        		continue;
        	}
        	sb.append(key+"="+map.get(key)[0]);
        	sb.append("&");	
		}
        String url = sb.substring(0,sb.length()-1);
		return url;
		
	}
}
