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
    <title>权限组细则</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
 	 <%@include file="../../include/base.jsp" %> 
 	<script src="js/pickList.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css" href="css/pickList.css" />
	<link rel="stylesheet" type="text/css" href="css/powerDetailStyle.css" />
	<script type="text/javascript" src="js/safe/powergroup/powerGroupAdd.js"></script>
  </head>
  
  <body>
  	<!-- 引入导航头 -->
  	<%@include file="../../include/top.jsp" %>
  	<!-- 引入左侧导航 -->
  	<%@include file="../../include/left.jsp" %>
	<!-- 右侧区域 -->
	<div class="container">
		<div class="jumbotron" style="margin-left: 100px;">
			<div>
				<input type="hidden" value="${powerGrou.id }" id="powergroupid">
			 	<h5 class="col-sm-2">权限组名称：</h5>
				<input id="powergroupname" class="form-control required col-sm-4" style="width: 30%;"  value="${powerGrou.powerGroup }" placeholder="权限组名称" type="text"/>
				<h5 class="col-sm-2">权限组描述：</h5>
				<input id="powergroupremark" class="form-control required" style="width: 30%;" value="${powerGrou.remark }" placeholder="权限组描述" type="text"/>
			</div> 
			<div class="container col-sm-12">
			<!--循环出一级菜单-->
			<ul id="myTab" class="nav nav-tabs">
				<c:forEach items="${list }" var="bean"  varStatus="status">
					<li ><a href="#tal${bean.id }" id="manuTree" data-toggle="tab">
					      ${bean.tableNameChs }<input name="operId" type="hidden" value="0"></a>
					</li>
				</c:forEach>
			</ul>
			<div id="myTabContent" class="tab-content">
				
			</div>
		</div>			
		</div>
	</div>
	
	
  </body>
</html>
