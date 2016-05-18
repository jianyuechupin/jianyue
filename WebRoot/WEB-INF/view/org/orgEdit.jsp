<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'organization.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->


  </head>
  
  <body>
  	<form action="orgInfo/orgadd.do" method="post">
   		<table>
   			<tr>
   				<td>上级单位：<select name="parentId" >
   						<option value="0">请选择</option>
   				  		<c:forEach items="${list}" var="bean">
   				  		<option value="${bean.id}">${bean.orgName}</option>
				      </c:forEach>
   				</select></td>
   			</tr>
   			<tr>
   				<td>单位名称：<input  type="text" name="orgName"></td>
   				
   			</tr>
   			<tr>
   				<td>单位备注：<input  type="text" name="remarks"></td>
   				
   			</tr>
   			</table>
   		<input type="submit" value="提交" >
   	</form>
  </body>
</html>
