package com.hyst.task;
/**
 * 任务调度类
 * @author DongYi
 * @version 创建时间：2016年4月7日 下午4:57:56
 */
public class TaskDemo {
	/**
	 * @author DongYi
	 * 自动任务执行方法（一）
	 */
	int i =0;
	public void execute(){
		System.out.println("次数"+(i++)+"方法EXECUTE");
	}
	/**
	 * @author DongYi
	 * 自动任务执行方法（二）
	 */
	public void taskMethod(){
		System.out.println("任务执行、次数\t"+(i++));
	}
}
