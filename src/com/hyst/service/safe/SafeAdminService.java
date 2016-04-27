package com.hyst.service.safe;

import java.util.List;


import com.hyst.vo.TableInfo;
import com.hyst.vo.UserGroup;

/**
 * @author DongYi
 * @version 创建时间：2016年4月26日 下午2:04:36
 * 类说明
 */
public interface SafeAdminService {

	/**
	 * 获取用户组列表
	 * @param pageNo 当前页数
	 * @param pageSize 每页大小
	 * @return 用户组集合
	 */
	public List<UserGroup> getUserGroups(int pageNo, int pageSize);

	/**
	 * 根据父ID查询列表
	 * @param pid 父ID
	 * @return
	 */
	public List<TableInfo> creatTbaleInfo(int pid);

}
