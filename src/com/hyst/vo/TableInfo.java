package com.hyst.vo;

import java.util.List;

public class TableInfo implements Comparable<TableInfo>{
	/**属性描述： 菜单ID*/
	private int id;
	/**属性描述：菜单名称 */
	private String tableName;
	/**属性描述： */
	private String tableNameChs;
	/**属性描述：删除标示，0未删除，1删除 */
	private int isDelete;
	/**属性描述：菜单序号 */
	private int sort;
	/**属性描述：父菜单ID，没有为0 */
	private int parentId;
	/**属性描述：访问连接 */
	private String doMain;
	/**属性描述：是否需要精确至部门级权限，0不需要，1需要*/
	private String hasOrg;
	/** 属性描述：子菜单列表 */
	private List<TableInfo> tableInfoList;

	/** 属性描述：操作功能列表 */	
	private List<TableOperView> tableOperViews;
	
	private List<TableOper> tableOpers;
	
	
	public List<TableOperView> getTableOperViews() {
		return tableOperViews;
	}
	public void setTableOperViews(List<TableOperView> tableOperViews) {
		this.tableOperViews = tableOperViews;
	}
	@Override
	public String toString() {
		return "TableInfo [id=" + id + ", tableName=" + tableName
				+ ", tableNameChs=" + tableNameChs + ", isDelete=" + isDelete
				+ ", sort=" + sort + ", parentId=" + parentId + ", doMain="
				+ doMain + ", hasOrg=" + hasOrg + ", tableInfoList="
				+ tableInfoList  + ", tableOpers="
				+ tableOpers + "]";
	}
	public String getHasOrg() {
		return hasOrg;
	}
	public void setHasOrg(String hasOrg) {
		this.hasOrg = hasOrg;
	}
	public List<TableOper> getTableOpers() {
		return tableOpers;
	}
	public void setTableOpers(List<TableOper> tableOpers) {
		this.tableOpers = tableOpers;
	}

	public List<TableInfo> getTableInfoList() {
		return tableInfoList;
	}
	public void setTableInfoList(List<TableInfo> tableInfoList) {
		this.tableInfoList = tableInfoList;
	}
	public int getId(){
		return this.id;
	}
	public String getTableName(){
		return this.tableName;
	}
	public String getTableNameChs(){
		return this.tableNameChs;
	}
	public int getIsDelete(){
		return this.isDelete;
	}
	public int getSort(){
		return this.sort;
	}
	public int getParentId(){
		return this.parentId;
	}
	public String getDoMain(){
		return this.doMain;
	}
	public void setId(int id){
		this.id = id;
	}
	public void setTableName(String tableName){
		this.tableName = tableName;
	}
	public void setTableNameChs(String tableNameChs){
		this.tableNameChs = tableNameChs;
	}
	public void setIsDelete(int isDelete){
		this.isDelete = isDelete;
	}
	public void setSort(int sort){
		this.sort = sort;
	}
	public void setParentId(int parentId){
		this.parentId = parentId;
	}
	public void setDoMain(String doMain){
		this.doMain = doMain;
	}
	/**
	 * 根据菜单的顺序进行排序
	 */
	@Override
	public int compareTo(TableInfo o) {
		return this.sort-o.getSort();
	}
}