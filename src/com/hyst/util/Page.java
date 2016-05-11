package com.hyst.util;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 分页工具类
 * @author DongYi
 * @version 创建时间：2016年4月18日 上午9:37:32
 */
public class Page {
	/** 当前页数 */
	private int pageNo;
	/** 每页大小 */
	private int pageSize;
	/** 总记录数 */
	private int totalRecord;
	/** 总页数 */
	private int totalPage;
	/** 查询条件 */
	private Map<String, String> params;  
	/** 数组查询条件   */	
	private Map<String, List<String>> paramLists;  
	/** Url地址  */
	private String searchUrl;  
	/** 可以显示的页号(分隔符"|"，总页数变更时更新) */
	private String pageNoDisp;      
	private Page() {  
		pageNo = 1;  
		pageSize = Integer.valueOf(30);  
		totalRecord = 0;  
		totalPage = 0;  
		params = Maps.newHashMap();  
		paramLists = Maps.newHashMap();  
		searchUrl = "";  
		pageNoDisp = "";  
	  }  
	
}
