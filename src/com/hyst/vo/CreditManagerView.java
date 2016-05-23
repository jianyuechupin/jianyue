package com.hyst.vo;
public class CreditManagerView{
	/**属性描述：id，部门保密管理员表的ID */
	private int id;
	/**属性描述： 用户ID*/
	private int userInfoId;
	/**属性描述： 用户名字*/
	private String userName;
	/**属性描述： 用户类型 1 兼职保密员，2保密处人员*/
	private int roleType;
	/**属性描述：被保密的部门列表 */
	private String orgsName;
	/**属性描述： 保密员所在部门的ID*/
	private String deptId;
	/**属性描述： 保密员所在部门的名称*/
	private String orgName;
	/**保密角色名称*/
	private String typeName;
 	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Override
	public String toString() {
		return "CreditManagerView [id=" + id + ", userInfoId=" + userInfoId
				+ ", userName=" + userName + ", roleType=" + roleType
				+ ", orgsName=" + orgsName + ", deptId=" + deptId
				+ ", orgName=" + orgName + "]";
	}
	public int getId(){
		return this.id;
	}
	public int getUserInfoId(){
		return this.userInfoId;
	}
	public String getUserName(){
		return this.userName;
	}
	public int getRoleType(){
		return this.roleType;
	}
	public String getOrgsName(){
		return this.orgsName;
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
	public void setUserInfoId(int userInfoId){
		this.userInfoId = userInfoId;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	public void setRoleType(int roleType){
		this.roleType = roleType;
		if (roleType==1) {
			setTypeName("兼职保密员");
		}else if (roleType==2) {
			setTypeName("保密处人员");
		}
	}
	public void setOrgsName(String orgsName){
		this.orgsName = orgsName;
	}
	public void setDeptId(String deptId){
		this.deptId = deptId;
	}
	public void setOrgName(String orgName){
		this.orgName = orgName;
	}
}