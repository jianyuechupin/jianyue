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
 	 <%@include file="../include/base.jsp" %> 
	<script type="text/javascript">
		 $(function(){
		 	/**取得权限组列表*/
		 	$.post("getPowerGroups.do",function(data){
				$("#table").bootstrapTable('load', data);
			}); 
			
			
		 });
		/* BootstrapTable.DEFAULTS = {
			 onDblClickRow: function (item, $element) {
			 	alert(123);
			}
		} */
	/* 	$scope.bsTableControl={
			options:{
				rowStyle:function(row,index){
					return{
						classes:'none'
					};
				},
				onClickRow:function(row,tr){
					alert(row);
				}
			}
		} */
	</script>

  </head>
  
  <body>
  	<!-- 引入导航头 -->
  	<%@include file="../include/top.jsp" %>
  	<!-- 引入左侧导航 -->
  	<%@include file="../include/left.jsp" %>
	<!-- 右侧区域 -->
	<div class="container">
		<div class="jumbotron" style="margin-left: 100px;">
		<!-- 权限组列表 -->
			<div id="tableDiv" class="container">
				<table id="table" data-toggle="table"
					data-show-columns="true" data-search="false"
					data-show-refresh="true" data-show-toggle="true"
					data-pagination="true" > 
					<thead>
						<tr id="head">
							<th data-field="state" data-radio="true"></th>
							<th data-field="id" data-formatter="idFormatter">#</th>
							<th data-field="powerGroup">权限组名称</th>
							<th data-field="remark">备注</th>
						</tr>
					</thead>
				</table>
			</div>
			<!-- 列表完成 -->	
		</div>
	</div>
  </body>
</html>
