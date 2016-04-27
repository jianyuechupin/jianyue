package com.hyst.service.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hyst.vo.user.User;
public interface UserService {
	/**
	 * 用户登录
	 * @param u 登录的用户
	 * @return 登录结果
	 */
	public String login(User u,HttpSession session,HttpServletRequest  req);
	/**
	 * 查询所有用户
	 * @param id2 
	 * @param id 
	 * @return
	 */
	public List<User> list(int id, int id2);
	/**
	 * 根据ID得到单个用户
	 * @param id
	 * @return 
	 */
	public User getOne(int id);
	/**
	 * 新增单个用户
	 * @param user
	 * @return
	 */
	public String add(User user);
	/**
	 * 根据不同条件查询用户
	 * @param u 用户对象
	 * @return 
	 */
	public User selectByTerms(User u);
}	
