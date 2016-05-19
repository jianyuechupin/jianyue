package com.hyst.service.safe.impl;

import java.util.ArrayList;
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
	//跳转到保密管理员详情页面的时候选择人员后加载已选的部门数据
	public List<CreditManagerTbl> getOrgsByUserId(int userId) {
		if (userId < 1) {
			return null;
		}
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("userId", userId);
		return creditManagerTblDao.list(map);
	}
	//保存管理员详情
	public String saveCreditDetail(CreditManagerOrgsTbl creditManagerOrgsTbl) {
		if(creditManagerOrgsTbl==null||creditManagerOrgsTbl.getUserInfoId()==0){
			return "请先选择人员进行保存";
		}
		CreditManagerOrgsTbl credit=creditManagerOrgsTblDao.getOne(creditManagerOrgsTbl);
		if (credit==null) {
			//保存保密管理员数据
			creditManagerOrgsTblDao.insert(creditManagerOrgsTbl);
		}
		
		//处理要保密的部门数据
		
		int userId=creditManagerOrgsTbl.getUserInfoId();
		//取得已经存在的细则数据
		List<CreditManagerTbl> list=getOrgsByUserId(userId);
		List<CreditManagerTbl> creditManagerTbls=creditManagerOrgsTbl.getCreditManagerTbls();
		if (list!=null&&creditManagerTbls!=null) {
			//从要保存的数据中移除已经存在的数据
			List<CreditManagerTbl> list2=new ArrayList<>(creditManagerTbls);
			creditManagerTbls.removeAll(list);
			//找出修改后不存在的数据进行删除
			list.removeAll(list2);
		}
		
		if (list!=null&&list.size()!=0) {//如果不为空，进行批量删除已存在记录
			creditManagerTblDao.batchDelete(list);
		}
		
		if (creditManagerTbls!=null&&creditManagerTbls.size()!=0) {//如果要保存的数据不为空，进行数据保存
			creditManagerTblDao.batchInsert(creditManagerTbls);
		}
		return "保存成功";
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
