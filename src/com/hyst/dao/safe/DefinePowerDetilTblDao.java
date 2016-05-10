package com.hyst.dao.safe;

import java.util.List;
import java.util.Map;

import com.hyst.dao.BaseDao;
import com.hyst.vo.DefinePowerDetilTbl;
import com.hyst.vo.UserGroupPowerDetail;

/**
 * 权限组细则Dao
 * @author DongYi
 * @version 创建时间：2016年5月6日 下午2:48:57
 */
public interface DefinePowerDetilTblDao extends BaseDao<DefinePowerDetilTbl>{
	/**
	 * 批量插入用户细则
	 * @param definePowerDetilTbls 要插入的权限组细则集合《list类型》
	 * @return 受影响记录数
	 */
	public int batchInsert(List<DefinePowerDetilTbl> definePowerDetilTbls);
	/**
	 * 删除所有用户组ID为powerGroupId且父ID为pid的二级菜单的所有权限细则 
	 * @param map 包含两个KEY 和value，powerGroupId=权限组ID，pid=一级菜单的ID 
	 * @return 受影响记录数
	 */
	public int batchDelete(Map<String , Object> map);
	/**
	 * 根据权限组ID查询所有权限细则集合
	 * @param powerGroupId 权限组ID
	 * @return 权限细则集合
	 */
	public List<DefinePowerDetilTbl> getByPowerGroupId(String powerGroupId);
}
