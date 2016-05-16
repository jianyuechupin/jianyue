package com.hyst.controller.user;


import java.util.List;

import org.apache.logging.log4j.core.helpers.SystemClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyst.service.safe.SafeAdminService;
import com.hyst.vo.DefinePowerDetilTbl;
import com.hyst.vo.Orgnization;
import com.hyst.vo.PowerDetails;
import com.hyst.vo.PowerGroupDetails;
import com.hyst.vo.PowerGroupTbl;
import com.hyst.vo.SafeList;
import com.hyst.vo.TableInfo;
import com.hyst.vo.UserGroup;
import com.hyst.vo.UserGroupPowerDetail;
import com.hyst.vo.UserGroupView;
import com.hyst.vo.UserPowerDetailTbl;
import com.hyst.vo.UserPowerDetailTbls;
import com.hyst.vo.UserPowerManageView;
import com.hyst.vo.user.UserInfo;

/**
 * @author DongYi
 * @version 创建时间：2016年4月20日 下午1:43:09
 * 类说明
 */
@Controller
@RequestMapping("safe")
public class SafeAdminController {
	@Autowired
	private SafeAdminService safeAdminService;

	public void setSafeAdminService(SafeAdminService safeAdminService) {
		this.safeAdminService = safeAdminService;
	}

	
 //********************用户组管理****************************************************
	/**
	 * 跳转到用户组页面《负责页面跳转  用户组》
	 * @param map
	 * @return
	 */
	@RequestMapping("userGroupManage")
	public String userGroupList(ModelMap map){
		map.addAttribute("list", safeAdminService.getUserGroups(0, 0));
		return "/WEB-INF/view/safe/usergroup/usergrouplist.jsp";
	}
	/**
	 * 获取用户组列表
	 * @return
	 */
	@RequestMapping("userGroupAjax")
	@ResponseBody
	public List<UserGroupView> getUserGroups(){
		
		return safeAdminService.getUserGroups(0, 0);
	}
	//跳转到修改、新增用户组界面
	/**
	 * 跳转到用户组详情页面，用于用户组新增和修改《负责跳转页面到   用户组细则》
	 * @param userGroupView
	 * @param map
	 * @return
	 */
	@RequestMapping("creatUserGroup")
	public String creatUserGroup(UserGroupView userGroupView,ModelMap map){
		if (userGroupView!=null&&userGroupView.getId()!=null) {
			userGroupView=safeAdminService.getUserGroupViewById(userGroupView);
		}else {
			userGroupView.setId(System.currentTimeMillis()+"");
		}
		System.out.println(userGroupView);
		map.addAttribute("usergroup", userGroupView);
		//添加1级菜单
		map.addAttribute("tableList", safeAdminService.creatTbaleInfo(0));
		return  "/WEB-INF/view/safe/usergroup/userGroupAdd.jsp";
	}
	/**
	 * 获取指定ID的权限组下权限细则的选中状态《异》
	 * @param powerGroupId 权限组ID
	 * @return 权限组细则集合
	 */
	@RequestMapping("getUserPowerChecked")
	@ResponseBody
	public List<DefinePowerDetilTbl> getUserPowerChecked(String userGroupId){
		
		return safeAdminService.getDefinePowerDetilTblsByPowerId(userGroupId);
	}
	/**
	 * 添加用户组细则<改进上一个方法>
	 * @param powerDetalils
	 * @return
	 */
	@RequestMapping("addUserGroupDetails")
	@ResponseBody
	public String addDetailss(PowerDetails powerDetalils){
		System.out.println("请求进来来-------------------------------------------------");
		System.out.println(powerDetalils);
		return null;
	}
	//***************************下面这个方法没用了
//	/**
//	 * 创建权限菜单页
//	 * 得到顶级菜单树列表
//	 * @return
//	 */
//	@RequestMapping("creatMenu")
//	//@ResponseBody
//	public String creat(ModelMap map){
//		map.addAttribute("list", safeAdminService.creatTbaleInfo(0));
//		return "/WEB-INF/view/safe/userGroupAdd.jsp";
//	}
	//***************************权限组操作***********************************//
	/**
	 *跳转到权限组列表页面
	 * @return
	 */
	@RequestMapping("powerGroup")
	public String powerGroup(){
		return "/WEB-INF/view/safe/powergroup/powergrouplist.jsp";
	}
	/**
	 * 获取权限组列表
	 * @return
	 */
	@RequestMapping("getPowerGroups")
	@ResponseBody
	public List<PowerGroupTbl> getPowerGroupTbls(){
		
		return safeAdminService.getPowerGroupTbls();
	}
	/**
	 * 删除权限组
	 * @param powerGroupTbl 权限组对象，主要接受权限组ID
	 * @return 跳转页面
	 */
	@RequestMapping("deletepowergroup")
	public String deletePowerGroup(PowerGroupTbl powerGroupTbl){
		safeAdminService.deletePowerGroup(powerGroupTbl);
		return "/safe/powerGroup.do";
	}
	/**
	 * 跳转到权限组新增页面《修改与新增权限组调用》
	 * @return 
	 */
	@RequestMapping("addpowergroup")
	public String powerGroup(String id,ModelMap map){
		if (id==null||id.length()==0) {
			id=+System.currentTimeMillis()+"";
		}
		map.addAttribute("powerGrou",safeAdminService.getPowerGroupById(id));
		map.addAttribute("list", safeAdminService.creatTbaleInfo(0));
		return "/WEB-INF/view/safe/powergroup/powergroupadd.jsp";
	}
	/**
	 * 判断权限组名称是否可用 《异步》
	 * @param powerGroupTbl 权限组对象
	 * @return true 可用，false不可用
	 */
	@RequestMapping("checkPowerGroupName")
	@ResponseBody
	public boolean checkName(PowerGroupTbl powerGroupTbl){
		System.out.println(powerGroupTbl);
		return safeAdminService.checkPowerGroupName(powerGroupTbl);
	}
	/**
	 * 保存权限组细则数据《异》
	 * @param details
	 * @return powerGroupDetails
	 */
	@RequestMapping("addpower")
	@ResponseBody
	public String powerGroupDetails(PowerGroupDetails details){
		
	
		String result=safeAdminService.savePowerGroupDetails(details);
		System.out.println("插入结果为-----------"+ result);
		return result ;
	}
	/**
	 * 获取指定ID的权限组下权限细则的选中状态《异》
	 * @param powerGroupId 权限组ID
	 * @return 权限组细则集合
	 */
	@RequestMapping("getChecked")
	@ResponseBody
	public List<DefinePowerDetilTbl> getChecked(String powerGroupId){
		return safeAdminService.getDefinePowerDetilTblsByPowerId(powerGroupId);
	}
	
