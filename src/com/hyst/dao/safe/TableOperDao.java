package com.hyst.dao.safe;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.hyst.dao.BaseDao;
import com.hyst.vo.TableOper;

/**
 * @author DongYi
 * @version 创建时间：2016年4月25日 下午4:58:33
 * 类说明
 */
@Repository("tableOperDao")
public interface TableOperDao extends BaseDao<TableOper>{
	public List<TableOper> listByTableId(int tableId);
}
