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
}
