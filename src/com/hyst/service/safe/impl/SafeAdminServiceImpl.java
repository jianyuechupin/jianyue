package com.hyst.service.safe.impl;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyst.dao.safe.DefinePowerDetilTblDao;
import com.hyst.dao.safe.OrgnizationDao;
import com.hyst.dao.safe.PowerGroupTblDao;
import com.hyst.dao.safe.TableInfoDao;
import com.hyst.dao.safe.TableOperDao;
import com.hyst.dao.safe.TableOperViewDao;
import com.hyst.dao.safe.UserGroupDao;
import com.hyst.dao.safe.UserGroupListTblDao;
import com.hyst.dao.safe.UserGroupPowerDetailDao;
import com.hyst.dao.safe.UserGroupViewDao;
import com.hyst.dao.safe.UserPowerDetailTblDao;
import com.hyst.dao.safe.UserPowerManageViewDao;
import com.hyst.service.safe.SafeAdminService;
import com.hyst.service.safe.TableInfoUtil;
import com.hyst.vo.DefinePowerDetilTbl;
import com.hyst.vo.Orgnization;
import com.hyst.vo.PowerGroupDetails;
import com.hyst.vo.PowerGroupTbl;
import com.hyst.vo.TableInfo;
import com.hyst.vo.TableOper;
import com.hyst.vo.TableOperView;
import com.hyst.vo.UserGroup;
import com.hyst.vo.UserGroupListTbl;
import com.hyst.vo.UserGroupPowerDetail;
import com.hyst.vo.UserGroupView;
import com.hyst.vo.UserPowerDetailTbls;
import com.hyst.vo.UserPowerManageView;

/**
 * 安全管理Service
 * @author DongYi
 * @version 创建时间：2016年4月26日 下午2:05:02
 * 类说明
 */
