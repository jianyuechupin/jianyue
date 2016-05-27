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
    <title>保密管理员列表页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
 	<%@include file="../../include/base.jsp" %> 
 	<script src="js/bootstrap-table-contextmenu.min.js"></script>
	<script src="js/safe/webrolesetting/webrolesetting.js"></script>


  </head>
  
  <body>
  	<!-- 引入导航头 -->
  	<%@include file="../../include/top.jsp" %>
  	<!-- 引入左侧导航 -->
  	<%@include file="../../include/left.jsp" %>
	<!-- 右侧区域 -->
	<div class="container">
		<div class="jumbotron" style="margin-left: 100px;">
		<!-- 权限组列表 -->
			<div id="tableDiv" class="container">
				<table id="table" data-show-columns="false" data-search="true"
					data-show-refresh="true" data-show-toggle="true"
				 	data-click-to-select="true" data-pagination="false" 
				 	data-single-select="true" data-striped="true"> 
					<thead>
						<tr id="head">
							<!-- <th data-field="state" data-checkbox="true"></th> -->
							<th data-field="id" data-visible="false" data-formatter="idFormatter"></th>
							<th data-field="role"  data-width="25%">角色名称</th>
							<th data-field="roleType" data-visible="false" /><!-- 保密员类型 -->
							<th data-field="users" >管理角色</th>
							<th data-field="action" data-formatter="actionFormatter" data-events="actionEvents">Action</th>
						</tr>
					</thead>
				</table>
			</div>
		<!-- 列表完成 -->	
		</div>
	</div>
	
	
  </body>
</html>
