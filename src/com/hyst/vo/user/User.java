package com.hyst.vo.user;

/**
 * @Content session中存放的人员信息
 * @author rpj
 * @date 2016年5月4日   上午10:56:44
 * @company hyst
 */
public class User {
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 显示的名称
	 */
	private String username;
	/**
	 * 部门编号
	 */
	private String dept;
	/**
	 * 人员类型，0：普通人员，-1：sysadmin，-2：logadmin，-3：safeadmin
	 */
	private String type;
	

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "User [account=" + account + ", username=" + username
				+ ", dept=" + dept + ", type=" + type + "]";
	}

	
	public User(String account, String username, String dept, String type) {
		super();
		this.account = account;
		this.username = username;
		this.dept = dept;
		this.type = type;
	}

	public User() {
		super();
	}

}
