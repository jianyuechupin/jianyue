package com.hyst.service.safe;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;

import com.hyst.dao.safe.TableInfoDao;
import com.hyst.dao.safe.TableOperDao;
import com.hyst.vo.TableInfo;
import com.hyst.vo.TableOper;


/**
 * @author DongYi
 * @version 创建时间：2016年4月25日 下午2:44:46
 * 类说明
 */
public class TableInfoUtil {
	@Autowired
	private TableInfoDao tableInfoDao;
	/**
	 * 后期去缓存里面读取
	 */
	@Autowired
	private static TableOperDao tableOperDao;

	public void setTableOperDao(TableOperDao tableOperDao) {
		this.tableOperDao = tableOperDao;
	}
	
		
	public void setTableInfoDao(TableInfoDao tableInfoDao) {
		this.tableInfoDao = tableInfoDao;
	}

	/**
	 * 根据父菜单集合得到所有子菜单
	 * @param flist 父菜单的集合
	 * @return 包含子菜单的父菜单的集合
	 */
	public static List<TableInfo> getSonTable(List<TableInfo> flist,TableInfoDao tableInfoDao,TableOperDao tableOperDao){
		for (int i = 0; i < flist.size(); i++) {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("parentId", flist.get(i).getId());
			List<TableInfo> slist= tableInfoDao.listByParentId(map);
			System.out.println("排序前\t"+slist);
			setTableOpers(slist,tableOperDao);
			sortList(slist);
			System.out.println("排序后\t"+slist);
			flist.get(i).setTableInfoList(slist);
		}
		return flist;
	};	
	
	public static void setTableOpers(List<TableInfo> slist,TableOperDao tableOperDao){
		for (int i = 0; i < slist.size(); i++) {
			List< TableOper>list=tableOperDao.listByTableId(slist.get(i).getId());
			slist.get(i).setTableOpers(list);
		}
		
	}
	
	
	/**
	 * 对list进行排序
	 * @param list
	 */
	@SuppressWarnings("unchecked")
	public static void sortList(List list){
		Collections.sort(list);
	}
}
