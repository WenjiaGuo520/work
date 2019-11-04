package edu.etime.cms.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * 编码过滤器
 * @author 1
 *
 */
@WebFilter("/*")
public class EncodeFilter implements Filter{
	private String encode ="UTF-8";
	private String contenttype="text/html";
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		request.setCharacterEncoding(encode);
		response.setCharacterEncoding(encode);
		response.setContentType(contenttype);
		
		chain.doFilter(request, response);
		
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	
}
