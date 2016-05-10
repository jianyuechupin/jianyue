package com.hyst.vo;

import java.util.List;

/**
 * @author DongYi
 * @version 创建时间：2016年4月29日 下午3:04:46
 * 类说明
 */
public class SafeList {
	/** tabloper*/
	private List<TableOperView> tableOperViews;

	public List<TableOperView> getTableOperViews() {
		return tableOperViews;
	}

	public void setTableOperViews(List<TableOperView> tableOperViews) {
		this.tableOperViews = tableOperViews;
	}

	@Override
	public String toString() {
		return "SafeList [tableOperViews=" + tableOperViews + "]/n";
	}
	
	
}
