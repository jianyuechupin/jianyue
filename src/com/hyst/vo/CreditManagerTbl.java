package com.hyst.vo;

import java.sql.Timestamp;
/**
 * 部门保密管理员 部门细则表
 */
public class CreditManagerTbl{
	/**属性描述： */
	private int id;
	/**属性描述： */
	private String orgId;
	/**属性描述： */
	private int userInfoId;
	/**属性描述： */
	private int roleType;
	/**属性描述： */
	private Timestamp  creatTime;
	/**属性描述： */
	private Timestamp  updateTime;
	/**属性描述： */
	private int isValid;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + isValid;
		result = prime * result + ((orgId == null) ? 0 : orgId.hashCode());
		result = prime * result + roleType;
		result = prime * result + userInfoId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditManagerTbl other = (CreditManagerTbl) obj;
		if (isValid != other.isValid)
			return false;
		if (orgId == null) {
			if (other.orgId != null)
				return false;
		} else if (!orgId.equals(other.orgId))
			return false;
		if (roleType != other.roleType)
			return false;
		if (userInfoId != other.userInfoId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CreditManagerTbl [id=" + id + ", orgId=" + orgId
				+ ", userInfoId=" + userInfoId + ", roleType=" + roleType
				+ ", creatTime=" + creatTime + ", updateTime=" + updateTime
				+ ", isValid=" + isValid + "]";
	}
	public int getId(){
		return this.id;
	}
	public String getOrgId(){
		return this.orgId;
	}
	public int getUserInfoId(){
		return this.userInfoId;
	}
	public int getRoleType(){
		return this.roleType;
	}
	public Timestamp  getCreatTime(){
		return this.creatTime;
	}
	public Timestamp  getUpdateTime(){
		return this.updateTime;
	}
	public int getIsValid(){
		return this.isValid;
	}
	public void setId(int id){
		this.id = id;
	}
	public void setOrgId(String orgId){
		this.orgId = orgId;
	}
	public void setUserInfoId(int userInfoId){
		this.userInfoId = userInfoId;
	}
	public void setRoleType(int roleType){
		this.roleType = roleType;
	}
	public void setCreatTime(Timestamp  creatTime){
		this.creatTime = creatTime;
	}
	public void setUpdateTime(Timestamp  updateTime){
		this.updateTime = updateTime;
	}
	public void setIsValid(int isValid){
		this.isValid = isValid;
	}
}