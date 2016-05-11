package com.hyst.vo.user;

public class AdminTbl {
	/** 属性描述： */
	private int id;
	/** 属性描述： */
	private String adminId;
	/** 属性描述： */
	private String pass;
	/** 属性描述： */
	private int roleType;
	/** 属性描述： */
	private java.sql.Timestamp changePasswdTime;
	/** 属性描述： */
	private java.sql.Timestamp lastLoginTime;
	/** admin中文名称 */
	private String adminName;

	public int getId() {
		return this.id;
	}

	public String getAdminId() {
		return this.adminId;
	}

	public String getPass() {
		return this.pass;
	}

	public int getRoleType() {
		return this.roleType;
	}

	public java.sql.Timestamp getChangePasswdTime() {
		return this.changePasswdTime;
	}

	public java.sql.Timestamp getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}

	public void setChangePasswdTime(java.sql.Timestamp changePasswdTime) {
		this.changePasswdTime = changePasswdTime;
	}

	public void setLastLoginTime(java.sql.Timestamp lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	@Override
	public String toString() {
		return "AdminTbl [id=" + id + ", adminId=" + adminId + ", pass=" + pass
				+ ", roleType=" + roleType + ", changePasswdTime="
				+ changePasswdTime + ", lastLoginTime=" + lastLoginTime
				+ ", adminName=" + adminName + "]";
	}

}