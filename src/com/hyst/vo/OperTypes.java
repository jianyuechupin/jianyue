package com.hyst.vo;
/**
 * 操作权限集合类 	0为可用，1不可用，3
 * @author DongYi
 * @version 创建时间：2016年4月25日 上午9:39:58
 * 类说明
 */
public class OperTypes {
	/** 属性描述：增加权限*/
	private Action add;
	/** 属性描述：删除权限*/
	private Action delete;
	/** 属性描述：更新权限*/
	private Action update;
	/** 属性描述：查看权限*/
	private Action query;
	/** 属性描述：上传权限*/
	private Action upLoad;
	/** 属性描述：借出权限*/
	private Action borrow;
	/** 属性描述：归还权限*/
	private Action retur;
	
	
	public Action getAdd() {
		return add;
	}
	public void setAdd(Action add) {
		this.add = add;
	}
	public Action getDelete() {
		return delete;
	}
	public void setDelete(Action delete) {
		this.delete = delete;
	}
	public Action getUpdate() {
		return update;
	}
	public void setUpdate(Action update) {
		this.update = update;
	}
	public Action getQuery() {
		return query;
	}
	public void setQuery(Action query) {
		this.query = query;
	}
	public Action getUpLoad() {
		return upLoad;
	}
	public void setUpLoad(Action upLoad) {
		this.upLoad = upLoad;
	}
	public Action getBorrow() {
		return borrow;
	}
	public void setBorrow(Action borrow) {
		this.borrow = borrow;
	}
	public Action getRetur() {
		return retur;
	}
	public void setRetur(Action retur) {
		this.retur = retur;
	}
	
	
	
}
