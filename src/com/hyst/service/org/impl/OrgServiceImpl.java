package com.hyst.service.org.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hyst.dao.org.OrgDao;
import com.hyst.vo.Orgnization;
import com.hyst.service.org.OrgService;


@Service("orgService")
public class OrgServiceImpl implements OrgService {
	@Autowired
	private OrgDao orgDao;
	
	public List<Orgnization> getList() {
		Map map=new HashMap();
		map.put("isDelete", 0);
	    List<Orgnization> list=orgDao.list(map);
		System.err.println("执行结果为:\t"+list);
		return list;
	}
	public List<Orgnization> getAllList() {
	    List<Orgnization> list=orgDao.orgList();
//	   for (OrganizationTbl organizationTbl : list) {
//		
//	      }
		System.err.println("执行结果为:\t"+list);
		return list;
	}
	
	@Override
	public String addList(Orgnization organization)
	{
		System.err.println("父ID为：\t"+organization.getParentId());
		if(   organization.getParentId()==0)//如果是一级部门
		 {
			organization.setOrgLevel(1);
			 int maxid=Integer.parseInt(orgDao.maxId(1))+1;
			 organization.setOrgNumber(maxid+"");
	     }else{//如果非第二级部门
	    	 organization.setOrgLevel(2);
			 System.err.println("二级部门个数为：\t"+orgDao.countpId(organization.getParentId()));
			 if(orgDao.countpId(organization.getParentId())>0)//如果当前部门下的二级部门个数不为0
			 {
			 String maxpid=orgDao.maxpId(organization.getParentId());
			 System.err.println("maxpid:\t"+maxpid);
			 int id=Integer.parseInt(maxpid)+1;
			 organization.setOrgNumber(id+"");
			 }else {
				 organization.setOrgNumber(organization.getParentId()+"0001");//设置第一个二级部门
			}
		 }
		
		int i= orgDao.insert(organization);
	  if (i>0) {
			return "sucess";
		}else {
			return"false";
		}
	}
	
	

	
}
