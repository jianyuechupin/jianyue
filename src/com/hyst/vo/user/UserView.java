package com.hyst.vo.user;
public class UserView{
	/**属性描述： */
	private int id;
	/**属性描述： */
	private String userName;
	/**属性描述： */
	private String deptId;
	/**属性描述： */
	private String orgName;
	
	@Override
	public String toString() {
		return "UserView [id=" + id + ", userName=" + userName + ", deptId="
				+ deptId + ", orgName=" + orgName + "]";
	}
	public int getId(){
		return this.id;
	}
	public String getUserName(){
		return this.userName;
	}
	public String getDeptId(){
		return this.deptId;
	}
	public String getOrgName(){
		return this.orgName;
	}
	public void setId(int id){
		this.id = id;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	public void setDeptId(String deptId){
		this.deptId = deptId;
	}
	public void setOrgName(String orgName){
		this.orgName = orgName;
	}
}