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
    <title>权限组列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
 	 <%@include file="../../include/base.jsp" %> 
 	 <script src="js/bootstrap-table-contextmenu.min.js"></script>
	<script type="text/javascript">
		 $(function(){
		 	/**绑定双击事件*/
		 	$('#table').bootstrapTable({
				onDblClickRow:function(row, $element){
					window.location='safe/creatUserGroup.do?id='+row.id;
				}
	        });
		 	/**取得权限组列表*/
 		 	$.post("safe/userGroupAjax.do",function(data){
				$("#table").bootstrapTable('load', data);
			});  
			/**修改按钮被点击*/
 			$("#update").click(function(){
				var selects=$("#table").bootstrapTable('getSelections');
				if(selects.length==0){
					alert("请选择要修改的权限组");
					return;
				}else if(selects.length > 1){
					alert("请正确选择要修改的权限组，每次只能选择一个");
					return;
				}
				var id=$("#table").bootstrapTable('getSelections')[0].id;
				window.location='safe/creatUserGroup.do?id='+id;
			}); 
			/**删除按钮被点击*/
 			$("#delete").click(function(){
				var selects=$("#table").bootstrapTable('getSelections');
				if(selects.length==0){
					alert("请选择要删除的权限组");
					return;
				}else if(selects.length > 1){
					alert("请正确选择要删除的权限组，且每次只能选择一个");
					return;
				}
				var id=$("#table").bootstrapTable('getSelections')[0].id;
				window.location='safe/deletepowergroup.do?id='+id;				
			}); 
		 });
	</script>


  </head>
  
  <body>
  	<!-- 引入导航头 -->
  	<%@include file="../../include/top.jsp" %>
  	<!-- 引入左侧导航 -->
  	<%@include file="../../include/left.jsp" %>
	<!-- 右侧区域 -->
	<div class="container">
		<div class="jumbotron" style="margin-left: 100px;">
			<!-- 增删改查按钮DIV -->
			<div>
				<table align="right" width="30%">
					<tr>
					<td><a href="safe/creatUserGroup.do?id=">新增</a></td>
					<td><button id="update">修改</button></td>
					<td><button id="delete">删除</button></td>
					</tr>
				</table>
			</div>
		<!-- 权限组列表 -->
			<div id="tableDiv" class="container">
				<table id="table" data-show-columns="false" data-search="true"
					data-show-refresh="true" data-show-toggle="true"
				 	data-click-to-select="true" data-pagination="false" 
				 	data-single-select="true" data-striped="true"> 
					<thead>
						<tr id="head">
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="id" data-visible="false" data-formatter="idFormatter">ID</th>
							<th data-field="userGroupName">用户组名称</th>
							<th data-field="unum">用户数</th>
							<th data-field="description">描述</th>
						</tr>
					</thead>
				</table>
			</div>
		<!-- 列表完成 -->	
		</div>
	</div>
	
	
  </body>
</html>
