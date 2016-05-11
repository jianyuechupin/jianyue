package com.hyst.vo.user;

public class UserInfo{
	/**属性描述： */
	private int id=-1;
	/**属性描述： */
	private String userName;
	/**属性描述： */
	private String userSex;
	/**属性描述： */
	private String deptId;
	/**属性描述： */
	private java.sql.Timestamp  borthDate;
	/**属性描述： */
	private String userFace;
	/**属性描述： */
	private String userJob;
	/**属性描述： */
	private String userOffice;
	/**属性描述： */
	private int secretLevelId;
	/**属性描述： */
	private int userTypeId;
	/**属性描述： */
	private int userStateID;
	/**属性描述： */
	private int secretProvide;
	/**属性描述： */
	private int isDelete;
	/**属性描述： */
	private String staffPhoto;
	/**属性描述： */
	private String jobPost;
	/**属性描述：域账号 */
	private String account;
	/**属性描述： */
	private String password;
	public int getId(){
		return this.id;
	}
	public String getUserName(){
		return this.userName;
	}
	public String getUserSex(){
		return this.userSex;
	}
	public String getDeptId(){
		return this.deptId;
	}
	public java.sql.Timestamp  getBorthDate(){
		return this.borthDate;
	}
	public String getUserFace(){
		return this.userFace;
	}
	public String getUserJob(){
		return this.userJob;
	}
	public String getUserOffice(){
		return this.userOffice;
	}
	public int getSecretLevelId(){
		return this.secretLevelId;
	}
	public int getUserTypeId(){
		return this.userTypeId;
	}
	public int getUserStateID(){
		return this.userStateID;
	}
	public int getSecretProvide(){
		return this.secretProvide;
	}
	public int getIsDelete(){
		return this.isDelete;
	}
	public String getStaffPhoto(){
		return this.staffPhoto;
	}
	public String getJobPost(){
		return this.jobPost;
	}
	public String getAccount(){
		return this.account;
	}
	public String getPassword(){
		return this.password;
	}
	public void setId(int id){
		this.id = id;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}
	public void setUserSex(String userSex){
		this.userSex = userSex;
	}
	public void setDeptId(String deptId){
		this.deptId = deptId;
	}
	public void setBorthDate(java.sql.Timestamp  borthDate){
		this.borthDate = borthDate;
	}
	public void setUserFace(String userFace){
		this.userFace = userFace;
	}
	public void setUserJob(String userJob){
		this.userJob = userJob;
	}
	public void setUserOffice(String userOffice){
		this.userOffice = userOffice;
	}
	public void setSecretLevelId(int secretLevelId){
		this.secretLevelId = secretLevelId;
	}
	public void setUserTypeId(int userTypeId){
		this.userTypeId = userTypeId;
	}
	public void setUserStateID(int userStateID){
		this.userStateID = userStateID;
	}
	public void setSecretProvide(int secretProvide){
		this.secretProvide = secretProvide;
	}
	public void setIsDelete(int isDelete){
		this.isDelete = isDelete;
	}
	public void setStaffPhoto(String staffPhoto){
		this.staffPhoto = staffPhoto;
	}
	public void setJobPost(String jobPost){
		this.jobPost = jobPost;
	}
	public void setAccount(String account){
		this.account = account;
	}
	public void setPassword(String password){
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", userName=" + userName + ", userSex="
				+ userSex + ", deptId=" + deptId + ", borthDate=" + borthDate
				+ ", userFace=" + userFace + ", userJob=" + userJob
				+ ", userOffice=" + userOffice + ", secretLevelId="
				+ secretLevelId + ", userTypeId=" + userTypeId
				+ ", userStateID=" + userStateID + ", secretProvide="
				+ secretProvide + ", isDelete=" + isDelete + ", staffPhoto="
				+ staffPhoto + ", jobPost=" + jobPost + ", account=" + account
				+ ", password=" + password + "]";
	}
}