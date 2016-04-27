package com.hyst.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hyst.service.safe.SafeAdminService;
import com.hyst.vo.TableInfo;
import com.hyst.vo.UserDeptView;
import com.hyst.vo.UserGroup;

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
/*	@Autowired
	private UserDeptViewService userDeptViewService;
	public void setUserDeptViewService(UserDeptViewService userDeptViewService) {
		this.userDeptViewService = userDeptViewService;
	}*/
/*	@Autowired
	private TableInfoService tableInfoService;
	public void setTableInfoService(TableInfoService tableInfoService) {
		this.tableInfoService = tableInfoService;
	}*/
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
	 * 获取用户列表请求
	 * @param map
	 * @return
	 */
/*	@RequestMapping("userlist")
	@ResponseBody
	public List<UserDeptView> getUsers(ModelMap map){
		map.put("list", userDeptViewService.getList());
		return userDeptViewService.getList();
	}*/
	/**
	 * 请求用户组列表
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
	/**
	 * 根据父ID，异步获取菜单列表
	 * @param pid 父id
	 * @return
	 */
	@RequestMapping("sonMenu")
	@ResponseBody
	public List<TableInfo> getSonmenu(int pid){
		List<TableInfo> tableInfos=safeAdminService.creatTbaleInfo(pid);
		return tableInfos;
	}
}
