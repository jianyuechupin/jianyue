package com.hyst.vo;
/**
 * @author DongYi
 * @version 创建时间：2016年5月5日 下午3:56:15
 * 类说明 权限组类
 */
public class PowerGroupTbl {
	/**权限组ID《唯一标示符》*/
	private String id;
	/**权限组名称*/
	private String powerGroup;
	/**权限组描述*/
	private String remark;
	/**删除标记*/
	private int isDelete;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPowerGroup() {
		return powerGroup;
	}
	public void setPowerGroup(String powerGroup) {
		this.powerGroup = powerGroup;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	@Override
	public String toString() {
		return "PowerGroupTbl [id=" + id + ", powerGroup=" + powerGroup
				+ ", remark=" + remark + ", isDelete=" + isDelete + "]";
	}
	
}
