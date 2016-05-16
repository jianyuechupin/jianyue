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
		 	/* $('#table').bootstrapTable({
				onDblClickRow:function(row, $element){
					alert("跳转到用户权限细则页面");
					//window.location='addpowergroup.do?id='+row.id;
				}
	        }); */
	        /**添加右击菜单功能*/
	        $('#table').bootstrapTable({
			    contextMenu: '#example1-context-menu',
			    onContextMenuItem: function(row, $el){
			        if($el.data("item") == "edit"){
			        	//$.post("userPowerDetails.do");
			            window.location='safe/userPowerDetails.do?uid='+row.uid;
			        } else if($el.data("item") == "delete"){
			            alert("删除记录: " + row.uid + ' ' + row.userName + ' ' + row.orgName);
			        } else if($el.data("item") == "action1"){
			            alert("其他操作: "+ row.uid + ' ' + row.userName + ' ' + row.orgName);
			        } 
			    }
			});
	        
		 	/**取得用户列表*/
 		 	$.post("safe/userPowerManageViews.do",function(data){
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
				alert("跳转到用户权限细则页面");
				//var id=$("#table").bootstrapTable('getSelections')[0].id;
				//window.location='addpowergroup.do?id='+id;
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
					<td><button id="update">修改</button></td>
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
						<tr id="head"><!-- data-visible="false" 设置列为不可见 -->
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="uid" data-visible="false">隐藏的ID列</th>
							<th data-field="orgName">单位</th>
							<th data-field="userName">姓名</th>
							<th data-field="powerGroup">系统账号</th>
							<th data-field="loginTypeString">登录方式</th>
						</tr>
					</thead>
				</table>
			</div>
		<!-- 列表完成 -->	
		</div>
		<!-- 右键菜单 -->
		
		<ul id="example1-context-menu" class="dropdown-menu">
		    <li data-item="edit" style="cursor:pointer"><a>修改</a></li>
		    <li data-item="delete" style="cursor:pointer"><a>删除</a></li>
		    <li data-item="action1" style="cursor:pointer"><a>其他行为1</a></li>
		</ul>
	</div>
	
	
  </body>
</html>
