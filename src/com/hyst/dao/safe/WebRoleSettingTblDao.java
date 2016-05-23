package com.hyst.dao.safe;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hyst.dao.BaseDao;
import com.hyst.vo.WebRoleSettingTbl;

/**
 * @author DongYi
 * @version 创建时间：2016年5月23日 下午2:44:11
 * 类说明
 */
@Repository("webRoleSettingTblDao")
public interface WebRoleSettingTblDao extends BaseDao<WebRoleSettingTbl>{
	public List<WebRoleSettingTbl> list();
}
