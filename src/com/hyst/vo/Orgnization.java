package com.hyst.vo;
public class Orgnization implements Comparable<Orgnization>{
	/**属性描述：ID */
	private int id;
	/**属性描述： 父部门ID，没有为0*/
	private int parentId;
	/**属性描述： 部门名称*/
	private String orgName;
	/**属性描述：部门级别 */
	private int orgLevel;
	/**属性描述：排序 */
	private int orderTax;
	/**属性描述： 删除标记，0未删除，1删除*/
	private int isDelete;
	
	
	public int getId(){
		return this.id;
	}
	public int getParentId(){
		return this.parentId;
	}
	public String getOrgName(){
		return this.orgName;
	}
	public int getOrgLevel(){
		return this.orgLevel;
	}
	public int getOrderTax(){
		return this.orderTax;
	}
	public int getIsDelete(){
		return this.isDelete;
	}
	public void setId(int id){
		this.id = id;
	}
	public void setParentId(int parentId){
		this.parentId = parentId;
	}
	public void setOrgName(String orgName){
		this.orgName = orgName;
	}
	public void setOrgLevel(int orgLevel){
		this.orgLevel = orgLevel;
	}
	public void setOrderTax(int orderTax){
		this.orderTax = orderTax;
	}
	public void setIsDelete(int isDelete){
		this.isDelete = isDelete;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Orgnization o) {
		// 
		return this.orderTax-o.getOrderTax();
	}
}