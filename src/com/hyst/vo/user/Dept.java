package com.hyst.vo.user;

import java.util.List;
//一对多测试  ONE TO MANY
public class Dept {
	private int id;
	private String deptname;
	private List<User> users;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Dept [id=" + id + ", deptname=" + deptname + ", users=" + users
				+ "]";
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
}
