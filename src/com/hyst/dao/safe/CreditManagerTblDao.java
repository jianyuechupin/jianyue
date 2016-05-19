package com.hyst.dao.safe;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hyst.dao.BaseDao;
import com.hyst.vo.CreditManagerTbl;

/**
 * @author DongYi
 * @version 创建时间：2016年5月18日 下午2:49:29
 * 类说明
 */
@Repository("creditManagerTblDao")
public interface CreditManagerTblDao extends BaseDao<CreditManagerTbl>{
	/**
	 * 批量插入部门管理员部门数据
	 * @param creditManagerTbls
	 * @return
	 */
	public int batchInsert(List<CreditManagerTbl> creditManagerTbls);
	/**
	 * 批量删除数据
	 * @param creditManagerTbls 要删除的数据
	 * @return 受影响记录数
	 */
	public int batchDelete(List<CreditManagerTbl> creditManagerTbls);
}
