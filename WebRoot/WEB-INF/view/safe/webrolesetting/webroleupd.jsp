<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>保密门户角色设置</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
 	 <%@include file="../../include/base.jsp" %> 
 	<script src="js/pickList.js" type="text/javascript" charset="utf-8"></script>
 	<link rel="stylesheet" type="text/css" media="all" href="css/pickList.css" />
	<script language="javascript" type="text/javascript" src="js/niceforms.js"></script>
	<link rel="stylesheet" type="text/css" media="all" href="css/niceforms-default.css" />
	<script type="text/javascript" src="js/safe/webrolesetting/webroleUpdate.js"></script>
	
	

  </head>
  
  <body>
  	<!-- 引入导航头 -->
  	<%@include file="../../include/top.jsp" %>
  	<!-- 引入左侧导航 -->
  	<%@include file="../../include/left.jsp" %>
	<!-- 右侧区域 -->
	 <div class="container"> 
	 <!-- 视图名称 bean -->
		<div class="jumbotron" style="margin-left: 100px;">
			<input type="hidden" id="userids" value="${model.userId }">
			<input type="hidden" value="${model.id }" id="id">
			<label for="roleType">用户角色</label>
			<select id="roleType" disabled="disabled">
				<option value="${model.roleType }">${model.role } </option>
			</select>
			
			<label for="dept">选择部门</label>
			<select id="dept" >
				<option value="0">--请选择--</option>
			</select>
						
			<!-- 部门左右移动块 -->
			<label for="selectuser" >人员筛选：</label><input id="selectuser" type="text" > <button id="check">查询</button>
			<%-- <input type="hidden" value="${model.typeName }" id="users"> --%>
			<div id="pickList"></div>
			
			<button  id="submit">保存</button>	
	</div></div>				
  </body>
</html>
