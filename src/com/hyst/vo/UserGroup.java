package com.hyst.vo;
public class UserGroup{
	/**属性描述： */
	private int id;
	/**属性描述： */
	private String userGroupName;
	/**属性描述： */
	private String description;
	/**属性描述： */
	private java.sql.Timestamp  creatTime;
	/**属性描述： */
	private java.sql.Timestamp  updateTime;
	
	
	@Override
	public String toString() {
		return "UserGroup [id=" + id + ", userGroupName=" + userGroupName
				+ ", description=" + description + ", creatTime=" + creatTime
				+ ", updateTime=" + updateTime + "]";
	}
	public int getId(){
		return this.id;
	}
	public String getUserGroupName(){
		return this.userGroupName;
	}
	public String getDescription(){
		return this.description;
	}
	public java.sql.Timestamp  getCreatTime(){
		return this.creatTime;
	}
	public java.sql.Timestamp  getUpdateTime(){
		return this.updateTime;
	}
	public void setId(int id){
		this.id = id;
	}
	public void setUserGroupName(String userGroupName){
		this.userGroupName = userGroupName;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public void setCreatTime(java.sql.Timestamp  creatTime){
		this.creatTime = creatTime;
	}
	public void setUpdateTime(java.sql.Timestamp  updateTime){
		this.updateTime = updateTime;
	}
}