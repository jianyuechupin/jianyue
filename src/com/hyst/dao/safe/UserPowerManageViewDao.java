package com.hyst.dao.safe;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hyst.dao.BaseDao;
import com.hyst.vo.UserPowerManageView;

/**
 * 用户权限管理视图Dao，用户权限管理首页展示数据
 * @author DongYi
 * @version 创建时间：2016年5月11日 下午5:23:55
 * 类说明
 */
@Repository("userPowerManageViewDao")
public interface UserPowerManageViewDao extends BaseDao<UserPowerManageView>{
	//public List<UserPowerManageView> list();
}
