package com.hyst.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class LogFilter implements Filter{
	private static Logger log;
	@Override
	public void destroy() {
		
	}
	/**
	 * 打印请求日志
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse rsp,
			FilterChain chain) throws IOException, ServletException {
		//获得所有请求参数
		Enumeration<String> names=req.getParameterNames();
		//获得请求路径
		String string=((HttpServletRequest)req).getRequestURI();
		string="{\"路径\":\""+string;
		StringBuffer sBuffer=new StringBuffer();
		sBuffer.append(string+"\",\"参数\":[{");
		for(Enumeration<String> e=names;e.hasMoreElements();){ 
				//参数名
		       String thisName=e.nextElement().toString();
		       //参数值
		       String thisValue=req.getParameter(thisName);
		       sBuffer.append("\""+thisName+"\":\""+thisValue+"\",");
		}
		String s=sBuffer.substring(0,sBuffer.length()-1)+"}]}";

		System.err.println("请求的地址为：\t"+req.getRemoteAddr()+"\t" +"\t"+req.getRemotePort());
		System.out.println(s);
		chain.doFilter(req, rsp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		log=Logger.getLogger("log4j.logger.java.sql.Connection");
	}

	

}
