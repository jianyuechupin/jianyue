package com.hyst.service.safe;

import java.util.List;








import com.hyst.vo.DefinePowerDetilTbl;
import com.hyst.vo.Orgnization;
import com.hyst.vo.PowerGroupDetails;
import com.hyst.vo.PowerGroupTbl;
import com.hyst.vo.TableInfo;
import com.hyst.vo.UserGroup;
import com.hyst.vo.UserGroupListTbl;
import com.hyst.vo.UserGroupPowerDetail;

/**
 * @author DongYi
 * @version 创建时间：2016年4月26日 下午2:04:36
 * 类说明
 */
public interface SafeAdminService {

	/**
	 * 获取用户组列表
	 * @param pageNo 当前页数
	 * @param pageSize 每页大小
	 * @return 用户组集合
	 */
	public List<UserGroup> getUserGroups(int pageNo, int pageSize);

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
	 * 添加用户组细则
	 * @param details 用户组细则集合
	 * @param pid 一级菜单的ID
	 * @return 添加状态
	 */
	public boolean addDetails(List<UserGroupPowerDetail> details,Integer parentId);
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
}