@Service("safeAdminService")
public class SafeAdminServiceImpl implements SafeAdminService{
	/**用户组Dao*/
	@Autowired
	private  UserGroupDao userGroupDao;
	/**菜单树Dao*/
	@Autowired
	private TableInfoDao tableInfoDao;
	/**菜单权限Dao*/
	@Autowired
	private TableOperDao tableOperDao;
	/**菜单权限视图Dao*/
	@Autowired
	private TableOperViewDao tableOperViewDao;
	/**用户组细则DAO*/
	@Autowired
	private UserGroupPowerDetailDao userGroupPowerDetailDao;
	/**用户组用户关联DAO*/
	@Autowired
	private UserGroupListTblDao userGroupListTblDao;
	/**部门Dao*/
	@Autowired
	private OrgnizationDao orgnizationDao;
	@Autowired
	private PowerGroupTblDao powerGroupTblDao;
	@Autowired
	private DefinePowerDetilTblDao definePowerDetilTblDao;
	@Autowired
	private UserPowerManageViewDao userPowerManageViewDao;
	@Autowired
	private UserPowerDetailTblDao userPowerDetailTblDao;
	@Autowired
	private UserGroupViewDao userGroupViewDao;
//****************************用户组管理******************************************
	//取得指定用户组ID下用户组细则
	public List<UserGroupPowerDetail> getPowerDetilTblsByGroupId(
			String userGroupId) {
		//return userGroupPowerDetailDao.selectByGroupId(userGroupId);
		return null;
	}


	
	//取得用户组视图
	public List<UserGroupView> getUserGroups(int pageNo, int pageSize) {
		pageNo=pageNo<1?1:pageNo;
		pageSize=pageSize<1?1:pageSize;
		Map<String, Object> map=new HashMap<String, Object>();
		return userGroupViewDao.list(map);
	}
	//根据用户组ID查询用户组视图
	public UserGroupView getUserGroupViewById(UserGroupView userGroupView){
		return userGroupViewDao.getOne(userGroupView);
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
	 * 二级菜单功能加载完以后，去查询选择被选择的
	 * @param tableInfos 二级菜单列表
	 */
	public void setTableOpers(List<TableInfo> tableInfos){
		for (int i = 0; i < tableInfos.size(); i++) {
			List<TableOperView> list=tableOperViewDao.listByTableId(tableInfos.get(i).getId());
			tableInfos.get(i).setTableOperViews(list);
		}
	}
	/**
	 * 获取部门列表
	 * @return
	 */
	public List<Orgnization> getOrgs(){
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("isDelete", 0);
		List<Orgnization> orgs= orgnizationDao.list(null);
		System.out.println(orgs);
		return orgs;
	}
	/**
	 * 为菜单权限视图设置状态及部门
	 * @param list
	 */
	public void setStates(List<TableOperView> list){
		List<TableOperView> listsList=null;
		for (int i = 0; i < list.size(); i++) {
			
		}
	}

	public boolean addUserGroupList(int[] userIds,int groupId) {
		int num2=userGroupListTblDao.deleteByUserGroupId(groupId);
		List<UserGroupListTbl> userGroupListTbls=new ArrayList<UserGroupListTbl>();
		for (int i = 0; i < userIds.length; i++) {
			userGroupListTbls.add(new UserGroupListTbl(userIds[i], groupId));
		}
		int num =userGroupListTblDao.batchInsert(userGroupListTbls);
		if (num==userGroupListTbls.size()) {
			return true;
		}
		return false;
	}
	//****************************权限组操作************************************//
	/**
	 * 批量删除原有的权限，再批量插入新权限
	 * @param details 用户权限细则
	 * @param pid 二级菜单的父ID
	 * @return
	 */
	public boolean addDetails(List<UserGroupPowerDetail> details,Integer parentId ){
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("parentId", parentId);
		map.put("userGroupId", details.get(0).getUserGroupId());
		userGroupPowerDetailDao.batchDelete(map);
		int num=userGroupPowerDetailDao.batchInsert(details);
		if (num==details.size()) {
			return true;
		}
		return false;
	}
	/**
	 * 检查权限组名称是否可用，true 可用 false不可用
	 */
	public boolean checkPowerGroupName(PowerGroupTbl powerGroupTbl) {
		PowerGroupTbl power=powerGroupTblDao.getByName(powerGroupTbl);
		//如果查询结果为空表示名称可用，或者查询结果不为空，且查询出的权限组的ID等于传入的ID也可用
		if (power==null||power.getId().equals(powerGroupTbl.getId())) {
			return true ;
		}
		return false;
	}
	//根据权限组ID查询权限组
	public PowerGroupTbl getPowerGroupById(String id){
		 return powerGroupTblDao.getOne(id);
	}
	//---保存权限组细则---
	public String savePowerGroupDetails(PowerGroupDetails powerGroupDetails) {
		//取得前台传来的权限组对象，然后判断是否存在若存在，不存则保存入库，存在则更新
		PowerGroupTbl power=powerGroupDetails.getPowerGroup();
		PowerGroupTbl powerg=powerGroupTblDao.getOne(power);
		if (powerg==null) {
			System.out.println("权限组数据为:\t"+power);
			powerGroupTblDao.insert(power);
			System.out.println("该ID数据不存在，插入权限组ID");
		}else {
			powerGroupTblDao.update(power);
			System.out.println("数据已经存在，更新权限组记录");
			//删除以前可能已经赋值的
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("parentId",powerGroupDetails.getPid());
			map.put("powerGroupID", power.getId());
			definePowerDetilTblDao.batchDelete(map);
		}
		//将权限组细则数据保存入库
		List<DefinePowerDetilTbl> list=powerGroupDetails.getDetails();
		if (list!=null&&list.size()>0) {
			int num=definePowerDetilTblDao.batchInsert(list);
		}
		return "secuess";			
		
	}
	//根据权限组Id查询权限细则,主要用于前台的选择状态
	public List<DefinePowerDetilTbl> getDefinePowerDetilTblsByPowerId(
			String powerGroupId) {
		if (powerGroupId==null||powerGroupId.length()==0) {
			return null;
		}
		return definePowerDetilTblDao.getByPowerGroupId(powerGroupId);
		
	}
	//获取权限组列表
	public List<PowerGroupTbl> getPowerGroupTbls() {
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("pagesize", 10);
		return powerGroupTblDao.list(map);
	}
	//删除权限组
	public String deletePowerGroup(PowerGroupTbl powerGroupTbl) {
		if (powerGroupTbl==null||powerGroupTbl.getId()==null) {
			return "删除失败";
		}
		int num= powerGroupTblDao.delete(powerGroupTbl);
		if (num>0) {
			return "删除成功";
		}
		return "删除失败";
	}
	//***********************用户权限管理****************************************//
	/**
	 * 用户权限组管理，获取用户列表
	 * @return
	 */
	public List<UserPowerManageView> getUserPowerManageViews(){
		System.out.println(userPowerManageViewDao==null);
		return userPowerManageViewDao.list(null);
	}
	//跟据用户ID查询出用户视图
	public UserPowerManageView getUser(int uid) {
		UserPowerManageView userPowerManageView =userPowerManageViewDao.getOne(uid);
		return userPowerManageView;
	}
	//保存用户权限细则《只是当前页信息》
	public String batchSaveUserPowers(
			UserPowerDetailTbls userPowerDetailTbls) {
		//验证传进来的权限细则集合是否为空
		if (userPowerDetailTbls.getUserPowers()!=null&&userPowerDetailTbls.getUserPowers().size()!=0) {	
			userPowerDetailTblDao.batchInsert(userPowerDetailTbls.getUserPowers());
		}else{
			System.out.println("要保存的权限细则为空");
		}
		return null;
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
	public void setTableOperViewDao(TableOperViewDao tableOperViewDao) {
		this.tableOperViewDao = tableOperViewDao;
	}
	public void setUserGroupPowerDetailDao(
			UserGroupPowerDetailDao userGroupPowerDetailDao) {
		this.userGroupPowerDetailDao = userGroupPowerDetailDao;
	}
	public void setUserGroupListTblDao(UserGroupListTblDao userGroupListTblDao) {
		this.userGroupListTblDao = userGroupListTblDao;
	}
	public void setOrgnizationDao(OrgnizationDao orgnizationDao) {
		this.orgnizationDao = orgnizationDao;
	}
	public void setPowerGroupTblDao(PowerGroupTblDao powerGroupTblDao) {
		this.powerGroupTblDao = powerGroupTblDao;
	}
	public void setDefinePowerDetilTblDao(
			DefinePowerDetilTblDao definePowerDetilTblDao) {
		this.definePowerDetilTblDao = definePowerDetilTblDao;
	}
	
	public void setUserPowerManageViewDao(
			UserPowerManageViewDao userPowerManageViewDao) {
		this.userPowerManageViewDao = userPowerManageViewDao;
	}


	public void setUserPowerDetailTblDao(
			UserPowerDetailTblDao userPowerDetailTblDao) {
		this.userPowerDetailTblDao = userPowerDetailTblDao;
	}
	public void setUserGroupViewDao(UserGroupViewDao userGroupViewDao) {
		this.userGroupViewDao = userGroupViewDao;
	}


}
