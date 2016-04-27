package com.hyst.vo;
public class TableOper{
	/**属性描述： */
	private int id;
	/**属性描述： */
	private int tableId;
	/**属性描述： */
	private int operTypeId;
	/**属性描述： */
	private String domain;
	public int getId(){
		return this.id;
	}
	public int getTableId(){
		return this.tableId;
	}
	public int getOperTypeId(){
		return this.operTypeId;
	}
	public String getDomain(){
		return this.domain;
	}
	public void setId(int id){
		this.id = id;
	}
	public void setTableId(int tableId){
		this.tableId = tableId;
	}
	public void setOperTypeId(int operTypeId){
		this.operTypeId = operTypeId;
	}
	public void setDomain(String domain){
		this.domain = domain;
	}
}