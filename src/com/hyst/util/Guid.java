package com.hyst.util;
/**
 * 类说明: GUID生成工具类
 * @author DongYi
 * @version 创建时间：2016年4月11日 上午10:50:39
 */
public class Guid {
	/**
	 * 获取GUID（UUID）
	 * @return GUID
	 */
	public static String getGuid(){
		return java.util.UUID.randomUUID().toString().replace("-", "");
	}
	/**
	 *  获取GUID（UUID）
	 * @param hasLine 生成GUID是否包含中划线，true包含，false不包含
	 * @return GUID
	 */
	public static String getGuid(boolean hasLine){
		if (hasLine) {
			return java.util.UUID.randomUUID().toString();
		}
		return getGuid();
	}
	/**
	 * 构造方法私有化，不允许创建本类实例
	 */
	private Guid(){
		
	}
	
}
