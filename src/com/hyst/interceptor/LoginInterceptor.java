package com.hyst.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hyst.vo.user.User;

/**
 * @author DongYi
 * @version 创建时间：2016年4月7日 下午6:04:33
 * 类说明
 */
public class LoginInterceptor implements HandlerInterceptor {
	//允许哪些url不被拦截，哪些需要被拦截
	private List<String> allowedPass;
	public void setAllowedPass(List<String> allowedPass) {
		this.allowedPass = allowedPass;
	}
	/**
	 * 在DispatcherServlet处理后执行-----清理工作
	 */
	@Override
	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse rsp, Object arg2, Exception arg3)
			throws Exception {
		
	}

	//在请求处理的方法执行之后执行
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse rsp,
			Object handler, ModelAndView mdv) throws Exception {
		// TODO Auto-generated method stub
		
	}

	//在请求处理的方法之前执行
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse rsp,
			Object handler) throws Exception {
		User user=(User) req.getSession().getAttribute("user");
		String url=req.getRequestURL().toString();
		System.out.println("请求资源为：\t"+url);
		if (user!=null) {//如果已登录
			return true;
		}
		for(String temp:allowedPass){//允许通过资源
			if(url.endsWith(temp)){
				return true;
			}
		}
		//未登录且请求的不是允许通过资源
		rsp.sendRedirect(req.getContextPath()+"/login.jsp");
		return false;
	}

}
