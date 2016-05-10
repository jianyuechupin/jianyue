package com.hyst.dao.safe;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hyst.dao.BaseDao;
import com.hyst.vo.UserGroupListTbl;

/**
 * 用户组与用户关联Dao
 * @author DongYi
 * @version 创建时间：2016年5月3日 上午9:55:31
 * 类说明
 */
@Repository("userGroupListTblDao")
public interface UserGroupListTblDao extends BaseDao<UserGroupListTbl>{

	/**
	 * 批量增加用户到用户组 
	 * @param list 用户组用户集合
	 * @return
	 */
	public int batchInsert(List<UserGroupListTbl> list);
	/**
	 * 根据用户组ID删除所有信息
	 * @param userGroupListTbl 用户组实体
	 * @return 受影响记录数
	 */
	public int deleteByUserGroupId(int userGroupId);
	
}
