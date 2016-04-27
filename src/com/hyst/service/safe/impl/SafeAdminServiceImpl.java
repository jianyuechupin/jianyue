package com.hyst.service.safe.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hyst.dao.safe.TableInfoDao;
import com.hyst.dao.safe.TableOperDao;
import com.hyst.dao.safe.UserGroupDao;
import com.hyst.service.safe.SafeAdminService;
import com.hyst.service.safe.TableInfoUtil;
import com.hyst.vo.TableInfo;
import com.hyst.vo.TableOper;
import com.hyst.vo.UserGroup;

/**
 * 安全管理Service
 * @author DongYi
 * @version 创建时间：2016年4月26日 下午2:05:02
 * 类说明
 */
@Service("safeAdminService")
public class SafeAdminServiceImpl implements SafeAdminService{
	@Autowired
	private  UserGroupDao userGroupDao;
	@Autowired
	private TableInfoDao tableInfoDao;
	@Autowired
	private TableOperDao tableOperDao;
//	@Autowired
//	private TableOperDao tableOperViewDao;

	//取得用户组
	public List<UserGroup> getUserGroups(int pageNo, int pageSize) {
		pageNo=pageNo<1?1:pageNo;
		pageSize=pageSize<1?1:pageSize;
		Map<String, Object> map=new HashMap<String, Object>();
		return userGroupDao.list(map);
	}

	//创建授权页信息
	public List<TableInfo> creatTbaleInfo(int pid) {
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("parentId", pid);
		//得到顶级菜单项
		List<TableInfo> tableInfos=tableInfoDao.listByParentId(map);
		if (pid!=0) {
			setTableOpers(tableInfos);;
		}
		return tableInfos;
	}
	/**
	 * 为每个二级菜单设置下面的增删查改等功能按钮
	 * @param slist 二级菜单列表
	 */
	public void setTableOpers(List<TableInfo> slist){
		for (int i = 0; i < slist.size(); i++) {
//			List< TableOper>list=tableOperViewDao.listByTableId(slist.get(i).getId());
//			slist.get(i).setTableOpers(list);
		}
	}
	//***********************非业务方法******************************************//
	public void setUserGroupDao(UserGroupDao userGroupDao) {
		this.userGroupDao = userGroupDao;
	}
	public void setTableInfoDao(TableInfoDao tableInfoDao) {
		this.tableInfoDao = tableInfoDao;
	}
	
	public void setTableOperDao(TableOperDao tableOperDao) {
		this.tableOperDao = tableOperDao;
	}
}
