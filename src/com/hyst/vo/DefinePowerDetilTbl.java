package com.hyst.vo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 类说明 权限组细则模型
 * @author DongYi
 * @version 创建时间：2016年5月5日 下午3:59:03
 */
public class DefinePowerDetilTbl {
	/**主键ID*/
	private int id;
	/**权限组ID*/
	private String powerGroupId;
	/**菜单功能Id*/
	private int tableOperId;
	/**表示部门集合的字符串*/
	private String deptList=null;
	/**部门集合*/
	private Set<String> deptSet;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPowerGroupId() {
		return powerGroupId;
	}
	public void setPowerGroupId(String powerGroupId) {
		this.powerGroupId = powerGroupId;
	}
	public int getTableOperId() {
		return tableOperId;
	}
	public void setTableOperId(int tableOperId) {
		this.tableOperId = tableOperId;
	}
	public String getDeptList() {
		return deptList;
	}
	public void setDeptList(String deptList) {
		this.deptList = deptList.trim();
		deptList=deptList.substring(0,deptList.length()-1);
		String[] deptz=deptList.split("\\|");
		setDeptSet(new HashSet<String>(Arrays.asList(deptz)));
	}
	public Set<String> getDeptSet() {
		return deptSet;
	}
	public void setDeptSet(Set<String> deptSet) {
		this.deptSet = deptSet;
	}
	@Override
	public String toString() {
		return "DefinePowerDetilTbl [id=" + id + ", powerGroupId="
				+ powerGroupId + ", tableOperId=" + tableOperId + ", deptList="
				+ deptList + ", deptSet=" + deptSet + "]";
	}
	
}
