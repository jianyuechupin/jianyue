package com.hyst.dao.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hyst.dao.BaseDao;
import com.hyst.vo.user.UserView;

/**
 * @author DongYi
 * @version 创建时间：2016年5月24日 下午4:04:02
 * 类说明
 */
@Repository("userViewDao")
public interface UserViewDao extends BaseDao<UserView>{

	/**
	 * 根据用户ID[] 查询用户视图
	 * @param uids
	 * @return
	 */
	public List<UserView> list(List<Integer> uids);

}