	//***************************个人权限管理*************************************//
	/**
	 * 跳转到用户权限管理页面
	 * @return
	 */
	@RequestMapping("userPowerManager")
	public String userPowerManager(){
		
		return "/WEB-INF/view/safe/userPower/userpowermanage.jsp";
	}
	/**
	 * 异步取得用户权限管理的用户视图数据
	 * @return
	 */
	@RequestMapping("userPowerManageViews")
	@ResponseBody
	public List<UserPowerManageView> getUserPowerManageViews(){
		return safeAdminService.getUserPowerManageViews();
	}
	/**
	 * 跳转到用户权限细则页面《用于修改用户个性权限或变更用户组》
	 * @return
	 */
	@RequestMapping("userPowerDetails")
	public String getUserPowersDetails(int uid,ModelMap map){
		//添加用户视图
		map.addAttribute("userview",safeAdminService.getUser(uid));
		//添加用户组列表
		map.addAttribute("powerGroups", safeAdminService.getPowerGroupTbls());
		//添加1级菜单
		map.addAttribute("tableList", safeAdminService.creatTbaleInfo(0));
		//添加用户信息视图 。添加权限组。添加个性化权限
		
		//添加部门
		map.addAttribute("deptList", getOrgs());
		return "/WEB-INF/view/safe/userPower/userpoweradd.jsp";
	}
	/**
	 * 获取指定用户的权限细则
	 * @param userInfo 用户对象
	 * @return 
	 */
	@RequestMapping("getUserPowerDetails")
	@ResponseBody
	public List<UserPowerDetailTbl> getUserPowerDetails(UserInfo userInfo){
		return null;
	}
	/***
	 * 保存用户权限细则《分页保存-->即每个一级菜单下的权限单独保存》
	 * @param userPowerDetailTbls
	 * @return 保存状态，成功、失败
	 */
	@RequestMapping("saveUserPowers")
	@ResponseBody
	public String saveUserPowers(UserPowerDetailTbls userPowerDetailTbls){
		safeAdminService.batchSaveUserPowers(userPowerDetailTbls);
		return null;
	}
	//*******************************公共内容******************************************//
	/**
	 * 根据父ID，异步获取二级菜单列表《异》
	 * @param pid 父id
	 * @return 该Pid的子菜单集合
	 */
	@RequestMapping("sonMenu")
	@ResponseBody
	public List<TableInfo> getSonmenu(int pid){
		List<TableInfo> tableInfos=safeAdminService.creatTbaleInfo(pid);
		return tableInfos;
	}
	/**
	 * 获得部门列表《授权时选择部门菜单时候使用》
	 * @return 部门列表
	 */
	@RequestMapping("getOrgs")
	@ResponseBody
	public List<Orgnization> getOrgs(){
		
		return safeAdminService.getOrgs();
	}
	
	
	@RequestMapping("submit")
	@ResponseBody
	public String submitTable(SafeList safeList){
		System.out.println(safeList);
		return null;
	}
	

	/**
	 * 向用户组添加用户
	 * @param userIds 用户ID的数组
	 * @param groupId 用户组ID
	 * @return 添加状态 sucess 添加成功 ，false添加失败
	 */
	@RequestMapping("addUserToUserGroup")
	@ResponseBody
	public String addUserToUserGroup(int[] userIds,int groupId){
//		safeAdminService.addUserGroupList(userIds);
		return null;
	}
	/**
	 * 添加权限细则//添加之前删除所有本页菜单下的已经存在的权限，重新再添加
	 * @param tableOperIds 菜单权限ID
	 * @param groupId 用户组ID
	 * @param pid 该二级菜单的父菜单的ID
	 * @return 操作状态 sucess 添加成功 ，false添加失败
	 */
	@RequestMapping("addUserGroupDetails2")
	@ResponseBody
	public String addDetails(int [] ids,int groupId,int pid){
		for (int i = 0; i < ids.length; i++) {
			System.out.println(ids[i]);
		}
		List<UserGroupPowerDetail> list=null;
		safeAdminService.addDetails(list,pid);
		//System.out.println(ids);
		return "sucess";
	}

}
