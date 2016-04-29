package com.hyst.dao.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hyst.dao.BaseDao;
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
	 * 登录验证，返回list结果，判断如果list count > 1，用户有重复
	 * 
	 * @param u
	 * @return
	 * @author --- rpj ---
	 * @time 2016年4月28日
	 */
	public List<UserInfo> loginAuthentication(UserInfo u);
}
