package com.hyst.vo;

import java.util.List;

/**
 * 用户权限细则数据封装类《用于接收前台传的用户权限细则集合数据》
 * @author DongYi
 * @version 创建时间：2016年5月12日 下午2:05:33
 */

public class UserPowerDetailTbls {
	/**用户权限细则集合*/
	private List<UserPowerDetailTbl> userPowers;
	/**一级菜单的ID*/
	private int pid;
	/**用户ID*/
	private int userId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<UserPowerDetailTbl> getUserPowers() {
		return userPowers;
	}
	public void setUserPowers(List<UserPowerDetailTbl> userPowers) {
		this.userPowers = userPowers;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	@Override
	public String toString() {
		return "UserPowerDetailTbls [userPowers=" + userPowers + ", pid=" + pid
				+ ", userId=" + userId + "]";
	}
	
	
}
