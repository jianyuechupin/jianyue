package com.hyst.vo;
public class UserGroupListTbl{
	/**属性描述：主键ID */
	private int id;
	/**属性描述：用户ID */
	private int userId;
	/**属性描述： 用户组ID*/
	private int userGroupId;
	public UserGroupListTbl(){
		
	}
	public UserGroupListTbl(int userId,int userGroupId){
		setUserId(userId);
		setUserGroupId(userGroupId);
	}
	public int getId(){
		return this.id;
	}
	public int getUserId(){
		return this.userId;
	}
	public int getUserGroupId(){
		return this.userGroupId;
	}
	public void setId(int id){
		this.id = id;
	}
	public void setUserId(int userId){
		this.userId = userId;
	}
	public void setUserGroupId(int userGroupId){
		this.userGroupId = userGroupId;
	}
}