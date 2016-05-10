<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	/hyst/WebRoot/JS/jquery.js-->
	<script type="text/javascript" src="JS/jquery.js"></script>
	<script type="text/javascript">
		$(function(){
		
			if("${msg}".length>0)
			 $("#err").text("${msg}");
		});
	</script>
  </head>
  
  <body>
   	<form action="user/login.do" method="post">
   		<table>
   			<tr>
   				<td>用户名：<input  type="text" name="name"></td>
   				<td>密    码：<input  type="password" name="pwd"></td>
   			</tr>
   			
   			<tr>	
   					<td id="err" >欢迎登录ERP管理系统</td>
   			</tr>
   		</table>
   		<input type="submit">
   	</form>
   	<a href="user/list.do">跳转到用户列表页</a>
  </body>
</html>
