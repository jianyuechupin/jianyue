package com.hyst.service.safe;

import java.util.List;

import com.hyst.vo.CreditManagerOrgsTbl;
import com.hyst.vo.CreditManagerTbl;
import com.hyst.vo.CreditManagerView;
import com.hyst.vo.WebRoleSettingTbl;
import com.hyst.vo.user.UserView;

/**
 * @author DongYi
 * @version 创建时间：2016年5月17日 下午5:28:33
 * 类说明 保密管理员service
 */
public interface CreditManagerService {
	////////////////////////---部门保密管理员设置----/////////////////////////
	/**
	 * 查询部门部门保密管理员列表
	 * @return
	 */
	public List<CreditManagerOrgsTbl> getCreditManagerOrgsTbls();
	/**
	 * 保存新增的部门保密管理员 <查询部门保密管理员>
	 * @param creditManagerOrgsTbl
	 * @return 操作状态
	 */
	public String saveCreditDetail(CreditManagerOrgsTbl creditManagerOrgsTbl);
	/**
	 * 根据保密管理员的ID查询该管理员管理的部门
	 * @param userId 保密管理员的用户ID
	 * @return
	 */
	public List<CreditManagerTbl> getOrgsByUserId(int userId);
	/**
	 * 根据ID查询保密管理员视图
	 * @param id 保密管理员视图ID
	 * @return
	 */
	public CreditManagerView getView(int id);
	/**
	 * 根据传入的保密管理员删除保密管理员信息 <保密管理员删除>
	 * @param creditManager
	 * @return 操作状态 
	 */
	public String deleteCreditManage(CreditManagerOrgsTbl creditManager);
	
	
	
	
	
	//*************************保密门户角色设置***************************************
	/**
	 * 获取保密门户角色
	 * @return 保密门户角色列表
	 */
	public List<WebRoleSettingTbl> getWebRoles();
	/**
	 * 根据ID查询保密门户角色信息
	 * @param id 
	 * @return 保密门户角色
	 */
	public WebRoleSettingTbl getWebRoleById(int id);
	/**
	 * 根据用户ID数组查询用户视图
	 * @param userIds 表示用户ID的字符串，以|分割
	 * @return
	 */
	public List<UserView> getUsersByIds(String userIds);
	/**
	 * 修改保密门户角色
	 * @param webRoleSettingTbl 被修改的对象
	 * @return
	 */
	public String updateWebRole(WebRoleSettingTbl webRoleSettingTbl);
}
