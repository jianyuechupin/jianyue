package com.hyst.vo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UserGroupPowerDetail{
	/**属性描述： */
	private int id;
	/**属性描述：用户组表Id */
	private int userGroupId;
	/**属性描述：菜单权限表Id */
	private int tableOperID;
	/**属性描述： */
	private String deptList;
	private Set<String> deptIds;
	
	
	
	@Override
	public String toString() {
		return "UserGroupPowerDetail [tableOperID=" + tableOperID
				+ ", deptList=" + deptList + ", deptIds=" + deptIds + "]/n";
	}
	public Set<String> getDeptIds() {
		return deptIds;
	}
	public void setDeptIds(Set<String> deptIds) {
		this.deptIds = deptIds;
	}
	public UserGroupPowerDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param userGroupId
	 * @param tableOperID
	 * @param deptList
	 */
	public UserGroupPowerDetail(int userGroupId, int tableOperID,
			String deptList) {
		super();
		this.userGroupId = userGroupId;
		this.tableOperID = tableOperID;
		this.deptList = deptList;
	}
	public int getId(){
		return this.id;
	}
	public int getUserGroupId(){
		return this.userGroupId;
	}
	public int getTableOperID(){
		return this.tableOperID;
	}
	public String getDeptList(){
		return this.deptList;
	}
	public void setId(int id){
		this.id = id;
	}
	public void setUserGroupId(int userGroupId){
		this.userGroupId = userGroupId;
	}
	public void setTableOperID(int tableOperID){
		this.tableOperID = tableOperID;
	}
	public void setDeptList(String deptList){
		this.deptList = deptList;
		deptList=deptList.substring(0,deptList.length()-1);
		String[] deptz=deptList.split("\\|");
		setDeptIds(new HashSet<String>(Arrays.asList(deptz)));
	}

}