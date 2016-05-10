package com.hyst.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
public class EncodingFilter implements Filter{
	String encoding="utf-8";
	@Override
	public void destroy() {
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("encoding");
		System.err.println("初始化中的encoding\t"+encoding);
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if(encoding==null||"".equals(encoding)){
			encoding="utf-8";
		}
		//解决的post请求
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		response.setContentType("text/html;charset=UTF-8");
		chain.doFilter(new HttpServletRequestWrapper((HttpServletRequest) request){
			@Override
			public String getParameter(String name) {
				//对于get请求特殊处理
				if("get".equals(this.getMethod().toLowerCase())){
					String value=super.getParameter(name);
					try {
						return new String(value.getBytes("iso-8859-1"),encoding);
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					return value;
				}
				return super.getParameter(name);
			}
		}, response);
	}
	public boolean test(int node) {
		// TODO Auto-generated method stub
		return false;
	}
}
