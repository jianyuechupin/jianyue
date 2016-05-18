package com.hyst.service.safe;

import java.util.List;

import com.hyst.vo.CreditManagerOrgsTbl;

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
	 * @return
	 */
	public String saveCreditDetail(CreditManagerOrgsTbl creditManagerOrgsTbl);
	
	
	
	
	
	//*********************************************************************
}
