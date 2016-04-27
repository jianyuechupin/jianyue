package com.hyst.interceptor;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@Intercepts({
		@Signature(type = Executor.class, method = "update", args = {
				MappedStatement.class, Object.class }),
		@Signature(type = Executor.class, method = "query", args = {
				MappedStatement.class, Object.class, RowBounds.class,
				ResultHandler.class }) })
public class MybatisInterceptor implements Interceptor {
	/**
	 * 待改--目的：性能优化
	 * 思路：做AOP环绕方法，把开始时间，结束时间初始化到类变量
	 * 环绕intercept方法，获取SQL，paramete，执行SQL，执行结果记录日志
	 */
	private Properties properties;
	private static Logger log=Logger.getLogger("vistor.sqllog");
	@Autowired
	HttpSession session ;
	public void setHttpSession(HttpSession httpSession) {
		this.session = httpSession;
	}
	
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		//mybatis获取MappedStatement
		MappedStatement mappedStatement = (MappedStatement) invocation
				.getArgs()[0];
		//初始化参数
		Object parameter = null;
		if (invocation.getArgs().length > 1) {
			parameter = invocation.getArgs()[1];
		}
		//获取映射文件的SQLID
		String sqlId = mappedStatement.getId();
		//获取封装的SQL
		BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		//获取配置文件管理器
		Configuration configuration = mappedStatement.getConfiguration();
		Object returnValue = null;
		//记录初始时间
		long start = System.currentTimeMillis();
		//运行invocation的proceed方法
		returnValue = invocation.proceed();
		System.out.println("运行结果为："+returnValue);
		//记录运行结束时间
		long end = System.currentTimeMillis();
		long time = (end - start);
		if (time > 1) {
			// 取得可执行SQLL
			String sql = getSql(configuration, boundSql, sqlId, time);
		}
		return returnValue;
	}
	/**
	 * 加上運行時間的SQL
	 * @param configuration
	 * @param boundSql
	 * @param sqlId
	 * @param time
	 * @return
	 */
	public static String getSql(Configuration configuration, BoundSql boundSql,
			String sqlId, long time) {
		String []sqls = showSql(configuration, boundSql);
		String sql=sqls[0];
		StringBuilder str = new StringBuilder(100);
		str.append(sqlId);
		str.append(":");
		str.append(sql);
		str.append("\trunTime: ");
		str.append(time);
		str.append("ms");
		System.out.println(sqls[1]+"\t"+sqls[2]);
		System.out.println("執行SQL為："+str);
		return str.toString();
	}

	/**
	 * 取得mapper参数
	 * 
	 * @param obj
	 * @return
	 */
	private static String getParameterValue(Object obj) {
		String value = null;
		if (obj instanceof String) {
			value = "'" + obj.toString() + "'";
		} else if (obj instanceof Date) {
			DateFormat formatter = DateFormat.getDateTimeInstance(
					DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
			value = "'" + formatter.format(new Date()) + "'";
		} else {
			if (obj != null) {
				value = obj.toString();
			} else {
				value = "";
			}

		}
		return value;
	}
	/**
	 * 展示SQL
	 * @param configuration 就像是Mybatis的总管，
	 * Mybatis的所有配置信息都存放在这里，此外，它还提供了设置这些配置信息的方法。
	 * Configuration可以从配置文件里获取属性值，也可以通过程序直接设置。
	 * @param boundSql
	 * @return
	 */
	public static String[] showSql(Configuration configuration, BoundSql boundSql) {
		String [] sqls=new String[3];
		Object parameterObject = boundSql.getParameterObject();
//		獲得ParameterMapping 映射參數
		List<ParameterMapping> parameterMappings = boundSql
				.getParameterMappings();
		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
//		替換后SQL：	select * from user WHERE name=?
		sqls[1]="SQL:"+sql;
		sqls[2]="PARAMES:"+boundSql.getParameterObject().toString();
		if (parameterMappings.size() > 0 && parameterObject != null) {
			TypeHandlerRegistry typeHandlerRegistry = configuration
					.getTypeHandlerRegistry();//TypeHandlerRegistry類型註冊器
			if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
				sql = sql.replaceFirst("\\?",
						getParameterValue(parameterObject));
				System.out.println("參數：|\t"+getParameterValue(parameterObject));
			} else {
				MetaObject metaObject = configuration
						.newMetaObject(parameterObject);
				for (ParameterMapping parameterMapping : parameterMappings) {
					String propertyName = parameterMapping.getProperty();
					if (metaObject.hasGetter(propertyName)) {
						Object obj = metaObject.getValue(propertyName);
						sql = sql.replaceFirst("\\?", getParameterValue(obj));
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						Object obj = boundSql
								.getAdditionalParameter(propertyName);
						sql = sql.replaceFirst("\\?", getParameterValue(obj));
					}
				}
			}
		}
		sqls[0]="QUERY_SQL:"+sql;
		return sqls;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties0) {
		this.properties = properties0;
	}

}