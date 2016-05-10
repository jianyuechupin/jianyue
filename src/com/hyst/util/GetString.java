package com.hyst.util;

import java.sql.SQLException;
import java.util.Map;

/**
 * @author DongYi
 * @version 创建时间：2016年4月11日 下午5:02:35
 * @description 获取xml文件、类文件字符串
 */
public class GetString {
	private static String head="<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n" +
			"<!DOCTYPE mapper \n" +
			"    PUBLIC \"-//mybatis.org//DTDMapper 3.0//EN\" \n" +
			"    \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\"> \n";
	
	/**
	 * @param tableName 表名
	 * @param clazzName 实体名
	 * @param packageName 包名
	 * @return 得到表示实体类的字符串
	 */
	public static String creat(String tableName,String clazzName,String packageName){
		if(packageName==null){
			packageName="com.hyst.vo";
		}
		packageName="package "+packageName+";\n";
		String str="";
		String clazz="";
		try {
			Map<Integer, Map<String, String>> map=GetTableInfo.getTableInfo(tableName);
			//包名
			String s="	public void set";
			String set="";
			String imp="";//引包
			String val="";
			String get="";
			str+="public class "+clazzName.substring(clazzName.indexOf(".")+1,clazzName.length())+"{\n";
			for (int i = 0; i < map.size(); i++) {
				Map<String ,String> m= map.get(i);
				String im= m.get("packageName");
				if ( im.length()>0) {//拼接导入的类
					imp+="import "+im+";/n";
				}
				String name=m.get("collName");
				String type=m.get("type");
				//拼接属性
				val+="	/**属性描述： */\n	private "+type+" "+m.get("collName")+";\n";
				String v=name.substring(0,1).toUpperCase()+name.substring(1,name.length());
				get+="	public "+type+" get"+v+"(){\n		return this."+name+";\n	}\n";
				set+=s+v+"("+type+" "+name+"){\n		this."+name+" = "+name+";\n	}\n";
			}
			clazz=packageName+imp+str+val+get+set+"}";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return clazz;
	}
	/**
	 * @param tableName 表名
	 * @param clazzName 实体名
	 * @return 得到表示实体类的字符串
	 */
	public static String creat(String tableName,String clazzName){
		return creat(tableName,clazzName,null);
	}
	/**
	 * 获取mapper.xml字符串
	 * @param tableName 表名
	 * @param clazzName 实体类名称
	 * @return 字符串表示的mapper.xml文件
	 */
	public static String creatXml(String tableName,String clazzName){
		return creatXml(tableName, clazzName, null);
	}
	/**
	 * 获取mapper.xml字符串
	 * @param tableName 表名
	 * @param clazzName 实体类名称
	 * @return 字符串表示的mapper.xml文件
	 */
	public static String creatXml(String tableName,String clazzName,String packageName){
		try {
			if (packageName==null||packageName.length()==0) {
				packageName="com.hyst.vo";
			}
			String vo=packageName+"."+clazzName;
			Map<Integer, Map<String, String>> map=GetTableInfo.getTableInfo(tableName);
			System.err.println("表信息： \t"+map);
			String mapper="<mapper namespace=\""+vo+"Dao\" >\n"+"\n sqls$$$ \n"+"</mapper>\n";
			String result="	 <resultMap type=\""+clazzName+"\" id=\""+GetTableInfo.headLower(clazzName)+"\" >\n";
			String insert="	<!-- 单个增加 -->\n	<insert id=\"insert\" parameterType=\""+vo+"\">\n"+
					"		insert into "+ tableName ;
			String sql=" (";
			String sql0=" values (";
			String select="	<select id=\"getOne\" resultType=\""+vo+"\">\n"+
					"		select * from "+tableName+" where $$$ \n"+"	</select>\n";
			String update="	<update id=\"update\" parameterType=\""+vo+"\">\n" +
					"		update "+tableName+" set $$$ 		where $$$ \n	</update>\n";

			String delete="	<delete id=\"delete\" parameterType=\""+vo+"\">\n" +
					"		delete from "+tableName+" where $$$ \n	</delete>\n";
			String wher=" where ";		//主键条件语句
			//动态条件语句
			String dynamic="		<where>\n";
			String setDynamic="\n		<set>\n";
					
			for (int i = 0; i < map.size(); i++) {
				Map<String ,String> map0= map.get(i);
				String colum=map0.get("collName");
				String defString=map0.get("defaultValue");
						
				
				if (i==0) {
					wher+= colum+"=#{"+colum+"}";
					//select=select.replace("$$$", colum+"=#{"+colum+"}");
					result+="		<id column=\""+map0.get("collName")+"\" property=\""+map0.get("collName")+"\"/>\n";
					continue;
				}
				dynamic+="			<if test=\" "+colum +" != "+defString+" \">\n			  and "+
						colum+"=#{"+colum+"}\n"+"			</if>\n";
				
				setDynamic+="		<if test=\""+colum +" != "+defString+"\">\n			"+colum+"=#{"+colum+"},\n"+"		</if>\n";
				result+="		<result column=\""+map0.get("collName")+"\" property=\""+map0.get("collName")+"\"/>\n";
				if (i<map.size()-1) {
					sql+=colum+",";
					sql0+="#{"+colum+"},";
				}else {
					sql+=colum+")";
					sql0+="#{"+colum+"})\n";
				}
			}
			result+="	</resultMap>\n\n";
			dynamic+="		</where>";
			setDynamic+= "		</set>\n";
			
			//删除
			delete="	<!-- 根据ID删除 -->\n"+delete.replace("where $$$", wher)/*.replace("		where"," where")*/;
			//增加单个
			insert+=sql+sql0+"	</insert>\n";
			//动态更新
			String up="	<!-- 动态根据ID更新 -->\n"+update.replace("set $$$", setDynamic).replace("where $$$", wher);
			//根据ID查询单个
			String byId="	<!-- 查询单个 -->\n"+select.replace("where $$$", wher);
			//根据动态条件查询
			String byOrder="	<!-- 动态查询 -->\n"+select.replace("where $$$", "\n"+dynamic).replace("getOne", "byOrder");
			//查询所有
			String list="	<!-- 查询所有 -->\n"+select.replace("where $$$", "").replace("getOne", "list");
			
			result+=delete+insert+up+byId +byOrder+list;
//			String sqls=result;
			//返回拼接完成的xml字符串
			return head+mapper.replace("sqls$$$", result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 表示Dao接口文件字符串;
	 * @param intefaceName dao接口名;
	 * @param packageName	包名;
	 * @return 表示Dao接口文件的字符串;
	 */
	public static String daoString(String intefaceName,String packageName){
		packageName="package "+packageName+";\n\n";
		String impot="import org.springframework.stereotype.Repository;\nimport com.hyst.dao.BaseDao;\n\n";
		String message="/**\n * @author \n * @version 创建时间：\n * @类说明\n */\n" +
				"@Repository(\"infoEquipInfo_ViewDao\")";
		intefaceName="public interface "+intefaceName+" extends BaseDao {\n}";
		
		return packageName+impot+message+intefaceName;
	}
	/**
	 * service层字符串--停用
	 * @param clazzName 类名
	 * @param packageName 包名
	 * @return 表示service层的字符串
	 * @deprecated 已停用，返回null
	 */
	@Deprecated
	public static String serviceString(String clazzName,String packageName){
		return null;
	}
}
