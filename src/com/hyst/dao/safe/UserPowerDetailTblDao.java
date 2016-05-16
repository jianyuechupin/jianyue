package com.hyst.dao.safe;

import java.util.List;

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
}
