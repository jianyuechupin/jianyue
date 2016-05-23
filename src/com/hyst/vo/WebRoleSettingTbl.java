package com.hyst.vo;
public class WebRoleSettingTbl{
	/**属性描述： ID*/
	private int id;
	/**属性描述： 角色类型 1-兼职保密员,2-处室领导,3-部领导*/
	private int roleType;
	/**属性描述： 角色类型名称*/
	private String role;
	/**属性描述：用户ID，多个之间以竖线分割 | */
	private String userId;
	public int getId(){
		return this.id;
	}
	public int getRoleType(){
		return this.roleType;
	}
	public String getUserId(){
		return this.userId;
	}
	public void setId(int id){
		this.id = id;
	}
	public void setRoleType(int roleType){
		this.roleType = roleType;
		//1-兼职保密员,2-处室领导,3-部领导
		if (roleType==1) {
			setRole("兼职保密员");
		}else if (roleType==2) {
			setRole("处室领导");
		}else if (roleType==3) {
			setRole("部领导");
		}
	}
	public void setUserId(String userId){
		this.userId = userId;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "WebRoleSettingTbl [id=" + id + ", roleType=" + roleType
				+ ", role=" + role + ", userId=" + userId + "]";
	}
	
}