package com.hyst.dao.org;

import java.util.List;

import com.hyst.dao.BaseDao;
import com.hyst.vo.Orgnization;

public interface OrgDao extends BaseDao<Orgnization> {
	public String maxId(int orgLevel);//获取最大ID
	public String maxpId(int pId);//获取最大父ID
	public int countpId(int pId);//获取二级部门的个数
	public List<Orgnization> orgList();//获取全部部门列表
}
