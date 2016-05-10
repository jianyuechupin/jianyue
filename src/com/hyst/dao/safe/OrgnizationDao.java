package com.hyst.dao.safe;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hyst.dao.BaseDao;
import com.hyst.vo.Orgnization;

/**
 * @author DongYi
 * @version 创建时间：2016年4月28日 下午5:56:32
 * 类说明
 */
@Repository("orgnizationDao")
public interface OrgnizationDao extends BaseDao<Orgnization>{
	public List<Orgnization> listByParentId(int parentId);

}
