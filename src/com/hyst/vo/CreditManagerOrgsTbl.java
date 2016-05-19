package com.hyst.vo;

import java.util.List;
/**
 * 部门保密管理员
 */
public class CreditManagerOrgsTbl{
	/**属性描述： */
	private int  id;
	/**属性描述： */
	private int userInfoId;
	/**属性描述： */
	private String userName;
	/**属性描述：角色类型 1兼职保密员，2保密处人员 */
	private int roleType;
	/**属性描述：角色名称*/
	private String roleName;
	/**属性描述： */
	private String orgsName;
	/**部门保密管理员 部门细则表*/
	private List<CreditManagerTbl> creditManagerTbls;
	
	public List<CreditManagerTbl> getCreditManagerTbls() {
		return creditManagerTbls;
	}
	public void setCreditManagerTbls(List<CreditManagerTbl> creditManagerTbls) {
		this.creditManagerTbls = creditManagerTbls;
	}
	public int  getId(){
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
	public void setId(int  id){
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
			setRoleName("兼职保密员");
		}else if (roleType==2) {
			setRoleName("保密处人员");
		}
		
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public void setOrgsName(String orgsName){
		this.orgsName = orgsName;
	}
}