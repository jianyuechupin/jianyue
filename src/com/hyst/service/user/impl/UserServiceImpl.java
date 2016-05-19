package com.hyst.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyst.dao.user.UserDao;
import com.hyst.service.user.UserService;
import com.hyst.vo.user.AdminTbl;
import com.hyst.vo.user.User;
import com.hyst.vo.user.UserInfo;

@Service("userService")
public class UserServiceImpl implements UserService {
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
	public List<UserInfo> list(int id, int id2) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		id = id < 0 ? 0 : id;
		id2 = id2 > id ? id2 : Integer.MAX_VALUE;

		System.out.println("id=" + id + ",id2" + id2);
		map.put("id", id);
		map.put("id2", id2);
		List<UserInfo> list = userDao.list(map);
		return list;
	}

	@Override
	public UserInfo getOne(int id) {
		return userDao.getOne(id);
	}

	@Override
	public String add(UserInfo user) {
		System.err.println("add user");
		int i = userDao.insert(user);
		if (i > 0) {
			return "sucess";
		} else {
			return "false";
		}
	}

	@Override
	public List<UserInfo> selectByTerms(UserInfo u) {
		Map<String, Object> mapUser = new HashMap<>();
		mapUser.put("account", u.getAccount());
		mapUser.put("password", u.getPassword());
		return userDao.userloginAuthentication(mapUser);
	}

	/**
	 * 普通用户登录判断
	 */
	@Override
	public String login(UserInfo u, HttpSession session, HttpServletRequest req) {
		List<?> user = null;
		Map<String, Object> mapUser = new HashMap<>();
		mapUser.put("account", u.getAccount());
		mapUser.put("password", u.getPassword());
		if (isAdmin(u.getAccount())) {
			user = userDao.userloginAuthentication(mapUser);
		} else {
			user = userDao.adminloginAuthentication(mapUser);
		}

		session.setMaxInactiveInterval(300);
		if (user != null && user.size() == 1) {
			System.out.println("登录状态:成功！");
			
			User uinfo=null;
			
			if (user.get(0) instanceof UserInfo) {
				UserInfo userinfo=(UserInfo) user.get(0);
				uinfo=new User(userinfo.getAccount(),userinfo.getUserName(), userinfo.getDeptId(), "0");
			}else {
				AdminTbl admininfo=(AdminTbl) user.get(0);
				uinfo=new User(admininfo.getAdminId(),admininfo.getAdminName(), null, String.valueOf(admininfo.getRoleType()));
			}
			
			session.setAttribute("user", uinfo);

			System.out.println(session.getAttribute("user"));
			return "redirect:/index.jsp";
		}
		System.out.println("登录状态:失败！");
		req.setAttribute("msg", "账号或密码错误!");
		return "forward:/login.jsp";
	}

	private boolean isAdmin(String account) {
		return !"safeadmin".equals(account) && !"sysadmin".equals(account)
				&& !"logadmin".equals(account);
	}

	/**
	 * 退出登录
	 */
	@Override
	public String logout() {
		System.out.println(session.getAttribute("user"));
		session.removeAttribute("user");
		return "redirect:/login.jsp";
	}

	/**
	 * 通过部门获取人员
	 */
	@Override
	public List<UserInfo> getusersbydept(String deptId) {
		return userDao.getusersbydept(deptId);
	}

}
