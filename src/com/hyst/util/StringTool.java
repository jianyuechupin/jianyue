package com.hyst.util;

/**
 * String常用类
 * 
 * @Content
 * @author rpj
 * @date 2016年4月29日 下午5:57:20
 * @company hyst
 */
public class StringTool {

	/**
	 * 去除字符串首尾的字符
	 * 
	 * @param str
	 * @return
	 * @author --- rpj ---
	 * @time 2016年4月29日
	 */
	public static String trimSymbol(String str, char symbol) {
		if (str.startsWith(String.valueOf(symbol)))
			return trimSymbol(
					new String(new StringBuffer(str).deleteCharAt(0)), symbol);
		else if (str.endsWith(String.valueOf(symbol)))
			return trimSymbol(new String(new StringBuffer(str).deleteCharAt(str
					.length() - 1)),symbol);
		else
			return str;
	}

	private StringTool() {
	}
}
