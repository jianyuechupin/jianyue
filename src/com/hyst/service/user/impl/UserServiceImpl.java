package com.hyst.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyst.dao.user.UserDao;
import com.hyst.service.user.UserService;
import com.hyst.util.SingleMD5;
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
		/* session超时时间配置在web.xml中 */
		/* session.setMaxInactiveInterval(300); */
		if (user != null && user.size() == 1) {
			System.out.println("登录状态:成功！");

			User uinfo = null;
			String salt = String.valueOf(session.getAttribute("salt"));

			if (user.get(0) instanceof UserInfo) {
				UserInfo userinfo = (UserInfo) user.get(0);

				if (!validationPassword(userinfo.getPassword(), salt,
						u.getPassword())) {
					req.setAttribute("msg", "账号或密码错误!");
					return "forward:/login.jsp";
				}

				uinfo = new User(userinfo.getAccount(), userinfo.getUserName(),
						userinfo.getDeptId(), "0");
				session.removeAttribute("salt");
				session.setAttribute("user", uinfo);
				return "redirect:/index.jsp";
			} else {
				AdminTbl admininfo = (AdminTbl) user.get(0);

				if (!validationPassword(admininfo.getPass(), salt,
						u.getPassword())) {
					req.setAttribute("msg", "账号或密码错误!");
					return "forward:/login.jsp";
				}

				uinfo = new User(admininfo.getAdminId(),
						admininfo.getAdminName(), null,
						String.valueOf(admininfo.getRoleType()));
				session.removeAttribute("salt");
				session.setAttribute("user", uinfo);

				if ("safeadmin".equals(admininfo.getAdminId()))
					return "redirect:/safe/userPowerManager.do";
				else if ("sysadmin".equals(admininfo.getAdminId())) {
					// return "redirect:/safe/userPowerManager.do";
				} else if ("logadmin".equals(admininfo.getAdminId())) {

				}

			}
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
	 * 密码加盐校验
	 * 
	 * @param md5Password
	 *            md5一次的值，存在数据库中的
	 * @param salt
	 *            随机盐
	 * @param addSaltPassWord
	 *            前端混合盐加密后的md5值
	 * @return true表示密码校验成功，否则失败
	 * @author --- rpj ---
	 * @time 2016年5月23日
	 */
	private Boolean validationPassword(String md5Password, String salt,
			String addSaltPassWord) {
		return SingleMD5.encryptByMD5(md5Password + salt).equals(
				addSaltPassWord);
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

	@Override
	public String getSalt(HttpSession session) {
		String salt = "";
		Random random = new Random();
		StringBuilder sb = new StringBuilder(16);
		sb.append(random.nextInt(99999999)).append(random.nextInt(99999999));
		if (sb.length() < 16) {
			for (int i = 0; i < 16 - sb.length(); i++) {
				sb.append("0");
			}
		}
		salt = sb.toString();
		session.setAttribute("salt", salt);
		return salt;
	}

}
