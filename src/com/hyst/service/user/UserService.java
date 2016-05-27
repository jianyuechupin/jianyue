package com.hyst.service.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hyst.vo.user.UserInfo;
public interface UserService {
	/**
	 * 用户登录
	 * @param u 登录的用户
	 * @return 登录结果
	 */
	public String login(UserInfo u,HttpSession session,HttpServletRequest  req);
	/**
	 * user logout
	 * @return redirect action
	 * @author --- rpj ---	
	 * @time 2016年5月3日
	 */
	public String logout();
	/**
	 * 查询所有用户
	 * @param id2 
	 * @param id 
	 * @return
	 */
	public List<UserInfo> list(int id, int id2);
	/**
	 * 根据ID得到单个用户
	 * @param id
	 * @return 
	 */
	public UserInfo getOne(int id);
	/**
	 * 新增单个用户
	 * @param user
	 * @return
	 */
	public String add(UserInfo user);
	/**
	 * 根据不同条件查询用户
	 * @param u 用户对象
	 * @return 
	 */
	public List<UserInfo> selectByTerms(UserInfo u);
	/**
	 * 通过部门获取人员
	 * @param deptId
	 * @return
	 * @author --- rpj ---	
	 * @time 2016年5月18日
	 */
	public List<UserInfo> getusersbydept(String deptId);
	
	/**
	 * 获取随机盐
	 * @param session
	 * @return
	 * @author --- rpj ---	
	 * @time 2016年5月23日
	 */
	public  String getSalt(HttpSession session);
	/**
	 * 设置用户登录方式
	 * @param userInfo 用户对象，用于接收用户ID，密码，登录方式等参数
	 * @return 操作状态
	 */
	public String setLoginType(UserInfo userInfo);
}	
