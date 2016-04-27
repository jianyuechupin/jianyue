package com.hyst.service.impl.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hyst.dao.user.UserDao;
import com.hyst.service.user.UserService;
import com.hyst.vo.user.User;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Autowired 
	HttpSession session;
	public void setSession(HttpSession session) {
		this.session = session;
	}
	@Autowired
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public List<User> list(int id,int id2) {
		Map<String, Integer> map=new HashMap<String, Integer>();
		id=id < 0 ? 0:id;
		id2=id2 >id?id2:Integer.MAX_VALUE;
		
		System.out.println("id="+id+",id2"+id2);
		map.put("id", id);
		map.put("id2", id2);
		List<User> list=userDao.list(map);
		return list;
	}
	@Override
	public User getOne(int id){
		return   userDao.getOne(id);
	}
	@Override
	public String add(User user) {
		System.err.println("add user");
		int i=userDao.insert(user);
		if (i>0) {
			return "sucess";
		}else {
			return"false";
		}
	}
	@Override
	public User selectByTerms(User u){
		return userDao.select2(u);
	}
	/**
	 * 登录判断
	 */
	@Override
	public String login(User u,HttpSession session,HttpServletRequest req) {
		User user=userDao.select2(u);
		session.setMaxInactiveInterval(7);
		if (user!=null) {
			System.out.println("登录状态:成功！");
			session.setAttribute("user", user);
			return "forward:/index.jsp";
		}
		System.out.println("登录状态:失败！");
		req.setAttribute("msg", "账号或密码错误");
		return "forward:/login.jsp";
	}

}
