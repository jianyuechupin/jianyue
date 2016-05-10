package com.hyst.dao.safe;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hyst.dao.BaseDao;
import com.hyst.vo.TableOperView;

/**
 * @author DongYi
 * @version 创建时间：2016年4月27日 下午5:57:04
 * 类说明
 */
@Repository("tableOperViewDao")
public interface TableOperViewDao extends BaseDao<TableOperView>{
	/**
	 * 根据tableId查询功能列表
	 * @param tableId 
	 * @return
	 */
	public List<TableOperView> listByTableId(int tableId);
}
