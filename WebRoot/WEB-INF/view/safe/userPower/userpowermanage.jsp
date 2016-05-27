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
    <title>用户权限</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
 	 <%@include file="../../include/base.jsp" %> 
 	<link href="css/safe/userpower/userList.css" rel="stylesheet">
 	<script src="js/bootstrap-table-contextmenu.min.js"></script>
 	<script type="text/javascript" src="js/safe/userpower/userList.js" ></script>
	<script type="text/javascript" src="js/safe/userpower/userListPatch.js" ></script>
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
				 	<div class="fixed-table-toolbar">
						<div class="columns columns-right btn-group pull-right">
						<button id="add" class="btn btn-default filter-show-clear" title="添加" type="button">
						<a><i class="glyphicon glyphicon-plus">&nbsp修改</i></a>
						</button>
						</div>
					</div>
				 	<div class="fixed-table-toolbar">
						<div class="columns columns-right btn-group pull-right">
						<button id="setlogintype" class="btn btn-default filter-show-clear" title="添加" type="button">
						<a><i class="glyphicon glyphicon-plus">&nbsp设置登录方式</i></a>
						</button>
						</div>
					</div>
					<thead>
						<tr id="head"><!-- data-visible="false" 设置列为不可见 -->
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="uid"  id="hellotest" data-visible="false">隐藏的ID列</th>
							<th data-field="orgName">单位</th>
							<th data-field="userName">姓名</th>
							<th data-field="account">系统账号</th>
							<th data-field="loginTypeString">登录方式</th>
							<th data-field="action" data-formatter="actionFormatter" data-events="actionEvents">Action</th>
						</tr>
					</thead>
					
				</table>
			</div>
		<!-- 列表完成 -->	
		</div>
		<!-- 模态层 -->
		<button id="show"> 显示</button>
		<div id="sh" class="modal fade">
		  <div class="modal-dialog">
		    <div class="modal-content">
		    <!-- 头 -->
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <label class="modal-title">设置用户登录方式</label>
		      </div>
		     <!-- body--> 
		      <div class="modal-body" style="hight:325px" align="center">
		      	
		      		<dl>
						<dt><label for="loginType">登录方式：</label></dt>
						<dd>
							<input type="hidden" id="updateUserId"  value="123">
							<input type="radio" id="zone" name="loginType" value="1" /><!--  -->
							<label for="zone" >密码登陆</label>
							<input type="radio" id="password" name="loginType"  value="0" />
							<label for="password" class="check_label">域登录</label>
						</dd>
					</dl>		
			        <dl id="passdl">
			            <dt ><label for="userName">    密  码   :</label></dt>
			            <dd ><input value="&*!~#" class="form-control myinput" id="myPassWord" type="password" name="pass"  /></dd>
			        </dl>			
		      		
		      </div>
		     <!-- -->
		      <div class="modal-footer">
		        <button type="button" id="changeType" class="btn btn-primary">保存</button>
		        <button id="closeModal" type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		      </div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->  
		  
	</div>
  </body>
</html>
