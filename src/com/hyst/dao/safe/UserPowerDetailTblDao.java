package com.hyst.dao.safe;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hyst.dao.BaseDao;
import com.hyst.vo.UserPowerDetailTbl;

/**
 * 用户权限细则
 * @author DongYi
 * @version 创建时间：2016年5月12日 下午2:54:33
 * 类说明
 */
@Repository("userPowerDetailTblDao")
public interface UserPowerDetailTblDao extends BaseDao<UserPowerDetailTbl>{
	/**
	 * 批量插入用户权限细则数据《到用户权限细则表-userPowerDetailTbl》
	 * @param userPowerDetailTbls 
	 * @return 受影响记录数
	 */
	public int batchInsert(List<UserPowerDetailTbl> userPowerDetailTbls);
	/**
	 * 根据用户ID查询出所有权限细则
	 * @param userid 用户ID
	 * @return
	 */
	public List<UserPowerDetailTbl> getListByUserId(int userid);
	/**
	 * 删除用户ID为 userID且一级菜单为parentId的权限细则集合
	 * @param map 包含两个键值对 <parentId:一级菜单ID> <userId：用户ID>
	 * @return
	 */
	public int batchDelete(Map<String ,Integer> map);
}
