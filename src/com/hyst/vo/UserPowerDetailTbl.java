package com.hyst.vo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UserPowerDetailTbl{
	/**属性描述： */
	private int id;
	/**属性描述： */
	private int userId;
	/**属性描述： */
	private int tableOperId;
	/**属性描述： */
	private String deptList;
	/**部门列表的set集合*/
	private Set<String> deptSet;
	@Override
	public String toString() {
		return "UserPowerDetailTbl [id=" + id + ", userId=" + userId
				+ ", tableOperId=" + tableOperId + ", deptList=" + deptList
				+ "]";
	}
	
	public Set<String> getDeptSet() {
		return deptSet;
	}

	public void setDeptSet(Set<String> deptSet) {
		this.deptSet = deptSet;
	}

	public int getId(){
		return this.id;
	}
	public int getUserId(){
		return this.userId;
	}
	public int getTableOperId(){
		return this.tableOperId;
	}
	public String getDeptList(){
		return this.deptList;
	}
	public void setId(int id){
		this.id = id;
	}
	public void setUserId(int userId){
		this.userId = userId;
	}
	public void setTableOperId(int tableOperId){
		this.tableOperId = tableOperId;
	}
	public void setDeptList(String deptList){
		this.deptList = deptList;
		if(deptList.endsWith("\\|")){
			deptList=deptList.substring(0, deptList.lastIndexOf("\\|"));
		}
		String[]depts=deptList.split("\\|");
		setDeptSet(new HashSet<String>(Arrays.asList(depts)));
	}
}