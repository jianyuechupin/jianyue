package com.hyst.dao.safe;

import org.springframework.stereotype.Repository;
import com.hyst.dao.BaseDao;
import com.hyst.vo.CreditManagerView;

/**
 * @author DongYi
 * @version 创建时间：2016年5月19日 下午5:56:14
 * 类说明  部门管理员视图
 */
@Repository("creditManagerViewDao")
public interface CreditManagerViewDao extends BaseDao<CreditManagerView>{
	
}
