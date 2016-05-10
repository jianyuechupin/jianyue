package com.hyst.dao.safe;

import org.springframework.stereotype.Repository;

import com.hyst.dao.BaseDao;
import com.hyst.vo.PowerGroupTbl;

/**
 * @author DongYi
 * @version 创建时间：2016年5月6日 上午10:12:56
 * 类说明
 */
@Repository("powerGroupTblDao")
public interface PowerGroupTblDao extends BaseDao<PowerGroupTbl>{
	/**
	 * 根据权限组名称查询，主要用于权限组重名判断
	 * @param powerGroupTbl 权限组对象
	 * @return 返回单个对象
	 */
	public PowerGroupTbl getByName(PowerGroupTbl powerGroupTbl);
}
