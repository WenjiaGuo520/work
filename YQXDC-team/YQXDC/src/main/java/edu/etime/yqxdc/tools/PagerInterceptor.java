package edu.etime.yqxdc.tools;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Administrator
 *
 */
public class PagerInterceptor implements HandlerInterceptor {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
						   ModelAndView modelAndView) throws Exception {

		String url = request.getRequestURL().toString();
		if (url.indexOf("/list") != -1) {
			url+="?a=a";
			Enumeration<String> names = request.getParameterNames();
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				if (!name.equals("a")&&!name.equals("index")) {
					url += "&" + name + "=" + request.getParameter(name);
				}
			}
			//System.out.println(url);
			modelAndView.addObject("url", url);
		}
	}

}
