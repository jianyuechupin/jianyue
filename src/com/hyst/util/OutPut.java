package com.hyst.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 *  文件生成工具类 （字符流）
 * @author DongYi
 * @version 创建时间：2016年4月12日 下午3:23:26
 */
public class OutPut {
	
	/**
	 *  @description 根据表名生成 自定义实体类及对应mapper.xml、service、Dao文件
	 * @param tableName 表名
	 * @param packageName 实体类的包名
	 * @param clazzName 类名
	 */
	public static void creatFile(String tableName,String packageName,String clazzName){
		if (packageName==null||packageName.length()==0) {
			packageName="com.hyst.vo";
		}
		String xml=GetString.creatXml(tableName, clazzName, packageName);
		String clazz=GetString.creat(tableName, clazzName, packageName);
		String pack="/src/"+packageName.replace(".", "/")+"/";
		String path=System.getProperty("user.dir").replace("\\", "/");
		String javapath=path+pack+clazzName+".java";
		String xmlpath=path+pack.replace("vo", "/config/mapper/")+clazzName.toLowerCase()+".mapper.xml";
		System.out.println("类文件路径为\t"+javapath);
		System.out.println("映射文件路径为\t"+xmlpath);
		File xmlfile=new File(xmlpath);
		File jaFile=new File(javapath);
		System.out.println(xmlpath+"\n"+javapath);
		if (!xmlfile.exists()) {
			try {
				xmlfile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!jaFile.exists()) {
			try {
				jaFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		OutPut.out(xml, xmlfile);
		OutPut.out(clazz, jaFile);
	}
	
	
	
	/**
	* 写文件
	* @param context 内容
	* @param file	  路径
	*/
	public static void out(String context,File file){
			BufferedWriter bw=null;
			try {
				bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF-8"));
				bw.write(context,0,context.length());
				bw.flush();
				System.out.println("-------------------生成文件完毕--------------------");
			} catch (IOException e) {
				System.err.println("-------------------生成文件失败--------------------");
			}finally{
				if (bw!=null) {
					try {
						bw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}
		
	}
}
