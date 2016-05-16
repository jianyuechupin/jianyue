package com.hyst.dao.safe;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hyst.dao.BaseDao;
import com.hyst.vo.UserGroupPowerDetail;

/**
 * @author DongYi
 * @version 创建时间：2016年5月3日 上午10:11:12
 * 类说明 用户组权限细则Dao <用户组>
 */
@Repository("userGroupPowerDetailDao")
public interface UserGroupPowerDetailDao extends BaseDao<UserGroupPowerDetail>{
	/**
	 * 批量插入用户组细则数据
	 * @param list 用户组细则列表
	 * @return 受影响记录数
	 */
	public int batchInsert(List<UserGroupPowerDetail> list);
	/**
	 * 根据用户组ID查询权限详情列表
	 * @param userGroupId 用户组ID
	 * @return
	 */
	public List<UserGroupPowerDetail> selectByGroupId(String userGroupId);
	/**
	 * 删除所有用户组ID为userGroupId且父ID为pid的二级菜单的所有权限细则 
	 * @param map 包含两个KEY 和value，userGroupId=用户组ID，pid=一级菜单的ID 
	 * @return 受影响记录数
	 */
	public int batchDelete(Map<String , Object> map);
}
