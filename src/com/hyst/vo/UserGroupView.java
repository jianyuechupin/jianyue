package com.hyst.vo;
public class UserGroupView{
	/**属性描述：用户组ID */
	private String id;
	/**属性描述： 用户组名称*/
	private String userGroupName;
	/**属性描述： 用户组描述*/
	private String description;
	/**属性描述： 用户数*/
	private int unum;
	
	@Override
	public String toString() {
		return "UserGroupView [id=" + id + ", userGroupName=" + userGroupName
				+ ", description=" + description + ", unum=" + unum + "]";
	}
	public String getId(){
		return this.id;
	}
	public String getUserGroupName(){
		return this.userGroupName;
	}
	public String getDescription(){
		return this.description;
	}
	public int getUnum(){
		return this.unum;
	}
	public void setId(String id){
		this.id = id;
	}
	public void setUserGroupName(String userGroupName){
		this.userGroupName = userGroupName;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public void setUnum(int unum){
		this.unum = unum;
	}
}