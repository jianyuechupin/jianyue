package com.hyst.dao.user;


import org.springframework.stereotype.Repository;

import com.hyst.dao.BaseDao;
import com.hyst.vo.user.User;
@Repository("userDao")
public interface UserDao extends BaseDao<User>{
	public User select2(User u);
}
