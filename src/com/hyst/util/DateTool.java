package com.hyst.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * @author DongYi
 * @version 创建时间：2016年4月14日 上午10:45:32
 */
public class DateTool {
	public static final String DATE_TIME="yyyy-MM-dd hh:mm:ss";
	public static final String DATE="yyyy-MM-dd";
	public static final String TIME="hh:mm:ss";
	/**
	 * 两日期比较，date是否早于lateDate
	 * @param date 需要比较的日期
	 * @param lateDate 被比较的日期，如果为空则取当前时间
	 * @return true date早于lateDate，false date不早于lateDate
	 */
	public static boolean isAfter(Date date,Date lateDate){
		boolean flg=false;
		if (date==null){
			new Exception("需要比较的日期不能为空");
			return flg;}
		if (lateDate==null) 
			lateDate=new Date();
		flg=date.after(lateDate);
		return flg;
	}
	
	/**
	 * 于当前日期比较
	 * @param date 要比较的日期
	 * @return  true date早于当前日期，false date不早于当前日期
	 */
	public static boolean afterNow(Date date){
		return isAfter(date, null);
	}
	
	/**
	 * 得到两日期中较晚的日期
	 * @param date 需要的比较的日期
	 * @param lateDate 被比较的日期，如果为空，则默认为当前日期时间
	 * @return 较晚的日期
	 */
	public static Date lastDate(Date date,Date lateDate){
		if (lateDate==null) 
			lateDate=new Date();
		
		if (isAfter(date, lateDate)) {
			return date;
		}
		return lateDate;
	}
	
	/**
	 * 得到两日期中较早的日期
	 * @param date 需要的比较的日期
	 * @param earlyDate 被比较的日期，如果为空，则默认为当前日期时间
	 * @return 较早的日期
	 */
	public static Date earlyDate(Date date,Date earlyDate){
		if (earlyDate==null) 
			earlyDate=new Date();
		
		if (!isAfter(date, earlyDate)) {
			return date;
		}
		return earlyDate;
	}
	
	/**
	 * 与当前日期比较，取较晚日期
	 * @param date 比较日期
	 * @return 与当前日期之间较晚的日期
	 */
	public static Date lastDate(Date date){
		return lastDate(date,null);
		
	}
	/**
	 * 与当前日期比较，取较早日期
	 * @param date 比较日期
	 * @return 与当前日期之间较早的日期
	 */
	public static Date earlyDate(Date date){
		return earlyDate(date,null);
		
	}
	
	public static void main(String[] args) {
		Date date=new Date();
		Date dat=null;
		try {
			dat=new SimpleDateFormat("yyyy-MM-dd").parse("2014-02-11");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(dat.after(date));
		System.out.println(lastDate(dat, null));
	}
	/**
	 * 将一个日期串格式的字符串转换成日期，
	 * @param dateString 表示一个日期的字符串 例如"2016-04-14 12:00:00" 为空则取当前日期
	 * @param dateFormat 字符串的格式，参见本类几种属性格式， 默认为"yyyy-MM-dd hh:mm:ss"格式
	 * @return 表示此字符串的时间对象
	 */
	public static Date toDate(String dateString,String dateFormat){
		Date date=new Date();
		if (dateString==null||dateString.length()==0) {
			return date;
		}
		if (dateFormat==null||dateFormat.length()==0) {
			dateFormat=DATE_TIME;
		}
		try {
			date=new SimpleDateFormat(dateFormat).parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		};
		return date;
	}
	
	/**
	 * 将一个日期串格式的字符串转换成日期，
	 * @param dateString 将"yyyy-MM-dd hh:mm:ss"格式字符串转换成日期格式 ，为空则取当前日期
	 * @return 表示此字符串的时间对象
	 */
	public static Date toDate(String dateString){
		Date date=new Date();
		if (dateString==null||dateString.length()==0) {
			return date;
		}
		try {
			date=new SimpleDateFormat(DATE_TIME).parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		};
		return date;
	}
	
	/**
	 * 格式化日期成字符串
	 * @param date 被格式化的日期
	 * @param format 格式化格式
	 * @return
	 */
	public static String dateFormat(Date date,String format){
		if (date==null ) {
			return null;
		}
		if (format==null||format.length()==0) {
			format=DATE_TIME;
		}
		return new SimpleDateFormat(format).format(date);
	}
	/**
	 * 格式化日期成字符串 "yyyy-MM-dd hh:mm:ss"格式
	 * @param date 被格式化的日期
	 * @return
	 */
	public static String dateFormat(Date date){
		if (date==null ) {
			return null;
		}
		return new SimpleDateFormat(DATE_TIME).format(date);
	}
	private DateTool(){}
}
