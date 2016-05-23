package com.hyst.vo;
/**
 * 用户权限管理视图
 */
public class UserPowerManageView{
	/**属性描述： 用户ID*/
	private int uid;
	/**属性描述：用户名 */
	private String userName;
	/**属性描述： 部门ID*/
	private int orgId;
	/**属性描述：部门名称 */
	private String orgName;
	/**属性描述： 登录名*/
	private String account;
	/**属性描述： 登录类型 1密码登录，0域登录*/
	private int loginType;
	/**属性描述：登录方式字符串*/
	private String loginTypeString;
	
	@Override
	public String toString() {
		return "UserPowerManageView [uid=" + uid + ", userName=" + userName
				+ ", orgId=" + orgId + ", orgName=" + orgName + ", account="
				+ account + ", loginType=" + loginType + ", loginTypeString="
				+ loginTypeString + "]";
	}
	public String getLoginTypeString() {
		return loginTypeString;
	}
	public void setLoginTypeString(String loginTypeString) {
		this.loginTypeString = loginTypeString;
	}

	public int getUid(){
		return this.uid;
	}
	public String getUserName(){
		return this.userName;
	}
	public int getOrgId(){
		return this.orgId;
	}
	public String getOrgName(){
		return this.orgName;
	}
	public String getAccount(){
		return this.account;
	}
	public int getLoginType(){
		return this.loginType;
	}
	public void setUid(int uid){
		this.uid = uid;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	public void setOrgId(int orgId){
		this.orgId = orgId;
	}
	public void setOrgName(String orgName){
		this.orgName = orgName;
	}
	public void setAccount(String account){
		this.account = account;
	}
	public void setLoginType(int loginType){
		this.loginType = loginType;
		if (loginType==0) {
			setLoginTypeString("域登录");
		}else {
			setLoginTypeString("密码登录");
		}
	}
}