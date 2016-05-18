package com.hyst.controller.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyst.service.org.OrgService;
import com.hyst.vo.Orgnization;
@Controller 
@RequestMapping("orgInfo")
public class OrgController {
	@Autowired 
	private OrgService orgService;
	public void setOrganizationTblService(OrgService orgService){
		this.orgService=orgService;
	}
	
	@RequestMapping("orglist")
	public String main(){
		return "/WEB-INF/view/orgFile/organization.jsp";
	}
	
	@RequestMapping("orgXianshi")
    public String getlist(ModelMap map){
	map.addAttribute("list",orgService.getList());
		return "/WEB-INF/view/orgFile/organization.jsp";
	}
	@RequestMapping("orgadd")
	public String addlist(Orgnization organization){
		
		orgService.addList(organization);
	    
		return "orgXianshi.do";
	}
	@RequestMapping("getAllList")
	public String getAllList(Orgnization organization,ModelMap map){
		
		map.addAttribute("list",orgService.getAllList());
	    return "/WEB-INF/view/orgFile/orgList.jsp";
	}
}
