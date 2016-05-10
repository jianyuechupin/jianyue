package com.hyst.vo;

import java.util.List;

/**
 * @author DongYi
 * @version 创建时间：2016年5月5日 下午3:54:30
 * 类说明 权限组细则保存集合，主要用于接收前台集合数据
 */
public class PowerGroupDetails {
	/**权限组细则集合*/
	private List<DefinePowerDetilTbl> details;
	/**权限组对象*/
	private PowerGroupTbl powerGroup;
	/**该二级菜单的父菜单ID*/
	private int pid;
	
	public List<DefinePowerDetilTbl> getDetails() {
		return details;
	}
	public void setDetails(List<DefinePowerDetilTbl> details) {
		this.details = details;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public void setdetails(
			List<DefinePowerDetilTbl> details) {
		this.details = details;
	}
	public PowerGroupTbl getPowerGroup() {
		return powerGroup;
	}
	public void setPowerGroup(PowerGroupTbl powerGroup) {
		this.powerGroup = powerGroup;
	}
	@Override
	public String toString() {
		return "PowerGroupDetails [details=" + details + ", powerGroup="
				+ powerGroup + "]";
	}
	
	
}
