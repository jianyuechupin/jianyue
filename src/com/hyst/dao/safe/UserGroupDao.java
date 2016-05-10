package com.hyst.dao.safe;

import org.springframework.stereotype.Repository;

import com.hyst.dao.BaseDao;
import com.hyst.vo.UserGroup;

/**
 * 用户组DAO
 * @author DongYi
 * @version 创建时间：2016年4月26日 下午2:02:03
 * 类说明
 */
@Repository("userGroupDao")
public interface UserGroupDao extends BaseDao<UserGroup>{
	
}
