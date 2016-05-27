package com.hyst.controller.user;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyst.service.user.UserService;
import com.hyst.vo.user.UserInfo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 用户登录
	 */
	@RequestMapping("login")
	public String login(UserInfo u, String account, HttpSession session,
			HttpServletRequest req) {
		return userService.login(u, session, req);
	}

	/**
	 * user logout
	 * 
	 * @return
	 * @author --- rpj ---
	 * @time 2016年5月3日
	 */
	@RequestMapping("logout")
	public String logout() {
		return userService.logout();
	}

	/**
	 * 查询用户列表
	 * 
	 * @param id
	 * @param id2
	 * @return
	 */
	@RequestMapping("list")
	public String list(ModelMap mp) {
		int id = -1;
		int id2 = -5;
		mp.addAttribute("list", userService.list(id, id2));
		return "/list.jsp";
	}

	@RequestMapping("insert")
	public String insert(UserInfo user) {
		userService.add(user);
		return "list.do";
	}

	/**
	 * 查询出某部门下的所有用户 <根据部门填充人员数据>
	 * 
	 * @param deptid
	 *            部门id
	 * @return
	 */
	@RequestMapping("getusersbydept")
	@ResponseBody
	public List<UserInfo> getusersbydept(String deptId) {
		return userService.getusersbydept(deptId);
	}
	
	/**
	 * 获取随机盐，并存入session中
	 * @param session
	 * @return
	 * @author --- rpj ---	
	 * @time 2016年5月23日
	 */
	@RequestMapping("getsalt")
	@ResponseBody
	public String getSalt(HttpSession session) {
		return userService.getSalt(session);
	}
	
	//修改用户密码
	@RequestMapping("setlogintype")
	@ResponseBody
	public String setLoginType(UserInfo userInfo){
		return userService.setLoginType(userInfo);
	}
}
