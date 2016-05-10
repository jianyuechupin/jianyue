package com.hyst.dao.safe;

import org.springframework.stereotype.Repository;

import com.hyst.dao.BaseDao;
import com.hyst.vo.UserDeptView;

/**
 * @author DongYi
 * @version 创建时间：2016年4月21日 上午9:54:23
 * 类说明 用户部门关联视图Dao
 */
@Repository("userDeptViewDao")
public interface UserDeptViewDao extends BaseDao<UserDeptView>{

}
