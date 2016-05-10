package com.hyst.vo;

import java.util.List;

import com.hyst.vo.user.Dept;

/**
 * 权限状态类
 * @author DongYi
 * @version 创建时间：2016年4月25日 上午9:42:42
 * 类说明
 */
public class Action {
	/** 功能权限对象*/
	private OperType operType;
	/** 0可用，1不可用，3不显示*/
	private int state;
	/** 部门列表*/
	private List<Dept> list;
	
	
	@Override
	public String toString() {
		return "Action [operType=" + operType + ", state=" + state + ", list="
				+ list + "]";
	}
	public OperType getOperType() {
		return operType;
	}
	public void setOperType(OperType operType) {
		this.operType = operType;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public List<Dept> getList() {
		return list;
	}
	public void setList(List<Dept> list) {
		this.list = list;
	}
	
}
