package com.hyst.service.safe.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyst.dao.safe.CreditManagerOrgsTblDao;
import com.hyst.dao.safe.CreditManagerTblDao;
import com.hyst.dao.safe.CreditManagerViewDao;
import com.hyst.dao.safe.WebRoleSettingTblDao;
import com.hyst.service.safe.CreditManagerService;
import com.hyst.vo.CreditManagerOrgsTbl;
import com.hyst.vo.CreditManagerTbl;
import com.hyst.vo.CreditManagerView;
import com.hyst.vo.WebRoleSettingTbl;

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
		}else if(credit.getRoleType()==1){
			//如果该人员是兼职保密员且已经存在更新管理的部门名称信息
			creditManagerOrgsTblDao.update(creditManagerOrgsTbl);
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
	//根据部门保密管理员ID查询视图
	public CreditManagerView getView(int id) {
		
		return creditManagerViewDao.getOne(id);
	}
	//删除保密管理员
	public String deleteCreditManage(CreditManagerOrgsTbl creditManager) {
		if (creditManager==null||creditManager.getRoleType()==0||creditManager.getUserInfoId()==0) {
			return "删除失败，请检查要删除的人员信息是否已正确选择";
		}
		//删除保密管理员
		creditManagerOrgsTblDao.delete(creditManager);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userInfoId", creditManager.getUserInfoId());
		map.put("roleType", creditManager.getRoleType());
		creditManagerTblDao.delete(map);
		return "删除成功";
	}
	//////////////////////////---保密门户角色设置---//////////////////////////////
	//获取保密角色列表
	public List<WebRoleSettingTbl> getWebRoles() {
		return webRoleSettingTblDao.list();
	}

	/////////////////////////---Dao等属性设置---/////////////////////////////////
	@Autowired
	private WebRoleSettingTblDao webRoleSettingTblDao;
	@Autowired
	private CreditManagerOrgsTblDao creditManagerOrgsTblDao;
	@Autowired
	private CreditManagerTblDao creditManagerTblDao;
	@Autowired
	private CreditManagerViewDao creditManagerViewDao;
	/////////////////////////---非业务方法处理---/////////////////////////////////
	
	public void setWebRoleSettingTblDao(
			WebRoleSettingTblDao webRoleSettingTblDao) {
		this.webRoleSettingTblDao = webRoleSettingTblDao;
	}
	public void setCreditManagerViewDao(
			CreditManagerViewDao creditManagerViewDao) {
		this.creditManagerViewDao = creditManagerViewDao;
	}
	public void setCreditManagerTblDao(CreditManagerTblDao creditManagerTblDao) {
		this.creditManagerTblDao = creditManagerTblDao;
	}
	public void setCreditManagerOrgsTblDao(
			CreditManagerOrgsTblDao creditManagerOrgsTblDao) {
		this.creditManagerOrgsTblDao = creditManagerOrgsTblDao;
	}




	
}
