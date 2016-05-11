<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>安全管理</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<%@include file="../include/base.jsp"%>

	<script type="text/javascript">
		$(function(){
			$('#userGroupAjax').click(function(){
				$("#tableDiv table").html("");
			});
		});
	</script>

</head>

<body>
	<!-- 引入导航头 -->
	<%@include file="../include/top.jsp"%>
	<!-- 引入左侧导航 -->
	<%@include file="../include/left.jsp"%>
	<!-- 右侧区域 -->
	<div id="show1" class="container">
		<div id="show" class="jumbotron" style="margin-left: 100px;">
		
			<div id="tableDiv" class="container">
				<table id="table" data-toggle="table"
					data-show-columns="true" data-search="false"
					data-show-refresh="true" data-show-toggle="true"
					data-pagination="true" > 
					<thead>
						<tr id="head">
							<th data-field="uid" data-formatter="idFormatter">序号</th>
							<th data-field="dname">单位</th>
							<th data-field="uname">姓名</th>
							<th data-field="post">系统账号</th>
							<th data-field="sex">性别</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
