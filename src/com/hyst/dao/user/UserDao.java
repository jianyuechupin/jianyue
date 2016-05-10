package com.hyst.dao.user;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hyst.dao.BaseDao;
import com.hyst.vo.user.AdminTbl;
import com.hyst.vo.user.UserInfo;

/**
 * @Content
 * @author rpj
 * @date 2016年4月28日 下午3:20:32
 * @company hyst
 */
@Repository("userDao")
public interface UserDao extends BaseDao<UserInfo> {
	/**
	 * 普通登录验证，返回list结果，判断如果list count > 1，用户有重复
	 * 
	 * @param u
	 * @return
	 * @author --- rpj ---
	 * @time 2016年4月28日
	 */
	public List<UserInfo> userloginAuthentication(Map<String,Object> u);
	
	/**
	 * 管理员登录验证，返回list结果，判断如果list count > 1，用户有重复
	 * @param u
	 * @return
	 * @author --- rpj ---	
	 * @time 2016年5月3日
	 */
	public List<AdminTbl> adminloginAuthentication(Map<String,Object> u);
}
