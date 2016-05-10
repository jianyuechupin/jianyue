package com.hyst.controller.user;

import java.util.ArrayList;
import java.util.List;

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
import com.hyst.vo.UserGroupListTbl;
import com.hyst.vo.UserGroupPowerDetail;

/**
 * @author DongYi
 * @version 创建时间：2016年4月20日 下午1:43:09
 * 类说明
 */
@Controller
public class SafeAdminController {
	@Autowired
	private SafeAdminService safeAdminService;

	public void setSafeAdminService(SafeAdminService safeAdminService) {
		this.safeAdminService = safeAdminService;
	}
	/**
	 * 进入安全管理页面，页面完善后删除
	 * @return
	 */
	@RequestMapping("main")
	public String main(){
		//tableInfoService.creatMenu();
		return "/WEB-INF/view/safe/index.jsp";
	}
	
	
	/**
	 * 异步取得二级子菜单请求
	 * @return
	 */
	@RequestMapping("ajax")
	@ResponseBody
	public String getTree(){
		String s="<li><a id='userlist' href=\"javascript:;\" onclick='go(this)'>用户权限管理</a></li><li>"+
				"<a id='userGroupAjax' href=\"javascript:;\" >权限批授权管理</a></li>"+
				"<li><a id='userGroupAjax' href=\"javascript:;\">用户组管理</a></li>";
		return s;
	}
 

	/**
	 * 获取用户组列表
	 * @return
	 */
	@RequestMapping("userGroupAjax")
	@ResponseBody
	public List<UserGroup> getUserGroups(){
		
		return safeAdminService.getUserGroups(0, 0);
	}
	
	/**
	 * 创建权限菜单页
	 * 得到顶级菜单树列表
	 * @return
	 */
	@RequestMapping("creatMenu")
	//@ResponseBody
	public String creat(ModelMap map){
		map.addAttribute("list", safeAdminService.creatTbaleInfo(0));
		return "/WEB-INF/view/safe/userGroupAdd.jsp";
	}
	//***************************权限组操作***********************************//
	/**
	 * 跳转到权限组新增页面
	 * @return 
	 */
	@RequestMapping("powergroup")
	public String powerGroup(String id,ModelMap map){
		if (id==null||id.length()==0) {
			id=+System.currentTimeMillis()+"";
		}
		map.addAttribute("id",id);
		map.addAttribute("list", safeAdminService.creatTbaleInfo(0));
		return "/WEB-INF/view/safe/powergroupadd.jsp";
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
		System.err.println("请求进入提交权限组细则controller");
		System.out.println(details);
	
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
	//***************************************************************************//
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
}
