package com.hyst.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.mysql.jdbc.Statement;

/**
 * 映射数据库表生成VO类
 * @author DongYi
 * @version 创建时间：2016年4月11日 下午3:09:55
 */
public class GetTableInfo {
	/**
	 * 根据表名生成VO类
	 * @param tableName 要生成实体类的表名
	 */
	public static void Creat(String tableName){
		
	}
	/**
	 * 取得数据库连接
	 * @return 数据库连接
	 */
	public static Connection getConnection(){
		try {
			Properties prop=new Properties();
			//新增
			prop.put("remarksReporting","true");
			//读取数据库配置文件
		    InputStream in = Object.class.getResourceAsStream("/com/hyst/config/db.properties");  
			prop.load(in);
			
			String url=prop.getProperty("url");
			//加载数据库驱动
		    Class.forName(prop.getProperty("driver"));
		    //获得数据库连接  prop也可换成prop.getProperty("username"),prop.getProperty("password")
			return DriverManager.getConnection(url,prop);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.err.println("数据库连接失败");
		return null;
	}
	

	/**
	 * 获取表信息
	 * @param connection 
	 * @param tableName
	 * @return 返回表中所有列名以及其对应的属性
	 * @throws SQLException 
	 */
	public static Map<Integer, Map<String, String>> getTableInfo(String tableName) throws SQLException{
		 Connection con=getConnection();
		 //取得所有列的集合
		 ResultSet columnSet = con.getMetaData().getColumns(null, null, tableName, null);
		
		 
		 int i=0;
		 Map<Integer, Map<String, String>> map=new HashMap<Integer, Map<String, String>>();
		 while(columnSet.next()){
			 
			 Map<String, String> m2=new HashMap<String, String>();
			 	//取得列名
			 	String collname=headLower(columnSet.getString(4));
			 	//取得列上面的注释
			 	String annotate=columnSet.getString("REMARKS");  //headLower(columnSet.getString(12));
			 	System.err.println("列名："+collname+"\t注释："+annotate);
			 	//取得sql数据库数据类型
			 	String co=columnSet.getString(6);
			 	//System.err.println(columnSet.getString(6));
			 	String ty=getJavaType(co)[0];
			 	//存入类型默认值
			 	m2.put("defaultValue",getJavaType(co)[1]);
//				类型放入map 
				m2.put("type",ty);
				int packag=collname.lastIndexOf(".");
//				列名放入map
				if (packag<0) {
					m2.put("collName", collname);
					m2.put("packageName", "");
				}else{
					m2.put("collName", collname.substring(packag));
					m2.put("packageName", collname.substring(0,packag-1));
				}
				map.put(i, m2);
				i++;
			}
		return map; 
		 
	}
	
	/**
	 * 前两位字母变小写
	 * @param str 原始字符串
	 * @return 首字符小写后的字符串
	 */
	public static String headLower(String str){
		if(str==null||str.length()<1)
			return str;
		return (str.substring(0, 2)).toLowerCase()+str.subSequence(2, str.length());
		
	}
	/**
	 * 首字母变大写
	 * @param str 原始字符串
	 * @return 首字符大写后的字符串
	 */
	public static String headUpper(String str){
		if(str==null||str.length()<0)
			return null;
		str=headLower(str);
		return (str.charAt(0)+"").toUpperCase()+str.subSequence(1, str.length());
		
	}
	/**
	 * SQL类型转换为对应JAVA类型
	 * @param str SQL类型
	 * @return JAVA类型字符串
	 */
	public static String[] getJavaType(String str){
		String defaultValue="null";
		switch (str) {
		case "timestamp":
		case "binary":
		case "varbinary":
		case "image":
			str="byte[]";
			defaultValue="null";
			break;
		case "float":
			str="double";
			defaultValue="0";
			break;
		case "real":
			str="float";
			defaultValue="0";
			break;
		case "int":
		case "tinyint":
		case "smallint":
			defaultValue="0";
			str="int";
			break;
		case "bigint":
			defaultValue="0";
			str="long";
			break;
		case "varchar":
		case "char":
		case "nchar":
		case "nvarchar":
		case "text":
		case "ntext":
		case "uniqueidentifier":
		case "sql_variant":
			defaultValue="null";
			str="String";
			break;
		case "decimal":
		case "money":
		case "smallmoney":
		case "numeric":
			defaultValue="0";
			str="java.math.BigDecima";
			break;
		case "smalldatetime":
		case "datetime":
			defaultValue="null";
			str="java.sql.Timestamp ";
			break;
		}
		String [] strs={str,defaultValue};
		return strs;
		
	}
	
}
