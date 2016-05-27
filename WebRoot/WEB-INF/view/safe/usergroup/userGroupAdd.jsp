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
    <title>用户权限细则</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
 	 <%@include file="../../include/base.jsp" %> 
 	<script src="js/pickList.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" type="text/css" href="css/pickList.css" />
	<link rel="stylesheet" type="text/css" href="css/powerDetailStyle.css" />
	<link href="css/safe/userpower/userList.css" rel="stylesheet">
	<script src="js/safe/userGroup/userGroupAdd.js" type="text/javascript" charset="utf-8"></script>
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
				
				<input type="hidden" value="${usergroup.id }" id="usergroupid">
			 	<h5 class="col-sm-2">用户组名称：</h5>
				<input id="usergroupname" class="form-control required col-sm-4" style="width: 30%;"  value="${usergroup.userGroupName }" placeholder="用户组名称" type="text"/>
				<h5 class="col-sm-2">用户组描述：</h5>
				<input id="usergroupremark" class="form-control required" style="width: 30%;" value="${usergroup.description }" placeholder="用户组描述" type="text"/>
			</div> 
			<div class="container col-sm-12">
			<!--循环出一级菜单-->
			<ul id="myTab" class="nav nav-tabs">
				<c:forEach items="${tableList }" var="bean"  varStatus="status">
					<li ><a href="#tal${bean.id }" id="manuTree" data-toggle="tab">
					      ${bean.tableNameChs }<input name="operId" type="hidden" value="0"></a>
					</li>
				</c:forEach>
					<li ><a href="#tal-1" id="manuTree" data-toggle="tab">
					      用户列表<input name="operId" type="hidden" value="0"></a>
					</li>				
			</ul>
			<div id="myTabContent" class="tab-content">
				<!-- ********************************************************** -->
				<div class="tab-pane fade"  id="tal-1" >
			        <dl>
			           <dt><label for="orgId">单  位  ：</label></dt>
			           <dd>
				           <select size="1" name="orgId" id="orgIds">
				              <option value="0">    --请选择--    </option>
				           </select>
			           </dd>
			        </dl>		
			        <dl>
			            <dt><label for="userName">用户筛选:</label></dt>
			            <dd><input type="text" id="selectuser" name="userName"  size="27" /></dd>
			            <dd><input type="button" name="userName" id="check" value="查询" /></dd>
			        </dl>			
					
					<div id="usersList">
					
					</div>
				</div>
			</div>
		</div>			
		</div>
	</div>
	
	
  </body>
</html>
