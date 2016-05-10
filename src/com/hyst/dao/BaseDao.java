package com.hyst.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao<E> {
	/**
	 * 根据动态条件查询
	 * @param obj 条件对象
	 * @return 符合条件的对象集合
	 */
	public List<E> byOrder(E e);
	/**
	 * 根据条件查询列表
	 * @param m 查询条件的map<条件名称,条件值>集合
	 * @return
	 */
	public List<E> list(Map<String, ?> m);
	/**
	 * 查询单条信息 修改参数为E，因为有些情况主键非INT类型
	 * @param e 
	 * @return 信息记录
	 */
	public E getOne(Object e);
	/**
	 * 数据库单条插入
	 * @param e 插入的的对象
	 * @return 受影响记录数
	 */
	public int insert(E e);
	/**
	 * 单条信息更新
	 * @param obj 更新对象
	 * @return 受影响记录数
	 */
	public int update(E e);
	/**
	 * 根据id删除记录
	 * @param id
	 * @return 受影响记录数
	 */
	public int delete(Integer  id);
}
