package com.hyst.vo;

import java.util.List;

/**
 * 用户组权限细则，绑定集合参数类
 * @author DongYi
 * @version 创建时间：2016年5月4日 下午1:57:47
 * 类说明
 */
public class PowerDetails {
	/** 用户组权限细则列表*/
	private  List<UserGroupPowerDetail> details;
	/**一级菜单ID*/
	private int pid;
	/**权限组*/
	private UserGroup userGroup;
	
	public UserGroup getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
	public List<UserGroupPowerDetail> getDetails() {
		return details;
	}
	public void setDetails(List<UserGroupPowerDetail> details) {
		this.details = details;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	@Override
	public String toString() {
		return "PowerDetails [details=" + details + ", pid=" + pid
				+ ", userGroup=" + userGroup + "]";
	}
	

	
}
