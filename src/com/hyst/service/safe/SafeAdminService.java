package com.hyst.service.safe;

import java.util.List;















import com.hyst.vo.DefinePowerDetilTbl;
import com.hyst.vo.Orgnization;
import com.hyst.vo.PowerDetails;
import com.hyst.vo.PowerGroupDetails;
import com.hyst.vo.PowerGroupTbl;
import com.hyst.vo.TableInfo;
import com.hyst.vo.UserGroup;
import com.hyst.vo.UserGroupListTbl;
import com.hyst.vo.UserGroupPowerDetail;
import com.hyst.vo.UserGroupView;
import com.hyst.vo.UserPowerDetailTbls;
import com.hyst.vo.UserPowerManageView;

/**
 * @author DongYi
 * @version 创建时间：2016年4月26日 下午2:04:36
 * 类说明
 */
public interface SafeAdminService {

	/**
	 * 获取用户组视图列表
	 * @param pageNo 当前页数
	 * @param pageSize 每页大小
	 * @return 用户组集合
	 */
	public List<UserGroupView> getUserGroups(int pageNo, int pageSize);

	/**
	 * 根据父ID查询列表
	 * @param pid 父ID
	 * @return
	 */
	public List<TableInfo> creatTbaleInfo(int pid);
	/**
	 * 向用户组添加用户
	 * @param userIds 用户组Id集合
	 * @param groupId 用户组ID
	 * @return 操作状态
	 */
	public boolean addUserGroupList(int[] userIds,int groupId);
	/**
	 * 根据用户组ID查询用户组所有权限细则
	 * @param userGroupId
	 * @return
	 */
	public List<UserGroupPowerDetail> getPowerDetilTblsByGroupId(String userGroupId);
	
	/**
	 * 根据用户组ID查询用户组视图
	 * @param userGroupView
	 * @return
	 */
	public UserGroupView getUserGroupViewById(UserGroupView userGroupView);
	/**
	 * 添加用户组细则
	 * @param details 用户组细则集合
	 * @param pid 一级菜单的ID
	 * @return 添加状态
	 */
	public boolean addDetails(PowerDetails powerDetalils);
	/**
	 * 为每个二级菜单设置下面的增删查改等功能按钮
	 * @param tableInfos 二级菜单列表
	 * 
	 */
	public void setTableOpers(List<TableInfo> tableInfos);
	/**
	 * 获取部门列表
	 * @return 部门列表
	 */
	public List<Orgnization> getOrgs();

	/**
	 * 检查权限组名称是否可用
	 * @param powerGroupTbl 要验证的权限组对象
	 * @return true 可用，false不可用
	 */
	public boolean checkPowerGroupName(PowerGroupTbl powerGroupTbl);
	/**
	 * 保存权限组--权限细则
	 * @param powerGroupDetails 包含权限组对象，权限细则集合的封装对象
	 * @return 操作状态
	 */
	public String savePowerGroupDetails(PowerGroupDetails powerGroupDetails);
	/**
	 * 根据权限组Id查询权限细则
	 * @param powerGroupId 权限组ID
	 * @return 权限组细则集合
	 */
	public List<DefinePowerDetilTbl> getDefinePowerDetilTblsByPowerId(String powerGroupId);
	/**
	 * 获取权限组列表
	 * @return 权限组列表
	 */
	public List<PowerGroupTbl> getPowerGroupTbls();
	/**
	 * 根据权限组的ID删除权限组
	 * @param powerGroupTbl 权限组对象，根据它的ID进行删除标示
	 * @return 操作状态 成功或者失败
	 */
	public String deletePowerGroup(PowerGroupTbl powerGroupTbl);
	/**
	 * 根据权限组ID查询权限组
	 * @param id 权限组ID
	 * @return
	 */
	public PowerGroupTbl getPowerGroupById(String id);
	//*************************用户权限管理**********************************//
	/**
	 * 用户权限组管理，取得用户列表
	 * @return
	 */
	public List<UserPowerManageView> getUserPowerManageViews();

	/**
	 * 根据用户ID查询用户视图对象
	 * @param uid 用户ID
	 * @return 用户视图
	 */
	public UserPowerManageView getUser(int uid);
	/**
	 * 保存当前页权限细则，批量插入用户权限细则数据
	 * 《根据用户ID和一级菜单ID先删除所有该菜单下的旧的数据，然后批量插入新数据》
	 * @param UserPowerDetailTbls
	 * @return 操作状态 
	 */
	public String batchSaveUserPowers(UserPowerDetailTbls userPowerDetailTbls);

	/**
	 * 根据用户组ID查询权限细则 <用户组>
	 * @param id 用户组ID
	 * @return 用户组权限细则列表
	 */
	public List<UserGroupPowerDetail> getUserGroupPowerDetails(String userGroupId);
	/**
	 * 用户查询 <用户查询>
	 * @param userPowerManageView
	 * @return 
	 */
	public List<UserPowerManageView> selectUsers(UserPowerManageView userPowerManageView);
}
