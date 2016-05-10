package com.hyst.vo;

import java.util.Arrays;

public class TableOperView{
	/**属性描述： */
	private int tableId;
	/**属性描述： */
	private int operTypeId;
	/**属性描述： */
	private int id;
	/**属性描述： */
	private String domain;
	/**属性描述： */
	private String operTypeName;
	/**属性描述： */
	private String tableName;
	/**属性描述：部门id数组*/
	private int[] deptsId;
	/**属性描述：选择状态 0未选 1选择*/
	private int state;
	
	
	public int getState() {
		return state;
	}
	/**
	 * 设置选择状态，0未选（默认）1选择
	 * @param state
	 */
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "TableOperView [id=" + id + ", domain=" + domain
				+ ", operTypeName=" + operTypeName + ", tableName=" + tableName
				+ ", deptsId=" + Arrays.toString(deptsId) + "]\n";
	}
	public void setDeptsId(int[] deptsId) {
		this.deptsId = deptsId;
	}
	public int[] getDeptsId() {
		return deptsId;
	}
	public int getTableId(){
		return this.tableId;
	}
	public int getOperTypeId(){
		return this.operTypeId;
	}
	public int getId(){
		return this.id;
	}
	public String getDomain(){
		return this.domain;
	}
	public String getOperTypeName(){
		return this.operTypeName;
	}
	public String getTableName(){
		return this.tableName;
	}
	public void setTableId(int tableId){
		this.tableId = tableId;
	}
	public void setOperTypeId(int operTypeId){
		this.operTypeId = operTypeId;
	}
	public void setId(int id){
		this.id = id;
	}
	public void setDomain(String domain){
		this.domain = domain;
	}
	public void setOperTypeName(String operTypeName){
		this.operTypeName = operTypeName;
	}
	public void setTableName(String tableName){
		this.tableName = tableName;
	}
}