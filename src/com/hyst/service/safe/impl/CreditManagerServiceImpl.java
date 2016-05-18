package com.hyst.service.safe.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyst.dao.safe.CreditManagerOrgsTblDao;
import com.hyst.dao.safe.CreditManagerTblDao;
import com.hyst.service.safe.CreditManagerService;
import com.hyst.vo.CreditManagerOrgsTbl;
import com.hyst.vo.CreditManagerTbl;

/**
 * @author DongYi
 * @version 创建时间：2016年5月17日 下午5:34:03
 * 类说明 保密管理员service实现
 */
@Service("creditManagerService")
public class CreditManagerServiceImpl implements CreditManagerService{
	
	//////////////////////////-----部门保密管理员设置----////////////////////////////
	//查询部门保密管理员列表
	public List<CreditManagerOrgsTbl> getCreditManagerOrgsTbls() {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("page", null);
		return creditManagerOrgsTblDao.list(map);
	}
	
	//保存管理员详情
	public String saveCreditDetail(CreditManagerOrgsTbl creditManagerOrgsTbl) {
		if(creditManagerOrgsTbl==null||creditManagerOrgsTbl.getUserInfoId()==0){
			return "请先选择人员进行保存";
		}
		int num=creditManagerOrgsTblDao.insert(creditManagerOrgsTbl);
		List<CreditManagerTbl> creditManagerTbls=creditManagerOrgsTbl.getCreditManagerTbls();
		if (creditManagerTbls!=null) {//
			int num2=creditManagerTblDao.batchInsert(creditManagerTbls);
			if (num2==creditManagerTbls.size()&&num>0) {
				return "保存成功";
			}
		}else {
			if(num>0){
				return "保存成功";
			}
		}
		return "保存失败";
	}
	/////////////////////////---Dao等属性设置---/////////////////////////////////
	
	@Autowired
	private CreditManagerOrgsTblDao creditManagerOrgsTblDao;
	@Autowired
	private CreditManagerTblDao creditManagerTblDao;
	/////////////////////////---非业务方法处理---/////////////////////////////////
	
	public void setCreditManagerTblDao(CreditManagerTblDao creditManagerTblDao) {
		this.creditManagerTblDao = creditManagerTblDao;
	}
	public void setCreditManagerOrgsTblDao(
			CreditManagerOrgsTblDao creditManagerOrgsTblDao) {
		this.creditManagerOrgsTblDao = creditManagerOrgsTblDao;
	}

	
}
