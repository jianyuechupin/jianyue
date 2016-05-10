package com.hyst.dao.safe;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hyst.dao.BaseDao;
import com.hyst.vo.TableInfo;

/**
 * @author DongYi
 * @version 创建时间：2016年4月21日 下午6:30:33
 * 类说明
 */
@Repository("tableInfoDao")
public interface TableInfoDao extends BaseDao<TableInfo>{
	/**
	 * g根据父ID查询所有功能菜单
	 * @param map 
	 * @return list集合
	 */
	public List<TableInfo> listByParentId(Map<String, Object> map);
}
